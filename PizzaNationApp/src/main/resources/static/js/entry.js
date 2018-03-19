/*
	Pizza Nation
	Entry page
*/

var video_width = 0;
var video_height = 0;
var bind_resize = false;

function load_video_background() {
    if (bind_resize === false) {
        panel_resize_bind();
        bind_resize = true;
    }

    // Check screen res is high enough (desktops - avoid tablets)
    var doc_size = viewport_size();
    if (doc_size['x'] > 768) {
        // Check div exists
        if ($('div#video_background').length > 0) {

            var poster = $('div#video_background').data('poster');
            var webm = $('div#video_background').data('webm');
            var mp4 = $('div#video_background').data('mp4');

            var markup = '<video autoplay poster="' + poster + '" id="bg_vid" loop>';
            markup += '<source src="' + webm + '" type="video/webm">';
            markup += '<source src="' + mp4 + '" type="video/mp4">';
            markup += '</video>';
            $('div#video_background').html(markup);

            video_width = $('div#video_background').data('width');
            video_height = $('div#video_background').data('height');

            resize_video();
        }
    }
}

function resize_video() {
    if ($('video#bg_vid').length > 0) {
        var doc_size = viewport_size();

        var wrapperWidth = doc_size['x'];
        var wrapperHeight = doc_size['y'];

        var videoWidth = video_width;
        var videoHeight = video_height;

        if (doc_size['x'] > doc_size['y']) {
            // Landscape
            //$('video#bg_vid').height(doc_size['y']);
        }
        else {
            // Portrait
            //$('video#bg_vid').width(doc_size['x']);
        }


        if (wrapperWidth / videoWidth > wrapperHeight / videoHeight) {
            $('video#bg_vid').css({
                "width": wrapperWidth + 2, // +2 pixels to prevent empty space after transformation
                "height": "auto"
            });
        } else {
            $('video#bg_vid').css({
                "width": "auto",
                "height": wrapperHeight + 2 // +2 pixels to prevent empty space after transformation
            });
        }
    }
}

function panel_resize_bind() {
    $(window).resize(function () {
        resize_video();
    });
}

//core
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