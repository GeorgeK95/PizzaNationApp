$(document).ready(function () {
    $(window).resize(function () {
        try_remove_padding();
        try_add_padding();
    });

    $(document)
        .on('click', 'a.select_btn', function () {
            addProductAjax($(this).attr('id'));
        })
        .on('click', 'a.add_to_cart_btn', function () {
            addProductAjax($(this).attr('id'));
        })

    $(document).on('click', 'a.product_link', function () {
        let productName = $(this).attr('id');

        $.get("/productDetails", productName)
            .done(function (productDetailsJson) {
                open_product_popup(productDetailsJson)
            })
            .fail(function () {
                console.log('Failed.');
            });
    })

    $(document).on('click', '#close_span', function () {
        close_product_popup()
    })

    window.onclick = function (event) {
        let popup_div = document.getElementById('popup_window');
        if (event.target === popup_div) {
            popup_div.style.display = "none";
        }
    }
});

function addProductAjax(productName) {
    let name = {"productName": productName.trim()};

    $.post("/cart/addProduct", name)
        .done(function (message) {
            console.log(message);
        })
        .fail(function () {
            console.log('Failed.');
        });

}

function close_product_popup() {
    let popup_div = document.getElementById('popup_window');
    popup_div.style.display = "none";
}

function open_product_popup(productDetailsJson) {
    // Get the modal
    let popup_div = document.getElementById('popup_window');
    popup_div.style.display = "block";

    //name
    let prod_name_el = document.getElementById('product-name');
    prod_name_el.innerHTML = productDetailsJson.name;

    //details
    let prod_details_el = document.getElementById('product-details');
    prod_details_el.innerHTML = productDetailsJson.details;

    createAndSetIngredientsParagraphs(productDetailsJson);

    let img_element = document.getElementById('popup_img');
    img_element.setAttribute("src", productDetailsJson.image.url);

    let add_to_cart_btn_element = document.getElementsByClassName('add_to_cart_btn');
    add_to_cart_btn_element[0].setAttribute("id", productDetailsJson.name);
}

function createAndSetIngredientsParagraphs(productDetailsJson) {
    let element = document.getElementById("ingredientsList");

    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }

    for (let i = 0; i < productDetailsJson.ingredients.length; i++) {
        let ingredientsStr = "";

        let current = productDetailsJson.ingredients[i];
        ingredientsStr += current.name + " ";
        ingredientsStr += current.quantity + " ";
        ingredientsStr += current.unit.toLowerCase();

        let para = document.createElement("p");
        para.setAttribute("id", "product-ingredients")
        let node = document.createTextNode(ingredientsStr);
        para.appendChild(node);

        let element = document.getElementById("ingredientsList");
        element.appendChild(para);
    }

}

function try_add_padding() {
    //removes padding if the screen is in mobile size range
    let currentWidth = $(window).width() + 17;
    let big_pad_element = document.getElementById("bigPad");

    let bigPadClassName = big_pad_element.className;

    if (big_pad_element !== null && currentWidth > 770 && !bigPadClassName.includes('pad240')) {
        big_pad_element.className += ' pad240';
    }

    let pad_200_element = document.getElementById('smallPad');
    let smallPadClassName;
    if (pad_200_element !== null)
        smallPadClassName = pad_200_element.className;

    if (pad_200_element !== null && currentWidth > 770 && smallPadClassName !== null && !smallPadClassName.includes('pad200')) {
        pad_200_element.className += ' pad200';
    }
}

function try_remove_padding() {
    //removes padding if the screen is in mobile size range
    let currentWidth = $(window).width() + 17;
    let big_pad_element = document.getElementById('bigPad');

    if (big_pad_element !== null && currentWidth <= 770) {
        big_pad_element.className = ' ';
    }

    let pad_200_element = document.getElementById('smallPad');
    if (pad_200_element != null && currentWidth <= 770) {
        pad_200_element.className = ' ';
    }
}