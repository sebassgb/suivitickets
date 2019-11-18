<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/application.css">
    <title>Gérer les applications</title>
</head>
<body>
<form method="post">
    <button class="logout" name="TODO" value="noop">Fermer la session</button>
    <button class="utilisateurs">Utilisateurs</button>
    <button class="applications">Créer utilisateur</button>
</form>
        <a href="login.html"><button class="logout">Fermer la session</button></a>
        <a href="utilisateur.html"><button class="utilisateurs">Utilisateurs</button></a>
        <a href="admin.html"><button class="applications">Créer utilisateur</button></a>

<center><h1 class="hit-the-floor">Gérer les applications</h1></center>
<center><form method="post" id="form">
        ID de l'application : <input type="text" name="app_id" id="app_id"/><br/><br/>
        Responsable de l'application : <input type="text" name="app_responsable" id="app_responsable"/><br/><br/>
        ID du projet de l'application : <input type="text" name="app_proj_id" id="app_proj_id"/><br/><br/>
        Nom de l'application : <input type="text" name="app_nom" id="app_nom"/><br/><br/>
        <button type="submit" class="createUtilisateur">Créer application</button>
    </form></center>
<center>
    Applications disponibles : <SELECT name="app_id" id="app_id" size="1"><br/><br/>
        <OPTION>app01
        <OPTION>app02
        <OPTION selected>app03
        <OPTION>app04
        </SELECT><button class="logout">Supprimer</button><br/><br/>
        ID de l'application <strong>example</strong><br/><br/>
        Responsable de l'application : <strong>example</strong><br/><br/>
        ID du projet de l'application : <strong>example</strong><br/><br/>
        Nom de l'application : <strong>example</strong><br/><br/>
</center>
</body>
</html>
