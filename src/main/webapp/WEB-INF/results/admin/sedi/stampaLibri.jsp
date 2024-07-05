<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: carla
  Date: 17/05/2024
  Time: 09:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scegli i libri</title>
    <link rel="stylesheet" type="text/css" href="./css/aggiungiLibroStyle.css">
</head>
<body>
<h1>Scegli i libri da aggiungere alla sede ${sede.idSede}: ${sede.citta} (${sede.via}, ${sede.civico})</h1>
<div class="torna-indietro">
    <form action="gestisci-sedi">
        <button type="submit">Torna indietro</button>
    </form>
</div>
<form action="insert-libroSede">
    <input type="hidden" name="idSede" value="${sede.idSede}">
    <ul>
        <c:forEach items="${libri}" var="libro">
            <li>
                <label>
                    <input type="checkbox" name="isbn" value="${libro.isbn}">
                        ${libro.titolo}, ISBN: ${libro.isbn}, ANNO PUBBLICAZIONE: ${libro.annoPubblicazioni}
                </label>
            </li>
        </c:forEach>
    </ul>
    <button type="submit">Aggiungi</button>
</form>
</body>
</html>
