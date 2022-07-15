<%@ include file="includes/needed-code.jsp" %>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Register</title>
</head>
<body class="gradient-custom1">
    <%@ include file="includes/navbar.jsp" %>
    <div class="container nav-fix">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">Registration Form</div>
            <div class="card-body">
                <form class="row g-3" action="user-register" method="post">
                    <div class="col-6">
                        <label class="form-label">First Name</label>
                        <input type="email" class="form-control" name="register-fName" placeholder="Enter Your First Name" required>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Last Name</label>
                        <input type="password" class="form-control" name="register-lName" placeholder="Enter Your First Name" required>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Phone Number</label>
                        <input type="password" class="form-control" name="register-phoneNumber" placeholder="Enter Your Phone Number" required>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Email Address</label>
                        <input type="email" class="form-control" name="register-email" placeholder="Enter Your Email" required>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Password</label>
                        <input type="password" class="form-control" name="register-password" placeholder="********" required>
                    </div>
                    <div class="mt-4 d-flex justify-content-center">
                        <button type="button" class="btn btn-primary btn-lg">Register</button>
                    </div>
                    <p class="text-center text-muted mt-4 mb-0">Have already an account?
                        <a href="login.jsp" class="fw-bold text-body"><u style="color: #fff">Login here</u></a></p>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>
