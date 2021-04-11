<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Teacher DB</title>
    <style>
        <%@ include file="styles.css"%>
    </style>
</head>
<body>

<div class="page_container">

    <h1 class="page_h1">Teacher database</h1>

    <h2>Add teachers information: </h2>
    <form action="teacherAdd">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" placeholder="Enter Name"><br>
        <label for="roll">Id:</label><br>
        <input type="text" id="roll" name="roll" placeholder="Enter ID"><br>
        <input type="submit">
    </form>

    <h2>Edit teachers information: </h2>
    <form action="teacherEdit">
        <label for="teacherID">Id:</label><br>
        <select id="teacherID" name="teacherID">
            <c:forEach items="${Teachers}" var="teacher">
                <option value="${teacher.id}">${teacher.id}</option>
            </c:forEach>
        </select><br>
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name"><br>

        <input type="submit">
    </form>

    <h2>Delete teachers information: </h2>
    <form action="teacherDelete">
        <label for="teacherID">Id:</label><br>
        <select id="teacherID" name="teacherID">
            <c:forEach items="${Teachers}" var="teacher">
                <option value="${teacher.id}">${teacher.id}</option>
            </c:forEach>
        </select><br>
        <input type="submit">
    </form>

    <h2>View all teachers</h2>
    <form action="viewAllTeachers">
        <input type="submit" value="View">
    </form>

</div>
</body>
</html>




