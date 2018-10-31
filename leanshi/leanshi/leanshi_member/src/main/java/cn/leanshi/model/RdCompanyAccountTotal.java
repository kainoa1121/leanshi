package cn.leanshi.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author :ldq
 * @date:2018/10/30
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class RdCompanyAccountTotal {

	private String countDate;//统计日期
	private BigDecimal orderWd;//订单现金收入
	private BigDecimal cashRepayment;//现金还款
	private BigDecimal cashIncomeTotal;//总现金收入
	private BigDecimal bonusRr;//奖励积分还款
	private BigDecimal bonusBt;//奖励积分转购物积分
	private BigDecimal bonusWd;//奖励积分提现
	private BigDecimal bonusIncomeTotal;//总奖励积分收入
	private BigDecimal shoppingIncomeTotal;//总购物积分收入
	private BigDecimal exchangeIncomeTotal;//总换购积分收入
	private BigDecimal wd;//提现
	private BigDecimal nr;//借款
	private BigDecimal cashOt;//现金退款
	private BigDecimal cashOutlayTotal;//总现金支出
	private BigDecimal bonusOutlayTotal;//总奖励积分支出
	private BigDecimal bt;//奖励积分转购物积分
	private BigDecimal ot;//订单退款
	private BigDecimal shoppingOutlayTotal;//总购物积分支出
	private BigDecimal exchangeOutlayTotal;//总换购积分支出
	private BigDecimal cashBalance;//现金结余
	private BigDecimal bonusBalance;//奖励积分结余
	private BigDecimal shoppingBalance;//购物积分结余
	private BigDecimal exchangeBalance;//换购积分结余


}
