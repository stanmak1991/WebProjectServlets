<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление пользователя</title>
</head>
<body>
<c:if test="${registration == false}">
    <p>Пользователь с данным логином уже зарегистрирован</p>
</c:if>
<form action="addNewUser" method="post">
    <p>Логин</p>
    <p><input type="text" name="login"/></p>
    <p>Пароль</p>
    <p><input type="password" name="password"/></p>
    <p>E-mail</p>
    <p><input type="text" name="email"/></p>
    <p>Роль</p>
    <p>
        <select name="role">
            <option>ADMIN</option>
            <option>USER</option>
        </select>
    </p>
    <p>
        <button type="submit">Добавить</button>
    </p>
</form>
</body>
</html>