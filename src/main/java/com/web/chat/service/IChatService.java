package com.web.chat.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.web.chat.vo.ChatVo;

@Transactional(rollbackFor = {Exception.class})
public interface IChatService {
	List<ChatVo> userChatList(ChatVo cv);
	int userSendMessage(ChatVo cv);
}
