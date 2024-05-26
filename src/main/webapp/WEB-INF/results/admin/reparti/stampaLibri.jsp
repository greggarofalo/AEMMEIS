<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scegli i libri</title>
</head>
<body>
    <h1>Scegli i libri da aggiungere al reparto ${reparto.idReparto}: ${reparto.nome}</h1>
    <form action="insert-libroReparto">
        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
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
