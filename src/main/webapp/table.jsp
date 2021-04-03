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
    <title>Journal Table</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
          crossorigin="anonymous">
    <link rel="stylesheet" href="./css/table.css">
</head>
<body>
    <c:forEach items="${teachers}" var="t">
        <h1>${t.getUserName()} - ${t.getEmail()}</h1>
    </c:forEach>

    <table class="table table-bordered border-primary">

        <tr>
            <td> Students </td>

            <c:forEach items="${lessons}" var="l">
                <td>${l.getId()}<br>${l.getLessonDate()}</td>
            </c:forEach>

            <c:if test="${sessionScope.currentUser.role.name().equals('TEACHER')}">
                <td>
                    <a href="/add-lesson">Add Lesson</a>
                </td>
            </c:if>
        </tr>

        <tr>
            <c:forEach items="${students}" var="st">
                <tr>
                    <td>${st.getUserName()}</td>

                    <c:forEach items="${homeworks.get(st.getId())}" var="hw">

                        <c:if test="${!hw.getHwDescription().isEmpty()}">

                            <td <c:if test="${hw.isDone()}"> style="background-color: green" </c:if>

                                <c:if test="${!hw.isDone()}"> style="background-color: yellow" </c:if>
                            >
                                <c:if test="${sessionScope.currentUser.id == st.getId()}">
                                    <a href="/hw?hwId=${hw.getId()}">
                                </c:if>
                                    ${hw.getHwDescription()}</a>
                            </td>
                        </c:if>

                        <c:if test="${hw.getHwDescription().isEmpty()}">
                            <td>
                                <c:if test="${sessionScope.currentUser.id == st.getId()}">
                                    <a href="/hw?hwId=${hw.getId()}">add</a>
                                </c:if>
                            </td>
                        </c:if>

                    </c:forEach>
                </tr>
            </c:forEach>
        </tr>
    </table>

    <div>
        <a href="/cabinet"><button type="button">Cabinet</button></a>

        <a href="/logout"><button type="button">Logout</button></a>
    </div>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
</body>
</html>
