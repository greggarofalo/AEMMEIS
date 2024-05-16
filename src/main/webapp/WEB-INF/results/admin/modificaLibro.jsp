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
</head>
<body>

<fieldset>
    <legend><h3 style="color: #3a6977">Nuovo Libro</h3></legend>
    <form action="aggiorna-libro">
        <label for="titolo">Titolo</label>
        <input type="text" id="titolo" name="titolo" value="${libro.titolo}"><br><br>
        <label for="isbn">Isbn</label>
        <input type="text" id="isbn" name="isbn" pattern="[0-9]{13}" value="${libro.isbn}" readonly><br><br>
        <label for="annoPubb">Anno Pubblicazione</label>
        <input type="text" id="annoPubb" name="annoPubb" value="${libro.annoPubblicazioni}"><br><br>
        <label for="genere">Genere</label>
        <input type="text" id="genere" name="genere" value="${libro.genere}"><br><br>
        <label for="prezzo">Prezzo</label>
        <input type="text" id="prezzo" name="prezzo" value="${libro.prezzo}"><br><br>
        <label for="sconto">Sconto</label>
        <input type="text" id="sconto" name="sconto" value="${libro.sconto}"><br><br>
        <label for="immagine">Genere</label>
        <input type="text" id="immagine" name="immagine" value="${libro.immagine}"><br><br>
        <label for="trama">Trama</label>
        <textarea id="trama" name="trama" rows="4" cols="50">${libro.trama}</textarea>
        <input type="submit" value="Modifica">

    </form>
</fieldset>

    <fieldset class="block" >
        <legend><h3 style="color: #3a6977">Sedi</h3></legend>
        <div style="text-align: left;">
            <ul>
                <c:forEach items="${sedi}" var="sede">
                    <li> ${sede.citta} (${sede.via}, ${sede.civico})</li>
                    <form action="eliminaLibro-sede">
                        <input type="hidden" name="isbn" value="${libro.isbn}">
                        <input type="hidden" name="idSede" value="${sede.idSede}">
                        <input type="submit" value="rimuovi">
                    </form>
                </c:forEach>
            </ul>
        </div>
        <div>
            <form action="aggiungiLibro-sede">
            <% int i=0; %>
            <c:forEach items="${sediNonPresenti}" var="sede2">
                <input type="checkbox" id="sede<%=i%>" name="sedeSelezionata" value="${sede2.idSede}">
                <label for="sede<%=i%>">${sede2.citta} (${sede2.via}, ${sede2.civico})</label><br>
            </c:forEach>

                <input type="hidden" name="isbn" value="${libro.isbn}">
                <input type="submit" value="Aggiungi">
            </form>

        </div>


    </fieldset>
    <fieldset class="block" >
        <legend><h3 style="color: #3a6977">Reparti</h3></legend>
        <div style="text-align: left;">
            <ul>
                <c:forEach items="${reparti}" var="reparto">
                    <li> ${reparto.descrizione}</li>
                    <form action="eliminaLibro-reparto">
                        <input type="hidden" name="isbn" value="${libro.isbn}">
                        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                        <input type="submit" value="rimuovi">
                    </form>
                </c:forEach>
            </ul>
        </div>
        <div>
            <form action="aggiungiLibro-reparto">
            <% i=0; %>
            <c:forEach items="${repartiNonPresenti}" var="reparto2">
                <input type="checkbox" id="reparto<%=i%>" name="repartoSelezionato" value="${reparto2.idReparto}">
                <label for="reparto<%=i%>">${reparto2.descrizione}</label><br>
            </c:forEach>

                <input type="hidden" name="isbn" value="${libro.isbn}">
                <input type="submit" value="Aggiungi">
            </form>

        </div>

    </fieldset>


</body>
</html>
