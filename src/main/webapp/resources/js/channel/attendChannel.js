/**
 * 
 */

$(document).on("click",".chatMsgWrap > div #inviteAccept",attendChannel);

function attendChannel(){
 var cname = $(this).parent().parent().find(".cname").text();
 var cmaster = $(this).parent().parent().parent().find("input[name='cmaster']").val();
 var ccode = $(this).parent().parent().parent().find("input[name='ccode']").val();
 $.ajax({
	 	url:"channels/attend",
		type:"POST",
		data:{cmaster:cmaster,ccode:ccode},
		success:function(data){
			var ch = data.attend;
			if(data.result){
				addChannelForm(ch.ccode,cname,ch.clevel);
			}
		}
		,error:function(xhr){
			alert(xhr.status);
			alert(xhr.errorText);
		}
	});
}

function addChannelForm(code,name,clevel){
	if(clevel == 1){
		$('<li>').prepend('<input type="hidden" value="'+code+'"/>'+
			'<div class="serverIconTxt">'+
			'<span>'+name+'</span>'+
			'<span class="serverTitle">'+name+'</span>'+
			'<i class="fas fa-times-circle"></i>'+
		'</div>').prependTo(".serverIcon ul");
	}else{
		$('<li>').prepend('<input type="hidden" value="'+code+'"/>'+
			'<div class="serverIconTxt">'+
			'<span>'+name+'</span>'+
			'<span class="serverTitle">'+name+'</span>'+
		'</div>').prependTo(".serverIcon ul");	
	}
}
