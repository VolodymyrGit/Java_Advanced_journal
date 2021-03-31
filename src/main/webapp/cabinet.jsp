<%--
  Created by IntelliJ IDEA.
  User: Laptop
  Date: 23.03.2021
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.currentUsr.u_name} Cabinet</title>
    <link rel="stylesheet" href="./css/cabinet.css">
</head>
<body>
    <div class="user-data">
        <h1>${sessionScope.currentUsr.u_name}</h1>
        <h1>Email - ${sessionScope.currentUsr.email}</h1>
        <h1>Phone Number - ${sessionScope.currentUsr.phone_number}</h1>
        <h1>Group Id - ${sessionScope.currentUsr.group_id}</h1>
        <h1>${sessionScope.currentUsr.role}</h1>
        <h1>Password ***</h1>
        <a href="/changeinfo"><button type="button">Change Info Data</button></a>
    </div>

    <div class="buttons">
        <a href="/table"><button type="button">Journal Table</button></a>

        <a href="/logout"><button type="button">Logout</button></a>
    </div>
</body>
</html>
