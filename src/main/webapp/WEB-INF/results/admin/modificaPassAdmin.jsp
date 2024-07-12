<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 08/07/2024
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica password</title>
    <link rel="stylesheet" type="text/css" href="./css/footerAdmin.css">

    <style>
        header{
            text-align: center;
            border-bottom-style: groove;
            padding: 10px;
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

        .pass{
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 20px; /* Add some top padding */
            margin-bottom: 50px;
        }

        .pass form{
            width: 100%;
            margin:15px;
        }

        .pass .pm{
            text-align: center;
        }

        .pass .pm .bottone{
            text-align: right;
        }

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

    </style>
</head>
<body>
<div class="wrapper">
    <%@include file="headerAdmin.jsp"%>
    <div class="content">

        <div class="pass">
            <form action="area-personaleAdmin">
                <input type="submit" value="Torna indietro">
            </form>
            <form action="modifica-password" class="pm" method="post">
                <label for="pass">Password</label>
                <input type="password" id="pass" name="password" placeholder="*****"  maxlength="16" required><br><br>
                <input type="submit" value="Conferma" class="bottone">
            </form>
        </div>
    </div>
    <%@include file="/WEB-INF/results/footer.jsp"%>
</div>

</body>
</html>
