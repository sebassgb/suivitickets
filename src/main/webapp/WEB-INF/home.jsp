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
    <link rel="stylesheet" type="text/css" href="css/agent.css">
    <title>Bienvenue ${surnom}</title>
</head>
<body>
    <form method="post">
        <button class="logout" name="TODO" value="noop">Fermer la session</button>
    </form>
        

<center><h1 class="hit-the-floor">Bonjour à ${surnom}, vous êtes ${user_id}</h1></center>

<c:if test="${user_id.equals('agent')}">
    <p class="hit-the-floor">Liste des tickets non résolus :</p>
    <ul>
        <c:forEach items="${responsable_ticket}" var="pr">
            <c:if test="${pr.ticket_status == 0}">
                <li>${pr.ticket_id} ${pr.ticket_appnom}
                    <form method="post">
                    <p>
                        <select class="optionTicket" name="role">
                            <option value="${pr.ticket_id}OK${surnom}"> Prendre en charge</option>
                            <option value="${pr.ticket_id}"> Liberer</option>
                        </select>
                        <button type="submit" name="TODO" value="charge"> Submit </button>
                    </p>
                </form>

                    <c:if test="${pr.ticket_aut == true}">
                        <c:choose>
                            <c:when test="${pr.ticket_responsable.getUsername().equals(surnom)}">
                                <form id="form">
                                    Ticket besoin resolu : <input class="envoyerTicket"  type="number" name="ticket" placeholder="Id Ticket" value="${pr.ticket_id}" required/>  <input class="envoyerTicket" type="text" name="date" placeholder="Date" required/>
                                    <input  class="envoyerTicket" type="text" name="commentaire" placeholder="Commentaire" required/>
                                    <button type="submit" name="TODO" value="resolu"> Changer!</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <script type="text/javascript">
                                    alert("Ce ticket a pris en charge");
                                </script>
                            </c:otherwise>

                        </c:choose>
                </c:if>

                </li>
            </c:if>
        </c:forEach>
    </ul>

</p>
</c:if>
</body>
</html>