<!DOCTYPE html>
<html>
<head>
    <title>BUY</title>
</head>
<body>
<h1>BUY</h1><hr>
<h4>Ваш заказ</h4>
<#list products as models>
    <div>
        <p><b>${models.title}</b> | <i>${models.price}</i> <a>Руб. в час</a></p>
    </div>
<#else >
    <h3>Товаров нет</h3>
</#list>
<hr>
<p><b>${Sum}</b> <a>Общяя сумма аренды</a></p>
<hr>
<form action="/oplata" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="ok"/>
</form>

</body>
</html>