<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>我的钱包</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体';}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .15rem;color:#333;}

       .img{
           width: .9rem;
           height:.9rem;
           overflow: hidden;
           margin: 0 auto;
           margin-top: .67rem;
       }
        .img img{
            width: .9rem;
            height:.9rem;
        }
        .balance{
            color: #010101;
            font-size: .18rem;
            margin: 0 auto;
            text-align: center;
            margin-top: .39rem;
        }
        .money{
            color: #ffba00;
            font-size: .22rem;
            margin: 0 auto;
            text-align: center;
            line-height:.29rem;
        }
        .intro p{
            width: 97%;
            float: left;
        }
        .btn{
            margin: 0 auto;
            margin-top: .62rem;
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
        .cancel{
            background: #44cc5b;
            margin-top: .15rem;
            color:#fff;
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

    <div class="img"><img src="img/wallet_09.png"/></div>
    <p class="balance">我的余额</p>
    <p class="money">￥${(user.balance/100)?string('0.00')}</p>
    <a class="btn" href="/pay/chongzhi.html">充值</a>
    <a class="btn cancel" href="tixian.html">提现</a>
</section>
</body>
</html>