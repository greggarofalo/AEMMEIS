<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione Reparti</title>
    <style>
        .repartoItem {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .repartoItem span {
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
<h1>Lista dei reparti</h1>
<a href="nuovo-reparto">Aggiungi un nuovo reparto</a><br><br>
<c:forEach items="${reparti}" var="reparto">
    <h3>${reparto.idReparto}: ${reparto.descrizione}</h3>
    <c:forEach items="${reparto.libri}" var="libro">
        <ul>
            <li class="repartoItem">
                <span><b>${libro.titolo}</b>,  ISBN: ${libro.isbn},  ANNO PUBBLICAZIONE: ${libro.annoPubblicazioni}</span>
                <div class="buttons">
                    <form action="modifica-reparto">
                        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                        <input type="hidden" name="isbn" value="${libro.isbn}">
                        <input type="submit" value="Elimina">
                    </form>
                </div>
            </li>
        </ul>
    </c:forEach>
    <br><br><form action="aggiungi-libro">
        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
        <input type="submit" value="Aggiungi libro">
    </form>
    <form action="elimina-reparto">
        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
        <input type="submit" value="Elimina Reparto">
    </form>

</c:forEach>

</body>
</html>
