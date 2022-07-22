<%@ page import="com.usermanagement.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="includes/needed-code.jsp"%>

<%
    if (auth != null) {
        response.sendRedirect("index.jsp");
    } else {
%>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Login</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar-principal.jsp" %>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <%
            String login_msg = (String) request.getAttribute("loginError");
            String register_msg = (String) request.getAttribute("registerSuccess");
            if (login_msg != null) {%>
            <div class="card-header">
            <%out.println("<font color=red size=4px>" + login_msg + "</font>");%>
            </div>
			<%} else if (register_msg != null) {%>
                <div class="card-header">
                    <%out.println("<font size=4px>" + register_msg + "</font>");%>
                </div>
                <%}
            }
        %>
        <div class="card-header text-center">User Login</div>
        <div class="card-body">
            <form class="row g-3" action="user-login" method="post">
                <div class="col-12">
                    <label class="form-label">Email Address</label>
                    <input type="email" class="form-control" name="login-email" placeholder="Enter Your Email" required>
                </div>
                <div class="col-12">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="login-password" placeholder="********" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="mt-3 btn btn-primary btn-lg">Login</button>
                </div>
                <p class="text-center text-muted mt-4 mb-0">Don't have an account?
                    <a href="register.jsp" class="fw-bold text-body"><u style="color: #fff">Sign up now</u></a></p>
            </form>
        </div>
    </div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>