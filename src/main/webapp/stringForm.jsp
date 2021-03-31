<%--
  Created by IntelliJ IDEA.
  User: Laptop
  Date: 30.03.2021
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change ${sessionScope.currentUsr.u_name} Info</title>

</head>
<body>
<form method="post" action="/changeinfo">
    <input type="text" name="description" value="${sessionScope.currentUsr.u_name}">
    <input type="hidden" name="user" value="${sessionScope.currentUsr}">
    <input type="submit">
</form>
</body>
</html>
