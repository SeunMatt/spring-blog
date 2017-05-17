<#include "../layouts/login.ftl"/>
<@login title="Admin Regsisteration" body="register-page">

<div class="register-box">
    <div class="register-logo">
        <a href="/"><b>Smatt</b> Blog</a>
    </div>

    <#include "../partials/alerts.ftl" />

    <div class="register-box-body">
        <p class="login-box-msg">Register</p>

        <form action="/register" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="Full name" name="name" required
                       value="<#if formdata??>${formdata.name!''}<#else></#if>" >
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="Username" name="username" required
                       value="<#if formdata??>${formdata.username!''}<#else></#if>">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="email" class="form-control" name="email" placeholder="Email" required
                       value="<#if formdata??>${formdata.email!''}<#else></#if>">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" name="password" placeholder="Password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" name="password_confirmation" placeholder="Retype password">
                <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="g-recaptcha" data-sitekey="6LcpmiAUAAAAACv69eLygOFO3OPoayrMpT2fk_rJ"></div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <#--<input type="checkbox"> I agree to the <a href="/terms">terms</a>-->
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Register</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

    <#--<div class="social-auth-links text-center">-->
    <#--<p>- OR -</p>-->
    <#--<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign up using-->
    <#--Facebook</a>-->
    <#--<a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign up using-->
    <#--Google+</a>-->
    <#--</div>-->

        <a href="/login" class="text-center">Already Registered? Login</a>
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

</@login>