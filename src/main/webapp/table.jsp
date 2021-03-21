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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
          crossorigin="anonymous">
<body>
    <c:forEach items="${teachers}" var="t">
        <h1>${t.u_name} - ${t.email}</h1>
    </c:forEach>

    <table class="table table-bordered border-primary">
        <tr>
            <td> # </td>
            <c:forEach items="${lessons}" var="l">
                <td>${l.id}<br>${l.create_date}</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach items="${students}" var="st">
                <tr>
                    <td>${st.u_name}</td>
                    <c:forEach items="${homeworks.get(st.id)}" var="hw">
                        <c:if test="${!hw.hw_description.isEmpty()}">
                        <td><a href="/hw?hwId=${hw.id}&desc=${hw.hw_description}">${hw.hw_description}</a></td>
                        </c:if>

                        <c:if test="${hw.hw_description.isEmpty()}">
                            <td><a href="/hw?hwId=${hw.id}">add</a></td>
                        </c:if>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tr>
    </table>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
</body>
</html>
