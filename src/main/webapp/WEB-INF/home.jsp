
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
        

<center><h1 class="hit-the-floor">Bonjour à ${surnom}, vous êtes ${user_id}</h1></center>
<center>
    <p class="hit-the-floor">Liste des tickets non résolus :</p>
    <ul>
        <c:forEach items="${responsable_ticket}" var="pr">
            <c:if test="${pr.ticket_status == 0}">
                <li>ID Ticket: ${pr.ticket_id}, Nom de l'application: ${pr.ticket_appnom}, Client creer: ${pr.ticket_client_creator}
                    <form method="post">
                    <p>
                        <select class="optionTicket" name="role">
                            <option value="${pr.ticket_id}OK${surnom}"> Prendre en charge</option>
                            <option value="${pr.ticket_id}"> Liberer</option>
                        </select>
                        <button type="submit" name="TODO" value="charge" > Submit </button>
                    </p>
                    </form>

                    <c:choose>
                        <c:when test="${pr.ticket_aut == true &&  pr.ticket_responsable.getUsername().equals(surnom)}">
                                <form id="form" method="post">
                                    Ticket besoin resolu : <input class="envoyerTicket" type="text" name="surnom" value="${surnom}" required> <input class="envoyerTicket"  type="number" name="ticket" placeholder="Id Ticket" value="${pr.ticket_id}" required/>  <input class="envoyerTicket" type="date" name="date" placeholder="Date" min="2019-01-01" max="12-31-2019" id="date_ticket" required/><input  class="envoyerTicket" type="text" name="commentaire" placeholder="Commentaire" required/>

                                    <button type="submit" name="TODO" value="resolu"> Changer!</button>
                                </form>
                        </c:when>
                    </c:choose>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</p>
</center>
</body>
</html>