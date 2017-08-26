<#include "../layouts/front.ftl"/>
<@app>
<div class="contact-top">
    <div class="container">
        <h2>Seun Matt</h2>
        <br>
        <#include "../partials/alerts.ftl" />
        <div class="contact">
            <div class="contact-down">
                <div class="contact-right">
                    <div class="col-md-6 contact-left">
                        <h5>CONTACT-INFO</h5>
                        <p>
                            Thank you for visiting my blog. I am eager to hear from you. What do you think
                            about my blog? Do you have questions for me? Just fill the form and shoot me an email.
                            I will respond to you typically within 24 hours.<br>
                            Cheers!
                        </p>
                        <address>
                            <strong>You can also catch me on:</strong>
                            <br>
                            Twitter: @SeunMatt2
                            <br>
                            Facebook: seun.matt
                            <br>
                        </address>
                    </div>
                    <div class="col-md-6 contact-info">
                        <form role="form" action="/contact" id="contactForm" method="POST">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <input type="text" name="name" placeholder="NAME" required>
                            <input type="email" name="email" placeholder="EMAIL" required>
                            <textarea cols="70" rows="5" name="message" placeholder="MESSAGE" required></textarea>
                            <div class="clearfix"> </div>
                            <input type="submit" value="SEND" />
                        </form>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $().ready(function () {
        $("#nav-contact").addClass("active");
    });
</script>
</@app>