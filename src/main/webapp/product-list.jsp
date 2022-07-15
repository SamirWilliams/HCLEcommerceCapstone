<%@ page import="com.usermanagement.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="includes/needed-code.jsp" %>

<%
    List<Product> productList = (List<Product>) request.getSession().getAttribute("productList");

    boolean alreadyInCart = false;
    if (request.getSession().getAttribute("alreadyInCart") != null) {
        alreadyInCart = (Boolean) request.getSession().getAttribute("alreadyInCart");
    }

    boolean addedToCart = false;
    if (request.getSession().getAttribute("addedToCart") != null) {
        addedToCart = (Boolean) request.getSession().getAttribute("addedToCart");
    }

    String productChoice = (String) request.getSession().getAttribute("category");
%>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title><%= productChoice %>s</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>

<div class="container nav-fix">
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="col-md-1 btn btn-primary">Back</a>
        <h1 class="flex-grow-1 text-center me-5"><%= productChoice %>s</h1>
    </div>
    <%
        if (alreadyInCart) { %>
    <h3 style='color:crimson; text-align: center'>Item Already in Cart</h3>
    <% request.getSession().setAttribute("alreadyInCart", false);
		}
        if (addedToCart) { %>
    <h3 style='text-align: center'>Item Added to Cart</h3>
    <% request.getSession().setAttribute("addedToCart", false);
    } %>
    <div class="card-group">
        <% if (!productList.isEmpty()) {
            for (Product p : productList) {
                if (p.getCategory().equals(productChoice)) { %>
        <div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                </div>
                <div class="card-footer">
                    <div class="mx-1 d-flex justify-content-between">
                        <a href="order-now?quantity=1&id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>" class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&price=<%= p.getUnitPrice() %>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <%
                    }
                }
            }
        %>

    </div>
</div>

<%@ include file="includes/footer.jsp" %>
</body>
</html>
