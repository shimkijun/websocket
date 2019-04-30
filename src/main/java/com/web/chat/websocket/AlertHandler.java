package com.web.chat.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.user.service.IUserService;
import com.web.user.vo.UserVo;


public class AlertHandler extends TextWebSocketHandler{

	Map<String,WebSocketSession> userSessions = new HashMap<>();
	
	@Autowired
	IUserService userService;
	
	private UserVo user = null;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> httpSession = session.getAttributes();
		String senderId = getId(session);
		userSessions.put(senderId,session);
		System.out.println("접속 유저 :::::::::: "+ userSessions);
	}

	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> httpSession = session.getAttributes();
		UserVo loginUser = (UserVo)httpSession.get("loginId");
		
		user = new UserVo();
		Map<String, Object> jParser = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		String msg = message.getPayload();
		jParser = mapper.readValue(msg, new TypeReference<Map<String, String>>(){});

		String codename = (String) jParser.get("codename");
		String[] reg = codename.split("#");
		String name = reg[0];
		int code = (Integer.parseInt(reg[1]));
		System.out.println("코드네임 :::::::::::::::: "+msg);
		user.setUname(name);
		user.setUcode(code);
		user = userService.getUserCodename(user);
		System.out.println("user:::::::::::::"+user);
		
			if("addfriends".equals(jParser.get("type"))) {
				if(userSessions.get(user.getUemail()) != null) {
					// type , 친구요청 한 이름 , 친구요청 한 메일 , 친구요청 한 코드 , 친구요청 한 썸네일 
					String alert = "addfriends,"+loginUser.getUname()+","+loginUser.getUemail()+","+loginUser.getUcode()+","+loginUser.getUprofilename();
					WebSocketSession id = userSessions.get(user.getUemail());
					id.sendMessage(new TextMessage(alert));
				}
			}
		
		
	}

	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String senderId = getId(session);
		userSessions.remove(senderId);
	}
	
	private String getId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVo loginUser = (UserVo)httpSession.get("loginId");
		if(null == loginUser) 
			return session.getId();
		else
			return loginUser.getUemail();
	}
	
	private String userCheck(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVo loginUser = (UserVo)httpSession.get("loginId");
		if(null == loginUser) 
			return session.getId();
		else
			return loginUser.getUemail();
	}
	
}
