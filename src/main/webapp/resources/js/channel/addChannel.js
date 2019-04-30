$(".addServer .serverIconTxt").on("click",function(){
    $.get("/resources/jsp/channel/addChannel.jsp", function(data){
     	$(".viewAdd").html(data); 
    });
  });

$(document).on("click",".DarkBack",function(){
	$(".addContainer").remove();
});

$(document).on("click",".addContent1 .createServer",function(){
	$(".addContent > ul").addClass("moveLeft");
});
$(document).on("click",".addChannelBtn .addChannelBack",function(){
	$(".addContent > ul").removeClass("moveLeft");
});

$(".addBtn").on("click",function(){
	var re = /(^\s)|(\s$)/;
	var cname = $("#cname");
	
	if(cname.val() == "" || cname.val() == null){
		$(".serverNaming .errorMessage").text(" - This filed is required");
		$(".serverNaming label").addClass("error");
		return false;
	}else if(cname.val().length < 2 || cname.val().length > 30){
		$(".serverNaming .errorMessage").text(" - 2길이에서 30사이 여야 합니다.");
		$(".serverNaming label").addClass("error");
		return false;
	}else if(re.test(cname.val())){
		$(".serverNaming .errorMessage").text(" - 문자 앞뒤에 공백이 없어야 합니다.");
		$(".serverNaming label").addClass("error");
		return false;
	}else{
		cname.val().trim();
		addChannelForm.submit();
	}
});