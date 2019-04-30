package com.web.channelchat.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.web.channelchat.vo.ChannelChatVo;

@Transactional(rollbackFor = {Exception.class})
public interface IChChatService{
	
	int chSendMessage(ChannelChatVo cc);
	List<ChannelChatVo> channelchat(ChannelChatVo cc);
}
