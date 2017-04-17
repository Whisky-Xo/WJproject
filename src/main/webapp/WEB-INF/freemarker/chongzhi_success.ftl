<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>充值成功</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .16rem;}
        div.amout{
            background: #fff;
            height:.43rem;
            width: 3.6rem;
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            line-height: .45rem;
            text-align: left;
            padding-left: .15rem;
        }
        .amout{
            margin-bottom: .28rem;
            margin-top: .08rem;
        }
        .amout_real{
            font-size: .15rem;
            margin-right: .15rem;
            color: #666666;
            float: right;
        }
      img{
         height: .77rem;
         width:.77rem;
       }
     .img{
        margin: 0 auto;
        text-align: center;
        padding-top: .15rem;
      }
        .img p{
            line-height: .42rem;
            font-size: .2rem;
        }
        .btn{
            margin: 0 auto;
           /* margin-top: 1rem;*/
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
    <div class="img"><img src="img/ok_09.png"/>
        <p>充值成功</p>
    </div>

    <div class="amout ">
        <span>充值金额</span>
        <span class="amout_real">￥1700元</span>
    </div>

    <a class="btn" href="#">完成</a>
</section>
</body>
</html>