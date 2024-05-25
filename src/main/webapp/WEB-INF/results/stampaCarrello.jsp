<%@ page import="model.carrelloService.RigaCarrello" %>
<%@ page import="model.carrelloService.Carrello" %>
<%@ page import="model.libroService.Libro" %>
<%@ page import="model.wishList.WishList" %><%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 24/05/2024
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stampa Carrello</title>
    <link rel="stylesheet" type="text/css" href="./css/carrelloStyle.css">
</head>
<body>
    <div class="book-list">
        <%
            Carrello cart = (Carrello) session.getAttribute("carrello");
            if(cart.getRigheCarrello()!=null){
                for(RigaCarrello riga : cart.getRigheCarrello()){
                    Libro libro = riga.getLibro();
        %>
            <div class="book-item">
                <img src="<%=libro.getImmagine()%>" alt="<%=libro.getTitolo()%><" class="book-image">
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
                        }else
                        %>
                        <span class="book-new-price"><%=libro.getPrezzo()* riga.getQuantita()%> €</span>

                    </div>
                    <%
                        WishList wishList = (WishList) session.getAttribute("wishList");
                        String address="images/hearts-icon.png";
                        if(wishList!=null) {
                            if (wishList.getLibri().contains(libro))
                                address = "images/heartsBlack-icon.png";
                        }
                        %>

                    <div class="book-actions">
                        <button class="favorite-button" onclick="addToFavorites('<%=libro.getIsbn()%>')" data-isbn="<%=libro.getIsbn()%>">
                            <img src="<%=address%>" alt="Aggiungi ai preferiti">
                        </button>

                        <button class="remove-button" onclick="removeFromCart('<%=libro.getIsbn()%>')">
                            <img src="images/trash-icon.png" alt="Rimuovi">
                        </button>
                        <div class="book-quantity">
                            <input type="number" name="quantita" value=<%=riga.getQuantita()%> min="1">
                        </div>
                    </div>

                    <!-- Input nascosto per memorizzare i dati del libro -->
                    <input type="hidden" class="book-price-info" data-price="<%=libro.getPrezzo()%>" data-sconto="<%=libro.getSconto()%>" data-quantita="<%=riga.getQuantita()%>">

                </div>

            </div>
                <%
            }
                    }
                %>
    </div>

    <!-- Totale e pulsanti in fondo -->
    <div class="cart-summary">
        <div class="total">
            Totale: <span id="total-amount">0.00</span> €
            <br>
            Punti Aemme: <span id="aemme-points">0</span>
        </div>
        <div class="cart-actions">
            <button onclick="saveCart()">Chiudi carrello</button>
            <button onclick="window.location.href='checkout.jsp'">Acquista ora</button>
        </div>
    </div>
    <script>
        function addToFavorites(isbn) {
            // Crea una nuova istanza di XMLHttpRequest
            var xhttp = new XMLHttpRequest();

            // Definisce la funzione di gestione degli eventi per la risposta ricevuta
            xhttp.onreadystatechange = function() {
                if (this.readyState === 4 && this.status === 200) {
                    // La richiesta è stata completata e la risposta è pronta
                    // Parsa la risposta JSON
                    var data = JSON.parse(this.responseText);

                    // Aggiorna l'icona del cuoricino
                    const favoriteButton = document.querySelector(`button[class="favorite-button"][data-isbn="` + isbn + `"]`);
                    if (data.isInWishList) {
                        favoriteButton.querySelector('img').src = "images/heartsBlack-icon.png";
                    } else {
                        favoriteButton.querySelector('img').src = "images/hearts-icon.png";
                    }
                } else {
                    // Gestisci eventuali errori
                    console.error('Errore durante l\'aggiunta ai preferiti:', this.status);
                }
            };

            // Imposta il metodo e l'URL della richiesta
            xhttp.open("POST", "aggiungi-ai-preferiti?isbn=" + isbn, true);

            // Imposta l'intestazione Content-Type
            xhttp.setRequestHeader("Content-Type", "application/json");

            // Invia la richiesta
            xhttp.send();

        }
        function removeFromCart(isbn) {

            // Crea una nuova istanza di XMLHttpRequest
            var xhttp = new XMLHttpRequest();

            // Definisce la funzione di gestione degli eventi per la risposta ricevuta
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4){
                    if(this.status == 200){
                        // Se la richiesta ha avuto successo, aggiorna la pagina
                        location.reload();
                    } else {
                        // Se si è verificato un errore, mostra un messaggio di errore
                        console.error('Errore durante la rimozione dal carrello:', this.status);
                }
                }
            };

            // Imposta il metodo e l'URL della richiesta
            xhttp.open("POST", "rimuovi-dal-carrello?isbn=" + isbn, true);

            // Imposta l'intestazione Content-Type
            xhttp.setRequestHeader("Content-Type", "application/json");

            // Invia la richiesta
            xhttp.send();
        }


        function saveCart() {
            // Seleziona tutti gli elementi del carrello (assumendo che ogni libro nel carrello sia contenuto in un elemento con la classe 'book-item')
            var cartItems = document.querySelectorAll('.book-item');
            var cartData = []; // Array per memorizzare i dati del carrello da inviare al server

            // Itera su ciascun elemento del carrello per raccogliere i dati necessari
            cartItems.forEach(function(item) {
                // Ottiene l'ISBN del libro dall'attributo 'data-isbn' del pulsante di preferiti
                var isbn = item.querySelector('.favorite-button').getAttribute('data-isbn');
                // Ottiene la quantità aggiornata del libro dall'input dell'utente
                var quantity = item.querySelector('input[name="quantita"]').value;
                // Aggiunge un oggetto contenente l'ISBN e la quantità all'array cartData
                cartData.push({ isbn: isbn, quantity: quantity });
            });

            // Crea una nuova istanza di XMLHttpRequest per inviare una richiesta AJAX al server
            var xhttp = new XMLHttpRequest();

            // Definisce la funzione di gestione degli eventi per la risposta ricevuta
            xhttp.onreadystatechange = function() {
                if (this.readyState === 4) { // Verifica se la richiesta è stata completata
                    if (this.status === 200) { // Verifica se la risposta ha avuto successo
                        // Carrello salvato con successo
                        console.log('Carrello aggiornato con successo');
                    } else {
                        // Gestisce eventuali errori, se lo stato della risposta non è 200 (successo)
                        console.error('Errore durante l\'aggiornamento del carrello:', this.status);
                    }
                }
            };

            // Imposta il metodo e l'URL della richiesta
            xhttp.open("POST", "aggiorna-carrello", true);

            // Imposta l'intestazione Content-Type per indicare che i dati sono in formato JSON
            xhttp.setRequestHeader("Content-Type", "application/json");

            // Invia la richiesta al server con i dati del carrello in formato JSON
            xhttp.send(JSON.stringify(cartData));

            closeCart();
        }

        function closeCart() {
                window.location.href = "index.html";

        }

        // Funzione per aggiornare il totale del carrello e i punti Effe
        function updateCart() {
            var total = 0;
            var bookPriceInfos = document.querySelectorAll('.book-price-info');
            var puntiAemme=0;
            // Itera attraverso tutti gli elementi selezionati
            bookPriceInfos.forEach(function(info) {
                var price = parseFloat(info.getAttribute('data-price'));
                var sconto = parseFloat(info.getAttribute('data-sconto'));
                var quantita = parseInt(info.getAttribute('data-quantita'));

                // Calcola il prezzo totale tenendo conto dello sconto, se presente
                if (sconto > 0) {
                    total += (price - (price * sconto / 100)) * quantita;
                } else {
                    total += price * quantita;
                }
            });

            // Calcola i punti Effe come il numero di libri nel carrello moltiplicato per 5
            bookPriceInfos.forEach(function(info) {
                var quantita = parseInt(info.getAttribute('data-quantita')); // Quantità del libro nel carrello
                puntiAemme += quantita * 5;
            });

            //aggiornamento del DOM
            document.getElementById('total-amount').innerText = total.toFixed(2);
            document.getElementById('aemme-points').innerText = puntiAemme;
        }

        // Inizializza il carrello quando la pagina viene caricata
        document.addEventListener('DOMContentLoaded', updateCart);




    </script>


</body>
</html>
