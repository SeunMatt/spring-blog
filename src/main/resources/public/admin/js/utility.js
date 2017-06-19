function displayWait(selector) {

    $(selector).block({
        message: '<div class="blockui-default-message"><i class="fa fa-circle-o-notch fa-spin"></i><h6>Processing . . .</h6></div>',
        overlayCSS:  {
            background: 'rgba(142, 159, 167, 0.8)',
            opacity: 1,
            cursor: 'wait'
        },
        css: {
            width: '50%'
        },
        blockMsgClass: 'block-msg-default'
    });
}


function cancelWait(selector) {
    $(selector).unblock()
}

$("#searchForm").on("submit", function(event) {
    event.preventDefault();
    console.log("called");
    if($("#keywords").val().length > 3) {
        location.href = "/search/" + $("#keywords").val();
    }
});