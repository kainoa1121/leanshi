package cn.leanshi.mapper;

import cn.leanshi.model.MemberQualification;

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
