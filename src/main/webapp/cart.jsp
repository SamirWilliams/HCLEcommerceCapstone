<%@ page import="com.usermanagement.model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.usermanagement.dao.ProductDao" %>
<%@ page import="com.usermanagement.connection.DBCon" %>

<%@ include file="includes/needed-code.jsp" %>

<%
    List<Cart> cartProduct = null;
    if (cart_List != null) {
        ProductDao productDao = new ProductDao(DBCon.getConnection());
        cartProduct = productDao.getCartProducts(cart_List);
        request.setAttribute("cart_list", cart_List);

        double total = productDao.getTotalCartPrice(cart_List);
        request.setAttribute("cart_total", total);
    }
%>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Shopping Cart</title>
    <style>
        .table tbody td {
            vertical-align: middle;
        }

        .btn-incre, .btn-decre {
            box-shadow: none;
            font-size: 20px;
        }
    </style>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>

<div class="container">
    <div class="my-3 d-flex justify-content-between align-items-center">
        <h3>Total Price: <fmt:formatNumber value="${(cart_total > 0)?cart_total:0.00}" type="currency"/></h3>
        <a class="mx-3 btn btn-primary" href="#">Check Out</a>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Remove Item</th>
        </tr>
        </thead>
        <tbody>
        <% if (cart_List != null) {
            for (Cart c : cartProduct) { %>
        <tr>
            <td><%= c.getProductName() %></td>
            <td><%= c.getCategory() %></td>
            <td>$<%= nf.format(c.getUnitPrice())%></td>
            <td>
                <form action="order-now" method="post" class="d-flex">
                    <input type="hidden" name="id" value="<%= c.getProductId() %>" class="form-input">
                    <input type="hidden" name="price" value="<%= c.getUnitPrice() %>" class="form-input">
                    <div class="pt-3 form-group d-flex justify-content-start align-items-center">
                        <a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getProductId()%>"><i
                                class="fas fa-minus-square"></i></a>
                        <input type="number" name="quantity" class="form-control w-50" value="<%=c.getQuantity()%>"
                               readonly>
                        <a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getProductId()%>"><i
                                class="fas fa-plus-square"></i></a>
                    </div>
                    <button class="btn btn-primary ms-auto align-self-end">Buy Now</button>
                    <div class="ms-auto align-self-end"></div>
                </form>
            </td>
            <td><a class="btn btn-danger" href="remove-from-cart?id=<%=c.getProductId()%>">Remove</a></td>
        </tr>
        <% }
        } %>

        </tbody>
    </table>
</div>

<%@ include file="includes/footer.jsp" %>
</body>
</html>
