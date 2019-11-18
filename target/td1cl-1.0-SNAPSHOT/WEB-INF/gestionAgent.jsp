<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="css/gestionAgent.css">
    <title>Gérer les agents</title>
</head>
<body>

        <form method="post">
            <button class="logout" name="TODO" value="noop">Fermer la session</button>
            <a href="gestionaire.html"><button class="gestionaire">Créer un projet</button></a>
        </form>

<center><h1 class="hit-the-floor">Géstion des agents</h1></center>
<center>
<%--        Liste des agents : <SELECT name="username" id="username" size="1"><br/><br/>--%>
<%--            <OPTION>agent01--%>
<%--            <OPTION>agent02--%>
<%--            <OPTION selected>agent03--%>
<%--            <OPTION>agent04--%>
<%--            </SELECT><br/><br/>--%>
<%--            Tickets pris en charge <strong>example</strong><br/><br/>--%>
<%--            Tickets résolues : <strong>example</strong><br/><br/>--%>
    <center><h1 class="hit-the-floor">Bonjour à ${surnom}, vous êtes ${user_id}</h1></center>

    Liste des agents :
    <form method="post">
        <select name="agent_select" id="username" size="1">
            <c:forEach items="${list_agent}" var="ag">
                <c:if test="${ag.getUser_profil_id().equals('agent')}">
                    <option value="${ag.username}">${ag.username}</option>
                </c:if>
            </c:forEach>
        </select>
        <button type="submit" name="TODO" value="agent"> Submit </button>

    </form>





</center>
</body>
</html>
