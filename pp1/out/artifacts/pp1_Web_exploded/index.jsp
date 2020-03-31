<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Forma Registratsii</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<h1>Java Mentor!</h1>

<h2>БД пользователей</h2><br/>

<form method="POST" action="/add">
    <br>
    Login: <input type="text" name="name"> <br>
    <br>
    Password: <input type="text" name="email"> <br>
    <br>
    <button type="submit">Add user</button>
    <input type="hidden" name="trigger" value="1">
</form>


<table  border="1" >
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Email</td>
        <td>Action</td></tr>
    <c:set var="users" scope="request" value="${users}"/>
    <c:forEach items="${users}" var="user">
        <tr><td>${user.id}</td><td>${user.name}</td><td>${user.email}</td>
            <td>
                <form action="/delete" method="post">
                    <button name="id" value="${user.id}" type="submit" onclick='this.form.submit()'>Delete</button>
                </form>
                <form action="/edit" method="get">
                    <button name = "id" value = "${user.id}" type="submit" onclick='this.form.submit()'>Edit</button>
                </form>
            </td></tr>
    </c:forEach>
    <tr>
<%--        <td>${user.id}</td>--%>
<%--        <td>${user.name}</td>--%>
<%--        <td>${user.email}</td>--%>
<%--        <td>--%>
<%--            <form action="/delete" method="get">--%>
<%--&lt;%&ndash;                <button name="name" value="${user.id}" type="submit" onclick='this.form.submit()'>Delete</button>&ndash;%&gt;--%>
<%--    </form>--%>
<%--    <form action="/edit" method=post">--%>
<%--&lt;%&ndash;        <button name = "name" value = "${user.id}" type="submit" onclick='this.form.submit()'>Edit</button>&ndash;%&gt;--%>
<%--    </form>--%>
<%--    </td>--%>
    </tr>

<%--    </c:forEach>--%>
</table>


</body>
</html>
