<%@ page import="model.utenteService.Utente" %><%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 25/05/2024
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica password</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">

    <style>

        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
        }
        .wrapper {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .content {
            flex: 1;
        }
        footer {
            margin-top: auto;
        }

        .pass{
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 20px; /* Add some top padding */
            margin-bottom: 50px;
        }

        .pass form{
            width: 100%;
            margin:15px;
        }

        .pass .pm{
            text-align: center;
        }

        .pass .pm .bottone{
            text-align: right;
        }

        input[type="submit"] {
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none; /* Rimuove il contorno */
            padding: 6px;
            border-radius: 5px; /* Bordo arrotondato */
            margin-top: 5px;
        }

        input[type="submit"]:hover {
            background-color: #356876;
        }

    </style>
</head>
<body>
<div class="wrapper">
    <%@include file="/WEB-INF/results/header.jsp"%>
    <div class="content">

        <div class="pass">
            <form action="area-personale">
                <input type="submit" value="Torna indietro">
            </form>

            <form action="modifica-password" class="pm">
                <label for="pass">Password</label>
                <input type="password" id="pass" name="password" placeholder="*****"  maxlength="16" required><br><br>
                <input type="submit" value="Conferma" class="bottone">
            </form>
        </div>
    </div>
    <%@include file="/WEB-INF/results/footer.jsp"%>
</div>

</body>
</html>
