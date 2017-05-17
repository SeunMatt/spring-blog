<#macro login title="" body="">
<#assign asset = "com.smatt.addons.AssetDirective"?new()>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Smatt Blog <#if (title?length > 0) >| ${title} </#if>)</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<@asset url = 'admin/css/bootstrap/bootstrap.min.css' />">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<@asset url = 'font-awesome-4.7.0/css/font-awesome.min.css' />">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<@asset url = 'admin/css/AdminLTE.min.css' />">
    <!-- iCheck -->
    <link rel="stylesheet" href="<@asset url = 'admin/plugins/iCheck/square/blue.css' />">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<body class="hold-transition ${body}">

<#nested >

<!-- jQuery 2.2.3 -->
<script src="<@asset url = 'admin/js/jQuery/jquery-2.2.3.min.js' />"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<@asset url = 'admin/js/bootstrap/bootstrap.min.js' /> "></script>
<!-- iCheck -->
<script src="<@asset url = 'admin/plugins/iCheck/icheck.min.js' />"></script>

<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>

</#macro>