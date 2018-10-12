package cn.leanshi.model;

import lombok.Data;

/**
 * 会员状态、级别及关系
 * @author :ldq
 * @date:2018/8/9
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class MemberRelation {

	private String mCode; //会员编号
	private int rank; //会员级别:参考级别体系
	private int rankNow; //当前计算后级别
	private int rankBefore; //上一周期级别
	private int rankBeforeDouble; //上上一周期级别
	private String rankPeriod; //升级业务期:YYYYMM
	private int reTail;//累计按零售价购买额:用于判断是否可 以升VIP会员
	private String loginPwd; //登录密码:加密保存
	private int pwdInitYn; //是否初始密码:0，登录时需修改密码 1，不需修改
	private int mStatus; //会员状态:0正常1冻结2注销
	private String statusPeriod; //状态变更业务期：YYYYMM
	private String sponsorCode; //推荐人编号
	private String sponsorName; //推荐人姓名
	private String placementCode; //放置人编号
	private int raSponsorStatus; //旧系统转来推荐人:0：临时状态 1：永久状态
	private int raStatus; //关联公司绑定状态：\r\n0:未绑定\r\n1：已绑定
	private int raShopYn; //老系统会员开店状态0：未开店1：已开店
	private String raCode; //关联公司会员号
	private String raNickName; //关联公司昵称
	private String raBindingDate; //绑定日期
}
