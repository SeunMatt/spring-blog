<#include "../layouts/app.ftl"/>
<@app>

<style>
    .inner-nav-container {
       margin-bottom: 0px;
    }
    .inner-nav-row {

    }
    .inner-nav {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
    }

    .inner-nav li {
        float: left;
    }

    .inner-nav li a {
        display: block;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        text-transform: uppercase;
        font-size: 15px;
        letter-spacing: 1px;
        color: #FFFFFF;
        font-weight: 800;
        font-family: 'Open Sans','Helvetica Neue',Helvetica,Arial,sans-serif;
    }

    .inner-nav li a:hover:not(.active) {
        color: rgba(255,255,255,.8)
    }

    .inner-nav li a.active {
        border-bottom: 3px solid #ffffff;
        padding-bottom: 1px;
        padding-left: 0px;
        padding-right: 0px;
    }

</style>
<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('<@asset url='front/img/home-bg.jpg'/>'); margin-bottom: 25px;">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="site-heading">
                    <h1>Seun Matt Online</h1>
                    <hr class="small">
                    <span class="subheading">This is where I journal my thoughts and journey into the ether</span>
                </div>
            </div>
        </div>

        <div class="row inner-nav-row">
            <div class="col-sm-10 col-sm-offset-1">
                <ul class="inner-nav">
                    <li><a class="active" href="/posts/latest">Latest</a></li>
                    <li><a class="" href="#">Trending</a></li>
                    <li><a href="#contact">Categories</a></li>
                    <li><a class="" href="#about">Sections</a></li>
                    <li><a class="" href="#about">Announcements</a></li>
                </ul>
            </div>
        </div>

    </div>
</header>

<!-- Main Content -->
<div class="container">

    <#--latest-->
    <div class="row">
        <h2 class="post-title">Technology</h2>
        <hr>
        <div class="row">
            <#--first column 2nd row-->
            <div class="col-sm-4">
                <#list latestPostsLeft as lf>
                    <div class="row">
                        <div class="wrapper-no-image">
                            <div class="card">
                                <div class="card__content card__padding">
                                    <div class="card__meta">
                                        <a href="#">${lf.category.category!""}</a>
                                        <time>${lf.createdAt?date.@localdatetime}</time>
                                    </div>
                                    <article class="card__article">
                                        <h5><a href="/p/${lf.id}">${lf.title}</a></h5>
                                        <p>${lf.post?substring(0, 120)} . . .</p>
                                    </article>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                </#list>
            </div>

                <#--second column 2nd row-->

            <div class="col-sm-4 divider-right">
                <#list latestPosts as latestPost>
                    <#if (latestPost?index % 2) == 0>
                        <article class="card__article card__article_padding">
                            <h5><a href="/p/${latestPost.id}">${latestPost.title}</a></h5>
                            <p>${latestPost.post?substring(0, 100)} . . .</p>
                        </article>
                    </#if>
                </#list>
            </div>

                <#--third colunmn 2nd row-->
            <div class="col-sm-4">
                <#list latestPosts as latestPost>
                    <#if (latestPost?index % 2) != 0>
                        <article class="card__article card__article_padding">
                            <h5><a href="/p/${latestPost.id}">${latestPost.title}</a></h5>
                            <p>${latestPost.post?substring(0, 100)} . . .</p>
                        </article>
                    </#if>
                </#list>
            </div>
        </div>
    </div>

    <#--featured-->
    <div class="row">
        <h2 class="post-title">Featured Posts</h2>
        <hr>
        <#list featuredPosts as featured>
        <div class="col-sm-4">
            <div class="wrapper">

                <div class="card radius">
                    <div class="card__image border-tlr-radius">
                    <#--<img src="http://lorempixel.com/400/200/sports/" alt="image" class="border-tlr-radius">-->
                        <img <#if (featured.coverPic?length > 0)>src="<@asset url='files/${featured.coverPic}'/>"<#else>src="<@asset url='front/img/post-sample-image.jpg'/>"</#if> alt="image" class="border-tlr-radius">
                    </div>

                    <div class="card__content card__padding">
                        <div class="card__share">
                            <div class="card__social">
                                <a class="share-icon facebook" href="#"><span class="fa fa-facebook"></span></a>
                                <a class="share-icon twitter" href="#"><span class="fa fa-twitter"></span></a>
                                <a class="share-icon googleplus" href="#"><span class="fa fa-google-plus"></span></a>
                            </div>

                            <a id="share" class="share-toggle share-icon" href="#"></a>
                        </div>

                        <div class="card__meta">
                            <a href="/p/${featured.id}">${featured.category.category}</a>
                            <time>${featured.createdAt?date.@localdatetime}</time>
                        </div>

                        <article class="card__article">
                            <h5><a href="#">${featured.title}</a></h5>
                            <p>${featured.post?substring(0, 100)} ...</p>
                        </article>
                    </div>

                    <div class="card__action">
                        <div class="card__author">
                            <div class="card__author-content">
                                By <a href="#">${featured.author.name}</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        </#list>
    </div>

    <#--trending posts-->
    <div class="row">
        <h2 class="post-title">Most Read Articles</h2>
        <hr>
        <div class="col-sm-4">
            <#list trendingPosts as trending>
                <#if (trending?index % 2) == 0>
                    <article class="card__article card__article_padding">
                        <h5><a href="/p/${trending.id}">${trending.title}</a></h5>
                        <p>${trending.post?substring(0, 100)} . . .</p>
                    </article>
                </#if>
            </#list>
        </div>
        <div class="col-sm-4">
            <#list trendingPosts as trending>
                <#if (trending?index % 2 > 0)>
                    <article class="card__article card__article_padding">
                        <h5><a href="/p/${trending.id}">${trending.title}</a></h5>
                        <p>${trending.post?substring(0, 100)} . . .</p>
                    </article>
                </#if>
            </#list>
        </div>
        <div class="col-sm-4">
            <h2 class="post-title">Announcements <span class="fa fa-star"></span> </h2>
            <#list announcements as announcement>
                <article class="card__article">
                    <h5><a href="/p/${announcement.id}">${announcement.title}</a></h5>
                    <p>${announcement.post?substring(0, 250)} ....</p>
                </article>
            </#list>
        </div>
    </div>

</div>

<div class="footer">
    <#--footer-->
    <div class="footer-top-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <div class="footer-about-us">
                            <h2>Spring Blog</h2>
                            <p>
                                Spring Blog is a complete blog application developed with Java Spring Framework. It is feature rich and ready to use
                                just deploy to your server and you are good to go.
                            </p>
                            <p>Author: <a href="https://twitter.com/seunmatt2">Seun Matt</a></p>
                            <p>Github Repo: <a href="https://github.com/SeunMatt/spring-blog">Complete Source Code</a></p>
                            <p><a href="/contact">Contact Us</a></p>

                            <div class="footer-social">
                                <a href="#" target="_blank"><i class="fa fa-facebook"></i></a>
                                <a href="#" target="_blank"><i class="fa fa-twitter"></i></a>
                                <a href="#" target="_blank"><i class="fa fa-linkedin"></i></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="footer-menu">
                            <h2 class="footer-wid-title">Categories</h2>
                            <ul>
                                <li><a href="/posts/technology">Technology</a></li>
                                <li><a href="#">Business</a></li>
                                <li><a href="#">Life</a></li>
                                <li><a href="#">Poems</a></li>
                                <li><a href="#">Guest Posts</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="footer-menu">
                            <h2 class="footer-wid-title">Sections </h2>
                            <ul>
                                <li><a href="#">Technology</a></li>
                                <li><a href="#">Business</a></li>
                                <li><a href="#">Politics</a></li>
                            </ul>

                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="footer-newsletter">
                            <h2 class="footer-wid-title">Newsletter</h2>
                            <p>Sign up to our newsletter and never miss any updates and latest posts again!</p>
                            <div class="newsletter-form">
                                <form action="#">
                                    <input type="email" placeholder="Type your email">
                                    <input type="submit" value="Subscribe">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End footer top area -->

</div>

<script>
    $(document).ready(function($) {

        $('.card__share > a').on('click', function(e){
            e.preventDefault() // prevent default action - hash doesn't appear in url
            $(this).parent().find( 'div' ).toggleClass( 'card__social--active' );
            $(this).toggleClass('share-expanded');
        });

    });
</script>
</@app>