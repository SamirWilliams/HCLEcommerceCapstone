<%@page import="com.ecommercecapstone.connection.DBCon" %>
<%@ page import="com.ecommercecapstone.model.Product" %>
<%@ page import="com.ecommercecapstone.dao.ProductDao" %>
<%@ include file="includes/needed-code.jsp" %>

<%
    String name = request.getParameter("search_bar");
    ProductDao pda = new ProductDao(DBCon.getConnection());

    Product p = pda.getProductByName(name);
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
    </div>
    <div class="row my-row">
        <div class="my-3 mx-3 d-flex">
            <div class="card my-card" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %></h5>
                    <h6 class="price">Price: $<%= p.getUnitPrice()%></h6>
                </div>
                <div class="card-footer">
                    <div class="mx-1 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>