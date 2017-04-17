<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>会说话的鱼</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体';}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .15rem;color:#333;}
        div.box{
            background: #fff;
            /*height:.8rem;
             width: 3.6rem;*/
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            /*padding:0  .14rem;*/
            padding:.1rem  .14rem;
            text-align: left;
            margin-bottom: .15rem;
        }
        .ttl{
            color: #333;
            font-size: .16rem;
        }
        .intro p{
            width: 97%;
            float: left;
        }
        #back_btn{
            float: right;
            width:.1rem;
            height:.18rem;
            font-size: .17rem;
            margin-left:.15rem;
        }
        .head{
            background: url('img/head2.jpg');
            display: inline-block;
            width:.61rem;
            height:.61rem;
            border-radius: 50%;
            -webkit-background-size: cover;
            background-size: cover;
            float: left;
            margin-right: .19rem;
            overflow: hidden;
           vertical-align: middle;
        }
        .head span{
            float: left;
        }
        #more{
            float: right;
        }
        #more .circle{
            width: .05rem;
            height:.05rem;
            display: inline-block;
            background: #cccccc;
            border-radius: 50%;
            margin-left: -3px;
        }
        .box_text{
            background: #fff;
           /* border-top: 1px solid #d9d9d9;*/
            padding: .04rem 0;
            color: #666666;
            line-height: .18rem;
        }
        .box_title{
            height:.6rem;
            line-height: .6rem;
        }
        hr{
            width: 3.59rem;
            height:1px;
            border:none;
            border-top:1px solid #d9d9d9;
            margin:.08rem 0;
        }
        .zan{
            height:.24rem;
           font-size: .18rem;
            color: #666666;
            text-align: right;
            line-height: .24rem;
        }
        .zan img{
            width: .15rem;
            height:.18rem;
        }
        .amout_real{
            font-size: .15rem;
            margin-left: .1rem;
            color: #666666;
        }
        .fabu{
            float: left;
        }
        .btn{
            margin: 0 auto;
            margin-top: 1rem;
            background: #44cc5b;
            width: 3.13rem;
            height: .42rem;
            display: block;
            border-radius: 8px;
            color: #fff;
            text-align: center;
            line-height:.42rem;
            font-size: .18rem;
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
    <div class="box">
        <div class="box_title">
            <span class="head"></span>
            <span style="font-size: .16rem;line-height:.2rem;">会说话的鱼</span>
            <span id="more"> 男</span>
        </div>
        <hr/>
        <p class="box_text">
            <span class="ttl">收入</span>    <span class="amout_real">￥32780</span>
            </p>
        <hr/>
        <p class="box_text">
            <span class="ttl">地区</span>    <span class="amout_real">北京</span>
        </p>
        <hr/>
        <p class="box_text">
            我是一条会说话的鱼，只有七秒的记忆，所以每一秒
            对我来说都格外珍惜。说都格外珍说都格外珍说都格
            说都格外珍外珍有七秒珍外珍有七秒珍外珍有七秒珍
            外珍有七秒的.</p>
        <hr/>
        <div onclick="location.href='gaojian.html'"  class="zan">
            <span class="fabu tt1">发布</span>
            <span class="amout_real">68</span>
            <span id="back_btn">> </span>
        </div>
    </div>
    <a class="btn" href="#">发消息</a>
</section>
</body>
</html>