<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 08/05/2024
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Personale</title>
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
</head>
<body>

<div class="container">
    <a href="show-wishlist">
        <img src="../../images/hearts-icon.png" width="25" height="25" class="image">
    </a>

    <a href="cart-servlet>">
        <img src="../../images/icon-cart.png" width="25" height="25" class="spaced-image image " >
    </a>

    <a href="area-personale">
        <img src="../../images/icon-user.png" width="24" height="25" class="image">
    </a>
</div>

<ul>
    <c:forEach items="${reparti}" var="reparto">
        <li>${reparto.descrizione}
        </li>
    </c:forEach>
</ul>

</body>
</html>
