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