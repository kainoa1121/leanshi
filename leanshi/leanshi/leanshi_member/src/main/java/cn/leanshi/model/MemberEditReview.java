package cn.leanshi.model;

import lombok.Data;

import java.util.Date;

/**
 * 会员修改记录信息
 * @author :ldq
 * @date:2018/8/7
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class MemberEditReview {
	private Integer rId;
	private String mCode;//会员编号
	private String mNameBefore;//原会员姓名
	private String mName;//新会员姓名
	private int idTypeBefore;//原证件类型 1.身份证2.护照3.军官证4.回乡证
	private int idType;//新证件类型 1.身份证2.护照3.军官证4.回乡证
	private String idCodeBefore;//原证件号码
	private String idCode;//新证件号码
	private String mNicknameBefore;//原会员昵称
	private String mNickname;//新会员昵称
	private int genderBefore;//原性别0.男 1.女
	private int gender;//新性别0.男 1.女
	private String mobileBefore;//原手机
	private String mobile;//新手机
	private String weChatBefore;//原微信号
	private String weChat;//新微信号
	private String emailBefore;//原电子邮箱
	private String email;//新电子邮箱
	private String addPostBefore;//原邮编
	private String addPost;//新邮编
	private String provinceBefore;//原省
	private String province;//新省
	private String cityBefore;//原市
	private String city;//新市
	private String countryBefore;//原区县
	private String country;//新区县
	private String detialBefore;//原详细地址
	private String detial;//新详细地址
	private String bankdetailBefore;//原银行详细信息
	private String bankdetail;//新银行详细信息
	private String accNameBefore;//'原开户名'
	private String accName;//'新开户名'
	private String accCodeBefore;//'原账户号码'
	private String accCode;//'新账户号码'
	private String withdrawDefaultBefore;//'原默认收款方式'
	private String withdrawDefault;//'新默认收款方式'
	private String sponsorCodeBefore;//'原推荐人编号'
	private String sponsorCode;//'新推荐人编号'
	private String sponsorNameBefore;//'原推荐人姓名'
	private String sponsorName;//'新推荐人姓名'
	private int rankBefore;//'原会员级别'
	private int rank;//'新会员级别'
	private String raCodeBefore;//'原老会员账号'
	private String raCode;//'新老会员账号'
	private String raNameBefore;//'原老会员姓名'
	private String raName;//'新老会员姓名'
	private int raIdTypeBefore;//老系统身份证类型
	private int raIdType;//新老系统身份证类型
	private String raIdCodeBefore;//老系统身份证号码
	private String raIdCode;//新老系统身份证号码
	private String updateBy;//'修改人'
	private String updateMemo;//'修改备注'
	private Integer updateType;//'修改类型 0.修改基本信息 1.修改敏感信息 2.会员更名 3.更改推荐人 4.更改会员级别 5.与老会员绑定'
	private Date updateTime;//'修改时间'
	private String reviewMemo;//'审核备注'
	private Integer reviewStatus;//'审核状态 0:待审 1:驳回 2：审核通过 3：无需审核'


}
