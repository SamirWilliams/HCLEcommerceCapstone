<%@ page import="com.usermanagement.connection.DBCon" %>
<%@ page import="com.usermanagement.model.User" %>
<%@ page import="com.usermanagement.dao.ProductDao" %>
<%@ page import="com.usermanagement.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    ProductDao productDao = new ProductDao(DBCon.getConnection());
    List<Product> productList = productDao.getAllProducts();

    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);

	boolean exist = false;
	if(request.getAttribute("exist") != null){
        exist = (Boolean) request.getAttribute("exist");
    }

    String productChoice = request.getParameter("cat");

%>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title><%= productChoice %>s</title>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>

<div class="container">
    <div class="card-header my-3">
        <a href="index.jsp" class="btn btn-primary">Back</a>
    </div>
    <h1 style="text-align: center"><%= productChoice %>s</h1>
    <%
        if (exist) {%>
    <h3 style='color:crimson; text-align: center'>Item Already in Cart</h3>
    <%
        } else {%>
			<h3 style='text-align: center'>Item Added to Cart</h3>
	<%  }%>
    <div class="card-deck">
        <% if (!productList.isEmpty()) {
            for (Product p : productList) {
                switch (productChoice) {
                    case "Phone":
                        if (p.getCategory().equals("Phone")) { %>
        <div class="col-md-3 my-3">
            <div class="card w-100" style="width: 18rem;">
                <img class="card-img-top" src="product-image/<%= p.getProductImage() %>" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                    <div class="mt-3 d-flex justify-content-between">
                        <a href="#" class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&cat=<%= p.getCategory()%>"
                           class="btn btn-outline-primary">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <%
                }
                break;
            case "Laptop":
                if (p.getCategory().equals("Laptop")) {
        %>
        <div class="col-md-3 my-3">
            <div class="card w-100" style="width: 18rem;">
                <img class="card-img-top" src="product-image/<%= p.getProductImage() %>" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                    <div class="mt-3 d-flex justify-content-between">
                        <a href="#" class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&cat=<%= p.getCategory()%>">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <% }
            break;
            case "Desktop":
                if (p.getCategory().equals("Desktop")) { %>
        <div class="col-md-3 my-3">
            <div class="card w-100" style="width: 18rem;">
                <img class="card-img-top" src="product-image/<%= p.getProductImage() %>" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= nf.format(p.getUnitPrice()) %>
                    </h6>
                    <div class="mt-3 d-flex justify-content-between">
                        <a href="#" class="btn btn-primary">Buy Now</a>
                        <a href="add-to-cart?id=<%= p.getProductId() %>&cat=<%= p.getCategory()%>">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
        <% }
            break;
            default:
                break;
        }
        }
        }
        %>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
</body>
</html>
