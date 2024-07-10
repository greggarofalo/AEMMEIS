<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 30/05/2024
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordine effettuato</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <style>
        .ordine {
            margin: 30px;
            display: flex;
            flex-direction: row;
            gap: 30px;
            align-items: center;
        }
        .ordine .image{
            width: 60%;
            box-shadow: 0 0 30px #427b8a; /* Ombra leggera attorno al contenitore */
            border-radius: 8px; /* Angoli arrotondati */
        }

        .words input[type="submit"]{
            margin-top: 20px;
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none; /* Rimuove il contorno */
            padding: 6px;
            border-radius: 5px; /* Bordo arrotondato */
        }
        .words input[type="submit"]:hover{
            background-color: #356876;
        }

        .words p{
            margin: 5px;
            border-radius: 4px;
            border: 2px solid #356876;
            padding: 10px;
            box-shadow: 0 0 2px 5px #427b8a, 0 0 7px 10px #afb2b3;

        }

        @media (max-width: 560px) {
            .ordine{
                flex-direction: column;
            }
        }
    </style>
</head>
<body>
        <div class="ordine">
            <img src="./images/carrello.png" class="image">
            <div class="words">
                <p>
                    L'ordine è avvenuto con successo...

                </p><br>
                <p>
                    Premi il pulsante termina per tornare alla Homepage
                </p>
                <form action="do-ordine">
                    <input type="hidden" value="${ordine.indirizzoSpedizione}" name="indirizzo">
                    <input type="hidden" value="${ordine.citta}" name="citta">
                    <input type="hidden" value="${ordine.puntiSpesi}" name="punti">
                    <input type="hidden" value="${ordine.costo}" name="costo">
                    <input type="submit" value="Termina">
                </form>
            </div>
        </div>
        <%@include file="footer.jsp"%>
</body>
</html>
