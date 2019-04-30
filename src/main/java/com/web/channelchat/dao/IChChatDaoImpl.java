package com.web.channelchat.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.channelchat.vo.ChannelChatVo;

@Repository
public class IChChatDaoImpl implements IChChatDao{

	@Autowired
	private IChChatMapper channelChatMapper;
	
	@Override
	public int chSendMessage(ChannelChatVo cc) {
		return channelChatMapper.chSendMessage(cc);
	}

	@Override
	public List<ChannelChatVo> channelchat(ChannelChatVo cc) {
		return channelChatMapper.channelchat(cc);
	}

}
