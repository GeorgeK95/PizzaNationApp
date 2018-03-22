function bind_mobile_nav() {
    // Top navigation
    $('a#mobile_nav_button').bind('click', function () {
        console.log(1);
        if ($('div#mobnav').is(':visible')) {
            $('div#mobnav').slideUp(50);
            console.log(2);
        }
        else {
            console.log(3);
            // Scroll to top
            $('html, body').animate({
                scrollTop: 0
            }, 0);
            $('div#mobnav').slideDown(100);
            console.log(4)
        }
        console.log(4)
        return false;
    });
}