	function loginForm(){
		var uemail = $("#uemail");
		var upassword = $("#upassword");
		var uemailReg = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		
		function uemailAddError(){
			uemail.parent().parent().children('h5').addClass("errorColor");
			uemail.parent().parent().children().children('.errorMessage').text("- This filed is required");
			uemail.addClass("errorBorder");
		}
		function uemailRemoveError(){
			uemail.parent().parent().children('h5').removeClass("errorColor");
			uemail.parent().parent().children().children('.errorMessage').text(" ");
			uemail.removeClass("errorBorder");
		}
		function upwAddError(){
			upassword.parent().parent().children('h5').addClass("errorColor");
			upassword.parent().parent().children().children('.errorMessage').text("- This filed is required");
			upassword.addClass("errorBorder");
		}
		function upwRemoveError(){
			upassword.parent().parent().children('h5').removeClass("errorColor");
			upassword.parent().parent().children().children('.errorMessage').text(" ");
			upassword.removeClass("errorBorder");
		}
		
		function upwSizeCheck(){
			upassword.parent().parent().children('h5').addClass("errorColor");
			upassword.parent().parent().children().children('.errorMessage').text("- 6 길이에서 150 사이 여야 합니다");
			upassword.addClass("errorBorder");
		}
		
		function emailCheck(){
			uemail.parent().parent().children('h5').addClass("errorColor");
			uemail.parent().parent().children().children('.errorMessage').text("- 정상적인 이메일 주소가 아닙니다.");
			uemail.addClass("errorBorder");
		}
		
		$("#loginBtn").on("click",loginAjax);
		
		function loginAjax(){
			$.ajax({
				url:"login",
				type:"POST",
				data:JSON.stringify({uemail:uemail.val(),upassword:upassword.val()}),
				contentType:"application/json;charset=utf-8",
				dataType:"json",
				success:function(data){
					if((uemail.val() == null || uemail.val() == "") && (upassword.val() == null || upassword.val() == "")){
						uemailAddError();
						upwAddError();
						return false;
					}else if(uemail.val() == null || uemail.val() == ""){
						uemailAddError();
						upwRemoveError();
						if((upassword.val().length > 1 && upassword.val().length < 6) || upassword.val().length > 150){
							upwSizeCheck();
						}
						return false;
					}else if(upassword.val() == null || upassword.val() == ""){
						uemailRemoveError();
						upwAddError();
						if(!uemailReg.test(uemail.val()) && uemail.val().length > 0){
							emailCheck();
						}
						return false;
					}else if((uemail.val() != null || uemail.val() != "") && (upassword.val() != null || upassword.val() != "")){
						uemailRemoveError();
						upwRemoveError();
						if(((upassword.val().length > 1 && upassword.val().length < 6) || upassword.val().length > 150) 
							&& (!uemailReg.test(uemail.val()) && uemail.val().length > 0)){
							upwSizeCheck();
							emailCheck();
						}else if((upassword.val().length > 1 && upassword.val().length < 6) || upassword.val().length > 150){
							upwSizeCheck();
						}else if((!uemailReg.test(uemail.val()) && uemail.val().length > 0)){
							emailCheck();
						}else if(data.idCheck == false){
							uemail.parent().parent().children('h5').addClass("errorColor");
							uemail.parent().parent().children().children('.errorMessage').text("- 존재하지 않는 이메일입니다.");
							uemail.addClass("errorBorder");
						}else if(data.passCheck == false){
							upassword.parent().parent().children('h5').addClass("errorColor");
							upassword.parent().parent().children().children('.errorMessage').text("- 비밀번호가 일치하지 않습니다.");
							upassword.addClass("errorBorder");
						}else if(data.idCheck == true && data.passCheck == true){
							location.href="/";
						}
					}
				},error:function(xhr){
					alert(xhr.status);
					alert(xhr.errorText);
				}
			});
		}
	}