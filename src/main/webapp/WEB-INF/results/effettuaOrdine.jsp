<%@ page import="model.ordineService.Ordine" %>
<%@ page import="model.ordineService.RigaOrdine" %>
<%@ page import="model.libroService.Libro" %><%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 27/05/2024
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Completa l'ordine</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
</head>
<body>
<%@include file="header.jsp"%>
    <div class="riepilogo-ordine">
        <div class="book-list"><%
            Ordine ordine = (Ordine) request.getAttribute("ordine");
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
                                <button class="remove-button" onclick="removeFromOrder('<%=libro.getIsbn()%>', '<%=ordine.getIdOrdine()%>')">
                                    <img src="images/trash-icon.png" alt="Rimuovi">
                                </button>
                                <div class="book-quantity">
                                    <input type="number" name="quantita" value=<%=riga.getQuantita()%> min="1">
                                </div>
                            </div>
                            <input type="hidden" class="book-price-info" data-price="<%=libro.getPrezzo()%>" data-sconto="<%=libro.getSconto()%>" data-quantita="<%=riga.getQuantita()%>">
                        </div>
            </div><%
            }%>
        </div>
        <div class="effettua-ordine">
            <form action="completa-ordine">

            </form>
        </div>
    </div>



<%@include file="footer.jsp"%>

<script>
    // Funzione per rimuovere un libro dall'ordine
    function removeFromOrder(isbn, idOrdine) {
        var xhr = new XMLHttpRequest();  // Crea una nuova richiesta XMLHttpRequest

        xhr.open("POST", "rimuovi-ordine", true);  // Configura la richiesta per il metodo POST verso l'URL "rimuoviLibro"
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");  // Imposta l'intestazione della richiesta
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                location.reload();  // Ricarica la pagina quando la richiesta è completata
            }
        };
        xhr.send("isbn=" + isbn + "idOrdine=" + idOrdine);  // Invia i dati della richiesta
    }

    // Funzione per aggiornare la quantità di un libro nell'ordine
    function updateQuantity(isbn, idOrdine, quantita) {
        var xhr = new XMLHttpRequest();  // Crea una nuova richiesta XMLHttpRequest
        xhr.open("POST", "aggiorna-ordine", true);  // Configura la richiesta per il metodo POST verso l'URL "aggiornaQuantita"
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");  // Imposta l'intestazione della richiesta
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                location.reload();  // Ricarica la pagina quando la richiesta è completata
            }
        };
        xhr.send("isbn=" + isbn + "idOrdine=" + idOrdine + "&quantita=" + quantita);  // Invia i dati della richiesta
    }
</script>
</body>
</html>
