package cn.leanshi.service;

import cn.leanshi.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface BonusService {
    List<RdBonusMaster> searchByPeriodCode(String periodCode);

    List<RdBonusPayment> searchBonusPaymentByCodeAndName(String mCode, String mNickName);

    List<RdBonusPayment> searchBonusPayment(String periodCodeLeft, String periodCodeRight, String mCode, String mName, String payStatus);


    int findMaxLayerByPeriodCode(String periodCode);

    List<MemberQualification> checkPeriodBe(String periodCode);

    List<MemberQualification> findMaxLayerList(int maxLayer,String periodCode);

    MemberQualification findBySponsorCode(String sponsorCode,String periodCode);

    RdBonusMaster searchBonusMasterByMCode(String mCode,String periodCode);

    void insertRdBonusMaster(RdBonusMaster rdBonusMaster1);

    void updateBonusDevp1(BigDecimal add, String mCode, String periodCode);

    void deleteByPeriodCode(String periodCode);

    void updateBonusDevp2(BigDecimal add, String mCode, String periodCode);

    List<MemberQualification> findByLayer(int num, String periodCode);

    void insertRdBonusMaster(MemberQualification memberQualification);

    void updateBonusDevpShare(BigDecimal add, String mCode, String periodCode);

    List<MemberQualification> findNewVip(String periodCode);

    void updateBonusNewVip(BigDecimal add, String mCode, String periodCode);

    List<RdBonusMaster> selectAll(String periodCode);

    void updateBonusSum(BigDecimal bounsDevpShare, BigDecimal bonusDevpDum, BigDecimal bonusLdSum, BigDecimal bonusSum, RdBonusMaster rdBonusMaster);

    int findSumPv(String periodCode);

    int findSumShare(String periodCode);

    List<MemberQualification> findRankGE7(int num, String periodCode);

    void updateBonusLDDirect(BigDecimal i, String periodCode, String mCode);

    void updateBonusLDIndirect(BigDecimal bonusLDIndirect, String periodCode, String mCode);

    void updateBonusLDSupport(BigDecimal i, String periodCode, String mCode);

    /*RdBonusAudit findAuditByPeriodCode(String periodCode);*/

    int findCustomerNum(String periodCode);

    BigDecimal findPerformance(String periodCode);

    BigDecimal findBonusNewVip(String periodCode);

    BigDecimal findBonusRetail(String periodCode);

    BigDecimal findBonusDevp(String periodCode);

    BigDecimal findBonusLd(String periodCode);

    void insertRdBonusAudit(RdBonusAudit rdBonusAuditNew);

    List<RdBonusAudit> findAuditByPeriodCodeAndStatus(String periodCode);

    List<RdBonusAudit> findBonusAuditAll(String periodCode);

    List<RdBonusAudit> findAuditPassed(String periodCode);

    RdReceivableMaster findRdReceivableMaster(String mCode);

    RdReceivableDetail findRdReceivableDetailLast(String mCode);

    void insertRdReceivableDetail(RdReceivableDetail rdReceivableDetail1);

    void updateCalStatusByPeriodCode(String periodCode);

    String findMemberNickNameByMcode(String mCode);

    void insertRdBonusPayment(RdBonusPayment rdBonusPayment);

    int selectNewMember(String periodCode);

    int findCountPpvGtZero(String periodCode);

    BigDecimal findSumBouns(String periodCode);

    BigDecimal findSumBounsReissue(String periodCode);

    BigDecimal findSumChargeSum(String periodCode);

    BigDecimal findSumPayableSum(String periodCode);

    void insertRdBonusPaymentStatistical(RdBonusPaymentStatistical rdBonusPaymentStatistical);

    List<RdBonusPayment> findBounsPaymentAll(String periodCode);

    BigDecimal findSumBonusNewVip(String periodCode);

    RdBonusAudit findAuditByPeriodCodeAndStatysAndLastTime(String periodCode);

    List<RdBonusPaymentStatistical> findRdBonusPaymentStatisticalByLR(String periodCodeLeft, String periodCodeRight);

    List<Member_basic> searchMemberByMcodeAndNickName(String mCode, String mNickName);

    List<RdBonusMaster> searchBonusMasterByCerteria(String periodCodeLeft, String periodCodeRight, String mCode);

    String findMemberMcodeByNickName(String mNickName);
}
