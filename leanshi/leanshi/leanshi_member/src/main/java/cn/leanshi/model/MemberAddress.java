package cn.leanshi.model;

import lombok.Data;

/**
 * 会员收货地址
 * @author :ldq
 * @date:2018/9/8
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class MemberAddress {
	private int aId; //id
	private String mCode;//会员编号
	private String consigneeName;//收货人姓名
	private String mobile;//手机
	private String phone;//电话
	private Integer defaultAdd;//默认地址  1 默认  0 非默认
	private int valid;//有效状态 0无效 1有效
	private String addProvinceCode;//省
	private String addCityCode;//市
	private String addCountryCode;//区县
	private String addTownCode;//街镇
	private String addDetial;//详细地址
	private String addPost;//邮编

}
