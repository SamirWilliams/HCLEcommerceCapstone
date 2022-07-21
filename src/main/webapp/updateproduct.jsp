<%@ page import="com.usermanagement.model.Product" %>
<%@ page import="com.usermanagement.dao.ProductDao" %>
<%@ page import="com.usermanagement.connection.DBCon" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/needed-code.jsp" %>
<%
List<Product> productList = (List<Product>) request.getSession().getAttribute("productList");
ProductDao prd = new ProductDao(DBCon.getConnection());
List<Product> products = prd.getAllProducts();
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Update Product</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<div class = "container">
<div class = "card w-100 mx-auto my-5">
<div class = "p-2 mb-1" style = "background: #772593"><h4 class = "card-header text-center" style = "background: #772593">Update Product</h4></div>
<div class = "card-body">
<div class = "row">
	<%
	for(Product p : products)
	{%>
		<div class = "col-md-3 my-3">
			<form action = "update-product" method = "post">
				<div class = "card w-100 h-100" style = "background: #772593; width: 18rem;">
				<img class = "card-img-top" src = "assets/product-image/<%= p.getProductImage() %>" width = "200" height="300" alt = "Card image cap">
				<div class = "card-body">
					<h6 class = "image">Image (.jpg): <input type = "text" class = "form-control my-2" name = "prodimage" placeholder = "<%= p.getProductImage() %>"></h6>
					<h6 class = "name">Name: <input type = "text" class = "form-control my-2" name = "prodname" placeholder = "<%= p.getProductName() %>" required></h6>
					<h6 class = "price">Price: $<input type = "text" class = "form-control my-2" name = "prodprice" placeholder = "<%= p.getUnitPrice() %>" required></h6>
					<h6 class = "category">Category: <input type = "text" class = "form-control my-2" name = "prodcategory" placeholder = "<%= p.getCategory() %>" required></h6>
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