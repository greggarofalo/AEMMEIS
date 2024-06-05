<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 30/05/2024
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Reindirizza a "next-servlet" dopo 5 secondi -->
    <meta http-equiv="refresh" content="5; url= do-ordine">
</head>
<body>
        pagamento effettuato!
        tra 5 secondi verrai reindirizzato al tuo carrello...
      <!--  <form action="do-ordine"> -->
            <input type="hidden" value="${ordine.indirizzoSpedizione}" name="indirizzo">
            <input type="hidden" value="${ordine.citta}" name="citta">
            <input type="hidden" value="${ordine.puntiSpesi}" name="punti">
      <!--  </form>-->
</body>
</html>
