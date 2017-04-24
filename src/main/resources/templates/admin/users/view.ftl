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
    </section>

    <!-- Main content -->
    <section class="content">
        <form class="form-horizontal" method="POST" id="delForm" action="/eyin/users/ban">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="id" value="${user.id}" />
            <input type="hidden" name="bstatus">
        </form>
        <div class="row">
            <div class="col-md-3">

                <!-- Profile Image -->
                <div class="box box-primary">
                    <div class="box-body box-profile">
                        <img id="profilePicImg" class="profile-user-img img-responsive img-circle"
                            <#if ((user.profilePic?length) > 0) >  src='/files/${user.profilePic}' <#else> src="<@asset url='admin/images/user4-128x128.jpg'/>" </#if>  alt="User profile picture">
                        <h3 class="profile-username text-center">
                            ${user.name}
                        </h3>
                        <p class="text-muted">
                            username: ${user.username}
                            <br>
                            email: ${user.email}
                            <br>
                            status: <#if (user.banned)!false == true>Banned<#else>Active</#if>
                            <br>
                            role: ${user.roleId}
                        </p>
                        <hr>
                        <p class="text-muted text-center">${(user.bio)!""}</p>

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
                        <select class="form-control">
                            <option>ROLE_EDITOR</option>
                            <option>ROLE_WRITER</option>
                        </select>
                        <br>
                        <button type="submit" class="btn btn-primary"> SAVE CHANGES</button>
                        <br>
                        <hr>

                        <button type="submit" id="delBt" class="btn btn-sm btn-danger"><span class="fa fa-trash"></span> DELETE</button>
                        <#if (user.banned)!false == true>
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
                                <!-- timeline time label -->
                                <li class="time-label">
                        <span class="bg-red">
                          10 Feb. 2014
                        </span>
                                </li>
                                <!-- /.timeline-label -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-envelope bg-blue"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 12:05</span>

                                        <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>

                                        <div class="timeline-body">
                                            Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                            weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                            jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                            quora plaxo ideeli hulu weebly balihoo...
                                        </div>
                                        <div class="timeline-footer">
                                            <a class="btn btn-primary btn-xs">Read more</a>
                                            <a class="btn btn-danger btn-xs">Delete</a>
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-user bg-aqua"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 5 mins ago</span>

                                        <h3 class="timeline-header no-border"><a href="#">Sarah Young</a> accepted your friend request
                                        </h3>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-comments bg-yellow"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 27 mins ago</span>

                                        <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                                        <div class="timeline-body">
                                            Take me to your leader!
                                            Switzerland is small and neutral!
                                            We are more like Germany, ambitious and misunderstood!
                                        </div>
                                        <div class="timeline-footer">
                                            <a class="btn btn-warning btn-flat btn-xs">View comment</a>
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline time label -->
                                <li class="time-label">
                        <span class="bg-green">
                          3 Jan. 2014
                        </span>
                                </li>
                                <!-- /.timeline-label -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-camera bg-purple"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 2 days ago</span>

                                        <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                                        <div class="timeline-body">
                                            <img src="http://placehold.it/150x100" alt="..." class="margin">
                                            <img src="http://placehold.it/150x100" alt="..." class="margin">
                                            <img src="http://placehold.it/150x100" alt="..." class="margin">
                                            <img src="http://placehold.it/150x100" alt="..." class="margin">
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
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

    });
</script>
</@admin>