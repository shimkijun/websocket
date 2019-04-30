package com.web.friends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.friends.dao.IFriendsDao;
import com.web.friends.vo.FriendsVo;
import com.web.user.vo.UserVo;

@Service
public class FriendsServiceImpl implements IFriendsService{

	@Autowired
	private IFriendsDao friendsDao;

	@Override
	public List<FriendsVo> friendList(FriendsVo fr) {
		return friendsDao.selectFriends(fr);
	}

	@Override
	public int friendRequest(FriendsVo fr) {
		return friendsDao.insertFriends(fr);
	}

	@Override
	public FriendsVo friendRequestSearch(FriendsVo fr) {
		return friendsDao.insertFrSearch(fr);
	}

	@Override
	public List<FriendsVo> friendSuggestSearch(FriendsVo fr) {
		return friendsDao.suggestFrSearch(fr);
	}

	@Override
	public int friendAccept(FriendsVo fr) {
		return friendsDao.updateAccept(fr);
	}

	@Override
	public int friendDelete(FriendsVo fr) {
		return friendsDao.deleteFriends(fr);
	}

	@Override
	public List<FriendsVo> friendOnlineCount(FriendsVo fr) {
		return friendsDao.selectFriendsCount(fr);
	}
	
}
