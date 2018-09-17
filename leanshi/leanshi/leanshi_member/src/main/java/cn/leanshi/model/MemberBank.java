package cn.leanshi.model;

import lombok.Data;

/**
 * 会员银行信息
 * @author :ldq
 * @date:2018/8/2
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class MemberBank {
	private int oId; //id
	private String mCode;//'会员编号'
	private String accType;//'账户类型:第三方支付账号、储蓄卡、信用卡'
	private String currency;//'币种'
	private String accCode;//'账户号码'
	private String accName;//'账户名'
	private String bankCode;//'银行名称编码'
	private String bankDetail;//'银行详细信息'
	private String validThru;//'有效期:MM/YY'
	private String cvv2;//'信用卡CVV2码:3位'
	private String paymentDefault;//'默认支付方式'
	private String withdrawDefault;//'默认提现方式'
	private String rechargeDefault;//'默认充值账户'


}
