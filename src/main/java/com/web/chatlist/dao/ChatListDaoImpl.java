package com.web.chatlist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.chat.vo.ChatVo;
import com.web.chatlist.vo.ChatListVo;

@Repository
public class ChatListDaoImpl implements IChatListDao{

	@Autowired
	private IChatListMapper chatListMapper;
	
	@Override
	public List<ChatListVo> chatList(ChatListVo clv) {
		return chatListMapper.chatList(clv);
	}

	@Override
	public int insertChatList(ChatListVo clv) {
		return chatListMapper.insertChatList(clv);
	}

	@Override
	public List<ChatListVo> chatListCheck(ChatListVo clv) {
		return chatListMapper.chatListCheck(clv);
	}

	@Override
	public int deleteList(String uemail) {
		return chatListMapper.deleteList(uemail);
	}

	
	
}
