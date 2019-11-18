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
            <button class="gestionAgent" name="TODO" value="agent   ">Agents</button></>
        </form>

<center><h1 class="hit-the-floor">Créer un projet</h1></center>

 <center><h1 class="hit-the-floor">Bonjour à ${surnom}, vous êtes ${user_id}</h1></center>

        <center><form method="post" id="form">
    Responsable du projet : <input type="text" name="" id="resp_proj"/><br/><br/>
    Description du projet : <textarea name="desc_proj" id="desc_proj" cols="30" rows="5"></textarea><br/><br/>

<%--    Application dans le projet: <SELECT name="app_proj_id" id="app_proj_id" size="1"><br/><br/>--%>
<%--        <OPTION>app01--%>
<%--        <OPTION>app02--%>
<%--        <OPTION selected>app03--%>
<%--        <OPTION>app04--%>
<%--        </SELECT><br/><br/>--%>
    

            <button type="submit" class="createProjet">Créer projet</button>
</form></center>
</body>
</html>
