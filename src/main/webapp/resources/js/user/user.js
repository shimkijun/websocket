/**
 * 
 */

$(".infoBtn button").on("click",userModifySelect);

function userModifySelect(){
	$.ajax({
	 	url:"user/info",
		type:"POST",
		success:modify,
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}

function modify(xhr){
	var data = xhr.data;
	$(".userWrap").remove();
	userModifyForm(data.uname,data.uemail,data.ucode);
	$('.userWrap').animate({opacity:1}, 400);
	$('#wrap').css({"transform":"scale(0.94)","opacity":"0"});
	
	$(document).on("click",".uivButton button",function(){
		modifyForm(data.uname,data.uemail,data.ucode);
	});
	
	$(document).on("click",".umbCancle",function(){
		modifyFormCancle(data.uname,data.uemail,data.ucode);
	});
	
	$(document).on("click",".modifyForm > p",function(){
		$(this).remove();
		$('<div class="mfLine">')
		.append('<h5>새 비밀번호 <span>*</span></h5>'+
				'<input type="password" id="nupassword" name="nupassword" maxlength="150" required>')
		.appendTo(".modifyForm");
	});

}

$(document).on("click",".userWrapClose",function(){
	$('.userWrap').remove();
	$('#wrap').css({"transform":"scale(1)","opacity":"1"});
})

$(document).on("click",".sbLogout",logOutForm);
function logOutForm(){
	$('<div class="addContainer">')
	.prepend('<div class="DarkBack"></div>'+
	'<div class="deleteFriendForm">'+
	'<input type="hidden" value=""/>'+
	'<h2> 로그아웃</h2>'+
	'<div class="deleteConfirm">정말로 로그아웃 하시겠습니까?</div>'+
	'<div class="deleteBtnBox">'+
	'<span class="deleteCancel">취소</span>'+
	'<span class="logoutOk">로그아웃</span>'+
	'</div>'+
	'</div>')
	.prependTo("body");
}
$(document).on("click",".logoutOk",function(){
	$.ajax({
	 	url:"user/logout",
		type:"GET",
		success:function(data){
			if(data.result){
				location.href="/";
			}
		},
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
});
$(document).on("focus",".mfLine input",function(){
	$(this).addClass("active");
})
$(document).on("focusout",".mfLine input",function(){
	$(this).removeClass("active");
})
//.infoBtn button click event 
function userModifyForm(name,email,code){
	$('<div class="userWrap">')
	.prepend('<div class="userLayer">'+
				'<div class="leftLayer">'+
					'<div class="sideBar">'+
						'<div class="leftSide">'+
							'<h4>사용자 설정</h4>'+
							'<ul>'+
								'<li class="active">'+
									'내 계정'+
								'</li>'+
							'</ul>'+
							'<div class="separator"></div>'+
							'<div class="sbLogout">'+
								'<span>로그아웃</span>'+
							'</div>'+
							'<div class="separator"></div>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="rightLayer">'+
					'<div class="rightContent">'+
						'<h2>내 계정</h2>'+
						'<div class="userInfoViewing">'+
							'<div class="uivIcon">'+
								'<img src="/images/profile_icon_1.png">'+
							'</div>'+
							'<div class="uivInfo">'+
								'<div>'+
									'<h4>사용자명</h4>'+
									'<div><span class="userName">'+name+'</span><span class="userCode">#'+code+'</span></div>'+
								'</div>'+
								'<div>'+
									'<h4>이메일</h4>'+
									'<div>'+email+'</div>'+
								'</div>'+
							'</div>'+
							'<div class="uivButton">'+
								'<button type="button">수정</button>'+
							'</div>'+
						'</div>'+
						'<div class="userWrapClose">'+
							'<p><i class="fas fa-times"></i></p>'+
							'<p>ESC</p>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>').prependTo("body");
}

function modifyForm(name,email,code){
	var pass = $("#upassword");
	pass.val("");
	$(".userInfoViewing").empty();
	$('<div class="userModfiyWrap">')
	.prepend('<div class="umTop">'+
				'<div class="umIcon">'+
					'<p>'+
						'<img src="/images/profile_icon_1.png">'+
						'<span class="umText">아바타<br/>변경</span>'+
						'<span><i class="fas fa-palette"></i></span>'+
						'<input type="file" name="upload" >'+
					'</p>'+
					'<p>최소 크기: 128x128</p>'+
				'</div>'+
				'<div class="modifyForm">'+
					'<div class="mfLine">'+
						'<h5>사용자명 <span>*</span></h5>'+
						'<p>'+
							'<input type="uname" id="uname" name="uname" maxlength="128" value="'+name+'" required>'+
							'<span>#'+code+'</span>'+
						'</p>'+
					'</div>'+
					'<div class="mfLine">'+
						'<h5>이메일 <span>*</span></h5>'+
						'<input type="email" id="uemail" name="uemail" maxlength="150" value="'+email+'" required readonly>'+
					'</div>'+
					'<div class="mfLine">'+
						'<h5>현재 비밀번호 <span>*</span></h5>'+
						'<input type="password" id="upassword" name="upassword" maxlength="150" required>'+
					'</div>'+
					'<p>비밀번호를 변경하고 싶으신가요?</p>'+
				'</div>'+
			'</div>'+
			'<div class="umBottom">'+
				'<div class="umbLeft">'+
					'<button class="userDelete">계정 삭제</button>'+
				'</div>'+
				'<div class="umbRight">'+
					'<button class="umbCancle">취소</button>'+
					'<button class="umbSubmit">저장</button>'+
				'</div>'+
			'</div>')
	.prependTo(".userInfoViewing");
}

function modifyFormCancle(name,email,code){
	$(".userInfoViewing").remove();
	$('<div class="userInfoViewing">')
	.append('<div class="uivIcon">'+
				'<img src="/images/profile_icon_1.png">'+
			'</div>'+
			'<div class="uivInfo">'+
				'<div>'+
					'<h4>사용자명</h4>'+
					'<div><span class="userName">'+name+'</span><span class="userCode">#'+code+'</span></div>'+
				'</div>'+
				'<div>'+
					'<h4>이메일</h4>'+
					'<div>'+email+'</div>'+
				'</div>'+
			'</div>'+
			'<div class="uivButton">'+
				'<button type="button">수정</button>'+
			'</div>'+
		'</div>')
	.appendTo(".rightContent");
}

$(document).on("click",".umbSubmit",userModifyAjax);

function userModifyAjax(){
	var name = $("#uname");
	var pass = $("#upassword");
	var npass = $("#nupassword");

	$.ajax({
	 	url:"user/modify",
		type:"POST",
		data:{"name":name.val(),"password":pass.val(),"npassword":npass.val()},
		success:function(data){
			if(data.result == true){
				location.href="/";
			}else if(data.result == false){
				pass.addClass("error");
			}
		},
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}

$(document).on("click",".userDelete",deleteForm);
$(document).on("click",".userDeleteOk",deleteDeleteAjax);
function deleteForm(){
	$('<div class="addContainer">')
	.prepend('<div class="DarkBack"></div>'+
	'<div class="deleteFriendForm">'+
	'<input type="hidden" value=""/>'+
	'<h2> 계정 삭제</h2>'+
	'<div class="deleteConfirm">정말로 삭제 하시겠습니까?</div>'+
	'<div class="deleteBtnBox">'+
	'<span class="deleteCancel">취소</span>'+
	'<span class="userDeleteOk">삭제</span>'+
	'</div>'+
	'</div>')
	.prependTo("body");
}
function deleteDeleteAjax(){
	var pass = $("#upassword");
	
	$.ajax({
	 	url:"user/delete",
		type:"POST",
		data:{"password":pass.val()},
		success:function(data){
			if(data.result == true){
				location.href="/";
			}else if(data.result == false){
				$(".addContainer").remove();
				pass.addClass("error");
			}
		},
		error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}
