package cn.leanshi.model;

import lombok.Data;

import java.util.Date;

/**
 * 会员老系统绑定信息
 * @author :ldq
 * @date:2018/8/9
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class RdRaBinding {

	private String raCode;//老系统会员编号
	private String raName;//老系统会员姓名
	private String raNickName;//老系统会员昵称
	private int raStatus;//老系统会员状态:1：正常2：冻结 -1：注销
	private int raIdType;//老系统身份证类型
	private String raIdCode;//老系统身份证号码
	private String raSponsorCode;//老系统推荐人编号
	private String raSponsorName;//老系统推荐人姓名
	private int bindingStatus;//绑定状态: 0：未绑定 1：已绑定
	private String rdCode;//绑定的RD会员号
	private Date bindingDate;//绑定时间
	private String bindingBy;//绑定操作人
}
