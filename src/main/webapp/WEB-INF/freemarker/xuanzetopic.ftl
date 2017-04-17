<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>选择频道</title>
    <link rel="stylesheet" href="css/mui.min.css">
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/mui.min.js"></script>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none;color: #000;}
        header{
           /* height:.62rem;*/
        }

        section{
           /* margin-top: .18rem;*/
            font-size: .16rem;
        }
        ul.part{
            background: #fff;
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            color: #333333;
           /* margin-bottom: .22rem;*/
        }
        ul.part li{
            height:.43rem;
            width: 3.63rem;
            line-height: .43rem;
            padding-left: .12rem;
         /*  border-bottom: 2px solid transparent;
            border-top: 2px solid transparent;*/
        }
        ul.part li:nth-last-child(1){
            border-bottom: 0px solid transparent;
        }
        .letter{
            color: #999999;
            font-size: .15rem;
            margin-left: .12rem;
            height: .22rem;
            line-height: .23rem;
        }
        .alert{
            display: none;
            width:2.7rem;
            height: 1.05rem;
            border-radius: 10px;
            color: #666;
            text-align: center;
            font-size: .17rem;
            background: #fff;
            margin: 0 auto;
            margin-top: .45rem;
            padding: 0;
        }
        .alert a{
            width: 50%;
            height:.41rem;
            display: inline-block;
            float: left;
            line-height: .41rem;
            box-sizing: border-box;
            color: #007aff;
}
        .alert a:nth-of-type(1){
            border-right: 1px solid #ccc;
        }
        .alert p{
            width: 100%;
            height:.45rem;
           /* line-height: .2rem;*/
            border-bottom:solid 1px  #ccc;
            padding-top:.1rem;
            padding-bottom: .07rem;
            color: #010101;
        }
        hr{
            width: 3.63rem;
            height:1px;
            border:none;
            border-top:1px solid #d9d9d9;
            margin-left: .12rem;
            margin-top: -1px;
            /*margin-bottom: -1px;*/
        }
    </style>
    <script>
        (function(){
            function change(){
                var oHtml=document.querySelector('html');
                oHtml.style.fontSize=document.documentElement.clientWidth/375*100+'px';
            }

            change();
            window.addEventListener('resize',change,false);
        })();
    </script>
</head>
<body>
<header></header>
<section>
    <#assign zimu="">
	<#assign part=0 >
	<#list datas as t>
		<#if zimu != t.zimu >
	    	<#assign part=1 >
	    	<#assign zimu=t.zimu>
	    </#if>
	    <#if part == 1 >
		    <#if t_index != 0>
		    </ul>
		    </#if>
		    <#assign part=0 >
		    <div class="letter">${t.zimu}</div>
		    <ul class="part">
	    </#if>
	        <li myid="${t.id}">${t.name}</li>
	    <#if t_index == (datas?size - 1)>
		    </ul>
		</#if>
	</#list>
</section>
<script type="text/javascript" charset="utf-8">
$(function(){
	mui.init(); 
	$(".part li").click( function (e) { 
		var tt = $(e.target);
		var cate = tt.text();
		var topicId = tt.attr('myid');
		msg = "发布到"+cate+"?"
		mui.confirm(msg,"提示",['确定','取消'], function(e) {
					if (e.index == 0) {
						var id = getQueryString("id");
						var data = {};
						data['id']=id;
						data['cate']=cate;
						data['topicId']=topicId;
						$.post("updateCate.do", data, function(data){
					     	if(data.code == 0){
					     		window.location.href="topic.html?id="+id;
					     	}else{
					     		alert(data.msg);
					     	}
					   	}, "json" );
					} else {
						
					}
				},'div');
	 });
});

function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
</script>

</body>
</html>