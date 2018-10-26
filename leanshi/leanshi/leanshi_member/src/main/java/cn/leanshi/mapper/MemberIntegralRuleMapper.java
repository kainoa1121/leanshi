package cn.leanshi.mapper;

import cn.leanshi.model.MemberIntegralRule;

/**
 * @author :ldq
 * @date:2018/10/25
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface MemberIntegralRuleMapper {
	MemberIntegralRule findRule();

	int updateRule(MemberIntegralRule memberIntegralRule);
}
