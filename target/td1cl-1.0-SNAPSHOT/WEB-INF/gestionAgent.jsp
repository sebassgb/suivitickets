<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="css/gestionAgent.css">
    <title>Gérer les agents</title>
</head>

<body>

    <form method="post">
        <button class="logout" name="TODO" value="noop">Fermer la session</button>
        <button class="gestionaire" name="TODO" value="gestionaire">Créer un projet</button>
    </form>

    <center>
        <h1 class="hit-the-floor">Géstion des agents</h1>
    </center>
    <center>
        <center>
            <h1 class="hit-the-floor">Bonjour à ${surnom}, vous êtes ${user_id}</h1>
        </center>

        Liste des agents :
        <form method="post">
            <select name="agent_select" id="username" size="1">
                <c:forEach items="${list_agent}" var="ag">
                    <c:if test="${ag.getUser_profil_id().equals('agent')}">
                        <option value="${ag.username}">${ag.username}</option>
                    </c:if>
                </c:forEach>
            </select>
            <button type="submit" name="TODO" value="agent"> Submit </button>
        </form>
        Tickets pris en charge de ${agent_select_username}:
        <c:forEach items="${ticket_pris_en_charge}" var="ticket_pris">
            <li>${ticket_pris.getTicket_id()} ${ticket_pris.getTicket_appnom()}</li>
        </c:forEach>
        <br>
        Tickets resolu de ${agent_select_username}:
        <c:forEach items="${ticket_resolu}" var="ticket">
            <li>${ticket.getTicket_id()} ${ticket.getTicket_appnom()}</li>
        </c:forEach>


    </center>
</body>

</html>