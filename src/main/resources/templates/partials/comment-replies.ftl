<!-- Box Comment -->
<div class="box box-widget" style="box-shadow: none;">
    <!-- /.box-body -->
    <div class="box-body box-comments" style="border-radius: 0;">
        <#list comments as comment>
        <div class="box-comment" style="border-bottom: none 0px;">
            <div class="comment-text">
                  <span class="username">
                    ${comment.name}
                    <span class="text-muted pull-right">${comment.createdAt?date.@localdatetime}</span>
                  </span><!-- /.username -->
                <p>${comment.comment}</p>
            </div>
            <!-- /.comment-text -->
        </div>
        <!-- /.box-comment -->
        </#list>
    </div>
</div>
<!-- /.box -->