<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione Ordini</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            margin-bottom: 20px;
        }
        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 15px;
        }
        .blocco {
            padding: 15px;
            border: 1px solid #333;
            border-radius: 8px;
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 200px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .blocco img {
            margin-bottom: 10px;
            width: 60px;
            height: 50px;
        }
        .blocco input[type="submit"] {
            text-decoration: none;
            font-family: sans-serif;
            font-size: medium;
            color: #000;
            background: none;
            border: none;
            cursor: pointer;
            padding: 8px 16px;
            border-radius: 4px;
            background-color: #e0e0e0;
            transition: background-color 0.3s;
            max-width: 180px; /* Imposta una larghezza massima per il pulsante */
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis; /*questi tre (white-space...)mi mettono i puntini sospensivi*/
            text-align: center;
        }


        .back-home input[type="submit"] {
            background-color: #e0e0e0;
            color: #000;
            font-size: medium;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            transition: background-color 0.3s;
            margin-top: 5px;
            margin-right: 30px;
            text-align: center;
            cursor: pointer;
        }

        .back-home input[type="submit"]:hover, .blocco input[type="submit"]:hover {
            background-color: #3a6977;
            color: #fff;
        }
        @media (max-width: 768px) {
            .blocco {
                max-width: 45%;
            }
        }
        @media (max-width: 480px) {
            .blocco {
                max-width: 100%;
            }
            .back-home input[type="submit"], .blocco input[type="submit"] {
                width: 100%;
                box-sizing: border-box;
            }
        }
    </style>
</head>
<body>

<h1>Scegli un utente</h1>
<div class="back-home">
    <form action="index.html">
        <input type="submit" value="Torna alla homepage">
    </form>
</div>
<div class="container">
    <c:if test="${utenti==null || utenti.isEmpty()}">
        <div class="blocco">
            Non ci sono utenti!
        </div>
    </c:if>
    <c:forEach items="${utenti}" var="utente">
        <div class="blocco">
            <img src="./images/delivery-icon.png" alt="Icona consegna">
            <form action="gestisci-ordiniByUtente">
                <input type="hidden" name="utenteScelto" value="${utente.email}">
                <input type="submit" value="${utente.email}" title="${utente.email}">
            </form>
        </div>
    </c:forEach>
</div>
</body>
</html>
