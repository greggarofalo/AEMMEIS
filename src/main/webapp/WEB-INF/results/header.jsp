<meta name="viewport" content="width=device-width, initial-scale=1.0">

<header>
    <div class="container">
        <div class="logo-searchBar">
            <div class="logoHeader">
                <img src="./images/logo.png" alt="AEMME" width="88" class="image">
            </div>
            <div class="search-bar">
                <input type="search" placeholder="Search">
            </div>
        </div>

        <div class="icons-and-menu">
            <button class="menu-toggle" onclick="toggleMenu()">
                <img src="images/menu-icon.png" alt="Menu" width="25" height="25">
            </button> <!-- Pulsante per il menu a discesa -->
            <div class="icons">
                <a href="show-wishlist">
                    <img src="./images/hearts-icon.png" width="25" height="25" class="image">
                </a>
                <a href="cart-servlet">
                    <img src="./images/icon-cart.png" width="25" height="25" class="spaced-image image">
                </a>
                <a href="area-personale">
                    <img src="./images/icon-user.png" width="24" height="25" class="image">
                </a>
            </div>
        </div>
    </div>


    <ul id="menu">
        <li><a class="active" href="index.html">Home</a></li>
        <c:forEach items="${reparti}" var="reparto">
            <c:if test="${reparto.nome ne 'Libri di Tendenza'}">
                <li><a href="mostra-reparto?id=${reparto.idReparto}">${reparto.nome}</a></li>
            </c:if>
        </c:forEach>
    </ul>
</header>


<script>
    function toggleMenu() {
        var menu = document.getElementById('menu');
        menu.classList.toggle('show');
    }
</script>
