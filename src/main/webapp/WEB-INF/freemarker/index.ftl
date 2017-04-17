<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>谁给我讲个冷笑话</title>
    <link rel="stylesheet" href="css/mui.min.css">
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/mui.min.js"></script>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .15rem;}
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
        .intro{
            height:.53rem;
            color: #999999;
        }
        .intro p{
            width: 97%;
            float: left;
        }
        #back_btn{
            float: right;
            width:.1rem;
            height:.18rem;
            line-height:.53rem ;
            font-size: .17rem;
        }
        .head{
            background: url('img/head1.jpg');
            display: inline-block;
            width:.38rem;
            height:.35rem;
            -webkit-background-size: contain;
            background-size: contain;
            float: left;
            margin-right: .19rem;
        }
        .head span{
            float: left;
        }
        #more{
            float: right;
            line-height:.0001rem ;
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
           /* margin-bottom: .1rem;*/
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
        .article_pic{
            width:100%;
            height:1.94rem;
        }
        .icon{
            display: inline-block;
            width: .15rem;
            height:.18rem;
            overflow: hidden;
            line-height: .32rem;
            vertical-align: middle;
        }

        .icon-del {
            background: url('img/hao.png') no-repeat ;
            background-size: contain;
            -webkit-background-size: contain;
        }
        .icon-dislike {
            background: url('img/cha.png') no-repeat ;
            background-size: contain;
            -webkit-background-size: contain;
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
           // window.addEventListener('resize',change,false);
        })();

    </script>
</head>
<body>
<header></header>
<section>
    <div class="mui-content"></div>
</section>
<footer>
    <ul class="footer_bar">
        <li><a class="active" href="#">首页</a></li>
        <li><a  href="dingyue.html">订阅</a></li>
        <li><a  href="msg.html">信息</a></li>
        <li><a  href="my.html">我的</a></li>
    </ul>
</footer>
</body>
<script type="text/javascript">
		//启用双击监听
		mui.init({
			gestureConfig:{
				doubletap:true
			},
			subpages:[{
				url:'sub3.html',
				id:'sub3.html',
				styles:{
					top: '0px',
					bottom: '.51rem',
				}
			}]
		});
	
		var contentWebview = null;
		document.querySelector('header').addEventListener('doubletap',function () {
			if(contentWebview==null){
				contentWebview = plus.webview.currentWebview().children()[0];
			}
			contentWebview.evalJS("mui('#pullrefresh').pullRefresh().scrollTo(0,0,100)");
		});
	</script>
</html>