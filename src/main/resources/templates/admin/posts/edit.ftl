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

    <form role="form" action="/eyin/posts" method="POST" id="postForm" enctype="multipart/form-data">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <#if post??> <input type="hidden" name="id" value="${post.id!""}"/>  <#else> </#if>
        <input type="hidden" name="published" value="true" />

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-3">

                <!-- Admin Box -->
                <div class="box box-secondary">
                    <!-- /.box-header -->
                    <div class="box-body box-admin">
                        <strong>CATEGORY</strong>
                        <br>
                        <select class="form-control" id="category" name="category">
                            <#list categories as category>
                                <#if post??>
                                    <option <#if post.category.category == category.category> selected </#if> value="${category.id}">${category.category}</option>
                                <#else>
                                <option value="${category.id}">${category.category}</option>
                                </#if>
                            </#list>
                        </select>
                        <br>
                        <strong>IS FEATURED</strong>
                        <br>
                        <select name="featured" class="form-control">
                            <#if post??>
                                <#if (post.featured == true) >
                                    <option value="1">Yes</option>
                                    <option value="0">No</option>
                                <#else>
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </#if>
                            <#else>
                            <option value="0">No</option>
                            <option value="1">Yes</option>
                            </#if>
                        </select>
                        <br>
                        <button id="previewBt" class="btn btn-primary btn-flat" <#if post??><#else>disabled</#if>>Preview</button>
                        <a id="previewLink" href="/eyin/posts/preview/${(post.id)!""}" target="_blank"></a>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->

                <!-- Tags Box -->
                <div class="box box-secondary">
                    <!-- /.box-header -->
                    <div class="box-body box-admin">
                        <strong>TAGS</strong>
                        <br>
                        <select name="tags" id="tags" class="form-control select2" multiple="multiple" data-placeholder="Add Tags" style="width: 100%;">
                            <#list tags as tag>
                                 <#if post??>
                                     <option <#if post.tags?seq_contains(tag)> selected </#if> value="${tag.id}">${tag}</option>
                                 <#else>
                                      <option value="${tag.id}">${tag}</option>
                                 </#if>
                            </#list>
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
                        <#if post??><h3 class="box-title">Update Post</h3><#else><h3 class="box-title">Compose New Post</h3></#if>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="form-group">
                            <label for="author">Author</label>
                            <input class="form-control" id="author" name="author" placeholder="Author:" <#if post??>value="${post.author.name!user.name}" <#else> value="${user.name}"</#if> disabled required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" id="title" name="title" placeholder="Post Title:" <#if post??>value="${post.title!""}" <#else></#if> required>
                        </div>
                        <div class="form-group">
                         <textarea id="post" name="post" class="form-control" style="resize:vertical; height: 300px" placeholder="Type your post here" required><#if post??>${post.post!""}<#else></#if></textarea>
                        </div>
                        <div class="form-group">
                            <input style="display:none;" type="file" name="file" id="cover_pic">
                            <input type="hidden" name="coverPic" value="<#if post??>${post.coverPic!""}<#else></#if>" />
                            <p class="help-block" id="cover_pic_name">Max. 30MB</p>
                            <br>
                            <div class="small-box">
                                <img class="img-responsive" id="img_cover_pic" src="<#if post??><@asset url='files/${post.coverPic!""}'/><#else></#if>" alt="Cover Picture" />
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
      //Initialize Select2 Elements
      $(".select2").select2();

      tinymce.init({
        selector: "textarea#post",
        plugins: [
            'advlist autolink link image charmap preview',
            'searchreplace wordcount code',
            'insertdatetime contextmenu directionality',
            'codesample'
        ],
        toolbar1:  "undo redo | " +
                    "styleselect |" +
                    " bold italic |" +
                    " alignleft aligncenter alignright alignjustify |" +
                    " bullist numlist outdent indent |" +
                    " link codesample",
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
               document.getElementById("postForm").submit();
          }
      });

      $("#cover_pic").on("change", function(){

          var file = document.getElementById("cover_pic").files[0];

          if(!file) { return; }

          if(file.type.indexOf("image") < 0) { //not an image
              swal("Oops", "Only Image files are supported", "error");
              document.getElementById("cover_pic").files[0] = false;
              $("#img_cover_pic").attr("src", "");
              return false;
          }

          if(file.size > 9000000) { //max 9mb
              swal("Oops", "Image File Too Large, max is 9MB", "error");
              document.getElementById("cover_pic").files[0] = false;
              $("#img_cover_pic").attr("src", "");
              return false;
          }


          //this will read the file content once the pix has been loaded
          var reader = new FileReader();

          //after reading it, use the result as the src target
          reader.onload = function(e) {
              $("#img_cover_pic").attr("src", e.target.result);
          }

          $("input[name='coverPic']").val(file.name);

//          console.log("file name " + $("input[name='coverPic'").val());

          reader.readAsDataURL(file);

      });

      $("#img_cover_pic").on("click", function() {
         $("#cover_pic").trigger("click");
      });

      $("#previewBt").on("click", function(event) {
            event.preventDefault();
            if(!$("input[name='id']").val()) {
                swal("You have to save draft first before you can preview");
                return false;
            }
          $('#previewLink').click(function() {
              this.click();
          }).click();
      });

      function validateReq() {

        $("#post").val(tinymce.get("post").getContent());

        if(!$("#post").val() || !$("#author").val() || !$("#title").val()) {
            swal("error", "Missing Parameter: Check to see Author, Title and Post are specified!", "error");
            return false;
        }

        if($("#post").val().split(" ").length < 150) {
            swal("error", "Article body should be at least 150 words in length", "error");
            return false;
        }

        if(!$("#tags").select2().val()) {
            swal("error", "You have to give article at least one tag", "error");
            return false;
        }

        if(!$("#category").val()) {
            swal("error", "Article must have a Category", "error");
            return false;
        }

        return true;
    }

  }); //end doc.ready


</script>
</@admin>