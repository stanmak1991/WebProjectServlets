<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список товаров</title>
</head>
<body>
<form action="<c:url value="/admin"/>" method="post">
    <button type="submit">На главную</button>
</form>
<form action="<c:url value="/admin/allUsers/addNewGood"/>" method="get">
    <button type="submit">Добавить товар</button>
</form>
<c:if test="${add == true}">
    <p>Товар добавлен!</p>
</c:if>
<c:if test="${update == true}">
    <p>Товар изменен!</p>
</c:if>
<c:if test="${delete == true}">
    <p>Товар удален!</p>
</c:if>
<table border='1' width='50%'>
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Цена</th>
        <th>Редактировать</th>
        <th>Удалить</th>
    </tr>
    <c:forEach items="${allGoods}" var="good">
    <tr>
        <td align="center"><c:out value="${good.getId()}"/></td>
        <td><c:out value="${good.getName()}"/></td>
        <td><c:out value="${good.getDescription()}"/></td>
        <td align="center"><c:out value="${good.getPrice()}"/></td>
        <td align="center"><a href="<c:url value="/admin/allGoods/updateGood?id=${good.getId()}"/>">Редактировать</a>
        </td>
        <td align="center"><a href="<c:url value="/admin/allGoods/deleteGood?id=${good.getId()}"/>">Удалить</a></td>
    </tr>
    </c:forEach>
</body>
</html>
