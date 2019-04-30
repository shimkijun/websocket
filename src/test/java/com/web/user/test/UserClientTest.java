package com.web.user.test;

import static org.junit.Assert.assertEquals;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.web.channel.service.IChannelService;
import com.web.channel.vo.ChannelVo;
import com.web.chatlist.dao.IChatListMapper;
import com.web.chatlist.vo.ChatListVo;
import com.web.friends.service.IFriendsService;
import com.web.friends.vo.FriendsVo;
import com.web.security.PasswordEncoding;
import com.web.user.service.IUserService;
import com.web.user.vo.UserVo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:testConfig/test-context.xml",
		"file:src/main/webapp/WEB-INF/spring/spring-security.xml"
		})
public class UserClientTest {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IChannelService channelService;

	@Autowired
	private IFriendsService fs;
	
	
	@Test
	@Ignore
	public void addChannel() {
		ChannelVo ch = new ChannelVo();
		ch.setCname("채널이름1");
		ch.setCnick("닉네임1");
		ch.setCuemail("이메일1");
		ch.setCuname("ㅅㄱㅈ1");
		channelService.addChannel(ch);
	}
	@Test
	public void selectfs() {
		FriendsVo fsvo = new FriendsVo();
		fsvo.setFreqemail("shimkijun@naver.com");
		fs.friendList(fsvo);
	}
	@Test
	@Ignore
	public void insertfs() {
		FriendsVo fsvo = new FriendsVo();
		/*fsvo.setFReqEmail("shimkijun@naver.com");
		fsvo.setFResEmail("123123@naver.com");
		fsvo.setFReqCode(1234);
		fsvo.setFResCode(3456);
		fs.friendRequest(fsvo);*/
	}
	@Test
	@Ignore
	public void passwordXmlTest() {
		String password = "abcd";
		System.out.println(passwordEncoder.encode(password));
		System.out.println(passwordEncoder.matches(password, passwordEncoder.encode(password)));
	}
	
	
	@Test
	@Ignore
	public void passwordTest() {
		String password = "abcd";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		PasswordEncoding passwordEncoding = new PasswordEncoding(passwordEncoder);
		System.out.println(passwordEncoder.encode(password));
		System.out.println(passwordEncoding.matches("444", passwordEncoding.encode(password)));
	}
	
	@Test
	@Ignore
	public void userSelect() {
		UserVo user = new UserVo();
		user.setUid(1);
		user.setUemail("ff");
		user = service.getUser(user);
		System.out.println(user);
		assertEquals("ff",user.getUemail());
	}
	
	@Test
	@Ignore
	public void userInsert() throws UnknownHostException {
		// 아이피 주소를 가져오는 함수
		InetAddress local = InetAddress.getLocalHost();
		String ip = local.getHostAddress(); 
		
		//4자리의 랜덤 숫자
		Random ran = new Random();
		int code =ran.nextInt(9998)+1;
		
		UserVo user = new UserVo();
		user.setUemail("skj3");
		user.setUname("test1");
		user.setUpassword(passwordEncoder.encode("1234"));
		user.setUcode(code);
		user.setUip(ip);
		service.registerUser(user);
		
	}
	
	@Test
	@Ignore
	public void userLogin() {
		UserVo user = new UserVo();
		user.setUemail("shimkijun@naver.com");
		user = service.getUser(user);
		if(user != null) {
			if(passwordEncoder.matches("1234567",user.getUpassword())) {
				System.out.println("로그인 성공");
			}else{
				System.out.println("비밀번호가 틀립니다.");
			}
		}else {
			System.out.println("아이디가 없습니다. 회원가입하세요");
		}
	}
	
	
}
