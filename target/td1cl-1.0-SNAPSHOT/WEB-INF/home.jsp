<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="css/agent.css">
    <title>Bienvenue ${surnom}</title>
</head>

<body>
    <form method="post">
        <button class="logout" name="TODO" value="noop">Fermer la session</button>
    </form>


    <center>
        <h1 class="hit-the-floor">Bonjour à ${surnom}, vous êtes ${user_id}</h1>
    </center>
        <p class="hit-the-floor">Liste des tickets non résolus :</p>
        <ul>
            <c:forEach items="${responsable_ticket}" var="pr">
                <c:if test="${pr.ticket_status == 0}">
                    <li><h3>ID du ticket : ${pr.ticket_id}</h3>
                        <h3>Nom de l'application : ${pr.ticket_appnom}</h3>
                        <h3>Client qui dépôt : ${pr.ticket_client_creator}</h3>
                        <form method="post">
                            <p>
                                <select class="optionTicket" name="role">
                                    <option value="${pr.ticket_id}OK${surnom}"> Prendre en charge</option>
                                    <option value="${pr.ticket_id}"> Liberer</option>
                                </select>
                                <button type="submit" name="TODO" value="charge"> Ok </button>
                            </p>
                        </form>

                        <c:choose>
                            <c:when
                                test="${pr.ticket_aut == true &&  pr.ticket_responsable.getUsername().equals(surnom)}">
                                <form id="form" method="post">
                                    Ticket besoin resolu : <input class="envoyerTicket" type="text" name="surnom"
                                        value="${surnom}" required> <input class="envoyerTicket" type="hidden"
                                        name="ticket" placeholder="Id Ticket" value="${pr.ticket_id}" required />
                                    <input type="date" name="date" id="date_ticket" value="2019-11-22" min="2019-01-01"
                                           max="12-31-2019" required>
                                    <input
                                        class="envoyerTicket" type="text" name="commentaire" placeholder="Commentaire"
                                        required />

                                    <button type="submit" name="TODO" value="resolu"> Changer!</button>
                                </form>
                            </c:when>
                        </c:choose>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
        </p>
</body>

</html>