<#include "../../layouts/login.ftl"/>
<@login title="Admin Reset Password" body="login-page">

<div class="login-box">
    <div class="login-logo">
        <a href="/"><b>Smatt</b> Blog</a>
    </div>

    <#include "../../partials/alerts.ftl" />

    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">Password Reset</p>

        <form action="/account/reset/${token}" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="Password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password_confirmation" class="form-control" placeholder="Confirm Password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Reset Password</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <a href="/register" class="text-center">Don't have Account?</a>

    </div>
    <!-- /.login-box-body -->
</div>

</@login>