package cn.leanshi.mapper;

import cn.leanshi.model.MemberAccount;

import java.util.Map;

/**
 * @author :ldq
 * @date:2018/9/10
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface MemberAccountMapper {
	MemberAccount findMemAccountByMCode(String mCode);

	int updateAccountStatus(Map<String, Object> map);

    void addMemAccount(MemberAccount memberAccount);
}
