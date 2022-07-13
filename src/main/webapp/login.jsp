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
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div>
            <%
                    String login_msg = (String) request.getAttribute("error");
                    if (login_msg != null) {
                        out.println("<font color=red size=4px>" + login_msg + "</font>");
                    }
                }
            %>
        </div>
        <div class="card-header text-center">User Login</div>
        <div class="card-body">
            <form action="user-login" method="post">
                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" class="form-control" name="login-email" placeholder="Enter Your Email" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="login-password" placeholder="********" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>
