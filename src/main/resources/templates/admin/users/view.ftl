<#include "../../layouts/admin.ftl"/>
<@admin title="Admin Posts">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            User Profile
        </h1>
        <ol class="breadcrumb">
            <li><a href="/eyin"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="/eyin/users">Users</a></li>
            <li class="active">User profile</li>
        </ol>
        <br>
        <#include "../../partials/alerts.ftl"/>
    </section>

    <!-- Main content -->
    <section class="content">
        <form class="form-horizontal" method="POST" id="delForm" action="/eyin/users/ban">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="id" value="${o_user.id}" />
            <input type="hidden" name="bstatus">
            <input type="hidden" name="role">
        </form>
        <div class="row">
            <div class="col-md-3">

                <!-- Profile Image -->
                <div class="box box-primary">
                    <div class="box-body box-profile">
                        <img id="profilePicImg" class="profile-user-img img-responsive img-circle"
                            <#if ((o_user.profilePic!""?length) > 0) >  src='/files/${o_user.profilePic}' <#else> src="<@asset url='admin/images/user.png'/>" </#if>  alt="User profile picture">
                        <h3 class="profile-username text-center">
                            ${o_user.name}
                        </h3>
                        <p class="text-muted">
                            username: ${o_user.username}
                            <br>
                            email: ${o_user.email}
                            <br>
                            status: <#if (o_user.banned)!false == true>Banned<#else>Active</#if>
                            <br>
                            role: ${(o_user.role)!""}
                        </p>
                        <hr>
                        <p class="text-muted text-center">${(o_user.bio)!""}</p>

                        <a href="#" class="btn btn-primary btn-block"><b>Send Mail</b></a>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->

                <!-- Admin Box -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Admin Use Only</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body box-admin">
                        <strong>Assign Role</strong>
                        <br><br>
                        <select class="form-control" id="role">
                            <#list roles as role>
                                <option>${role}</option>
                            </#list>
                        </select>
                        <br>
                        <button id="roleBt" class="btn btn-primary"> SAVE CHANGES</button>
                        <br>
                        <hr>

                        <button type="submit" id="delBt" class="btn btn-sm btn-danger"><span class="fa fa-trash"></span> DELETE</button>
                        <#if (o_user.banned)!false == true>
                         <button type="submit" id="unbanBt" class="btn btn-sm btn-success"><span class="fa fa-ban"></span> UNBLOCK</button>
                        <#else>
                         <button type="submit" id="banBt" class="btn btn-sm btn-danger"><span class="fa fa-ban"></span> BLOCK</button>
                        </#if>

                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->

            </div>
            <!-- /.col -->
            <div class="col-md-9">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li><a href="#articles" data-toggle="tab">Articles</a></li>
                    </ul>
                    <div class="tab-content">

                        <div class="active tab-pane" id="articles">
                            <!-- The timeline -->
                            <ul class="timeline timeline-inverse">
                                <#list posts as post>
                                    <!-- timeline time label -->
                                    <li class="time-label">
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
                                                <a class="btn btn-danger btn-xs del-post" data-id="${post.id}">Delete</a>
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
                        <!-- /.tab-pane -->
                    </div>
                    <!-- /.tab-content -->
                </div>
                <!-- /.nav-tabs-custom -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script>
    $(document).ready(function() {

        $("#delBt").on("click", function () {
            swal({
                        title: "Are you sure?",
                        text: "Do you really wanna Delete the User?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-default",
                        confirmButtonClass: "btn-warning",
                        confirmButtonText: "Yes, Do it!",
                        closeOnConfirm: true,
                    },
                    function () {
                        displayWait(".box-admin");
                        $("#delForm").attr("action", "/eyin/users/delete");
                        document.getElementById("delForm").submit();
                    });
        });

        $("#banBt").on("click", function () {
            swal({
                        title: "Are you sure?",
                        text: "Do you really wanna Ban the User?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-default",
                        confirmButtonClass: "btn-warning",
                        confirmButtonText: "Yes, Do it!",
                        closeOnConfirm: true,
                    },
                    function () {
                        $("#delForm").attr("action", "/eyin/users/ban");
                        $("input[name='bstatus']").val("true");
                        displayWait(".box-admin");
                        document.getElementById("delForm").submit();
                    });
        });

        $("#unbanBt").on("click", function () {
            swal({
                        title: "Are you sure?",
                        text: "Do you want to Unblock the User?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-default",
                        confirmButtonClass: "btn-warning",
                        confirmButtonText: "Yes, Do it!",
                        closeOnConfirm: true,
                    },
                    function () {
                        $("#delForm").attr("action", "/eyin/users/ban");
                        $("input[name='bstatus']").val("false");
                        displayWait(".box-admin");
                        document.getElementById("delForm").submit();
                    });
        });

        $("#roleBt").on("click", function () {
            var role = $("#role").val();

            swal({
                        title: "Are you sure?",
                        text: "Do you really wanna make the User a " + role + "?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-default",
                        confirmButtonClass: "btn-warning",
                        confirmButtonText: "Yes, Do it!",
                        closeOnConfirm: true,
                    },
                    function () {
                        $("#delForm").attr("action", "/eyin/users/role");
                        $("input[name='role']").val(role);
                        displayWait(".box-admin");
                        document.getElementById("delForm").submit();
                    });
        });

        $(".del-post").on("click", function () {
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
                        $("#delForm").attr("action", "/eyin/posts/delete");
                        $("input[name='id']").val(id);
                        document.getElementById("delForm").submit();
                    });
        });
    });
</script>
</@admin>