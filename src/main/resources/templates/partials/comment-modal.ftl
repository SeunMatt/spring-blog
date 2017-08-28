<div class="modal modal-primary" id="replyModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <#--<div class="modal-header">-->
                <#--<button type="button" class="close" data-dismiss="modal" aria-label="Close">-->
                    <#--<span aria-hidden="true">&times;</span></button>-->
                <#--<h4 class="modal-title">Comment Reply</h4>-->
            <#--</div>-->
            <div class="modal-body">
                <div class="comment-text" id="replyText">

                </div>
                <hr>
                <!-- form start -->
                <form class="form-horizontal" id="replyForm" action="/comment/add" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" name="postId" value="${post.id}">
                    <input type="hidden" name="parentCommentId" value="">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="comment" class="col-sm-2 control-label">Comment</label>
                            <div class="col-sm-10">
                                <textarea rows="5" cols="5" class="form-control" id="comment" name="comment" placeholder="Reply" required></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="notify"> Notify me, via email, of direct comment reply
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-info pull-right">Post Reply</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="closeReplyBt" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
    $("#replyForm").on("submit", function (event) {
        event.preventDefault();
        $.ajax({
            url: "/comment/reply",
            type: "post",
            data: $("#replyForm").serialize(),
            beforeSend: function () {
                displayWait("#replyForm");
            },
            success: function (response) {
                cancelWait("#replyForm");
                $("#r__" + $("#activeC").val()).html(response);
                $("#replyForm #email, #replyForm #name, #replyForm #comment").val("");
                $("#replyModal").modal("hide");
            },
            error: function (error) {
                cancelWait("#replyForm");
                $("#replyModal").modal("hide");
            }
        });
    });
</script>