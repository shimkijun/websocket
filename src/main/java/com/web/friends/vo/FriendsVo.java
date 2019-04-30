package com.web.friends.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FriendsVo {
	private int fid;
	private String freqemail;
	private String fresemail;
	private int freqcode;
	private int frescode;
	private int freswait;
	private String uname;
	private int ustatus;
	private int umystatus;
	private String uprofilename;
}
