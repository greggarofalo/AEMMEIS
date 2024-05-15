<%@ page import="model.libroService.LibroDAO" %>
<%@ page import="model.libroService.Libro" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 14/05/2024
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione Prodotti</title>
</head>
<body>
<h1>Lista di prodotti</h1>
<a href="nuovo-prodotto">Add New Product</a>
<table border="1" width="100%">
    <tr>
        <th>Titolo</th>
        <th>ISBN</th>
        <th>Sconto</th>
        <th>Prezzo</th>
        <th>Azione</th>
    </tr>
    <c:forEach items="${libri}" var="libro">
        <tr>
            <td>${libro.titolo}</td>
            <td>${libro.isbn}</td>
            <td>${libro.sconto}</td>
            <td>${libro.prezzo}</td>
        <td>
            <form action="modifica-libro">
                <input type="hidden" name="isbn" value="${libro.isbn}">
                <input type="submit" value="Modifica">
            </form>
            <form action="elimina-libro">
                <input type="hidden" name="isbn" value="${libro.isbn}">
                <input type="submit" value="Elimina">
            </form>
            <%-- <a href="editProduct.jsp?id=<%= product.getId() %>">Edit</a>
            <a href="deleteProduct.jsp?id=<%= product.getId() %>">Delete</a>--%>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
