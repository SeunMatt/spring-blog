<#include "../../layouts/admin.ftl"/>
<@admin title="Admin Posts">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Sections
            <small></small>
        </h1>
        <br>
        <a href="#" class="btn btn-primary btn-lg btn-flat margin-top margin-bottom" id="addBt">Add Section</a>
        <ol class="breadcrumb">
            <li><a href="/eyin"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Sections</li>
        </ol>
        <br>
        <#include "../../partials/alerts.ftl"/>
    </section>

    <!-- Main content -->
    <section class="content">
        <form role="form" method="post" id="delForm" action="/eyin/sections/delete">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="id" />
            <input type="hidden" name="section" />
        </form>
        <!-- row -->
        <div class="row">
            <div class="col-md-12">

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Sections</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table id="sections" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>Sections</th>
                                <th>No. of Articles</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list sections as section>
                            <tr>
                                <td>${section.section}</td>
                                <td>${section.articleCount}</td>
                                <td>
                                    <button data-id="${section.id}" data-cat="${section.section}" class="btn btn-sm btn-primary edit"><span class="fa fa-pencil"></span>
                                    </button>
                                    <button data-id="${section.id}" class="btn btn-sm btn-danger del"><span class="fa fa-trash"></span>
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
                       text: "Do you really wanna Delete the Section?",
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
                       $("#delForm").attr("action", "/eyin/sections/delete");
                       document.getElementById("delForm").submit();
                   });
       });

       $(".edit").on("click", function (event) {
           var id = $(this).attr("data-id"), sec = $(this).attr("data-cat");
           swal({
                       title: "Rename Section",
                       text: "Old Section Name: " + sec,
                       type: "input",
                       showCancelButton: true,
                       closeOnConfirm: false,
                       animation: "slide-from-top",
                       inputPlaceholder: "New Section Name",
                       showLoaderOnConfirm: true
                   },
                   function(inputValue){
                       if (inputValue === false) return false;

                       if (inputValue === "") {
                           swal.showInputError("You need to write something!");
                           return false
                       }
                       $("input[name='id']").val(id);
                       $("input[name='section']").val(inputValue);
                       $("#delForm").attr("action", "/eyin/sections/update");
                       document.getElementById("delForm").submit();
                   });

       });

       $("#addBt").on("click", function (event) {
           event.preventDefault();
           swal({
                       title: "New Section",
                       text: "",
                       type: "input",
                       showCancelButton: true,
                       closeOnConfirm: false,
                       animation: "slide-from-top",
                       inputPlaceholder: "Section Name",
                       showLoaderOnConfirm: true
                   },
                   function(inputValue){
                       if (inputValue === false) return false;

                       if (inputValue === "") {
                           swal.showInputError("You need to write something!");
                           return false
                       }
                       $("input[name='section']").val(inputValue);
                       $("#delForm").attr("action", "/eyin/sections");
                       document.getElementById("delForm").submit();
                   });

       });

   });
</script>

</@admin>