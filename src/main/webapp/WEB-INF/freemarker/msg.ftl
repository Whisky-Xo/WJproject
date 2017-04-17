<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>消息</title>
    <script src="js/jquery-1.11.2.min.js"></script>
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
           /* padding:0  .1rem;*/
            text-align: left;
           /* margin-bottom: .15rem;*/
        }
        .intro p{
            width: 97%;
            float: left;
        }
        .back_btn{
            float: right;
            height:.18rem;
            font-size: .15rem;
            margin-left:.15rem;
            color: #999;
            line-height: .26rem;
        }
       .head{
           /* background: url('img/head2.jpg');*/
            display: inline-block;
            width:.5rem;
            height:.5rem;
            border-radius: 50%;
            -webkit-background-size: cover;
            background-size: cover;
            /*overflow: hidden;*/
           vertical-align: middle;
            margin-right: .1rem;
            float: left;
       }

        .head span{
            float: left;
        }
        span.head.p1{
            background: url('img/head1.jpg');
            -webkit-background-size: cover;
            background-size: cover;
        }
        span.head.p2{
            background: url('img/head2.jpg');
            -webkit-background-size: cover;
            background-size: cover;
        }
        span.head.p3{
            background: url('img/head3.jpg');
            -webkit-background-size: cover;
            background-size: cover;
        }
        span.head.p4{
            background: url('img/head1.jpg');
            -webkit-background-size: cover;
            background-size: cover;
        }
        span.head.p5{
            background: url('img/head2.jpg');
            -webkit-background-size: cover;
            background-size: cover;
        }
        span.head.p6{
            background: url('img/head3.jpg');
            -webkit-background-size: cover;
            background-size: cover;
        }
        .box_title{
            height:.56rem;
           /* line-height: .6rem;*/
            /*padding-top: .1rem;
            padding-bottom: .1rem;*/
            padding: .1rem;
        }
        hr{
            width: 3.65rem;
            height:1px;
            border:none;
            border-top:1px solid #d9d9d9;
            /*margin:.08rem 0;*/
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
        //点击事件
        window.addEventListener('DOMContentLoaded',function(){
            var oBox=document.querySelectorAll('.box_title');
            //第一行显示选中效果
            oBox[0].style.background='#e3e3e3'
            for(var i=0 ;i<oBox.length;i++){
            oBox[i].addEventListener('touchstart',function(ev){
                for(var j=0 ;j<oBox.length;j++){
                    oBox[j].style.background='#fff'
                }
                this.style.background='#e3e3e3'

                function fnEnd(){
                    document.removeEventListener('touchend',fnEnd,false);
                }
                this.addEventListener('touchend',fnEnd,false);
                ev.preventDefault();
                location.href='msg_item.html?other='+this.getAttribute('myid');
            },false);
            }
        })
        
    </script>
</head>
<body>
<header></header>
<section>
    <div class="box">
    <#list msgs as msg>
        <div class="box_title" myid='${msg.fuser.uid}'>
            <span class="back_btn">${msg.createTime?string('yyyy/MM/dd')}</span>
            <span class="head p1"><img src="${msg.fuser.pic}" style="width:.5rem;height:.5rem"></span>
            <span style="font-size: .16rem;line-height:.32rem;">${msg.fuser.name}</span><br/>
            <span style="font-size: .15rem;color: #999999;">${msg.content}</span>
        </div>
        <hr/>
        </#list>
    </div>

</section>
<footer>
    <ul class="footer_bar">
        <li><a href="index.html">首页</a></li>
        <li><a  href="dingyue.html">订阅</a></li>
        <li><a class="active" href="#">信息</a></li>
        <li><a href="my.html">我的</a></li>
    </ul>
</footer>
</body>
</html>