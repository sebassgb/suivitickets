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
        <button class="utilisateurs" name="TODO" value="admin">Utilisateurs</button>
        <button class="applications" name="TODO" value="Utilisateur">Créer utilisateur</button>
    </form>

    <center>
        <h1 class="hit-the-floor">Gérer les applications</h1>
    </center>
    <center>
        <form method="post" id="form">
            Responsable de l'application :
            <select name="app_responsable" required>
                <c:forEach items="${list_admin}" var="ag">
                        <option value="${ag.username}">${ag.username}</option>
                </c:forEach>
            </select>
            <br />
            assigner au projet avec ID :
            <SELECT name="app_proj_id" size="1">
                <c:forEach items="${projets_created}" var="projet">
                    <OPTION value="${projet.getProj_id()}">${projet.getProj_id()} ${projet.getProj_responsable().getUsername()}
                </c:forEach>
            </SELECT><br /><br />
            Nom de l'application : <input type="text" name="app_nom" id="app_nom" required/><br /><br />
            <button type="submit" class="createUtilisateur" name="TODO" value="createApplication">Créer
                application</button>
        </form>
    </center>
    <center>
        Applications disponibles :<br /><br /><br /><br />
        <c:forEach items="${applications_created}" var="application">
            <%--    <button class="logout">Supprimer</button><br/><br/>--%>
            Application dans le projet avec ID : <strong>${application.getApp_proj_id()}</strong><br /><br />
            Responsable de l'application :
            <strong>${application.getApp_responsable().getUsername()}</strong><br /><br />
            Nom de l'application : <strong>${application.getApp_nom()}</strong><br /><br />
            ID de l'application : <strong>${application.getApp_id()}</strong><br /><br /><br /><br />
        </c:forEach>
    </center>
</body>

</html>