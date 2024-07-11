<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica dati</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">

    <style>
        .email{
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 20px; /* Add some top padding */
            margin-bottom: 50px;
        }

        .email form{
            margin:10px;
        }

        .email .fieldset{
            text-align: center;
            padding: 20px;
            margin-bottom: 20px;
            margin-top: 15px;
            border-radius: 10px;
        }

        .email .fieldset label, .email .fieldset input{
            display: block;
            width: 100%;
            text-align: left;
            margin: 10px 0;
        }

        input[type="submit"] {
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none; /* Rimuove il contorno */
            padding: 6px;
            border-radius: 5px; /* Bordo arrotondato */
            margin-top: 5px;
        }

        input[type="submit"]:hover {
            background-color: #356876;
        }

        .add-phone, .remove-phone{
            background: transparent;
            border: none;
            margin-left: 80%;
            margin-bottom: 3px;

            display: block; /* Mostra come blocco */
        }

    </style>
</head>
<body>

<%@include file="/WEB-INF/results/header.jsp"%>

<div class="email">
    <form action="area-personale">
        <input type="submit" value="Torna indietro">
    </form>

    <fieldset class="fieldset">
        <legend><h3>Dati</h3></legend>
        <form action="modifica-dati">
            <label for="nomeUtente">Nome utente: </label>
            <input type="text" id="nomeUtente" name="nomeUtente" placeholder=${utente.nomeUtente}>

            <div class="phone-container">
                <c:forEach items="${utente.telefoni}" var="telefono">
                    <div class="phone-item">
                        <div id="phone">
                            <label for="telefono">Telefono</label>
                            <input type="text" id="telefono" name="telefono" pattern="[0-9]{10}" placeholder="${telefono}" readonly>
                        </div>
                        <button type="button" class="remove-phone" onclick="removePhoneField(this)" data-telefono="${telefono}">
                            <img src="images/trash-bin.png" alt="Menu" width="18" height="18"><br>
                        </button>
                    </div>
                </c:forEach>
            </div>


            <br><br>
            <button type="button" class="add-phone" onclick="addPhoneField()">
                <img src="images/add-icon3.png" alt="Menu" width="18" height="18"><br>
            </button>

            <input type="submit" value="Conferma">
        </form>
    </fieldset>
</div>

<script>
    function addPhoneField() {
        var container = document.getElementById('phone');
        var newField = document.createElement('div');
        newField.innerHTML = '<input type="text" name="telefono" pattern="[0-9]{10}" placeholder="Inserisci numero di telefono">';
        container.appendChild(newField);
    }

    function removePhoneField(button) {
        var phoneFields = document.querySelectorAll('input[name="telefono"]');
        if (phoneFields.length <= 1) {
            alert('Deve esserci almeno un numero di telefono.');
            return;
        }

        var telefonoDaRimuovere = button.getAttribute('data-telefono');
        var emailUtente = "${utente.email}";

        // Esegui una chiamata AJAX per rimuovere il telefono tramite la Servlet
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'rimuovi-telefono', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                console.log('Telefono rimosso dal database con successo.');
                // Rimuovi il campo telefono dall'interfaccia utente
                var phoneField = button.closest('.phone-container');
                phoneField.remove();
            } else {
                console.log('Errore durante la rimozione del telefono dal database.');
                // Gestisci eventuali errori o fornisci feedback all'utente
            }
        };
        var data = JSON.stringify({ email: emailUtente, telefono: telefonoDaRimuovere });

        // Invia la richiesta con i dati
        xhr.send(data);
    }
</script>

<%@include file="/WEB-INF/results/footer.jsp"%>


</body>
</html>