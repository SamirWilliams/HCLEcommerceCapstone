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

boolean wasUpdated = false;
if (request.getSession().getAttribute("wasUpdated") != null) {
    wasUpdated = (boolean) request.getSession().getAttribute("wasUpdated");
}

String productChoice = (String) request.getSession().getAttribute("category");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Update Product</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<div class = "container">
<%
if (wasUpdated) {%>
            <p style="margin-top: 20px; margin-bottom: -20px; text-align: center; color: red; font-size: 25px; font-weight: bold"> Product Updated. <a href="product?cat=<%= productChoice %>"> Check</a></p>
            <% request.getSession().setAttribute("wasUpdated", false);
}
%>
<div class = "card w-100 mx-auto my-5">
<div class = "p-2 mb-1" style = "background: #772593"><h4 class = "card-header text-center" style = "background: #772593">Update Product</h4></div>
<div class = "card-body">
<div class = "row">
	<%
		nf.setGroupingUsed(false);
		for(Product p : products)
	{%>
		<div class = "col-md-3 my-3">
			<form action = "update-product" method = "post">
				<div class = "card w-100 h-100" style = "background: #772593; width: 18rem;">
				<img class = "card-img-top" src = "assets/product-image/<%= p.getProductImage() %>" width = "200" height="300" alt = "Card image cap">
				<div class = "card-body">
					<h6 class = "image">Image (.jpg): <input type = "file" class = "form-control my-2" name = "prodimage" value = "<%= p.getProductImage() %>"></h6>
					<h6 class = "name">Name: <input type = "text" class = "form-control my-2" name = "prodname" value = "<%= p.getProductName() %>" ></h6>
					<h6 class = "price">Price: $<input type = "text" class = "form-control my-2" name = "prodprice" value = "<%= nf.format(p.getUnitPrice()) %>"></h6>
					<h6 class = "category">Category: <input type = "text" class = "form-control my-2" name = "prodcategory" value = "<%= p.getCategory() %>"></h6>
					<div class = "text-center">
						<input class = "invisible" name = "prodID" value = "<%= p.getProductId()%>">
						<button class = "btn btn-primary" style = "background: #333399">Update</button>
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