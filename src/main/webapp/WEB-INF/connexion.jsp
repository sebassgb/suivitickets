<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <title>Login</title>
</head>

<body>
    <center><img src="img/insa.jpg" alt="INSA Logo" width="200px" height="100px" id="img"></center>
    <center>
        <h1 class="hit-the-floor">Système de dépôt et de suivi de tickets de maintenance</h1>
    </center>
    <center>
        <h2>Sebastian GIRALDO, Hai DINH</h2>
    </center>
    <center>
        <h3>Professeur : Matthieu EXBRAYAT</h3>
    </center>
    <center>
        <form method="post" id="form">
            Username : <input type="text" name="login" id="username" /><br /><br />
            Password : <input type="password" name="password" id="password" /><br /><br />
            <button type="submit" class="signin" name="TODO" value="log">Se logguer</button>
        </form>
    </center>
</body>

</html>