<div class="row">
    <div class="col-xs-10">
        <!-- PRODUCT LIST -->
        <div class="box">
            <div class="box-header with-border">
                <h3 class="box-title">Most Read Articles</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <ul class="products-list product-list-in-box">
                <#list trendingPosts as trendingPost>
                    <li class="item">
                        <a href="/p/${trendingPost.id}" class="product-title">${trendingPost.title}</a>
                        <p class="product-description text-muted">${trendingPost.post?substring(0, 45)} ...</p>
                    </li>
                </#list>
                    <li>
                        <a style="color:#000000;" class="product-title " href="/contact"><h3>Wanna Sponsor an Article?</h3></a>
                    </li>
                </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer text-center">

            </div>
            <!-- /.box-footer -->
        </div>
        <!-- /.box -->
    </div>
</div>