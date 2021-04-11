<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form action="add"  modelAttribute="student">
        <form:label path="name" for="name">Name:</form:label><br>
        <form:input path="name"/><br>
        <form:errors cssStyle="color: red;" path="name"/><br>

        <form:label path="roll" for="roll">Roll:</form:label><br>
        <form:input path="roll"/><br>
        <form:errors cssStyle="color: red;" path="roll"/><br>

        <input type="submit">
    </form:form>

    <h2>Edit a student: </h2>
    <form:form action="edit" modelAttribute="student">
        <form:label path="roll" for="roll">Roll:</form:label><br>
        <%--adding courses directly from the database (Course table)--%>
        <form:select path="roll" id="roll" name="roll">
            <c:forEach items="${StudentList}" var="student">
            <option value="${student.roll}">${student.roll}</option>
            </c:forEach>
        </form:select><br>

        <form:label path="name" for="name">Name:</form:label><br>
        <form:input path="name" type="text" id="name" name="name"/><br>
        <input type="submit">
    </form:form>

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
            <%--adding courses directly from the database (Course table)--%>
            <select id="studentID" name="studentID">
                <c:forEach items="${StudentList}" var="student">
                    <option value="${student.roll}">${student.roll}</option>
                </c:forEach>
            </select><br>
        <input type="submit" value="Add course">
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