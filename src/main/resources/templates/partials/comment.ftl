<h4>COMMENTS</h4>
<hr class="star-primary">
<div class="comments-main">
    <#list comments as comment>
        <div class="col-md-10 cmts-main-right">
            <h5>${comment.name} <span>On ${comment.createdAt?date.@localdatetime}</span></h5>
            <p>${comment.comment}</p>
            <br>
            <div class="replies">
                <div class="cmts-main-right reply">
                    <h6>TOM BROWN <span>On April 14, 2014, 18:01</span></h6>
                    <p>Vivamus congue turpis in augue pellentesque scelerisque. Pellentesque aliquam laoreet sem nec ultrices. Fusce blandit nunc vehicula massa vehicula tincidunt. Nam venenatis cursus urna sed gravida. Ut tincidunt elit ut quam malesuada consequat. Sed semper purus sit amet lorem elementum faucibus.
                    </p>
                </div>
                <br>
                <div class="cmts-main-right reply">
                    <h6>TOM BROWN <span>On April 14, 2014, 18:01</span></h6>
                    <p>Vivamus congue turpis in augue pellentesque scelerisque. Pellentesque aliquam laoreet sem nec ultrices. Fusce blandit nunc vehicula massa vehicula tincidunt. Nam venenatis cursus urna sed gravida. Ut tincidunt elit ut quam malesuada consequat. Sed semper purus sit amet lorem elementum faucibus.
                    </p>
                </div>
                <br>
                <div class="cmts-main-right reply">
                    <h6>TOM BROWN <span>On April 14, 2014, 18:01</span></h6>
                    <p>Vivamus congue turpis in augue pellentesque scelerisque. Pellentesque aliquam laoreet sem nec ultrices. Fusce blandit nunc vehicula massa vehicula tincidunt. Nam venenatis cursus urna sed gravida. Ut tincidunt elit ut quam malesuada consequat. Sed semper purus sit amet lorem elementum faucibus.
                    </p>
                </div>
            </div>
            <br>
            <div class="cmts">
                <div class="cmnts-right">
                    <a href="#">Reply</a>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="clearfix"></div>
    </#list>
</div>