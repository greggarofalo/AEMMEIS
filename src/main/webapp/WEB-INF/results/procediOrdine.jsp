<%@ page import="model.libroService.Sede" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 28/05/2024
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Procedi-ordine</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/formStyle2.css">

    <style>
        .containerIndirizzo {
            background-color: #555555;
            margin-left: 100px;
            display: block;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .label-ind{
            display: block;
            align-items: center;
        }
        .button-row {
            display: flex;
            gap: 20px;
            justify-content: space-around;
            align-items: center;
            width: 10px;
        }

        .choice-button {
            background-color: #356876;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .choice-button:hover {
            background-color: #041e30;
        }

        #content {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
    </style>
</head>
<body>
<%@include file="header.jsp"%>

    <div class="containerIndirizzo">
        <div class="button-row">
            <button class="choice-button" onclick="showSection('indirizzo')">Indirizzo</button>
            <button class="choice-button" onclick="showSection('scegli-sede')">Scegli una Sede</button>
        </div>

        <div id="content"></div>
    </div>

<%@include file="footer.jsp"%>
<script>
    function showSection(section) {
        let content = document.getElementById('content');
        content.innerHTML = ''; // Clear the content

        if (section === 'indirizzo') {
        content.innerHTML = `
        <form action="do-pagamento" method="post">
            <input type = "hidden" value = "indirizzo" name = "typeForm">
            <div>
                <label for="indirizzo" class="label-ind">Indirizzo:</label>
                <input type="text" id="indirizzo" name="indirizzo" required>
            </div>
            <div>
                <label for="citta" class="label-ind">Città:</label>
                <input type="text" id="citta" name="citta" required>
            </div>
            <div>
                <label for="cap" class="label-ind">CAP:</label>
                <input type="text" id="cap" name="cap" required pattern="[0-9]{5}">
            </div>
            <div>
                <input type="submit" value="Procedi con il pagamento">
            </div>
        </form>
        `;
        }
        else if (section === 'scegli-sede') {
            content.innerHTML = `
                <h3>Scegli una Sede</h3>
                <form action="do-pagamento" >
                    <input type="hidden" name="typeForm" value="scegli-sede">
                    <ul>
                        <% List<Sede> sedi = (List<Sede>) request.getAttribute("sedi");
                            if(!sedi.isEmpty()){
                        %>
                            <c:forEach items="${sedi}" var="sede">
                                <li>
                                    <input type="radio" id="radio-${sede.idSede}" name="sede" value="${sede.idSede}" required>
                                    <label for="radio-${sede.idSede}">Sede in via: ${sede.via} (${sede.civico}), città: ${sede.citta}, (${sede.cap})</label>
                                </li>
                            </c:forEach>

                            </ul>
                            <input type="submit" value="Procedi con il pagamento">
                        <%}else{%>
                            <p>Non ci sono sedi disponibili </p>
                        <%}%>
                </form>
            `;
        }
    }

    function setAddress(address) {
        // Set the address and go back to the main form
        showSection('indirizzo');
        document.getElementById('indirizzo').value = address;

    }

    </script>
</body>
</html>
