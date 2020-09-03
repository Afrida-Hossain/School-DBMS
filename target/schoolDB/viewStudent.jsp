<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>View Student</title>
    <style>
        <%@ include file="styles.css"%>
    </style>
</head>
<body>
<div class="page_container">
    <h1>Student information</h1>
    <p>
        Name: ${name}<br>
        Roll: ${roll}<br>
        Number of courses: ${courseNo}<br>
        <li>
            <c:forEach items="${Courses}" var="course">
            <item value="${course.courseName}">${course.courseName}</item><br>
            </c:forEach>
        </li>
    </p>

</div>

</body>
</html>
