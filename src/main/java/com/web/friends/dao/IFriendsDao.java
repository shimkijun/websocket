package com.web.friends.dao;

import java.util.List;

import com.web.friends.vo.FriendsVo;
import com.web.user.vo.UserVo;

public interface IFriendsDao {
	int insertFriends(FriendsVo fr);
	List<FriendsVo> selectFriends(FriendsVo fr);
	FriendsVo insertFrSearch(FriendsVo fr);
	List<FriendsVo> suggestFrSearch(FriendsVo fr);
	int updateAccept(FriendsVo fr);
	int deleteFriends(FriendsVo fr);
	List<FriendsVo> selectFriendsCount(FriendsVo fr);
}
