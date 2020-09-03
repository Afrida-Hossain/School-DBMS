<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%--<link href="<c:url value='styles.css' />" rel="stylesheet">--%>
    <title>SchoolDB</title>
    <%--<link href="<c:url value="/styles.css" />" rel="stylesheet" >--%>
        <style>
            <%@ include file="styles.css"%>
        </style>
</head>
<body>

<div class="homepage_container">

        <%--<a href="studentPage.jsp">Student database</a><br>--%>
        <form  action="loadStudentPage">
            <input class="index_options" type="submit" value="Student database">
        </form>

        <form  action="loadTeacherPage">
            <input class="index_options" type="submit" value="Teacher database">
        </form>

        <form  action="loadCoursePage">
            <input class="index_options" type="submit" value="Course database">
        </form>


</div>

</body>
</html>