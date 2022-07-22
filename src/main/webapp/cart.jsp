<%@ page import="com.ecommercecapstone.model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommercecapstone.dao.ProductDao" %>
<%@ page import="com.ecommercecapstone.connection.DBCon" %>

<%@ include file="includes/needed-code.jsp" %>

<%
    List<Cart> cartProduct = null;
    if (cart_List != null) {
        ProductDao productDao = new ProductDao(DBCon.getConnection());
        cartProduct = productDao.getCartProducts(cart_List);

        double total = productDao.getTotalCartPrice(cart_List);
        request.setAttribute("cart_total", total);
    }
%>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Shopping Cart</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>

<div class="container table-responsive">
    <form action="cart-check-out" method="post" class="d-flex">
        <div class="mt-3 mb-1 w-100 d-flex align-items-center">
            <h3 style="color: black" class="fw-bold">Total Price: <fmt:formatNumber value="${(cart_total > 0)?cart_total:0.00}" type="currency"/></h3>
            <button class="mx-3 ms-auto btn btn-primary"><i class="fa-solid fa-credit-card"></i> Check Out</button>
        </div>
    </form>

    <table style="table-layout: fixed;" class="table table-light table-striped align-middle table-bordered">
        <thead>
        <tr>
            <th style="width: 25%" scope="col">Name</th>
            <th style="width: 10%" scope="col">Category</th>
            <th style="width: 10%; text-align: right" scope="col">Price</th>
            <th style="width: 40%" scope="col">Quantity</th>
            <th style="width: 20%; text-align: center" scope="col">Remove Item</th>
        </tr>
        </thead>
        <tbody>
        <% if (cartProduct != null) {
            for (Cart c : cartProduct) { %>
        <tr>
            <td><%= c.getProductName() %></td>
            <td><%= c.getCategory() %></td>
            <td style="text-align: right">$<%= nf.format(c.getUnitPrice())%></td>
            <td>
                <form action="order-now" method="post" class="d-flex align-items-center">
                    <input type="hidden" name="id" value="<%= c.getProductId() %>" class="form-input">
                    <input type="hidden" name="price" value="<%= c.getUnitPrice() %>" class="form-input">
                    <div class="pt-3 form-group d-flex align-items-center">
                        <input style="-webkit-appearance: none; -moz-appearance: textfield;" type="number" name="quantity" class="form-control w-75" value="<%=c.getQuantity()%>"
                               readonly>
                        <div class="d-flex flex-column justify-content-end">
                            <a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getProductId()%>"><i
                                class="fas fa-plus-square"></i></a>
                            <a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getProductId()%>"><i
                                    class="fas fa-minus-square"></i></a>
                        </div>
                    </div>
                        <div class="ps-5 mt-3 ms-auto d-flex flex-column">
                            <button style="min-width: 55px;" class="btn btn-primary ">Buy Now</button>
                        </div>
                    <div class="ms-auto align-self-end"></div>
                </form>
            </td>
            <td style="text-align: center"><a class="btn btn-danger" onclick="return confirm('Are you sure you want to remove this item?')" href="remove-from-cart?id=<%=c.getProductId()%>">Remove</a></td>
        </tr>
        <% }
        } %>

        </tbody>
    </table>
</div>

<%@ include file="includes/footer.jsp" %>
</body>
</html>
