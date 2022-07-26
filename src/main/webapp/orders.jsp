<%@ page import="java.util.List" %>
<%@ page import="com.ecommercecapstone.model.Order" %>
<%@ page import="com.ecommercecapstone.dao.OrderDao" %>
<%@ page import="com.ecommercecapstone.connection.DBCon" %>
<%@ include file="includes/needed-code.jsp" %>

<%
    List<Order> orderList = null;
    if (auth != null) {
        request.setAttribute("auth", auth);
        orderList = new OrderDao(DBCon.getConnection()).listUserOrders(auth.getUserId());
    } else {
        response.sendRedirect("login.jsp");
    }

    int oId = 0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Orders</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>

<div class="container table-responsive">
    <div class="card-header my-3 fw-bold">All Orders</div>
    <table style="table-layout: fixed;" class="table table-light table-striped align-middle table-bordered">
        <thead>
        <tr>
            <th style="width: 10%" scope="col">Order Date</th>
            <th style="width: 60%" scope="col" colspan="4">Products In Order</th>
            <th style="width: 8%; text-align: right;" scope="col">Total Price</th>
            <th style="width: 8%; text-align: center" scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (orderList != null) {
                for (Order o : orderList) {
                    if (oId == 0) {
                        oId = o.getOrderId();%>
        <tr>
            <td><span class="fw-bold">Date: </span> <%=o.getOrderDate()%>
            </td>
            <td colspan="4">
                <table style="table-layout: fixed;" class="table mb-0 table-secondary table-striped align-middle table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 40%" scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th style="text-align: right;" scope="col">Quantity</th>
                        <th style="text-align: right;" scope="col">Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Order order : orderList) {
                            if (oId == order.getOrderId()) {%>
                    <tr>
                        <td><%=order.getProductName()%></td>
                        <td><%=order.getCategory()%></td>
                        <td style="text-align: right;"><%=order.getQuantity()%></td>
                        <td style="text-align: right;">$<%=nf.format(order.getOrderPrice())%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </td>
            <% int sum = 0;
                for (Order order : orderList) {
                    if (oId == order.getOrderId()) {
                        sum += order.getOrderPrice();
                    }
                }%>
            <td style="text-align: right;">$<%= nf.format(sum) %>
            </td>
            <td style="text-align: center"><a class="btn btn-danger" onclick="return confirm('Are you sure you want to cancel?')" href="cancel-order?id=<%=o.getOrderId()%>">Cancel</a></td>
        </tr>
        <%
        } else if (oId != o.getOrderId()) {
            oId = o.getOrderId();
        %>
        <tr>
            <td><span class="fw-bold">Date: </span> <%=o.getOrderDate()%>
            </td>
            <td colspan="4">
                <table style="table-layout: fixed;" class="table mb-0 table-secondary table-striped align-middle table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 40%" scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th style="text-align: right;" scope="col">Quantity</th>
                        <th style="text-align: right;" scope="col">Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Order order : orderList) {
                            if (oId == order.getOrderId()) {%>
                    <tr>
                        <td><%= order.getProductName() %></td>
                        <td><%= order.getCategory() %></td>
                        <td style="text-align: right;"><%= order.getQuantity() %></td>
                        <td style="text-align: right;">$<%= nf.format(order.getOrderPrice())%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </td>
            <% int sum = 0;
                for (Order order : orderList) {
                    if (oId == order.getOrderId()) {
                        sum += order.getOrderPrice();
                    }
                }%>
            <td style="text-align: right;">$<%= nf.format(sum) %>
            </td>
            <td style="text-align: center"><a class="btn btn-danger" onclick="return confirm('Are you sure you want to cancel?')" href="cancel-order?id=<%= o.getOrderId() %>">Cancel</a></td>
        </tr>
        <%
                    }
                }
            }
        %>
        </tbody>
    </table>
</div>

<%@ include file="includes/footer.jsp" %>
</body>
</html>
