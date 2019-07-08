<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<div style="text-align: center;">
    <c:if test="${registration == true}">
        <p>Регистрация успешно завершена!</p>
    </c:if>
    <c:if test="${registration == false}">
        <p>Пользователь с данным логином уже зарегистрирован</p>
    </c:if>
    <c:if test="${login == false}">
        <p>Некорректный логин или пароль</p>
    </c:if>
    <c:if test="${delete == true}">
        <p>Пользователь удалён!</p>
    </c:if>
    <form action="login" method="post">
        <p>Логин</p>
        <p><input type="text" name="login"/></p>
        <p>Пароль</p>
        <p><input type="password" name="password"/></p>
        <p>
            <button type="submit">Войти</button>
        </p>
    </form>
    <form action="registration" method="get">
        <p>
            <button type="submit">Зарегистрироваться</button>
        </p>
    </form>
</div>
</body>
</html>
