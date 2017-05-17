<#include "../layouts/admin.ftl"/>
<@admin title="Dashboard">
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Dashboard
            </h1>
            <br>
            <#include "../partials/alerts.ftl" />
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Small boxes (Stat box) -->
            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-aqua">
                        <div class="inner">
                            <h3>${userPosts}</h3>
                            <p>Posts</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-book"></i>
                        </div>
                        <a href="/eyin/posts" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-red">
                        <div class="inner">
                            <h3>${userPostViewsCount!0}</h3>

                            <p>Total Views</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-eye"></i>
                        </div>
                        <a href="#" class="small-box-footer"> <i class="fa fa-eye"></i></a>
                    </div>
                </div>
            </div>
            <!-- /.row -->

        <#if (user.role == "SUPER_ADMIN" || user.role == "EDITOR")>
            <!-- Small boxes (Stat box) -->
            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3>${postCount!0}</h3>

                            <p>Total Posts</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-book"></i>
                        </div>
                        <a href="/eyin/posts" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-aqua">
                        <div class="inner">
                            <h3>${categoryCount!0}</h3>

                            <p>Categories</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-th-list"></i>
                        </div>
                        <a href="/eyin/categories" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-yellow">
                        <div class="inner">
                            <h3>${sectionCount!0}</h3>

                            <p>Sections</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-th-large"></i>
                        </div>
                        <a href="/eyin/sections" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-red">
                        <div class="inner">
                            <h3>${viewCount!0}</h3>

                            <p>Total Views</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-eye"></i>
                        </div>
                        <a href="#" class="small-box-footer"> <i class="fa fa-eye"></i></a>
                    </div>
                </div>
                <!-- ./col -->
            </div>
            <!-- /.row -->
        </#if>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
 </@admin>