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
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h1 {
            padding: 20px;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            border-radius: 8px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .nuovo-prodotto {
            margin-bottom: 30px;
            margin-top: 25px;
        }
        input[type="submit"] {
            background-color: #427b8a;
            color: #fff;
            font-size: 16px;
            border: none;
            padding: 6px;
            border-radius: 10px;
            margin-top: 5px;
            margin-right: 30px;
        }
        input[type="submit"]:hover {
            background-color: #356876;
        }
        form {
            display: inline-block;
            margin: 0;
        }
        .actions {
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>
<h1>Lista di prodotti</h1>
<div class="container">
    <div class="nuovo-prodotto">
        <form action="nuovo-prodotto">
            <input type="hidden" name="isbn" value="${libro.isbn}">
            <input type="submit" value="Nuovo Prodotto">
        </form>
    </div>
    <table>
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
                    <div class="actions">
                        <form action="modifica-libro">
                            <input type="hidden" name="isbn" value="${libro.isbn}">
                            <input type="submit" value="Modifica">
                        </form>
                        <form action="elimina-libro">
                            <input type="hidden" name="isbn" value="${libro.isbn}">
                            <input type="submit" value="Elimina">
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
