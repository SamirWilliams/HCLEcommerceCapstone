<%@ page import="com.ecommercecapstone.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/needed-code.jsp" %>
<%
    List<Product> productList = (List<Product>) request.getSession().getAttribute("productList");

	boolean wasAdded = false;
	if (request.getSession().getAttribute("wasAdded") != null) {
    	wasAdded = (boolean) request.getSession().getAttribute("wasAdded");
	}
	
	String productChoice = (String) request.getSession().getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Add Product</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<div class = "container">
<%
if (wasAdded) {%>
            <p style="margin-top: 20px; margin-bottom: -20px; text-align: center; color: red; font-size: 25px; font-weight: bold"> Product Posted. <a href="product?cat=<%= productChoice %>"> Check</a></p>
            <% request.getSession().setAttribute("wasAdded", false);
}
%>
<div class = "card w-100 mx-auto my-5">
<div class = "p-2 mb-1" style = "background: #772593"><h4 class = "card-header text-center" style = "background: #772593">Add Product</h4></div>
<div class = "card-body mx-auto">
<div class = "row">
<table>
	<thead></thead>
	<tbody>
		<tr>
			<form action = "add-product" method = "post">
				<div class = "col-md-15 my-3">
					<div class = "card w-100">
						<div class = "card-body text-left" style = "background: #772593; width: 900px;">
							<h6 class = "image my-1">Image (.jpg): <input type = "file" class = "form-control my-2" name = "prodimage" placeholder = "ImageName.jpg"></h6>
							<h6 class = "name my-3">Product Name*: <input type = "name" class = "form-control my-2" name = "prodname" placeholder = "Enter Product Name" required></h6>
							<h6 class = "price my-4">Product Price*: $<input type = "text" class = "form-control my-2" name = "prodprice" placeholder = "Enter Product Price" required></h6>
							<h6 class = "category my-4">Product Category*: <input type = "text" class = "form-control my-2" name = "prodcategory" placeholder = "Enter Product Category" required></h6>
							<div class = "text-center">
								<button class = "btn btn-primary my-2" style = "background: #333399">Post</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>