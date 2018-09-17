package cn.leanshi.model;

import lombok.Data;

import java.util.Date;

/**
 * 会员基本信息
 * @author :ldq
 * @date:2018/8/1
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class Member_basic {

	private int mId;
	private String mCode;//会员编号
	private String mName;//会员姓名
	private String mNickname;//会员昵称
	private int idType;//证件类型 1.身份证2.护照3.军官证4.回乡证
	private String idCode;//证件号码
	private int gender;//性别0.男 1.女
	private Date birthdate;//生日
	private String email;//电子邮件
	private String nationId;//国家地区:国际标准简写
	private String province;//省
	private String city;//市
	private String country;//区县
	private String town;//街镇
	private String detial;//详细地址
	private String addPost;//邮编
	private String mobile;//手机
	private String weChat;//微信号
	private String qq;//QQ号
	private String facebook;//faceboot号
	private String phone;//电话
	private String creationSource;//录入来源
	private String creationIp;//来源IP地址
	private String creationData;//录入时间
	private String creationer;//录入人
	private String creationPeriod;//录入业务周期:YYYYMM
	private String updateSource;//最后更新来源
	private String updateIp;//更新来源IP地址
	private String updateDate;//最后更新日期
	private String updateBy;//最后更新人
	private String updatePeriod;//最后更新业务周期:YYYYMM

}
