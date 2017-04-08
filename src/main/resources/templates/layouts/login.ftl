<#macro login title="">
<#--make the asset helper available-->
    <#assign asset = "com.smatt.addons.AssetDirective"?new()>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${title} | Seun Matt Online</title>

    <!-- Bootstrap -->
    <link href="<@asset url ='admin/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">

    <link href="<@asset url='font-awesome-4.7.0/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
    <!-- NProgress -->
    <link href="<@asset url='admin/css/nprogress/nprogress.css' />" rel="stylesheet">

    <link href="<@asset url = 'admin/css/animate/animate.min.css' />" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="<@asset url='admin/css/custom.min.css' />" rel="stylesheet">

</head>

<#nested >

</html>
</#macro>