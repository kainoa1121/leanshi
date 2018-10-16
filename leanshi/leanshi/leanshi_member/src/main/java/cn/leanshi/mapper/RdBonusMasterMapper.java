package cn.leanshi.mapper;

import cn.leanshi.model.RdBonusMaster;

import java.util.List;

/**
 * @author :ldq
 * @date:2018/10/15
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface RdBonusMasterMapper {
	List<RdBonusMaster> findMasterByPeriod(String periodCode);
}
