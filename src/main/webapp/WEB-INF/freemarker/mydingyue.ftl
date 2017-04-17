<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>我的订阅</title>
    <link rel="stylesheet" href="/css/ladda.min.css">
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="/js/spin.min.js"></script>
    <script src="/js/ladda.min.js"></script>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{/*margin-top: .18rem;*/ font-size: .16rem;}
        div.alert{
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
            margin-left: .35rem;
            color: #666666;
        }

        .btn{
            margin: 0 auto;
            margin-top: 1.18rem;
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
        .alert{
           display: none;
            width:1.47rem;
            height: .42rem;
            border-radius: 8px;
            color: #666;
            text-align: center;
            line-height:.42rem;
            font-size: .18rem;
            background: #ccc;
            margin: 0 auto;
            margin-top: .3rem;
            padding: 0;
        }
        .deadline{
            color: #a5a5a6;
            line-height: .42rem;
            text-align: center;
            margin-top: .1rem;
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
    </script>

</head>
<body>
<header></header>
<section>
    <p class="deadline"><span style="color:#333">到期：</span><#if user.endTime??>${user.endTime?string('yyyy/MM/dd')}</#if></p>
    <ul>
        <li class="amout ">
            <span>购买天数</span>
            <!--<span class="amout_real">银行卡</span>-->
            <input type="text" name="days" placeholder="请输入购买天数" autofocus required/>
        </li>
        <li class="amout ">
            <span>账户余额</span>
            <span>${(user.balance/100)?string('0.00')}元</span>
        </li>
        <li class="amout ">
            <span>金额</span>
            <span class="amout_real">0元</span>
        </li>
    </ul>
    <a id="paybtn" class="ladda-button btn " data-color="green" data-style="zoom-in"  href="#">订阅</a>
    <div class="alert">订阅成功</div>
</section>

</body>
<script>
Ladda.bind( '.ladda-button', { timeout: 2000 } );
$(function(){
	$("input[type='text']").keyup( function() {
		var m = $(this).val();
		var mt = m*0.1
		if(mt && mt != 'NaN'){
			mt = mt.toFixed(2);
			$(".amout_real").text(mt+'元');
		}else if(mt == 0){
			$(".amout_real").text(mt+'元');
		}
	});
	
	
	$('#paybtn').on('click',function(){
		var days = $("input[type='text']").val();
	    $.ajax({
	        url:'/dingyuepay.do',
	        type : "post",
	        data : {
	        	"days":days
	        },
	        success : function(response) {
	            var obj = eval('(' + response + ')'); 
	           if(obj.code == 0){
	           		alert("订阅成功");
	           }else{
	           		alert(obj.msg);
	           }
	        }     
	    });         
	});
	
});



</script>
</html>