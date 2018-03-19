$(function () {
    // on page loaded
    app_token();
});

function app_token() {
    // do we have html5 storage capability?
    if (typeof(Storage) !== "undefined") {
        // read and send stored token
        $.ajax({
            url: docRoot + 'ajax/?do=app_token',
            dataType: 'json',
            type: 'post',
            data: {
                'token': get_token(),
                'previous': document.referrer
            },
            success: function (data) {
                console.log(data);
                // save returned token
                if (data.token) {
                    save_token(data.token);
                }
                // alert message
                if (data.alert) {
                    alert(data.alert);
                }
                // forward user
                if (data.redirect) {
                    window.location.href = data.redirect;
                }
            }
        });
    } else {
        // browser does not support web storage... ignore and now relies on legacy login
    }
}

// save to html5 storage
function save_token(token) {
    localStorage.t_log = token;
    return true;
}

// get from html5 storage
function get_token() {
    return localStorage.t_log;
}