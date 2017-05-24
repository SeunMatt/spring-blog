<#include "../layouts/app.ftl"/>
<@app>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('<@asset url = 'front/img/home-bg.jpg' />')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="site-heading">
                    <h1>Clean Blog</h1>
                    <hr class="small">
                    <span class="subheading">A Clean Blog Theme by Start Bootstrap</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">

            <#list posts as post>
                <div class="post-preview">
                    <a href="/post/${post.id}">
                        <h2 class="post-title">
                        ${post.title}
                        </h2>
                        <h3 class="post-subtitle">
                        ${post.post}
                        </h3>
                    </a>
                    <p class="post-meta">Posted by <a href="#">${post.author}</a> on ${post.createdAt?date} </p>
                </div>
                <hr>
            <#else>
                <h2 class="post-title">
                    No Posts Yet . . . Still Cooking Them
                </h2>
            </#list>

            <!-- Pager -->
            <ul class="pager">
                <li class="next">
                    <a href="#">Older Posts &rarr;</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<hr>

</@app>