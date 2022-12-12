<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <#--<title>BUY</title>-->
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">Buy</span>
    </div>
</nav>
<#--<h1>BUY </h1>-->
<hr>


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

<h4>Товары на аренду</h4>

<form action="/order/example" method="post">
    <input type="text" name="example" list="exampleList">
    <datalist id="exampleList">
        <option value="Ноутбук">
        <option value="Принтер">
        <option value="Кресло">
        <option value="Проектор">
        <option value="Экран">
        <option value="Кронштейн">
        <option value="Презентер">
        <option value="Сетевой удлинитель">
    </datalist>
    <input type="submit" value="Поиск"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>

<div class="card-columns" style="width: 30rem;">
    <#list product as models>
        <a href="/models/${models.ID}" class="product-link">
            <div class="card text-white bg-dark my-3 product">
                <img src="/images/${models.previewImageId}"
                     class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${models.title}</h5>
                    <p class="card-text">${models.price} ₽/час</p>
                </div>
            </div>
        </a>
    <#else>
        <h1 style="text-align: center">Не найдено</h1>
    </#list>

</div>
    <hr>
    <h4>Ваш список</h4>
    <#list list as models>
        <#if models.ID??>

            <div>
                <p><b>${models.title}</b> | <a href="/modelsOrder/${models.ID}">Подоробнее...</a></p>
            </div>

        </#if>
        <#else >
            <h3>Товаров нет</h3>
    </#list>
   <hr>
    <div>
        <p><a href="/order/apply/"> Заказать </a></p>
    </div>

</body>
</html>