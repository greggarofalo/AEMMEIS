<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 25/04/2024
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        p  {
            color: dimgrey;
            text-align: center;
        }
        .block {
            display: inline-block; /* Imposta le immagini come blocchi inline */
            margin-right: 100px;
            margin-left: 100px;
        }

        fieldset {
            width: 400px; /* Imposta la larghezza del fieldset */
            padding: 10px; /* Aggiunge spazio interno al fieldset */
            border: 2px solid #000; /* Aggiunge un bordo al fieldset */
        }
    </style>
</head>
<body>
<div>
    <fieldset class="block">
        <legend><h3 style="color: #3a6977">Accedi</h3></legend>
        <form action="login-servlet">
            <label for="email">e-mail</label>
            <input type="email" id="email" name="email"><br><br>
            <label for="pw">password</label>
            <input type="password" id="pw" name="pw"><br><br>
            <input type="submit" value="Accedi" align="right">
        </form>
    </fieldset>

    <form action="registrazione.html" class="block">
        <p>
            <h3 style="color: #3a6977">Non sei ancora registrato?</h3><br>
            Registrati inserendo i tuoi dati.<br>
            La registrazione è semplice e gratuita.<br><br>
            <input type="submit" value="Registrati">

        </p>
    </form>
</div>

</body>
</html>
