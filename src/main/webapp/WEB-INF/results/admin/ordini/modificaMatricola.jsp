<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matricole</title>
    <link rel="stylesheet" type="text/css" href="./css/aggiungiLibroStyle.css">
    <style>
        input[type="radio"]{
            margin-right: 10px;
        }
        @media (max-width: 600px) {
            form {
                padding: 10px;
            }

            button {
                width: 100%;
                box-sizing: border-box;
                float: none;
                margin-top: 10px;
            }

            .torna-indietro button {
                width: 100%;
                box-sizing: border-box;
                float: none;
                margin-top: 10px;
            }

            li {
                flex-direction: column;
                align-items: flex-start;
            }

            input[type="radio"] {
                margin-bottom: 5px;
            }
        }
    </style>
</head>
<body>
<h3>Scegli matricola</h3>
<div class="torna-indietro">
    <form action="gestisci-ordiniByUtente">
        <input type="hidden" name="utenteScelto" value="${utenteScelto.email}">
        <button type="submit">Torna indietro</button>
    </form>
</div>
<form action="cambia-gestore">
    <input type="hidden" name="utenteScelto" value="${utenteScelto.email}">
    <input type="hidden" name="ordineID" value="${ordineAttuale.idOrdine}">
    <ul>
        <c:forEach items="${gestori}" var="gestore">
            <li>
                <input type="radio" id="${gestore.matricola}" name="matricola" value="${gestore.matricola}">
                <label for="${gestore.matricola}">${gestore.matricola}</label>
            </li>
        </c:forEach>
    </ul>
    <button type="submit">Scegli</button>
</form>
</body>
</html>
