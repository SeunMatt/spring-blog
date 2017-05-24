<#include "../../layouts/admin.ftl"/>
<@admin title="Admin Posts">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Posts
            <small></small>
        </h1>
        <br>
        <a href="/eyin/posts/edit" class="btn btn-primary btn-lg btn-flat margin-top margin-bottom">Compose</a>
        <ol class="breadcrumb">
            <li><a href="/eyin"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Posts</li>
        </ol>
        <br>
        <#include "../../partials/alerts.ftl"/>
    </section>

    <!-- Main content -->
    <section class="content">
        <form role="form" method="post" id="delForm" action="/eyin/posts/delete">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="id" />
        </form>
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <!-- The time line -->
                <ul class="timeline">

                 <#list posts as post>
                    <!-- timeline time label -->
                    <li class="time-label">
                        <#assign x = "2017-05-22T09:38:23"/>
                        <span class="bg-green"> ${post.createdAt?date.@localdatetime} </span>
                    </li>
                    <!-- /.timeline-label -->
                    <!-- timeline item -->
                    <li>
                        <i class="fa fa-comments bg-blue"></i>

                        <div class="timeline-item">

                            <span class="time"><i class="fa fa-eye"></i> ${post.views}</span>

                            <h3 class="timeline-header"><a href="/eyin/posts/read/${post.id}">${post.title}</a></h3>

                            <div class="timeline-body">
                                ${post.post}
                                 <hr style="margin-bottom: 0px;">

                                <span style="color: rgba(0,0,0,0.6); margin-right: 3%; float: left;">
                                    category: ${post.category.category!""}
                                </span>
                                <span style="color: rgba(0,0,0,0.6); margin-right: 3%; float: left;">
                                    section: ${post.section.section!""}
                                </span>
                                <br>
                            </div>
                            <div class="timeline-footer">
                                <a class="btn btn-primary btn-xs" href="/eyin/posts/edit/${post.id}">Edit</a>
                                <a class="btn btn-danger btn-xs del" data-id="${post.id}">Delete</a>
                            </div>
                        </div>
                    </li>
                    <!-- END timeline item -->
                 </#list>
                    <li>
                        <i class="fa fa-clock-o bg-gray"></i>
                    </li>
                </ul>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->

    </section>

</div>

<script>
    $(document).ready(function () {
        $(".del").on("click", function () {
            var id = $(this).attr("data-id");
            swal({
                        title: "Are you sure?",
                        text: "Do you really wanna Delete the Post?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-default",
                        confirmButtonClass: "btn-warning",
                        confirmButtonText: "Yes, Do it!",
                        closeOnConfirm: true,
                    },
                    function() {
                        displayWait(".row");
                        $("input[name='id']").val(id);
                        document.getElementById("delForm").submit();
                    });
        });
    });
</script>

</@admin>