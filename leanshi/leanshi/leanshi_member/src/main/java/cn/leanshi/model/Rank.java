package cn.leanshi.model;

import lombok.Data;

/**
 * 级别表
 * @author :ldq
 * @date:2018/9/5
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class Rank {

	private int rankId;//ID
	private String rankName;//级别名称
	private String rankShortName;//级别简称
	private int rankOrder;//级别序号
	private int rankClass;//级别的等级 0：无级别 1：VIP级 2：代理级 3：店级 4：旗舰店级
	private int staticYn;//是否固定 0：不固定，可升降 1：固定，只升不降
	private int rPreRank;//前置级别（要想达到本级别，至少要先达到的级别）
	private int activePv;//当月重消PV要求
	private int rankKeepMonths;//级别可保持的月数
}
