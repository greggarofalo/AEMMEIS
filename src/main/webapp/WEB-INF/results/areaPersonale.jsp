<%@ page import="model.utenteService.Utente" %>
<%@ page import="model.tesseraService.Tessera" %>
<%@ page import="model.tesseraService.TesseraDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Personale</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/areaPersonale.css">
</head>

<body>
    <%@include file="header.jsp"%>
<div class="contenuto">
    <div class="elementi">

            <div class="azioni">
                <ul>
                    <li><a href="visualizza-ordini">Visualizza Ordini</a></li>
                    <li><a href="elimina-account" onclick="return confirm('Sei sicuro di voler eliminare il tuo account?')">Elimina Account</a></li>
                    <li><a href="log-out" onclick="return confirm('Sei sicuro di voler fare logout?')">Logout</a></li>
                </ul>
            </div>
            <div class="non-azioni">
                <fieldset class="blocco dati">
                    <legend><h3>I miei dati</h3></legend>
                    <ul>
                        <li>${utente.nomeUtente}</li>
                        <li>${utente.tipo}</li>
                        <c:forEach items="${utente.telefoni}" var="telefono">
                            <li>${telefono}</li>
                        </c:forEach>
                    </ul>
                    <form action="modifica-dati-supporto" method="post">
                        <input type="submit" class="button" value="Modifica dati">
                    </form>
                </fieldset>

                <fieldset class="blocco email">
                    <legend><h3>Email</h3></legend>
                    <ul>
                        <li>${utente.email}</li>

                    </ul>
                </fieldset>


                <fieldset class="blocco password">
                    <legend><h3>Password</h3></legend>
                    <form action="modifica-password-supporto" method="post">
                        <input type="submit" value="Modifica password" class="button">
                    </form>
                   <!-- <a href="modifica-password-supporto" class="button">Modifica password</a>-->
                </fieldset>



                <fieldset class="blocco tessera">

                    <legend><h3>Tessera</h3></legend>
                    <% Utente utente = (Utente) session.getAttribute("utente");
                        if(utente.getTipo().equalsIgnoreCase("premium")){
                            TesseraDAO serviceTessera = new TesseraDAO();
                            Tessera tessera = serviceTessera.doRetrieveByEmail(utente.getEmail());
                    %>
                    <li>
                        Numero tessera: <%= tessera.getNumero()%>
                    </li>
                    <li>
                        Punti tessera: <%= tessera.getPunti() %>
                    </li>
                    <form id="standardForm" action="cambia-tipo" method="post">
                        <input type="submit" value="Diventa utente Standard" class="button">
                    </form>
                    <% } else if (utente.getTipo().equalsIgnoreCase("standard")) { %>
                    <form id="premiumForm" action="cambia-tipo" method="post">
                        <input type="submit" value="Diventa utente Premium" class="button">
                    </form>
                    <% } %>

                </fieldset>
            </div>
    </div>
</div>
        <%@include file="footer.jsp"%>

        <script>
            document.getElementById('standardForm')?.addEventListener('submit', function(event) {
                event.preventDefault();
                var confirmed = confirm('Sei sicuro di voler diventare un utente Standard? Perderai i punti accumulati');
                if (confirmed) {
                    this.submit();
                }
            });

            document.getElementById('premiumForm')?.addEventListener('submit', function(event) {
                event.preventDefault();
                var confirmed = confirm('Sei sicuro di voler diventare un utente Premium?');
                if (confirmed) {
                    this.submit();
                }
            });
        </script>

</body>
</html>
