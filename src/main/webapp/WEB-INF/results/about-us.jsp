<%--
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
    <title>Chi Siamo - AEMME</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/ServiceStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
</head>
<body>
<div class="wrapper">

    <%@include file="header.jsp"%>
    <div class="content">
        <section class="about-us">
            <h1>Chi Siamo</h1>
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
