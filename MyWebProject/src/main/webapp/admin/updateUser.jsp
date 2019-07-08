<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактировать данные</title>
</head>
<body>
<form action="updateUser" method="post">
    <p><input type="hidden" name="id" value=<c:out value="${id}"/>></p>
    <p>E-mail</p>
    <p><input type="email" name="email"/></p>
    <p>Пароль</p>
    <p><input type="password" name="password"/></p>
    <p>
        <select name="role">
            <option>ADMIN</option>
            <option>USER</option>
        </select>
    </p>
    <p>
        <button type="submit">Обновить</button>
    </p>
</form>
</body>
</html>
