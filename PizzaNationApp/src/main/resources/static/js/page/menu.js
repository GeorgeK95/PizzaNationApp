$(document).ready(function () {
    $(window).resize(function () {
        try_remove_padding();
        try_add_padding();
    });

    $(document).on('click', 'a.product_link', function () {
        let product_name = $('a.product_link').attr('id');
        open_product_popup(product_name)
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

    let prod_name_el = document.getElementById('product-name');
    prod_name_el.innerHTML = product_name;
}

function try_add_padding() {
    //removes padding if the screen is in mobile size range
    let currentWidth = $(window).width() + 17;
    let big_pad_element = document.getElementById('bigPad');

    if (big_pad_element !== null && currentWidth > 770) {
        big_pad_element.className += ' pad240';
    }
}

function try_remove_padding() {
    //removes padding if the screen is in mobile size range
    let currentWidth = $(window).width() + 17;
    let big_pad_element = document.getElementById('bigPad');

    if (big_pad_element !== null && currentWidth <= 770) {
        big_pad_element.className = ' ';
    }
}