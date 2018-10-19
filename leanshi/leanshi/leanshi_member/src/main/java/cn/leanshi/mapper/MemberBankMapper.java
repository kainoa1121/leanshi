package cn.leanshi.mapper;

import cn.leanshi.model.MemberBank;

import java.util.List;
import java.util.Map;

/**
 * @author :ldq
 * @date:2018/8/2
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface MemberBankMapper {

	List<MemberBank> findMBankByMCode(String mCode);

	int updateByMBank(Map<String,Object> map);

	int delBankByOid(Map<String,Object> map);

	int outAllBankByMCode(String mCode);

	int countMBankByMCode(String mCode);

	int addBankByMCode(MemberBank bank);

	int updateBankDefaultByMCode(String mCode);

	int selectCount(String mCode);

	int defBankByOid(Map<String, Object> map);
}
