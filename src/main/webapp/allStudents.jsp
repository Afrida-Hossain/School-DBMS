<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>All ${Placeholder}</title>
    <style>
        <%@ include file="styles.css"%>
    </style>
</head>
<body class="page_container">
<table>
    <caption><h2>List of ${Placeholder}</h2></caption>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="student" items="${AllStudents}">
        <tr>
            <td><c:out value="${student.roll}" /></td>
            <td><c:out value="${student.name}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
