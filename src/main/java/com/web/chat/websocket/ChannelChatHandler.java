package com.web.chat.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.channel.service.IChannelService;
import com.web.channel.vo.ChannelVo;
import com.web.friends.controller.FriendsServiceController;
import com.web.user.vo.UserVo;

public class ChannelChatHandler extends TextWebSocketHandler{
		private static final Logger logger = LoggerFactory.getLogger(FriendsServiceController.class);
		Map<String,WebSocketSession> userSessions = new HashMap<>();
		
		@Autowired
		IChannelService channelService;
		
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			Map<String, Object> httpSession = session.getAttributes();
			String senderId = getId(session);
			userSessions.put(senderId,session);
		}

		
		
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			Map<String, Object> jParser = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			ChannelVo ch = new ChannelVo();
			String msg = message.getPayload();
			jParser = mapper.readValue(msg, new TypeReference<Map<String, String>>(){});
			
			if("chMessage".equals(jParser.get("type"))) {
				String code = (String) jParser.get("channel");
	
				for(int i = 0; i < getUsers(code).size(); i++) {
					WebSocketSession toid = userSessions.get(getUsers(code).get(i));
					if(userSessions.get(getUsers(code).get(i)) != null) {
						toid.sendMessage(new TextMessage(msg));
						logger.info("현재 채널에 총 인원 :::::::::::::::::::: {}", getUsers(code).get(i));
						logger.info("현재 채널에 접속한 인원 :::::::::::::::::::: ["+(i+1)+"] :: "+toid);
					}
				}
				
				logger.info("보내는 메시지 :::::::::::::::::::: {}", msg);
				logger.info("채널 코드 :::::::::::::::::::: {}", code);
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
		
		private List<String> getUsers(String code) {
			List<String> users = new ArrayList<>();
			ChannelVo ch = new ChannelVo();
			ch.setCcode(code);
			List<ChannelVo> channel = (List<ChannelVo>)channelService.channelContacts(ch);
			for (ChannelVo c : channel) {
				users.add(c.getCuemail());
			}
			
			return users;
		}
		
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			System.out.println("afterConnectionClosed : " + session + " : " + status);
			Map<String, Object> httpSession = session.getAttributes();
			String senderId = getId(session);
			userSessions.remove(senderId);

		}
		
}
