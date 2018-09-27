package cn.leanshi.mapper;

import cn.leanshi.model.SysPeriodLog;

import java.util.List;

/**
 * @author :ldq
 * @date:2018/9/20
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface SysPeriodLogMapper {
	int addPeriodLog(SysPeriodLog sysPeriodLog);

	List<SysPeriodLog> findPeriodLogAll(String periodCode);
}
