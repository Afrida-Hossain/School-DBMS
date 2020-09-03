<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
   <title>Student DB</title>
    <style>
        <%@ include file="styles.css"%>
    </style>
</head>
<body>
<div class = "page_container">

    <h1 class="page_h1">Student database</h1>
    <h2>Add a student: </h2>
    <form action="add">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name"><br>
        <label for="roll">Roll:</label><br>
        <input type="text" id="roll" name="roll"><br>
        <input type="submit">
    </form>

    <h2>Edit a student: </h2>
    <form action="edit">
        <label for="roll">Roll:</label><br>
        <%--adding courses directly from the database (Course table)--%>
        <select id="roll" name="roll">
            <c:forEach items="${StudentList}" var="student">
            <option value="${student.roll}">${student.roll}</option>
            </c:forEach>
        </select><br>
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name"><br>
        <input type="submit">
    </form>

    <h2>Delete a student: </h2>
    <form action="delete">
        <label for="roll">Roll:</label><br>
        <%--adding courses directly from the database (Course table)--%>
        <select id="roll" name="roll">
            <c:forEach items="${StudentList}" var="student">
            <option value="${student.roll}">${student.roll}</option>
            </c:forEach>
        </select><br>
        <input type="submit">
    </form>

    <h2>Add a course for a student</h2>
    <form action="addCourseStudent">
        <%--    <label for="id">Id:</label><br>
            <input type="text" id="id" name="id"><br>--%>
        <label for="name">Course Name:</label><br>
            <%--adding courses directly from the database (Course table)--%>
        <select id="name" name="name">
            <c:forEach items="${CourseName}" var="course">
                <option value="${course.courseName}">${course.courseName}</option>
            </c:forEach>
        </select><br>
        <label for="studentID">Student ID:</label><br>
        <input type="text" id="studentID" name="studentID"><br>
        <input type="submit">
    </form>

    <h2>View student information: </h2>
    <form action="viewStudent">
        <label for="roll">Roll:</label><br>
        <%--adding courses directly from the database (Course table)--%>
        <select id="roll" name="roll">
            <c:forEach items="${StudentList}" var="student">
            <option value="${student.roll}">${student.roll}</option>
            </c:forEach>
        </select><br>
        <input type="submit">
    </form>

    <h2>View all students</h2>
    <form action="viewAllStudents">
        <input type="submit" value="View">
    </form>
</div>

</body>
</html>