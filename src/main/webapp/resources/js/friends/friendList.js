/**
 * 
 */
//친구 메뉴 > 헤드영역 메뉴
$(document).on("click",".mainFriendMe .headLine li",function(){
	$(this).addClass("on").siblings().removeClass("on");
});
	
$(document).on("click",".mainFriendMe .headLine li:eq(0)",friendAddView);
function friendAddView(){
	 $.get("/resources/jsp/friend/addFriend.jsp", function(data){
     	$(".mainContent").html(data); 
    });
	 $.ajax({
		 	url:"friends/suggest",
			type:"GET",
			contentType:"application/json;charset=utf-8",
			dataType:"json",
			success:friendSuggestList
			,error:function(xhr){
				alert(xhr.status);
				alert(xhr.errorText);
			}
		});
	}
function friendSuggestList(xhr){
	var data = xhr.data;
	if(data[0] != null){
		$('.fmTbableBody > ul').empty();
		$.each(data,function(idx,fr){
			if(fr.freswait == 0){
			$('<li>').append('<div><input type="hidden" name="freqemail" value="'+fr.freqemail+'" />'+ 
					'<input type="hidden" name="freqcode" value="'+fr.freqcode+'" />'+ 
					'<div class="table-colum fmb-name">'+
					'<span class="fmIcon">'+
					'<img src="images/'+fr.uprofilename+'" alt="icon"></span>'+
					'<span class="fmName">'+fr.uname+'</span>'+
					'<span class="fmCode">#'+fr.freqcode+'</span>'+
					'</div>'+
					'<div class="table-colum fmb-status">'+
					'<span class="fmStatusText">수락 대기중</span>'+
					'</div>'+
					'</div>'+
					'<button class="friendDeleteBtn"><i class="fas fa-user-times"></i></button>'+
					'<button class="friendUpdateBtn" style="right:65px;"><i class="fas fa-user-check"></i></button>')
					.appendTo(".fmTbableBody > ul")
					.animate({height:"62px",opacity:1},200);
			}
		});//each
	}
}//친구 제안 리스트

//1.모든 친구 리스트 보여주는 click 이벤트
$(".mcmBox > ul > li:eq(1)").on("click",friendListView);
$(document).on("click",".mainFriendMe .headLine li:eq(1)",friendListView);


//1.모든 친구 리스트 보여주는 ajax 함수
function friendListView(){
	 $.get("/resources/jsp/friend/me.jsp", function(data){
	     	$(".mainContent").html(data);
	    });
	$.ajax({
		url:"friends/me",
		type:"GET",
		contentType:"application/json;charset=utf-8",
		dataType:"json",
		success:friendAllList
		,error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}
//1.친구 모두 표시 롤백 함수
function friendAllList(xhr){
	var data = xhr.data;
	if(data[0] != null){
		$('.fmTbableBody > ul').empty();
		$.each(data,function(idx,fr){
			//현재 상태 배열변수[]
			var status = new Array();
			
			if(fr.ustatus == 0 || (fr.ustatus == 1 && fr.umystatus == 4)){
				status.push("오프라인")
			}else if(fr.umystatus == 1){
				status.push("온라인")
			}else if(fr.umystatus == 2){
				status.push("자리비움")
			}else if(fr.umystatus == 3){
				status.push("다른 용무중")
			}
			if(fr.freswait == 1){
			$('<li>').append('<div class="allList"><input type="hidden" name="freqemail" value="'+fr.fresemail+'" />'+
				'<div class="table-colum fmb-name">'+
				'<span class="fmIcon">'+
				'<img src="images/'+fr.uprofilename+'" alt="icon"></span>'+
				'<span class="fmName">'+fr.uname+'</span>'+
				'<span class="fmCode">#'+fr.frescode+'</span>'+
				'</div>'+
				'<div class="table-colum fmb-status">'+
				'<span class="fmStatus'+fr.umystatus+' fmuStatus'+fr.ustatus+'"></span>'+
				'<span class="fmStatusText">'+status+'</span>'+
				'</div>'+
				'<div class="table-colum fmb-server" >'+
				'<span class="fmServer">s</span></div>'+
				'</div>'+
				'<button class="friendDeleteBtn"><i class="fas fa-user-times"></i></button>')
				.appendTo(".fmTbableBody > ul")
				.animate({height:"62px",opacity:1},200);
			}
		});//each

	}else{
		$('.friendsEmpty').empty();
		$('<div class="friendsEmpty">')
		.append('<p class="frEmptyImg"><img src="images/frEmpty.png" alt="친구가 없어요"/>'+
				'<span class="frEmptyTxt">등록된 친구가 없어요...</span>'+
				'</p>')
		.appendTo('.fmTbableBody');
	}
}//친구 모두 표시 롤백 함수


//2.접속중 && 승인한 친구 리스트 보여주는 click 이벤트
$(document).on("click",".mainFriendMe .headLine li:eq(2)",friendOnlineView);

//2.접속중 && 승인한 친구 리스트 보여주는 ajax 이벤트
function friendOnlineView(){
	 $.get("/resources/jsp/friend/onlineFriend.jsp", function(data){
	     	$(".mainContent").html(data); 
	    });
	$.ajax({
		url:"friends/me",
		type:"GET",
		contentType:"application/json;charset=utf-8",
		dataType:"json",
		success:friendOnlineList
		,error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}
//2.접속중 && 친구 승인한 사람만 상태 확인.
function friendOnlineList(xhr){
	var data = xhr.data;
	if(data[0] != null){
		$('.fmTbableBody > ul').empty();
		$.each(data,function(idx,fr){
			//현재 상태 배열변수[]
			var status = new Array();
			
			if(fr.ustatus == 0 || (fr.ustatus == 1 && fr.umystatus == 4)){
				status.push("오프라인")
			}else if(fr.umystatus == 1){
				status.push("온라인")
			}else if(fr.umystatus == 2){
				status.push("자리비움")
			}else if(fr.umystatus == 3){
				status.push("다른 용무중")
			}
			if((fr.ustatus > 0 && fr.freswait == 1) && (fr.ustatus == 1 && fr.umystatus < 4)){
			$('<li>').append('<div><input type="hidden" name="freqemail" value="'+fr.fresemail+'" />'+
					'<div class="table-colum fmb-name">'+
					'<span class="fmIcon">'+
					'<img src="images/'+fr.uprofilename+'" alt="icon"></span>'+
					'<span class="fmName">'+fr.uname+'</span>'+
					'<span class="fmCode">#'+fr.frescode+'</span>'+
					'</div>'+
					'<div class="table-colum fmb-status">'+
					'<span class="fmStatus'+fr.umystatus+' fmuStatus'+fr.ustatus+'"></span>'+
					'<span class="fmStatusText">'+status+'</span>'+
					'</div>'+
					'<div class="table-colum fmb-server" >'+
					'<span class="fmServer">s</span></div>'+
					'</div>'+
					'<button class="friendDeleteBtn"><i class="fas fa-user-times"></i></button>'
					).appendTo(".fmTbableBody > ul")
					.animate({height:"62px",opacity:1},200);
			}
		});//each
	}else{
		$('.friendsEmpty').empty();
		$('<div class="friendsEmpty">')
		.append('<p class="frEmptyImg"><img src="images/frEmpty.png" alt="친구가 없어요"/>'+
				'<span class="frEmptyTxt">등록된 친구가 없어요...</span>'+
				'</p>')
		.appendTo('.fmTbableBody')
	}
}//접속중 && 친구 승인한 롤백 함수


//3.친구요청 승인대기 click 이벤트
$(document).on("click",".mainFriendMe .headLine li:eq(3)",friendRequestView);

//3.친구요청 승인대기 확인
function friendRequestView(){
	 $.get("/resources/jsp/friend/requestFriend.jsp", function(data){
	     	$(".mainContent").html(data); 
	    });
	$.ajax({
		url:"friends/me",
		type:"GET",
		contentType:"application/json;charset=utf-8",
		dataType:"json",
		success:friendRequestList,
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}
//3.친구요청 승인대기 리스트
function friendRequestList(xhr){
	var data = xhr.data;
	if(data[0] != null){
		$('.fmTbableBody > ul').empty();
		$.each(data,function(idx,fr){
			if(fr.freswait == 0){
			$('<li>').append('<div><input type="hidden" name="freqemail" value="'+fr.fresemail+'" />'+
					'<div class="table-colum fmb-name">'+
					'<span class="fmIcon">'+
					'<img src="images/'+fr.uprofilename+'" alt="icon"></span>'+
					'<span class="fmName">'+fr.uname+'</span>'+
					'<span class="fmCode">#'+fr.frescode+'</span>'+
					'</div>'+
					'<div class="table-colum fmb-status">'+
					'<span class="fmStatusText">승인 대기중</span>'+
					'</div>'+
					'</div>'+
					'<button class="friendDeleteBtn"><i class="fas fa-user-times"></i></button>')
					.appendTo(".fmTbableBody > ul")
					.animate({height:"62px",opacity:1},200);
			}
		});//each
	}
}//친구요청 승인대기 리스트

//4.친구 제안 승인 기능
$(document).on("click",".friendUpdateBtn",friendsAcceptFunction);
	function friendsAcceptFunction(){
	var reqId = $(this).parent().find("input[name='freqemail']").val();
	var reqCode = $(this).parent().find("input[name='freqcode']").val();
		$.ajax({
			url:"friends/accept",
			type:"PUT",
			data:JSON.stringify({freqemail:reqId,freqcode:reqCode}),
			contentType:"application/json;charset=utf-8",
			dataType:"json",
			success:function(result){
				$(".mainFriendMe .headLine li:eq(0)").click();
			}
			,error:function(xhr){
				alert(xhr.status);
				alert(xhr.errorText);
			}
		});
	}
	
	//5.친구 삭제
	$(document).on("click",".deleteBtnBox > span.deleteCancel",function(){
		$(".addContainer").remove();
	});
	$(document).on("click",".deleteBtnBox > span.deleteOk",friendDeleteOk)
	
	function friendDeleteOk(){
		 var reqId = $(this).parent().siblings("input[name='freqemail']").val();
		 $.ajax({
			 	url:"friends/delete",
				type:"POST",
				data:{freqemail:reqId},
				success:function(){
					$(".addContainer").remove();
					$(".mainFriendMe .headLine li.on").click();
				}
				,error:function(xhr){
					alert(xhr.status);
					alert(xhr.errorText);
				}
			});
	}
	
	$(document).on("click",".friendDeleteBtn",friendDeleteView);
	
	function friendDeleteView(){
		$(document).on('click','.fmTbableBody > ul > li',function(e){
			return false;
		});
		var fmbName = $(this).parent().find(".fmName").text();
		var reqId = $(this).parent().find("input[name='freqemail']").val();
		$('<div class="addContainer">')
			.append('<div class="DarkBack"></div>'+
			'<div class="deleteFriendForm">'+
			'<input type="hidden" name="freqemail" value="'+reqId+'"/>'+
			'<h2>'+fmbName+' 님을 제거</h2>'+
			'<div class="deleteConfirm">친구 '+fmbName+'님을 삭제하시겠습니까?</div>'+
			'<div class="deleteBtnBox">'+
			'<span class="deleteCancel">취소</span>'+
			'<span class="deleteOk">친구 삭제</span>'+
			'</div>'+
			'</div>')
			.appendTo(".viewAdd");
		}//5.친구 삭제
	     
