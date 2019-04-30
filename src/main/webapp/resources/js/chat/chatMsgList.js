/**
 *  친구 선택시 게인 메시지 영역에 list 추가 
 */

//게인 메시지 리스트 삭제
$(document).on("click",".msgList ul > li button",chatListDel);
function chatListDel(){
	var i = $(".msgList ul > li").length;
	var uemail = $(this).prev().find("input[type='hidden']").val();
	$.ajax({
	 	url:"chat/listDel",
		type:"POST",
		data:{uemail:uemail},
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
	$(this).parent().remove();
	if(i == 1){
		$('<li class="listEmpty">').append('<img src="/images/listEmpty.svg" alt="개인 메시지가 없을경우 이미지"/>').appendTo(".msgList > ul");
	}
}

//게인 메시지 리스트 추가
$(document).on("click",".fmTbableBody > ul > li > div.allList",addChatList);
function addChatList(){
	var uemail = $(this).find("input[type='hidden']").val();
	$.ajax({
	 	url:"chat/chatList",
		type:"post",
		data:{uemail:uemail},
		success:function(data){
			var toid = data.toid;
			var result = data.result;
			if (!result) return;
			var condition = "";
			if(toid.ustatus == 0){
				condition = "off";
			}else if(toid.ustatus == 1){
				if(toid.umystatus == 1){
					condition = "on";
				}else if(toid.umystatus == 2){
					toid = "away";
				}else if(toid.umystatus == 3){
					condition = "other";
				}else if(toid.umystatus ==4){
					condition = "off";
				}
			}
			$('.msgList > ul > li.listEmpty').remove();
			$('<li>')
			.append('<div><input type="hidden" name="clName" id="clName" value="'+toid.uemail+'" />'+
					'<p class="profileIcon">'+
					'<img src="/images/'+toid.uprofilename+'" />'+
					'<span class="profile '+condition+'"></span>'+
					'</p>'+
					'<p class="profileName">'+toid.uname+'</p>'+
				'</div>'+
				'<button type="button" id="close_msg"><i class="fas fa-times"></i></button>').appendTo(".msgList > ul");
		},
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}

