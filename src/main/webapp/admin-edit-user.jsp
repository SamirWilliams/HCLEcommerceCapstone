<%@ page import="java.util.List" %>
<%@ include file="includes/needed-code.jsp" %>

<%
    if (auth == null || !auth.isAdmin()) {
        response.sendRedirect("index.jsp");
    }

    List<User> userList = (List<User>) request.getSession().getAttribute("userList");

    User user = null;
    for (User u : userList) {
        if (u.getUserId() == Integer.parseInt(request.getParameter("id"))) {
            user = u;
        }
    }
%>

<html>
<head>
   <%@ include file="includes/navbar-principal.jsp" %>
    <title>Edit User</title>
</head>
<body class="gradient-custom1">

<div class="container">
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="users.jsp" class="col-md-1 btn btn-primary"><i class="fa-solid fa-arrow-left"> Back</i></a>
        <h1 class="flex-grow-1 text-center me-5">Edit User</h1>
    </div>
    <div class="card w-50 mx-auto my-5">
        <%
            String userEditError = (String) request.getAttribute("userEditError");
            if (userEditError != null) {%>
        <div class="card-header">
            <%out.println("<font color=red size=4px>" + userEditError + "</font>");%>
        </div>
        <%
            }
        %>
        <div class="card-header text-center">Edit User Form</div>
        <div class="card-body">
            <div style="text-align: center">
                <p style="color: red">Ignore fields you don't plan on changing</p>
            </div>
            <%
                if (user != null) {
                    if (user.getUserId() == 1) {
                        request.getSession().setAttribute("adminAccountChange", true);
                        response.sendRedirect("list-users");
                    } else if (user.getUserId() == Integer.parseInt(request.getParameter("id"))) {
            %>
            <form class="row g-3" action="edit-user" method="post">
                <input type="hidden" name="userId" value="<%= user.getUserId()%>">
                <div class="col-6">
                    <label class="form-label" for="edit-fName">First Name</label>
                    <input type="text" class="form-control" id="edit-fName" name="edit-fName"
                           value="<%= user.getFirstName()%>" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="edit-lName">Last Name</label>
                    <input type="text" class="form-control" id="edit-lName" name="edit-lName"
                           value="<%= user.getLastName()%>" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="edit-phoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="edit-phoneNumber" name="edit-phoneNumber"
                           value="<%= user.getPhoneNumber()%>" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="edit-email">Email Address</label>
                    <input type="email" class="form-control" id="edit-email" name="edit-email"
                           value="<%= user.getEmail()%>" required>
                </div>
                <div class="col-12">
                    <label class="form-label" for="edit-address">Address</label>
                    <input type="text" class="form-control" id="edit-address" name="edit-address"
                           value="<%= user.getAddress()%>" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="edit-city">City</label>
                    <input type="text" class="form-control" id="edit-city" name="edit-city"
                           value="<%= user.getCity()%>" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="edit-zipcode">ZipCode</label>
                    <input type="text" class="form-control" id="edit-zipcode" name="edit-zipcode"
                           value="<%= user.getZipCode()%>" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="edit-country">Country</label>
                    <input type="text" class="form-control" id="edit-country" name="edit-country"
                           value="<%= user.getCountry()%>" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="edit-adminStatus">Is this user an Admin?</label>
                    <select id="edit-adminStatus" name="edit-adminStatus" class="form-select">
                        <option value="0">No</option>
                        <option value="1">Yes</option>
                    </select>
                </div>
                <div class="mt-4 d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary btn-lg">Update User</button>
                </div>
            </form>
            <% }
            }
            %>
        </div>
    </div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>

