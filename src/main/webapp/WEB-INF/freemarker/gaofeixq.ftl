<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>稿费明细详情</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .15rem;color: #666666;}
        div.box{
            background: #fff;
          /*  height:.43rem;
            width: 3.6rem;*/
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            /*padding:0  .14rem;*/
            padding:.1rem  .14rem;
            text-align: left;
            margin-bottom: .15rem;
        }

        .intro p{
            width: 97%;
            float: left;
        }
        .box_text{
            background: #fff;
           /* border-top: 1px solid #d9d9d9;*/
            padding: .04rem 0;

            line-height: .18rem;
        }
        .box_title{
            height:.22rem;
            line-height: .22rem;
        }
        hr{
            width: 3.59rem;
            height:1px;
            border:none;
            border-top:1px solid #d9d9d9;
            margin:.08rem 0;
        }
        .zan img{
            width: .15rem;
            height:.18rem;

        }
        .amout_real{
            float: right;
           /* margin-right: .12rem;*/
        }
        .article_pic{
            width:100%;
            height:1.94rem;
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
<#list fenzhanList as item>
    <!--第一个段落-->
    <div class="box">
        <div class="box_title">
        <span >今日：${item.content.favour}赞</span>
        <span class="amout_real"> ￥${item.amount1}</span>
       </div>
        <hr/>
        <div class="box_title">
            <span >总计：${item.content.favour}赞 </span>
            <span class="amout_real"> ￥${item.amount1}</span>
        </div>
        <hr/>
        <#if item.content.pic??>
        <img class="article_pic" src="http://ks3-cn-beijing.ksyun.com/aaread/${item.content.pic}"/>
        </#if>
        <p class="box_text">
        ${item.content.content}
        </p>
    </div>
 </#list>
</section>
</body>
</html>