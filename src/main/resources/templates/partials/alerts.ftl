<#if (error?length > 0)??>
    <div class="alert alert-danger">
        <button data-dismiss="alert" class="close close-sm" type="button">
            <i class="fa fa-times"></i>
        </button>
        <strong>Error! </strong> ${error}.
    </div>
<#else>
</#if>