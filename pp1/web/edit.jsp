<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
</head>
<body>
<h3>Edit User</h3>
<form action="/edit" method="post">
    <input type="hidden" value="${user.id}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${user.name}" /><br><br>
    <label>Price</label><br>
    <input name="email" value="${user.email}" type="number" min="100" /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>
