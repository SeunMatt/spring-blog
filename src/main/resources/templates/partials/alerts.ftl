<#if (error?length > 0)??>
    <div class="alert alert-danger">
        <button data-dismiss="alert" class="close close-sm" type="button">
            <i class="fa fa-times"></i>
        </button>
        <strong>Error! </strong> ${error}.
    </div>
<#else>
</#if>
<#if (success?length > 0)??>
<div class="alert alert-success">
    <button data-dismiss="alert" class="close close-sm" type="button">
        <i class="fa fa-times"></i>
    </button>
    <strong>Success! </strong> ${success}.
</div>
<#else>
</#if>
<#if (status?length > 0)??>
<div class="alert alert-primary">
    <button data-dismiss="alert" class="close close-sm" type="button">
        <i class="fa fa-times"></i>
    </button>
    <strong>Status! </strong> ${status}.
</div>
<#else>
</#if>