<%@ page import="model.utenteService.Utente" %>
<%@ page import="model.ordineService.Ordine" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 26/05/2024
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza Ordini</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">

    <style>

        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: Arial, sans-serif;
        }
        .wrapper {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .content {
            flex: 1;
            padding: 20px;
        }
        footer {
            margin-top: auto;
        }
        .ordini {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        .ordine {
            border: 1px solid #2b2d30;
            border-radius: 8px;
            padding: 20px;
            background-color: #f9f9f9;
        }
        .ordine ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        .ordine ul li {
            margin-bottom: 10px;
        }
        .ordine h3 {
            display: inline;
            font-size: 1em;
            margin: 0;
            font-weight: normal;
        }
        .ordine .button {
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none; /* Rimuove il contorno */
            padding: 6px;
            border-radius: 10px; /* Bordo arrotondato */
            margin-top: 5px;
        }
        .ordine .button:hover {
            background-color: #356876;
        }

        input[type="submit"] {
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none; /* Rimuove il contorno */
            padding: 6px;
            border-radius: 5px; /* Bordo arrotondato */
            margin-top: 5px;
            margin-bottom: 7px;
        }

        input[type="submit"]:hover {
            background-color: #356876;
        }
        @media (max-width: 768px) {
            .ordine {
                padding: 15px;
            }
        }
    </style>
</head>
<body>

<div class="wrapper">
    <%@include file="/WEB-INF/results/header.jsp"%>
        <div class="content">

            <form action="area-personale">
                <input type="submit" value="Torna indietro">
            </form>

            <div class="ordini">
                <c:forEach items="${ordini}" var="ordine">
                    <div class="ordine">
                        <ul>
                            <li><h3>ID ORDINE: </h3> ${ordine.idOrdine}</li>
                            <li><h3>COSTO: </h3> ${ordine.costo}€</li>
                            <li><h3>INDIRIZZO SPEDIZIONE: </h3> ${ordine.indirizzoSpedizione} (${ordine.citta})</li>
                            <% Utente utente = (Utente) session.getAttribute("utente");
                            if(utente.getTipo().equalsIgnoreCase("premium")){%>
                            <li><h3>PUNTI OTTENUTI: </h3> ${ordine.puntiOttenuti}</li>
                            <%}%>
                            <li><h3>DATA EFFETTUAZIONE: </h3> ${ordine.dataEffettuazione}</li>
                            <li>
                                <h3>STATO: </h3> ${ordine.stato}
                                <c:if test="${ordine.dataArrivo} != null ">
                                    <h3> IN DATA: </h3> ${ordine.dataArrivo}
                                </c:if>
                            </li>
                        </ul>
                    <form action="riepilogo-ordine">
                        <input type="hidden" name="idOrdine" value="${ordine.idOrdine}">
                        <input type="submit" value="Riepilogo ordine" class="button">
                    </form>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%@include file="/WEB-INF/results/footer.jsp"%>
</div>
</body>
</html>
