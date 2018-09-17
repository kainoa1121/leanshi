package cn.leanshi.mapper;

import cn.leanshi.model.Member_basic;

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
}
