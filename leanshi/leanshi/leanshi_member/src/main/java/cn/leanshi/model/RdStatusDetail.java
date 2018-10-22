package cn.leanshi.model;

import lombok.Data;

import java.util.Date;

/**
 * 会员状态变更明细
 * @author :ldq
 * @date:2018/10/19
 * @description:leanshi_member cn.leanshi.controller
 */
@Data
public class RdStatusDetail {

	private String sId;//id
	private String mCode;//会员编号
	private String mNickname;//会员昵称
	private String statusType;//状态类型： 会员状态：MM  会员积分状态：MR
	private int statusBefore;//修改前状态
	private int statusAfter;//修改后状态
	private String updateBy;//操作人
	private Date updateTime;//操作时间
	private String updateDesc;//更改备注
}
