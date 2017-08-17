<#macro app title="" body="" description="">
<!DOCTYPE HTML>
<html>
<head>
    <title>Smatt Blog</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta content="width=device-width, initial-scale=1, user-scalable=yes" name="viewport" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="blog, java, seun matt, smatt">
    <meta name="robots" content="index,follow" />


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
                    <li><a href="#"><i class="fb"> </i></a></li>
                    <li><a href="#"><i class="twt"> </i></a></li>
                    <li>
                        <div class="search2">
                            <form>
                                <input type="text" value="Search.." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search..';}">
                                <input type="submit" value="">
                            </form>
                        </div></li>
                    <div class="clearfix"></div>
                </ul>

            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="head-nav">
            <span class="menu"> </span>
            <ul class="cl-effect-15">
                <li class="active"><a href="index.html">HOME</a></li>
                <li><a href="/about" data-hover="ABOUT">ABOUT</a></li>
                <li><a href="/contact" data-hover="CONTACT">CONTACT</a></li>
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
<div class="footer">
    <div class="container">
        <div class="col-md-3 copy">
            <div class="top1">
                <i class="fa fa-home"></i>
            </div>
            <div class="top2">
                <h6>Copyrights © 2017 Spring Blog</h6>
                <p>All rights reserved</p>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="col-md-3 copy">
            <div class="top1">
                <i class="fa fa-link"></i>
            </div>
            <div class="top2">
                <h6>Open Source </h6>
                <p><a href="https://github.com/seunmatt/spring-blog" target="_blank">Fork me on github</a></p>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="col-md-3 copy">
            <div class="top1">
                <i class="fa fa-list-ul"></i>
            </div>
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
    </div>
</div>
<!-- footer -->

<script src="<@asset url = 'admin/js/bootstrap/bootstrap.min.js' /> "></script>
<script src="<@asset url = 'admin/js/jquery.blockUI.js' />"></script>
<script src="<@asset url = 'admin/js/sweetalert.min.js' />"></script>
</body>
</html>
</#macro>