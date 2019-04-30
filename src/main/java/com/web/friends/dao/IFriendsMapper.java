package com.web.friends.dao;

import java.util.List;

import com.web.friends.vo.FriendsVo;
import com.web.user.vo.UserVo;

public interface IFriendsMapper {
	int friendRequest(FriendsVo fr);
	List<FriendsVo> myFriendsList(FriendsVo fr);
	List<FriendsVo> allList();
	FriendsVo friendRequestSearch(FriendsVo fr);
	List<FriendsVo> friendSuggestSearch(FriendsVo fr);
	int friendAccept(FriendsVo fr);
	int friendDelete(FriendsVo fr);
	List<FriendsVo> friendOnlineCount(FriendsVo fr);
}
