<#include "layouts/app.ftl">
<@app>

<!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('<@asset url='front/img/post-bg.jpg'/>')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1>${post.title}</h1>
                        <h2 class="subheading"></h2>
                        <span class="meta">Posted by <a href="#">${post.author}</a> on ${post.createdAt?date}</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <p>${post.post}</p>
                </div>
            </div>
        </div>
    </article>

    <hr>

 </@app>