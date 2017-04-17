<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>充值</title>
    <link rel="stylesheet" href="/css/ladda.min.css">
    <script src="/js/spin.min.js"></script>
    <script src="/js/ladda.min.js"></script>
    <script src="/js/jquery-1.11.2.min.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .16rem;}
        div.box{
            background: #fff;
            height:.43rem;
            width: 3.6rem;
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            line-height: .43rem;
            text-align: left;
            padding-left: .15rem;
        }
        .amout{
            margin-bottom: .13rem;
        }
        img#wechat{
            height: auto;
            width: .28rem;
            vertical-align: middle;
        }
        #checked{
            height: .28rem;
            width: auto;
            vertical-align: middle;
            margin-left: 2.58rem;
        }
        .btn{
            margin: 0 auto;
            margin-top: 1rem;
            background: #44cc5b;
            width: 3.13rem;
            height: .42rem;
            display: block;
            border-radius: 8px;
            color: #efeff4;
            text-align: center;
            line-height:.42rem;
            padding:0;
            font-size: .16rem;
        }
        input[type="text"]{
            width:2.8rem;
            height:100%;
            /*float: right;*/
            border: transparent;
            outline:none;
            font-size: .15rem;
            color: #666666;
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
<section >
    <form  action=""  method="post" enctype="">
    <div class="amout box">
        <span>充值金额</span>
        <input type="text" id="amount" name="amount" placeholder="请输入充值金额" autofocus required/>
    </div>
    <div class="box">
        <img id="wechat" src="img/wechat.gif"/>
        微信
        <img id="checked" src="img/checked_09.gif"/>
    </div>
        <p class="tip_msg">输入错误提示信息</p>

</form>
   <a id="paybtn" class="ladda-button btn " data-color="green" data-style="zoom-in"  href="#">立即充值</a>


</section>
<script>
    // Bind normal buttons
    Ladda.bind( '.ladda-button', { timeout: 2000 } );
    
    //加载
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '${appId}', // 必填，公众号的唯一标识
    timestamp: ${timestamp}, // 必填，生成签名的时间戳
    nonceStr: '${noncestr}', // 必填，生成签名的随机串
    signature: '${signature}',// 必填，签名，见附录1
    jsApiList: [
    'checkJsApi',
            'chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
wx.ready(function(){
//alert(location.href.split('#')[0]);
//支付
});
wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
});
wx.checkJsApi({
    jsApiList: ['chooseWXPay'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
    success: function(res) {
    //alert("检测接口:"+res.err_msg);
    }
    });


$('#paybtn').on('click',function(){
    $.ajax({
        url:'/order.do',
        type : "get",
        data : {
        	"amount":parseInt($('#amount').val()*100),
            "timestamp" : new Date().getTime()
        },
        success : function(response) {
            var obj = eval('(' + response + ')'); 
            WeixinJSBridge.invoke('getBrandWCPayRequest',{  
                "appId" : obj.appId,                  //公众号名称，由商户传入  
                "timeStamp":obj.timestamp,          //时间戳，自 1970 年以来的秒数  
                "nonceStr" : obj.nonce,         //随机串  
                "package" : obj.packageName,      //商品包信息</span>  
                "signType" : obj.signType,        //微信签名方式
                "paySign" : obj.signature           //微信签名  
                },function(res){      

                if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
                    alert('支付成功');  
                }
            });  
        }     
    });         
});
    
</script>
</body>
</html>