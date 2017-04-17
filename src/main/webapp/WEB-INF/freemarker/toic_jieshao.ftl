<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>频道介绍</title>
    <link rel="stylesheet" href="css/mui.min.css">
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/mui.min.js"></script>
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
        .tt1{
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
        .box_text{
            background: #fff;
           /* border-top: 1px solid #d9d9d9;*/
            padding: .04rem 0;
            color: #666666;
            line-height: .21rem;
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
           font-size: .16rem;
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
        .cancel{
            background: #cccccc;
            margin-top: .17rem;
            color: #666666;
        }
        .clearfix{ zoom:1;}
        .clearfix:after{ display:block; content:''; clear:both;}

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
        <p class="box_text">${topic.idesc}</p>
        <hr/>
        <div class="zan clearfix">
            <span class="fabu tt1">今日稿费</span>
            <span class="amout_real">${topic.gaofeiToday}</span>
        </div>
        <hr/>
        <div class="zan clearfix">
            <span class="fabu tt1">累计稿费</span>
            <span class="amout_real">${topic.gaofeiTotal}</span>
        </div>
        <hr/>
        <div class="zan clearfix">
            <span class="fabu tt1">订阅人数</span>
            <span class="amout_real">${topic.dingyueNum}</span>
        </div>
        <hr/>
        <div onclick="location.href='topic.html?id=${topic.id}'" class="zan clearfix">
            <span class="fabu tt1">文章排行</span>
            <span  id="back_btn">> </span>
        </div>
    </div>
    <#if dingyue??>
    <a class="btn cancel" href="quxiaoDingyue.do?tid=${topic.id}">取消订阅</a>
    <#else>
    <a class="btn" href="dingyueTopic.do?tid=${topic.id}">订阅</a>
    </#if>
</section>
</body>
<script type="text/javascript">
	$(function(){
		var msg = getQueryString("msg");
		if(msg && msg != null){
			if(msg == 1){
				mui.toast("取消订阅成功");
			}else{
				mui.toast("订阅成功");
			}
		}
	});
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	}
	
</script>
</html>