package com.web.user.vo;

import java.util.Date;
import lombok.Data;
import lombok.ToString;

@Data 
@ToString(exclude ="upassword")
public class UserVo {
	private int uid;
	private String uemail;
	private String uname;
	private String upassword;
	private String uip;
	private int ucode;
	private int uverification;
	private int ustatus;
	private int umystatus;
	private String uprofilename;
	private Date udate;
}

