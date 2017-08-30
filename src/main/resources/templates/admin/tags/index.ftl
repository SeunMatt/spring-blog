<#include "../../layouts/admin.ftl"/>
<@admin title="Admin - Tags">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Tags
            <small></small>
        </h1>
        <br>
        <a href="#" class="btn btn-primary btn-lg btn-flat margin-top margin-bottom" id="addBt">Add Tag</a>
        <ol class="breadcrumb">
            <li><a href="/eyin"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Tags</li>
        </ol>
        <br>
        <#include "../../partials/alerts.ftl"/>
    </section>

    <!-- Main content -->
    <section class="content">
        <form role="form" method="post" id="delForm" action="/eyin/tags/delete">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="id" />
            <input type="hidden" name="tag" />
        </form>
        <!-- row -->
        <div class="row">
            <div class="col-md-12">

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Tags</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table id="tags" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>Tag</th>
                                <th>No. of Articles</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list tags as tag>
                            <tr>
                                <td>${tag}</td>
                                <td></td>
                                <td>
                                    <button data-id="${tag.id}" data-cat="${tag.tag}" class="btn btn-sm btn-primary edit"><span class="fa fa-pencil"></span>
                                    </button>
                                    <button data-id="${tag.id}" class="btn btn-sm btn-danger del"><span class="fa fa-trash"></span>
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
                       text: "Do you really wanna Delete the Tag?",
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
                       $("#delForm").attr("action", "/eyin/tags/delete");
                       document.getElementById("delForm").submit();
                   });
       });

       $(".edit").on("click", function (event) {
           var id = $(this).attr("data-id"), tag = $(this).attr("data-cat");
           swal({
                       title: "Rename Tag",
                       text: "Old Tag Name: " + tag,
                       type: "input",
                       showCancelButton: true,
                       closeOnConfirm: false,
                       animation: "slide-from-top",
                       inputPlaceholder: "New Tag Name",
                       showLoaderOnConfirm: true
                   },
                   function(inputValue){
                       if (inputValue === false) return false;

                       if (inputValue === "") {
                           swal.showInputError("You need to write something!");
                           return false
                       }
                       $("input[name='id']").val(id);
                       $("input[name='tag']").val(inputValue);
                       $("#delForm").attr("action", "/eyin/tags/update");
                       document.getElementById("delForm").submit();
                   });

       });

       $("#addBt").on("click", function (event) {
           event.preventDefault();
           swal({
                       title: "New Tag",
                       text: "",
                       type: "input",
                       showCancelButton: true,
                       closeOnConfirm: false,
                       animation: "slide-from-top",
                       inputPlaceholder: "Tag Name",
                       showLoaderOnConfirm: true
                   },
                   function(inputValue){
                       if (inputValue === false) return false;

                       if (inputValue === "") {
                           swal.showInputError("You need to write something!");
                           return false
                       }
                       $("input[name='tag']").val(inputValue);
                       $("#delForm").attr("action", "/eyin/tags");
                       document.getElementById("delForm").submit();
                   });

       });

   });
</script>

</@admin>