<%@page import="com.ecommercecapstone.connection.DBCon" %>
<%@ page import="com.ecommercecapstone.model.Product" %>
<%@ page import="com.ecommercecapstone.dao.ProductDao" %>
<%@ page import="java.util.List" %>
<%@ include file="includes/needed-code.jsp" %>

<%
    String name = request.getParameter("search_bar");
    ProductDao prd = new ProductDao(DBCon.getConnection());
    List<Product> products = prd.getAllProducts();
    String productChoice = (String) request.getSession().getAttribute("category");
    Product pd = prd.getProductByName(name);

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
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Search</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<%@ include file="includes/cat-navbar.jsp" %>
<div class="container">
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="col-md-1 btn btn-primary"><i class="fa-solid fa-arrow-left"> Back</i></a>
        <h1 class="flex-grow-1 text-center me-5">Phones</h1>
    </div>
    <%
        if (alreadyInCart) { %>
    <h3 style='color:crimson; text-align: center'>Item Already in Cart</h3>
    <% request.getSession().setAttribute("alreadyInCart", false);
    }
        if (addedToCart) { %>
    <h3 style='color:black; text-align: center'>Item Added to Cart</h3>
    <% request.getSession().setAttribute("addedToCart", false);
    } %>
    <%
        if (name.equalsIgnoreCase("phone") || name.equalsIgnoreCase("Phone") || name.equalsIgnoreCase("phones") || name.equalsIgnoreCase("Phones")) {%>
    <div class="card-group">
        <%
            for (Product p : products) {%>
        <%
            if (p.getCategory().equalsIgnoreCase("Phone")) {%>
        <div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" width="200" height="300"
                     alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-1 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>&pagetype=2&usersearch=<%= name %>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <%}%>
    </div>
    <%} else if (name.equalsIgnoreCase("laptop") || name.equalsIgnoreCase("Laptop") || name.equalsIgnoreCase("laptops") || name.equalsIgnoreCase("Laptops")) {%>
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="col-md-1 btn btn-primary"><i class="fa-solid fa-arrow-left"> Back</i></a>
        <h1 class="flex-grow-1 text-center me-5">Laptops</h1>
    </div>
    <div class="card-group">
        <%
            for (Product p : products) {%>
        <%
            if (p.getCategory().equalsIgnoreCase("Laptop")) {%>
        <div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" width="200" height="300"
                     alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-1 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>&pagetype=2&usersearch=<%= name %>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <%}%>
    </div>
    <%} else if (name.equalsIgnoreCase("desktop") || name.equalsIgnoreCase("Desktops") || name.equalsIgnoreCase("Desktop") || name.equalsIgnoreCase("desktops")) {%>
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="col-md-1 btn btn-primary"><i class="fa-solid fa-arrow-left"> Back</i></a>
        <h1 class="flex-grow-1 text-center me-5">Desktops</h1>
    </div>
    <div class="card-group">
        <%
            for (Product p : products) {%>
        <%
            if (p.getCategory().equalsIgnoreCase("Desktop")) {%>
        <div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" width="200" height="300"
                     alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-1 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>&pagetype=2&usersearch=<%= name %>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <%}%>
    </div>
    <%} else if (name.contains("google") || name.contains("GOOGLE") || name.contains("Google") || name.contains("PIXEL") || name.contains("Pixel") || name.contains("pixel")) {%>
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="col-md-1 btn btn-primary"><i class="fa-solid fa-arrow-left"> Back</i></a>
        <h1 class="flex-grow-1 text-center me-5">Google Products</h1>
    </div>
    <div class="card-group">
        <%
            for (Product p : products) {%>
        <%
            if (p.getProductName().contains("Google") || p.getProductName().contains("Pixel")) {%>
        <div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" width="200" height="300"
                     alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-1 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>&pagetype=2&usersearch=<%= name %>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <%}%>
    </div>
    <%} else if (name.contains("apple") || name.contains("Apple") || name.contains("APPLE") || name.contains("iPhone") || name.contains("IPHONE") || name.contains("iphone") || name.contains("Iphone")) {%>
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="col-md-1 btn btn-primary"><i class="fa-solid fa-arrow-left"> Back</i></a>
        <h1 class="flex-grow-1 text-center me-5">Apple Products</h1>
    </div>
    <div class="card-group">
        <%
            for (Product p : products) {%>
        <%
            if (p.getProductName().contains("iPhone") || p.getProductName().contains("Apple")) {%>
        <div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" width="200" height="300"
                     alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-1 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>&pagetype=2&usersearch=<%= name %>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <%}%>
    </div>
    <%} else {%>
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="col-md-1 btn btn-primary"><i class="fa-solid fa-arrow-left"> Back</i></a>
        <h1 class="flex-grow-1 text-center me-5">Search Results</h1>
    </div>
    <div class="card-group">
        <%
            for (Product p : products) {%>
        <%
            if (p.getProductName().contains(name)) {%>
        <div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" width="200" height="300"
                     alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-1 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>&pagetype=2&usersearch=<%= name %>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <%}%>
    </div>
    <%}%>
</div>
</body>
</html>