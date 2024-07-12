<%@ page import="model.utenteService.Utente" %><%--
  Created by IntelliJ IDEA.
  User: carla
  Date: 12/07/2024
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>Servizi - AEMME</title>

    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <!-- se non è amministratore si includono cose, se lo è altre -->
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
        /* Stile per il contenuto principale */
        main {
            padding: 20px;
        }

        h1{
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

        /* Stile per la sezione dei servizi */
        .services {
            background-color: #ffffff;
            padding: 40px;
            margin: 20px auto;
            max-width:fit-content;
        }

        .services article {
            margin-bottom: 20px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }

        .services article h2 {
            color: #000000;
        }

        .services article p {
            color: #000000;
        }
    </style>

</head>
<body>
<c:choose>
    <c:when test="${utente != null && utente.tipo == 'Amministratore'}">
        <%@include file="/WEB-INF/results/admin/headerAdmin.jsp"%>
    </c:when>
    <c:otherwise>
        <%@ include file="header.jsp" %>
    </c:otherwise>
</c:choose>

    <section class="services">
        <h1>I Nostri Servizi</h1><br>
        <article>
            <h2>Ampia Selezione di Libri</h2>
            <br><p>Offriamo una vasta collezione di libri in vari generi e categorie. Che tu stia cercando gli ultimi bestseller o la letteratura classica, abbiamo qualcosa per tutti.</p><br>
        </article>
        <article>
            <h2>Vantaggi per i Membri</h2>
            <br><p>Unisciti al nostro programma di membership per sconti esclusivi, accesso anticipato alle nuove uscite e altri vantaggi speciali.</p><br>
        </article>
        <article>
            <h2>Eventi della Comunità</h2>
            <br><p>Partecipa ai nostri eventi comunitari online, inclusi incontri con autori, discussioni sui libri e molto altro.</p><br>
        </article>
    </section>

<%@ include file="footer.jsp" %>
</body>
</html>
