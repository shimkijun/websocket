/**
 * 
 */

chatConnect();

var uchatws;
function chatConnect() {
	console.log('[INFO] : USER CHATTING CONNECTION OPEN.'); 
	uchatws = new WebSocket("ws://localhost:80/userChat");
	uchatws.onopen = function () {
		//서버로부터 메시지를 받으면 호출되는 함수 지정
	    uchatws.onmessage = function(event){
			var data = event.data;
			var recv = JSON.parse(data);
			var ch = recv.channel;
			var chReg;
			
			if(ch == null){
			console.log("보내는 사람 이름 : "+recv.name+" 메시지 내용 : "+recv.msg);
			$('<div class="chatMessage">')
			.append('<div class="chatIcon">'+
						'<img src="/images/'+recv.profile+'">'+
					'</div>'+
					'<div class="chatInfo">'+
						'<div class="chatName">'+
							'<strong>'+recv.name+'</strong>'+
							'<span class="chatDate"> '+recv.date+'</span>'+
						'</div>'+
						'<div class="chatContent">'+recv.msg+
							'<span class="msgController">'+
								'<i class="fas fa-ellipsis-v"></i>'+
							'</span>'+
						'</div>'+
					'</div>')
					.appendTo(".userMsg");
			$(".chatMsgWrap").scrollTop($(".chatMsgWrap")[0].scrollHeight);
		}else{
			chReg = JSON.parse(ch);
			console.log("보내는 사람 이름 : "+recv.name+" 채널이름 : "+chReg.cname);
			$('<div class="chatMessage">')
			.append('<div class="chatIcon">'+
						'<img src="/images/'+recv.profile+'">'+
					'</div>'+
					'<div class="chatInfo">'+
						'<div class="chatName">'+
							'<strong>'+recv.name+'</strong>'+
							'<span class="chatDate"> '+recv.date+'</span>'+
						'</div>'+
						'<div class="chatContent">'+recv.msg+
							'<span class="msgController">'+
								'<i class="fas fa-ellipsis-v"></i>'+
							'</span>'+
						'</div>'+
						'<div class="chInviteWrap">'+
							'<p class="chInviteTxt">그룹 가입 초대장을 보냈습니다.</p>'+
							'<input type="hidden" name="cmaster" value="'+chReg.cmaster+'" />'+
							'<input type="hidden" name="ccode" value="'+chReg.ccode+'" />'+
							'<div class="chInviteBox">'+
								'<div class="serverIconTxt">'+chReg.cname.substring(0,1)+'</div>'+
								'<div class="chName">'+
									'<p class="cname">'+chReg.cname+'</p>'+
									'<p class="cstatus">'+
									'<span class="profile on"></span><span class="statusTxt">2명 접속중</span>'+
									'<span class="profile off"></span><span class="statusTxt">2명의 멤버</span>'+
									'</p>'+
								'</div>'+
								'<div class="inviteButton"><button id="inviteAccept">참가</button></div>'+
							'</div>'+
						'</div>')
						.appendTo('.userMsg');
			$(".chatMsgWrap").scrollTop($(".chatMsgWrap")[0].scrollHeight);
		}
	};
	uchatws.onclose = function(event){
		console.log('[INFO] : USER CHATTING CONNECTION CLOSED.'); 
		setTimeout( function(){ chatConnect(); }, 1000); // retry connection!!
	};
}
}