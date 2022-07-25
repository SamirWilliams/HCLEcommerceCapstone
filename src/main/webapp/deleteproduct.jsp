<%@ page import="com.ecommercecapstone.model.Product" %>
<%@ page import="com.ecommercecapstone.dao.ProductDao" %>
<%@ page import="com.ecommercecapstone.connection.DBCon" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/needed-code.jsp" %>
<%
List<Product> productList = (List<Product>) request.getSession().getAttribute("productList");
ProductDao prd = new ProductDao(DBCon.getConnection());
List<Product> products = prd.getAllProducts();

boolean wasDeleted = false;
if (request.getSession().getAttribute("wasDeleted") != null) {
	wasDeleted = (boolean) request.getSession().getAttribute("wasDeleted");
}

String productChoice = (String) request.getSession().getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Delete Product</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<div class = "container">
<%
if (wasDeleted) {%>
            <p style="margin-top: 20px; margin-bottom: -20px; text-align: center; color: red; font-size: 25px; font-weight: bold"> Product Deleted.</p>
            <% request.getSession().setAttribute("wasDeleted", false);
}
%>
<div class = "card w-100 mx-auto my-5">
<div class = "p-2 mb-1" style = "background: #772593"><h4 class = "card-header text-center" style = "background: #772593">Delete Product</h4></div>
<div class = "card-body">
<div class = "row">
	<%
	for(Product p : products)
	{%>
	<div class = "col-md-3 my-3">
		<form action = "delete-product" method = "post">
			<div class = "card w-100 h-100" style = "background: #772593; width: 18rem;">
				<img class = "card-img-top" src = "assets/product-image/<%= p.getProductImage() %>" width = "200" height="300" alt = "Card image cap">
				<div class = "card-body w-100 h-100">
					<h5 class = "card-title"><%= p.getProductName() %></h5>
					<h6 class = "price">Price: $<%= nf.format(p.getUnitPrice()) %></h6>
					<h6 class = "category">Category: <%= p.getCategory() %></h6>
					<div class = "text-center">
						<input class = "invisible" name = "prodID" value = "<%= p.getProductId()%>">
						<button class = "btn btn-danger" onclick="return confirm('Are you sure you want to delete <%= p.getProductName() %>?')">Delete</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<%} %>
</div>
</div>
</div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>