package cn.leanshi.model;

import lombok.Data;

/**
 * 会员资格表
 * @author :ldq
 * @date:2018/8/9
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class MemberQualification {

	private String periodCode;//业务月份（YYYYMM），每一个业务月保留完整的会员关系、级别、业绩等数据
	private String mCode;//会员编号
	private String mName;//会员姓名
	private String sponsorCode;//推荐人编号
	private String sponsorName;//推荐人姓名
	private int mStatus;//当期会员的状态
	private int raStatus;//关联公司绑定状态 0:未绑定 1：已绑定
	private int raShopYn;//老系统会员开店状态 0：未开店 1：已开店
	private int ppv;//当期个人购买的PV
	private int ppvRetail;//当期个人零售的PV，包括分享给普通顾客购买的PV
	private int appvInit;//期初个人累计PV
	private int appvFinal;//期末个人累计PV
	private int retailInit;//个人零售购买的期初值
	private int retail;//个人零售购买额
	private int retailFinal;//个人零售购买的期末值
	private int rankInit;//期初个人级别
	private int rank;//当期计算后个人级别
	private int rankRecordHigh;//历史最高级别
	private int leafYn;//是否叶子节点\r\n0：不是\r\n1：是叶子节点
	private Integer orphan;//是否孤儿 0：是  1：不是
	private int layer;//所处层次，公司节点为0层，以下每层+1
	private int g7pv;//当期团队7层的PV
	private int npv;//当期全网络的PV
	private int gpvFlagship;//达到旗舰店(7级)以上级别的人员，其同级以下团队的总业绩
	private int groupRankMax;//团队最高级别（包括本人）
	private int ddRank2Number;//直接推荐代理的人数，代理级别为2
	private int dlRank6Number;//下属3级代理店网络数
	private int dlRank7Number;//下属旗舰店网络数
	private int dlRank8Number;//下属高级旗舰店网络数
	private int ppvqualified;//当月个人消费是否合格\r\n0：不合格\r\n1：合格
	private int tourismQualified;//当月旅游奖是否合格\r\n0：不合格\r\n1：合格
	private int carQualified;//当月车奖是否合格\r\n0：不合格\r\n1：合格
	private int dividendQualified;//当月分红奖是否合格\r\n0：不合格\r\n1：合格

}
