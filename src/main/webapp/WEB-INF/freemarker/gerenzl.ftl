<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>个人资料</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family:'黑体';}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .15rem;color:#666;}
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
        .tt1,.personal{
            color: #333;
            font-size: .17rem;
        }
        .personal{
            margin-bottom: .1rem;
        }
        .intro p{
            width: 97%;
            float: left;
        }
        .back_btn{
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
            float: right;
            overflow: hidden;
            vertical-align: middle;
          }
        .box_text{
            background: #fff;
           /* border-top: 1px solid #d9d9d9;*/
            padding: .04rem 0;
            color: #666666;
            line-height: .18rem;
        }
        .box_title{
            height:.62rem;
            line-height: .62rem;
        }
        hr{
            width: 3.59rem;
            height:1px;
            border:none;
            border-top:1px solid #d9d9d9;
            margin:.08rem 0;
        }
        .zan{
            height:.26rem;
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
            <span class="back_btn">> </span>
            <span class="head"><img src="${user.pic}" style="width:.61rem;height:.61rem"></span>
            <span style="font-size: .16rem;line-height:.2rem;">头像</span>
        </div>
        <hr/>
        <div class="zan" onclick="location.href='input.html?name=name'">
            <span class="fabu tt1">名字</span>
            <span class="amout_real">${user.name}</span>
            <span class="back_btn">> </span>
        </div>
        <hr/>
        <div class="zan" onclick="location.href='input.html?name=position'">
            <span class="fabu tt1">位置</span>
            <span class="amout_real">${user.position}</span>
            <span class="back_btn">> </span>
        </div>
        <hr/>
        <div class="zan" onclick="location.href='input.html?name=sex'">
            <span class="fabu tt1">性别</span>
            <span class="amout_real"><#if user.sex==1>男<#elseif user.sex==0>女</#if></span>
            <span class="back_btn">> </span>
        </div>
        <hr/>
        <p class="box_text" onclick="location.href='input.html?name=idesc'">
            <div class="personal">个人简介</div>
            ${user.idesc}
        </p>

    </div>

</section>
</body>
</html>