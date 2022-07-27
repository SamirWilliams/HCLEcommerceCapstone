<style type = "text/css">
    .dropdown-menu> a:hover {
      background-color: #b82e95 !important;
    }
    .btn:hover{
        background-color: #b82e95;
        border-color: #b82e95;
    }
    .dropdown:hover .dropdown-menu 
	{
    	display: block;
	}
</style>
<nav style="background: #772593" class="navbar navbar-expand-lg navbar-dark">
    <div class="d-flex align-items-center justify-content-between" style="width: 100%">
        <a style="font-size: 25px" class="navbar-brand fw-bold ms-5 my-1" href="index.jsp">Ecommerce Shopping <em style="font-size: 30px" class="fas fa-dolly"></em></a>
        <div class="col-md-5">
            <form style="margin-bottom: 0" role="search" method="post" action="search.jsp">
                <div class="input-group">
                    <input style="background: #CBC3E3" type="search" name="search_bar"
                           placeholder="Search" class="form-control" required="" oninvalid="this.setCustomValidity('Please Enter Something.')" oninput="setCustomValidity('')">
                    <button class="btn bg-light" name="save" type="submit">
                        <em class="fa fa-search"></em>
                    </button>
                </div>
            </form>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse me-3" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a style="font-size: 18px" class="nav-link active btn" href="index.jsp"><em class="fa-solid fa-home"></em> Home</a>
                </li>
                <li class="nav-item">
                    <a style="font-size: 18px" class="nav-link active btn" href="cart.jsp"><em class="fa-solid fa-cart-shopping"></em> Shopping Cart
                        <span style="background: #2e73b8" class="badge">${cart_list.size()}</span></a>
                </li>
                <% if (auth != null) {%>
                <li class="nav-item">
                    <a style="font-size: 18px" class="nav-link active btn" href="orders.jsp"><em class="fa-solid fa-scroll"></em> Orders</a>
                </li>
                <% if (auth.isAdmin()){ %>
                <li class="nav-item">
                    <a style="font-size: 18px" class="nav-link active btn" href="list-users"><em class="fa-solid fa-users"></em> Users</a>
                </li>
                
                <li class="nav-item dropdown active">
      				<a style="font-size: 18px" class="nav-link dropdown-toggle btn active" type="button"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><em class="fa-solid fa-th-list"></em> Products</a>
					<div class="dropdown-menu" style="background: #772593">
						<a class="dropdown-item" style="color: white; font-weight: bold; font-size: 15px;" href="addproduct.jsp">Add Product</a>
						<a class="dropdown-item" style="color: white; font-weight: bold; font-size: 15px;" href="updateproduct.jsp">Update Product</a>
						<a class="dropdown-item" style="color: white; font-weight: bold; font-size: 15px;" href="deleteproduct.jsp">Delete Product</a>
					</div>
   		 		</li>
                <% } %>
                <li class="nav-item">
                    <a style="font-size: 18px" class="nav-link active btn" href="user-logout"><em class="fa-solid fa-arrow-right-from-bracket"></em>
                        Logout</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a style="font-size: 18px" class="nav-link active btn" href="register.jsp"><em class="fa-solid fa-user-plus"></em> Register</a>
                </li>
                <li class="nav-item">
                    <a style="font-size: 18px" class="nav-link active btn" href="login.jsp"><em class="fa-solid fa-arrow-right-to-bracket"></em> Login</a>
                </li>

                <% } %>

            </ul>
        </div>
    </div>
</nav>