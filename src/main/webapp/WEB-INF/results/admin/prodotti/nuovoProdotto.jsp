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
        <input type="text" id="titolo" name="titolo" required><br><br>
        <label for="isbn">Isbn</label>
        <input type="text" id="isbn" name="isbn" pattern="[0-9]{13}" required><br><br>
        <label for="genere">Genere</label>
        <input type="text" id="genere" name="genere" required><br><br>
        <label for ="annoPubb">Anno Pubblicazione</label>
        <input type="text" id="annoPubb" name="annoPubb" required><br><br>
        <label for="prezzo">Prezzo</label>
        <input type="text" id="prezzo" name="prezzo" required><br><br>
        <label for="sconto">Sconto</label>
        <input type="text" id="sconto" name="sconto"><br><br>
        <label for="trama">Trama</label>
        <textarea id="trama" name="trama"> </textarea><br><br>
        <label for="immagine">Immagine</label>
        <input type="text" id="immagine" name="immagine"><br>

        <div id="authors">
            <h4>Autori</h4>
            <div class="author">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" required><br><br>
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome" name="cognome" required><br><br>
                <label for="cf">Codice Fiscale</label>
                <input type="text" id="cf" name="cf" required><br><br>
            </div>
        </div>
        <button type="button" onclick="addAuthor()">
            <img src="images/add-icon3.png" alt="Menu" width="18" height="18"><br>
        </button>
        <br><br>
        <input type="submit" value="Inserisci">
    </form>
</div>

</body>

<script>
function addAuthor() {
    // Creazione di un nuovo div per il set di campi dell'autore
    var div = document.createElement('div');
    div.className = 'author';

    // Creazione dei campi di input per nome, cognome e codice fiscale
    div.innerHTML = `
    <label for="nome">Nome</label>
    <input type="text" id="nome" name="nome" required><br><br>
    <label for="cognome">Cognome</label>
    <input type="text" id="cognome" name="cognome" required><br><br>
    <label for="cf">Codice Fiscale</label>
    <input type="text" id="cf" name="cf" required><br><br>
    <br><br>
    `;

    // Aggiunta del nuovo set di campi al div contenitore degli autori
    document.getElementById('authors').appendChild(div);
}
</script>
</html>
