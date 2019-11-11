<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>La page de ${details.nom}</title>
</head>
<body>
Bonjour ${details.nom}!


<p>
    Les projets dont vous êtes responsable :
    <ul>
    <c:forEach items="${details.resp}" var="pr">
        <li>${pr}</li>
    </c:forEach>
    </ul>
</p>

<p>
    Les projets auxquels vous participez :
<ul>
    <c:forEach items="${details.part}" var="pp">
        <li>${pp}</li>
    </c:forEach>
</ul>
</p>


<form>
    Filtre : <input type="text" name="filtre"/>
    <button type="submit" name="TODO" value="filtrer">
        Filtrer!</button>
</form>

<form>
    Intitule : <input type="text" name="intitule"/>
    Description : <input type="text" name="description"/>
    <button type="submit" name="TODO" value="ajouterProjet">Ajouter</button>
</form>

<form>
    Nouveau surnom : <input type="text" name="surnom"/>
    <button type="submit" name="TODO" value="renommer">
        Changer</button>
</form>



</body>
</html>
