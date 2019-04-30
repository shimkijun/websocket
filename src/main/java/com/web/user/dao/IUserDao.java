package com.web.user.dao;

import java.util.List;

import com.web.user.vo.UserVo;

public interface IUserDao {
	UserVo userInfo(UserVo user);
	UserVo getUser(UserVo user);
	int userRegister(UserVo user);
	List<UserVo> userAllList();
	int userOnOff(UserVo user);
	int userCondition(UserVo user);
	UserVo userCodename(UserVo user);
	int userModify(UserVo user);
	int deleteUser(String email);
}
