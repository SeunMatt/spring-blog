<#include "../../layouts/login.ftl"/>
<@login title="Account Reset" body="lockscreen">

<div class="lockscreen-wrapper">

    <#include "../../partials/alerts.ftl"/>

    <div class="lockscreen-logo">
        <a href="/"><b>Spring</b> Blog</a>
    </div>
    <!-- User name -->
    <#--<div class="lockscreen-name">John Doe</div>-->

    <!-- START LOCK SCREEN ITEM -->
    <div class="lockscreen-item">
    <!-- lockscreen image -->
    <div class="lockscreen-image">
        <img src="<@asset url='admin/images/user.png' />" alt="User Image">
    </div>
    <!-- /.lockscreen-image -->

    <!-- lockscreen credentials (contains the form) -->
    <form class="lockscreen-credentials" method="post" action="/resend/email/${path}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="input-group">
            <input type="email" class="form-control" placeholder="email" name="email" required>
            <div class="input-group-btn">
                <button type="submit" class="btn"><i class="fa fa-arrow-right text-muted"></i></button>
            </div>
        </div>
    </form>
    <!-- /.lockscreen credentials -->

</div>
    <!-- /.lockscreen-item -->

    <div class="help-block text-center">
         Enter the email associated with your account
    </div>

    <div class="text-center">
        <a href="/login">Login</a>
        <br>
        <a href="/register">Register</a>
    </div>
<#--<div class="lockscreen-footer text-center">-->
    <#--Copyright &copy; 2014-2016 <b><a href="http://almsaeedstudio.com" class="text-black">Almsaeed Studio</a></b><br>-->
    <#--All rights reserved-->
<#--</div>-->
</div>
<!-- /.center -->

</@login>
