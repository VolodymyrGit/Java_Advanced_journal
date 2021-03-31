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
    <link rel="stylesheet" href="./css/registration.css">
</head>
<body>
    <form method="post" action="/registration">
        <label><input type="text" name="name" required> Name</label><br>
        <label><input type="text" name="email" required> Email</label><br>
        <label><input type="text" name="phone" required> Phone number</label><br>
        <label><input type="text" name="groupId" required> Group id</label><br>
        <label><input type="password" name="password" required> Password</label><br>
        <label><select name="role" required>
            <option value="STUDENT">Student</option>
            <option value="TEACHER">Teacher</option>
        </select> Role</label><br>
        <input type="submit">
    </form>

    <div>
        <a href="/login"><button type="button">Login</button></a>
    </div>
</body>
</html>
