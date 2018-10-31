package cn.leanshi.mapper;

import cn.leanshi.model.Member_basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员基本信息
 * @author :ldq
 * @date:2018/7/30
 * @description:leanshi cn.leanshi.mapper
 */
public interface MemberMapper {
	List<Member_basic> selectBySearchMap(Map map);

	Member_basic selectByMCode(String mCode);

	int updateByMCodeAndMName(Member_basic member);

	int updateNameByMCode(Member_basic memberBasic);

	List<Member_basic> findMemByMNickName(Map<String,Object> map);

	List<Member_basic> findMemByMobile(Map<String,Object> map);

	int updateMobAndNickByMCode(Member_basic memberBasic);

	List<Member_basic> findMemAll();

    String findMemberNickNameByMcode(String mCode);

    int selectNewMember(String periodCode);

	/**
	 * 根据会员昵称和会员编号查询会员信息
	 * @param map
	 * @return
	 */
    List<Member_basic> searchMemberByMcodeAndNickName(HashMap<String,Object> map);

	/**
	 * 根据昵称查询会员编号
	 * @param mNickName
	 * @return
	 */
    String findMemberMcodeByNickName(String mNickName);

	/**
	 * 根据会员昵称和会员编号查询会员信息
	 * @param map
	 * @return
	 */
	Member_basic findMemberBasicByMcodeAndNickName(HashMap<String,Object> map);

	/**
	 * 根据会员昵称查询会员基础信息
	 * @param mNickName
	 * @return
	 */
	Member_basic findByNickName(String mNickName);

	/**
	 * 根据证件类型和证件号码查询会员信息
	 * @param map
	 * @return
	 */
	Member_basic findMemberBasicByIdTypeAndIdCode(HashMap<String,Object> map);

	/**
	 * 根据手机哈嘛查询会员信息
	 * @param mobile
	 * @return
	 */
	Member_basic findMemberBasicByMobile(String mobile);

	/**
	 * 查找最新注册用户的会员编号
	 * @return
	 */
    String findLastMemberMcode();

	/**
	 * 添加用户
	 * @param memberBasic
	 */
	int addMember(Member_basic memberBasic);
}
