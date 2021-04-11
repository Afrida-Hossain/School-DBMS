<%--
  Created by IntelliJ IDEA.
  User: afridahossain
  Date: 9/6/20
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Failed</title>
    <style>
        <%@ include file="styles.css"%>
    </style>
</head>
<body class="page_container">
<h2>Your operation was not successful. <br>Please go back and try again.<br> Here are some of the reasons your operation might have failed.</h2>

<li class="error">
    <%--use messageSource for this?--%>
    <item>You might have added duplicate ${primaryKey}.</item>
    <item>You might not have provided ${primaryKey}.</item>

</li>
</body>
</html>
