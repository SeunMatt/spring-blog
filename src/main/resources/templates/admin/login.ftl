<#include "../layouts/login.ftl"/>
<@login title="Login">

<body class="login">
 <div class="">



    <div class="login_wrapper">
        <div class="animate form login_form">
            <#include "../partials/alerts.ftl" />
            <section class="login_content">
                <form role="form" action="/login" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <h1>Login</h1>
                    <div>
                        <input type="text" class="form-control" placeholder="username" name="username" required/>
                    </div>
                    <div>
                        <input type="password" class="form-control" placeholder="Password" name="password" required/>
                    </div>
                    <div>
                        <button class="btn btn-default submit">Log in</button>
                        <a class="reset_pass" href="#">Lost your password?</a>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">New to site?
                            <a href="/register" class="to_register"> Create Account </a>
                        </p>

                        <div class="clearfix"></div>

                        <br>
                        <br><br>
                       <#include "../partials/login_footer.ftl" />

                    </div>
                </form>
            </section>
        </div>
     </div>

  </div>
 </body>
</@login>