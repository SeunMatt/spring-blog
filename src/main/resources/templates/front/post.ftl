<#include "../layouts/front.ftl"/>
<@app title="${post.title}" description="${description}">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

        <div class="row">

            <div class="col-md-9">

            <#--image row-->
                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2">
                        <div class="small-box">
                            <img class="img-responsive" id="img_cover_pic" src="<#if (post.coverPic?length > 0)><@asset url='files/${post.coverPic!""}'/><#else><@asset url='admin/images/default-post-picture.png'/></#if>" alt="Cover Picture" />
                        </div>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <h1>${post.title}</h1>
                        <h5><i>Posted by ${post.author.name} on ${post.createdAt?date.@localdatetime}, <span class="fa fa-eye"></span> ${post.views}</i></h5>
                        <small>Tags: Java, Technology</small>
                    </div>
                </div>
                <br>
                <#--row for posts-->
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <div class="postdiv">
                            ${post.post}
                        </div>
                    </div>
                </div>

                <br>

                <div class="row text-center">
                        <h4><i>Share with your friends on:</i></h4>
                        <div id="share"></div>
                        <br>
                        <div id="share1"></div>
                   <br>
                </div>

            </div>

            <div class="col-md-3">
                <!-- PRODUCT LIST -->
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Related Articles</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <ul class="products-list product-list-in-box">
                            <#list relatedPosts as rPost>
                            <li class="item">
                               <a href="/p/${rPost.id}" class="product-title">${rPost.title}</a>
                            </li>
                            <!-- /.item -->
                            </#list>
                        </ul>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-center">
                        <a href="/posts/${post.category.category}" class="uppercase">View All</a>
                    </div>
                    <!-- /.box-footer -->
                </div>
                <!-- /.box -->
                <br><br>

            </div>

        </div>

        <br><br>

        <#--Add Comment Row-->
        <div class="row">
            <div class="col-sm-5">
                <!-- Horizontal Form -->
                <div class="box box-info collapsed-box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Add Comment</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" id="collapseComment" data-widget="collapse"><i class="fa fa-plus"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->
                    <form class="form-horizontal" id="commentForm" action="/comment/add" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" name="postId" value="${post.id}">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">Name</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="name" placeholder="Name" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" name="email" placeholder="Email" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">Comment</label>
                                <div class="col-sm-10">
                                    <textarea rows="5" cols="5" class="form-control" name="comment" placeholder="Comment" required></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="notify"> Notify me, via email, of direct comment reply
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-8">
                                    <div class="g-recaptcha" data-sitekey="6LcpmiAUAAAAACv69eLygOFO3OPoayrMpT2fk_rJ"></div>
                                </div>
                            </div>

                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <button type="submit" class="btn btn-info pull-right">Post Comment</button>
                        </div>
                        <!-- /.box-footer -->
                    </form>
                </div>
                <!-- /.box -->
            </div>
        </div>

        <#--View Comments Row-->
        <div class="row">
            <div class="col-sm-10" id="commentColumn">

            </div>
        </div>


        <#include "../partials/comment-modal.ftl" />

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script>
    $().ready(function() {

        $("#commentForm").on("submit", function (event) {
            event.preventDefault();
            $.ajax({
                 type: "post",
                 url: "/comment/add",
                 data: $("#commentForm").serialize(),
                 beforeSend: function () {
                     displayWait("#commentForm");
                 },
                 success: function (response) {
                     swal("Comment Posted", "Your comment has been posted successfully", "success")
                     cancelWait("#commentForm");
                     $("#commentForm")[0].reset();
                     grecaptcha.reset();
                 },
                 error: function (error) {
                     swal("Oops", "ERROR: " + error.responseText, "error");
                     cancelWait("#commentForm");
                     grecaptcha.reset();
                 }
             });
        });

        $("#replyForm").on("submit", function (event) {
            event.preventDefault();
            $.ajax({
                type: "post",
                url: "/comment/reply",
                data: $("#replyForm").serialize(),
                beforeSend: function () {
                    displayWait("#replyForm");
                },
                success: function (response) {
                    cancelWait("#replyForm");
                    $("#replyForm")[0].reset();
                    $("#replyModal").modal("hide");
                },
                error: function (error) {
                    cancelWait("#replyForm");
                    $("#replymodal").modal("hide");
                    swal("Oops", "ERROR: " + error.responseText, "error");
                }
            });
        });

        $("#share").jsSocials({
            shares: [
                "twitter",
                {   share: "facebook",
                    label: "Share",
                    logo: "fa fa-facebook",
                    hashtags: "blogpost"
                },
                "googleplus"
            ],
            showCount: false,
            shareIn: "popup"
        });

        $("#share1").jsSocials({
            shares: [
                "linkedin",
                "whatsapp",
                {
                    share: "messenger",
                    label: "Messenger",
                    shareIn: "self"
                }
            ],
            showCount: false,
            shareIn: "popup"
        });

        $(document).on("click", ".reply", function(event) {
            event.preventDefault();
            $("input[name='parentCommentId']").val($(this).attr("data-id"));
            $("#replyModal").modal({"static": true});
//            console.log("name: " + $(this).parent().children("span").html());
//            console.log("comment: " + $(this).parent().children("p").html());
            $("#replyText").html($(this).parent().children("span").html() + "<br>" + $(this).parent().children("p").html());
        });

        $(document).on("click", ".vreply", function(event) {
            event.preventDefault();
            displayWait("#comment_" + $(this).attr("data-id"));
            loadCommentReplies($("input[name='postId']").val(), $(this).attr("data-id"));
        });

        loadComments($("input[name='postId']").val());


    });


</script>
</@app>