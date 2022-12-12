<!DOCTYPE html>
<html>
<head>
    <title>BUY</title>
</head>
<body>
<h1>BUY </h1><hr>

<#if user.email?? >
    <h3>Имя пользователя <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
<#else >
    <a href="/login"> Войти</a> </h1>
    <hr>
</#if>

<h4>Ваши заказы</h4>
<#list orderProducts as Order>

    <div>
        <p><b>Заказ номер: ${Order.num}</b> | <a href ="/order/${Order.ID}">Подоробнее...</a></p>
    </div>

<#else >
    <h3>Заказов нет</h3>
</#list>
<hr>

<form action ="/order/create" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Заказать"/>
</form>

</body>
</html>