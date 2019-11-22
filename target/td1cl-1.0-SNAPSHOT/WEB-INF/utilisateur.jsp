<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/utilisateur.css">
    <title>Liste des utilisateurs</title>
</head>
<body>
    <form method="post">
        <button class="logout" name="TODO" value="noop">Fermer la session</button>
        <button class="utilisateurs" name="TODO" value="Utilisateur">Créer utilisateurs</button></a>
        <button class="applications" name="TODO" value="application">Applications</button>
    </form>

<center><h1 class="hit-the-floor">Liste des utilisateurs</h1></center>

    <center><h1 class="hit-the-floor">Bonjour à ${surnom}, vous êtes ${user_id}</h1></center>


    <c:forEach items="${list_utilisateurs}" var="utilisateurs" varStatus="utilisateurs.getUsername()">
        <c:if test="${utilisateurs.getUser_profil_id() != surnom}">
            <ul>
                <li>Utilisateur: ${utilisateurs.getUsername()} <br>
                Role: ${utilisateurs.getUser_profil_id()}
                    <br>
                    Quelle rôle vous avez besoin de changer?  <form method="post">
                        <select name="role_change" id="user_profil_id">
                            <option value="${utilisateurs.getUsername()}+gestionaire" selected> Gestionnaire
                            <option value="${utilisateurs.getUsername()}+agent"> Agent
                            <option value="${utilisateurs.getUsername()}+client"> Client
                            <option value="${utilisateurs.getUsername()}+admin"> Administrateur
                        </select>
                        <button class="autorisation" type="submit" name="TODO" value="autorisation"> Modifier autorisation </button>
                        <button class="logout" type="submit" name="TODO" value="supprimerUtilisateur">Supprimer</button>
                    </form>

                </li>

            </ul>
        </c:if>
    </c:forEach>
</body>
</html>
