package cn.leanshi.mapper;

import cn.leanshi.model.SysPeriodLog;

import java.util.List;
import java.util.Map;

/**
 * @author :ldq
 * @date:2018/9/20
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface SysPeriodLogMapper {
	int addPeriodLog(SysPeriodLog sysPeriodLog);

	List<SysPeriodLog> findPeriodLogAll(Map<String,Object> map);
}
