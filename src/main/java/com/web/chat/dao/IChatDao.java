package com.web.chat.dao;

import java.util.List;

import com.web.chat.vo.ChatVo;

public interface IChatDao {
	List<ChatVo> selectChatList(ChatVo cv);
	int userInsertMsg(ChatVo cv);
}
