<#include "../layouts/front.ftl"/>
<@app>

<div class="details">
    <input type="hidden" id="postId" value="${post.id}" />
    <div class="container">
        <div class="row">
            <div class="col-sm-offset-2 col-sm-8">
                <div class="det_pic">
                    <img class="img-responsive" id="img_cover_pic" src="<#if (post.coverPic?length > 0)><@asset url='files/${post.coverPic!""}'/><#else><@asset url='admin/images/default-post-picture.png'/></#if>" alt="" />
                </div>
                <br>
                <h1 class="title">${post.title} </h1>
                <ul class="links">
                    <li><i class="date"> </i><span class="icon_text">${post.createdAt?date.@localdatetime}</span></li>
                    <li><i class="admin"> </i><span class="icon_text">Posted in <strong>${post.category.category}</strong> by <strong>${post.author.name}</strong></li>
                    <li><i class="views"> </i><span class="icon_text">Hits: ${post.views}</span></li>
                    <li><i class="tags"> </i><span class="icon_text"> ${post.section.section}</span></li>
                </ul>
                <div class="det_text">
                  ${post.post}
                </div>
                <div class="row text-center">
                    <div id="share"></div>
                    <br>
                </div>
            </div>
            <#--<div class="col-sm-4">-->
                <#--<#include "../partials/single-post-right-column.ftl" />-->
            <#--</div>-->
        </div>
        <br>
        <#--comment section-->
        <ul class="links" style="border-bottom: 0px none;">
            <li>
                <a href="#commentModal" data-toggle="modal" class="btn btn-lg btn-default">Read/Leave a Comment</a>
            </li>
        </ul>

        <br><br>

        <#-- slider section -->
        <div class="slide" style="">
            <div class="container">
                <div class="fle-xsel">
                    <h3><a href="/posts/${post.category.category}">More ${post.category.category} Stories</a></h3>
                    <ul id="newsRoll">
                        <li>
                            <div class="might-grid">
                                <div class="might-top">
                                    <h4><a href="#">Build Web Applications like a Pro in 6 Simple Steps</a></h4>
                                    <p>Nullam non magna lobortis, faucibus erat eu, consequat justo. Suspendisse commodo nibh odio.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </li>
                        <li>
                            <div class="might-grid">
                                <div class="might-top">
                                    <h4><a href="#">This is the Beginning of the End</a></h4>
                                    <p>Nullam non magna lobortis, faucibus erat eu, consequat justo. Suspendisse commodo nibh odio.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </li>
                        <li>
                            <a href="#">
                                <div class="banner-1">
                                    <img src="<@asset url='front/images/s-1.jpg' />" class="img-responsive" alt="">
                                    <h3></h3>
                                </div>
                            </a>
                        </li>
                        <li>
                            <div class="might-grid">
                                <div class="might-top">
                                    <h4><a href="#">This is the Beginning of the End</a></h4>
                                    <p>Nullam non magna lobortis, faucibus erat eu, consequat justo. Suspendisse commodo nibh odio.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </li>
                        <li>
                            <a href="#">
                                <div class="banner-1">
                                    <img src="/front/images/s-3.jpg" class="img-responsive" alt="">
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="banner-1">
                                    <img src="/front/images/s-6.jpg" class="img-responsive" alt="">
                                </div>
                            </a>
                        </li>
                    </ul>
                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>


    <#--comment modal-->
        <div class="portfolio-modal modal fade" id="commentModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-content">
                <div class="close-modal" data-dismiss="modal">
                    <div class="lr">
                        <div class="rl">
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <div class="modal-body">
                                <#--collapsible for leave a comment-->
                                <div class="panel-group">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title" style="font-weight: 700;text-align: left;font-size: 20px;">
                                                <a data-toggle="collapse" href="#collapse1">Leave a Comment</a>
                                            </h4>
                                        </div>
                                        <div id="collapse1" class="panel-collapse collapse">
                                            <div class="panel-body">
                                                <div class="lev" style="text-align: left;margin-top: 0px;">
                                                    <form id="commentform" action="/comment/add" method="post">
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                        <input type="hidden" name="postId" value="${post.id}">
                                                        <label for="name">Name</label>
                                                        <input id="name" name="name" type="text" value="" maxlength="30" size="30" aria-required="true" required>
                                                        <label for="email">Email</label>
                                                        <input id="email" name="email" type="text" value="" maxlength="30" size="30" aria-required="true" required>
                                                        <label for="comment">Comment</label>
                                                        <textarea name="comment" required></textarea>
                                                        <br>
                                                        <input type="checkbox" name="notify"> Notify me, via email, of direct comment reply
                                                        <br><br>
                                                        <div class="clearfix"></div>
                                                        <div class="g-recaptcha" data-sitekey="6LcpmiAUAAAAACv69eLygOFO3OPoayrMpT2fk_rJ"></div>
                                                        <div class="clearfix"></div>
                                                        <br>
                                                        <input name="submit" type="submit" id="submit" value="Send">
                                                        <input id="collapseBt" type="submit" value="Cancel">
                                                        <div class="clearfix"></div>
                                                    </form>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="comments1" id="commentColumn"></div>
                                <br>
                                <button type="button" class="btn btn-default btn-md" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



    </div>
</div>
<script src="<@asset url='admin/js/utility.js' />"></script>
<script src="<@asset url='front/plugins/jssocials-1.4.0/jssocials.js' />"></script>
<script src="<@asset url='front/js/prism.js' />"></script>
<script type="text/javascript" src="<@asset url = 'front/js/jquery.flexisel.js' />"></script>
<script type="text/javascript">
    $(window).load(function() {
        $("#newsRoll").flexisel({
            visibleItems: 3,
            animationSpeed: 1000,
            autoPlay: true,
            autoPlaySpeed: 3000,
            pauseOnHover: true,
            enableResponsiveBreakpoints: true,
            responsiveBreakpoints: {
                portrait: {
                    changePoint:480,
                    visibleItems: 1,
                    itemsToScroll: 1
                },
                landscape: {
                    changePoint:640,
                    visibleItems: 3,
                    itemsToScroll: 1
                },
                tablet: {
                    changePoint:768,
                    visibleItems: 3,
                    itemsToScroll: 1
                }
            }
        });
    });

    $(document).ready(function () {

        loadComments($("#postId").val());

        $("#share").jsSocials({
            shares: [
                "twitter",
                {
                    share: "facebook",
                    label: "Share",
                    logo: "fa fa-facebook",
                    hashtags: "blogpost"
                },
                "googleplus",
                "linkedin",
                "whatsapp",
                {
                    share: "messenger",
                    label: false,
                    shareIn: "self"
                }
            ],
            showCount: false,
            showLabel: false,
            shareIn: "popup"
        });

        $("#collapseBt").on("click", function (event) {
            event.preventDefault();
            $("#collapse1").collapse("hide");
        });

        $("#commentform").on("submit", function (event) {
            event.preventDefault();
            $.ajax({
                type: "post",
                url: "/comment/add",
                data: $("#commentform").serialize(),
                beforeSend: function () {
                    displayWait("#commentform");
                },
                success: function (response) {
                    swal("Comment Posted", "Your comment has been posted successfully", "success")
                    cancelWait("#commentform");
                    $("#commentform")[0].reset();
                    grecaptcha.reset();
                    loadComments($("input[name='postId']").val());
                    $("#collapse1").collapse("hide");
                },
                error: function (error) {
                    swal("Oops", "ERROR: " + error.responseText, "error");
                    cancelWait("#commentform");
                    grecaptcha.reset();
                }
            });
        });
    });
</script>
</@app>