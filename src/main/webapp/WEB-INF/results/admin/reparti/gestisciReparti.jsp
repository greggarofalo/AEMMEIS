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

        /* Contenitore principale dei reparti */
        .reparto-container {
            width: 80%; /* Larghezza del 90% del contenitore genitore */
            border-radius: 8px; /* Bordo arrotondato */
            margin: 50px;
        }

        /* Contenitore per ogni reparto */
        .reparto {
            display: flex;
            justify-content: space-between; /* Distribuisce lo spazio in modo uniforme */
            align-items: flex-start; /* Allinea gli elementi in alto */
            gap: 50px;/* spazio fra i contenitori sulla stessa riga */
            border-top: 1px solid #ddd;
        }

        /* Stile dei pulsanti */
        input[type="submit"] {
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none; /* Rimuove il contorno */
            padding: 6px;
            border-radius: 10px; /* Bordo arrotondato */
            margin-top: 5px;
        }

        input[type="submit"]:hover {
            background-color: #356876;
        }

        .elimina-reparto input[type="submit"]:hover {
            background-color: #ed0d0d;
        }

        .libri {
            flex: 1;
        }

        .nuovo-reparto{
            margin: 50px;
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
    </style>
</head>

<body>
<h1>Lista dei reparti</h1>

<div class="nuovo-reparto">
    <form action="nuovo-reparto">
        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
        <input type="submit" value="Nuovo Reparto">
    </form>
</div>

<c:forEach items="${reparti}" var="reparto">
    <div class="reparto-container">

        <div class="reparto">
            <div class="libri">
                <h3>${reparto.idReparto}: ${reparto.nome}</h3><br>
                <c:forEach items="${reparto.libri}" var="libro">
                    <div class="repartoItem">
                        <b>TITOLO: </b>${libro.titolo},<b>  ISBN:</b> ${libro.isbn},<b> ANNO PUBBLICAZIONE:</b> ${libro.annoPubblicazioni}
                        <div class="buttons">
                            <form action="modifica-reparto">
                                <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                                <input type="hidden" name="isbn" value="${libro.isbn}">
                                <input type="submit" value="Elimina">
                            </form>
                        </div>
                    </div>
                </c:forEach>
                <div class="aggiungi-libro">
                    <form action="aggiungi-libro">
                        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
                        <input type="submit" value="Aggiungi libro">
                    </form>
                </div>
            </div>

            <div class="modificaReparto">
                <br><br><br>
                <div class="aggiorna-reparto">
                    <form action="aggiorna-reparto">
                        <input type="hidden" name="idReparto" value="${reparto.idReparto}">
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
