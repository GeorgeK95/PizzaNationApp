/*
	Pizza Slice
	Global functions
*/

/* Mobile nav */
function bind_mobile_nav() {
	// Top navigation
	$('a#mobile_nav_button').bind('click', function() {
		if($('div#mobnav').is(':visible')) {
			$('div#mobnav').slideUp(50);
		}
		else{
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
	if(mask == 1) {
		var doc_size = viewport_size();
		$('div#ui_mask')
			.height(doc_size['y'])
			.width(doc_size['x'])
			.show();
	}
	// Unmask
	else{
		$('div#ui_mask').hide();
	}
}

getViewport = function() {
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
		.offset({ top: y, left: x })
		.hide()
		.removeClass('hide_entry');
	if(unhide_element) {
		$(element_focus)
			.fadeIn(fadein_speed);
	}
}

// Open an alert
function ui_alert(text) {
	ui_alert_close();
	//mask_main_page(1);
	var html = '<div id="ui_alert"><div id="ui_alert_inner">';
	html += '<p>'+text+'</p>';
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


/* ~ */

// Postcode
function postcode_init() {
	var thisval = $('input.postcode_input').val();
	if(thisval != '') {
		$('input.postcode_input').addClass('postcode_uppercase');
	}
	
	$('input.postcode_input').on('keyup', function() {
		var thisval = $(this).val();
		if(thisval != '') {
			$('input.postcode_input').addClass('postcode_uppercase');
		}
		else {
			$('input.postcode_input').removeClass('postcode_uppercase');
		}
	});
}
// Branch page
function branch_init() {
	if($('div#show_other_branches a#showmore').length > 0) {
		$('div#show_other_branches a#showmore').bind('click', function() {
			$('div#show_other_branches').hide();
			$('div#other_branches').slideDown('slow');
		});
	}
	
	$('a#change_postcode').bind('click', function() {
		$('div#branch_select').hide();
		$('div#postcode_box').show();
		return false;
	});
}
// Mobile navigation
function mobile_nav_init() {
	$('a#menu_nav').bind('click', function() {
		$('div#main_nav_button').toggle();
		return false;
	});
}
// Interactive
function nearby_stores(store_id) {
	var store_id = parseInt(store_id);
	$.ajax({
		url: docRoot + 'ajax/?do=nearby_stores&store_id='+escape(store_id),
		success: function(data) {
			if(data != '') {
				$('div#nearby_stores').html(data);
			}
			else{
				$('div#nearby_stores').html('Error finding nearby stores.');
			}
		}
	});
}
// Stores 
function trigger_store_map(location_title, postcode, map_lat, map_long, map_x, map_y, map_z) {
	// Requires location_map.js
	location_map('Pizza Nation '+location_title, postcode, map_lat, map_long, map_x, map_y, map_z);
}
// Update mini cart (sidebar)
function update_cart() {
	// Get details
	$.ajax({
		url: docRoot + 'ajax/?do=cart_details',
		dataType: 'json',
		success: function(data) {
			// Update mini cart (sidebar)
			var item_word = parseInt(data.item_count) == 1 ? 'item' : 'items';
			//$('div#cartpreview_1').html('<p>' + parseInt(data.item_count) + ' '+item_word+' - &pound;' + escape(data.price.toFixed(2)) + '</p>');
			// Hide mask (sidebar)
			//$('div#mask_cartarea_1').hide();
			// Update mini cart (mobile nav)
			//$('span#cartpreview_2').html(parseInt(data.item_count));
			
			$('span.cart_item_count').html(parseInt(data.item_count));
			// Add checkout_active
			if(data.item_count > 0) {
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

/* Log reg page */
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
	markup += '<source src="'+video_mp4+'" type="video/mp4">';
	markup += '<source src="'+video_webm+'" type="video/webm">';
	markup += 'Sorry. Your browser does not support the video tag.';
	markup += '</video>';
	
	$(target).html(markup);
	return false;
}