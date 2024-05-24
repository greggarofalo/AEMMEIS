<div class="header">


    <div class="container">

        <div class="search-bar">
            <input type="search" placeholder="Search">
        </div>

        <div class="icons">
            <a href="show-wishlist">
                <img src="./images/hearts-icon.png" width="25" height="25" class="image">
            </a>

            <a href="cart-servlet">
                <img src="./images/icon-cart.png" width="25" height="25" class="spaced-image image " >
            </a>

            <a href="area-personale">
                <img src="./images/icon-user.png" width="24" height="25" class="image">
            </a>
        </div>
    </div><br><br>

    <ul>
        <li><a class="active" href="index.html">Home</a></li>
        <c:forEach items="${reparti}" var="reparto">
            <li><a href="mostra-reparto">${reparto.descrizione}</a></li>
        </c:forEach>

    </ul><br><br>
</div>



