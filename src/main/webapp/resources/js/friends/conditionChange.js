/**
 * 
 */

$(".myInfo > div > p.profileIcon").on("click",function(){
	$(".condition-box:not(:animated)").fadeToggle();
});
	
//상태 변환 ajax
$(".condition-box > li").on("click",conditionChange);
function conditionChange(){
	$(".condition-box:not(:animated)").fadeOut();
	var num = $(this).index()+1;
	$.ajax({
		url:"user/condition",
		type:"PUT",
		data:JSON.stringify({umystatus:num}),
		contentType:"application/json;charset=utf-8",
		dataType:"json",
		success:function(result){
			var condition = result.data;
			if(condition == 1){
				$(".myInfo .profile").addClass("on");
				$(".myInfo .profile").removeClass("away").removeClass("other").removeClass("off");
			}else if(condition == 2){
				$(".myInfo .profile").addClass("away");
				$(".myInfo .profile").removeClass("on").removeClass("other").removeClass("off");
			}else if(condition == 3){
				$(".myInfo .profile").addClass("other");
				$(".myInfo .profile").removeClass("on").removeClass("away").removeClass("off");
			}else if(condition == 4){
				$(".myInfo .profile").addClass("off");
				$(".myInfo .profile").removeClass("on").removeClass("away").removeClass("other");
			}
		}
		,error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}
