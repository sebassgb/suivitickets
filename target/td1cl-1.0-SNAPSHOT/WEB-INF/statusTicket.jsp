<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/statusTicket.css">
    <title>Status Ticket</title>
</head>
<body>
        <a href="login.html"><button class="logout">Fermer la session</button></a>
        <a href="createTicket.html"><button class="createTicket">Créer Ticket</button></a>

<center><h1 class="hit-the-floor">Status des tickets déposés</h1></center>
<center>
    Date de dépot du ticket : <strong>2019-11-05</strong><br/><br/>
    Titre du ticket : <strong>App iphone</strong><br/><br/>
    Description du ticket : <textarea name="desc_ticket" id="desc_ticket" cols="30" rows="5" disabled=true>Ne marche pas</textarea><br/><br/>
    Nom de l'application : <strong>Apple</strong><br/><br/>
    <center><button id="ticket_status" disabled=true>Non résolue</button> </center>

</center>
</body>
</html>
