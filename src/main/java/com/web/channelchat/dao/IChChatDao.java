package com.web.channelchat.dao;

import java.util.List;

import com.web.channelchat.vo.ChannelChatVo;

public interface IChChatDao {
	int chSendMessage(ChannelChatVo cc);
	List<ChannelChatVo> channelchat(ChannelChatVo cc);
}
