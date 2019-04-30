

channelChatConnect();

function channelChatConnect() {
	console.log('[INFO] : CHANNEL CHATTING CONNECTION OPEN.'); 
	cchatws = new WebSocket("ws://localhost:80/channelChat");
	cchatws.onopen = function () {
		//서버로부터 메시지를 받으면 호출되는 함수 지정
		cchatws.onmessage = function(event){
			var data = event.data;
			var recv = JSON.parse(data);
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
					.appendTo(".channelMsg");
			$(".channelMsg").scrollTop($(".channelMsg")[0].scrollHeight);
		}
	};
	cchatws.onclose = function(event){
		console.log('[INFO] : CHANNEL CHATTING CONNECTION CLOSED.');  
	};

}
