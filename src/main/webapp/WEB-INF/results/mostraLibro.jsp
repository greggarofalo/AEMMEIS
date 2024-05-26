<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 25/05/2024
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Libro</title>
    <link rel="stylesheet" type="text/css" href="./css/libro.css">
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">

</head>
<body>
<%@include file="header.jsp"%>
<div class="book-container">
    <h1>${libro.titolo}</h1>
    <h2>di
        <c:forEach items="${autori}" var="autore" varStatus="status"><!--Aggiunta dell'attributo varStatus="status" al tag forEach
         Questo introduce una variabile status che tiene traccia dello stato del loop,
         inclusa la proprietà last che indica se l'elemento corrente è l'ultimo nell'iterazione.-->
            <h2> ${autore.nome} ${autore.cognome}</h2><c:if test="${!status.last}">, </c:if>
        </c:forEach>
    </h2>
    <div class="book-details">
        <div class="book-image">
            <img src="${libro.immagine}" alt="${libro.titolo}">
        </div>
        <div class="book-info">
            <p>Prezzo: <span id="prezzo-originale" class="original-price">${libro.prezzo}€</span>
                <c:choose>
                    <c:when test="${libro.sconto != 0}">
                        <span id="prezzo-scontato" class="discounted-price"></span>
                        (<span id="percentuale-sconto">-${libro.sconto}%</span>)
                    </c:when>
                    <c:otherwise>
                        <span id="prezzo-scontato" class="discounted-price">${libro.prezzo}€</span>
                    </c:otherwise>
                </c:choose>
            </p>

            <button class="add-to-cart">Aggiungi al carrello</button>

        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script>
    function calcolaPrezzoScontato(prezzo, sconto) {
        return prezzo - (prezzo * sconto / 100);
    }

    window.onload = function() {
        // Recupera gli elementi che contengono i dati del prezzo e dello sconto
        var prezzoElement = document.getElementById("prezzo-originale");
        var scontoElement = document.getElementById("percentuale-sconto");
        var prezzoScontatoElement = document.getElementById("prezzo-scontato");

        // Recupera i valori dal contenuto degli elementi
        var prezzo = parseFloat(prezzoElement.textContent.replace('€', '').trim());
        var sconto = parseFloat(scontoElement.textContent.replace('-', '').replace('%', '').trim());

        // Calcola il prezzo scontato
        var prezzoScontato = calcolaPrezzoScontato(prezzo, sconto).toFixed(2);

        // Aggiorna l'elemento del prezzo scontato con il valore calcolato
        prezzoScontatoElement.textContent = prezzoScontato + ' €';
    };
</script>

</body>
</html>
