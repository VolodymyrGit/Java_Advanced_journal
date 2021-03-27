<%--
  Created by IntelliJ IDEA.
  User: Laptop
  Date: 26.03.2021
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3>${errorMessage}</h3>
    <form method="post" action="/login">
        <label><input name="login" type="text" value="${previousLogin}"> Email</label><br>
        <label><input name="password" type="password"> Password</label><br>
        <input type="submit">
    </form>
</body>
</html>
