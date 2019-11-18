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

<center><h1 class="hit-the-floor">Gérer les applications</h1></center>
<center><form method="post" id="form">
        Responsable de l'application : <input type="text" name="app_responsable" id="app_responsable" value="${surnom}"/><br/><br/>
        ID du projet de l'application : <input type="text" name="app_proj_id" id="app_proj_id"/><br/><br/>
        Nom de l'application : <input type="text" name="app_nom" id="app_nom"/><br/><br/>
        <button type="submit" class="createUtilisateur" name="TODO" value="createApplication">Créer application</button>
    </form></center>
<center>
    Applications disponibles : <SELECT name="app_id" size="1"><br/><br/>
    <c:forEach items="${applications_created}" var="application">
    <OPTION selected>${application.app_nom}
        </SELECT>
<%--    <button class="logout">Supprimer</button><br/><br/>--%>
        ID de l'application <strong>${application.app_id}</strong><br/><br/>
        Responsable de l'application : <strong>${surnom}</strong><br/><br/>
<%--        ID du projet de l'application : <strong>${application.app_nom}</strong><br/><br/>--%>
        Nom de l'application : <strong>${application.app_nom}</strong><br/><br/>
    </c:forEach>
</center>
</body>
</html>
