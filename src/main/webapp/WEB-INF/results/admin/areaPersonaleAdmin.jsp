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
  <!--  <link rel="stylesheet" type="text/css" href="./css/areaPersonale.css">-->

    <style>
        .header{
            text-align: center;
            border-bottom-style: groove;
            padding: 10px;
        }
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }

        .content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .dati {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            max-width: 900px;
            gap: 60px;
        }

        .azioni {
            margin: 20px;
            text-align: center;
        }

        .azioni ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            padding-right: 30px;
        }

        .azioni ul li {
            margin-bottom: 10px;
        }

        .azioni ul li a {
            background-color:#2b2d30;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            border: none;
            cursor: pointer;
            display: block;
            width: 100%;
            max-width: 200px;
            margin: 0 auto;
        }

        .azioni ul li a:hover {
            background-color: #3a6977;
        }

       /* .blocco-container {
                display: flex;
                justify-content: space-between;
                width: 100%;
            }*/

        .blocco {
            margin-bottom: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #f9f9f9;
            width: 30%; /* Larghezza di ciascun blocco per essere in linea */
        }

        .blocco legend {
            padding: 0 10px;
            font-size: 1.2em;
        }

        .blocco ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        .blocco ul li {
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }

        .blocco ul li:last-child {
            border-bottom: none;
        }

        .button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #2b2d30;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #3a6977;
        }

        @media (max-width: 765px) {
            .dati{
                display: flex;
                justify-content: center;
                flex-direction: column;
            }
            .content{
                width: 70%;
                margin: auto;
            }


        }
    </style>

</head>
<body>
<%@include file="headerAdmin.jsp"%>
<div class="content">
<div class="dati">
    <div class="azioni">
        <ul>
            <li>
                <a href="index.html">Torna alla homepage</a>
               <!-- <div class="back-home">
                    <form action="index.html">
                        <input type="submit" value="Torna alla homepage">
                    </form>
                </div>-->
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
</div>
<%@include file="/WEB-INF/results/footer.jsp"%>
</body>
</html>
