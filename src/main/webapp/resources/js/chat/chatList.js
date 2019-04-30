document.write("<script type='text/javascript' src='/js/utill/dateformat.js'><"+"/script>");
document.write("<script type='text/javascript' src='/js/chat/chatWebSocket.js'><"+"/script>");
document.write("<script type='text/javascript' src='/js/channel/attendChannel.js'><"+"/script>");

//1:1 채팅 내용 리스트 EVENT 
$(document).on("click",".fmTbableBody > ul > li > div.allList",userChatList);
$(document).on("click",".msgList ul > li > div",userChatList);

function userChatList(){
	 var uctoid = $(this).find("input[type='hidden']").val();
	  $.ajax({
	 	url:"chat/list",
		type:"POST",
		data:{uctoid:uctoid},
		success:chatList,
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}

function chatList(xhr){	
	var data = xhr.data;
	var toid = xhr.toid;
	// js/chat/chatWebSocket 메시지 보내는 websocket
	$('.mainContent').empty();
	$('.mainContent')
	.html('<div class="userChat">'+
			'<div class="headLine">'+
				'<div class="chatName">'+ 
					'<h2><i class="fas fa-at"></i> <span>'+toid.uname+'</span>'+
					'<span class="fmStatus'+toid.umystatus+' fmuStatus'+toid.ustatus+'"></span></h2>'+
				'</div>'+
			'</div>'+
			'<div class="chatMsgWrap userMsg">'+
				'<div class="chatTop">'+
					'<i class="fas fa-at"></i> <span>'+toid.uname+'</span> 님과 대화의 첫 부분입니다.'+
				'</div>'+
			'</div>'+
			'<div class="chatBottom">'+
				'<textarea placeholder="@'+toid.uname+' 에게 메시지 보내기" name="userMessage" id="userMessage"></textarea>'+
			'</div>'+
		'</div>');
		$.each(data,function(idx,cv){
			var _today = new Date(cv.ucDate);
			if(cv.ccode != null){
				var invite = '<div class="chInviteWrap"><p class="chInviteTxt">그룹 가입 초대장을 보냈습니다.</p>'+
						'<input type="hidden" name="cmaster" value="'+cv.cmaster+'" />'+
						'<input type="hidden" name="ccode" value="'+cv.ccode+'" />'+
						'<div class="chInviteBox">'+
						'<div class="serverIconTxt">'+cv.cname.substring(0,1)+'</div>'+
						'<div class="chName">'+
							'<p class="cname">'+cv.cname+'</p>'+
							'<p class="cstatus">'+
							'<span class="profile on"></span><span class="statusTxt">2명 접속중</span>'+
							'<span class="profile off"></span><span class="statusTxt">2명의 멤버</span>'+
							'</p>'+
						'</div>'+
						'<div class="inviteButton">'+
						'<button id="inviteAccept">참가</button></div>'+
						'</div>';
			}else{
				var invite ="";
			}
			$('<div class="chatMessage">')
			.append('<div class="chatIcon">'+
						'<img src="/images/'+cv.uprofilename+'">'+
					'</div>'+
					'<div class="chatInfo">'+
						'<div class="chatName">'+
							'<strong>'+cv.ucfromname+'</strong>'+
							'<span class="chatDate"> '+_today.format('yyyy-MM-dd a/p hh:mm:ss')+'</span>'+
						'</div>'+
						'<div class="chatContent">'+cv.ucmessage+
							'<span class="msgController">'+
								'<i class="fas fa-ellipsis-v"></i>'+
							'</span>'+
						'</div>'+invite+'</div>')
					.appendTo(".chatMsgWrap");
		});//each
		
		$(".chatMsgWrap").scrollTop($(".chatMsgWrap")[0].scrollHeight);
		$("#userMessage").on("keydown",textResize);
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
			 			date: d.format('yyyy-MM-dd a/p hh:mm:ss'),
			 			profile:xhr.profile
			 		  };
			 		  uchatws.send(JSON.stringify(socketMsg));
			 		  userChatSend(toid.uemail,msg);
			 		  $(this).val("");
			    }
			}
		}
}
//메시지 보내기
function userChatSend(toid,msg){
	$.ajax({
	 	url:"chat/send",
		type:"post",
		data:{uctoid:toid,ucmessage:msg},
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}


