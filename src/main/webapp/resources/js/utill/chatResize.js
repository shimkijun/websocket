/**
 * 
 */
function textResize(){
	$(this).height(1).height($(this).prop('scrollHeight')-20);
	var content = $(this).val();
	if (event.keyCode == 13) {
		event.preventDefault();
	    if (content.length > 2000){
	    	$(this).blur();
	        $('<div class="addContainer">')
			.append('<div class="DarkBack"></div>'+
			'<div class="messageSizeAlert">'+
			'<h2>메시지가 너무 길어요.</h2>'+
			'<div class="alertText">'+
			'<p>다른 분들이 불쾌해 하지 않도록 2,000자 이상은 보낼 수 없도록 했습니다.메시지 내용을 줄여주세요.</p>'+
			'</div>'+
			'<div class="alertConfirm"><button type="submit">확인</button></div>'+
			'</div>')
			.appendTo(".viewAdd");
	        $(document).on("click",".alertConfirm button",function(){
	        	$(".addContainer").remove();
	        });
	    }else if(content.length < 1){
	    	
	    }else{
	    	  var d = new Date();
	 		  if (uchatws.readyState !== 1) return;
	 		  var msg = $(this).val();
	 		  var socketMsg = {
	 			type:"message",
	 			email:toid.uemail,
	 			name:xhr.uname,
	 			msg : msg,
	 			date: d.format('yyyy-MM-dd a/p hh:mm:ss')
	 		  };
	 		  uchatws.send(JSON.stringify(socketMsg));
	 		  userChatSend(toid.uemail,msg);
	 		  $(this).val("");
	    }
	}
}