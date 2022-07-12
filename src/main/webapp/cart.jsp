<%@ page import="com.usermanagement.connection.DBCon" %>
<%@ page import="com.usermanagement.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null){
        request.setAttribute("auth", auth);
    }
%>

<html>
<head>
    <%@ include file="includes/header.jsp"%>
    <title>Shopping Cart</title>
    <style>
        .table tbody td{
            vertical-align: middle;
        }
        .btn-incre, .btn-decre{
            box-shadow: none;
            font-size: 20px;
        }
    </style>
</head>
<body>
<%@ include file="includes/navbar.jsp"%>

<div class="container">
    <div class="d-flex py-3"><h3>Total Price: $1230</h3><a class="mx-3 ml-auto btn btn-primary" href="#">Check Out</a></div>
    <table class="table table-light">
        <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Category</th>
                <th scope="col">Price</th>
                <th scope="col">Buy Now</th>
                <th scope="col">Cancel</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Women Shoes</td>
                <td>Shoes</td>
                <td>$45</td>
                <td>
                    <form action="" method="post" class="form-inline">
                        <input type="hidden" name="id" value="1" class="form-input">
                        <div class="form-group d-flex justify-content-between"></div>
                        <a class="btn btn-sm btn-decre" href=""><i class="fas fa-minus-square"></i></a>
                        <input type="number" name="quantity" class="form-control" value="1" readonly>
                        <a class="btn btn-sm btn-incre" href=""><i class="fas fa-plus-square"></i></a>
                    </form>
                </td>
                <td><a class="btn btn-sm btn-danger" href="">Remove</a></td>
            </tr>
        </tbody>
    </table>
</div>

<%@ include file="includes/footer.jsp"%>
</body>
</html>
