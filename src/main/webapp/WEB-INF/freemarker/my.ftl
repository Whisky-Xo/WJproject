<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>我的</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体';}
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
    width:.42rem;
    height:.42rem;
    border-radius: 50%;
    -webkit-background-size: cover;
    background-size: cover;
    overflow: hidden;
   vertical-align: middle;
    margin-right: .1rem;
}
        .head span{
            float: left;
        }
        span.headpic{
            display: inline-block;
            width:.28rem;
            height:.28rem;
            border-radius: 50%;
            -webkit-background-size: cover;
            background-size: cover;
          /*  float: left;*/
            overflow: hidden;
            vertical-align: middle;
        }
        span.headpic.p1{
            background: url('img/my1.png');
            -webkit-background-size: cover;
            background-size: cover;
        }
        span.headpic.p2{
            background: url('img/my2.png');
            -webkit-background-size: cover;
            background-size: cover;
        }
        span.headpic.p3{
            background: url('img/my3.png');
            -webkit-background-size: cover;
            background-size: cover;
        }
        span.headpic.p4{
            background: url('img/my4.png');
            -webkit-background-size: cover;
            background-size: cover;
        }

        .box_title{
            height:.6rem;
            line-height: .6rem;
        }
        .box_list{
            height:.39rem;
            line-height: .39rem;
        }

        hr{
            width: 3.59rem;
            height:1px;
            border:none;
            border-top:1px solid #d9d9d9;
            margin:.08rem 0;
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

    <!--第一个段落-->
    <div class="box" >
        <div onclick="location.href='gerenzl.html'" class="box_title">
            <span class="back_btn">> </span>
            <span class="head"><img src="${user.pic}" style="width:.42rem;height:.42rem"></span>
            <span style="font-size: .16rem;line-height:.6rem;">${user.name}</span>
        </div>
        <hr/>

        <div onclick="location.href='qianbao.html'" class="box_list">
            <span class="back_btn">> </span>
            <span class="headpic p1"></span>
            <span style="font-size: .15rem;line-height:.39rem;" onclick="location.href='qianbao.html'" >我的钱包</span>
        </div>
        <hr/>
        <div onclick="location.href='sub.html?uid=${user.uid}'" class="box_list">
            <span class="back_btn">> </span>
            <span class="headpic  p2"></span>
            <span style="font-size: .15rem;line-height:.39rem;">我的稿件</span>
        </div>
        <hr/>
        <div onclick="location.href='gaofeimx.html'" class="box_list">
            <span class="back_btn">> </span>
            <span class="headpic p3"></span>
            <span style="font-size: .15rem;line-height:.39rem;">稿费明细</span>
        </div>
        <hr/>
        <div onclick="location.href='mydingyue.html'"  class="box_list">
            <span class="back_btn ">> </span>
            <span class="headpic p4"></span>
            <span style="font-size: .15rem;line-height:.39rem;">我的订阅</span>
        </div>
        <hr/>
        <div onclick="location.href='fabu.html'"  class="box_list">
            <span class="back_btn ">> </span>
            <span class="headpic p4"></span>
            <span style="font-size: .15rem;line-height:.39rem;">发布</span>
        </div>

    </div>

</section>
<footer>
    <ul class="footer_bar">
        <li><a href="index.html">首页</a></li>
        <li><a  href="dingyue.html">订阅</a></li>
        <li><a  href="msg.html">信息</a></li>
        <li><a class="active" href="#">我的</a></li>
    </ul>
</footer>
</body>
</html>