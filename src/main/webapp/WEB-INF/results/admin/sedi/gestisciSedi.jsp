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
        body {
            width: 100%;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif; /* Utilizza Arial come font predefinito */
        }

        h1 {
           text-align: center;
            margin-top: 30px; /* Aggiungi spazio sopra il titolo */
        }

        h3 {
            border-top: 1px solid #ddd;
            padding-top: 25px;
            font-family: 'Arial', sans-serif; /* Font dell'intestazione */
            margin-bottom: 20px; /* Spazio sotto l'intestazione */
            margin-top: 30px; /* Aggiungi spazio sopra il titolo */
            margin-left:50px;
        }

        .sedeItem {
            display: flex;
            align-items: center;
            margin-left:50px;
            margin-top: 10px; /* Aggiungi spazio sopra */
        }

        .buttons{
            margin-left:auto;/* Spinge i bottoni all'estrema destra */
            right: 50px;
        }

        input[type="submit"] {
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none; /* Rimuove il contorno */
            padding: 6px;
            border-radius: 10px; /* Bordo arrotondato */
            margin-top: 5px;
            margin-left: 50px;
        }

        input[type="submit"]:hover {
            background-color: #356876;
        }

        @media (max-width: 768px) {
            h3, .sedeItem {
                margin-left: 5%; /* Riduci il margine per schermi piccoli */
                margin-right: 5%; /* Riduci il margine per schermi piccoli */
            }
        }

        @media (max-width: 480px) {
            .sedeItem {
                flex-direction: column; /* Dispone gli elementi in colonna su schermi piccoli */
                align-items: flex-start; /* Allinea gli elementi a sinistra */
            }

            .buttons {
                margin-left: 0;
                margin-top: 10px; /* Aggiungi spazio sopra i bottoni */
            }
            input[type="submit"] {
                margin-left:5px;
            }
        }
    </style>
</head>

<body>
<h1>Lista delle sedi</h1>
<div class="back-home">
    <form action="index.html">
        <input type="submit" value="Torna alla homepage">
    </form>
</div>
<div class="nuova-sede">
    <form action="nuova-sede">
        <input type="hidden" name="idSede" value="${sede.idSede}">
        <input type="submit" value="Nuova Sede">
    </form>
</div>

<c:forEach items="${sedi}" var="sede">
    <h3> ${sede.citta} (${sede.via}, ${sede.civico})</h3>
    <c:forEach items="${sede.libri}" var="libro">
        <div class="sedeItem">
            <span><b>TITOLO: </b>${libro.titolo},<b> ISBN:</b> ${libro.isbn},<b> ANNO PUBBLICAZIONE:</b> ${libro.annoPubblicazioni}</span>
            <div class="buttons">
                <form action="modifica-sede">
                    <input type="hidden" name="idSede" value="${sede.idSede}">
                    <input type="hidden" name="isbn" value="${libro.isbn}">
                    <input type="submit" value="Elimina">
                </form>
            </div>
        </div>
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

