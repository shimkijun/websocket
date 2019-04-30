package com.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.channel.service.IChannelService;
import com.web.channel.vo.ChannelVo;
import com.web.chatlist.service.IChatListService;
import com.web.chatlist.vo.ChatListVo;
import com.web.friends.service.IFriendsService;
import com.web.friends.vo.FriendsVo;
import com.web.user.service.IUserService;
import com.web.user.vo.UserVo;


@Controller
public class MainController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IChannelService channelService;
	
	@Autowired
	private IFriendsService friendsService;
	
	@Autowired
	private IChatListService chatListService;
	
	@GetMapping("/")
	public String index(HttpSession session,Model model){
		UserVo user = new UserVo();
		ChannelVo ch = new ChannelVo();
		FriendsVo fr = new FriendsVo();
		ChatListVo clv = new ChatListVo();
		int cnt = 0;
		user = (UserVo)session.getAttribute("loginId");
		ch.setCuemail(user.getUemail());
		List<ChannelVo> list = channelService.channeList(ch);
		fr.setFreqemail(user.getUemail());
		List<FriendsVo> count = friendsService.friendOnlineCount(fr);
		model.addAttribute("myChannel",list);
		model.addAttribute("userInfo", userService.getUser(user));
		for(FriendsVo cc:count) {
			if(cc.getUstatus() == 1) {
				cnt += cc.getUstatus();
			}
		}
		clv.setClemail(user.getUemail());
		List<ChatListVo> chatList = chatListService.chatList(clv);
		
		model.addAttribute("cnt",cnt);
		model.addAttribute("chatList",chatList);
		
		return "index";
	}
}
