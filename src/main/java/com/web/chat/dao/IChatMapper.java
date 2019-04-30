package com.web.chat.dao;

import java.util.List;

import com.web.chat.vo.ChatVo;

public interface IChatMapper {
	List<ChatVo> userChatList(ChatVo cv);
	int userSendMessage(ChatVo cv);
}
