package cn.leanshi.mapper;

import cn.leanshi.model.MemberAccount;

/**
 * @author :ldq
 * @date:2018/9/10
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface MemberAccountMapper {
	MemberAccount findMemAccountByMCode(String mCode);
}
