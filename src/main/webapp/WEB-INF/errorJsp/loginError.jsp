
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Errore Login</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/error.css">
</head>
<body>
<div class="error-class">
    <img src="./images/warning.png" width="50%" alt="warning-image" class="image">
    <div class="message">
        <p>
            Email e password errati
        </p>
        <form action="area-personale">
            <input type="submit" value="Torna al login">
        </form>
    </div>
</div>
<%@include file="/WEB-INF/results/footer.jsp"%>
</body>
</html>