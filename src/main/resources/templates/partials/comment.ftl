<h4>COMMENTS</h4>
<hr class="star-primary">
<div class="comments-main">
    <input type="hidden" id="activeC" value="" />
    <#list comments as comment>
        <div class="col-md-10 cmts-main-right">
            <h5>${comment.name} <span>On ${comment.createdAt?date.@localdatetime}</span></h5>
            <p>${comment.comment}</p>
            <div class="replies" id="r__${comment.id}"></div>
            <div class="cmts">
                <div class="cmnts-right">
                    <a href="#" class="replyBt" data-id="${comment.id}">Reply</a>
                    <a href="#" class="vReply" data-id="${comment.id}">View Replies</a>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="clearfix"></div>
    </#list>
    <script>
        $(document).on("click", ".replyBt", function (event) {
            event.preventDefault();
            $("input[name='parentCommentId']").val( $(this).attr("data-id") );
            $("#activeC").val($(this).attr("data-id"));
            $("#replyModal").modal();
        });
        $(document).on("click", ".vReply", function (event) {
           event.preventDefault();
           var vRId = $(this).attr("data-id");
            $.get("/comment/replies/" + $("#postId").val() + "/" + $(this).attr("data-id"), function (response) {
                $("#r__" + vRId).html(response);
            });
        });
    </script>
</div>