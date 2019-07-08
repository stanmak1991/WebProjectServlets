<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
<c:if test="${delete == true}">
    <p>Товар удален из корзины!</p>
</c:if>
<p>
<form action="goodsList" method="get">
    <button type="submit">На главную</button>
</form>
</p>
<table border='1' width='50%'>
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Цена</th>
        <th>Количество</th>
        <th>Общая цена</th>
        <th>Удалить из корзины</th>
    </tr>
    <c:forEach items="${allItems}" var="item">
        <c:forEach items="${allGoodsInCart}" var="good">
            <c:if test="${good.getId() == item.getGoodId()}">
                <tr>
                    <td align="center"><c:out value="${good.getId()}"/></td>
                    <td><c:out value="${good.getName()}"/></td>
                    <td><c:out value="${good.getDescription()}"/></td>
                    <td align="center"><c:out value="${good.getPrice()}"/></td>
                    <td align="center"><c:out value="${item.getQuantity()}"/></td>
                    <td align="center"><c:out value="${good.getPrice() * item.getQuantity()}"/></td>
                    <td align="center">
                        <form action="deleteFromCart" method="post">
                            <p><input type="hidden" name="id" value=<c:out value="${item.getId()}"/>></p>
                            <button>Удалить из корзины</button>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </c:forEach>
</table>
<p>
    Итого:
    <c:out value="${Double.parseDouble(toPay)}"/>
<form action="buy" method="get">
    <button>Оплатить</button>
</form>
</p>
</body>
</html>
