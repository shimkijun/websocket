package com.web.channel.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.channel.service.IChannelService;
import com.web.channel.vo.ChannelVo;
import com.web.channelchat.service.IChChatService;
import com.web.channelchat.vo.ChannelChatVo;
import com.web.chat.vo.ChatVo;
import com.web.friends.service.IFriendsService;
import com.web.friends.vo.FriendsVo;
import com.web.user.service.IUserService;
import com.web.user.vo.UserVo;

@Controller
@RequestMapping("/channels")
public class ChannelServiceController {

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IFriendsService friendsService;
	@Autowired
	private IChChatService channelChatService;
	
	
	@PostMapping("/add")
	public String addChannel(HttpSession session,ChannelVo ch){
		UserVo user = (UserVo)session.getAttribute("loginId");
		String ccode = UUID.randomUUID().toString();
		if(!ch.getCname().isEmpty()) {
			ch.setCname(ch.getCname());
			ch.setClevel(1);
			ch.setCnick(user.getUname());
			ch.setCuemail(user.getUemail());
			ch.setCmaster(user.getUemail());
			ch.setCuname(user.getUname());
			ch.setCcode(ccode);
			channelService.addChannel(ch);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/ch/{ch}")
	@ResponseBody
	public Map channelChatList(HttpSession session,@PathVariable String ch){
		Map<String, Object> result = new HashMap<>();
		List<ChannelVo> list = new ArrayList<>();
		List<ChannelChatVo> chatList = new ArrayList<>();
		ChannelVo co = new ChannelVo();
		ChannelChatVo cc = new ChannelChatVo();
		UserVo user = (UserVo)session.getAttribute("loginId");
		co.setCuemail(user.getUemail());
		co.setCcode(ch);
		co = channelService.channelInfo(co);
		co.setCcode(ch);
		list = channelService.channelContacts(co);
		cc.setCcode(ch);
		chatList = channelChatService.channelchat(cc);
		result.put("user", list);
		result.put("msg", chatList);
		result.put("result", Boolean.TRUE);
		result.put("data", co);
		
		return result;
	}
	
	@PostMapping("/attend")
	@ResponseBody
	public Map attendChannel(HttpSession session,ChannelVo ch){
		Map<String, Object> result = new HashMap<>();
		System.out.println("::::::::::::::::ch::::::::::::::::attend"+ch);
		UserVo user = (UserVo)session.getAttribute("loginId");
		ch.setCuemail(user.getUemail());
		ch.setCcode(ch.getCcode());
		if(channelService.channelInfo(ch) == null) {
			ChannelVo attend = new ChannelVo();
			attend.setClevel(0);
			attend.setCnick(user.getUname());
			attend.setCuemail(user.getUemail());
			attend.setCuname(user.getUname());
			attend.setCmaster(ch.getCmaster());
			attend.setCcode(ch.getCcode());
			channelService.attendChannel(attend);
			result.put("result", Boolean.TRUE);
			result.put("attend", attend);
		}else {
			result.put("result", Boolean.FALSE);
		}
		
		
		return result;
	}
	
	@PostMapping("/send")
	@ResponseBody
	public Map channelChatMessage(HttpSession session,ChannelChatVo cc){
		Map<String, Object> result = new HashMap<>();
		UserVo user = (UserVo)session.getAttribute("loginId");
		
		
		cc.setCcfromid(user.getUemail());
		cc.setCcmessage(cc.getCcmessage());
		cc.setCcode(cc.getCcode());

		int success = channelChatService.chSendMessage(cc);

		if(success == 1) {
			result.put("result", Boolean.TRUE);
		}else {
			result.put("result", Boolean.FALSE);
		}
		return result;
	}
	
	@DeleteMapping("/delete/{code}")
	@ResponseBody
	public Map userChatList(HttpSession session,@PathVariable String code){
		Map<String, Object> result = new HashMap<>();
		channelService.channelDelete(code);
		result.put("result",Boolean.TRUE);
		return result;
	}
	
}
