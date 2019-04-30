package com.web.channel.dao;

import java.util.List;

import com.web.channel.vo.ChannelVo;

public interface IChannelDao {
	int insertChannel(ChannelVo ch);
	List<ChannelVo> selectList(ChannelVo ch);
	List<ChannelVo> channelContacts(ChannelVo ch);
	ChannelVo channelInfo(ChannelVo ch);
	ChannelVo channeInvite(ChannelVo ch);
	int attendChannel(ChannelVo ch);
	int channelDelete(String code);
}
