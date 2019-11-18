<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/statusTicket.css">
    <title>Status Ticket</title>
</head>
<body>
<form method="post">
    <button class="logout" name="TODO" value="noop">Fermer la session</button>
        <button class="createTicket" name="TODO">Créer Ticket</button>
</form>

<center><h1 class="hit-the-floor">Status des tickets déposés</h1></center>
<ul>
    <c:forEach items="${tickets_deposes}" var="tickets_client">
            <li>Date de dépot du ticket : <strong>${tickets_client.ticket_date}</strong> </li>
            <li>Titre du ticket : <strong>${tickets_client.ticket_title}</strong> </li>
            <li>Description du ticket : <br/><textarea name="desc_ticket" id="desc_ticket" cols="30" rows="5" disabled=true>${tickets_client.ticket_desc}</textarea> </li>
            <li>Nom de l'application : <strong>${tickets_client.ticket_appnom}</strong> </li>
            <li>Etat du ticket : <strong>${tickets_client.ticket_status}</strong> </li><br/><br/>
    </c:forEach>
</ul>

</body>
</html>
