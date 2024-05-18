<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: carla
  Date: 17/05/2024
  Time: 09:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione Sedi</title>
    <style>
        .sedeItem {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .sedeItem span {
            flex-grow: 1;
        }
        .buttons {
            margin-left: 2px;
            padding-right: 35px;
        }
        .buttons form {
            display: inline;
        }
    </style>
</head>

<body>
<h1>Lista delle sedi</h1>
<a href="nuova-sede">Aggiungi una nuova sede</a><br><br>
<c:forEach items="${sedi}" var="sede">
    <li> ${sede.citta} (${sede.via}, ${sede.civico})</li>
    <c:forEach items="${sede.libri}" var="libro">
        <ul>
            <li class="sedeItem">
                <span><b>${libro.titolo}</b>,  ISBN: ${libro.isbn},  ANNO PUBBLICAZIONE: ${libro.annoPubblicazioni}</span>
                <div class="buttons">
                    <form action="modifica-sede">
                        <input type="hidden" name="idSede" value="${sede.idSede}">
                        <input type="hidden" name="isbn" value="${libro.isbn}">
                        <input type="submit" value="Elimina">
                    </form>
                </div>
            </li>
        </ul>
    </c:forEach>
    <br><br>
    <form action="aggiungi-libro-sede">
    <input type="hidden" name="idSede" value="${sede.idSede}">
    <input type="submit" value="Aggiungi libro">
    </form>
    <form action="elimina-sede">
        <input type="hidden" name="idSede" value="${sede.idSede}">
        <input type="submit" value="Elimina Sede">
    </form>

</c:forEach>

</body>
</html>

