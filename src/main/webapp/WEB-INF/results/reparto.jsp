<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.libroService.Reparto" %>
<%@ page import="model.wishList.WishList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: carla
  Date: 25/05/2024
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>AEMME-Reparto</title>
    <link rel="stylesheet" type="text/css" href="./css/generale.css">
    <link rel="stylesheet" type="text/css" href="./css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/homePageStyle.css">
    <link rel="stylesheet" type="text/css" href="./css/repartiStyle.css">
  </head>
  <body>
     <%@include file="header.jsp"%>
     <% WishList wishList = (WishList) session.getAttribute("wishList");
       Reparto reparto = (Reparto) session.getAttribute("reparto");
       List<Reparto> reparti = (List<Reparto>) session.getAttribute("reparti");
     int i = 0;%>
     <div class="reparto">
       <img class="imgReparto" src="${reparto.immagine}">
       <div class="dropdown-container">
         <form id="repartoForm" action="mostra-reparto" method="GET">
           <select id="repartoSelect" class="dropdown-menu" name="id" onchange="document.getElementById('repartoForm').submit()">
             <c:forEach items="${reparti}" var="rep">
               <option value="${rep.idReparto}" <c:if test="${repartoAttuale == rep.idReparto}">selected</c:if>>${rep.nome}</option>
             </c:forEach>
           </select>
           <noscript><input type="submit" value="Submit"></noscript>
         </form>
       </div>
     </div>

     <p class="descrizione">${reparto.descrizione}</p>

    <div class="griglia">
      <c:forEach items="${reparto.libri}" var="libro">
        <div class="item">
          <img class="img" src="${libro.immagine}">

          <% String path="./images/hearts-icon.png";
            if(wishList!=null && wishList.getLibri()!=null) {
              if(wishList.getLibri().contains(reparto.getLibri().get(i))){
                path="./images/heartsBlack-icon.png";
              }
            }
            i++;%>

          <div class="azioni">
            <form action="modifica-preferiti">
              <input type="hidden" name="isbn" value="${libro.isbn}">
              <input type="image" src="<%=path%>" name="aggPreferBut" alt="Preferiti" width="20" height="20">
            </form>
            <form action="aggiungi-carrello">
              <input type="hidden" name="isbn" value="${libro.isbn}">
              <input type="image" src="./images/icon-cart.png" name="aggCarBut" alt="Carrello" width="20" height="20">
            </form>
          </div>


          <p class="titolo">${libro.titolo}</p>
          <p class=autori>
            <c:forEach var="autore" items="${libro.autori}" varStatus="status">
              ${autore.nome}${autore.cognome}
              <c:if test="${!status.last}">
                ,
              </c:if>
            </c:forEach>
          </p>
        </div>

      </c:forEach>
    </div>


       <script>
         // Imposta l'opzione predefinita nel menu a tendina
         window.onload = function () {
           var repartoSelect = document.getElementById('repartoSelect');
           if (repartoSelect) {
             repartoSelect.value = repartoAttuale;
           }
         };
       </script>

       <%@include file="footer.jsp" %>
  </body>
</html>
