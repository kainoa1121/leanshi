package cn.leanshi.mapper;

import cn.leanshi.model.MemberRelation;

/**
 * 会员状态、级别及关系
 * @author :ldq
 * @date:2018/8/9
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface MemberRelationMapper {
	MemberRelation findRelationByMCode(String mCode);

	int updateRankByMCode(MemberRelation relation);

	int updateRelaSponNameByMCode(String mCode);

	int updateSponsorByMCode(MemberRelation relation);
}
