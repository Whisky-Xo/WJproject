<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>订阅</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none;color: #000;}
header{
    height:.62rem;
}
        section{
            margin-top: .18rem;
            font-size: .16rem;
            margin-bottom: .49rem;
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
            line-height: .43rem;
            margin-left: .12rem;
            border-bottom: 1px solid #d9d9d9;
        }
        ul.part li:nth-last-child(1){
            border-bottom: 0px solid transparent;
        }
       .letter{
            color: #999999;
            font-size: .15rem;
            margin-left: .12rem;
            height: .22rem;
            line-height: .22rem;
        }
       footer{
          height:.49rem;
          background: #f7f7f8;
          position: fixed;
          bottom: 0;
          left:0;
          width:100%;

       }
        .footer_bar li{
            float: left;
            font-size: .16rem;
            width: 25%;
            margin-left:0;
            line-height: .49rem;
            text-align: center;
        }
        .footer_bar li a{
            color: #929191;
        }
        .footer_bar li a.active{
            color: #44cc5b;
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
<ul class="part">
    <li onclick="location.href='faxian.html'">发现频道</li>
    <li onclick="location.href='sub2.html'">今日最热</li>
    <li onclick="location.href='fabu.html'">我要投稿</li>
</ul>
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
        <li onclick="location.href='toic_jieshao.html?id=${t.id}'">${t.name}</li>
    <#if t_index == (datas?size - 1)>
	    </ul>
	</#if>
</#list>
</section>
<footer>
    <ul class="footer_bar">
        <li><a href="index.html">首页</a></li>
        <li><a class="active" href="#">订阅</a></li>
        <li><a href="msg.html">信息</a></li>
        <li><a href="my.html">我的</a></li>
    </ul>
</footer>
</body>
</html>