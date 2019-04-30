package com.web.chatlist.vo;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatListVo {
	private int clid;
	private String clemail;
	private String uemail;
	private String uname;
	private int ustatus;
	private int umystatus;
	private String uprofilename;
}
