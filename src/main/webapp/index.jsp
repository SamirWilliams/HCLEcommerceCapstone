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
%>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Welcome</title>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="container">
    <form action="" method="post">
        <div class="card-header my-3">Product Categories</div>
        <div class="row">
            <div class="col-md-4 my-3">
                <div class="card w-100 text-center" style="width: 18rem;">
                    <div class="card-body">
                        <h4 class="card-title">Phones</h4>
                        <div>
                            <a href="products.jsp?cat=Phone" class="btn btn-primary">Go</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 my-3">
                <div class="card w-100 text-center" style="width: 18rem;">
                    <div class="card-body">
                        <h4 class="card-title">Laptops</h4>
                        <div>
                            <a href="products.jsp?cat=Laptop" class="btn btn-primary">Go</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 my-3">
                <div class="card w-100 text-center" style="width: 18rem;">
                    <div class="card-body">
                        <h4 class="card-title">Desktops</h4>
                        <div>
                            <a href="products.jsp?cat=Desktop" class="btn btn-primary">Go</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>
