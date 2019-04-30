document.write("<script type='text/javascript' src='/js/utill/dateformat.js'><"+"/script>");
document.write("<script type='text/javascript' src='/js/channel/chatWebSocket.js'><"+"/script>");

var cchatws;

$(document).on("click",".serverIconTxt > span:first-child",channelChat);
function channelChat(){
	 var ccode = $(this).parent().parent().find("input").val();
	 $.ajax({
		 	url:"channels/ch/"+ccode,
			type:"GET",
			success:channelChatList
			,error:function(xhr){
				alert(xhr.status);
				alert(xhr.errorText);
			}
		});
}

function channelChatList(xhr){
	
	var ch = xhr.data;
	var user = xhr.user;
	var msg = xhr.msg;
	var condition = "";
	$(".chatSearch").empty();
	$(".menuContent").empty();
	$('.mainContent').empty();
	$(".chatSearch").html('<span class="chname">'+ch.cname+'</span><span class="invite">초대</span>');
	$(".menuContent")
		.html('<div class="friendsMessageList">'+
			  '<h4>온라인</h4>'+
				  '<div class="chChatList on"><ul></ul></div>'+
			  '</div>'+
			  '<div class="friendsMessageList">'+
			  '<h4>오프라인</h4>'+
				  '<div class="chChatList off"><ul></ul></div>'+
			  '</div>');
	$('.mainContent')
	.html('<div class="userChat">'+
			'<div class="headLine">'+
				'<div class="chatName">'+ 
					'<h2><span># '+ch.cname+'</span>'+
				'</div>'+
			'</div>'+
			'<div class="chatMsgWrap channelMsg">'+
				'<div class="chatTop">'+
					'<span># '+ch.cname+'</span> 대화의 첫 부분입니다.'+
				'</div>'+
			'</div>'+
			'<div class="chatBottom">'+
				'<textarea placeholder="# '+ch.cname+' 에게 메시지 보내기" name="channelChat" id="channelChat"></textarea>'+
			'</div>'+
		'</div>');
	$('<li>')
	
	$.each(user,function(idx,us){
		var crown;
		if(us.clevel == 1){
			crown = '<span class="crown"><i class="fas fa-crown"></i></span>';
		}else{
			crown ='';
		}
		if(us.ustatus == 0 || us.ustatus == 4){
			condition = "off";
			$('<li>')
			.append('<div>'+
				  	'<input type="hidden" name="clName" id="clName" value="'+us.cuemail+'" />'+
				  	'<p class="profileIcon">'+
				  	'<img src="/images/profile_icon_2.png" /><span class="profile '+condition+'"></span>'+
				  	'</p>'+
				  	'<p class="profileName">'+us.cnick+crown+'</p>'+
			  	'</div>')
				.appendTo(".chChatList.off > ul");
		}else if(us.ustatus == 1){
			if(us.umystatus == 1){
				condition = "on";
			}else if(us.umystatus == 2){
				condition = "away";
			}else if(us.umystatus == 3){
				condition = "other";
			}
			$('<li>')
			.append('<div>'+
				  	'<input type="hidden" name="clName" id="clName" value="'+us.cuemail+'" />'+
				  	'<p class="profileIcon">'+
				  	'<img src="/images/'+us.uprofilename+'" /><span class="profile '+condition+'"></span>'+
				  	'</p>'+
				  	'<p class="profileName">'+us.cnick+crown+'</p>'+
			  	'</div>')
				.appendTo(".chChatList.on > ul");
		}
		
		});
	$(document).on("click",".invite",function(){
		$('<div class="addContainer">')
		.append('<div class="DarkBack"></div>'+
		'<div class="inviteWrap">'+
		'<h2> 친구를 '+ch.cname+' 그룹으로 초대</h2>'+
		'<p class="linkText">이 링크를 공유하면 다른 사람들이 서버에 들어올 수 있습니다!</p>'+
		'<div class="inviteInput">'+
		'<div>'+
		'<input type="text" id="ccode" value="'+ch.ccode+'" /><button id="inviteCopy">복사</button>'+
		'</div>'+
		'</div>'+
		'<div class="inviteBottom">'+
		'<p>이 링크는 영구적 입니다.</p>'+
		'</div>'+
		'</div>')
		.appendTo(".viewAdd");
	});

	$(document).on("click","#inviteCopy",function(){
		var copy = $("#ccode");
		copy.select();
		document.execCommand("copy");
		$(this).text("복사됨").addClass("copy");
		$(".inviteInput > div").addClass("copy");
		setTimeout(function(){ 
			$("#inviteCopy").text("복사").removeClass("copy");
			$(".inviteInput > div").removeClass("copy");
		},1000);
	});
	
	$.each(msg,function(idx,cv){
		var _today = new Date(cv.ccdate);
		$('<div class="chatMessage">')
		.append('<div class="chatIcon">'+
					'<img src="/images/'+cv.uprofilename+'">'+
				'</div>'+
				'<div class="chatInfo">'+
					'<div class="chatName">'+
						'<strong>'+cv.nick+'</strong>'+
						'<span class="chatDate"> '+_today.format('yyyy-MM-dd a/p hh:mm:ss')+'</span>'+
					'</div>'+
					'<div class="chatContent">'+cv.ccmessage+
						'<span class="msgController">'+
							'<i class="fas fa-ellipsis-v"></i>'+
						'</span>'+
					'</div></div>')
				.appendTo(".channelMsg");
	});//each
	
	
	$("#channelChat").on("keydown",textResize);
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
		    	 var msg = $(this).val();
		    	  var d = new Date();
		 		  if(cchatws.readyState !== 1) return;
		 		  var socketMsg = {
		 			type:"chMessage",
		 			channel:ch.ccode,
		 			email:ch.cuemail,
		 			name:ch.cnick,
		 			msg : msg,
		 			date: d.format('yyyy-MM-dd a/p hh:mm:ss'),
		 			profile:ch.uprofilename
		 		  };
		 		 cchatws.send(JSON.stringify(socketMsg));
		 		 channelChatSend(ch.ccode,msg);
		 		  $(this).val("");
		 		 $(".channelMsg").scrollTop($(".channelMsg")[0].scrollHeight);
		    }
		}
	}
	$(".channelMsg").scrollTop($(".channelMsg")[0].scrollHeight);
}

function channelChatSend(code,msg){
	$.ajax({
	 	url:"channels/send",
		type:"post",
		data:{ccode:code,ccmessage:msg},
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}

