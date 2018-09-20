package cn.leanshi.model;

import lombok.Data;

import java.util.Date;

/**
 * 业务周期变更日志
 * @author :ldq
 * @date:2018/8/2
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class SysPeriodLog {

	private int lId; //'动作序号（自动生成)'
	private String periodCode;//'业务周期'
	private String actionCode;//'影响字段 SALES_STATUS BONUS_STATUS SALARY_STATUS'
	private int valBefoer;//修改前值'
	private String valBefoerDesc;//'修改前值描述'
	private int valAfter;//'修改后值'
	private String valAfterDesc;//'修改后值描述'
	private Date updateTime;//'修改时间'
	private String updateBy;//'修改人'
	private String updateMemo;//'修改备注（包括修改原因、特殊说明、可能的影响）'
}
