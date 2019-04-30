package com.web.chatlist.dao;

import java.util.List;

import com.web.chat.vo.ChatVo;
import com.web.chatlist.vo.ChatListVo;

public interface IChatListMapper {
	List<ChatListVo> chatList(ChatListVo clv);
	int insertChatList(ChatListVo clv);
	List<ChatListVo> chatListCheck(ChatListVo clv);
	int deleteList(String uemail);
}
