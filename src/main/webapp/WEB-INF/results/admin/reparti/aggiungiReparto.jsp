<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 15/05/2024
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aggiungi il nuovo reparto</title>
</head>
<body>
<fieldset>
    <legend><h3 style="color: #3a6977">Nuovo Reparto</h3></legend>
    <form action="aggiungi-reparto">
        <label for="nome">Nome</label>
        <input type="text" id="nome" name="nome"><br><br>
        <label for="descrizione">Descrizione</label>
        <textarea id="descrizione" name="descrizione"> </textarea><br><br>
        <label for="immagine">Immagine</label>
        <input type="text" id="immagine" name="immagine"><br><br>
        <input type="submit" value="Aggiungi">
    </form>
</fieldset>
</body>
</html>
