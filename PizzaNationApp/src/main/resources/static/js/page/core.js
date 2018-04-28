// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
    scrollFunction()
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("gotoTopBtn").style.display = "block";
    } else {
        document.getElementById("gotoTopBtn").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

function activate_hamburger_menu() {
    // Top navigation
    let a = $("div#mobnav").attr("style");
    // console.log(a)
    if (a === "display:block;") {
        $("div#mobnav").attr("style", "display:none;");
    } else {
        $("div#mobnav").attr("style", "display:block;");
    }
}


$(document).ready(function () {
    $.get("/cart/cartSize", function (cartSize) {
        updateProductsCount(cartSize);
    })

    $(document)
        .on('click', 'a.select_btn', function () {
            addProductAjax($(this).attr('id'));
        })
        .on('click', 'a.add_to_cart_btn', function () {
            addProductAjax($(this).attr('id'));
        })
})

function addProductAjax(productName) {
    let name = {"productName": productName.trim()};

    $.post("/cart/addProduct", name)
        .done(function (message) {
            updateProductsCount(message.cartSize);
        })
        .fail(function () {
            console.log('Failed.');
        });

}

function updateProductsCount(productsCount) {
    if (productsCount === 0) removePaddedTable();
    $.get("/cart/cartSize", function () {
        let cartSizeElements = document.getElementsByClassName('cart_item_count');
        setCartSizeElementsInnerHtml(cartSizeElements, productsCount);
    })
        .fail(function () {
            console.log('Failed.');
        });
}

function setCartSizeElementsInnerHtml(cartSizeElements, productsCount) {
    for (let current of cartSizeElements) {
        current.innerHTML = productsCount;
    }
}