
//친구 요청 {유저 중복,유저 체크,패턴 체크,친구 요청 상태,친구 요청 완료}
	var codename;
	var codeReg;
	$(document).on("keyup","#discordTag",function(){
		codename = $("#discordTag");
		if(codename.val().length > 0){
			$("#addFriendBtn").addClass('notAllowed');
		}
		if(codename.val().length == 0){
			$("#addFriendBtn").removeClass('notAllowed');
		}
	})
	$(document).on("click","#addFriendBtn",addFriendFuction);
	function addFriendFuction(){
		codename = $("#discordTag");
		codeReg = /^[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ가-힣]*#[0-9]{4}$/i;
		$.ajax({
			url:"friends/add",
			type:"POST",
			data:{tag:codename.val()},
			success:function(result){
				if(!codeReg.test(codename.val())){
					$(".addFriendContent p")
					.text("myfriend#0000 형식에 맞춰 입력하세요")
					.addClass("errorMessage")
					.removeClass("seccessMessage")
					.removeClass("requestMessage");
				}else if(codeReg.test(codename.val())){
					if(result.check == false){
						$(".addFriendContent p")
						.text("없는 사람이랍니다~~!! 다시 한번 확인해 주세요 !!")
						.addClass("errorMessage")
						.removeClass("seccessMessage")
						.removeClass("requestMessage");
					}else if(result.same == true){
						$(".addFriendContent p")
						.text(codename.val()+" 나 자신에게 요청할 수 없습니다")
						.addClass("errorMessage")
						.removeClass("seccessMessage")
						.removeClass("requestMessage");
					}else if(result.request == false){
						$(".addFriendContent p")
						.text(codename.val()+"님은 승인이 대기 중이던가 친구 수락 전입니다.")
						.addClass("requestMessage")
						.removeClass("seccessMessage")
						.removeClass("errorMessage");
					}else if(result.request == true){
						$(".addFriendContent p")
						.text(codename.val()+"님은 이미 친구입니다^^")
						.addClass("requestMessage")
						.removeClass("seccessMessage")
						.removeClass("errorMessage");
					}else if(result.result == true){
						$(".addFriendContent p")
						.text("성공하였습니다! "+codename.val()+" 에게 친구 요청이 전달되었습니다!!!")
						.addClass("seccessMessage")
						.removeClass("errorMessage")
						.removeClass("requestMessage");
						 if (a.readyState !== 1) return;
				 		  var socketMsg = {
				 			type:"addfriends",
				 			codename:codename.val()
				 		  };
				 		  a.send(JSON.stringify(socketMsg));
					}
				}
			}
			,error:function(xhr){
				alert(xhr.status);
				alert(xhr.errorText);
			}
		});
	}