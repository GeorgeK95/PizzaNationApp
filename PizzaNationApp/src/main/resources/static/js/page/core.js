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