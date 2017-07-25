<#macro admin title="" body="">
<#--make the asset helper available-->
<#assign asset = "com.smatt.addons.AssetDirective"?new()>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="robots" content="noindex,nofollow" />
    <title>Smatt Blog <#if ((title?length) > 0) > | ${title}</#if></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<@asset url = 'admin/css/bootstrap/bootstrap.min.css' />">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<@asset url = 'font-awesome-4.7.0/css/font-awesome.min.css' />">

    <link rel="stylesheet" href="<@asset url = 'admin/css/AdminLTE.min.css' />">
    <!-- Theme style -->
    <link rel="stylesheet" href="<@asset url = 'admin/css/blockui.min.css' />">
    <link rel="stylesheet" href="<@asset url = 'admin/css/sweet-alert-animations.min.css' />">
    <link rel="stylesheet" href="<@asset url = 'admin/css/sweetalert.css' />">
    <link rel="stylesheet" type="text/css" href="/front/css/prism.css" />

    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<@asset url = 'admin/css/skins/_all-skins.css' />">

    <!-- jQuery 2.2.3 -->
    <script src="<@asset url = 'admin/js/jquery/jquery-2.2.3.min.js' />"></script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="/" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>S</b>M</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Smatt</b> Blog</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img <#if (user.profilePic??)>  src='/files/${user.profilePic}' <#else> src="<@asset url='admin/images/user.png'/>" </#if> class="user-image" alt="User Image">
                            <span class="hidden-xs">${user.username!'Guest'}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img <#if (user.profilePic??)>  src='/files/${user.profilePic}' <#else> src="<@asset url='admin/images/user.png'/>" </#if> class="img-circle" alt="User Image">
                                <p>
                                    ${user.username ! 'Hacker'}
                                    <small></small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="row">
                                    <div class="col-xs-4 text-center">
                                        <a href="#">${(user.role)!""}</a>
                                    </div>
                                </div>
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="/eyin/users/profile" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="/logout" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img <#if (user.profilePic??)>  src='/files/${user.profilePic}' <#else> src="<@asset url='admin/images/user.png'/>" </#if> class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${user.username!'Guest'}</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>

            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">MAIN NAVIGATION</li>
                <li class="<#if dashboardMenu??>active<#else></#if>">
                    <a href="/eyin">
                        <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                    </a>
                </li>
                <li class="<#if postMenu??>active<#else></#if>">
                    <a href="/eyin/posts">
                        <i class="fa fa-book"></i> <span>Posts</span>
                    </a>
                </li>
                <#if user.role == "SUPER_ADMIN">
                <li class="<#if categoryMenu??>active<#else></#if>">
                    <a href="/eyin/categories">
                        <i class="fa fa-th-list"></i> <span>Categories</span>
                    </a>
                </li>
                <li class="<#if sectionMenu??>active<#else></#if>">
                    <a href="/eyin/sections">
                        <i class="fa fa-th-large"></i> <span>Sections</span>
                    </a>
                </li>
                <li class="<#if userMenu??>active<#else></#if>">
                    <a href="/eyin/users">
                        <i class="fa fa-users"></i> <span>Users</span>
                    </a>
                </li>
                </#if>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
     <#nested >
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 0.0.1
        </div>
        <strong>Copyright &copy; 2017 <a href="https://twitter.com/seunmatt2">SMATT Softwares</a>.</strong> All rights
        reserved.
    </footer>

</div>
<!-- ./wrapper -->

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
<script type="text/javascript" src="<@asset url = 'admin/js/tinymce/tinymce.min.js'/>"></script>
<!-- AdminLTE for demo purposes -->
<script src="<@asset url = 'admin/js/demo.js' />"></script>
<script src="<@asset url = 'admin/js/utility.js' />"></script>
<script src="/front/js/prism.js"></script>


</body>
</html>

</#macro>

