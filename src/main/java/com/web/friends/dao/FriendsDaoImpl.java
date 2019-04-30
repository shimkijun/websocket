package com.web.friends.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.friends.vo.FriendsVo;
import com.web.user.vo.UserVo;


@Repository
public class FriendsDaoImpl implements IFriendsDao{

	@Autowired
	private IFriendsMapper friendsMapper;

	@Override
	public List<FriendsVo> selectFriends(FriendsVo fr) {
		List<FriendsVo> frList = friendsMapper.myFriendsList(fr);
		return frList;
	}

	@Override
	public int insertFriends(FriendsVo fr) {
		return friendsMapper.friendRequest(fr);
	}

	@Override
	public FriendsVo insertFrSearch(FriendsVo fr) {
		FriendsVo frSearch = friendsMapper.friendRequestSearch(fr);
		return frSearch;
	}

	@Override
	public List<FriendsVo> suggestFrSearch(FriendsVo fr) {
		List<FriendsVo> frSearch = friendsMapper.friendSuggestSearch(fr);
		return frSearch;
	}

	@Override
	public int updateAccept(FriendsVo fr) {
		return friendsMapper.friendAccept(fr);
	}

	@Override
	public int deleteFriends(FriendsVo fr) {
		return friendsMapper.friendDelete(fr);
	}

	@Override
	public List<FriendsVo> selectFriendsCount(FriendsVo fr) {
		List<FriendsVo> count = friendsMapper.friendOnlineCount(fr);
		return count;
	}
	


}
