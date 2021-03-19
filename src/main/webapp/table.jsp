<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Laptop
  Date: 19.03.2021
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Table</title></head>
<body>
    <c:forEach items="${teachers}" var="t">
        <h1>${t.u_name} - ${t.email}</h1>
    </c:forEach>

    <table>
        <tr>
            <td></td>
            <c:forEach items="${lessons}" var="l">
                <td>${l.id}<br>${l.create_date}</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach items="${students}" var="st">
                <tr>
                    <td>${st.u_name}</td>
                    <c:forEach items="${homeworks.get(st.id)}" var="hv">
                        <td>${hv.hv_description}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tr>
    </table>
</body>
</html>
