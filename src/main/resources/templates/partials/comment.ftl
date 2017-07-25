<!-- Box Comment -->
<div class="box box-widget">
    <!-- /.box-body -->
    <div class="box-body box-comments">
        <#list comments as comment>
        <div class="box-comment">
            <!-- User image -->
            <img class="img-circle img-sm" src="<@asset url='admin/images/default-post-user.jpg' />" alt="User Image">

            <div class="comment-text">
                      <span class="username">
                        ${comment.name}
                        <span class="text-muted pull-right">${comment.createdAt?date.@localdatetime}</span>
                      </span><!-- /.username -->
                ${comment.comment}
                <br><br>
                <button type="button" class="btn btn-default btn-xs reply" data-id="${comment.id}"><i class="fa fa-share"></i> Reply</button>
                <button type="button" class="btn btn-default btn-xs vreply" data-id="${comment.id}"><i class="fa fa-eye"></i> View Replies</button>
            </div>
            <!-- /.comment-text -->
        </div>
        <!-- /.box-comment -->
        </#list>

    </div>
</div>
<!-- /.box -->