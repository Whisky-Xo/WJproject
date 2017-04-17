<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>发现频道</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none;color: #000;}
/*header{
    height:.62rem;
}*/

        section{
            margin-top: .18rem;
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
            line-height: .43rem;
            margin-left: .12rem;
            border-bottom: 1px solid #d9d9d9;
        }
        ul.part li:nth-last-child(1){
            border-bottom: 0px solid transparent;
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
<#list datas as t>
	 <li onclick="location.href='toic_jieshao.html?id=${t.id}'">${t.name}</li>
</#list>
</ul>

</body>
</html>