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

    <link rel="stylesheet" type="text/css" href="./css/footerAdmin.css">
    <style>
        .header{
            text-align: center;
            border-bottom-style: groove;
            padding: 10px;
        }

        body {
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .header .icons .image{
            text-align: center;
        }
        .contenitore {
            margin-top: 50px;
            text-align: center;
        }
        .iconAmm {
            margin: 20px;
        }
        .iconAmm img {
            margin: 0 auto;
        }
        .iconAmm a {
            display: block;
            margin-top: 10px;
            font-size: 20px;
            text-decoration: none;
            color: #000;
        }
        .iconAmm a:hover {
            color: #3a6977 /* Cambia colore del testo al passaggio del cursore */
        }

        @media (max-width: 768px) {
            body {
                padding: 10px;
            }

            .iconAmm {
                margin: 10px;
            }

            .iconAmm img {
                height: auto;
            }

            .iconAmm a {
                font-size: 18px;
            }
        }

        @media (max-width: 480px) {

            .contenitore {
                padding: 10px;
            }

            .iconAmm {
                margin: 5px;
            }

            .iconAmm img {
                height: auto;
            }

            .iconAmm a {
                font-size: 16px;
            }
        }
    </style>
</head>
<body>
    <%@include file="headerAdmin.jsp"%>
    <div class="contenitore">
        <div class="iconAmm">
            <a href="gestisci-prodotti">
                <img src="./images/book-icon.png" width="50" height="50" class="image">
                <a href="gestisci-prodotti">Modifica Prodotti</a><br><br>
            </a>
        </div>

        <div class="iconAmm">
            <a href="gestisci-reparti">
                <img src="./images/elenco-icon.png" width="60" height="70" class="image">
                <a href="gestisci-reparti">Modifica Reparti</a><br><br>
            </a>
        </div>

        <div class="iconAmm">
            <a href="gestisci-sedi">
                <img src="./images/sede-icon.png" width="30" height="45" class="image">
                <a href="gestisci-sedi">Modifica Sedi</a><br><br>
            </a>
        </div>

        <div class="iconAmm">
            <a href="gestisci-ordini">
                <img src="./images/procurement.png" width="48" height="50" class="image">
                <a href="gestisci-ordini">Modifica Ordini</a><br><br>
            </a>
        </div>
    </div>
    <%@include file="/WEB-INF/results/footer.jsp"%>
</body>
</html>
