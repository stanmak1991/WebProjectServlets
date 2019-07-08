<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подтверждение покупки</title>
</head>
<body>
<div style="text-align: center;">
    <h2>Введите одноразовый код</h2>
    <form action="buy" method="post">
        <input hidden type="text" name="goodId" value="${good_id}"/>
        <input type="password" title="Код" name="code"/>
        <input type="submit">
    </form>
</div>
</body>
</html>
