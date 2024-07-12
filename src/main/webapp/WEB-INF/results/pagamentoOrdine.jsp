<%@ page import="model.utenteService.Utente" %>
<%@ page import="model.tesseraService.Tessera" %>
<%@ page import="model.tesseraService.TesseraDAO" %><%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 30/05/2024
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pagamento Ordine</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="css/formStyle2.css">
    <style>
        .form{
            margin:auto;
            margin-bottom: 15px;
        }

    </style>

</head>
<body>

<%@include file="header.jsp"%>

<div class="form">
    <form action="pagamento-effettuato" method="post">
        <input type="hidden" value="${ordine.indirizzoSpedizione}" name="indirizzo">
        <input type="hidden" value="${ordine.citta}" name="citta">
        <div class="form-pag">
            <label for="cardName">Nome sulla Carta</label>
            <input type="text" id="cardName" name="cardName" required>
        </div>

        <div class="form-pag">
            <label for="cardNumber">Numero della Carta</label>
            <input type="text" id="cardNumber" name="cardNumber" pattern="\d{16}" required><!--l pattern \d{16} è una regex (espressione regolare)
                                                                                            \d: corrisponde a qualsiasi cifra numerica (da 0 a 9)-->
        </div>

        <div class="form-pag">
            <label for="cvv">CVV</label>
            <input type="text" id="cvv" name="cvv" pattern="\d{3}" required>
        </div>

        <div class="form-pag">
            <label for="expiryDate">Data di Scadenza</label>
            <input type="date" id="expiryDate" name="expiryDate" required>
        </div>

        <% Utente utente = (Utente) session.getAttribute("utente");
            TesseraDAO tesseraDAO = new TesseraDAO();
            Tessera tessera = tesseraDAO.doRetrieveByEmail(utente.getEmail());
            if(utente.getTipo().equalsIgnoreCase("Premium")){
        %>
            <div class="form-pag">
                <label for="punti">Vuoi sfruttare dei punti accumulati? (disponibili: <%=tessera.getPunti()%>)</label>
                <input type="text" id="punti" name="punti" oninput="aggiornaCosto()" max="<%= tessera.getPunti() %>" min="0">
            </div>
        <%}%>

        <div class="form-pag">
           <br><br><label for="costoAggiornato">Costo Aggiornato:</label>
            <span id="costoAggiornato">${ordine.costo}</span> €
        </div>


        <input type="hidden" id="costoIniziale" value="${ordine.costo}" name="costo">
        <input type="submit" value="Paga Ora">
    </form>
</div>

<%@include file="footer.jsp"%>

    <script>
        function aggiornaCosto() {
            let punti = document.getElementById('punti').value;
            if(!punti)
                punti = 0;
            let costoOrdine = document.getElementById('costoIniziale').value;
            let costoAggiornato = document.getElementById('costoAggiornato');
            let sconto = parseInt(punti) * 0.10; // Ogni punto vale 10 centesimi di euro
            let nuovoCosto = Math.max(0, parseFloat(costoOrdine) - sconto);
            costoAggiornato.innerHTML = nuovoCosto.toFixed(2); // Mostra il nuovo costo con due cifre decimali
        }
    </script>
</body>
</html>
