package com.web.chatlist.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.web.chat.vo.ChatVo;
import com.web.chatlist.vo.ChatListVo;

@Transactional(rollbackFor = {Exception.class})
public interface IChatListService {
	List<ChatListVo> chatList(ChatListVo clv);
	int inserChatList(ChatListVo clv);
	List<ChatListVo> chatListCheck(ChatListVo clv);
	int deleteList(String uemail);
}
