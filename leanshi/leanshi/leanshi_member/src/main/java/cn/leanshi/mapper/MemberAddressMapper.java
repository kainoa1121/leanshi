package cn.leanshi.mapper;

import cn.leanshi.model.MemberAddress;

import java.util.List;
import java.util.Map;

/**
 * @author :ldq
 * @date:2018/9/8
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface MemberAddressMapper {
	List<MemberAddress> findAddByMCode(String mCode);

	int updateAddDefaultByMCode(String mCode);

	int selectCount(String mCode);

	int addMemAdd(MemberAddress memberAddress);

	MemberAddress findMemAddByMCode(Map<String, Object> map);

	int editMemAdd(MemberAddress memberAddress);

	int delMemAddByAId(Map<String, Object> map);

	int defAddByAIdAndMCode(Map<String, Object> map);

	int outAllAddByMCode(String mCode);
}
