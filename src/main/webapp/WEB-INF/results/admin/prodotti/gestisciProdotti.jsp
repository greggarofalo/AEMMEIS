<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 14/05/2024
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione Prodotti</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h1 {
            padding: 20px;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            border-radius: 8px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .nuovo-prodotto {
            margin-bottom: 30px;
            margin-top: 25px;
        }
        input[type="submit"] {
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none;
            padding: 6px;
            border-radius: 10px;
            margin-top: 5px;
            margin-right: 30px;
        }
        input[type="submit"]:hover {
            background-color: #356876;
        }
        form {
            display: inline-block;
            margin: 0;
        }
        .actions {
            display: flex;
            align-items: center;
        }

        @media (max-width: 768px) {
            .container {
                width: 90%;
                padding: 15px;
            }
            th, td {
                padding: 8px;
            }
            input[type="submit"] {
                font-size: 14px;
                padding: 5px;
                margin-right: 5px;
            }
        }

        @media (max-width: 590px) {
            .container {
                width: 100%; /* La larghezza del contenitore diventa il 100% della larghezza dello schermo */
                padding: 10px; /* Riduzione del padding per adattarsi meglio agli schermi più piccoli */
            }
            h1 {
                font-size: 24px; /* Riduzione della dimensione del font del titolo per adattarsi meglio agli schermi più piccoli */
                padding: 10px; /* Riduzione del padding intorno al titolo */
            }
            table, thead, tbody, th, td, tr {
                display: block; /* Cambia la visualizzazione degli elementi della tabella a blocco per poter gestire meglio il layout in verticale */
            }
            th {
                display: none; /* Nascondi le intestazioni della tabella per risparmiare spazio */
            }
            thead {
                display: none; /* Nascondi l'elemento thead per risparmiare spazio */
            }
            tr {
                margin-bottom: 10px; /* Aggiungi uno spazio tra le righe della tabella */
                border-bottom: 1px solid #ddd; /* Aggiungi un bordo inferiore alle righe della tabella per una separazione visiva */
                padding-bottom: 10px; /* Aggiungi padding nella parte inferiore delle righe della tabella */
            }
            td {
                display: flex; /* Usa flexbox per le celle della tabella per disporre i contenuti in modo flessibile */
                justify-content: space-between; /* Distribuisci gli elementi delle celle della tabella con spazio tra di loro */
                align-items: center; /* Allinea verticalmente al centro gli elementi delle celle della tabella */
                padding: 10px; /* Aggiungi padding alle celle della tabella per migliorare l'usabilità */
                border-bottom: none; /* Rimuovi i bordi inferiori delle celle della tabella */
            }
            td[data-label]:before {
                content: attr(data-label); /* Usa l'attributo data-label per creare una pseudo-etichetta per i dati */
                font-weight: bold; /* Rendi il testo delle pseudo-etichetta in grassetto per differenziare dai valori */
            }
            .actions {
                display: flex; /* Usa flexbox per disporre i pulsanti di azione */
                justify-content: space-between; /* Distribuisci i pulsanti con spazio tra di loro */
                width: 100%; /* I pulsanti di azione occupano l'intera larghezza disponibile */
            }
            .actions form {
                margin-right: 10px; /* Aggiungi uno spazio a destra dei form di azione per separare i pulsanti */
            }
            .actions input[type="submit"] {
                width: 100%; /* I pulsanti di azione occupano l'intera larghezza del loro contenitore */
                margin-top: 5px; /* Aggiungi uno spazio in cima ai pulsanti di azione */
            }
        }



    </style>
</head>
<body>
<h1>Lista di prodotti</h1>
<div class="container">
    <div class="nuovo-prodotto">
        <form action="nuovo-prodotto">
            <input type="hidden" name="isbn" value="${libro.isbn}">
            <input type="submit" value="Nuovo Prodotto">
        </form>
    </div>
    <table>
        <tr>
            <th>Titolo</th>
            <th>ISBN</th>
            <th>Sconto</th>
            <th>Prezzo</th>
            <th>Disponibilita'</th>
            <th>Azione</th>
        </tr>
        <c:forEach items="${libri}" var="libro">
            <tr>
                <td>${libro.titolo}</td>
                <td>${libro.isbn}</td>
                <td>${libro.sconto}</td>
                <td>${libro.prezzo}</td>
                <td>${libro.disponibile ? 'Disponibile' : 'Non dispoibile'}</td>
                <td>
                    <div class="actions">
                        <form action="modifica-libro">
                            <input type="hidden" name="isbn" value="${libro.isbn}">
                            <input type="submit" value="Modifica">
                        </form>
                        <c:choose>
                            <c:when test="${!libro.disponibile}">
                                <form action="disponibile">
                                    <input type="hidden" name="isbn" value="${libro.isbn}">
                                    <input type="submit" value="Disponibile">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="non-disponibile">
                                    <input type="hidden" name="isbn" value="${libro.isbn}">
                                    <input type="submit" value="Non disponibile">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>