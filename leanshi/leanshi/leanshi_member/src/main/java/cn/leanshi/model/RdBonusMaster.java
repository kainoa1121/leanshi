package cn.leanshi.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 奖金主表
 * @author :ldq
 * @date:2018/10/15
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class RdBonusMaster {

	private String periodCode;//'业务周期'
	private String mCode; //会员编号
	private BigDecimal bonusRetail; //当期全部零售产生的奖励
	private BigDecimal bonusNewVip; //当期推荐新的VIP产生奖励
	private BigDecimal bonusDevp1; //市场拓展奖第1代
	private BigDecimal bonusDevp2; //市场拓展奖第2代
	private BigDecimal bonusDevpShare; //市场拓展奖分红
	private BigDecimal bonusDevpSum; //市场拓展奖合计
	private BigDecimal bonusLdDirect; //领导奖直接奖励
	private BigDecimal bonusLdIndirect; //领导奖间接奖励
	private BigDecimal bonusLdSupport; //领导奖同级支持奖
	private BigDecimal bonusLdSum; //领导奖合计
	private BigDecimal chargeService; //服务费扣款
	private BigDecimal bonusSum; //总奖励合计


}
