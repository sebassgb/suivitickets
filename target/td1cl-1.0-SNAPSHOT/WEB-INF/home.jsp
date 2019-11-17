<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 04/10/2019
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>La page de ${surnom}</title>
</head>
<body>
Bonjour à ${surnom}

<p>Les proj resp:
    <ul>
    <c:forEach items="${responsable}" var="pr">
        <li>${pr.intituleP} (${pr.descriptionP})</li>
    </c:forEach>
</ul>
</p>

<p>Les proj partcipe:
<ul>
    <c:forEach items="${participe}" var="pr">
        <li>${pr.intituleP} (${pr.descriptionP})</li>
    </c:forEach>
</ul>

</p>
<form>
    Filtre : <input type="text" name="filtre">
    <button type="submit" name="TODO" value="filtrer">Filtrer</button>
</form>
</body>
</html>
