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
    <#list categories as category>
        <li><a href="/posts/${category.category}">${category.category}</a></li>
    </#list>
    </ul>
</div>
<div class="abt-2">
    <h3 class="right-col-heading" style="text-align: center">NEWSLETTER</h3>
    <small>Never miss an update from me again!</small>
    <div class="news">
        <form>
            <input type="text" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" />
            <input type="submit" value="Subscribe">
        </form>
    </div>
</div>