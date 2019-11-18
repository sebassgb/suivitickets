<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/utilisateur.css">
    <title>Liste des utilisateurs</title>
</head>
<body>
        <a href="login.html"><button class="logout">Fermer la session</button></a>
        <a href="admin.html"><button class="utilisateurs">Créer utilisateurs</button></a>
        <a href="applications.html"><button class="applications">Applications</button></a>

<center><h1 class="hit-the-floor">Liste des utilisateurs</h1></center>

   <ul>
       <li>User1 <SELECT name="user_profil_id" id="user_profil_id" size="1">
        <OPTION>client
        <OPTION>agent
        <OPTION selected>gestionaire
        <OPTION selected>admin
        </SELECT><button class="autorisation">Modifier autorisation</button><button class="logout">Supprimer</button></li>
       <li>User2 <SELECT name="user_profil_id" id="user_profil_id" size="1">
            <OPTION>client
            <OPTION>agent
            <OPTION selected>gestionaire
            <OPTION selected>admin
            </SELECT><button class="autorisation">Modifier autorisation</button><button class="logout">Supprimer</button></li>
       <li>User3 <SELECT name="user_profil_id" id="user_profil_id" size="1">
            <OPTION>client
            <OPTION>agent
            <OPTION selected>gestionaire
            <OPTION selected>admin
            </SELECT><button class="autorisation">Modifier autorisation</button><button class="logout">Supprimer</button></li>
   </ul>

</body>
</html>
