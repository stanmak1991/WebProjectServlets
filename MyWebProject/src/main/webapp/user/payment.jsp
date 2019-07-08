<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оплата</title>
</head>
<body>
<c:if test="${isPayed == true}">
    <p>Оплата прошла!</p>
</c:if>
<c:if test="${isPayed == false}">
    <p>Оплата отклонена</p>
</c:if>
<form action="goodsList" method="post">
    <button type="submit">На главную</button>
</form>
</body>
</html>