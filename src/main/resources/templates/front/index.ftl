<#include "../layouts/front.ftl"/>
<@app>

<!--main content starts-->
<div class="about" style="background-color: #fff;">
    <div class="container">
        <div class="about-main">
            <div class="col-md-8 about-left">
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
            <div class="col-md-4 about-right heading">
                <div class="abt-2">
                    <h3>CATEGORIES</h3>
                    <ul>
                        <#list categories as category>
                            <li><a href="/posts/${category.category}">${category.category}</a></li>
                        </#list>
                    </ul>
                </div>
                <div class="abt-2">
                    <h3 style="text-transform: none;">Tags</h3>
                    <ul>
                    <#list categories as category>
                        <li><a href="/posts/${category.category}">${category.category}</a></li>
                    </#list>
                    </ul>
                </div>
                <div class="abt-2">
                    <h3>NEWS LETTER</h3>
                    <div class="news">
                        <form>
                            <input type="text" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" />
                            <input type="submit" value="Subscribe">
                        </form>
                    </div>
                </div>
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
            <ul id="newsRoll">
                <li>
                    <div class="might-grid">
                        <div class="might-top">
                            <h4><a href="#">Build Web Applications like a Pro in 6 Simple Steps</a></h4>
                            <p>Nullam non magna lobortis, faucibus erat eu, consequat justo. Suspendisse commodo nibh odio.</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </li>
                <li>
                    <div class="might-grid">
                        <div class="might-top">
                            <h4><a href="#">This is the Beginning of the End</a></h4>
                            <p>Nullam non magna lobortis, faucibus erat eu, consequat justo. Suspendisse commodo nibh odio.</p>
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
                <li>
                    <div class="might-grid">
                        <div class="might-top">
                            <h4><a href="#">This is the Beginning of the End</a></h4>
                            <p>Nullam non magna lobortis, faucibus erat eu, consequat justo. Suspendisse commodo nibh odio.</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </li>
                <li>
                    <a href="#">
                        <div class="banner-1">
                            <img src="/front/images/s-3.jpg" class="img-responsive" alt="">
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <div class="banner-1">
                            <img src="/front/images/s-6.jpg" class="img-responsive" alt="">
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
</script>

</@app>
