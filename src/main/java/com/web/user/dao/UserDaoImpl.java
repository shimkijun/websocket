package com.web.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.user.vo.UserVo;

@Repository
public class UserDaoImpl implements IUserDao{

	@Autowired
	private IUserMapper userMapper;
	
	@Override
	public UserVo userInfo(UserVo user) {
		UserVo users = userMapper.selectUser(user);
		return users;
	}

	
	@Override
	public UserVo getUser(UserVo user) { 
		UserVo users = userMapper.selectUserById(user);
		return users;
	}

	@Override
	public int userRegister(UserVo user) {
		return userMapper.insertUser(user);
	}

	@Override
	public List<UserVo> userAllList() {
		List<UserVo> userList =  userMapper.selectUserList();
		return userList;
	}
	
	@Override
	public int userOnOff(UserVo user) {
		return userMapper.statusUpdate(user);
	}

	@Override
	public int userCondition(UserVo user) {
		return userMapper.mystatusUpdate(user);
	}

	@Override
	public UserVo userCodename(UserVo user) {
		UserVo users = userMapper.getUserCodename(user);
		return users;
	}


	@Override
	public int userModify(UserVo user) {
		return userMapper.userModify(user);
	}


	@Override
	public int deleteUser(String email) {
		return userMapper.deleteUser(email);
	}

	
}
