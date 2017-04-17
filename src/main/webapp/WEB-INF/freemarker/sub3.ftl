<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>谁给我讲个冷笑话</title>
  <link rel="stylesheet" href="css/mui.min.css">
    <script src="js/mui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/app.css" />
	<script src="js/jquery-1.11.2.min.js"></script>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .16rem;}
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
            height: 1.2rem;
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
            text-align: left;
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
            margin-left: 2.8rem;
            position:absoulte;
            z-index: 100000;
        }

        hr{
            opacity: 0;
        }
        .user_nip{
            font-size: .13rem;
            line-height:.2rem;
            color: #999999;
        }
        .user_time{
           font-size: .12rem;
            color: #999999;
        }
        .mui-table-view{
            background: transparent;
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
        
        $(function(){
			$("body").delegate(".icon-del","click",function(){
				console.log("Click Event");
				alert(1111);
			})	
		});
        

//动态加载 下拉刷新
        mui.init({
            pullRefresh: {
                container: '#pullrefresh',
                down: {
                    callback: pulldownRefresh
                },
                up: {
                    contentrefresh: '正在加载...',
                    callback: pullupRefresh
                }
            }
        });
        
        
        mui("#pullrefresh").on('tap','.box_title',function(){
			var cId = this.getAttribute("myid");
			var params = {};
			params['cId'] = cId
			$.get("dianzan.do",params, function(data){
			  	if(data.code == 0){
			  		this.style.background="url('img/cha_checked.png') no-repeat";
                    this.style.backgroundSize='contain';
                    this.style.webkitBackgroundSize='contain';
			  		mui.toast('点赞成功');
			  	}
			},'json');
		});




        var arr=[
            {'title': '小小向日葵new1','date':'45分钟前','price':'￥32','url':'img/article_pic_06.jpg',
            'text':'即使我是一棵仙人球，也偶尔需要用雨水浇灌，哪怕只有一滴二滴三滴四滴……至少，让我有勇气和信心去企盼那迷人的雨季'} ,
            {'title': '新增名称2','date':'25分钟前','price':'￥52','url':'img/article_pic_06.jpg',
             'text':'即使我是一棵仙人球，也偶尔需要用雨水浇灌，哪怕只有一滴二滴三滴'} ,
            {'title': '最后一个名称','date':'95分钟前','price':'￥82','url':'img/article_pic_06.jpg',
                'text':'即使我是一棵仙人球，也偶尔需要用雨水浇灌，哪怕只有一滴二滴三滴'} ,
        ]
        
        var start = 0;
        /**
         * 下拉刷新具体业务实现
         */

        function pulldownRefresh() {
        	
        	var section = document.body.querySelector('#main_content');
    		var params = {};
    		if(start > 0){
    			params['start'] = 0;
    		}
    		var topic = getQueryString('topic');
    		if(topic && topic != null){
    			params['topicId'] = topic;
    		}
    		var uid = getQueryString('uid');
    		if(uid && uid != null){
    			params['uid'] = uid;
    		}
    		$.get("list.do",params, function(data){
    		  	if(data.data && data.data.length>0){
    		  		for(var i=0;i<data.data.length;i++){
    		  			var item = data.data[i];
    		  			var dstr = getDateDiff(item.createTime);
    		  			var div = document.createElement('div');
                        div.className = 'box';
                        var title_div = document.createElement('div');
                        title_div.className = 'box_title';
                        title_div.innerHTML ='<span class="head" ><img uid="'+item.uid+'" src="'+item.userPic+'" style="width:.38rem;height:.35rem"></span><span class="user_nip">'+item.username+'</span><br/><span class="user_time" >'+dstr+'</span>';
                        var cHtml = "";
                        if(item.pic && item.pic.length>0){
                        	cHtml += '<hr/><img class="article_pic" src="http://ks3-cn-beijing.ksyun.com/aaread/'+item.pic+'"/>';
                		}
                        cHtml += '<p class="box_text">'+item.content+'</p> <hr/>';
                        
                        div.innerHTML = cHtml;
                        var zan_div = document.createElement('div');
                        zan_div.className = 'zan';
                        //zan_div.innerHTML = ('￥0'+'<span myid="'+item.id+'" class="icon icon-del"></span>');
                        
                        var textNode = document.createTextNode("￥0");
                        zan_div.insertBefore(textNode ,zan_div.firstChild);
                        var zan_span = document.createElement('span');
                        zan_span.className = 'icon icon-del';
                        zan_div.appendChild(zan_span);
                        
                        div.insertBefore(title_div,div.firstChild);
                        div.appendChild(zan_div);
                         div.onclick = function(){alert("haha23")};
                        //下拉刷新，新纪录插到最前面；
                        section.insertBefore(div, section.firstChild);
                        start = data.data[i].id;
    		  		}
    		  		 mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
    		  	}else{
    		  		 mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
    		  	}
    		},'json');
        }

        var count = 0;
      
        /**
         * 上拉加载具体业务实现
         */
        function pullupRefresh() {
        	
        	var section = document.body.querySelector('#main_content');
    		var params = {};
    		if(start > 0){
    			params['start'] = start
    		}
    		var topic = getQueryString('topic');
    		if(topic && topic != null){
    			params['topicId'] = topic;
    		}
    		var uid = getQueryString('uid');
    		if(uid && uid != null){
    			params['uid'] = uid;
    		}
    		$.get("list.do",params, function(data){
    		  	if(data.data && data.data.length>0){
    		  		for(var i=0;i<data.data.length;i++){
    		  			var item = data.data[i];
    		  			var dstr = getDateDiff(item.createTime);
    		  			 var div = document.createElement('div');
    	                    div.className = 'box';
    	                    var title_div = document.createElement('div');
    	                    title_div.className = 'box_title';
    	                    title_div.innerHTML ='<span class="head"><img uid="'+item.uid+'" src="'+item.userPic+'" style="width:.38rem;height:.35rem"></span><span class="user_nip" >'+item.username+'</span><br/><span class="user_time" >'+dstr+'</span>'
    	                    var cHtml = "";
                            if(item.pic && item.pic.length>0){
                            	cHtml += '<hr/><img class="article_pic" src="http://ks3-cn-beijing.ksyun.com/aaread/'+item.pic+'"/>';
                    		}
                            cHtml += '<p class="box_text">'+item.content+'</p> <hr/>';
                            div.innerHTML = cHtml;
    	                    
    	                    var zan_div = document.createElement('div');
    	                    zan_div.className = 'zan';
    	                   // zan_div.innerHTML =  ('￥0'+'<span myid="'+item.id+'" class="icon icon-del"></span>');
    	                   var textNode = document.createTextNode("￥0");
                       	    zan_div.insertBefore(textNode ,zan_div.firstChild);
    	                    var zan_span = document.createElement('span');
	                        zan_span.className = 'icon icon-del';
	                        zan_div.appendChild(zan_span);
	                        
    	                    div.insertBefore(title_div,div.firstChild);
    	                    div.appendChild(zan_div);
    	                    //上拉刷新，新纪录插到最后面；
    	                    section.appendChild(div);
    	                    start = data.data[i].id;
    		  		}
    		  		 mui('#pullrefresh').pullRefresh().endPullupToRefresh(false);
    		  	}else{
    		  		 mui('#pullrefresh').pullRefresh().endPullupToRefresh(true);
    		  	}
    		},'json');
        }
        if (mui.os.plus) {
            mui.plusReady(function() {
                setTimeout(function() {
                    mui('#pullrefresh').pullRefresh().pullupLoading();
                }, 1000);

            });
        } else {
            mui.ready(function() {
                mui('#pullrefresh').pullRefresh().pullupLoading();
            });
        }
       // pulldownRefresh()
       
    function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	}
	function getDateDiff(dateTimeStamp){
		var minute = 1000 * 60;
		var hour = minute * 60;
		var day = hour * 24;
		var halfamonth = day * 15;
		var month = day * 30;
		var now = new Date().getTime();
		var diffValue = now - dateTimeStamp;
		if(diffValue < 0){return;}
		var monthC =diffValue/month;
		var weekC =diffValue/(7*day);
		var dayC =diffValue/day;
		var hourC =diffValue/hour;
		var minC =diffValue/minute;
		if(monthC>=1){
			result="" + parseInt(monthC) + "月前";
		}
		else if(weekC>=1){
			result="" + parseInt(weekC) + "周前";
		}
		else if(dayC>=1){
			result=""+ parseInt(dayC) +"天前";
		}
		else if(hourC>=1){
			result=""+ parseInt(hourC) +"小时前";
		}
		else if(minC>=1){
			result=""+ parseInt(minC) +"分钟前";
		}else
		result="刚刚";
		return result;
	}
	function getDateTimeStamp(dateStr){
	 return Date.parse(dateStr.replace(/-/gi,"/"));
	}
       
    </script>
</head>
<body>
<header></header>
<section id="pullrefresh" class="mui-content mui-scroll-wrapper">
    <div class="mui-scroll">
   
    <div id="main_content" class="mui-table-view mui-table-view-chevron">
    
    
	</div>
    </div>
</section>
</body>
</html>