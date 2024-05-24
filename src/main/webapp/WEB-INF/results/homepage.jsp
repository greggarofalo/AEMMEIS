<%@ page import="model.wishList.WishList" %>
<%@ page import="model.libroService.Libro" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>AEMME-HOMEPAGE</title>
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">

</head>
<body>
 <%@include file="header.jsp"%>

    <% WishList wishList = (WishList) session.getAttribute("wishList");
    List<Libro> libri = (List<Libro>) request.getAttribute("libriHome");
    int i=0;%>
 <c:forEach items="${libriHome}" var="libro">
    <div>
        <li>${libro.titolo}, ISBN: ${libro.isbn}, ANNO PUBBLICAZIONE: ${libro.annoPubblicazioni}
        </li>
        <% String path="./images/hearts-icon.png";
        if(wishList!=null && wishList.getLibri()!=null) {
            if(wishList.getLibri().contains(libri.get(i))){
                path="./images/heartsBlack-icon.png";
            }
        }
        i++;%>
        <%--<a href="show-wishlist">
            <img src="<%=path%>>" width="20" height="20" class="image">
        </a>
        <a href="cart-servlet>">
            <img src="./images/icon-cart.png" width="20" height="20" class="spaced-image image " >
        </a>--%>
        <form action="modifica-preferiti">
            <input type="hidden" name="isbn" value="${libro.isbn}">
            <input type="image" src="<%=path%>" name="aggPreferBut" alt="Preferiti" width="20" height="20">
        </form>
        <form action="aggiungi-carrello">
            <input type="hidden" name="isbn" value="${libro.isbn}">
            <input type="image" src="./images/icon-cart.png" name="aggCarBut" alt="Carrello" width="20" height="20">
        </form>

    </c:forEach>
    </div>

<%@include file="footer.jsp"%>

</body>
</html>