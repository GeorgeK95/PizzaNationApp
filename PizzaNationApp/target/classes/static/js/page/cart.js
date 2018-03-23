/*
/!* Checkout | Author: Mark Willis *!/
var max_toppings = 12;
var max_quantity = 15;

function update_max_toppings_checkout(new_value) {
    max_toppings = new_value;
}

function update_max_quantity_checkout(new_value) {
    max_quantity = new_value;
}

var total_quantity = ''; // Kind of like a hash...
function set_total_quantity(new_value) {
    total_quantity += String(new_value);
}

/!*
	Pizza GOGO
	Global functions
*!/

/!* Mobile nav *!/
function bind_mobile_nav() {
    // Top navigation
    $('a#mobile_nav_button').bind('click', function () {
        if ($('div#mobnav').is(':visible')) {
            $('div#mobnav').slideUp(50);
        }
        else {
            // Scroll to top
            $('html, body').animate({
                scrollTop: 0
            }, 0);
            $('div#mobnav').slideDown(100);
        }
        return false;
    });
}

// Returns array of viewport dimensions
function viewport_size() {
    var array = new Array();
    array['x'] = $(window).width();
    array['y'] = $(window).height();
    return array;
}

// Change page, based on root
function change_page(url) {
    window.location.href = docRoot + url;
    return false;
}

// Provide absolute url to navigate to
function change_page_absolute(url) {
    window.location.href = url;
    return false;
}

function mask_ui(mask) {
    // Mask
    if (mask == 1) {
        var doc_size = viewport_size();
        $('div#ui_mask')
            .height(doc_size['y'])
            .width(doc_size['x'])
            .show();
    }
    // Unmask
    else {
        $('div#ui_mask').hide();
    }
}

getViewport = function () {
    var $w = $(window);
    return {
        l: $w.scrollLeft(),
        t: $w.scrollTop(),
        w: $w.width(),
        h: $w.height()
    }
}

// Element to focus:string - 'div#example' | unhide_element: true/false, fadein_speed:int
function position_this(element_focus, unhide_element, fadein_speed) {
    // Position
    var doc_size = viewport_size();
    var x = Math.round((doc_size['x'] / 2) - ($(element_focus).width() / 2));
    var y = Math.round((doc_size['y'] / 2) - ($(element_focus).height() / 2));

    // Fix for off screen
    y = y < 0 ? 0 : y;

    $(element_focus)
        .offset({top: y, left: x})
        .hide()
        .removeClass('hide_entry');
    if (unhide_element) {
        $(element_focus)
            .fadeIn(fadein_speed);
    }
}

// Open an alert
function ui_alert(text) {
    ui_alert_close();
    //mask_main_page(1);
    var html = '<div id="ui_alert"><div id="ui_alert_inner">';
    html += '<p>' + text + '</p>';
    html += '<center>';
    html += '<a href="#" id="alert_cancel" onclick="return ui_alert_close();" class="button_flat button_flat_green">Ok, close</a>';
    html += '</center>';
    html += '</div></div>';
    $('div#main_page').prepend(html);
    position_this('div#ui_alert', true, 0);
}

function ui_alert_close() {
    $('div#ui_alert').remove();
    //mask_main_page(0);
    return false;
}


/!* ~ *!/

// Postcode
function postcode_init() {
    var thisval = $('input.postcode_input').val();
    if (thisval != '') {
        $('input.postcode_input').addClass('postcode_uppercase');
    }

    $('input.postcode_input').on('keyup', function () {
        var thisval = $(this).val();
        if (thisval != '') {
            $('input.postcode_input').addClass('postcode_uppercase');
        }
        else {
            $('input.postcode_input').removeClass('postcode_uppercase');
        }
    });
}

// Branch page
function branch_init() {
    if ($('div#show_other_branches a#showmore').length > 0) {
        $('div#show_other_branches a#showmore').bind('click', function () {
            $('div#show_other_branches').hide();
            $('div#other_branches').slideDown('slow');
        });
    }

    $('a#change_postcode').bind('click', function () {
        $('div#branch_select').hide();
        $('div#postcode_box').show();
        return false;
    });
}

// Mobile navigation
function mobile_nav_init() {
    $('a#menu_nav').bind('click', function () {
        $('div#main_nav_button').toggle();
        return false;
    });
}

// Interactive
function nearby_stores(store_id) {
    var store_id = parseInt(store_id);
    $.ajax({
        url: docRoot + 'ajax/?do=nearby_stores&store_id=' + escape(store_id),
        success: function (data) {
            if (data != '') {
                $('div#nearby_stores').html(data);
            }
            else {
                $('div#nearby_stores').html('Error finding nearby stores.');
            }
        }
    });
}

// Stores
function trigger_store_map(location_title, postcode, map_lat, map_long, map_x, map_y, map_z) {
    // Requires location_map.js
    location_map('Pizza GoGo ' + location_title, postcode, map_lat, map_long, map_x, map_y, map_z);
}

// Update mini cart (sidebar)
function update_cart() {
    // Get details
    $.ajax({
        url: docRoot + 'ajax/?do=cart_details',
        dataType: 'json',
        success: function (data) {
            // Update mini cart (sidebar)
            var item_word = parseInt(data.item_count) == 1 ? 'item' : 'items';
            //$('div#cartpreview_1').html('<p>' + parseInt(data.item_count) + ' '+item_word+' - &pound;' + escape(data.price.toFixed(2)) + '</p>');
            // Hide mask (sidebar)
            //$('div#mask_cartarea_1').hide();
            // Update mini cart (mobile nav)
            //$('span#cartpreview_2').html(parseInt(data.item_count));

            $('span.cart_item_count').html(parseInt(data.item_count));
            // Add checkout_active
            if (data.item_count > 0) {
                $('a.cart').addClass('checkout_active');
            }
            else {
                $('a.cart').removeClass('checkout_active');
            }


        }
    });
}

function mask_cart() {
    $('div#mask_cartarea_1').width($('div#cartarea_1').width()).height($('div#cartarea_1').height()).show();
}

/!* Log reg page *!/
function logreg_log() {
    $('div#logreg').hide();
    $('div#page_tabs').show();
    $('a#tablink_1').addClass('active');
    $('div#tabpanel_1').fadeIn();
    return false;
}

function logreg_reg() {
    $('div#logreg').hide();
    $('div#page_tabs').show();
    $('a#tablink_2').addClass('active');
    $('div#tabpanel_2').fadeIn();
    return false;
}

function facebook_link_init() {
    // Nothing at the moment
}

function load_video_in(source, target) {
    var video_webm = $(source).data('videowebm');
    var video_mp4 = $(source).data('videomp4');

    var markup = '<video width="100%" height="auto" controls autoplay loop>';
    markup += '<source src="' + video_mp4 + '" type="video/mp4">';
    markup += '<source src="' + video_webm + '" type="video/webm">';
    markup += 'Sorry. Your browser does not support the video tag.';
    markup += '</video>';

    $(target).html(markup);
    return false;
}

function bind_quantity_update() {
    $('input.quantity_input').live('blur', function () {
        var thisval = $(this).val();
        if (thisval < 0) {
            $(this).val(1);
        }
        if (thisval > max_quantity) {
            $(this).val(max_quantity);
        }

        var totalcount = ''; // Temp hash
        // Count all
        // QIF_FIX - don't need this any more
        /!*
        $('input.quantity_input').each(function() {
            totalcount += String($(this).val());
        });

        if(totalcount != total_quantity) {
            $('div#quantity_update_button').fadeIn();
            $('input#quantity_update').addClass('button_flat_red');
        }
        else{
            $('div#quantity_update_button').fadeOut();
            $('input#quantity_update').removeClass('button_flat_red');
        }
        *!/
    });

    // QIF_FIX - Update - show button straight away
    $('input.quantity_input').live('focus', function () {
        $('div#quantity_update_button').fadeIn();
        $('input#quantity_update').addClass('button_flat_red');
    });

    $('a.cart_remove').live('click', function () {
        remove_cart_item($(this));
        return false;
    });

    $('input#quantity_update').live('click', function () {
        cartmask();
        return true;
    });
}


/!***********************************************************************************!/
function cartmask() {
    $('div#cartmask')
        .width($('div#hold_cart').width())
        .height($('div#hold_cart').height())
        .show();
}

function remove_cart_item(element_item) {
    var thisid = element_item.attr('id').split('_');
    var itemkey = thisid[1];
    if (thisid[0] == 'special') {
        var special = 1;
    }
    else {
        var special = 0;
    }

    // Mask cart
    cartmask();
    // Send request
    $.ajax({
        url: docRoot + 'ajax/?do=remove_from_cart&itemkey=' + escape(parseInt(itemkey)) + '&special=' + escape(parseInt(special)),
        success: function (data) {
            // Reload cart area (ajax)
            reload_cart();
            //location.reload();
        }
    });
}

function reload_cart() {
    $.ajax({
        url: docRoot + 'ajax/?do=reload_cart',
        success: function (data) {
            // Load in html to DOM
            $('div#the_cart').html(data);
            // Remove mask
            $('div#cartmask').hide();
            // Update mini cart
            update_cart();
        }
    });
}

/!*
function bind_payment_selection() {
	$('input.payment_method').bind('click', function() {
		var thisval = $(this).val();
		// Hide both 
		$('div.payment_continue').hide();
		// Unhide relevant
		$('div#continue_'+parseInt(thisval)).show();
	});
}
*!/
function change_payment_buttons(set_id) {
    if (set_id > 2) {
        set_id = 3;
    }

    // Hide all
    $('div.payment_continue').hide();
    // Unhide relevant
    $('div#continue_' + parseInt(set_id)).show();
}

function verify_bind() {
    $('input#smsgo').bind('click', function () {
        send_sms_code();
        return false;
    });
    $('input#codego').bind('click', function () {
        verify_code();
        return false;
    });
    $('a#send_another').bind('click', function () {
        $('div#confirm_2').hide();
        $('div#confirm_1').show();
        return false;
    });
    $('a#ihavecode').bind('click', function () {
        $('div#confirm_1').hide();
        $('div#confirm_2').show();
        return false;
    });
}

function send_sms_code() {
    // Mask send area
    $('div#verify_mask')
        .width($('div#verify_area').width())
        .height($('div#verify_area').height())
        .show();

    var send_data = $('form#mobilesms').serialize();
    $.ajax({
        url: docRoot + 'ajax/?do=send_verify_sms',
        type: 'post',
        data: send_data,
        dataType: 'json',
        success: function (data) {
            if (data.success == 1) {
                // Reveal second area
                $('div#confirm_1').hide();
                $('div#confirm_2').show();
                // Remove any error with success
                $('div#report').html('<div class="successbox"><b>Confirmation code sent.</b> It may take up to a minute to arrive.</div><br /><br />');
                // Unmask
                $('div#verify_mask').hide();
            }
            else {
                // Send error back to UI
                $('div#report').html('<div class="errorbox">' + data.message + '</div><br /><br />');
                // Unmask
                $('div#verify_mask').hide();
            }
        }
    });
}

function verify_code() {
    // Mask send area
    $('div#verify_mask')
        .width($('div#verify_area').width())
        .height($('div#verify_area').height())
        .show();

    var send_data = $('form#mobilesms_verify').serialize();
    $.ajax({
        url: docRoot + 'ajax/?do=confirm_code',
        type: 'post',
        data: send_data,
        dataType: 'json',
        success: function (data) {
            if (data.success == 1) {
                // Reveal second area
                $('div#confirm_1').hide();
                $('div#confirm_2').hide();
                $('div#confirm_3').show();
                // Remove any error with success
                $('div#report').html('');
                // Unmask
                $('div#verify_mask').hide();
            }
            else {
                // Send error back to UI
                $('div#report').html('<div class="errorbox">' + data.message + '</div><br /><br />');
                // Unmask
                $('div#verify_mask').hide();
            }
        }
    });
}

// Stops double submitting form.
var submit_count = 0;

function bind_cart_submit_mask() {
    $('form#checkoutform input.cart_submit').bind('click', function () {
        var payment_method = parseInt($('input#payment_method').val());
        var payment_method_expired = parseInt($('input#payment_method_expired').val());
        if (payment_method > 2) {
            if (payment_method_expired == 1) {
                alert("Please select a payment method.");
                // Scroll to payment
                $('html,body').animate({
                    scrollTop: $("a#payment_method").offset().top - 500
                }, 400);
                return false;
            }
        }

        submit_count++;
        if (submit_count > 1) {
            //alert(submit_count);
            return false;
        }
        $('div#mask_cart_buttons')
            .width($('div#cart_buttons').width())
            .height($('div#cart_buttons').height())
            .show();
    });
}

/!* PAYMENT SCREEN *!/

function checkout_payment_area() {
    $('#paynow').bind('click', function () {
        $('div#payment_area_mask')
            .width($('div#payment_area').width())
            .height($('div#payment_area').height())
            .show();
        setTimeout(function () {
            $('div#payment_area_mask').hide();
        }, 2500);
    });
}

// Checkout process and pay invoices by card
function checkout_buttons() {
    // Standard payment methods
    $('div.checkout_payments img.checkout_icon').bind('click', function () {
        var method = $(this).attr('id');

        $('img.checkout_icon').removeClass("payment_on");
        $('img.checkout_icon').addClass("payment_off");

        $(this).removeClass("payment_off");
        $(this).addClass("payment_on");
        continueCheckout(method);
    });
}

function continueCheckout(method) {
    update_paying_with(method);

    $('#checkoutFinish').fadeIn();

    if (method == 'paypal') {
        $('input#paynow').unbind().bind('click', function () {
            $(this).attr('disabled', true);
            // this loads a redirect page contolled by paypal class
            //window.location = docRoot + 'dashboard/paypal/checkout';
        });
    }
    else if (method == 'google') {
        $('input#paynow').unbind().bind('click', function () {
            $(this).attr('disabled', true);
            // this loads a fresh google checkout form and submits it via JS
            //load_google_checkout();
        });
    }
    else {
        $('input#paymentType').val(method);
        $('input#paynow').unbind().bind('click', function () {
            // we have set the card type hidden field, we then loads the worldpay form into highslide
            document.worldpay_form.submit();
            // Unhide payment form
            $('div#payment_screen').show();
            // Hide select payment
            $('div#checkoutFinish').hide();
            $('div#checkout_payments').hide();
            // Start polling
            var order_id = parseInt($('input#cartId').val());
            wp_result_wait(order_id, 0, true);
            // Page warning
            //bind_page_warning();
        });
    }

    // Scroll to payment
    $('html,body').animate({
        scrollTop: $("div#payment_area").offset().top
    }, 1000);

    return false;
}

function checkout_start_polling() {
    var order_id = parseInt($('input#cartId').val());
    wp_result_wait(order_id, 0, true);
}

function check_for_payment_init() {
    var order_id = parseInt($('input#cartId').val());
    if (order_id == '' || order_id == 0) {
        $('div#payment_checking').hide();
        $('div#check_payment_error').html('<div class="errorbox">Error: We have no Order ID for your order. Please go back to your cart and try again. If you feel this is an error please contact support.</div>');
    }
    else {
        checkout_start_polling();
        setTimeout(function () {
            $('div#check_payment_error').html('<div class="errorbox">Error: We\'re having trouble finding a payment for your order. If you feel this is an error please contact support.</div>');
        }, 30000);
    }
}

function update_paying_with(method) {
    if (method == 'paypal') {
        $('span#paying_with').html('Paying with PayPal');
    }
    else if (method == 'google') {
        $('span#paying_with').html('Paying with Google Checkout');
    }
    else {
        switch (method) {
            case 'VISD':
                var payment = 'Visa Debit Card';
                break;
            case 'VISA':
                var payment = 'Visa Credit Card';
                break;
            case 'MSCD':
                var payment = 'Mastercard';
                break;
            case 'VIED':
                var payment = 'Visa Electron';
                break;
        }
        $('span#paying_with').html('Paying with ' + payment);
    }
}

function load_google_checkout() {
    // Not applicable
}

function wp_result_wait(cart_id, counter, retry) {
    var delay_time = 4000;

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: docRoot + 'ajax/?do=wp_result_polling&cartId=' + parseInt(cart_id)
        + '&counter=' + counter
        ,
        async: true, /!* If set to non-async, browser shows page as "Loading.."*!/
        cache: false,
        timeout: 20000, /!* Timeout in ms *!/

        success: function (data) {
            if (data.result == 'ok') {
                setTimeout(function () {
                    unbind_page_warning();
                    window.location = docRoot + 'checkout/checkout/finished';
                }, 100);
                return false;
            }
            else {
                if (retry) {
                    setTimeout(function () {
                        wp_result_wait(cart_id, ++counter, true)
                    }, delay_time);
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (counter == 5) {
                alert('Problem connecting to transaction server. Please call your account manager');
                return false;
            }
            if (retry) {
                setTimeout(function () {
                    wp_result_wait(cart_id, ++counter, true)
                }, delay_time);
            }
        },
    });
};

var page_warning = false;
var use_unload_warning = true;

function bind_page_warning() {
    if (use_unload_warning) {
        if (page_warning === false) {
            $(window).bind("beforeunload", function () {
                return "Please do not navigate away from this page until your order has been processed.";
            });
            page_warning = true;
        }
    }
}

function unbind_page_warning() {
    if (use_unload_warning) {
        if (page_warning === true) {
            $(window).unbind("beforeunload");
            page_warning = false;
        }
    }
}

function checkout_no_navigate() {
    //bind_page_warning();
}

var checkTimeout;

function finish_checkout_init() {

    $('div#loading_area').html('<div class="loading17" id="check_loading"><!-- --></div>');

    var cartID = $('input#cart_id').val();

    // Checkout fail
    checkTimeout = setTimeout(function () {
        checkout_check_failed(cartID);
    }, 60000);

    // Request confirmation
    $.ajax({
        type: 'GET',
        url: 'ajax?ajax=confirm_payment',
        async: true, /!* If set to non-async, browser shows page as "Loading.."*!/
        cache: false,
        success: function (data) {
            load_completed(data);
        }
    });
}

function load_completed(data) {
    $('div#loading_area').hide();
    $('div#start_message').hide();
    $('div#result_area').html(data).fadeIn();
    clearTimeout(checkTimeout);
    unbind_page_warning();
}

// Incase something goes wrong, add a 60 second 'oops' error.
function checkout_check_failed(cartID) {
    $('div#actions').html('<div class="alert error">It seems something has gone wrong. Please contact us quoting <b>order reference: ' + cartID + '</b></div>');
}

/!* Share *!/
function pizza_share_facebook_working() {
    var share_url = $('input#share_url').val();
    var share_text = $('input#share_text').val();
    var share_title = 'Look what I just ordered!';

    var window_width = $(window).width();
    var max_width = 500;
    var min_width = 300;
    if (window_width > max_width) {
        window_width = max_width
    }
    if (window_width < min_width) {
        window_width = min_width
    }

    location.href;
    t = document.title;
    window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(share_url) + '&t=' + encodeURIComponent(share_title), 'sharer', 'toolbar=0,status=0,width=' + parseInt(window_width) + ',height=436');
    return false;
}

function pizza_share_facebook() {
    postToFeed();
}

function postToFeed() {
    // calling the API ...
    var obj = {
        method: 'feed',
        redirect_uri: $('input#share_url').val(),
        link: $('input#share_url').val(),
        picture: 'http://pgg.nowmarketing.co.uk/media/large/1.jpg', // ToDo what is this reffence?
        name: 'Look what I just ordered!',
        caption: 'From Pizza GoGo',
        description: $('input#share_text').val()
    };

    function callback(response) {
        document.getElementById('msg').innerHTML = "Post ID: " + response['post_id'];
    }

    FB.ui(obj, callback);
}


function pizza_share_facebook_dialog() {
    var share_url = $('input#share_url').val();
    var share_text = $('input#share_text').val();
    var share_desc = 'Order pizzas online from Pizza GoGO.';
    var share_name = 'Pizza GoGo';
    var share_caption = 'Pizza!';
    var app_id = '384037728361616';

    var facebook_url = 'http://www.facebook.com/dialog/feed?';
    facebook_url += 'app_id=' + escape(app_id);
    facebook_url += '&link=' + encodeURIComponent(share_url);
    facebook_url += '&picture=http://fbrell.com/f8.jpg';
    facebook_url += '&name=' + encodeURIComponent(share_name);
    facebook_url += '&caption=' + encodeURIComponent(share_caption);
    facebook_url += '&description=' + encodeURIComponent(share_desc);
    facebook_url += '&message=' + encodeURIComponent(share_text);
    facebook_url += '&redirect_uri=' + encodeURIComponent(share_url);

    location.href;
    t = document.title;
    window.open(facebook_url, 'sharer', 'toolbar=0,status=0,width=626,height=436');
    return false;
}

function pizza_share_twitter() {
    var share_url = $('input#share_url').val();
    var share_text = $('input#share_text_twitter').val();

    var twitter_url = 'http://twitter.com/share?url=' + encodeURIComponent(share_url) + '&text=' + encodeURIComponent(share_text);

    var window_width = $(window).width();
    var max_width = 500;
    var min_width = 300;
    if (window_width > max_width) {
        window_width = max_width
    }
    if (window_width < min_width) {
        window_width = min_width
    }

    // Tweet
    location.href;
    t = document.title;
    window.open(twitter_url, 'sharer', 'toolbar=0,status=0,width=' + parseInt(window_width) + ',height=300');

    return false;
}

var current_offer = 0;
var offer_redeemed = 0;

function cart_special_offers() {
    if ($('div#banging_offers').length > 0) {

        var offer_count = $('div.cart_offer').length;
        if (offer_count > 1) {
            $('a.cart_offers_next').show();
        }

        // Bind close button
        $('a#cart_offers_close').bind('click', function () {
            $('div#banging_offers').slideUp();
            return false;
        });
        // Bind next offer
        $('a.cart_offers_next').bind('click', function () {
            var next_offer = current_offer + 1;
            if ($('div#cart_offer_' + parseInt(next_offer)).length > 0) {
                current_offer = next_offer;
            }
            else {
                current_offer = 0;
                next_offer = 0;
            }
            $('div.cart_offer').hide();
            $('div#cart_offer_' + parseInt(next_offer)).show();
            return false;
        });

        // Bind redeem
        $('a.cart_offers_redeem').bind('click', function () {
            if (offer_redeemed == 1) {
                return false;
            }
            offer_redeemed = 1;
            return true;
        });
    }
}

/!* Checkout select payment method *!/
function select_payment_method(method) {
    $('div#pm_1, div#pm_2').removeClass('select_payment_method_active');
    $('div#pm_' + parseInt(method)).addClass('select_payment_method_active');
    $('input#payment_method').val(parseInt(method));
    change_payment_buttons(method);

    var expired = $('div#pm_' + parseInt(method)).data('expired');
    $('input#payment_method_expired').val(parseInt(expired));
}

function select_payment_method_saved(pid) {
    $('div.pm_all').removeClass('select_payment_method_active');
    $('div#pm_' + parseInt(pid)).addClass('select_payment_method_active');
    $('input#payment_method').val(parseInt(pid));
    change_payment_buttons(pid);

    var expired = $('div#pm_' + parseInt(pid)).data('expired');
    $('input#payment_method_expired').val(parseInt(expired));

    if (pid == 1) {
        $('div#card_next_stage').show();
        $('a#checkout_continue').show();
    }
    else {
        $('div#card_next_stage').hide();
        $('a#checkout_continue').hide();
    }
}

/!* Display all payment methods *!/
function button_change_payment_method() {
    $('div#change_payment_method_1').hide();
    $('div#change_payment_method_2').show();
    $('div.pm_all').show();
    return false;
}

function button_change_payment_method_select() {
    $('div#change_payment_method_2').hide();
    $('div#change_payment_method_1').show();

    $('div.pm_all').each(function (e) {
        if ($(this).hasClass('select_payment_method_active')) {
            $(this).show();
        }
        else {
            $(this).hide();
        }
    });
    return false;
}

function name_order() {
    $('div#order_name_trigger').hide();
    $('div#order_name').fadeIn();
    return false;
}

function close_sbwarning() {
    $('div#sbwarning').hide();
    return false;
}

function checkout_continue() {
    // Scroll to payment
    $('html,body').animate({
        scrollTop: $("a#review_order").offset().top - 500
    }, 400);
    return false;
}
*/
