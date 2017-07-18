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



    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script>
    $().ready(function() {

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

    });
</script>
</@app>