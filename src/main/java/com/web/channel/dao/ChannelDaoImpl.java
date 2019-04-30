package com.web.channel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.channel.vo.ChannelVo;

@Repository
public class ChannelDaoImpl implements IChannelDao{

	@Autowired
	private IChannelMapper channelMapper;
	
	@Override
	public int insertChannel(ChannelVo ch) {
		return channelMapper.addChannel(ch);
	}

	@Override
	public List<ChannelVo> selectList(ChannelVo ch) {
		List<ChannelVo> list = channelMapper.channelList(ch);
		return list;
	}

	@Override
	public ChannelVo channelInfo(ChannelVo ch) {
		return channelMapper.channelInfo(ch);
	}

	@Override
	public ChannelVo channeInvite(ChannelVo ch) {
		return channelMapper.channeInvite(ch);
	}

	@Override
	public int attendChannel(ChannelVo ch) {
		return channelMapper.attendChannel(ch);
	}

	@Override
	public List<ChannelVo> channelContacts(ChannelVo ch) {
		return channelMapper.channelContacts(ch);
	}

	@Override
	public int channelDelete(String code) {
		return channelMapper.channelDelete(code);
	}

}
