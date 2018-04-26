/*function load_video_in(source, target) {
    var video_webm = $(source).data('videowebm');
    var video_mp4 = $(source).data('videomp4');

    var markup = '<video width="100%" height="auto" controls autoplay loop>';
    markup += '<source src="' + video_mp4 + '" type="video/mp4">';
    markup += '<source src="' + video_webm + '" type="video/webm">';
    markup += 'Sorry. Your browser does not support the video tag.';
    markup += '</video>';

    $(target).html(markup);
    return false;
}*/

$(document).ready(function () {
    $(document).on('click', 'a.add_to_cart_btn', function () {
        addProductAjax($(this).attr('id'));
    })
})

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