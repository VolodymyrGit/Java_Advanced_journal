<%--
  Created by IntelliJ IDEA.
  User: Laptop
  Date: 25.03.2021
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form method="post" action="/registration">
        <label><input type="text" name="name"> Name</label><br>
        <label><input type="text" name="email"> Email</label><br>
        <label><input type="text" name="phone"> Phone number</label><br>
        <label><input type="text" name="groupId"> Group id</label><br>
        <label><input type="password" name="password"> Password</label><br>
        <label><select name="role">
            <option value="STUDENT">Student</option>
            <option value="TEACHER">Teacher</option>
        </select> Role</label><br>
        <input type="submit">
    </form>
</body>
</html>
