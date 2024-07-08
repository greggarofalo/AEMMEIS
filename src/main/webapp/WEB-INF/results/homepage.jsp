<%@ page import="model.wishList.WishList" %>
<%@ page import="model.libroService.Libro" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>AEMME-HOMEPAGE</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/homePageStyle.css">
    <!--questo style aggiuntivo serve per spostare il footer in basso alla bagina-->
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
        }
        .wrapper {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .content {
            flex: 1;
        }
        footer {
            margin-top: auto;
        }
    </style>
</head>

<body>
    <div class="wrapper">
        <%@include file="header.jsp"%>
            <div class="content">

                <% WishList wishList = (WishList) session.getAttribute("wishList");
                List<Libro> libri = (List<Libro>) request.getAttribute("libriHome");
                int i=0;%>
                <div class="griglia">
                 <c:forEach items="${libriHome}" var="libro">
                    <div class="item">
                        <a href="mostra-libro?isbn=${libro.isbn}">
                            <img class="img" src="${libro.immagine}">
                        </a>

                        <% String path="./images/hearts-icon.png";
                            if(wishList!=null && wishList.getLibri()!=null) {
                                if(wishList.getLibri().contains(libri.get(i))){
                                    path="./images/heartsBlack-icon.png";
                                }
                            }
                            i++;%>

                        <div class="azioni">
                            <form action="modifica-preferiti">
                                <input type="hidden" name="isbn" value="${libro.isbn}">
                                <input type="image" src="<%=path%>" name="aggPreferBut" alt="Preferiti" width="20" height="20">
                            </form>

                            <c:choose>
                                <c:when test="${libro.disponibile}">
                                    <form action="aggiungi-carrello">
                                        <input type="hidden" name="isbn" value="${libro.isbn}">
                                        <input type="image" src="./images/icon-cart.png" name="aggCarBut" alt="Carrello" width="20" height="20">
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="isbn" value="${libro.isbn}">
                                    <input type="image" src="./images/icon-cart-disabled.png" name="aggCarBut" alt="Carrello" width="20" height="20">
                                </c:otherwise>
                            </c:choose>
                        </div>


                        <p class="titolo">${libro.titolo}</p>
                        <p class=autori>
                            <c:forEach var="autore" items="${libro.autori}" varStatus="status">
                                ${autore.nome}${autore.cognome}
                                <c:if test="${!status.last}">
                                    ,
                                </c:if>
                            </c:forEach>
                        </p>
                    </div>
                 </c:forEach>
                </div>
            </div>
        <%@include file="footer.jsp"%>
    </div>


</body>
</html>