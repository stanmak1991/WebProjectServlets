<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление товара</title>
</head>
<body>
<c:if test="${add == false}">
    <p>Такой товар уже есть в базе</p>
</c:if>
<form action="addNewGood" method="post">
    <p>Название</p>
    <p><input type="text" name="name"/></p>
    <p>Цена</p>
    <p><input type="number" step="any" min="0" name="price"/></p>
    <p>Описание</p>
    <p><input type="text" name="description"/></p>
    <p>
        <button type="submit">Добавить</button>
    </p>
</form>
</body>
</html>
