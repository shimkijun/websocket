package com.web.chat.vo;


import java.util.Date;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatVo {
	private int ucid;
	private String ucfromid;
	private String ucfromname;
	private String uctoid;
	private String ucmessage;
	private Date ucDate;
	private int ustatus;
	private int umystatus;
	private String uprofilename;
	private String cname;
	private String ccode;
	private String cmaster;
}
