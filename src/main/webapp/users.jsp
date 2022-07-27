<%@ page import="java.util.List" %>
<%@ include file="includes/needed-code.jsp" %>

<%
    if (auth == null || !auth.isAdmin()) {
        response.sendRedirect("index.jsp");
    }

	boolean adminAccountChange = false;
	if (request.getSession().getAttribute("adminAccountChange") != null) {
        adminAccountChange = (boolean) request.getSession().getAttribute("adminAccountChange");
    }

	boolean adminAccountDelete = false;
	if (request.getSession().getAttribute("adminAccountDelete") != null) {
        adminAccountDelete = (boolean) request.getSession().getAttribute("adminAccountDelete");
    }

    List<User> userList = (List<User>) request.getSession().getAttribute("userList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Users</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<div class="container">
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <h1 class="flex-grow-1 text-center">List of Users</h1>
    </div>
    <div class="my-4 w-100 d-flex align-items-center">
        <a href="admin-register.jsp" class="ms-auto btn btn-primary">Add New User</a>
    </div>
    <%
        if (adminAccountChange) {%>
            <p style="text-align: center; color: red; font-size: 25px">You are not allowed to change Admin account</p>
            <% request.getSession().setAttribute("adminAccountChange", false);
		}
		if (adminAccountDelete) {%>
            <p style="text-align: center; color: red; font-size: 25px">You are not allowed to delete Admin account</p>
            <% request.getSession().setAttribute("adminAccountDelete", false);
        }
    %>
    <table style="table-layout: auto;" class="table table-light table-striped align-middle table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Address</th>
            <th>City</th>
            <th>ZipCode</th>
            <th>Country</th>
            <th>Admin Status</th>
            <th style="width: 13%">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (userList != null && !userList.isEmpty()) {
                for (User user : userList) {%>
        <tr>
            <td><%= user.getFirstName() + " " + user.getLastName() %></td>
            <td><%= user.getPhoneNumber() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getAddress() %></td>
            <td><%= user.getCity() %></td>
            <td><%= user.getZipCode() %></td>
            <td><%= user.getCountry() %></td>
            <%if (user.isAdmin()) { %>
            <td>Yes</td>
            <% } else { %>
            <td>No</td>
            <% }%>
            <td >
                <a class="btn btn-primary me-3" href="admin-edit-user.jsp?id=<%= user.getUserId() %>">Edit</a>
                <a class="btn btn-danger" onclick="return confirm('Are you sure you want to delete?')" href="delete-user?id=<%= user.getUserId() %>">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>

    </table>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>