/**
 * 
 */

$(document).on("click",".deleteBtnBox > span.deleteChannel",channelDeleteOk)

function channelDeleteOk(){
	 var code = $(this).parent().siblings("input").val();
	 $.ajax({
		 	url:"channels/delete/"+code,
			type:"DELETE",
			success:function(data){
				if(data.result){
					location.href="/";
				}
			}
			,error:function(xhr){
				alert(xhr.status);
				alert(xhr.errorText);
			}
		});
}

$(document).on("click",".fa-times-circle",channelDeleteView);

function channelDeleteView(){
	var name = $(this).siblings(".serverTitle").text();
	var code = $(this).parent().parent().find("input").val();
	$('<div class="addContainer">')
		.append('<div class="DarkBack"></div>'+
		'<div class="deleteFriendForm">'+
		'<input type="hidden" value="'+code+'"/>'+
		'<h2>'+name+' 채널 제거</h2>'+
		'<div class="deleteConfirm">채널 '+name+'을 삭제하시겠습니까?</div>'+
		'<div class="deleteBtnBox">'+
		'<span class="deleteCancel">취소</span>'+
		'<span class="deleteChannel">채널 삭제</span>'+
		'</div>'+
		'</div>')
		.appendTo(".viewAdd");
	}

/*$(".serverIcon > ul > li").on('mousedown', function() {
	 if ((event.button == 2) || (event.which == 3)) {
		   var x = event.pageX;
		   var y = event.pageY;
		   var name = $(this).find("span").text(); 
		   alert('x좌표:' +x + ', y좌표:' + y + " 서버이름: " + name);
		  }
	});*/