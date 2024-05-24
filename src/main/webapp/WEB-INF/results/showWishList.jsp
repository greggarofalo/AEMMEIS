<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 17/05/2024
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>WishList</title>
</head>
<body>
<h1>Wish List</h1>
<c:forEach items="${wishList.libri}" var="libro">
    <li>${libro.titolo}, ISBN: ${libro.isbn}, ANNO PUBBLICAZIONE: ${libro.annoPubblicazioni}
    </li>
    <form action="modifica-preferiti">
        <input type="hidden" name="isbn" value="${libro.isbn}">
        <input type="submit" name="rimuoviPref" value="Rimuovi">
    </form>
</c:forEach>
<a href="index.html">HomePage</a>

</body>
</html>
