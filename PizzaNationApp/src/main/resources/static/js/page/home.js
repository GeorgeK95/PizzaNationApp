function activate_hamburger_menu() {
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