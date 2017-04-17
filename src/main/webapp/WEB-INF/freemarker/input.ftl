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
			h5 {
				margin: 5px 7px;
			}
		</style>
	</head>

	<body>
		<div class="mui-content">
			<div class="mui-content-padded" style="margin: 5px;">
				<form class="mui-input-group" method="post" action="saveUser">
					<#if name!='sex'>
					<div class="mui-input-row">
						<input name="${name}" type="text" >
					</div>
					<#else>
					<div class="mui-input-row mui-radio mui-left">
						<label>男</label>
						<input name="sex" type="radio" value="1">
					</div>
					<div class="mui-input-row mui-radio mui-left">
						<label>女</label>
						<input name="sex" type="radio" value="0">
					</div>
					</#if>
					<div class="mui-button-row">
						<button type="submit" class="mui-btn mui-btn-primary" >确认</button>&nbsp;&nbsp;
						<button type="button" class="mui-btn mui-btn-danger" onclick="location.href='gerenzl.html'">取消</button>
					</div>
				</form>
				
			</div>
		</div>
		<script src="/js/mui.min.js"></script>
		<script>
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			 //语音识别完成事件
			document.getElementById("search").addEventListener('recognized', function(e) {
				console.log(e.detail.value);
			});
		</script>
	</body>

</html>