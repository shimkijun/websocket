package com.web.friends.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.web.friends.vo.FriendsVo;

@Transactional(rollbackFor = {Exception.class})
public interface IFriendsService {
	List<FriendsVo> friendList(FriendsVo fr);
	int friendRequest(FriendsVo fr);
	FriendsVo friendRequestSearch(FriendsVo fr);
	List<FriendsVo> friendSuggestSearch(FriendsVo fr);
	int friendAccept(FriendsVo fr);
	int friendDelete(FriendsVo fr);
	List<FriendsVo> friendOnlineCount(FriendsVo fr);
}
