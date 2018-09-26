package cn.leanshi.mapper;

import cn.leanshi.model.SysPeriod;

import java.util.List;
import java.util.Map;

/**
 * @author :ldq
 * @date:2018/8/28
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface SysPeriodMapper {
	SysPeriod findPeriodByDate(String periodCode);

	int addPeriod(SysPeriod period);

	SysPeriod findPrePeriod(String nextPeriod);

	int editPeriodStatu(Map<String, Object> map);

	int ClosePeriodSales(Map<String, Object> map);

	List<SysPeriod> findPeriodAll();

	int editPeriod(Map<String, Object> map);
}
