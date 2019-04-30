package com.web.chatlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.chatlist.dao.IChatListDao;
import com.web.chatlist.vo.ChatListVo;


@Service
public class ChatListServiceImpl implements IChatListService{

	@Autowired
	private IChatListDao dao;
	
	@Override
	public List<ChatListVo> chatList(ChatListVo clv) {
		return dao.chatList(clv);
	}

	@Override
	public int inserChatList(ChatListVo clv) {
		return dao.insertChatList(clv);
	}

	@Override
	public List<ChatListVo> chatListCheck(ChatListVo clv) {
		return dao.chatListCheck(clv);
	}

	@Override
	public int deleteList(String uemail) {
		return dao.deleteList(uemail);
	}

}
