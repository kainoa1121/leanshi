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
}
