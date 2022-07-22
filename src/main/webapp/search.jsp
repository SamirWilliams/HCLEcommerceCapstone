
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.usermanagement.connection.DBCon"%>

<%@page import="java.util.*"%>
<%@ page import="com.usermanagement.model.Product" %>
<%@ page import="com.usermanagement.dao.ProductDao" %>
<%@ include file="includes/needed-code.jsp"%>




<%
String name = request.getParameter("search_bar");
ProductDao pda = new ProductDao(DBCon.getConnection());

Product p = pda.getProductByName(name);
%>






<!DOCTYPE html>
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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="includes/navbar-principal.jsp" %>
	<div class="container my-container">
		<div class="row my-row">

		<div class="my-3 mx-3 d-flex">
            <div class="card" style="width: 18rem;" id="my-card">
                <img class="card-img-top" src="assets/product-image/<%= p.getProductImage() %>" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductName() %>
                    </h5>
                    <h6 class="price">Price: $<%= p.getUnitPrice()%>
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


		</div>
	</div>
</body>
</html>