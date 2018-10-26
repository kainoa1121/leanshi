package cn.leanshi.model;

import lombok.Data;

/**
 * 积分规则设置
 * @author :ldq
 * @date:2018/10/25
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class MemberIntegralRule {

	private Integer rId;//id
	private Integer rsCountBonusPoint;//返佣计入奖励积分比例
	private Integer bonusPointWdLimit;//奖励积分提现最低限额
	private Integer bonusPointWd;//奖励积分提现手续费
	private Integer bonusPointShopping;//奖励积分转入购物积分比例
	private Integer shoppingPointSr;//购物积分购物比例
	private Integer tranksShoppingPoint;//购物积分转账手续费


}
