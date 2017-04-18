<#include "../../layouts/admin.ftl"/>
<@admin title="Admin Post">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>Admin Post
        </h1>
        <ol class="breadcrumb">
            <li><a href="/eyin"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="/eyin/posts"> Posts</a></li>
            <li class="active">Post</li>
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
        <div class="row">
            <div class="col-md-3">
                <a href="/eyin/posts/edit/" class="btn btn-primary btn-block margin-bottom">Compose</a>
            </div>
            <!-- /.col -->
            <div class="col-md-9">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">${post.title}</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body no-padding">
                        <div class="mailbox-read-info">
                            <h4>Published: ${post.published?c}</h4>
                            <h4>Author: ${post.author}</h4>
                            <h5>Category: ${post.category_id}</h5>
                                <h5>Section: ${post.section_id}</h5>

                            <h5>
                                <span class="mailbox-read-time pull-left">reads: ${post.reads}</span>
                                <br>
                                <span class="mailbox-read-time pull-left">created at: ${post.createdAt?date}</span>
                                <br>
                                <span class="mailbox-read-time pull-left">updated at: ${post.updatedAt?date}</span>

                            </h5>
                            <h5></h5>
                        </div>
                        <!-- /.mailbox-read-info -->
                        <div class="mailbox-controls with-border text-center">
                            <div class="btn-group">
                                <a href="/eyin/posts/edit/${post.id}"><button type="button" class="btn btn-default btn-sm" data-toggle="tooltip" data-container="body" title="Edit">
                                    <i class="fa fa-pencil"></i></button></a>
                            </div>
                            <!-- /.btn-group -->
                            <button type="button" class="btn btn-default btn-sm del" data-id="${post.id}" data-toggle="tooltip" data-container="body" title="Delete">
                                <i class="fa fa-trash-o"></i></button>
                            <button type="button" class="btn btn-default btn-sm" data-toggle="tooltip" title="Print">
                                <i class="fa fa-print"></i></button>
                        </div>
                        <!-- /.mailbox-controls -->
                        <div class="mailbox-read-message">

                            <p>
                               ${post.post}
                            </p>

                        </div>
                        <!-- /.mailbox-read-message -->
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <div class="pull-right"></div>
                        <button type="button" class="btn btn-default del" data-id="${post.id}"><i class="fa fa-trash-o"></i> Delete</button>
                        <a href="/eyin/posts/edit/${post.id}"><button type="button" class="btn btn-default"><i class="fa fa-pencil"></i> Edit</button></a>
                    </div>
                    <!-- /.box-footer -->
                </div>
                <!-- /. box -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
</div>

<script>
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
</script>
</@admin>