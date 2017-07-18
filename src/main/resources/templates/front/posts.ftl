<#include "../layouts/front.ftl" />
<@app title="${title} and Articles" description="${title} and Articles">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

        <div class="row">

            <div class="col-md-offset-1 col-md-8">

                <div class="row">
                    <div class="col-md-6 col-md-offset-3 text-center">
                        <h1>${title}</h1>
                    </div>
                </div>

                <br><br>

                <div class="row">

                    <div class="col-sm-6">
                        <ul class="timeline">
                            <#list posts as post>
                            <#if (post?index % 2) == 0>
                            <!-- timeline item -->
                            <li>
                                <i class="fa fa-comments bg-blue"></i>

                                <div class="timeline-item">
                                    <span class="time"><i class="fa fa-clock-o"></i> ${post.createdAt?date.@localdatetime}</span>

                                    <h3 class="timeline-header"><a href="/p/${post.id}">${post.title}</a></h3>

                                    <div class="timeline-body">
                                    ${post.post?substring(0, 150)} ...
                                    </div>
                                    <div class="timeline-footer">
                                        <a href="/p/${post.id}" class="btn btn-primary btn-xs btn-flat">Read more</a>
                                    </div>
                                </div>
                            </li>
                            <!-- END timeline item -->
                            </#if>
                            </#list>
                        </ul>
                    </div>

                    <div class="col-sm-6">
                        <ul class="timeline">
                            <#list posts as post>
                                <#if (post?index % 2) != 0>
                                    <!-- timeline item -->
                                    <li>
                                        <i class="fa  fa-newspaper-o bg-green"></i>

                                        <div class="timeline-item">
                                            <span class="time"><i class="fa fa-clock-o"></i> ${post.createdAt?date.@localdatetime}</span>

                                            <h3 class="timeline-header"><a href="/p/${post.id}">${post.title}</a></h3>

                                            <div class="timeline-body">
                                            ${post.post?substring(0, 150)} ...
                                            </div>
                                            <div class="timeline-footer">
                                                <a href="/p/${post.id}" class="btn btn-primary btn-xs btn-flat">Read more</a>
                                            </div>
                                        </div>
                                    </li>
                                    <!-- END timeline item -->
                                </#if>
                            </#list>
                        </ul>
                    </div>

                </div>

                <br>

                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <#if (totalPages > 1)>
                        <ul class="list-inline text-center">
                            <#if ((prevLink!'')?length > 0)>
                                <li><a class="btn btn-md btn-flat bg-purple" href="${prevLink}"> <span class="fa fa-arrow-left"></span> Previous</a></li>
                            </#if>

                            <li><span class="h1">${currentPage}</span> / <span class="h3">${totalPages}</span></li>

                            <#if ( (nextLink!'')?length > 0)>
                                <li><a class="btn btn-md btn-flat bg-navy" href="${nextLink}">Next <span class="fa fa-arrow-right"></span></a></li>
                            </#if>
                        </ul>
                        </#if>
                    </div>
                </div>

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
