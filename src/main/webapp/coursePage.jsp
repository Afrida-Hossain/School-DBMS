<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Course DB</title>
    <style>
        <%@ include file="styles.css"%>
    </style>
</head>
<body>
<div class="page_container">
    <h1 class="page_h1">Course information</h1>

    <%--add new course:--%>
    <h2>Add a new course:</h2>
    <form action="addCourse">
        <label for="courseName">Course Name:</label><br>
        <input type="text" id="courseName" name="courseName"><br>
        <input type="submit">
    </form>

    <%--add new course:--%>
    <h2>Remove an existing course:</h2>
    <form action="deleteCourse">
        <label for="courseName">Course Name:</label><br>
        <%--adding courses directly from the database (Course table)--%>
        <select id="courseName" name="courseName">
            <c:forEach items="${CourseName}" var="course">
                <option value="${course.courseName}">${course.courseName}</option>
            </c:forEach>
        </select><br>
        <input type="submit">
    </form>

    <%--assign teacher to each course:--%>
    <h2>Change course teacher:</h2>
    <form action="addCourseTeacher">
        <label for="name">Course Name:</label><br>
        <%--adding courses directly from the database (Course table)--%>
        <select id="name" name="name">
            <c:forEach items="${CourseName}" var="course">
                <option value="${course.courseName}">${course.courseName}</option>
            </c:forEach>
        </select><br>
        <label for="teacherID">Teacher ID:</label><br>
        <%--adding courses directly from the database (Course table)--%>
        <select id="teacherID" name="teacherID">
            <c:forEach items="${Teachers}" var="teacher">
                <option value="${teacher.id}">${teacher.id}</option>
            </c:forEach>
        </select><br>
        <input type="submit">
    </form>

    <h2>Remove course teacher:</h2>
    <form action="removeCourseTeacher">
        <label for="name">Course Name:</label><br>
        <%--adding courses directly from the database (Course table)--%>
        <select id="name" name="name">
            <c:forEach items="${CourseWithTeacher}" var="course">
                <option value="${course.courseName}">${course.courseName}</option>
            </c:forEach>
        </select><br>
        <input type="submit">
    </form>

    <h2>View all courses</h2>
    <form action="viewAllCourses">
        <input type="submit" value="View">
    </form>

</div>

</body>
</html>
