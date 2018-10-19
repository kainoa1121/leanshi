package cn.leanshi.mapper;

import cn.leanshi.model.MemberQualification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :ldq
 * @date:2018/8/9
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface MemberQualificationMapper {

	List<MemberQualification> findQualificationByPeriod(String periodCode);

	MemberQualification findQualificationByPeriodAndMCode(Map<String, Object> map);

	List<MemberQualification> findQualificationMCodeByPeriod(String periodCode);

	int addMqlf(MemberQualification mqlf);


	/**
	 * @author zc
	 * @param periodCode
	 * @return
	 */
	int findMaxLayerByPeriodCode(String periodCode);

	/**
	 * @author zc
	 * @param map
	 * @return
	 */
	List<MemberQualification> findByLayer(Map<String, Object> map);

	/**
	 * @author zc 根据周期查询出当前周期内新晋升成为vip的顾客
	 * @param periodCode
	 * @return
	 */
    List<MemberQualification> findNewVip(String periodCode);

	/**
	 * @author zc 根据周期查询该周期内总pv
	 * @param periodCode
	 * @return
	 */
    int findSumPv(String periodCode);

	/**
	 * @author zc 根据周期以及所处层次，查询该期符合获得领导奖的客户MemberQualification集合
	 * @param map
	 * @return
	 */
	List<MemberQualification> findRankGE7(HashMap<String,Object> map);

	int delQulfByPeriod(String periodCode);

	int updateQualifi(Map<String, Object> map);

	int updateQualifiLeafYn(Map<String, Object> map);

	int findLayerMax(String periodCode);

	List<MemberQualification> findQualifiByLayer(Map<String, Object> map);

	int updateQlfNPV(Map<String, Object> map);

	int updateQlfG7PV(Map<String, Object> map);

	int updateQulfGPV(Map<String, Object> map);

	int updateQulfRank(Map<String, Object> map);

	int findQulfCountRankBySponsorCode(Map<String, Object> map);

	List<MemberQualification> findQulfGroup(Map<String, Object> map);

	int updateQulfGroup(Map<String, Object> map);

	int updateQulfD(Map<String, Object> map);

	int updateQulfRankHight(Map<String, Object> map);

	int delQulfPV(MemberQualification qualification);

}
