<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование товара</title>
</head>
<body>
<form action="updateGood" method="post">
    <p><input type="hidden" name="id" value=<c:out value="${id}"/>></p>
    <p>Название</p>
    <p><input type="text" name="name"/></p>
    <p>Цена</p>
    <p><input type="number" step="any" min="0" name="price"/></p>
    <p>Описание</p>
    <p><input type="text" name="description"/></p>
    <p>
        <button type="submit">Обновить</button>
    </p>
</form>
</body>
</html>
