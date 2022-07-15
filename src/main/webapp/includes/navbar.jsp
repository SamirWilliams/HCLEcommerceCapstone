<nav style="background: #772593" class="navbar navbar-expand-lg navbar-dark fixed-top">
    <div class="container">
        <a style="font-size: 30px" class="navbar-brand fw-bold" href="index.jsp">Ecommerce Shopping <i style="font-size: 40px" class="fas fa-dolly"></i></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active" href="index.jsp"><i class="fa-solid fa-home"></i> Home</a>
                </li>
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active" href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i> Shopping Cart
                        <span style="background: #2e73b8" class="badge">${cart_list.size()}</span></a>
                </li>
                <% if (auth != null) {%>
                <!-- TODO Add isAdmin check here-->
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active" href="orders.jsp"><i class="fa-solid fa-scroll"></i> Orders</a>
                </li>
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active" href="user-logout"><i class="fa-solid fa-arrow-right-from-bracket"></i>
                        Logout</a>
                </li>
                <% } else { %>
                <li style="font-size: 20px" class="nav-item">
                    <a class="nav-link active" href="register.jsp"><i class="fa-solid fa-user-plus"></i> Register</a>
                </li>
                <li style="font-size: 20px" class="nav-item">
                    <a class="nav-link active" href="login.jsp"><i class="fa-solid fa-arrow-right-to-bracket"></i> Login</a>
                </li>

                <% } %>

            </ul>
        </div>
    </div>
</nav>
