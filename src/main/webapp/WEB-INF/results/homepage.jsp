<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>AEMME-HOMEPAGE</title>
    <link rel="stylesheet" type="text/css" href="./css/topCss.css">

</head>
<body>

<div class="container">
    <a href="show-wishlist">
        <img src="./images/hearts-icon.png" width="25" height="25" class="image">
    </a>

    <a href="cart-servlet">
        <img src="./images/icon-cart.png" width="25" height="25" class="spaced-image image " >
    </a>

    <a href="area-personale">
        <img src="./images/icon-user.png" width="24" height="25" class="image">
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