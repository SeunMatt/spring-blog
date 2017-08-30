<#include "../layouts/front.ftl"/>
<@app>
<div class="main" style="padding-top: 0px; padding-bottom: 30px;">
    <div class="container">
        <div class="row" style="margin-top: 100px; margin-bottom: 20px;">
            <div class="col-sm-offset-3 col-sm-6">
                <img src="<@asset url='front/images/email-newsletter.gif'/>" class="img-responsive" alt="Thank you for Subscribing"/>
            </div>
        </div>
        <div class="error-404 text-center">
            <p style="text-transform: none;">
                We have sent a verification mail to your address, proceed from there. Thanks
            </p>
            <a class="b-home" href="/">Home</a>
        </div>
    </div>
</div>
</@app>