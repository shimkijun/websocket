package com.web.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.channel.service.IChannelService;
import com.web.channel.vo.ChannelVo;
import com.web.chat.service.IChatService;
import com.web.chat.vo.ChatVo;
import com.web.chatlist.service.IChatListService;
import com.web.chatlist.vo.ChatListVo;
import com.web.user.service.IUserService;
import com.web.user.vo.UserVo;


@Controller
@RequestMapping("/chat")
public class ChatServiceController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IChannelService channelService;
	
	@Autowired
	private IChatService chatService;
	
	@Autowired
	private IChatListService chatListService;
	
	@PostMapping("/list")
	@ResponseBody
	public Map userChatList(HttpSession session,ChatVo cv){
		Map<String, Object> result = new HashMap<>();
		ChannelVo ch = new ChannelVo();
		UserVo user = (UserVo)session.getAttribute("loginId");
		UserVo toid = new UserVo();
		
		toid.setUemail(cv.getUctoid());
		toid = userService.userInfo(toid);
		cv.setUcfromid(user.getUemail());
		cv.setUctoid(cv.getUctoid());
		List<ChatVo> chatList = chatService.userChatList(cv);
		
		result.put("uname",user.getUname());
		result.put("profile",user.getUprofilename());
		result.put("toid", toid);
		result.put("data", chatList);
		
		return result;
	}
	
	@PostMapping("/send")
	@ResponseBody
	public Map userChatMessage(HttpSession session,ChatVo cv){
		Map<String, Object> result = new HashMap<>();
		UserVo user = (UserVo)session.getAttribute("loginId");
		
		System.out.println("컨트롤러 메시지"+cv.getUcmessage());
		
		cv.setUcfromid(user.getUemail());
		cv.setUcfromname(user.getUname());
		cv.setUctoid(cv.getUctoid());
		cv.setUcmessage(cv.getUcmessage());

		int success = chatService.userSendMessage(cv);

		if(success == 1) {
			result.put("result", Boolean.TRUE);
		}else {
			result.put("result", Boolean.FALSE);
		}
		return result;
	}
	
	@PostMapping("/chatList")
	@ResponseBody
	public Map userMsgList(HttpSession session,ChatListVo clv){
		Map<String, Object> result = new HashMap<>();
		ChatListVo check = new ChatListVo();
		UserVo user = (UserVo)session.getAttribute("loginId");
		UserVo toid = new UserVo();
		check.setClemail(user.getUemail());
		check.setUemail(clv.getUemail());
		List<ChatListVo> list = chatListService.chatListCheck(check);
		
		toid.setUemail(clv.getUemail());
		toid = userService.userInfo(toid);
		System.out.println(list.size());
		if(list.size() == 0) {
				clv.setClemail(user.getUemail());
				clv.setUemail(clv.getUemail());
			    chatListService.inserChatList(clv);
				result.put("result", Boolean.TRUE);
				result.put("toid", toid);
		}else {
			result.put("result", Boolean.FALSE);
		}
		return result;
	}
	
	@PostMapping("/listDel")
	@ResponseBody
	public Map msgListDelete(HttpSession session,String uemail){
		Map<String, Object> result = new HashMap<>();
		int success = chatListService.deleteList(uemail);
		if(success == 1) {
			result.put("result", Boolean.TRUE);
		}else {
			result.put("result", Boolean.FALSE);
		}
		return result;
	}
}
