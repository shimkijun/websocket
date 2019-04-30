package com.web.channel.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.web.channel.vo.ChannelVo;

@Transactional(rollbackFor = {Exception.class})
public interface IChannelService {
	int addChannel(ChannelVo ch);
	List<ChannelVo> channeList(ChannelVo ch);
	List<ChannelVo> channelContacts(ChannelVo ch);
	ChannelVo channelInfo(ChannelVo ch);
	ChannelVo channeInvite(ChannelVo ch);
	int attendChannel(ChannelVo ch);
	int channelDelete(String code);
}
