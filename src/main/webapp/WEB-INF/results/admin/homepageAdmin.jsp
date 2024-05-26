<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 15/05/2024
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin HomePage</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .contenitore {
            text-align: center;
        }
        .icon {
            margin: 20px;
        }
        .icon img {
            margin: 0 auto;
        }
        .icon a {
            display: block;
            margin-top: 10px;
            font-size: 20px;
            text-decoration: none;
            color: #000;
        }
        .icon a:hover {
            color: #3a6977 /* Cambia colore del testo al passaggio del cursore */
        }
    </style>
</head>
<body>
    <div class="contenitore">
        <div class="icon">
            <a href="gestisci-prodotti">
                <img src="./images/book-icon.png" width="50" height="50" class="image">
                <a href="gestisci-prodotti">Modifica Prodotti</a><br><br>
            </a>
        </div>

        <div class="icon">
            <a href="gestisci-reparti">
                <img src="./images/elenco-icon.png" width="60" height="70" class="image">
                <a href="gestisci-reparti">Modifica Reparti</a><br><br>
            </a>
        </div>

        <div class="icon">
            <a href="gestisci-sedi">
                <img src="./images/sede-icon.png" width="30" height="45" class="image">
                <a href="gestisci-sedi">Modifica Sedi</a>
            </a>
        </div>
    </div>

</body>
</html>
