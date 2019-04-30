package com.web.channelchat.vo;


import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChannelChatVo {
	private int ccid;
	private String ccfromid;
	private String ucfromname;
	private String ccmessage;
	private Date ccdate;
	private String ccode;
	private String nick;
	private String uprofilename;
}
