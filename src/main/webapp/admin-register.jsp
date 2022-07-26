<%@ include file="includes/needed-code.jsp" %>

<%
    if (auth == null || !auth.isAdmin()){
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Admin Register</title>
</head>
<body class="gradient-custom1">

<div class="container">
    <div class="card-header my-3 d-flex justify-content-between align-items-center">
        <a href="users.jsp" class="col-md-1 btn btn-primary"><em class="fa-solid fa-arrow-left"> Back</em></a>
        <h1 class="flex-grow-1 text-center me-5">Add User</h1>
    </div>
    <div class="card w-50 mx-auto my-5">
        <%
            String register_msg = (String) request.getAttribute("registerError");
            if (register_msg != null) {%>
        <div class="card-header">
            <%out.println("<font color=red size=4px>" + register_msg + "</font>");%>
        </div>
        <%
            }
        %>
        <div class="card-header text-center">User Registration Form</div>
        <div class="card-body">
            <form class="row g-3" action="user-register" method="post">
                <input type="hidden" name="admin-page" value="1">
                <div class="col-6">
                    <label class="form-label" for="register-fName">First Name</label>
                    <input type="text" class="form-control" id="register-fName" name="register-fName" placeholder="Enter First Name"
                           required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="register-lName">Last Name</label>
                    <input type="text" class="form-control" id="register-lName" name="register-lName" placeholder="Enter Last Name"
                           required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="register-phoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="register-phoneNumber" name="register-phoneNumber"
                           placeholder="Enter Phone Number" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="register-email">Email Address</label>
                    <input type="email" class="form-control" id="register-email" name="register-email" placeholder="Enter Email"
                           required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="register-password">Password (Minimum 8 Characters)</label>
                    <input type="password" class="form-control" id="register-password" name="register-password" minlength="8"
                           placeholder="********" required>
                </div>
                <div class="col-6">
                </div>
                <div class="col-12">
                    <label class="form-label" for="register-address">Address</label>
                    <input type="text" class="form-control" id="register-address" name="register-address" placeholder="Enter Address"
                           required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="register-city">City</label>
                    <input type="text" class="form-control" id="register-city" name="register-city" placeholder="Enter City" required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="register-zipcode">ZipCode</label>
                    <input type="text" class="form-control" id="register-zipcode" name="register-zipcode" placeholder="Enter ZipCode"
                           required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="register-country">Country</label>
                    <input type="text" class="form-control" id="register-country" name="register-country" placeholder="Enter Country"
                           required>
                </div>
                <div class="col-6">
                    <label class="form-label" for="register-adminStatus">Is this user an Admin?</label>
                    <select id="register-adminStatus" name="register-adminStatus" class="form-select">
                        <option selected value="0">No</option>
                        <option value="1">Yes</option>
                    </select>

                </div>
                <div class="mt-4 d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary btn-lg">Register User</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>
