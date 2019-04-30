package com.web.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.user.dao.IUserDao;
import com.web.user.vo.UserVo;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao dao;
	
	@Override
	public UserVo userInfo(UserVo user) {
		return dao.userInfo(user);
	}
	
	@Override
	public UserVo getUser(UserVo user) {
		return dao.getUser(user);
	}

	@Override
	public int registerUser(UserVo user) {
		return dao.userRegister(user);
	}

	@Override
	public List<UserVo> allListUser() {
		return dao.userAllList();
	}

	@Override
	public int statusUpdate(UserVo user) {
		return dao.userOnOff(user);
	}

	@Override
	public int mystatusUpdate(UserVo user) {
		return dao.userCondition(user);
	}

	@Override
	public UserVo getUserCodename(UserVo user) {
		return dao.userCodename(user);
	}

	@Override
	public int userModify(UserVo user) {
		return dao.userModify(user);
	}

	@Override
	public int deleteUser(String email) {
		return dao.deleteUser(email);
	}

	

}
