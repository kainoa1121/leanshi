package cn.leanshi.service;

import cn.leanshi.model.MemberAccount;
import cn.leanshi.model.MemberAddress;
import cn.leanshi.model.MemberBank;
import cn.leanshi.model.MemberEditReview;
import cn.leanshi.model.MemberQualification;
import cn.leanshi.model.MemberRelation;
import cn.leanshi.model.Member_basic;
import cn.leanshi.model.Rank;
import cn.leanshi.model.RdRaBinding;
import cn.leanshi.model.SysPeriod;
import cn.leanshi.model.SysPeriodLog;

import java.util.Date;
import java.util.List;

/**
 * @author :ldq
 * @date:2018/7/30
 * @description:leanshi cn.leanshi.service
 */
public interface MemberService {
	List<Member_basic> search(String mCode,String mName,String mobile,String mNickname);

	Member_basic findByMCode(String mCode);

	int updateByMCodeAndMName(Member_basic member,String mDesc);

	List<MemberBank> findMBankByMCode(String mCode);

	int updateByMBank(String mCode,String mName,String mobile,String mNickname,String withdrawDefault,String mDesc);

	int updateIdByMCodeAndMName(String mCode,String mName,String newMName,Integer idType,String idCode,String mNickname,String mobile,String uploadPath,String mDesc);

	MemberRelation findRelationByMCode(String mCode);

	int updateRelationByMCode(String mCode, String mName, int rank, int rankNew, String mDesc);

	RdRaBinding findBindingByMCode(String mCode);

	int updateBindingByMCode(String mCode, String mName, String raCode, String raName, String raCodeNew, String raNameNew, Integer raIdType, String raIdCode, String mDesc);

	List<MemberEditReview> findEditAll(String mCode, String mName, Integer updateType, Integer reviewStatus, Date timeStar,Date timeEnd);

	MemberEditReview findEditById(Integer rId);

	int updateEditById(Integer rId,Integer reviewStatus,String reviewMemo);

	List<MemberEditReview> findEditStatus(String mCode, String mName, Date timeStar,Date timeEnd);

	SysPeriod findPeriod(String date);

	List<MemberQualification> findQualificationByPeriod(String periodCode);

	MemberQualification findQualificationByPeriodAndMCode(String statusPeriod, String mCode);

	List<Rank> fingRankAll();

	int addPeriod(String periodCode,String prePeriod, Date beginDate, Date endDate);

	List<MemberAddress> findAddByMCode(String mCode);

	int addMemAdd(MemberAddress memberAddress);

	int delBankByOid(Integer oId,String mCode);

	MemberAddress findMemAddByMCode(String mCode, Integer aId);

	int editMemAdd(MemberAddress memberAddress);

	int delMemAddByAId(Integer aId, String mCode);

	int defAddByAIdAndMCode(Integer aId, String mCode, Integer defaultAdd);

	MemberAccount findMemAccountByMCode(String mCode);

	boolean outAllBankByMCode(String mCode);

	List<MemberAddress> findAddAllByMCode(String mCode);

	boolean outAllAddByMCode(String mCode);

	int updateRelaSponByMCode(String mCode, String mName, String sponsorCode, String sponsorName, String sponsorCodeNew, String sponsorNameNew, String mDesc);

	int addBankByMCode(MemberBank bank);

	int addBinding(String mCode, String raCode, String raName, Integer raIdType, String raIdCode, String raSponsorName,String raSponsorCode, String raSponsorIdCode);

	List<Member_basic> findMemByMNickName(String mCode,String mNickname);

	List<Member_basic> findMemByMobile(String mCode,String mobile);

	List<MemberEditReview> findEditByMCode(String mCode, int updateType);

	String findRank(int rank);

	int findRankByRankName(String rankName);

	SysPeriod findPrePeriod(String periodCode);

	int editPeriodStatu(String periodCode,int salesStatus, int calStatus, int bonusStatus);

	int addPeriodLog(String periodCode, String actionCode, int valBefoer, String valBefoerDesc, int valAfter, String valAfterDesc, String updateMemo);

	int ClosePeriodSales(String periodCode);

	List<SysPeriod> findPeriodAll();

	int editPeriod(String periodCode, Date endDate);

	int delPeriod(String periodCode);

	List<SysPeriodLog> findPeriodLogAll(String periodCode);

	int updatePeriod(String periodCode, Date beginDate, Date endDate);

	void updatePeriodAddNext(String prePeriod, String periodCode);

	List<MemberQualification> findQualificationMCodeByPeriod(String periodCode);

	List<Member_basic> findMemAll();

	int addMqlf(MemberQualification mqlf);

	int delQulfByPeriod(String periodCode);
}
