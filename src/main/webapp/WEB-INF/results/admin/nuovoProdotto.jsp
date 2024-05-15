<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 15/05/2024
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci Libro</title>
</head>
<body>
<fieldset>
    <legend><h3 style="color: #3a6977">Nuovo Libro</h3></legend>
    <form action="insert-libro">
        <label for="titolo">Titolo</label>
        <input type="text" id="titolo" name="titolo"><br><br>
        <label for="isbn">Isbn</label>
        <input type="text" id="isbn" name="isbn" pattern="[0-9]{16}"><br><br>
        <label for="genere">Genere</label>
        <input type="text" id="genere" name="genere"><br><br>
        <label for = "annoPubb">Anno Pubblicazione</label>
        <input type="text" id="annoPubb" nome="annoPubb"><br><br>
        <label for="prezzo">Prezzo</label>
        <input type="text" id="prezzo" name="prezzo"><br><br>
        <label for="sconto">Sconto</label>
        <input type="text" id="sconto" name="sconto"><br><br>
        <label for="trama">Trama</label>
        <textarea id="trama" name="trama"> </textarea><br><br>
        <label for="immagine">Sconto</label>
        <input type="text" id="immagine" name="trama"><br><br>
        <input type="submit" value="Inserisci">
    </form>
</fieldset>

</body>
</html>
