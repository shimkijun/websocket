package com.web.chat.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.chat.vo.ChatVo;

@Repository
public class ChatDaoImpl implements IChatDao{

	@Autowired
	private IChatMapper chatMapper;
	
	@Override
	public List<ChatVo> selectChatList(ChatVo cv) {
		List<ChatVo> chatList = chatMapper.userChatList(cv);
		return chatList;
	}

	@Override
	public int userInsertMsg(ChatVo cv) {
		return chatMapper.userSendMessage(cv);
	}
	
}
