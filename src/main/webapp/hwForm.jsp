<%--
  Created by IntelliJ IDEA.
  User: Laptop
  Date: 20.03.2021
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Homework</title>
</head>
<body>
    <form method="post" action="/hw">
        <input type="text" name="description" value="${description}">
        <input type="hidden" name="hwId" value="${hwId}">
        <input type="submit">
    </form>
</body>
</html>
