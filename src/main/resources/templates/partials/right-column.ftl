<div class="abt-2">
    <h3 class="right-col-heading">
        CATEGORIES
    </h3>
    <ul>
    <#list categories as category>
        <li><a href="/posts/${category.category}">${category.category}</a></li>
    </#list>
    </ul>
</div>
<div class="abt-2">
    <h3 class="right-col-heading">TAGS</h3>
    <ul>
    <#list tags as tag>
        <li><a href="/posts/tag_${tag.tag}">${tag}</a></li>
    </#list>
    </ul>
</div>
<div class="abt-2">
    <h3 class="right-col-heading" style="text-align: center">NEWSLETTER</h3>
    <small>Never miss an update from me again!</small>
    <div class="news">
        <form method="post" action="/newsletter/subscribe">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="email" name="email" id="email" placeholder="example@gmail.com" required/>
            <input type="submit" value="Subscribe">
        </form>
    </div>
</div>