<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>提现成功</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .16rem;}

        .amout_real{
            font-size: .15rem;
            float: right;
            margin-right: .15rem;
            color: #666666;
        }
        img{
            height: .77rem;
            width:.77rem;
        }
       .img{
            margin: 0 auto;
            text-align: center;
            padding-top: .17rem;
       }
        .img p{
            line-height: .65rem;
            font-size: .22rem;
            color: #333;
        }
        ul{
            background: #fff;
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
        }
        li{
            height:.44rem;
            line-height: .44rem;
            margin-left: .12rem;
            border-bottom: 1px solid #d9d9d9;
        }
        li:nth-last-child(1){
            border-bottom: 0px solid transparent;
        }
        .btn{
            margin: 0 auto;
            margin-top: .3rem;
            background: #44cc5b;
            width: 3.13rem;
            height: .42rem;
            display: block;
            border-radius: 8px;
            color: #fff;
            text-align: center;
            line-height:.42rem;
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
    <div class="img"><img src="img/clock_09.png"/>
        <p>提现申请已提交</p>
    </div>
    <ul>
        <li class="amout ">
            <span>提现到</span>
            <span class="amout_real">微信钱包</span>
        </li>
        <li class="amout ">
            <span>提现金额</span>
            <span class="amout_real">￥1700</span>
        </li>
    </ul>
    <a class="btn" href="#">完成</a>
</section>
</body>
</html>