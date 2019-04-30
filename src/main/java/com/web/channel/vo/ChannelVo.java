package com.web.channel.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="cicon")
public class ChannelVo {
	private int cid;
	private String cname;
	private int clevel;
	private String cnick;
	private String cuemail;
	private String cmaster;
	private String cuname;
	private String cicon;
	private String ccode;
	private int ustatus;
	private int umystatus;
	private String uprofilename;
}
