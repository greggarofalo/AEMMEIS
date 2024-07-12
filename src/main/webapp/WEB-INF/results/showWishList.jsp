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
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/carrelloStyle.css">

</head>
<body>

<div class="wrapper">
    <%@include file="header.jsp"%>
        <div class="content">
            <h1>Wish List</h1>
            <div class="book-list">
            <c:forEach items="${wishList.libri}" var="libro">
                <div class="book-item">
                    <a href="mostra-libro?isbn=${libro.isbn}">
                        <img src="${libro.immagine}" alt="${libro.titolo}<" class="book-image">
                    </a>
                    <div class="book-details">
                        <h3 class="book-title">${libro.titolo}</h3>
                        <div class="book-actions">
                            <a href="modifica-preferiti?isbn=${libro.isbn}&source=wishList">
                                <img src="images/heartsBlack-icon.png" alt="togli dai preferiti" width="30" height="30" class="image">
                            </a>
                            <%--<form action="modifica-preferiti">
                                <input type="hidden" name="isbn" value="${libro.isbn}">
                                <input class="favorite-button"  type="submit" name="rimuoviPref" value="images/heartsBlack-icon.png">
                            </form>--%>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
        <%@include file="footer.jsp"%>
</div>

</body>
</html>
