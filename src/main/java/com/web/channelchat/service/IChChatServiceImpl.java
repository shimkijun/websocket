package com.web.channelchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.channelchat.dao.IChChatDao;
import com.web.channelchat.vo.ChannelChatVo;

@Service
public class IChChatServiceImpl implements IChChatService{

	@Autowired
	private IChChatDao dao;


	public int chSendMessage(ChannelChatVo cc) {
		return dao.chSendMessage(cc);
	}


	@Override
	public List<ChannelChatVo> channelchat(ChannelChatVo cc) {
		return dao.channelchat(cc);
	}

	
}
