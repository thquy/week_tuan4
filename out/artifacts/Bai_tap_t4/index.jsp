<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
<img src="images/murach.jpg" alt="Murach Logo" style="width:120px;">
<h2>Survey</h2>

<!-- Thông báo lỗi -->
<p style="color:red;">${message}</p>

<form action="emailList" method="post">
    <input type="hidden" name="action" value="add">

    <h3>Your information:</h3>
    <label>First Name</label>
    <input type="text" name="firstName" value="${param.firstName}" required><br>

    <label>Last Name</label>
    <input type="text" name="lastName" value="${param.lastName}" required><br>

    <label>Email</label>
    <input type="email" name="email" value="${param.email}" required><br>

    <input type="submit" value="Submit" id="submit">
</form>
</body>
</html>
