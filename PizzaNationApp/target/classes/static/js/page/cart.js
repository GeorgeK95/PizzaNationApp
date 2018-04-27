$(document).ready(function () {
    $(document).on('click', 'a.remove_product_btn', function () {
        let element_id = $(this).attr('id');

        removeProductAjax(element_id);

        document.getElementById(element_id).remove();
    })
})

function removeProductAjax(productName) {
    let name = {"productName": productName.trim()};

    $.post("/cart/removeProduct", name)
        .done(function (message) {
            updateProductsCount(message.products.length);
            updateCartPagePrices(message.subTotalPrice, message.totalPriceWithDelivery);
        })
        .fail(function () {
            console.log('Failed.');
        });

}

function updateCartPagePrices(subTotal, total) {
    let subtotalElement = document.getElementsByClassName('subtotal_price_holder');
    let totalPriceElement = document.getElementsByClassName('total_price_holder');

    for (let i = 0; i < subtotalElement.length; i++) {
        let current = subtotalElement[i];
        current.innerHTML = "£" + parseFloat(Math.round(subTotal * 100) / 100).toFixed(2);
    }

    for (let i = 0; i < totalPriceElement.length; i++) {
        let current = totalPriceElement[i];
        current.innerHTML = "£" + parseFloat(Math.round(total * 100) / 100).toFixed(2);
    }
}

/*

function updateProductsCount() {
    $.get("/cart/cartSize")
        .done(function (productsCount) {
            // console.log(productsCount);
            let cartSizeElements = document.getElementsByClassName('cart_item_count');
            // console.log(cartSizeElements);
            setCartSizeElementsInnerHtml(cartSizeElements, productsCount);
        })
        .fail(function () {
            console.log('Failed.');
        });
}

function setCartSizeElementsInnerHtml(cartSizeElements, productsCount) {
    for (let i = 0; i < cartSizeElements.length; i++) {
        let current = cartSizeElements[i];
        current.innerHTML = productsCount;
    }
}*/
