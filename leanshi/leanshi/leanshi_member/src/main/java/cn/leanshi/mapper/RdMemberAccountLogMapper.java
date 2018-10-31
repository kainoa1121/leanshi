package cn.leanshi.mapper;

import cn.leanshi.model.RdMemberAccountLog;

import java.util.List;
import java.util.Map;

/**
 * @author :ldq
 * @date:2018/10/24
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface RdMemberAccountLogMapper {
	List<RdMemberAccountLog> findRdAccLogBOP(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLogSHP(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLogPUI(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLog2(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLogBOPINTO(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLogSHPINTO(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLogPUIINTO(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLogBOPOUT(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLogSHPOUT(Map<String, Object> map);

	List<RdMemberAccountLog> findRdAccLogPUIOUT(Map<String, Object> map);

	List<RdMemberAccountLog> findAccountLogWD();

	int updateAccLogWDOne(Map<String, Object> map);

	List<RdMemberAccountLog> findAccountLogWDALL(Map<String, Object> map);

	int updateAccLogWDAll(String transNumber);
}
