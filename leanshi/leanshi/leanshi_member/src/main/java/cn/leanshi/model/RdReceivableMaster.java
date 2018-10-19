package cn.leanshi.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员欠款主表
 * @author :ldq
 * @date:2018/10/16
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class RdReceivableMaster {

	private String mCode;//'会员编号'
	private String mNickname;//'昵称'
	private BigDecimal receivableBlance;//会员欠款余额
	private String currencyCode;
	private int status;//状态 0：正常 1：异常
	private int bnsDeductPecent;//自动扣工资的百分比


}
