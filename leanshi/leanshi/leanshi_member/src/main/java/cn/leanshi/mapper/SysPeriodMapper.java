package cn.leanshi.mapper;

import cn.leanshi.model.SysPeriod;

/**
 * @author :ldq
 * @date:2018/8/28
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface SysPeriodMapper {
	SysPeriod findPeriodByDate(String periodCode);

	int addPeriod(SysPeriod period);

	SysPeriod findPrePeriod(String nextPeriod);
}
