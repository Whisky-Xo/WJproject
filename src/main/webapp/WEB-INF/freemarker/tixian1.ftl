<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>提现1</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .16rem;}
        div{
            background: #fff;
            height:.43rem;
            width: 3.6rem;
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            line-height: .43rem;
            text-align: left;
            padding-left: .15rem;
        }
        .amout_real{
            font-size: .15rem;
            margin-left: .3rem;
            color: #666666;
        }

        .btn{
            margin: 0 auto;
          /*  margin-top: 1.18rem;*/
            background: #44cc5b;
            width: 3.13rem;
            height: .42rem;
            display: block;
            border-radius: 8px;
            color: #fff;
            text-align: center;
            line-height:.42rem;
            font-size: .18rem;
            border: transparent;
            outline: none;
            -webkit-appearance: none;
        }
        input[type="text"]{
            width:2.8rem;
            height:100%;
            float: right;
           border: transparent;
            outline:none;
            font-size: .15rem;
            color: #666666;
        }
        .deadline{
            color: #999;
            line-height: .4rem;
            text-align: center;
            margin-top: .2rem;
            margin-bottom: .3rem;
            font-size: .13rem;
        }
        .balance{
            font-size: .15rem;
            line-height: .37rem;
            margin-left: .12rem;
            color: #666;
        }
        .balance span{
            color:#999999;
            font-size: .13rem;
            margin-right: .05rem;
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
        .tip_msg{
            color: red;
            font-size: .12rem;
            line-height:.25rem;
            margin-left: .15rem;
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


     //点击事件
     window.addEventListener('DOMContentLoaded',function(){
         var oBox=document.querySelector('input[type="button"]');
         var alert=document.querySelector('.alert');


         oBox.addEventListener('touchstart',function(ev){
                 alert.style.display='block'

             function fnEnd(){
                 document.removeEventListener('touchend',fnEnd,false);
             }
             oBox.addEventListener('touchend',fnEnd,false);
             ev.preventDefault();
         },false);
         })
    </script>

</head>
<body>
<header></header>
<section>

    <form method="post" action="" enctype="">
        <ul>
            <li class="amout ">
                <span>提现到</span>
                <span class="amout_real">微信钱包</span>
            </li>
            <li class="amout ">
                <span>提现金额</span>
                <input type="text" name="days" placeholder="￥247"  autofocus required/>
            </li>
         </ul>
        <p class="balance"><span >账户余额</span>￥3280</p>
        <p class="tip_msg">输入错误提示信息</p>
        <p class="deadline">三个工作日内到账</p>
    <input type="submit" class="btn" value="立即提现" />
    </form>

</section>

</body>
</html>