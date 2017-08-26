<#include "../layouts/front.ftl"/>
<@app>

<!--main content starts-->
<div class="about" style="background-color: #fff;">
    <div class="container">
        <div class="about-main">
            <div class="col-md-8 about-left">
                <#include "../partials/alerts.ftl"/>
                <div class="about-tre">
                    <div class="a-1">
                         <#list featuredPosts as featured>
                            <div class="col-md-6 abt-left">
                                <a href="/p/${featured.id}"><img <#if ( (featured.author.profilePic!'')?length > 0)>src="<@asset url='files/${featured.author.profilePic}'/>"<#else>src="<@asset url='admin/images/default-post-picture.png'/>"</#if> alt="" /></a>
                                <h3><a href="/p/${featured.id}">${featured.title}</a></h3>
                                <p>${featured.post}</p>
                                <label>${featured.createdAt?date.@localdatetime}</label>
                            </div>
                         </#list>
                        <div class="clearfix"></div>
                    </div>
                    <div class="a-1">
                        <#list superList as maps>
                        <#list maps as key, value>
                        <div class="row">
                            <h3 class="section-title"><a href="/posts/${key}">${key}</a></h3>
                            <div class="col-sm-6">
                                <#list value as post>
                                <#if (post?index % 2 == 0)>
                                <div class="might-grid">
                                    <div class="grid-might">
                                        <a href="/p/${post.id}">
                                            <#if (post.coverPic?length > 0)>
                                            <img src="<@asset url='files/${post.coverPic!""}'/>" class="img-responsive" alt="">
                                            <#else>
                                             <img class="img-responsive" src="<@asset url='admin/images/photo1.png'/>" alt="Image">
                                            </#if>
                                        </a>
                                    </div>
                                    <div class="might-top">
                                        <h4><a href="/p/${post.id}">${post.title}</a></h4>
                                        <p>${post.post?substring(0, 100)}</p>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                </#if>
                                 <#else>
                                    <br>
                                    <h3>Coming Soon! Subscribe to stay tuned!</h3>
                                    <br>
                                </#list>
                            </div>
                            <div class="col-sm-6">
                                <#list value as post>
                                    <#if (post?index % 2 != 0)>
                                        <div class="might-grid">
                                            <div class="grid-might">
                                                <a href="/p/${post.id}">
                                                    <#if (post.coverPic?length > 0)>
                                                        <img src="<@asset url='files/${post.coverPic!""}'/>" class="img-responsive" alt="">
                                                    <#else>
                                                        <img class="img-responsive" src="<@asset url='admin/images/photo1.png'/>" alt="Image">
                                                    </#if>
                                                </a>
                                            </div>
                                            <div class="might-top">
                                                <h4><a href="/p/${post.id}">${post.title}</a></h4>
                                                <p>${post.post?substring(0, 100)}</p>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                        </#list>
                        </#list>
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
<!--main content end-->

<!--slide-starts-->
<div class="slide" style="">
    <div class="container">
        <div class="fle-xsel">
            <h3 class="section-title" style="font-weight: 200;">Around the Web</h3>
            <ul id="newsRoll">
                <li>
                    <div class="might-grid">
                        <div class="might-top">
                            <h4><a href="#">Place your adverts here</a></h4>
                            <p>Wanna advertise with us? reach out to us now <a hre="/contact">contact me</a></p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </li>
                <li>
                    <div class="might-grid">
                        <div class="might-top">
                            <h4><a href="#">Your Featured Posts here</a></h4>
                            <p>Have something interesting coming up? Why not let us write about it? <a hre="/contact">reach out</a> now</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </li>
                <li>
                    <a href="#">
                        <div class="banner-1">
                            <img src="<@asset url='front/images/s-1.jpg' />" class="img-responsive" alt="">
                            <h3></h3>
                        </div>
                    </a>
                </li>
            </ul>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!--slide-end-->

<script type="text/javascript" src="<@asset url = 'front/js/jquery.flexisel.js' />"></script>
<script type="text/javascript">
    $(window).load(function() {

        $("#newsRoll").flexisel({
            visibleItems: 3,
            animationSpeed: 1000,
            autoPlay: true,
            autoPlaySpeed: 3000,
            pauseOnHover: true,
            enableResponsiveBreakpoints: true,
            responsiveBreakpoints: {
                portrait: {
                    changePoint:480,
                    visibleItems: 2
                },
                landscape: {
                    changePoint:640,
                    visibleItems: 3
                },
                tablet: {
                    changePoint:768,
                    visibleItems: 3
                }
            }
        });

    });
    $().ready(function () {
       $("#nav-home").removeClass("active");
       $("#nav-home").addClass("active");
    });
</script>

</@app>
