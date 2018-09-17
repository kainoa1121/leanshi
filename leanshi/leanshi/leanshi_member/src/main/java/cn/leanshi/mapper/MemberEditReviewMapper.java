package cn.leanshi.mapper;

import cn.leanshi.model.MemberEditReview;

import java.util.List;
import java.util.Map;

/**
 * 会员修改审核mapper
 */
public interface MemberEditReviewMapper {

	MemberEditReview selectLastByMCode(String mCode);

	int saveEdit(MemberEditReview editReview);

	List<MemberEditReview> findEditAll(Map<String, Object> map);

	MemberEditReview findEditById(Integer rId);

	int updateEditById(Map<String, Object> map);

	List<MemberEditReview> findEditStatus(Map<String, Object> map);

	List<MemberEditReview> findEditByMCode(Map<String, Object> map);
}
