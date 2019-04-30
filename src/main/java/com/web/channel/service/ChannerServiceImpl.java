package com.web.channel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.channel.dao.IChannelDao;
import com.web.channel.vo.ChannelVo;

@Service
public class ChannerServiceImpl implements IChannelService{

	@Autowired
	private IChannelDao channelDao;
	
	@Override
	public int addChannel(ChannelVo ch){
		return channelDao.insertChannel(ch);
	}

	@Override
	public List<ChannelVo> channeList(ChannelVo ch) {
		return channelDao.selectList(ch);
	}

	@Override
	public ChannelVo channelInfo(ChannelVo ch) {
		return channelDao.channelInfo(ch);
	}

	@Override
	public ChannelVo channeInvite(ChannelVo ch) {
		return channelDao.channeInvite(ch);
	}

	@Override
	public int attendChannel(ChannelVo ch) {
		return channelDao.attendChannel(ch);
	}

	@Override
	public List<ChannelVo> channelContacts(ChannelVo ch) {
		return channelDao.channelContacts(ch);
	}

	@Override
	public int channelDelete(String code) {
		return channelDao.channelDelete(code);
	}
	

}
