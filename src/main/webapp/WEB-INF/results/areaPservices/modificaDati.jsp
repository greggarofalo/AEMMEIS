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
            width: 100%;
        }

        .email .fieldset{
            text-align: center;
            padding: 20px;
            margin: 0 auto; /* Center the fieldset */
            margin-bottom: 20px;

        }

        .email .fieldset label, .email .fieldset input{
            display: block;
            width: 100%;
            text-align: left;
            margin: 10px 0;

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
                    <input type="email" id="nomeUtente" name="nomeUtente" placeholder=${utente.nomeUtente}>

                    <c:forEach items="${utente.telefoni}" var="telefono">
                       <br><br><label for="telefono">Telefono</label>
                        <input type="text" id="telefono" name="telefono" pattern="[0-9]{10}" placeholder="${telefono}"><br><br>
                    </c:forEach>
                    <input type="submit" value="Conferma">
                </form>
        </fieldset>
    </div>


    <%@include file="/WEB-INF/results/footer.jsp"%>


</body>
</html>
