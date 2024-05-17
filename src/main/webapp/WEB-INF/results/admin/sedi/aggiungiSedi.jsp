<%--
  Created by IntelliJ IDEA.
  User: carla
  Date: 17/05/2024
  Time: 09:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aggiungi la nuova sede</title>
</head>
<body>
<fieldset>
    <legend><h3 style="color: #3a6977">Nuova Sede</h3></legend>
    <form action="aggiungi-sede">
        <label for="citta">Citta</label>
        <textarea id="citta" name="citta"> </textarea><br><br>
        <label for="via">Via</label>
        <textarea id="via" name="via"> </textarea><br><br>
        <label for="civico">Civico</label>
        <textarea id="civico" name="civico"> </textarea><br><br>
        <label for="cap">Cap</label>
        <textarea id="cap" name="cap"> </textarea><br><br>
        <input type="submit" value="Aggiungi">
    </form>
</fieldset>
</body>
</html>

