package com.web.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.chat.dao.IChatDao;
import com.web.chat.vo.ChatVo;

@Service
public class ChatServiceImpl implements IChatService{

	@Autowired
	private IChatDao chatDao;
	
	@Override
	public List<ChatVo> userChatList(ChatVo cv) {
		return chatDao.selectChatList(cv);
	}

	@Override
	public int userSendMessage(ChatVo cv) {
		return chatDao.userInsertMsg(cv);
	}

}
