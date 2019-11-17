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
            <c:if test="${pr.ticket_status == 0}">
                <li>${pr.ticket_id} ${pr.ticket_appnom}
            </c:if>
        </c:forEach>
    </ul>

</p>
    <form>
        Ticket besoin resolu : <input type="number" name="ticket" placeholder="Id Ticket" required/>  <input type="text" name="date" placeholder="Date" required/>
        <input type="text" name="commentaire" placeholder="Commentaire" required/>
        <button type="submit" name="TODO" value="resolu"> Changer!</button>
    </form>

</c:if>
<form>
    <button type="submit" name="TODO" value="noop">
        Retour</button>
</form>

</body>
</html>
