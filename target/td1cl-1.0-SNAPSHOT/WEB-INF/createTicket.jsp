<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/createTicket.css">
    <title>Créer Ticket</title>
</head>
<body>
<form method="post">
        <button class="logout" name="TODO" value="noop">Fermer la session</button>
        <button class="statusTicket" name="TODO" value="ticketDepose">Tickets déposés</button>
</form>

<center><h1 class="hit-the-floor">Créer un ticket de maintenance</h1></center>
<center><form method="post" id="form">
    Date du ticket : <input type="date" name="date_ticket" id="date_ticket" value="2019-11-22" min="2019-01-01" max="12-31-2019" required/><br/><br/>
    Titre du ticket : <input type="text" name="title_ticket" id="title_ticket" required/><br/><br/>
    Nom de l'utilisateur : <input type="text" name="username" id="username" required value="${surnom}"/><br/><br/>
    Description du ticket : <textarea name="desc_ticket" id="desc_ticket" cols="30" rows="5">l'application ne marche pas comme prevu</textarea><br/><br/>
    Nom de l'application : <SELECT name="name_application" id="name_application" size="1"><br/><br/>
         <c:forEach items="${applications_created}" var="application">
             <option value="${application.getApp_nom()}">${application.getApp_nom()}</option>
         </c:forEach>
        </SELECT><br/><br/>

    <button type="submit" class="envoyerTicket" name="TODO" value="createTicket">Envoyer ticket</button>
</form></center>
</body>
</html>
