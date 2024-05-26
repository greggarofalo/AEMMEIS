<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 25/05/2024
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica password</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">

    <style>
        .pass{
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 20px; /* Add some top padding */
            margin-bottom: 50px;
        }

        .pass form{
            width: 100%;
        }

        .pass .pm{
            text-align: center;
        }

        .pass .pm .bottone{
            text-align: right;
        }
    </style>
</head>
<body>
    <%@include file="/WEB-INF/results/header.jsp"%>
    <div class="pass">

        <form action="area-personale">
            <input type="submit" value="Torna indietro">
        </form>

        <form action="modifica-password" class="pm">
            <label for="pass">Password</label>
            <input type="password" id="pass" name="password" placeholder="*****"><br><br>
            <input type="submit" value="Conferma" class="bottone">
        </form>
    </div>


    <%@include file="/WEB-INF/results/footer.jsp"%>

</body>
</html>
