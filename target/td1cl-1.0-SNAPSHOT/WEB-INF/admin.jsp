<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/admin.css">
    <title>Créer Ticket</title>
</head>
<body>
        <a href="login.html"><button class="logout">Fermer la session</button></a>
        <a href="utilisateur.html"><button class="utilisateurs">Utilisateurs</button></a>
        <a href="applications.html"><button class="applications">Applications</button></a>

<center><h1 class="hit-the-floor">Créer un utilisateur</h1></center>
<center><form method="post" id="form">
    Définir username : <input type="text" name="username" id="username"/><br/><br/>
    Définir mot de passe : <input type="text" name="password" id="password"/><br/><br/>
    Définir le rol de l'utilisateur : <SELECT name="user_profil_id" id="user_profil_id" size="1"><br/><br/>
        <OPTION>client
        <OPTION>agent
        <OPTION selected>gestionaire
        <OPTION selected>admin
        </SELECT><br/><br/>
    <button type="submit" class="createUtilisateur">Créer utilisateur</button>
</form></center>
</body>
</html>
