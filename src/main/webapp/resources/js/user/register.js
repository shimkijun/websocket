/**
 * 
 */
function registerForm() {
	var uemail = $(".registerForm #uemail");
	var uname = $(".registerForm #uname");
	var upassword = $(".registerForm #upassword");
	var uemailReg = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	var unameReg = /^[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ가-힣]$/i;
	function uemailAddError() {
		uemail.parent().parent().children('h5').addClass("errorColor");
		uemail.parent().parent().children().children('.errorMessage').text(
				"- This filed is required");
		uemail.addClass("errorBorder");
	}
	function uemailRemoveError() {
		uemail.parent().parent().children('h5').removeClass("errorColor");
		uemail.parent().parent().children().children('.errorMessage').text(" ");
		uemail.removeClass("errorBorder");
	}
	function unameAddError() {
		uname.parent().parent().children('h5').addClass("errorColor");
		uname.parent().parent().children().children('.errorMessage').text(
				"- This filed is required");
		uname.addClass("errorBorder");
	}
	function unameRemoveError() {
		uname.parent().parent().children('h5').removeClass("errorColor");
		uname.parent().parent().children().children('.errorMessage').text(" ");
		uname.removeClass("errorBorder");
	}
	function upwAddError() {
		upassword.parent().parent().children('h5').addClass("errorColor");
		upassword.parent().parent().children().children('.errorMessage').text(
				"- This filed is required");
		upassword.addClass("errorBorder");
	}
	function upwRemoveError() {
		upassword.parent().parent().children('h5').removeClass("errorColor");
		upassword.parent().parent().children().children('.errorMessage').text(
				" ");
		upassword.removeClass("errorBorder");
	}

	function upwSizeCheck() {
		upassword.parent().parent().children('h5').addClass("errorColor");
		upassword.parent().parent().children().children('.errorMessage').text(
				"- 6 길이에서 150 사이 여야 합니다");
		upassword.addClass("errorBorder");
	}

	function emailCheck() {
		uemail.parent().parent().children('h5').addClass("errorColor");
		uemail.parent().parent().children().children('.errorMessage').text(
				"- 정상적인 이메일 주소가 아닙니다.");
		uemail.addClass("errorBorder");
	}

	$(".registerForm #registerBtn").on("click", registerFucntion);

	function registerFucntion() {
		$.ajax({
			url : "register",
			type : "POST",
			data : JSON.stringify({
				uemail : uemail.val(),
				uname : uname.val(),
				upassword : upassword.val()
			}),
			contentType : "application/json;charset=utf-8",
			dataType : "json",
			success : function(data) {
				if ((uemail.val() == "" || uemail.val() == null)
						&& (uname.val() == "" || uname.val() == null)
						&& (upassword.val() == "" || upassword.val() == null)) {
					uemailAddError();
					unameAddError();
					upwAddError();
					if ((upassword.val().length > 1 && upassword.val().length < 6)
							|| upassword.val().length > 150) {
						upwSizeCheck();
					} else if (!uemailReg.test(uemail.val())
							&& uemail.val().length > 0) {
						emailCheck();
					}
					return;
				} else if ((uname.val() == "" || uname.val() == null)
						&& (upassword.val() == "" || upassword.val() == null)) {
					uemailRemoveError();
					unameAddError();
					upwAddError();
					if ((upassword.val().length > 1 && upassword.val().length < 6)
							|| upassword.val().length > 150) {
						upwSizeCheck();
					} else if (!uemailReg.test(uemail.val())
							&& uemail.val().length > 0) {
						emailCheck();
					}
				} else if ((uemail.val() == "" || uemail.val() == null)
						&& (upassword.val() == "" || upassword.val() == null)) {
					uemailAddError();
					unameRemoveError();
					upwAddError();
					if ((upassword.val().length > 1 && upassword.val().length < 6)
							|| upassword.val().length > 150) {
						upwSizeCheck();
					} else if (!uemailReg.test(uemail.val())
							&& uemail.val().length > 0) {
						emailCheck();
					}
				} else if ((uemail.val() == "" || uemail.val() == null)
						&& (uname.val() == "" || uname.val() == null)) {
					uemailAddError();
					unameAddError();
					upwRemoveError();
					if ((upassword.val().length > 1 && upassword.val().length < 6)
							|| upassword.val().length > 150) {
						upwSizeCheck();
					} else if (!uemailReg.test(uemail.val())
							&& uemail.val().length > 0) {
						emailCheck();
					}
				} else if (uemail.val() == "" || uemail.val() == null) {
					uemailAddError();
					unameRemoveError();
					upwRemoveError();
					if ((upassword.val().length > 1 && upassword.val().length < 6)
							|| upassword.val().length > 150) {
						upwSizeCheck();
					} else if (!uemailReg.test(uemail.val())
							&& uemail.val().length > 0) {
						emailCheck();
					}
				} else if (uname.val() == "" || uname.val() == null) {
					uemailRemoveError();
					unameAddError();
					upwRemoveError();
					if ((upassword.val().length > 1 && upassword.val().length < 6)
							|| upassword.val().length > 150) {
						upwSizeCheck();
					} else if (!uemailReg.test(uemail.val())
							&& uemail.val().length > 0) {
						emailCheck();
					}
				} else if (upassword.val() == ""
						|| upassword.val() == null) {
					uemailRemoveError();
					unameRemoveError();
					upwAddError();
					if ((upassword.val().length > 1 && upassword.val().length < 6)
							|| upassword.val().length > 150) {
						upwSizeCheck();
					} else if (!uemailReg.test(uemail.val())
							&& uemail.val().length > 0) {
						emailCheck();
					}
				} else if ((uemail.val() != "" || uemail.val() == null)
						&& (uname.val() != "" || uname.val() != null)
						&& upassword.val() != "") {
					uemailRemoveError();
					unameRemoveError();
					upwRemoveError();
					if ((upassword.val().length > 1 && upassword.val().length < 6)
							|| upassword.val().length > 150) {
						upwSizeCheck();
					} else if (!uemailReg.test(uemail.val())
							&& uemail.val().length > 0) {
						emailCheck();
					} else if (data.register == false) {
						uemail.parent().parent().children('h5')
								.addClass("errorColor");
						uemail.parent().parent().children().children(
								'.errorMessage')
								.text("- 이미 등록된 이메일입니다");
						uemail.addClass("errorBorder");
					} else if (data.register == true) {
						location.href = "/user/login";
					}
				}
			}
		});
	}
}