<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 10/07/2024
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Stampa Carrello</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/carrelloStyle.css">

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
            padding-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <%@include file="header.jsp"%>
    <div class="content">
        <div class="book-list">
            <c:if test="${not empty results}">
                <c:forEach var="libro" items="${results}">
                    <div class="book-item">
                        <a href="mostra-libro?isbn=${libro.isbn}">
                            <img src="${libro.immagine}" alt="${libro.titolo}" class="book-image">
                        </a>
                        <div class="book-details">
                            <h3 class="book-title">${libro.titolo}</h3>
                            <div class="book-price">
                                <c:if test="${libro.sconto != 0}">
                                    <span class="book-discount">-${libro.sconto}%</span>
                                    <span class="book-new-price">${(libro.prezzo - (libro.prezzo * libro.sconto / 100))} €</span>
                                    <span class="book-old-price">${libro.prezzo} €</span>
                                </c:if>
                                <c:if test="${libro.sconto == 0}">
                                    <span class="book-new-price">${libro.prezzo} €</span>
                                </c:if>
                            </div>
                            <c:choose>
                                <c:when test="${wishList != null && wishList.libri.contains(libro)}">
                                    <c:set var="address" value="images/heartsBlack-icon.png"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="address" value="images/hearts-icon.png"/>
                                </c:otherwise>
                            </c:choose>
                            <div class="book-actions">
                                <button class="favorite-button" onclick="addToFavorites('${libro.isbn}')" data-isbn="${libro.isbn}">
                                    <img src="${address}" alt="Aggiungi ai preferiti">
                                </button>
                                <c:choose>
                                    <c:when test="${libro.disponibile}">
                                        <form action="aggiungi-carrello">
                                            <input type="hidden" name="isbn" value="${libro.isbn}">
                                            <input type="hidden" name="source" value="ricerca">
                                            <input type="hidden" name="q" value="${q}">
                                            <input type="image" src="./images/icon-cart.png" name="aggCarBut" alt="Carrello" width="20" height="20">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="isbn" value="${libro.isbn}">
                                        <input type="image" src="./images/icon-cart-disabled.png" name="aggCarBut" alt="Carrello" width="20" height="20">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <%@include file="footer.jsp"%>
</div>


<script>
    function addToFavorites(isbn) {
        // Crea una nuova istanza di XMLHttpRequest
        var xhttp = new XMLHttpRequest();

        // Definisce la funzione di gestione degli eventi per la risposta ricevuta
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                // La richiesta è stata completata e la risposta è pronta
                // Parsa la risposta JSON
                var data = JSON.parse(this.responseText);

                // Aggiorna l'icona del cuoricino
                const favoriteButton = document.querySelector(`button[class="favorite-button"][data-isbn="` + isbn + `"]`);
                if (data.isInWishList) {
                    favoriteButton.querySelector('img').src = "images/heartsBlack-icon.png";
                } else {
                    favoriteButton.querySelector('img').src = "images/hearts-icon.png";
                }
            } else {
                // Gestisci eventuali errori
                console.error('Errore durante l\'aggiunta ai preferiti:', this.status);
            }
        };

        // Imposta il metodo e l'URL della richiesta
        xhttp.open("POST", "aggiungi-ai-preferiti?isbn=" + isbn, true);

        // Imposta l'intestazione Content-Type
        xhttp.setRequestHeader("Content-Type", "application/json");

        // Invia la richiesta
        xhttp.send();

    }



</script>
</body>
</html>