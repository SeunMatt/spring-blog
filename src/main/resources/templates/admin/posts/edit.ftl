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
            <li class="active">Compose</li>
        </ol>
        <br>
        <#include "../../partials/alerts.ftl"/>
    </section>

    <form role="form" action="/eyin/posts" method="POST" id="postForm">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <#if post??> <input type="hidden" name="id" value="${post.id}"/>  <#else> </#if>
        <input type="hidden" name="published" value="true" />

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-3">

                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h4 class="box-title">CATEGORY</h4>

                        <div class="box-tools">
                            <#--<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>-->
                            <#--</button>-->
                        </div>
                    </div>
                    <div class="box-body no-padding">
                            <select class="form-control" name="category">
                                <option>General</option>
                                <option>Technology</option>
                                <option>Politics</option>
                                <option>Side Gist</option>
                            </select>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /. box -->
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h4 class="box-title">SECTION</h4>

                        <div class="box-tools">
                            <#--<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>-->
                            <#--</button>-->
                        </div>
                    </div>
                    <div class="box-body no-padding">
                        <select class="form-control" name="section">
                            <option>General</option>
                            <option>Robotics</option>
                            <option>Drones</option>
                            <option>AI</option>
                            <option>National Politics</option>
                            <option>Local Politics</option>
                            <option>International News</option>
                        </select>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->
            <div class="col-md-9">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Compose New Post</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="form-group">
                            <input class="form-control" id="title" name="title" placeholder="Post Title:" <#if post??>value="${post.title}" <#else> </#if> required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" id="author" name="author" placeholder="Author:" <#if post??>value="${post.author}" <#else> </#if> required>
                        </div>
                        <div class="form-group">
                         <textarea id="post" name="post" class="form-control" style="resize:vertical; height: 300px" placeholder="Type your post here" required><#if post??>${post.post}<#else></#if></textarea>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="coverPic" value="<#if post??>${post.coverPic}<#else></#if>" />
                            <p class="help-block" id="cover_pic_name">Max. 30MB</p>
                            <br>
                            <div class="small-box">
                                <img class="img-responsive" id="img_cover_pic" src="<#if post??><@asset url='eyin/files/${post.coverPic}'/><#else></#if>" alt="Cover Picture" />
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <div class="pull-left">
                           <button type="button" class="btn btn-default" id="draftBt"><i class="fa fa-pencil"></i> Save Draft</button>
                           <button type="button" class="btn btn-primary" id="pubBt"><i class="fa fa-envelope-o"></i> Publish</button>
                        </div>
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
    </form>
    <input style="display:none;" type="file" name="file" id="cover_pic">
</div>

<script>
  $(document).ready(function(){

    tinymce.init({
        selector: "textarea#post",
        plugins: [
            'advlist autolink link image charmap preview',
            'searchreplace wordcount code fullscreen',
            'insertdatetime contextmenu directionality',
        ],
        toolbar1:  "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link",
        height: 280
    }); //end tinymce

      $("#pubBt, #draftBt").on("click", function (event) {
          event.preventDefault();
          if( $(this).attr("id") == "pubBt") {
              $("input[name='published']").val(true);
          }else {
              $("input[name='published']").val(false);
          }
          if(validateReq()) {
              if(document.getElementById("cover_pic").files[0]) { //upload files if it's there
                  uploadForm();
              } else { //else am just probably updating a post so skip that
                  document.getElementById("postForm").submit();
              }
          }
      });

      $("#cover_pic").on("change", function(){

          var file = document.getElementById("cover_pic").files[0];

          if(!file) { return; }

          if(file.type.indexOf("image") < 0) { //not an image
              swal("Oops", "Only Image files are supported", "error");
              return false;
          }

          if(file.size > 9000000) { //max 9mb
              swal("Oops", "Image File Too Large, max is 9MB", "error");
              return false;
          }


          //this will read the file content once the pix has been loaded
          var reader = new FileReader();

          //after reading it, use the result as the src target
          reader.onload = function(e) {
              $("#img_cover_pic").attr("src", e.target.result);
          }

          reader.readAsDataURL(file);

      });

      $("#img_cover_pic").on("click", function() {
         $("#cover_pic").trigger("click");
      });

    function uploadForm() {
        //first uplaod the cover_pic
        var formData = new FormData();
        formData.append('file', document.getElementById("cover_pic").files[0]);
        formData.append("${_csrf.parameterName}", "${_csrf.token}");
        $.ajax({
            url: "/eyin/files/",
            method: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function(data) {
              $("input[name='coverPic']").val(data);
              console.log("file name " + $("input[name='coverPic'").val());
              //then submit the form
              document.getElementById("postForm").submit();
            },
            error: function (error) {
                swal("error uploading file ");
                console.log("error \n" + JSON.stringify(error));
                return false;
            }
        });
    }

    function validateReq() {

        $("#post").val(tinymce.get("post").getContent());

        if(!$("#post").val() || !$("#author").val() || !$("#title").val()) {
            swal("error", "Missing Parameter: Check to see Author, Title and Post are specified!", "error");
            return false;
        }

        return true;
    }

  }); //end doc.ready


</script>
</@admin>