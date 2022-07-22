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
<style>
.my-container {
	margin-top: 20px;
}

#my-image{
	border-radius: 5px;
}

.my-row {
	
}

.my-col {
	
}

#my-card {
	box-shadow: 0 0 20px 2px rgba(0, 0, 0, .1);
	transition:2s;
	cursor: poiter;
}

#my-card:hover{
	transform:scale(1.1);
	z-index:1;
}



.my-bod {
	height: 10%;
}
</style>


    <%@ include file="includes/header.jsp" %>
    <title><%= productChoice %>s</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar-principal.jsp" %>

<div class="container">
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="col-md-1 btn btn-primary"><i class="fa-solid fa-arrow-left"> Back</i></a>
        <h1 class="flex-grow-1 text-center me-5"><%= productChoice %>s</h1>
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
    <div class="card-group" >
        <% if (!productList.isEmpty()) {
            for (Product p : productList) {
                if (p.getCategory().equals(productChoice)) { %>
        <div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;" id = "my-card">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" width = "200" height="300" alt="Card image cap">
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