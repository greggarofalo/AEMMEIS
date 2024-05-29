<%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 25/04/2024
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        /* Stile per il contenitore generale */
        .container {
            display: flex; /* Utilizza Flexbox per disporre i contenitori sulla stessa riga */
            justify-content: center; /* Centra il contenuto orizzontalmente */
            align-items: center; /* Centra il contenuto verticalmente */
            padding: 20px; /* Aggiunge spazio intorno al contenitore */
            gap: 50px
        }

        .accedi{
            width: 350px;
        }

        .registrati{
            display: flex; /* Utilizza Flexbox per centrare il contenuto */
            flex-direction: column; /* Dispone il contenuto in colonna */
            justify-content: center; /* Centra il contenuto verticalmente */
            align-items: center; /* Centra il contenuto orizzontalmente */
            text-align: center;
        }

        /* Stile per ogni blocco */
        .block {
            align-items: center;
            background-color: #f9f9f9; /* Colore di sfondo leggero */
            padding: 20px; /* Aggiunge spazio interno */
            border: 1px solid #ddd; /* Bordo sottile */
            border-radius: 10px; /* Angoli arrotondati */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Aggiunge un'ombra leggera */
        }

        /* Stile per il titolo delle sezioni */
        h3 {
            color: #3a6977; /* Colore del testo del titolo */
            font-family: 'Arial', sans-serif; /* Font del titolo */
            margin-top: 0; /* Rimuove il margine superiore */
        }

        /* Stile per le etichette dei campi */
        label {
            display: block; /* Mostra le etichette come blocchi */
            margin-bottom: 5px; /* Spazio sotto le etichette */
            color: #333; /* Colore del testo delle etichette */
            font-family: 'Arial', sans-serif; /* Font delle etichette */
        }

        /* Stile per i campi di input */
        input[type="email"],
        input[type="password"] {
            width: 85%;
            padding: 10px; /* Padding interno */
            margin-bottom: 15px; /* Spazio sotto i campi */
            border: 1px solid #ccc; /* Bordo dei campi */
            border-radius: 5px; /* Bordo arrotondato */
            font-family: 'Arial', sans-serif; /* Font dei campi */
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

        /* Stile per i paragrafi */
        p {
            color: #333; /* Colore del testo */
            font-family: 'Arial', sans-serif; /* Font del testo */
            line-height: 1.6; /* Altezza della linea */
        }

        /* Stili responsivi */
        @media (max-width: 576px) {
            .container {
                flex-direction: column; /* Disponi i blocchi in colonna */
                align-items: center; /* Allinea i blocchi al centro orizzontalmente */
            }

            .block {
                width: 80%; /* Imposta la larghezza al 100% */
                margin-bottom: 10px; /* Aggiunge spazio sotto i blocchi */
            }
        }
    </style>
</head>
<body>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<div class="container">
    <div class="block">
          <div class="accedi">
              <legend><h3>Accedi</h3></legend>
              <form action="login-servlet">
                  <label for="email">e-mail</label>
                  <input type="email" id="email" name="email">
                  <label for="pw">password</label>
                  <input type="password" id="pw" name="pw"><br>
                  <input type="submit" value="Accedi">
              </form>
          </div>
    </div>

    <div class="block">
       <div class="registrati">
           <h3>Non sei ancora registrato?</h3>
           <p>
               Registrati inserendo i tuoi dati.<br>
               La registrazione è semplice e gratuita.
           </p>
           <form action="registrazione.html">
               <input type="submit" value="Registrati">
           </form>
       </div>
    </div>
</div>
</body>
</html>
