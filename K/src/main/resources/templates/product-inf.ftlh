<!DOCTYPE html>
<html>
<head>
    <title>BUY</title>
</head>
<body>
<h1>BUY</h1><hr>
<h4>Информация о конкреном товаре</h4>

<#list images as img>
    <div>
        <img src="/images/${img.getID()}" height="30px"/><br><br>
    </div>
<#else >
    <h3>Фотографий нет</h3>
</#list>

<b>Наименование товара </b>${models.title}<br>
<b>Цена товара </b>${models.price}<br>
<b>Описание товара </b>${models.description}<br>
<b>производитель </b>${models.author}<br>
<hr>
<form action ="/order/createList/${models.ID}" method="post">
    <input type="datetime-local" name="times">
    <input type="datetime-local" name="times2">
    <input type="submit" value="Добавить товар"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
</body>
</html>