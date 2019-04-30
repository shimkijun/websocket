package com.web.channel.dao;

import java.util.List;

import com.web.channel.vo.ChannelVo;

public interface IChannelMapper {	
	int addChannel(ChannelVo ch);
	List<ChannelVo> channelList(ChannelVo ch);
	List<ChannelVo> channelContacts(ChannelVo ch);
	ChannelVo channelInfo(ChannelVo ch);
	ChannelVo channeInvite(ChannelVo ch);
	int attendChannel(ChannelVo ch);
	int channelDelete(String code);
}
