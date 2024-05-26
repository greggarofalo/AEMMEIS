<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione Reparti</title>
    <style>
        /* Impostazioni globali */
        body {
            width: 100%;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif; /* Utilizza Arial come font predefinito */
        }

        /* Stile del titolo della lista dei reparti */
        h1 {
            text-align: center; /* Allinea il titolo al centro */
            margin-top: 30px; /* Aggiungi spazio sopra il titolo */
        }

        /* Stile del link per aggiungere un nuovo reparto */
        a {
            margin: 20px;
            padding: 20px;
            color: #000000; /* Colore del testo blu */
            text-decoration: none; /* Rimuovi la sottolineatura */
        }

        a:hover{
            color: #3a6977;
        }

        /* Contenitore principale dei reparti */
        .reparto-container {
            width: 80%; /* Larghezza del 90% del contenitore genitore */
            border-radius: 8px; /* Bordo arrotondato */
            margin: 50px;
        }

        .reparto {
            display: flex;
            justify-content: space-between; /* Distribuisce lo spazio in modo uniforme */
            align-items: flex-start; /* Allinea gli elementi in alto */
            gap: 50px;/* spazio fra i contenitori sulla stessa riga */
        }

        /* Stile dei pulsanti */
        input[type="submit"] {
            background-color: #3a6977;
            color: #fff;
            font-size: 16px;
            border: none; /* Rimuove il contorno */
            padding: 6px;
            border-radius: 10px; /* Bordo arrotondato */
            margin-top: 5px;
        }

        input[type="submit"]:hover {
            background-color: #466871;
        }

        .libri {
            flex: 1;
        }

        .modificaReparto {
            flex-grow: 1; /* Permette al div di occupare tutto lo spazio rimanente */
            max-width: 300px; /* Limita la larghezza massima del form */
        }

        /* Stile degli input del form */
        .reparto-form input[type="text"],
        .reparto-form textarea {
            width: 100%; /* Larghezza completa */
            padding: 10px; /* Spazio interno */
            border: 1px solid #ccc; /* Bordo grigio chiaro */
            border-radius: 4px; /* Bordo arrotondato */
            box-sizing: border-box; /* Include padding e bordo nella larghezza */
            margin-bottom: 10px; /* Margine inferiore */
        }

        /* Stile del pulsante di invio del form */
        .reparto-form input[type="submit"] {
            background-color: #3a6977; /* Colore di sfondo blu */
            color: #fff; /* Colore del testo bianco */
            border: none; /* Nessun bordo */
            cursor: pointer; /* Cambia il cursore al passaggio */
            font-size: 16px; /* Dimensione del testo */
            padding: 12px 20px; /* Spazio interno */
            border-radius: 4px; /* Bordo arrotondato */
            text-transform: uppercase; /* Trasforma il testo in maiuscolo */
        }

        .reparto-form input[type="submit"]:hover {
            background-color: #a1aeb4; /* Colore di sfondo blu scuro al passaggio */
        }
    </style>
</head>

<body>
<h1>Lista dei reparti</h1>

<a href="nuovo-reparto">Aggiungi un nuovo reparto</a><br><br>

<c:forEach items="${reparti}" var="reparto">
    <div class="reparto-container">
        <h3>${reparto.idReparto}: ${reparto.nome}</h3>
        <div class="reparto">
            <div class="libri">
                <c:forEach items="${reparto.libri}" var="libro">
                    <ul>
                        <li class="repartoItem">
                            <span><b>${libro.titolo}</b>,  ISBN: ${libro.isbn},  ANNO PUBBLICAZIONE: ${libro.annoPubblicazioni}</span>
                            <div class="buttons">
                                <form action="modifica-reparto">
                                    <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                                    <input type="hidden" name="isbn" value="${libro.isbn}">
                                    <input type="submit" value="Elimina">
                                </form>
                            </div>
                        </li>
                    </ul>
                </c:forEach>
                <div class="aggiungi-libro">
                    <form action="aggiungi-libro">
                        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                        <input type="submit" value="Aggiungi libro">
                    </form>
                </div>
            </div>

            <div class="modificaReparto">
                <div class="aggiorna-reparto">
                    <form action="aggiorna-reparto">
                        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                        <label for="nome">Nome</label>
                        <input type="text" id="nome" name="nome" value="${reparto.nome}"><br><br>
                        <label for="descrizione">Descrizione</label>
                        <textarea id="descrizione" name="descrizione" rows="4" cols="50">${reparto.descrizione}</textarea><br><br>
                        <label for="immagine">Immagine</label>
                        <input type="text" id="immagine" name="immagine" value="${reparto.immagine}"><br><br>
                        <input type="submit" value="Modifica">
                    </form>
                </div>

                <div class="elimina-reparto">
                    <form action="elimina-reparto">
                        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                        <input type="submit" value="Elimina Reparto">
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

</body>
</html>
