$(document).ready(function () {
    $(window).resize(function () {
        try_remove_padding();
        try_add_padding();
    });

    $(document).on('click', 'a.product_link', function () {
        // let product_name = $('a.product_link')/*.attr('value');*/
        let val = document.getElementsByClassName('product_link');
        // let alligator = val.slice(0, 6);

        console.log(val[6].getAttribute('id'));

        /*$.get("/productDetails", product_name)
            .done(function (productDetailsJson) {
                console.log('done from js');
                console.log(productDetailsJson);
                open_product_popup(product_name)
            })
            .fail(function () {
                console.log('failed from js');
            });*/

    })

    $(document).on('click', '#close_span', function () {
        close_product_popup()
    })

    window.onclick = function (event) {
        let popup_div = document.getElementById('popup_window');
        if (event.target == popup_div) {
            popup_div.style.display = "none";
        }
    }
});

function close_product_popup() {
    let popup_div = document.getElementById('popup_window');
    popup_div.style.display = "none";
}

function open_product_popup(product_name) {
    // Get the modal
    let popup_div = document.getElementById('popup_window');
    popup_div.style.display = "block";

    //name
    let prod_name_el = document.getElementById('product-name');
    prod_name_el.innerHTML = product_name;

    //details
    let prod_details_el = document.getElementById('product-details');
    // prod_details_el.innerHTML = product_details;
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
    let smallPadClassName = pad_200_element.className;

    if (pad_200_element != null && currentWidth > 770 && !smallPadClassName.includes('pad200')) {
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