<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 14/05/2024
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Modifica Libro</title>
    <link rel="stylesheet" type="text/css" href="css/formStyle2.css">
</head>
<body>

<div class="form-conteiner">
    <div class="torna-indietro">
        <form action="gestisci-prodotti">
            <input type="submit" value="Torna indietro">
        </form>
    </div>
    <div class="form">
        <h3>Nuovo Libro</h3>
        <div class="info-libro">
            <form action="aggiorna-libro" method="post">
                <label for="titolo">Titolo</label>
                <input type="text" id="titolo" name="titolo" value="${libro.titolo}" required><br><br>
                <label for="isbn">Isbn</label>
                <input type="text" id="isbn" name="isbn" pattern="[0-9]{13}" value="${libro.isbn}" readonly><br><br>
                <label for="annoPubb">Anno Pubblicazione</label>
                <input type="text" id="annoPubb" name="annoPubb" value="${libro.annoPubblicazioni}" pattern="[0-9]{4}" required title="Inserisci un anno di 4 cifre (es. 2024)"><br><br>
                <label for="genere">Genere</label>
                <input type="text" id="genere" name="genere" value="${libro.genere}" required><br><br>
                <label for="prezzo">Prezzo</label>
                <input type="text" id="prezzo" name="prezzo" value="${libro.prezzo}" pattern="^[0-9]+(\.[0-9]{1,2})?$" required><br><br>
                <label for="sconto">Sconto</label>
                <input type="text" id="sconto" name="sconto" value="${libro.sconto}" pattern="[0-9]{0,3}" oninput="validateSconto()"><br><br>
                <label for="immagine">Immagine</label>
                <input type="text" id="immagine" name="immagine" value="${libro.immagine}"><br><br>
                <label for="trama">Trama</label>
                <textarea id="trama" name="trama" rows="4" cols="50">${libro.trama}</textarea>
                <input type="submit" value="Modifica">
            </form>
        </div>
    </div>

    <div class="form" >
        <h3>Reparti</h3>
        <div style="text-align: left;">
            <c:forEach items="${reparti}" var="reparto">
                <p>${reparto.nome}</p>
                <form action="eliminaLibro-reparto">
                    <input type="hidden" name="isbn" value="${libro.isbn}">
                    <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                    <input type="submit" value="rimuovi">
                </form>
            </c:forEach>
        </div>

        <div class="checkbox">
            <form action="aggiungiLibro-reparto">
                <% int i=0; %>
                <c:forEach items="${repartiNonPresenti}" var="reparto2">
                    <input type="checkbox" id="reparto<%=i%>" name="repartoSelezionato" value="${reparto2.idReparto}">
                    <label for="reparto<%=i%>">${reparto2.nome}</label><br>
                    <% i++;%>
                </c:forEach>

                <input type="hidden" name="isbn" value="${libro.isbn}">
                <input type="submit" value="Aggiungi">
            </form>
        </div>
    </div>

    <div class="form" >
        <div style="text-align: left;">
            <h3>Sedi</h3>
            <c:forEach items="${sedi}" var="sede">
                <p>${sede.citta} (${sede.via}, ${sede.civico})</p>
                <form action="eliminaLibro-sede">
                    <input type="hidden" name="isbn" value="${libro.isbn}">
                    <input type="hidden" name="idSede" value="${sede.idSede}">
                    <input type="submit" value="rimuovi">
                </form>
            </c:forEach>
        </div>
        <div class="checkbox">
            <form action="aggiungiLibro-sede">
                <% i=0; %>
                <c:forEach items="${sediNonPresenti}" var="sede2">
                    <input type="checkbox" id="sede<%=i%>" name="sedeSelezionata" value="${sede2.idSede}">
                    <label for="sede<%=i%>">${sede2.citta} (${sede2.via}, ${sede2.civico})</label><br>
                    <%i++;%>
                </c:forEach>

                <input type="hidden" name="isbn" value="${libro.isbn}">
                <input type="submit" value="Aggiungi">
            </form>
        </div>
    </div>

</div>


<script>
    function validateSconto() {
        var scontoInput = document.getElementById('sconto');
        var scontoValue = scontoInput.value.trim(); // Ottieni il valore e rimuovi spazi vuoti

        // Se il campo è vuoto, non fare alcuna validazione
        if (scontoValue === "") {
            scontoInput.setCustomValidity(""); // Nessun messaggio di errore
            return;
        }

        // Verifica se il valore è un numero valido tra 0 e 100
        var intValue = parseInt(scontoValue);
        if (isNaN(intValue) || intValue < 0 || intValue > 100) {
            scontoInput.setCustomValidity("Inserisci un numero valido tra 0 e 100.");
        } else {
            scontoInput.setCustomValidity(""); // Nessun messaggio di errore personalizzato
        }
    }
</script>

</body>
</html>
