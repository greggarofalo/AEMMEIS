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
    <link rel="stylesheet" type="text/css" href="css/formStyle2.css">
</head>
<body>
<div class="form">
    <h3>Nuovo Libro</h3>
    <form action="insert-libro">
        <label for="titolo">Titolo</label>
        <input type="text" id="titolo" name="titolo"><br><br>
        <label for="isbn">Isbn</label>
        <input type="text" id="isbn" name="isbn" pattern="[0-9]{13}"><br><br>
        <label for="genere">Genere</label>
        <input type="text" id="genere" name="genere"><br><br>
        <label for ="annoPubb">Anno Pubblicazione</label>
        <input type="text" id="annoPubb" name="annoPubb"><br><br>
        <label for="prezzo">Prezzo</label>
        <input type="text" id="prezzo" name="prezzo"><br><br>
        <label for="sconto">Sconto</label>
        <input type="text" id="sconto" name="sconto"><br><br>
        <label for="trama">Trama</label>
        <textarea id="trama" name="trama"> </textarea><br><br>
        <label for="immagine">Immagine</label>
        <input type="text" id="immagine" name="immagine"><br>
        <input type="submit" value="Inserisci">
    </form>
</div>

</body>
</html>
