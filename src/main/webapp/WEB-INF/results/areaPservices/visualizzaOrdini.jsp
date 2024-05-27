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

    </style>
</head>
<body>
<%@include file="/WEB-INF/results/header.jsp"%>

    <div class="ordini">
        <c:forEach items="${ordini}" var="ordine">
            <div class="ordine">
                <ul>
                    <li><h3>ID ORDINE: </h3> ${ordine.idOrdine}</li>
                    <li><h3>COSTO: </h3> ${ordine.costo}€</li>
                    <li><h3>INDIRIZZO SPEDIZIONE: </h3> ${ordine.indirizzoSpedizione} (${ordine.citta})</li>
                    <li><h3>PUNTI OTTENUTI: </h3> ${ordine.puntiOttenuti}</li>
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

<%@include file="/WEB-INF/results/footer.jsp"%>
</body>
</html>
