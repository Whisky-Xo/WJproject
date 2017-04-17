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
            window.addEventListener('resize',change,false);
        })();


    </script>
</head>
<body>
<header></header>
<section>
    <div id="refreshContainer" class="mui-content mui-scroll-wrapper">
  		<div class="mui-scroll">
    		<!--数据列表-->
    		<ul id="data_list" class="mui-table-view mui-table-view-chevron">
      			<!--第一个段落-->
			    
    		</ul>
  		</div>
	</div>
</section>
</body>
<script type="text/javascript">

mui.init({
	  pullRefresh : {
	    container:refreshContainer,//待刷新区域标识，querySelector能定位的css选择器均可，比如：id、.class等
	    up : {
	      height:50,//可选.默认50.触发上拉加载拖动距离
	      auto:true,//可选,默认false.自动上拉加载一次
	      contentrefresh : "正在加载...",//可选，正在加载状态时，上拉加载控件上显示的标题内容
	      contentnomore:'没有更多数据了',//可选，请求完毕若没有更多数据时显示的提醒内容；
	      callback :pullfreshFunction //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
	    }
	  }
	});
	mui(".mui-table-view").on('tap','img',function(){
	 	var id = this.getAttribute("myid");
	 	if(id && id != null){
	 		window.parent.location.href = 'neirong.html?id='+id;
	 	}else{
	 		var uid = this.getAttribute("uid");
	 		window.parent.location.href = 'taren.html?uid='+uid;
	 	}
	 	
	 	
	});
	mui(".mui-table-view").on('tap','.box_text',function(){
		var id = this.getAttribute("myid");
		window.parent.location.href = 'neirong.html?id='+id;
	});
	mui(".mui-table-view").on('tap','.icon-del',function(){
		var cId = this.getAttribute("myid");
		var params = {};
		params['cId'] = cId
		$.get("dianzan.do",params, function(data){
		  	if(data.code == 0){
		  		mui.toast('点赞成功')
		  	}
		},'json');
	});
	

	var start = 0;
	function pullfreshFunction(){
		var obj= this;
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
		  		var table = document.body.querySelector('.mui-table-view');
		  		for(var i=0;i<data.data.length;i++){
		  			var div = document.createElement('div');
		  			div.className = 'box';
		  			div.innerHTML = reader(data.data[i]);
		  			table.appendChild(div);
		  			start = data.data[i].id;
		  		}
		  		obj.endPullupToRefresh();
		  	}else{
		  		obj.endPullupToRefresh(false);
		  	}
		},'json');
		//this.endPullupToRefresh(false);
	}

	function reader(data){
		var html = [];
		var dstr = getDateDiff(data.createTime);
		html.push('<div class="box_title">');
		html.push('<span class="head"><img uid="'+data.uid+'" src="'+data.userPic+'" style="width:.38rem;height:.35rem"></span>');
		html.push('<span style="font-size: .16rem;line-height:.2rem;">'+data.username+'</span><br/>');
		html.push('<span style="font-size: .12rem;color: #999999;">'+dstr+'</span>');
		html.push('<span id="more">');
		html.push('<span class="circle"></span>');
		html.push('<span class="circle"></span>');
		html.push('<span class="circle"></span>');
		html.push('</span>');
		html.push('</div>');
		html.push('<hr/>');
		if(data.pic && data.pic.length>0){
			html.push('<img class="article_pic" myid="'+data.id+'" src="http://ks3-cn-beijing.ksyun.com/aaread/'+data.pic+'"/>');
		}
		
		html.push('<p class="box_text" myid="'+data.id+'">');
		html.push(data.content);
		html.push('</p>');
		html.push('<hr/>');
		html.push('<div class="zan">');
		//html.push('￥32');
		html.push('<span myid="'+data.id+'"  class="icon icon-del"></span>');
		html.push('|');
		html.push('<span myid="'+data.id+'"  class="icon icon-dislike"></span>');
		html.push('</div>');
		return html.join('');
	}
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
</html>