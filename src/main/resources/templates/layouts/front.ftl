<#macro app title="" body="" description="">
<#--make the asset helper available-->
<#assign asset = "com.smatt.addons.AssetDirective"?new()>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Smatt Blog <#if ((title?length) > 0) > | ${title}</#if></title>
    <meta content="width=device-width, initial-scale=1, user-scalable=yes" name="viewport" />
    <#--<meta description="<#if ((description?length > 0))>${description}<#else>This is the personal blog of Seun Matt built with Java</#if>" />-->

    <meta name="keywords" content="blog, java, seun matt, smatt">
    <meta name="robots" content="index,follow" />

    <!-- twitter card -->
    <meta name="twitter:card" content="summary" />
    <meta name="twitter:site" content="@SeunMatt2" />
    <meta name="twitter:creator" content="@SeunMatt2" />
    <meta name="twitter:title" content="<#if ((title?length) > 0) >${title}<#else>Smatt Blog - The Journal of Seun Matt and his thoughts</#if>" />
    <meta name="twitter:description" content="<#if ((description?length > 0))>${description}<#else>This is the personal blog of Seun Matt built with Java</#if>" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:image:alt" content="logo" />

    <!-- Schema.org markup for Google+ -->
    <meta itemprop="name" content="<#if ((title?length) > 0) >${title}<#else>Smatt Blog - The Journal of Seun Matt and his thoughts</#if>" />
    <meta itemprop="description" content="<#if ((description?length > 0))>${description}<#else>This is the personal blog of Seun Matt built with Java</#if>" />
    <meta itemprop="image" content="">

    <!-- Open Graph data -->
    <meta property="og:title" content="<#if ((title?length) > 0) >${title}<#else>Smatt Blog - The Journal of Seun Matt and his thoughts</#if>" />
    <meta property="og:type" content="article" />
    <meta property="og:url" content="https://smattblog.herokuapp.com/" />
    <meta property="og:image" content=""/>
    <meta property="og:description" content="<#if ((description?length > 0))>${description}<#else>This is the personal blog of Seun Matt built with Java</#if>" />
    <meta property="og:site_name" content="Smatt Blog" />


    <link rel="canonical" href="https://smattblog.herokuapp.com/"/>


    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<@asset url = 'admin/css/bootstrap/bootstrap.min.css' />">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<@asset url = 'font-awesome-4.7.0/css/font-awesome.min.css' />">

    <link rel="stylesheet" href="<@asset url = 'admin/css/AdminLTE.min.css' />">

    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<@asset url = 'admin/css/skins/_all-skins.css' />">

    <link rel="stylesheet" href="<@asset url = 'admin/css/sweet-alert-animations.min.css' />">
    <link rel="stylesheet" href="<@asset url = 'admin/css/sweetalert.css' />">

    <!-- footer -->
    <link href="<@asset url='front/css/mystyle.css' />" rel="stylesheet">


<#--js social-->
    <link rel="stylesheet" type="text/css" href="/front/plugins/jssocials-1.4.0/jssocials.css" />
    <link rel="stylesheet" type="text/css" href="/front/plugins/jssocials-1.4.0/jssocials-theme-flat.css" />

    <link rel="stylesheet" type="text/css" href="/front/css/prism.css" />

    <!-- jQuery 2.2.3 -->
    <script src="<@asset url = 'admin/js/jquery/jquery-2.2.3.min.js' />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <#include "../partials/google-analytics.ftl" />
</head>
<body class="hold-transition skin-blue-light sidebar-collapse layout-top-nav">
<!-- Site wrapper -->

<#--top header-->
<div class="container-fluid top-most">
    <div class="row">
        <div class="col-sm-3">

        </div>
        <div class="col-sm-5 text-center">
            <h1><a href="/" style="text-decoration: none;">Spring Blog</a></h1>
            <h4>The Journal of Seun Matt and his thoughts</h4>
        </div>
        <div class="col-sm-4">

        </div>
    </div>
</div>

<div class="wrapper">

    <!-- =============================================== -->

    <header class="main-header">
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">

            <!-- Sidebar toggle button-->
            <#--<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">-->
                <#--<span class="sr-only">Toggle navigation</span>-->
                <#--<span class="icon-bar"></span>-->
                <#--<span class="icon-bar"></span>-->
                <#--<span class="icon-bar"></span>-->
            <#--</a>-->

            <div class="navbar-header">
                <a class="navbar-brand" href="/"><span class="fa fa-home"></span></a>
                <div class="input-group">
                    <form role="form" action="#" id="searchForm">
                    <div class="search">
                            <span class="fa fa-search"></span>
                            <input placeholder="keywords" id="keywords" required>
                    </div>
                    </form>
                </div>
            </div>


            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                </ul>
            </div>


        </nav>
    </header>

    <!-- =============================================== -->

    <#include "../partials/alerts.ftl"/>

    <!-- Content Wrapper. Contains page content -->
     <#nested >
    <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->

<div class="footer">
<#--footer-->
    <div class="footer-top-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <div class="footer-about-us">
                        <h2><a href="/" style="color:inherit;">Spring Blog</a></h2>
                        <p>
                            Spring Blog is a complete blog application developed with Java Spring Framework. It is feature rich and ready to use
                            just deploy to your server and you are good to go.
                        </p>
                        <p>Author: <a href="https://twitter.com/seunmatt2">Seun Matt</a></p>
                        <p>Github Repo: <a href="https://github.com/SeunMatt/spring-blog">Complete Source Code</a></p>
                        <p><a href="/contact">Contact Us</a></p>

                        <div class="footer-social">
                            <a href="https://www.facebook.com/seun.matt" target="_blank"><i class="fa fa-facebook"></i></a>
                            <a href="https://twitter.com/SeunMatt2" target="_blank"><i class="fa fa-twitter"></i></a>
                            <a href="https://www.linkedin.com/in/seun-matt-06351955/" target="_blank"><i class="fa fa-linkedin"></i></a>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6">
                    <div class="footer-menu">
                        <h2 class="footer-wid-title">Categories</h2>
                        <ul>
                            <#list categories as category>
                                <li><a href="/posts/${category.category}">${category.category}</a></li>
                            </#list>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6">
                    <div class="footer-menu">
                        <h2 class="footer-wid-title">Tags </h2>
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


<!-- Bootstrap 3.3.6 -->
<script src="<@asset url = 'admin/js/bootstrap/bootstrap.min.js' /> "></script>
<!-- SlimScroll -->
<script src="<@asset url = 'admin/js//slimscroll/jquery.slimscroll.min.js' />"></script>
<!-- FastClick -->
<script src="<@asset url = 'admin/js/fastclick/fastclick.min.js' />"></script>
<script src="<@asset url = 'admin/js/jquery.blockUI.js' />"></script>

<script src="<@asset url = 'admin/js/sweetalert.min.js' />"></script>
<!-- AdminLTE App -->
<script src="<@asset url = 'admin/js/app.min.js' />"></script>

<script src="/front/plugins/jssocials-1.4.0/jssocials.js"></script>
<script src="/front/js/prism.js"></script>

<script src="<@asset url = 'admin/js/utility.js' />"></script>


</body>
</html>

</#macro>

