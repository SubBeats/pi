<!DOCTYPE html>
<html>
<head>
    <title>BUY</title>
</head>
<body>
<h1>BUY</h1>
<hr>
<h4>Информация о заказе</h4>

<div class="card-columns" style="width: 18rem;">
    <#list Lists as models>
        <a href="/models/${models.ID}" class="product-link">
            <div class="card text-white bg-dark my-3 product">
                <img src="/images/${models.previewImageId}"
                     class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${models.title}</h5> <p class="card-text">${models.price} ₽/час</p>
                </div>
            </div>
        </a>
    <#else>
        <h1 style="text-align: center">Не найдено</h1>
    </#list>
</div>
<h4>Общяя сумма заказа ${Sums}</h4>
</body>
</html>
