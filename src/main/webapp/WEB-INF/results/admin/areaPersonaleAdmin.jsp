<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 04/07/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Personale Admin</title>
    <link rel="stylesheet" type="text/css" href="./css/footerAdmin.css">
    <link rel="stylesheet" type="text/css" href="./css/areaPersonale.css">

    <style>
        .header{
            text-align: center;
            border-bottom-style: groove;
            padding: 10px;
        }
    </style>

</head>
<body>
<%@include file="headerAdmin.jsp"%>
<div class="dati">
    <div class="azioni">
        <ul>
            <li>
                <div class="back-home">
                    <form action="index.html">
                        <input type="submit" value="Torna alla homepage">
                    </form>
                </div>
            </li>
            <li><a href="elimina-account" onclick="return confirm('Sei sicuro di voler eliminare il tuo account?')">Elimina Account</a></li>
            <li><a href="log-out" onclick="return confirm('Sei sicuro di voler fare logout?')">Logout</a></li>
        </ul>
    </div>

    <fieldset class="blocco">
        <legend><h3>Email</h3></legend>
        <ul>
            <li>${utente.email}</li>
        </ul>
    </fieldset>

    <fieldset class="blocco">
        <legend><h3>Password</h3></legend>
        <form action="modifica-password-supporto">
            <input type="submit" value="Modifica password" class="button">
        </form>
    </fieldset>

</div>
<%@include file="/WEB-INF/results/footer.jsp"%>
</body>
</html>
