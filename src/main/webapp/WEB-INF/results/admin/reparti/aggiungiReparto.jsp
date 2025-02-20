<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 15/05/2024
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aggiungi il nuovo reparto</title>
    <link rel="stylesheet" type="text/css" href="css/formStyle2.css">
</head>
<body>
<div class="form-conteiner">
    <div class="form">
        <h3>Nuovo Reparto</h3>
        <div class="torna-indietro">
            <form action="gestisci-reparti">
                <input type="submit" value="Torna indietro">
            </form>
        </div>
        <form action="aggiungi-reparto">
            <%String esito= (String) request.getAttribute("esito");
                if(esito!=null && esito.equals("non riuscito")){
            %>
            <p style="color: red">Reparto già esistente</p>
            <%}%>
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" required>
            <label for="descrizione">Descrizione</label>
            <textarea id="descrizione" name="descrizione" required></textarea>
            <label for="immagine">Immagine</label>
            <input type="text" id="immagine" name="immagine" required>
            <input type="submit" value="Aggiungi">
        </form>
    </div>
</div>
</body>
</html>

