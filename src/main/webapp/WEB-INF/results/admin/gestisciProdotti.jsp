<%@ page import="model.libroService.LibroDAO" %>
<%@ page import="model.libroService.Libro" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: M.DELUCIA18
  Date: 14/05/2024
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Management</title>
</head>
<body>
<h1>Product List</h1>
<a href="newProduct.jsp">Add New Product</a>
<table border="1" width="100%">
    <tr>
        <th>Titolo</th>
        <th>ISBN</th>
        <th>Sconto</th>
        <th>Prezzo</th>
        <th>Azione</th>
    </tr>
    <%
        LibroDAO productDAO = new LibroDAO();
        List<Libro> libri = productDAO.doRetriveAll();
        for (Libro libro : libri) {
    %>
    <tr>
        <td><%= libro.getTitolo() %></td>
        <td><%= libro.getIsbn() %></td>
        <td><%= libro.getSconto() %></td>
        <td><%= libro.getPrezzo() %></td>
        <td>
            <form action="modifica-libro">
                <input type="hidden" name="isbn" value="${libro.isbn}">
                <input type="submit" value="Modifica">
            </form>
            <form action="elimina-libro">
                <input type="hidden" name="isbn" value="${libro.isbn}">
            </form>
            <%-- <a href="editProduct.jsp?id=<%= product.getId() %>">Edit</a>
            <a href="deleteProduct.jsp?id=<%= product.getId() %>">Delete</a>--%>
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
