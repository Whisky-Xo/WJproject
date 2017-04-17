$(function() {
	//votePage页
	$('#count').click(function(event) {
		$(this).attr("disabled","disabled");//投票按钮只能点击一次
		poi();
	});
	$('.bestrow').click(function(event) {
		$(this).css('display', 'none');
		window.location.reload();
	});
	$('.search_wrap input').focus(function(event) {
		$(this).val('');
	});
	
	function poi(){
		var id = $("#team_id").val();
		 $.ajax({
			    url:"vote.do?id="+id,
			    type:"post",
				dataType:"json",
				success:function(data){					
					//loading取消
				    //document.body.removeChild(circle.canvas);
				   /*if(data.code == 0 || data.code == -1){
					   $('.bestrow > div').text(data.desc);
					   $('.bestrow').css('display', 'block');
					  
				   }else{
					   $(this).removeAttr("disabled");
				   }*/
				   if (data.code == 0) {
				   	$('.bestrow div img').attr('src', 'img/tp_su.png');
				   	$('.bestrow').css('display', 'block');
				   } else if(data.code == -1){
				   	$('.bestrow div img').attr('src', 'img/tp.png');
				   	$('.bestrow').css('display', 'block');
				   }else if(data.code == -2){
				   	alert('投票已结束！');
				   } else{
					   $(this).removeAttr("disabled");
				   };
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {					
			        alert("请求超时，请重试");
			        $(this).removeAttr("disabled");
			      } 
		      });
	}
});