package com.web.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.web.user.vo.UserVo;

@Transactional(rollbackFor = {Exception.class})
public interface IUserService {
	UserVo getUser(UserVo user);
	UserVo userInfo(UserVo user);
	int registerUser(UserVo user);
	List<UserVo> allListUser();
	int statusUpdate(UserVo user);
	int mystatusUpdate(UserVo user);
	UserVo getUserCodename(UserVo user);
	int userModify(UserVo user);
	int deleteUser(String email);
}
