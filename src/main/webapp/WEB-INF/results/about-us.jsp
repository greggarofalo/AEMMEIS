<%@ page import="model.utenteService.Utente" %><%--
  Created by IntelliJ IDEA.
  User: carla
  Date: 12/07/2024
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>Chi Siamo - AEMME</title>

    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <%  Utente utente = (Utente) session.getAttribute("utente");
        if((utente==null) || !(utente.getTipo().equalsIgnoreCase("amministratore"))){ %>
        <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
        <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">

    <%}else{%>
        <link rel="stylesheet" type="text/css" href="./css/footerAdmin.css">
        <style>
            header{
                text-align: center;
                border-bottom-style: groove;
                padding: 10px;
            }
        </style>
    <%}%>

    <style>
        /* Stile generale per il corpo */

        /* Stile per il contenuto principale */
        main {
            padding: 20px;
        }

        h1 {
            color: #000000;
            font-family: 'Merriweather', serif;
            text-align: center;
        }

        /* Stile per i titoli */
         h2 {
            color: #222;
            font-family: 'Merriweather', serif;
        }

        /* Stile per i paragrafi */
        p {
            line-height: 1.6;
            color: #000000;
        }

        /* Stile per la sezione Chi Siamo */
        .about-us {
            background-color: #ffffff;
            padding: 40px;
            margin: 20px 50px;
            max-width:fit-content;
        }

        .about-us .founders {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin-top: 20px;
        }

        .about-us .founder {
            flex-basis: 30%;
            text-align: center;
            margin-bottom: 20px;
        }

        .about-us .founder h2 {
            color: #000000;
        }

        .about-us .founder p {
            color: #000000;
        }
    </style>

</head>
<body>
<div class="wrapper">
    <c:choose>
    <c:when test="${utente != null && utente.tipo == 'Amministratore'}">
        <%@include file="/WEB-INF/results/admin/headerAdmin.jsp"%>
    </c:when>
    <c:otherwise>
        <%@ include file="header.jsp" %>
    </c:otherwise>
    </c:choose>
    <div class="content">
        <section class="about-us">
            <h1>Chi Siamo</h1><br>
            <p>AEMME è stata fondata da tre appassionate studentesse dell'Università degli Studi di Salerno, che frequentano la facoltà di informatica. La nostra missione è rendere accessibile una vasta gamma di libri a tutti, fornendo un'esperienza arricchente per tutti i lettori.</p>
            <div class="founders">
                <div class="founder">
                    <h2>Alessanda Raia</h2>
                    <p>Alessandra ha una passione per la lettura e la tecnologia. La sua visione è quella di integrare le nuove tecnologie per migliorare l'esperienza di lettura e rendere i libri più accessibili a tutti.</p>
                </div>
                <div class="founder">
                    <h2>Martina De Lucia</h2>
                    <p>Martina ama esplorare nuovi generi letterari. Con la sua esperienza in sviluppo software, ha contribuito a creare un'interfaccia utente intuitiva e funzionale per la nostra libreria online.</p>
                </div>
                <div class="founder">
                    <h2>Carla Stefanile</h2>
                    <p>Carla è appassionata di libri e innovazione. Grazie alla sua competenza in intelligenza artificiale, ha sviluppato il nostro sistema di raccomandazioni personalizzate.</p>
                </div>
            </div>
            <p>Il nostro team è dedicato a creare una comunità dove i lettori possono scoprire, condividere e godere della letteratura. Crediamo nel potere dei libri di ispirare ed educare, e siamo impegnati a fornire il miglior servizio possibile ai nostri clienti.</p>
        </section>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
