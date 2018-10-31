package cn.leanshi.service;

import cn.leanshi.model.MemberAccount;
import cn.leanshi.model.MemberAddress;
import cn.leanshi.model.MemberBank;
import cn.leanshi.model.MemberEditReview;
import cn.leanshi.model.MemberIntegralRule;
import cn.leanshi.model.MemberQualification;
import cn.leanshi.model.MemberRelation;
import cn.leanshi.model.Member_basic;
import cn.leanshi.model.Rank;
import cn.leanshi.model.RdBonusMaster;
import cn.leanshi.model.RdMemberAccountLog;
import cn.leanshi.model.RdRaBinding;
import cn.leanshi.model.RdReceivableDetail;
import cn.leanshi.model.RdReceivableMaster;
import cn.leanshi.model.RdStatusDetail;
import cn.leanshi.model.SysPeriod;
import cn.leanshi.model.SysPeriodLog;

import java.math.BigDecimal;
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

	List<MemberRelation> findRelationBySponsorCode(String mCode);

	int updateQualifi(String periodCode, String code, int layerNext);

	int updateQualifiLeafYn(String mCode, String periodCode, int leafYn);

	int findLayerMax(String periodCode);

	List<MemberQualification> findQualifiByLayer(String periodCode, int i);

	int updateQlfNPV(String periodCode, String mCode, int npv);

	int updateQlfG7PV(String periodCode, String mCode, int g7pv);

	int updateQulfGPV(String periodCode, String mCode, int npv);

	int updateQulfRank(String periodCode, String mCode, int rank);

	int findRltCountRank2BySponsorCode(String mCode);

	int findQulfCountRankBySponsorCode(String periodCode,String mCode, int i);

	List<MemberQualification> findQulfGroup(String periodCode, String mCode);

	int updateQulfGroup(String periodCode, String mCode, int groupRankMax);

	int updateQulfD(String periodCode, String mCode, int count2, int count6, int count7, int count8);

	int updateQulfRankHight(String periodCode, String mCode, int rankRecordHigh);

	List<RdBonusMaster> findMasterByPeriod(String periodCode);

	int delQulfPV(MemberQualification qualification);

	List<RdReceivableMaster> findReceivableAll(String mCode, String mNickname, int status);

	int defBankByOid(Integer oId, String mCode, Integer defaultBank);

    Member_basic findMemberBasicByMcodeAndNickName(String mCode, String mNikcName);

    Member_basic findByNickName(String mNickName);

	Member_basic findMemberBasicByIdTypeAndIdCode(int idType, String idCode);

	Member_basic findMemberBasicByMobile(String mobile);

	List<RdReceivableDetail> findReceivableDetailAll(String mCode, String mNickname, Integer transNumber, String trTypeCode, int status, Date timeStar, Date timeEnd);

	int updateRD(String mCode, int transNumber, int status);

	RdReceivableDetail findRD(String mCode, int transNumber);

	RdReceivableMaster findReceivableByMCode(String mCode);

	int addReceivableM(RdReceivableMaster master);

	int updateRM(String mCode, BigDecimal blanceAfter, int bnsDeductPecent);

	MemberBank findMBankByMCodeAndDefual(String mCode);

	int addRDNR(RdReceivableDetail detail);

	int addRDRR(RdReceivableDetail detail);

	int updateRelaStatus(String mCode, int statusAfter);

	int updateAccountStatus(String mCode, int statusAfter);

	int addStatusD(String mCode, String mNickname, String statusType, int statusBefore, int statusAfter, String updateDesc);

	List<RdStatusDetail> findStatusDetailAll(String statusType, Date timeStar, Date timeEnd, String mCode, String mNickname);

	List<RdMemberAccountLog> findRdAccLog1(Date timeStar, Date timeEnd, String mCode, String mNickname, Integer transNumber, Integer batchNumber, String typeS);

	List<RdMemberAccountLog> findRdAccLog2(Date timeStar, Date timeEnd, String mCode, String mNickname, Integer transNumber, Integer batchNumber, String transTypeCode, String typeS);

	MemberIntegralRule findRule();

	List<RdMemberAccountLog> findAccountLogWD();

	MemberBank findMBankByOId(Integer oId);

	int updateAccLogWDOne(String mCode, Integer transNumber, int status);

	List<RdMemberAccountLog> findAccountLogWDALL(Integer transNumber, Date timeStar, Date timeEnd, String mCode, String mNickname, int status);

	int updateRule(MemberIntegralRule memberIntegralRule);


	String findLastMemberMcode();

	int addMember(Member_basic memberBasic);

	void addMemberRelation(MemberRelation memberRelation);

	void addMemAccount(MemberAccount memberAccount);

	void addMemBank(MemberBank memberBank);

	int updateAccLogWDAll(String transNumber);

}
