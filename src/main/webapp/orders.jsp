<%@ page import="com.usermanagement.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null){
        request.setAttribute("auth", auth);
    }
%>

<html>
<head>
    <%@ include file="includes/header.jsp"%>
    <title>Orders</title>
</head>
<body>
<%@ include file="includes/navbar.jsp"%>
<%@ include file="includes/footer.jsp"%>
</body>
</html>
