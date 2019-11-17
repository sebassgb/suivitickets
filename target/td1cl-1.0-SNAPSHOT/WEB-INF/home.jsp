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
Bonjour à ${surnom}, vous êtes ${user_id}

<c:if test="${user_id.equals('agent')}">
    <p>Les tickets resp:
    <ul>
        <c:forEach items="${responsable_ticket}" var="pr">
            <li>${pr.ticket_id} ${pr.ticket_appnom}</li>
        </c:forEach>
    </ul>
</p>
</c:if>



<%--</p>--%>
</body>
</html>
