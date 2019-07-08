<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная</title>
</head>
<body>
<p>
<form action="LogOut" method="get">
    Привет, <c:out value="${user.getLogin()}"/>!
    <button type="submit">Выход</button>
</form>
</p>
<c:if test="${goodAdded == true}">
    <p>Товар добавлен в корзину!</p>
</c:if>
<form action="cart" method="get">
    <button type="submit">Корзина</button>
</form>
<table border='1' width='50%'>
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Цена</th>
        <th>Купить</th>
    </tr>
    <c:forEach items="${allGoods}" var="good">
        <tr>
            <td align="center"><c:out value="${good.getId()}"/></td>
            <td><c:out value="${good.getName()}"/></td>
            <td><c:out value="${good.getDescription()}"/></td>
            <td align="center"><c:out value="${good.getPrice()}"/></td>
            <td align="center">
                <form action="addToCart" method="post">
                    <p><input type="hidden" name="id" value=<c:out value="${good.id}"/>></p>
                    <button>Добавить в корзину</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>