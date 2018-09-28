package cn.leanshi.model;

import lombok.Data;

import java.util.Date;

/**
 * 业务周期
 * @author :ldq
 * @date:2018/8/28
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class SysPeriod {

	private String periodCode;//'业务周期'
	private String prePeriod;//'前一业绩期'
	private String nextPeriod;//'下一业绩期'
	private int salesStatus;//'业绩状态 0：未开始 1：已开始 2：外部关闭补录中 3：已关闭'
	private int calStatus;//'奖金状态 0：未开始 1：计算中 2：临时发布核对中 3：正式发布'
	private Date beginDate;//'业绩开始日期'an w
	private Date endDate;//'业绩结束日期 （两业绩期间不允许重叠，可以有空档）'
	private int calTimes;//'计算次数'
	private int bonusStatus;//'发放状态 0：未发出 1：已发出'

}
