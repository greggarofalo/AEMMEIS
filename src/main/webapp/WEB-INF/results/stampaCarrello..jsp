<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 08/05/2024
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stampo Carrello</title>
</head>
<body>
    <c:forEach items="${rigaCarrello}" var="libro">
        <li>${libro.titolo}
        </li>
    </c:forEach>

</body>
</html>
