<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/createTicket.css">
    <title>Créer Ticket</title>
</head>
<body>
        <a href="login.html"><button class="logout">Fermer la session</button></a>
        <a href="statusTicket.html"><button class="statusTicket">Tickets déposés</button></a>

<center><h1 class="hit-the-floor">Créer un ticket de maintenance</h1></center>
<center><form method="post" id="form">
    Date du ticket : <input type="date" name="date_ticket" id="date_ticket" value="2019-11-22" min="2019-01-01" max="12-31-2019"/><br/><br/>
    Titre du ticket : <input type="text" name="title_ticket" id="title_ticket"/><br/><br/>
    Description du ticket : <textarea name="desc_ticket" id="desc_ticket" cols="30" rows="5"></textarea><br/><br/>
    Nom de l'application : <SELECT name="name_application" id="name_application" size="1"><br/><br/>
        <OPTION>Android
        <OPTION>Apple
        <OPTION selected>Huaweii
        </SELECT><br/><br/>
    <button type="submit" class="envoyerTicket">Envoyer ticket</button>
</form></center>
</body>
</html>
