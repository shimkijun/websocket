/**
 * 
 */

$(".homeiconImg").on("click",homeIconClick);

function homeIconClick(){
	$(this).addClass("active");
	$(".serverIcon > ul > li").removeClass("active");
	location.href="/";
}


$(document).on("click",".serverIcon > ul > li",serverIconClick);
function serverIconClick(){
	$(this).addClass("active").siblings().removeClass("active");
	$(".homeiconImg").removeClass("active");
}

$(document).on("click",".mcmBox > ul > li",mcmActive);
function mcmActive(){
	$(this).addClass("active").siblings().removeClass("active");
}


