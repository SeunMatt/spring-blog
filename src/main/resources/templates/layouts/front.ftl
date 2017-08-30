<#macro app title="" description="">
<!DOCTYPE HTML>
<html>
<head>
    <title><#if ((title?length) > 0) >Smatt Blog - ${title}<#else>Smatt Blog - The Journal of Seun Matt and his thoughts</#if></title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta content="width=device-width, initial-scale=1, user-scalable=yes" name="viewport" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="blog, java, seun matt, smatt">
    <meta name="robots" content="index,follow" />
    <meta name="description" content="<#if ((description?length > 0))>${description}<#else>This is the personal blog of Seun Matt built with Java</#if>" />

    <!-- twitter card -->
    <meta name="twitter:card" content="summary" />
    <meta name="twitter:site" content="@SeunMatt2" />
    <meta name="twitter:creator" content="@SeunMatt2" />
    <meta name="twitter:title" content="<#if ((title?length) > 0) >${title} | Smatt Blog<#else>Smatt Blog - The Journal of Seun Matt and his thoughts</#if>" />
    <meta name="twitter:description" content="<#if ((description?length > 0))>${description}<#else>This is the personal blog of Seun Matt built with Java</#if>" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:image:alt" content="logo" />

    <!-- Schema.org markup for Google+ -->
    <meta itemprop="name" content="<#if ((title?length) > 0) >${title} | Smatt Blog<#else>Smatt Blog - The Journal of Seun Matt and his thoughts</#if>" />
    <meta itemprop="description" content="<#if ((description?length > 0))>${description}<#else>This is the personal blog of Seun Matt built with Java</#if>" />
    <meta itemprop="image" content="">

    <!-- Open Graph data -->
    <meta property="og:title" content="<#if ((title?length) > 0) >${title} | Smatt Blog<#else>Smatt Blog - The Journal of Seun Matt and his thoughts</#if>" />
    <meta property="og:type" content="article" />
    <meta property="og:url" content="https://smattblog.herokuapp.com/" />
    <meta property="og:image" content=""/>
    <meta property="og:description" content="<#if ((description?length > 0))>${description}<#else>This is the personal blog of Seun Matt built with Java</#if>" />
    <meta property="og:site_name" content="Smatt Blog" />


    <link rel="canonical" href="https://smattblog.herokuapp.com/"/>


    <link rel="stylesheet" href="<@asset url = 'front/css/bootstrap.css' />">
    <link rel="stylesheet" href="<@asset url = 'font-awesome-4.7.0/css/font-awesome.min.css' />">
    <link href="<@asset url = 'front/css/coffe-style.css' />" rel="stylesheet" type="text/css" media="all" />
    <link href="<@asset url = 'front/css/animus-style.css' />" rel="stylesheet" type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" href="<@asset url = 'front/plugins/jssocials-1.4.0/jssocials.css' />" />
    <link rel="stylesheet" type="text/css" href="<@asset url = 'front/plugins/jssocials-1.4.0/jssocials-theme-flat.css' />" />
    <link rel="stylesheet" type="text/css" href="<@asset url = 'front/css/prism.css' />" />
    <link rel="stylesheet" type="text/css" href="<@asset url = 'front/css/freelancer.css' />" />
    <link rel="stylesheet" href="<@asset url = 'admin/css/sweet-alert-animations.min.css' />">
    <link rel="stylesheet" href="<@asset url = 'admin/css/sweetalert.css' />">


    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>

    <#--<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>-->
    <script src="<@asset url = 'admin/js/jquery/jquery-2.2.3.min.js' />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <#include "../partials/google-analytics.ftl" />
    <script>
        var csrf = "${_csrf.token}";
    </script>
</head>
<body>
<!-- header -->
<div class="banner">
    <div class="container">
        <div class="header">
            <div class="logo">
                <a href="/">
                    <img src="<@asset url='front/images/mylogo.jpg' />" class="img-responsive img-circle" alt="" />
                    <span>Spring Blog</span>
                </a>
            </div>
            <div class="header-right">
                <ul>
                    <li><a target="_blank" href="https://facebook.com/seun.matt"><i class="fb"> </i></a></li>
                    <li><a target="_blank" href="https://twitter.com/seunmatt2"><i class="twt"> </i></a></li>
                    <li>
                        <div class="search2">
                            <form action="#" id="searchForm">
                                <input type="text" id="searchBox" value="Search.." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search..';}">
                                <input type="submit" value="">
                            </form>
                        </div>
                        <script>
                            $(function () {
                                $("#searchForm").on("submit", function (event) {
                                    event.preventDefault();
                                    window.location.href = "/search/" + $("#searchBox").val();
                                });
                            });
                        </script>
                    </li>
                    <div class="clearfix"></div>
                </ul>

            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="head-nav">
            <span class="menu"> </span>
            <ul class="cl-effect-15">
                <li id="nav-home"><a href="/">HOME</a></li>
                <li id="nav-about"><a href="/about" data-hover="ABOUT">ABOUT</a></li>
                <li id="nav-contact"><a href="/contact" data-hover="CONTACT">CONTACT</a></li>
                <div class="clearfix"> </div>
            </ul>
        </div>

        <!-- script-for-nav -->
        <script>
            $( "span.menu" ).click(function() {
                $( ".head-nav ul" ).slideToggle(300, function() {
                    // Animation complete.
                });
            });
        </script>
        <!-- script-for-nav -->
    </div>
</div>
<!-- header -->

<#nested />

<!-- footer -->
<div class="footer" style="padding: 50px 0px;">
    <div class="container">
        <div class="row">
            <div class="col-md-3 copy">
            <div class="top2">
                <h6>Copyrights © 2017 Spring Blog</h6>
                <p>All rights reserved</p>
                <p><a href="https://github.com/seunmatt/spring-blog" target="_blank">Fork me on github</a></p>
            </div>
            <div class="clearfix"> </div>
        </div>
            <div class="col-md-4 copy">
            <div class="top2">
                <h6>Categories</h6>
                <ul>
                    <#list categories as category>
                        <li><a href="/posts/${category.category}">${category.category}</a></li>
                    </#list>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
            <div class="col-md-5 copy">
            <div class="top2">
                <div class="news">
                    <h6>Newsletter</h6>
                    <form method="post" action="/newsletter/subscribe">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="email" name="email" id="email" placeholder="example@gmail.com" required/>
                        <input style="float:left;" type="submit" value="Subscribe">
                    </form>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        </div>
    </div>
</div>
<!-- footer -->

<script src="<@asset url = 'admin/js/bootstrap/bootstrap.min.js' /> "></script>
<script src="<@asset url = 'admin/js/jquery.blockUI.js' />"></script>
<script src="<@asset url = 'admin/js/sweetalert.min.js' />"></script>
</body>
</html>
</#macro>