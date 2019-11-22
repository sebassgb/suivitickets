<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/admin.css">
    <title>Créer Ticket</title>
</head>
<body>
    <form method="post">
        <button class="logout" name="TODO" value="noop">Fermer la session</button>
        <button class="utilisateurs" name="TODO" value="admin">Utilisateurs</button></a>
        <button class="applications">Applications</button>
    </form>

<center><h1 class="hit-the-floor">Créer un utilisateur</h1></center>
<center><form method="post" id="form">
    Définir username : <input type="text" name="username" id="username"/><br/><br/>
    Définir mot de passe : <input type="text" name="password" id="password"/><br/><br/>
    Définir le rol de l'utilisateur : <SELECT name="user_profil_id" id="user_profil_id" size="1"><br/><br/>
            <option value="gestionaire" selected> Gestionnaire
            <option value="agent"> Agent
            <option value="client"> Client
            <option value="admin"> Administrateur
        </SELECT><br/><br/>
    <button type="submit" class="createUtilisateur">Créer utilisateur</button>

</form></center>
</body>
</html>
