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
                            <input class="form-control" name="title" placeholder="Post Title:" <#if post??>value="${post.title}" <#else> </#if> >
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="author" placeholder="Author:" <#if post??>value="${post.author}" <#else> </#if>>
                        </div>
                        <div class="form-group">
                         <textarea name="post" id="compose-textarea" class="form-control" style="resize:vertical; height: 300px" placeholder="Type your post here"><#if post??>${post.post}<#else></#if></textarea>
                        </div>
                        <div class="form-group">
                            <div class="btn btn-default btn-file">
                                <i class="fa fa-paperclip"></i> Upload Cover Picture
                                <input type="file" name="cover_pic">
                            </div>
                            <p class="help-block" id="cover_pic_name">Max. 30MB</p>
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
</div>

<script>
    $("#pubBt, #draftBt").on("click", function (event) {
       event.preventDefault();
       if( $(this).attr("id") == "pubBt") {
           $("input[name='published']").val(true);
       }else {
           $("input[name='published']").val(false);
       }
       document.getElementById("postForm").submit();
    });
</script>
</@admin>