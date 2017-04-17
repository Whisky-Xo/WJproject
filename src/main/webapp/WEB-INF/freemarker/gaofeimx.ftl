<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>稿费明细</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none;color: #000;}
/*header{
    height:.62rem;
}*/

        section{
           /* margin-top: .18rem;*/
            font-size: .16rem;
        }
        ul.part{
            background: #fff;
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            color: #666;
           /* padding-right: .12rem;
            width:3.63rem;*/
           /* margin-bottom: .22rem;*/
        }
        ul.part li{
            height:.43rem;
            line-height: .43rem;
            margin-left:.12rem;
            border-bottom: 1px solid #d9d9d9;
        }
        ul.part li:nth-last-child(1){
            border-bottom: 0px solid transparent;
        }
        .amout_real{
            float: right;
            margin-right: .12rem;
        }
        #amount{
            /*margin: 0 auto;*/
            text-align: center;
            color: #333;
            font-size: .16rem;
            height:.53rem;
            line-height: .53rem;
        }
      .color{
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
    <p id="amount">总计 <span class="color">${(total/100)?string("0.00")}</span></p>
<ul class="part">
<#list ws as item>
    <li onclick="location.href='gaofeixq.html?waterId=${item.id}'">${item.createTime?string("yyyy/MM/dd")}<span class="amout_real">￥${(item.amount/100)?string("0.00")}</span></li>
</#list>
</ul>

</body>
</html>