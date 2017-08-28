<div class="box-body box-comments" style="border-radius: 0;">
    <#list comments as comment>
        <div class="cmts-main-right reply">
            <h6>${comment.name} <span>${comment.createdAt?date.@localdatetime}</span></h6>
            <p>${comment.comment}</p>
        </div>
    </#list>
</div>
