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
    <title>Title</title>
</head>
<body>

</body>
</html>
