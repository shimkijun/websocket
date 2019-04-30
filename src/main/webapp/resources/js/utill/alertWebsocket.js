/**
 * 
 */

var a;

alertConnect();

function alertConnect() {
	console.log('[INFO] : ALERT CONNECTION OPEN.'); 
	a = new WebSocket("ws://localhost:80/alert");
	a.onopen = function () {
		//서버로부터 메시지를 받으면 호출되는 함수 지정
	    a.onmessage = function(event){
	    	// type , 친구요청 한 이름 , 친구요청 한 메일 , 친구요청 한 코드 , 친구요청 한 썸네일 
	    	var data = event.data.split(',');
	    	var type = data[0];
	    	if(type == "addfriends"){
	    		$('<li>').append('<div><input type="hidden" name="freqemail" value="'+data[2]+'" />'+ 
						'<input type="hidden" name="freqcode" value="'+data[3]+'" />'+ 
						'<div class="table-colum fmb-name">'+
						'<span class="fmIcon">'+
						'<img src="images/'+data[4]+'" alt="icon"></span>'+
						'<span class="fmName">'+data[1]+'</span>'+
						'<span class="fmCode">#'+data[3]+'</span>'+
						'</div>'+
						'<div class="table-colum fmb-status">'+
						'<span class="fmStatusText">수락 대기중</span>'+
						'</div>'+
						'</div>'+
						'<button class="friendDeleteBtn"><i class="fas fa-user-times"></i></button>'+
						'<button class="friendUpdateBtn" style="right:65px;"><i class="fas fa-user-check"></i></button>')
						.appendTo(".fmTbableBody > ul")
						.animate({height:"62px",opacity:1},200);
	    		
	    		$('<div class="alert">').append('<ul></ul>').appendTo("#wrap");
	    		$("<li>").append('<div>'+
	    				'<p><img src="images/'+data[4]+'"></p>'+
	    				'<p>'+data[1]+'#'+data[3]+' 님이 <br/>친구 요청을 신청 하였습니다.</p>'+
	    				'</div>')
						.appendTo(".alert ul")
						.animate({left:"0",opacity:1},200);
	    		$(document).on("click",".alert ul li",function(){
	    			$(".mcmBox > ul > li:eq(1)").click();
	    			$(".primary").click();
	    			$(this).fadeOut("normal",function(){$(this).remove();});
	    		})
	    	}
		}
	};
	a.onclose = function(event){
		console.log('[INFO] : ALERT CONNECTION CLOSED.'); 
	};
}
