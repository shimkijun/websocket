package com.web.user.dao;

import java.util.List;

import com.web.user.vo.UserVo;

public interface IUserMapper {
	UserVo selectUserById(UserVo user);
	UserVo selectUser(UserVo user);
	int insertUser(UserVo user);
	List<UserVo> selectUserList();
	int statusUpdate(UserVo user);
	int mystatusUpdate(UserVo user);
	UserVo getUserCodename(UserVo user);
	int userModify(UserVo user);
	int deleteUser(String email);
}
