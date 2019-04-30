package com.web.chat.websocket;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.channel.service.IChannelService;
import com.web.channel.vo.ChannelVo;
import com.web.user.vo.UserVo;

public class ChatEchoHandler extends TextWebSocketHandler{
		Map<String,WebSocketSession> userSessions = new HashMap<>();
	
		@Autowired
		IChannelService channelService;
		
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			String senderId = getId(session);
			userSessions.put(senderId,session);
			System.out.println("채팅:::::::::::::::::::::::::"+senderId+"유저 세션:::::::::::::::::::::::::"+ userSessions);
		}

		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			Map<String, Object> jParser = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			TextMessage tmpMsg = null;
			UserVo user = new UserVo();
			ChannelVo ch = new ChannelVo();
			String senderId = getId(session);
			String msg = message.getPayload();
			
			jParser = mapper.readValue(msg, new TypeReference<Map<String, String>>(){});
			
			if("message".equals(jParser.get("type"))) {
				WebSocketSession toid = userSessions.get(jParser.get("email"));
				WebSocketSession fromid = userSessions.get(senderId);
				String code = (String) jParser.get("msg");
				ch.setCuemail(senderId);
				ch.setCcode(code);
				ch = channelService.channeInvite(ch);
				String jsonString = mapper.writeValueAsString(ch);
				jParser.put("channel", jsonString);
				String chInfo = mapper.writeValueAsString(jParser);
				if(StringUtils.isNotEmpty(msg)) {
					if(ch == null) {	
						tmpMsg = new TextMessage(msg);
					}else {
						tmpMsg = new TextMessage(chInfo);
					}
					if(toid != null) {
						toid.sendMessage(tmpMsg);
						fromid.sendMessage(tmpMsg);
					}else {
						fromid.sendMessage(tmpMsg);
					}
				}
				
				System.out.println("toid:::::::::::::"+toid+"\n"+
						"fromid::::::::::::::::::::"+fromid+"\n"+
						"채널 정보 :::::::::::::::::::::::"+jsonString+"\n");
			}
			
		}
		
		private String getId(WebSocketSession session) {
			Map<String, Object> httpSession = session.getAttributes();
			UserVo loginUser = (UserVo)httpSession.get("loginId");
			if(null == loginUser) 
				return session.getId();
			else
				return loginUser.getUemail();
		}

		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			Map<String, Object> httpSession = session.getAttributes();
			String senderId = getId(session);
			userSessions.remove(senderId);
		}

}
