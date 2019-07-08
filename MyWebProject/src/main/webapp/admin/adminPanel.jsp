<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Панель администратора</title>
</head>
<body>
<p>Привет, <c:out value="${user.getLogin()}"/>(Администратор)!</p>
<form action="admin/allUsers" method="get">
    <button type="submit">Просмотр аккаунтов</button>
</form>
<form action="admin/allGoods" method="get">
    <button type="submit">Просмотр товаров</button>
</form>
<form action="LogOut" method="get">
    <button type="submit">Выход</button>
</form>
</body>
</html>
