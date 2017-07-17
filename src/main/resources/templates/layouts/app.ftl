<#macro app title = "">
<!DOCTYPE html>
<html lang="en">

<head>

<#--make the asset helper available-->
<#assign asset = "com.smatt.addons.AssetDirective"?new()>

    <title>Smatt Blog</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="keywords" content="blog, java, seun matt, smatt">
    <meta name="robots" content="index,follow" />

    <!-- twitter card -->
    <meta name="twitter:card" content="summary" />
    <meta name="twitter:site" content="@SeunMatt2" />
    <meta name="twitter:creator" content="@SeunMatt2" />
    <meta name="twitter:title" content="Smatt Blog - The Journal of Seun Matt and his thoughts" />
    <meta name="twitter:description" content="This is the personal blog of Seun Matt built with Java" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:image:alt" content="logo" />

    <!-- Schema.org markup for Google+ -->
    <meta itemprop="name" content="Smatt Blog - The Journal of Seun Matt and his thoughts">
    <meta itemprop="description" content="This is the personal blog of Seun Matt built with Java">
    <meta itemprop="image" content="">

    <!-- Open Graph data -->
    <meta property="og:title" content="Smatt Blog - The Journal of Seun Matt and his thoughts" />
    <meta property="og:type" content="article" />
    <meta property="og:url" content="https://smattblog.herokuapp.com/" />
    <meta property="og:image" content=""/>
    <meta property="og:description" content="This is the personal blog of Seun Matt built with Java" />
    <meta property="og:site_name" content="Smatt Blog" />


    <link rel="canonical" href="https://smattblog.herokuapp.com/"/>


    <!-- Bootstrap Core CSS -->
    <link href="<@asset url='front/vendor/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">

    <#--for cards-->
    <link href="<@asset url='front/css/blog-post-card.css' />" rel="stylesheet">

    <#--for footer-->
    <link href="<@asset url='front/css/ustora-footer.css' />" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="<@asset url='front/css/clean-blog.css' />" rel="stylesheet">


    <!-- Custom Fonts -->
    <link href="<@asset url='font-awesome-4.7.0/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


    <!-- jQuery -->
    <script src="<@asset url='front/vendor/jquery/jquery.min.js' />"></script>


</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                Menu <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="/">Smatt Blog</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<#--display contents from other templates here-->
<#nested>


<!-- Footer -->
<#--<#include '../partials/footer.ftl' />-->

<!-- Bootstrap Core JavaScript -->
<script src="<@asset url='front/vendor/bootstrap/js/bootstrap.min.js' />"></script>

<!-- Contact Form JavaScript -->
<script src="<@asset url='front/js/jqBootstrapValidation.js' />"></script>
<script src="<@asset url='front/js/contact_me.js' />"></script>

<!-- Theme JavaScript -->
<script src="<@asset url='front/js/clean-blog.min.js' />"></script>

</body>
</html>
</#macro>