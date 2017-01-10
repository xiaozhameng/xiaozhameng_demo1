<div class="banner">
    <div>
	  <#if homebanners?exists >
		  <#list homebanners as homebanner>
		    <img  src= "${homebanner.bannerPic}">
		  </#list>
	  </#if>
    </div>
</div>
<div class="mid">
	  <#if homebanners?exists >
		  <#list advertbanners as advertbanner>
		    <a style="background-image:url(${advertbanner.bannerPic})" class="left" href="javascript:void(0);"></a>
		  </#list>
	  </#if>
</div>
