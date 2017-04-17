
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>发布</title>
    <link rel="stylesheet" href="css/ladda.min.css">
    <script src="js/spin.min.js"></script>
    <script src="js/ladda.min.js"></script>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/ajaxfileupload_pledge.js"></script>
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
            width: 3.45rem;
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            line-height: .43rem;
            text-align: left;
            padding-left: .15rem;
            padding-right: .15rem;
        }
        .main_text{
            /*height:1.86rem;*/
             padding:.1rem .15rem;
            border-bottom: 1px solid #d9d9d9;
             border-top: 1px solid #d9d9d9;
            line-height: .43rem;
            text-align: left;
            background: #fff;

        }
        .add{
            height:.91rem;
            width:.91rem;
            margin-left: .15rem;
            margin-top: .15rem;
            position: relative;
            border: 1px solid #d9d9d9;
            padding: 0;
            background: #fff;
        }
        .add img{
        	height:.90rem;
            width:.90rem;
            position:absolute; left:0;top:0;right:0;bottom:0; margin: auto;
        }
        .amout{
            margin-bottom: .13rem;
        }
        input[type="text"]{
            width:100%;
            border: transparent;
            outline:none;
            font-size: .15rem;
            color: #666666;
        }
        textarea{
          width:100%;
          border: transparent;
          outline:none;
          font-size: .15rem;
          color: #666666;
      }
        .ui_button {
            display: inline-block;
            height: .21rem;
            width: .21rem;
            text-align: center;
            position:absolute; left:0;top:0;right:0;bottom:0; margin: auto;
            background: url('img/add_03.jpg');
            background-repeat: no-repeat;
            background-position: center;
            background-size: contain;
            -webkit-background-size: contain;
            outline: 0 none;
            overflow: visible;
        }
        .btn{
            margin: 0 auto;
            margin-top: .75rem;
            background: #44cc5b;
            width: 3.13rem;
            height: .42rem;
            display: block;
            border-radius: 8px;
            color: #efeff4;
            text-align: center;
            line-height:.42rem;
            border: transparent;
            outline: none;
            font-size: .18rem;
            -webkit-appearance: none;
            padding: 0;

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

    <form id="myform" action="" method="post" enctype="multipart/form-data" target="">
    	 <input type="hidden" name="pic" id="pic"/>
        <div class="amout box ">
            <input type="text" name="title" placeholder="标题" value="标题" autofocus required/>
        </div>

        <div class="main_text ">
           <textarea  rows="10" name="content" >正文</textarea>
        </div>
       <div id="fileList" class="add" onClick="chooseFile2();" >
           <label class="ui_button "></label>
       </div>
       <input id="fileId" onchange="handleFiles(this);" type="file" accept="image/*" name="myFile" style="position:absolute;clip:rect(0 0 0 0);" />
     </form>
        <a class="ladda-button btn" data-color="green" data-style="zoom-in"  onClick="fabu();">发布</a>
</section>
<script>

	   //加载
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '${appId}', // 必填，公众号的唯一标识
	    timestamp: ${timestamp}, // 必填，生成签名的时间戳
	    nonceStr: '${noncestr}', // 必填，生成签名的随机串
	    signature: '${signature}',// 必填，签名，见附录1
	    jsApiList: [
	    'checkJsApi',
	            'chooseImage','uploadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function(){
	//alert(location.href.split('#')[0]);
	//支付
	});
	wx.error(function(res){
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});
	wx.checkJsApi({
	    jsApiList: ['chooseImage','uploadImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
	    success: function(res) {
	    //alert("检测接口:"+res.err_msg);
	    }
	});

	// Create a new instance of ladda for the specified button
	var l = Ladda.create( document.querySelector( '.ladda-button' ) );
	function fabu(){
		// Start loading
		l.start();
		// Will display a progress bar for 50% of the button width
		//l.setProgress( 0.5 );
		var fileList = document.getElementById("fileList");
		var old = fileList.getElementsByTagName("img");
		if(old && old.length>0){
			var localId = old[0].src;
			if (localId.indexOf("wxlocalresource") != -1) {
				localId = localId.replace("wxlocalresource", "wxLocalResource");
			}
			wx.uploadImage({
			    localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
			    isShowProgressTips: 0, // 默认为1，显示进度提示
			    success: function (res) {
			        var serverId = res.serverId; // 返回图片的服务器端ID
			        $("#pic").val(serverId);
			        fabuSubmit();
			    }
			});
		}else{
			fabuSubmit();
		}
	}
	
	function fabuSubmit(){
		var url = "save2.do";
		var data = $("#myform").serializeJson();
	    $.ajaxFileUpload({ 
	        url:url, 
	        data:data,
	        secureuri:false, 
	        fileElementId:['fileId'],        //file的id  
	        dataType:"json",                  //返回数据类型为文本  
	        success:function(data,status){ 
	        	if(data.code == 0){
	        		window.location.href="xuanzetopic.html?id="+data.data;
	        	}
	        	//alert(data.msg);
	        	// Stop loading
				l.stop();
				// Toggle between loading/not loading states
				l.toggle();
				// Delete the button's ladda instance
				l.remove();
	        },
	        error:function(xhr, status, e){
	        	l.stop();
				// Toggle between loading/not loading states
				l.toggle();
				// Delete the button's ladda instance
				l.remove();
	        }
	    });
	}
	
    // Bind normal buttons
    //Ladda.bind( '.ladda-button', { timeout: 2000 } );
        
        
    function chooseFile(){
		var fileInput = document.getElementById("fileId");//隐藏的file文本框ID 
		fileInput.click();
	}
    function chooseFile2(){
		wx.chooseImage({
		    count: 1, // 默认9
		    sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
		    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		    success: function (res) {
		       // var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		       var img = new Image();
		       img.src = res.localIds[0];
		       var fileList = document.getElementById("fileList");
		       var old = fileList.getElementsByTagName("img");
		        if(old && old.length>0){
	            	fileList.replaceChild(img,old[0]);
	            }else{
	            	fileList.appendChild(img);
	            }
		    }
		});
	}
	
	

	window.URL = window.URL || window.webkitURL;
    var fileElem = document.getElementById("fileElem"),
    fileList = document.getElementById("fileList");
    function handleFiles(obj) {
        var files = obj.files,
        img = new Image();
        if(window.URL){
            //File API
          //  alert(files[0].name + "," + files[0].size + " bytes");
            img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
            img.onload = function(e) {
                window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
            }
            var old = fileList.getElementsByTagName("img");
            if(old && old.length>0){
            	fileList.replaceChild(img,old[0]);
            }else{
            	fileList.appendChild(img);
            }
            
        }else if(window.FileReader){
            //opera不支持createObjectURL/revokeObjectURL方法。我们用FileReader对象来处理
            var reader = new FileReader();
            reader.readAsDataURL(files[0]);
            reader.onload = function(e){
                alert(files[0].name + "," +e.total + " bytes");
                img.src = this.result;
               	var old = fileList.getElementsByTagName("img");
            	if(old && old.length>0){
            		fileList.replaceChild(img,old[0]);
            	}else{
            		fileList.appendChild(img);
            	}
            }
        }else{
            //ie
            obj.select();
            obj.blur();
            var nfile = document.selection.createRange().text;
            document.selection.empty();
            img.src = nfile;
            img.onload=function(){
                //alert(nfile+","+img.fileSize + " bytes");
            }
           	var old = fileList.getElementsByTagName("img");
            if(old && old.length>0){
            	fileList.replaceChild(img,old[0]);
            }else{
            	fileList.appendChild(img);
            }
        }
    }
    
    
        
    </script>

</body>
</html>