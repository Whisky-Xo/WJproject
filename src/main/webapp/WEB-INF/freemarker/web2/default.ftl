<!DOCTYPE html>
<html>
<head>
<title>
拾分阅读
</title>
<meta charset="utf-8" />
<meta http-equiv="Cache-Control" content="no-transform " /> 
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no" />
<meta name="description" content="互联网的运营规划，为企业提供有效的运营解决方案" />
<meta name="keywords" content="互联网运营，行业运营，网站运营， APP运营" /> 
<link rel="shortcut icon" href="http://www.ishayou.com/wp-content/themes/gr/images/favicon.ico" />
<link rel='stylesheet' id='sytle-css'  href='css/style.css' type='text/css' media='all' />
<link rel='stylesheet' id='home-css'  href='css/home.css' type='text/css' media='all' />
<link rel='stylesheet' id='ishayou-pc-css'  href='css/ishayou-pc.css' type='text/css' media='all' />
<link rel='stylesheet' id='ishayou-ipad-css'  href='css/ishayou-ipad.css' type='text/css' media='all' />
<link rel='stylesheet' id='ishayou-phone-css'  href='css/ishayou-phone.css' type='text/css' media='all' />
<script type='text/javascript' src='js/html5shiv.js'></script>
<script type='text/javascript' src='js/css3-mediaqueries.js'></script>
<script type='text/javascript' src='js/selectivizr-min.js'></script>
<script type='text/javascript' src='js/jquery.1.11.1.js'></script>
<script type='text/javascript' src='js/zan.js'></script>
</head>
<body>
<header id="header-web">
  <div class="header-main">
      <h1 class="logo"><a href="http://www.aaread.com/"  rel="home"><img src="picture/logo.png" alt=""></a></h1>
       <h1 class="logo2"><a href="http://www.aaread.com/"  rel="home"><img src="picture/logo2.png" alt=""></a></h1>
    <!--logo-->
         	<nav class="header-nav">
      	<ul id="menu-%e9%a1%b6%e9%83%a8%e8%8f%9c%e5%8d%95" class="menu">
      	<li id="menu-item-2234" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-2234"><a href="/video_6_1.html">推荐</a></li>
		<li id="menu-item-2237" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-2237"><a href="video_3_1.html">综艺</a></li>
		<li id="menu-item-2236" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2236"><a href="/video_2_1.html">搞笑</a></li>
		<li id="menu-item-2236" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2236"><a href="/video_4_1.html">创意</a></li>
		<li id="menu-item-2236" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2236"><a href="/video_5_1.html">科普</a></li>
		</ul>    </nav>
     <div class="weix">公众号
           
           <div class="er"><img src="picture/ishayou6.png"/></div>
           </div>
  </div>
  
</header>
<!--header-web-->
<div id="main"><div id="container">
 
<#list page.list as data>  
 <section class="list">

    <div class=" <#if data.pic??>mecc<#else>mucc</#if>">
     <a href="item_${data.id}.html" target="_blank" class="iu" >
 ${data.zanNum?default(0)} 
 </a>
      <h2 class="mecctitle"> <a href="item_${data.id}.html" target="_blank">
        ${data.title}        </a> </h2>
      <address class="meccaddress">
       <time>
        ${data.createTime?string('MM.dd')}      
        </time>
       <a href="http://www.aaread.com/category/yunyin/yonghu" rel="category tag"></a>       
      </address>
    </div>
    <#if data.pic??>
        <span class="titleimg">
        <a href="item_${data.id}.htm" target="_blank">
        <img width="1000" height="600" src="${data.pic}" class="attachment- Large wp-post-image" alt="mujju" />        </a>
        </span>
        </#if>
        <div class=" <#if data.pic??>zaiyao<#else>zuiyao</#if>">
            <p>${data.idesc}</p>
    </div>
    <div class="clear"></div>
  </section>  
  </#list>
  
    <!--list-->
    <div class="pagenavi">
    <span class="page-numbers">${page.pageNum} / ${page.pages} </span>
    <#if !page.isFirstPage>
    	<a class='page-numbers' href='video_${type}_${page.prePage}.html' title='上一页'> 上一页</a> 
    </#if> 
    <#list page.navigatepageNums as nav>
    	<#if page.pageNum == nav>
    		<span class='page-numbers current'>${nav}</span> 
    	<#else>
    		<a class='page-numbers' href='video_${type}_${nav}.html' title='第 ${nav} 页'> ${nav}</a> 
    	</#if>
    </#list>
    <#if page.hasNextPage>
    	<a class='page-numbers' href='video_${type}_${page.nextPage}.html' title='下一页'>下一页</a>  
    </#if> 
     </div>
    
    <!--Page End-->
  <nav class="navigation">
  <#if page.hasNextPage>
    	<div class="nav-previous">
    <a href="video_${type}_${page.nextPage}.html" >下一页</a>  </div>
    </#if> 
    <#if page.hasPreviousPage>
    <div class="nav-next">
    <a href="video_${type}_${page.prePage}">上一页</a>  </div>
     </#if> 
    </nav>
<!-- .navigation -->
  <!--phonepage--> 
</div>
<!--Container-->
<aside id="sitebar"> 
   <div id="soutab">
  <form method="get" class="search" action="http://www.aaread.com/" >
    <input class="text" type="text" name="s" placeholder=" 请输入关键词" value="">
    <input class="butto" value="" type="submit">
  </form>
</div>


      <span class="tagtitle">整体规划</span>
      <div id="tagg">
        <ul id="menu-%e5%8f%b3%e4%be%a7%e8%8f%9c%e5%8d%95" class="menu"><li id="menu-item-603" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-603"><a href="http://www.aaread.com/category/yunyin/marketfenxi">市场分析</a></li>
<li id="menu-item-751" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-751"><a href="http://www.aaread.com/category/yunyin/yonghuyajiu">用户研究</a></li>
<li id="menu-item-611" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-611"><a href="http://www.aaread.com/category/yunyin/dingwei">定位/差异化</a></li>
<li id="menu-item-605" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-605"><a href="http://www.aaread.com/category/yunyin/design">产品/服务设计</a></li>
<li id="menu-item-604" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-604"><a href="http://www.aaread.com/category/yunyin/yonghu">用户体验</a></li>
<li id="menu-item-845" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-845"><a href="http://www.aaread.com/category/yunyin/shuju">数据分析</a></li>
<li id="menu-item-118" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-118"><a href="http://www.aaread.com/category/yunyin/yunyings">运营思维</a></li>
</ul>      </div>
      <span class="tagtitle">营销推广</span>
      <div id="tagg">
        <ul id="menu-%e8%90%a5%e9%94%80%e6%8e%a8%e5%b9%bf" class="menu"><li id="menu-item-607" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-607"><a href="http://www.aaread.com/category/yunyin/%e8%90%a5%e9%94%80%e5%bf%83%e7%90%86%e5%ad%a6/ad">广告投放</a></li>
<li id="menu-item-606" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-606"><a href="http://www.aaread.com/category/yunyin/%e8%90%a5%e9%94%80%e5%bf%83%e7%90%86%e5%ad%a6/yingxiaosiwei">营销思维</a></li>
<li id="menu-item-591" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-591"><a href="http://www.aaread.com/category/yunyin/%e8%90%a5%e9%94%80%e5%bf%83%e7%90%86%e5%ad%a6/leirong">内容营销</a></li>
<li id="menu-item-609" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-609"><a href="http://www.aaread.com/category/yunyin/%e8%90%a5%e9%94%80%e5%bf%83%e7%90%86%e5%ad%a6/she">社会化营销</a></li>
<li id="menu-item-592" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-592"><a href="http://www.aaread.com/category/yunyin/%e8%90%a5%e9%94%80%e5%bf%83%e7%90%86%e5%ad%a6/pinpai">品牌/口碑建设</a></li>
<li id="menu-item-645" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-645"><a href="http://www.aaread.com/category/yunyin/%e8%90%a5%e9%94%80%e5%bf%83%e7%90%86%e5%ad%a6/shijian">事件营销</a></li>
<li id="menu-item-594" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-594"><a href="http://www.aaread.com/category/yunyin/%e8%90%a5%e9%94%80%e5%bf%83%e7%90%86%e5%ad%a6/xinlixue">营销心理学</a></li>
<li id="menu-item-593" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-593"><a href="http://www.aaread.com/category/yunyin/%e8%90%a5%e9%94%80%e5%bf%83%e7%90%86%e5%ad%a6/kefu">客服培养</a></li>
</ul>      </div>
             <span class="tagtitle">管理/成长</span>
        <div id="tagg">
        <ul id="menu-%e7%ae%a1%e7%90%86" class="menu"><li id="menu-item-803" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-803"><a href="http://www.aaread.com/category/yunyin/chuangye">创业</a></li>
<li id="menu-item-598" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-598"><a href="http://www.aaread.com/category/yunyin/guanli/team">团队建设</a></li>
<li id="menu-item-596" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-596"><a href="http://www.aaread.com/category/yunyin/guanli/qiyewenhua">企业文化</a></li>
<li id="menu-item-610" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-610"><a href="http://www.aaread.com/category/yunyin/guanli/zhichang">个人成长</a></li>
<li id="menu-item-804" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-804"><a href="http://www.aaread.com/category/yunyin/shashuo">瞎说</a></li>
</ul>      </div>
  <div class="sitebar_list">
    <h4 class="sitebar_title">随机热文</h4>

    <ul class="sitebar_list_ul">
    <#list list as item>
          <li><a href="item_${item.id}" target="_blank">
        ${item.title}        </a> </li>
    </#list>
    </ul>
  </div>
  <script type="text/javascript"> 
$(function() { 
    var elm = $('.sitebar_list'); 
    var startPos = $(elm).offset().top; 
    $.event.add(window, "scroll", function() { 
        var p = $(window).scrollTop(); 
        $(elm).css('position',((p) > startPos) ? 'fixed' : 'static'); 
        $(elm).css('top',((p) > startPos) ? '20px' : ''); 
    }); 
}); 
</script>
</aside>

<div class="clear"></div>
</div>
<!--main-->
<footer id="dibu">
  <div class="header-main">
  
    <div class="bottomlist">
      
    </div>


    <div class="bleft">
      <ul id="menu-%e5%ba%95%e9%83%a8%e8%8f%9c%e5%8d%95" class="menu"></ul>      
      <div class="bottom"> © 2016 <a href="http://www.aaread.com/">
        十分阅读        </a>  / <a href="http://www.miitbeian.gov.cn/" rel="external nofollow" target="_blank"> 京ICP备16049117号 </a>
        <div class="tongji"><script src="js/z_stat.js" language="JavaScript"></script></div>
      </div>
    </div>
    
  </div>
  <div class="off">
  <div class="scroll" id="scroll" style="display:none;"> ︿ </div>
  </div>
  <script type="text/javascript">
	$(function(){
		showScroll();
		function showScroll(){
			$(window).scroll( function() { 
				var scrollValue=$(window).scrollTop();
				scrollValue > 500 ? $('div[class=scroll]').fadeIn():$('div[class=scroll]').fadeOut();
			} );	
			$('#scroll').click(function(){
				$("html,body").animate({scrollTop:0},200);	
			});	
		}
	})
	</script> 
</footer>
<!--dibu-->
</body></html>