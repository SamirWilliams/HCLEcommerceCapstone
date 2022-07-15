<%@ include file="includes/needed-code.jsp" %>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Users</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<div class="row nav-fix">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%= request.getContextPath() %>/new" class="btn btn-success">Add
                New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Address</th>
                <th>City</th>
                <th>ZipCode</th>
                <th>Country</th>
                <th>Actions</th>
            </tr>

            </thead>
            <tbody>

            <c:forEach var="user" items="${listUser}">

                <tr>
                    <td><c:out value="${user.userId}"/></td>
                    <td><c:out value="${user.firstName} ${user.lastName}"/></td>
                    <td><c:out value="${user.phoneNumber}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.city}"/></td>
                    <td><c:out value="${user.zipCode}"/></td>
                    <td><c:out value="${user.country}"/></td>
                    <td><a href="edit?id=<c:out value='${user.userId}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${user.userId}' />">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>