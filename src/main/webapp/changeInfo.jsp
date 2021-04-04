<%--
  Created by IntelliJ IDEA.
  User: Laptop
  Date: 30.03.2021
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change ${sessionScope.currentUsr.u_name} Info</title>
</head>
<body>
<h2>Here you can change information about you</h2>
<h2>${errorMessage}</h2>
<form method="post" action="/changeinfo">
    <input type="hidden" name="id" value="${sessionScope.currentUsr.id}">
    <label><input type="text" name="name" value="${sessionScope.currentUsr.u_name}"> Name</label><br>
    <label><input type="text" name="email" value="${sessionScope.currentUsr.email}"> Email</label><br>
    <label><input type="text" name="phone" value="${sessionScope.currentUsr.phone_number}"> Phone number</label><br>
    <label><input type="text" name="groupId" value="${sessionScope.currentUsr.group_id}"> Group id</label><br>
    <label><select name="role">
        <option value="STUDENT">Student</option>
        <option value="TEACHER">Teacher</option>
    </select> Role</label><br>
    <label><input type="password" name="password"> Current Password</label><br>
    <label><input type="password" name="npassword"> New Password</label><br>
    <input type="submit">
</form>
</body>
</html>
