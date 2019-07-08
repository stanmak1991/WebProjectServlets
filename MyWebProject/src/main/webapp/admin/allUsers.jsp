<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Управление юзерами</title>
</head>
<body>
<form action="<c:url value="/admin"/>" method="post">
    <button type="submit">На главную</button>
</form>
<form action="<c:url value="/admin/allUsers/addNewUser"/>" method="get">
    <button type="submit">Добавить пользователя</button>
</form>
<c:if test="${registration == true}">
    <p>Пользователь добавлен!</p>
</c:if>
<c:if test="${update == true}">
    <p>Пользователь изменен!</p>
</c:if>
<c:if test="${delete == true}">
    <p>Пользователь удален!</p>
</c:if>
<table border='1' width='50%'>
    <tr>
        <th>ID</th>
        <th>Логин</th>
        <th>E-mail</th>
        <th>Роль</th>
        <th>Редактировать</th>
        <th>Удалить</th>
    </tr>
    <c:forEach items="${allUsers}" var="user">
        <tr>
            <td align="center"><c:out value="${user.getId()}"/></td>
            <td align="center"><c:out value="${user.getLogin()}"/></td>
            <td align="center"><c:out value="${user.getEmail()}"/></td>
            <td align="center"><c:out value="${user.getRole().getRole()}"/></td>
            <td align="center"><a
                    href="<c:url value="/admin/allUsers/updateUser?id=${user.getId()}"/>">Редактировать</a></td>
            <td align="center"><a href="<c:url value="/admin/allUsers/deleteUser?id=${user.getId()}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
