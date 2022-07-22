


<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ecommerce</title>

<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/style2.css" rel="stylesheet" type="text/css" />
</head>
<style type="text/css">
.dropdown-menu>a:hover {
	background-color: #b82e95 !important;
}

.btn:hover {
	background-color: #b82e95;
	border-color: #b82e95;
}
</style>


<body>

	<div class="main-navbar shadow-sm sticky-top">
		<div class="top-navbar">
			<div class="container-fluid">
				<div class="row">
					<div
						class="col-md-2 my-auto d-none d-sm-none d-md-block d-lg-block">
						<h5 class="brand-name">XOXO</h5>
					</div>

					<div class="col-md-5 my-auto">
						<form role="search" method="post" action="search.jsp">
							<div class="input-group">
								<input type="search" name="search_bar"
									placeholder="Search your product" class="form-control" />
								<button class="btn bg-white" name="save" type="submit">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</form>
					</div>
					<div class="col-md-5 my-auto">

						<ul class="nav justify-content-end">

							<li class="nav-item"><a class="nav-link " href="cart.jsp">
									<i class="fa fa-shopping-cart"></i> Cart<span
									class="badge badge-warning px-1">${cart_list.size()}</span>
							</a></li>


							 <% if (auth != null) {%>
							<li class="nav-item"><a class="nav-link " href="orders.jsp">
									<i class="fa-solid fa-scroll"></i> Orders
							</a></li>


							<% if (auth.isAdmin()){ %>
							<li class="nav-item"><a class="nav-link " href="list-users">
									<i class="fa-solid fa-users"></i> Users
							</a></li>
							<li class="nav-item dropdown active"><a
								style="font-size: 20px" class="nav-link dropdown-toggle "
								type="button" data-toggle="dropdown" role="button"
								aria-haspopup="true" aria-expanded="false"><i
									class="fa-solid fa-th-list"></i> Products</a>
								<div class="dropdown-menu" style="background: #772593">
									<a class="dropdown-item"
										style="color: white; font-weight: bold; font-size: 15px;"
										href="addproduct.jsp">Add Product</a> <a class="dropdown-item"
										style="color: white; font-weight: bold; font-size: 15px;"
										href="updateproduct.jsp">Update Product</a> <a
										class="dropdown-item"
										style="color: white; font-weight: bold; font-size: 15px;"
										href="deleteproduct.jsp">Delete Product</a>
								</div></li>

							<% } %>
							<li class="nav-item"><a class="nav-link " href="user-logout">
									<i class="fa fa-loock"></i> Logout
							</a></li>

							 <% } else { %>
							<li class="nav-item"><a style="font-size: 20px"
								class="nav-link " href="register.jsp"><i
									class="fa-solid fa-user-plus"></i> Register</a></li>
							<li class="nav-item"><a style="font-size: 20px"
								class="nav-link active btn" href="login.jsp"><i
									class="fa-solid fa-arrow-right-to-bracket"></i> Login</a></li>

							 <% } %>



						</ul>
					</div>
				</div>
			</div>
		</div>
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<a class="navbar-brand d-block d-sm-block d-md-none d-lg-none"
					href="#"> E-commerce </a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="index.jsp">All
								Categories</a></li>

						<li class="nav-item"><a class="nav-link"
							href="product?cat=Phone">Phones </a></li>
						<li class="nav-item"><a class="nav-link"
							href="product?cat=Laptop">Laptops </a></li>
						<li class="nav-item"><a class="nav-link" name="electronics"
							href="index.jsp">Electronics</a></li>
						<li class="nav-item"><a class="nav-link"
							href="product?cat=Desktop">Desktops</a></li>
						

					</ul>
				</div>

			</div>
		</nav>
	</div>

</body>