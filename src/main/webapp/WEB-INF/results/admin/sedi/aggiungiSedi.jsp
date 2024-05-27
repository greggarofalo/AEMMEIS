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
    <link rel="stylesheet" type="text/css" href="./css/formStyle.css">
</head>
<body>
<div class="form">
    <h3>Nuova Sede</h3>
    <form action="aggiungi-sede">
        <label for="citta">Città</label>
        <input type="text" id="citta" name="citta"> <br><br>
        <label for="via">Via</label>
        <input type="text" id="via" name="via"> <br><br>
        <label for="civico">Civico</label>
        <input type="text" id="civico" name="civico"> <br><br>
        <label for="cap">Cap</label>
        <input type="text" id="cap" name="cap"><br>
        <input type="submit" value="Aggiungi">
    </form>
</div>
</body>
</html>

