<#include "../../layouts/admin.ftl"/>
<@admin title="Admin Users">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
           Users
            <small></small>
        </h1>
        <br>
        <a href="#" class="btn btn-primary btn-lg btn-flat margin-top margin-bottom" id="addBt">Create User</a>
        <ol class="breadcrumb">
            <li><a href="/eyin"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">users</li>
        </ol>
        <br>
        <#include "../../partials/alerts.ftl"/>
    </section>

    <!-- Main content -->
    <section class="content">
        <form role="form" method="post" id="delForm" action="/eyin/users/delete">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="id" />
        </form>
        <!-- row -->
        <div class="row">
            <div class="col-md-12">

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Users</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table id="users" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>Full Name</th>
                                <th>username</th>
                                <th>email</th>
                                <th>role</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                                <#list users as user>
                                <tr>
                                    <td>${user.name}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>${user.roleId}</td>
                                    <td>
                                        <a href="/eyin/users/view/${user.id}" class="btn btn-sm btn-primary"><span class="fa fa-search"></span>
                                        </a>
                                        <button data-id="${user.id}" class="btn btn-sm btn-danger del"><span class="fa fa-trash"></span>
                                        </button>
                                    </td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
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
                        text: "Do you really wanna Delete the User?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-default",
                        confirmButtonClass: "btn-warning",
                        confirmButtonText: "Yes, Do it!",
                        closeOnConfirm: true,
                    },
                    function() {
                        displayWait(".box-body");
                        $("input[name='id']").val(id);
                        document.getElementById("delForm").submit();
                    });
        });

    });

</script>

</@admin>