<#include "../layouts/front_old.ftl"/>
<@app>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

        <div class="row">

            <div class="col-sm-offset-1 col-md-8">

                <#--featured posts-->
                <div class="row">
                    <h1>Featured Posts</h1>
                    <#list featuredPosts as featured>
                    <div class="col-md-4">
                        <!-- Box Comment -->
                        <div class="box box-widget">
                            <div class="box-header with-border">
                                <div class="user-block">
                                    <img class="img-circle" <#if ( (featured.author.profilePic!'')?length > 0)>src="<@asset url='files/${featured.author.profilePic}'/>"<#else>src="<@asset url='admin/images/default-post-user.jpg'/>"</#if> alt="User Image">
                                    <span class="username"><a href="#">${featured.author.name}</a></span>
                                    <span class="description">${featured.createdAt?date.@localdatetime}</span>
                                </div>
                                <!-- /.user-block -->
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <img class="img-responsive pad" src="<@asset url='admin/images/default-post-picture.png'/>" alt="Photo">
                                <h5><a href="/p/${featured.id}">${featured.title}</a></h5>
                                <button type="button" class="btn btn-default btn-xs"><i class="fa fa-share"></i> Share</button>
                                <button type="button" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up"></i> Like</button>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                    </#list>
                </div>

                <#list superList as maps>
                <#list maps as key, value>
                <div class="row">
                        <h1>${key} <a href="/posts/${key}" class="btn btn-flat bg-olive btn-xs">VIEW ALL</a></h1>

                        <div class="col-sm-6">
                            <#list value as post>
                            <#if (post?index % 2 == 0)>
                            <div class="attachment-block clearfix">
                                <#if (post.coverPic?length > 0)>
                                <img class="attachment-img" src="<@asset url='files/${post.coverPic!""}'/>" alt="Image">
                                <#else>
                                 <img class="attachment-img" src="<@asset url='admin/images/photo1.png'/>" alt="Image">
                                </#if>
                                <div class="attachment-pushed">
                                    <h4 class="attachment-heading"><a href="/p/${post.id}">${post.title}</a></h4>

                                    <div class="attachment-text">
                                        ${post.post?substring(0, 100)}... <a href="/p/${post.id}">more</a>
                                    </div>
                                    <!-- /.attachment-text -->
                                </div>
                                <!-- /.attachment-pushed -->
                            </div>
                            </#if>
                            </#list>
                        </div>

                        <div class="col-sm-6">
                            <#list value as post>
                                <#if (post?index % 2 > 0)>
                                    <div class="attachment-block clearfix">
                                        <img class="attachment-img" src="<@asset url='admin/images/photo1.png'/>" alt="Image">

                                        <div class="attachment-pushed">
                                            <h4 class="attachment-heading"><a href="/p/${post.id}">${post.title}</a></h4>

                                            <div class="attachment-text">
                                            ${post.post?substring(0, 100)}... <a href="/p/${post.id}">more</a>
                                            </div>
                                            <!-- /.attachment-text -->
                                        </div>
                                        <!-- /.attachment-pushed -->
                                    </div>
                                </#if>
                            </#list>
                        </div>

                    </div>
                </#list>
                </#list>
            </div>

            <div class="col-md-3">
                <br><br>
                <#include "../partials/most-read-article.ftl" />
                <br><br>
            </div>




        </div>



    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

</@app>