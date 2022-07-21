<style type = "text/css">
.dropdown-menu> a:hover {
  background-color: #b82e95 !important;
}
</style>
<nav style="background: #772593" class="navbar navbar-expand-lg navbar-dark">
    <div class="d-flex align-items-center justify-content-between" style="width: 100%">
        <a style="font-size: 25px" class="navbar-brand fw-bold ms-5 my-1" href="index.jsp">Ecommerce Shopping <i style="font-size: 30px" class="fas fa-dolly"></i></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse me-3" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active btn" href="index.jsp"><i class="fa-solid fa-home"></i> Home</a>
                </li>
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active btn" href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i> Shopping Cart
                        <span style="background: #2e73b8" class="badge">${cart_list.size()}</span></a>
                </li>
                <% if (auth != null) {%>
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active btn" href="orders.jsp"><i class="fa-solid fa-scroll"></i> Orders</a>
                </li>
                <% if (auth.isAdmin()){ %>
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active btn" href="list-users"><i class="fa-solid fa-users"></i> Users</a>
                </li>
                
                <li class="nav-item dropdown active">
      				<a style="font-size: 20px" class="nav-link dropdown-toggle btn active" type="button"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa-solid fa-th-list"></i> Products</a>
					<div class="dropdown-menu" style="background: #772593">
						<a class="dropdown-item" style="color: white; font-weight: bold; font-size: 15px;" href="addproduct.jsp">Add Product</a>
						<a class="dropdown-item" style="color: white; font-weight: bold; font-size: 15px;" href="updateproduct.jsp">Update Product</a>
						<a class="dropdown-item" style="color: white; font-weight: bold; font-size: 15px;" href="deleteproduct.jsp">Delete Product</a>
					</div>
   		 		</li>
                <% } %>
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active btn" href="user-logout"><i class="fa-solid fa-arrow-right-from-bracket"></i>
                        Logout</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active btn" href="register.jsp"><i class="fa-solid fa-user-plus"></i> Register</a>
                </li>
                <li class="nav-item">
                    <a style="font-size: 20px" class="nav-link active btn" href="login.jsp"><i class="fa-solid fa-arrow-right-to-bracket"></i> Login</a>
                </li>

                <% } %>

            </ul>
        </div>
    </div>
</nav>
