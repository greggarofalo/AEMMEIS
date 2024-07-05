<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scegli i libri</title>
    <link rel="stylesheet" type="text/css" href="./css/aggiungiLibroStyle.css">
</head>
<body>
<h1>Scegli i libri da aggiungere al reparto ${reparto.idReparto}: ${reparto.nome}</h1>
<div class="torna-indietro">
    <form action="gestisci-reparti">
        <button type="submit">Torna indietro</button>
    </form>
</div>
<form action="insert-libroReparto">
    <input type="hidden" name="idReparto" value="${reparto.idReparto}">
    <ul>
        <c:forEach items="${libri}" var="libro">
            <li>
                <label>
                    <input type="checkbox" name="isbn" value="${libro.isbn}">
                    <strong>TITOLO:</strong> ${libro.titolo}, <strong> ISBN:</strong> ${libro.isbn}, <strong> ANNO PUBBLICAZIONE:</strong> ${libro.annoPubblicazioni}
                </label>
            </li>
        </c:forEach>
    </ul>
    <button type="submit">Aggiungi</button>
</form>
</body>
</html>
