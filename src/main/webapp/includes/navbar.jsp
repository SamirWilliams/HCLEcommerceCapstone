<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Ecommerce Shopping</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="index.jsp"><i class="fa-solid fa-home"></i> Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i> Shopping Cart <span
                            class="badge bg-danger">${cart_list.size()}</span></a>
                </li>
                <% if (auth != null) { %>
                <li class="nav-item">
                    <a class="nav-link active" href="orders.jsp"><i class="fa-solid fa-scroll"></i> Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="user-logout"><i class="fa-solid fa-arrow-right-from-bracket"></i>
                        Logout</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link active" href="login.jsp"><i class="fa-solid fa-arrow-right-to-bracket"></i> Login</a>
                </li>
                <% } %>

            </ul>
        </div>
    </div>
</nav>
