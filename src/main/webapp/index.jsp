<%@ page import="java.util.List" %>
<%@ page import="com.ecommercecapstone.model.Product" %>
<%@ include file="includes/needed-code.jsp"%>
<%
	List<Product> productList = (List<Product>) request.getSession().getAttribute("productList");
	String productChoice = (String) request.getSession().getAttribute("category");
	String productId = (String) request.getSession().getAttribute("id");
	
	boolean alreadyInCart = false;
    if (request.getSession().getAttribute("alreadyInCart") != null) {
        alreadyInCart = (Boolean) request.getSession().getAttribute("alreadyInCart");
    }

    boolean addedToCart = false;
    if (request.getSession().getAttribute("addedToCart") != null) {
        addedToCart = (Boolean) request.getSession().getAttribute("addedToCart");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Home</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<%@ include file="includes/cat-navbar.jsp" %>
<div class="container">
<%
    if (alreadyInCart) { %>
    <h3 style='color:crimson; text-align: center'>Item Already in Cart</h3>
    <% request.getSession().setAttribute("alreadyInCart", false);
	}
    if (addedToCart) { %>
    <h3 style='color:black; text-align: center'>Item Added to Cart</h3>
    <% request.getSession().setAttribute("addedToCart", false);
    } 
%>
    <div class="card-header text-center my-3"><h1><em class="fa-solid fa-fire"></em> Hot Products</h1></div>
    <div class="row">
        <div class="col-md-4 my-2">
        <div class="card w-100 h-100 text-center" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/iPhone13ProMax.jpg" width = "300" height="500" alt="assets/product-image/Default.jpg">
                <div class="card-body">
                <div class= "text-danger my-1" style="font-weight: bold; font-size: 25px">SALE</div>
                    <h5 class="card-title" style="font-weight: bold">Apple - iPhone 13 Pro Max
                    </h5>
                    <h6 class="price" style="font-weight: bold">Price: <del class="text-danger" style="font-style: italic">$1,199.00</del> $1,099.00 </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-5 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=3&price=1099.00" class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=3&price=1099.00&pagetype=1"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
        </div>
        </div>

        <div class="col-md-4 my-2">
        <div class="card w-100 h-100 text-center" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/ASUSVivoBook15.jpg" width = "300" height="500" alt="assets/product-image/Default.jpg">
                <div class="card-body">
                <div class= "text-danger my-1" style="font-weight: bold; font-size: 25px">SALE</div>
                    <h5 class="card-title" style="font-weight: bold">ASUS VivoBook 15
                    </h5>
                    <h6 class="price" style="font-weight: bold">Price: <del class="text-danger" style="font-style: italic">$999.00</del> $989.00 </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-5 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=4&price=989.00" class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=4&price=989.00&pagetype=1"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
        </div>
        
        </div>
       <div class="col-md-4 my-2">
        <div class="card w-100 h-100 text-center" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/CyberpowerPCGamingDesktop.jpg" width = "300" height="500" alt="assets/product-image/Default.jpg">
                <div class="card-body">
                <div class= "text-danger my-1" style="font-weight: bold; font-size: 25px">SALE</div>
                    <h5 class="card-title" style="font-weight: bold">Cyberpower PC Gaming Desktop
                    </h5>
                    <h6 class="price" style="font-weight: bold">Price: <del class="text-danger" style="font-style: italic">$1,999.00</del> $1,839.00 </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-5 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=7&price=1839.00" class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=7&price=1839.00&pagetype=1"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
        </div>
        </div>
    </div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>