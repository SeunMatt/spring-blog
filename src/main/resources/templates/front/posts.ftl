<#include "../layouts/front.ftl" />
<@app title="${title}" description="${title}">
<div class="about" style="background-color: #fff;">
    <div class="container">
        <div class="about-main">
            <div class="col-md-8 about-left">
                <h3 class="section-title">${title}</h3>
                <div class="about-tre">
                    <div class="a-1">
                        <#list posts as post>
                            <#if (post?index < 2)>
                                <div class="col-md-6 abt-left">
                                    <a href="/p/${post.id}"><img class="img-responsive" <#if ( (post.coverPic!'')?length > 0)>src="<@asset url='files/${post.coverPic}'/>"<#else>src="<@asset url='admin/images/default-post-picture.png'/>"</#if> alt="" /></a>
                                    <h3><a href="/p/${post.id}">${post.title}</a></h3>
                                    ${post.post?substring(0, 100)}. . .
                                    <label>${post.createdAt?date.@localdatetime}</label>
                                </div>
                            </#if>
                        </#list>
                        <div class="clearfix"></div>
                    </div>
                    <div class="a-1">
                        <div class="row">
                            <div class="col-sm-6">
                                <#list posts as post>
                                    <#if ((post?index % 2) == 0 && post?index >= 2)>
                                    <div class="might-grid">
                                        <div class="grid-might">
                                            <a href="/p/${post.id}"><img class="img-responsive" <#if ( (post.coverPic!'')?length > 0)>src="<@asset url='files/${post.coverPic}'/>"<#else>src="<@asset url='admin/images/default-post-picture.png'/>"</#if> alt="" /></a>
                                        </div>
                                        <div class="might-top">
                                            <h4><a href="/p/${post.id}">${post.title}</a></h4>
                                           ${post.post?substring(0, 100)}. . .
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    </#if>
                                </#list>
                            </div>
                            <div class="col-sm-6">
                                <#list posts as post>
                                    <#if ((post?index % 2) != 0 && post?index >= 2)>
                                        <div class="might-grid">
                                            <div class="grid-might">
                                                <a href="/p/${post.id}"><img class="img-responsive" <#if ( (post.coverPic!'')?length > 0)>src="<@asset url='files/${post.coverPic}'/>"<#else>src="<@asset url='admin/images/default-post-picture.png'/>"</#if> alt="" /></a>
                                            </div>
                                            <div class="might-top">
                                                <h4><a href="/p/${post.id}">${post.title}</a></h4>
                                                ${post.post?substring(0, 100)}. . .
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                    </div>
                    <div class="a-1">
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
                </div>
            </div>
            <div class="col-md-4 about-right">
                <#include "../partials/right-column.ftl" />
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
</@app>