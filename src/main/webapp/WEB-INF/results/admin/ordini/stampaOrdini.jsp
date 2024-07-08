<%@ page import="java.util.List" %>
<%@ page import="model.ordineService.Ordine" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Elenco di ordini</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            display: block;
        }
        .blocco {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin: 15px;
            padding: 15px;

        }
        .blocco h3 {
            margin-top: 0;
        }
        .contenuto-ordine {
            margin-top: 10px;
            border-top: 1px solid #ccc;
            padding: 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;

        }
        .dettagli-ordine, .modifica-ordine {
            margin: 20px;
            margin-inline: auto;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }
        .dettagli-ordine span, .dettagli-ordine label,
        .modifica-ordine span, .modifica-ordine label {
            display: ruby;
            margin: 5px 0;
        }
        .modifica-ordine form, .modifica-matricola form, .remove-button form {
            margin-top: 10px;
        }
        .modifica-ordine form select {
            width: 100%;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
            margin: 10px;
        }
        .remove-button {
            margin-top: 10px;
        }

        .torna-indietro {
            margin-bottom: 20px;
        }
        input[type="submit"] {
            background-color: #427b8a;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color:  #3a6977;
        }
        /* Media queries per la reattività */
        @media (max-width: 768px) {
            .contenuto-ordine {
                flex-direction: column;
                align-items: center;
            }
            .dettagli-ordine, .modifica-ordine {
                width: 100%;
                margin: 10px 0;
            }
            .modifica-ordine form select {
                margin: 5px 0;
            }
        }
    </style>
</head>
<body>
<div class="torna-indietro">
    <form action="gestisci-ordini">
        <input type="submit" value="Torna indietro">
    </form>
</div>
<div class="container">
    <% List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordiniUtente");
    if((ordini.isEmpty()) || ordini == null){%>
    <h3>Non ci sono ordini</h3>
    <%
    }else{
    %>
    <h1>Scegli un ordine</h1><br>
    <c:forEach items="${ordiniUtente}" var="ordine">
        <div class="blocco">
            <h3>Ordine: ${ordine.idOrdine}</h3>
            <div class="contenuto-ordine">
                <div class="dettagli-ordine">
                    <label><h4>Costo: </h4> ${ordine.costo}</label>
                    <span><h4>Indirizzo spedizione: </h4> ${ordine.indirizzoSpedizione}, ${ordine.citta}</span>
                    <label><h4>Punti spesi: </h4> ${ordine.puntiSpesi}</label>
                    <label><h4>Punti ottenuti: </h4> ${ordine.puntiOttenuti}</label>
                    <span><h4>Data effettuazione: </h4> ${ordine.dataEffettuazione}</span>
                </div>
                <div class="modifica-ordine">

                    <span><h4>Data arrivo: </h4> ${ordine.dataArrivo}</span>
                    <form action="modifica-stato">
                        <input type="hidden" name="ordineID" value="${ordine.idOrdine}">
                        <input type="hidden" name="utenteScelto" value="${utenteScelto.email}">
                        <label for="stato"><h4>Stato dell'ordine:</h4></label>
                        <select name="stato" id="stato">
                            <option value="${ordine.stato}">${ordine.stato}</option>
                            <c:if test="${ordine.stato == 'In Lavorazione'}">
                                <option value="In Transito">In Transito</option>
                                <option value="Consegnato">Consegnato</option>
                            </c:if>
                            <c:if test="${ordine.stato == 'In Transito'}">
                                <option value="In Lavorazione">In Lavorazione</option>
                                <option value="Consegnato">Consegnato</option>
                            </c:if>
                            <c:if test="${ordine.stato == 'Consegnato'}">
                                <option value="In Lavorazione">In Lavorazione</option>
                                <option value="In Transito">In Transito</option>
                            </c:if>
                        </select>
                        <input type="submit" value="Cambia">
                    </form>
                    <form action="modifica-matricola">
                        <input type="hidden" name="utenteScelto" value="${utenteScelto.email}">
                        <input type="hidden" name="ordineID" value="${ordine.idOrdine}">
                        <input type="hidden" name="matricolaAttuale" value="${ordine.matricola}">
                        <h4>Matricola del gestore che si occupa dell'ordine:</h4> ${ordine.matricola}
                        <input type="submit" value="Modifica matricola">
                    </form>
                </div>
            </div>

            <div class="remove-button">
                <form action="elimina-ordine">
                    <input type="hidden" name="utenteScelto" value="${utenteScelto.email}">
                    <input type="hidden" name="idOrdine" value="${ordine.idOrdine}">
                    <input type="submit" value="Elimina Ordine">
                </form>
            </div>
        </div>
    </c:forEach>
    <%}%>
</div>
</body>
</html>
