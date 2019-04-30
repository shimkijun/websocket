package com.web.friends.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.web.friends.service.IFriendsService;
import com.web.friends.vo.FriendsVo;
import com.web.user.service.IUserService;
import com.web.user.vo.UserVo;

@Controller
@RequestMapping("friends")
public class FriendsServiceController {

	private static final Logger logger = LoggerFactory.getLogger(FriendsServiceController.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IFriendsService friendsService;
	
	@GetMapping("/me")
	@ResponseBody
	public Map friendList(HttpSession session){
		Map<String, Object> result = new HashMap<>();
		UserVo user = (UserVo)session.getAttribute("loginId");
		FriendsVo friends = new FriendsVo();
		friends.setFreqemail(user.getUemail());
		List<FriendsVo> myFriends = friendsService.friendList(friends);
		friends.setFresemail(user.getUemail());
		result.put("result", Boolean.TRUE);
		result.put("data", myFriends);
		return result;
	}
	
	@GetMapping("/suggest")
	@ResponseBody
	public Map friendSuggestList(HttpSession session){
		Map<String, Object> result = new HashMap<>();
		UserVo user = (UserVo)session.getAttribute("loginId");
		FriendsVo friends = new FriendsVo();
		friends.setFresemail(user.getUemail());
		List<FriendsVo> myFriends = friendsService.friendSuggestSearch(friends);
		result.put("result", Boolean.TRUE);
		result.put("data", myFriends);
		return result;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map friendDelete(HttpSession session,String freqemail){
		System.out.println(freqemail + "======================idididid");
		Map<String, Object> result = new HashMap<>();
		UserVo user = (UserVo)session.getAttribute("loginId");
		FriendsVo friends = new FriendsVo();
		friends.setFreqemail(user.getUemail());
		friends.setFresemail(freqemail);
		int delete = friendsService.friendDelete(friends);
		if(delete > 0) {
			result.put("result", Boolean.TRUE);
			
		}else {
			result.put("result", Boolean.FALSE);
		}
		return result;
	}
	
	@PutMapping("/accept")
	@ResponseBody
	public Map friendAccept(HttpSession session,@RequestBody FriendsVo fr){
		Map<String, Object> result = new HashMap<>();
		logger.info("받은값 ==================" + fr.getFreqemail());
		UserVo user = (UserVo)session.getAttribute("loginId");
		String resId = user.getUemail();
		String reqId = fr.getFreqemail();
		int resCode = user.getUcode();
		int reqCode = fr.getFreqcode();
		FriendsVo friends = new FriendsVo();
		friends.setFreswait(1);
		friends.setFreqemail(reqId);
		friends.setFresemail(resId);
		friends.setFreqemail(resId);
		friends.setFresemail(reqId);
		int accept = friendsService.friendAccept(friends);
		if(accept > 0) {
			FriendsVo fr2 = new FriendsVo();
			fr2.setFreqemail(resId);
			fr2.setFresemail(reqId);
			fr2.setFreqcode(resCode);
			fr2.setFrescode(reqCode);
			fr2.setFreswait(1);
			friendsService.friendRequest(fr2);
			result.put("result", Boolean.TRUE);
		}else {
			result.put("result", Boolean.FALSE);
		}
		return result;
	}
	
	
	@PostMapping("/add")
	@ResponseBody
	public Map addFriend(HttpSession session,String tag){
		Map<String, Object> result = new HashMap<>();
		try {
			UserVo user = null;
			FriendsVo fr =  null;
			FriendsVo fr1 =  null;
			String reqId = "";
			String resId = "";
			int reqCode = 0;
			int resCode = 0;
			
			UserVo loginId = (UserVo) session.getAttribute("loginId");
			user = new UserVo();
			String[] codeArr = tag.split("#");
			String name = codeArr[0];
			int code = Integer.parseInt(codeArr[1]);
			user.setUname(name);
			user.setUcode(code);
			user = userService.getUserCodename(user);
			if(user == null) {
				result.put("check",Boolean.FALSE);
			}else if(user != null) {
				result.put("check",Boolean.TRUE);
				reqId = loginId.getUemail();
				resId = user.getUemail();
				reqCode = loginId.getUcode();
				resCode = user.getUcode();
				if(reqId.equals(resId) || resId.equals(reqId)) {
					result.put("same",Boolean.TRUE);
				}else{
					fr = new FriendsVo();
					fr.setFreqemail(reqId);
					fr.setFresemail(resId);
					fr = friendsService.friendRequestSearch(fr);
					fr1 = new FriendsVo();
					fr1.setFreqemail(resId);
					fr1.setFresemail(reqId);
					fr1 = friendsService.friendRequestSearch(fr);
					if(fr == null && fr1 == null){
						FriendsVo fr2 = new FriendsVo();
						fr2.setFreqemail(reqId);
						fr2.setFresemail(resId);
						fr2.setFreqcode(reqCode);
						fr2.setFrescode(resCode);
						fr2.setFreswait(0);
						friendsService.friendRequest(fr2);
						result.put("result", Boolean.TRUE);
						result.put("friends", fr);
					}else {
						if(fr.getFreswait() == 0) {
							result.put("request", Boolean.FALSE);
						}else if(fr.getFreswait() == 1) {
							result.put("request", Boolean.TRUE);
						}
					}
				}
			}	
		}catch (ArrayIndexOutOfBoundsException e) {
		}catch(NullPointerException e) {
		}catch(NumberFormatException e) {
		}

		return result;
		
	}
}
