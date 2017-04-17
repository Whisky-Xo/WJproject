<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="/css/app.css" />
		<style>
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
		</style>
	</head>

	<body>
		<div id="refreshContainer" class="mui-content  mui-scroll-wrapper">
		<div class="mui-scroll">
			<ul id="data_list" class="mui-table-view mui-table-view-chevron">
      			<!--第一个段落-->
			    
    		</ul>
			<div class="mui-card">
				
			</div>
		</div>
		</div>
		<script src="/js/mui.min.js"></script>
		<script src="/js/jquery-1.11.2.min.js"></script>
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
	mui(".mui-table-view").on('tap','.mui-card-content',function(){
	 	var id = this.getAttribute("myid");
	 	window.parent.location.href = 'neirong.html?id='+id;
	});
	mui(".mui-table-view").on('tap','.mui-card-header',function(){
	 	var uid = this.getAttribute("uid");
	 	window.parent.location.href = 'taren.html?uid='+uid;
	});
	
	mui(".mui-table-view").on('tap','.mui-card-link',function(){
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
		  			div.className = 'mui-card';
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
		html.push('<div uid="'+data.uid+'" class="mui-card-header mui-card-media">');
		html.push('<img src="'+data.userPic+'" />');
		html.push('<div class="mui-media-body">');
		html.push(data.username);
		html.push('<p>发表于 '+dstr+'</p>');
		html.push('</div>');
		html.push('</div>');
		html.push('<div myid="'+data.id+'"  class="mui-card-content" >');
		if(data.pic && data.pic.length>0){
			html.push('<img src="http://ks3-cn-beijing.ksyun.com/aaread/'+data.pic+'" alt=""  style="width:100%;height:10.94rem"/>');
		}
		html.push('<div class="mui-card-content-inner">');
		html.push('<p style="color: #333;">'+data.content+'</p>');
		html.push('</div>');
		html.push('</div>');
		html.push('<div class="mui-card-footer">');
		html.push('<a myid="'+data.id+'" class="mui-card-link"><img class="icon-del" src="img/hao.png" /></a>');
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
	</body>

</html>