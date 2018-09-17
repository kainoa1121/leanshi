package cn.leanshi.model;

import lombok.Data;

/**
 * 会员账户信息
 * @author :ldq
 * @date:2018/9/10
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class MemberAccount {

	private String mCode;//会员编号
	private int bonusStatus;//奖励账户状态: 0.未激活 1.正常 2.冻结
	private String bonusCurrency;//奖金币种
	private int bonusBlance;//奖金账户余额
	private int walletStatus;//报单币账户状态: 0.未激活 1.正常 2.冻结
	private String walletCurrency;//报单币币种
	private int walletBlance;//报单币账户余额
	private int redemptionStatus;//换购积分账户状态: 0未激活 1正常 2冻结
	private String redemptionCurrency;//换购积分币种
	private int redemptionBlance;//换购积分账户余额
	private String weChatAcc;//微信账户
	private String alipayAcc;//支付宝账记有
	private String paymentPhone;//支付绑定手机:忘记密码后找回的验证码手机
	private String paymentPWD;//支付密码:加密保存

}
