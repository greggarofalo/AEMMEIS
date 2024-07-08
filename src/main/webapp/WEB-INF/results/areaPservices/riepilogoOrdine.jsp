<%@ page import="java.util.List" %>
<%@ page import="model.ordineService.RigaOrdine" %>
<%@ page import="model.libroService.Libro" %>
<%@ page import="model.ordineService.Ordine" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Riepilogo Ordine</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/carrelloStyle.css">
    <style>

        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
        }
        .wrapper {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .content {
            flex: 1;
        }
        footer {
            margin-top: auto;
        }

        .ordine-actions .button {
            display: flex;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            background-color: #427b8a;
            color: white;
            border: none; /* Rimuove il bordo*/
        }
        .add-to-cart {
            background-color: #427b8a; /* Sfondo verde*/
            color: white; /* Testo bianco*/
            padding: 10px 20px; /* Padding interno*/
            border: none; /* Nessun bordo*/
            border-radius: 5px; /* Angoli arrotondati*/
            cursor: pointer; /* Puntatore del mouse a forma di mano*/
        }

        /* Stile per il pulsante "Aggiungi al carrello" quando è in hover */
        .add-to-cart:hover {
            background-color: #356876; /* Sfondo verde più scuro*/
        }
        @media (max-width: 300px) {
            .content {
                padding: 10px;
            }
            .book-item {
                flex-direction: column;
                align-items: center;
            }
            .book-item img {
                max-width: 100px;
                margin-right: 0;
                margin-bottom: 10px;
            }
            .book-title {
                text-align: center;
            }
            .book-actions {
                text-align: center;
            }
        }

    </style>
</head>
<body>
<div class="wrapper">
    <%@include file="/WEB-INF/results/header.jsp"%>
    <div class="content">

        <div class="book-list">
            <%
                Ordine ordine = (Ordine) session.getAttribute("ordine");
                for(RigaOrdine riga : ordine.getRigheOrdine()) {
                    Libro libro = riga.getLibro();
            %>
            <div class="book-item">
                <a href="mostra-libro?isbn=<%=libro.getIsbn()%>">
                    <img src="<%=libro.getImmagine()%>" alt="<%=libro.getTitolo()%><" class="book-image">
                </a>
                    <div class="book-details">
                        <h3 class="book-title"><%=libro.getTitolo()%></h3>
                        <div class="book-price">
                            <%if(libro.getSconto()!=0){%>
                            <span class="book-discount">-<%=libro.getSconto()%>%</span>
                            <span id="newPrice" class="book-new-price"><script>
                                function myFunction(p1, p2) {
                                    return (p1 - (p1 * p2 / 100))*<%=riga.getQuantita()%>
                                }

                                let result = myFunction(<%=libro.getPrezzo()%>, <%=libro.getSconto()%>);
                                document.getElementById("newPrice").innerHTML = result;
                            </script>€</span>
                            <span class="book-old-price"><%=libro.getPrezzo()* riga.getQuantita()%> €</span>
                            <%
                            }else {
                            %>
                            <span class="book-new-price"><%=libro.getPrezzo()* riga.getQuantita()%> €</span>
                            <%}%>
                        </div>
                        <div class="book-actions">
                            <form action="aggiungi-carrello">
                                <input type="hidden" name="isbn" value="${libro.isbn}">
                                <input type="hidden" name="idOrdine" value="${ordine.idOrdine}">
                                <input type="hidden" name="source" value="riepilogoOrdine">
                                <input class= "add-to-cart" type="submit" value="Aggiungi al carrello">
                            </form>
                        </div>
                </div>
            </div>
            <%
                }
            %>
            <div class="ordine-actions">
                <form action="visualizza-ordini">
                    <input type="submit" value="Chiudi riepilogo" class="button">
                </form>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/results/footer.jsp"%>
</div>

</body>
</html>
