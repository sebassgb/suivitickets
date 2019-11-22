<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/gestionaire.css">
    <title>Créer un projet</title>
</head>
<body>
        <form method="post">
            <button class="logout" name="TODO" value="noop">Fermer la session</button>
            <button class="gestionAgent" name="TODO" value="agent">Agents</button>
        </form>

<center><h1 class="hit-the-floor">Créer un projet</h1></center>

 <center><h1 class="hit-the-floor">Bonjour à ${surnom}, vous êtes ${user_id}</h1></center>

        <center><form method="post" id="form">
    Responsable du projet : <input type="text" name="resp_proj" id="resp_proj"/><br/><br/>
    Description du projet : <textarea name="desc_proj" cols="30" rows="5" id="desc_proj"></textarea><br/><br/>
    Application dans le projet : <SELECT multiple="multiple" name="application_select" id="app_proj"><br/><br/>
                <c:forEach items="${applications_created}" var="application">
                <option value="${application}">${application.getApp_nom()}</option>
                    </c:forEach>
            </SELECT><br/><br/>
<%--    Application dans le projet: <c:forEach items="${applications}" var="application">--%>
<%--            <option value="${application.getApp_nom()}">${application.getApp_nom()}</option>--%>
<%--        </c:forEach>--%>
            <button class="createProjet" type="submit" name="TODO" value="creerprojet">Créer projet</button>
</form></center>
</body>
</html>
