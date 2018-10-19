package cn.leanshi.service;

import cn.leanshi.model.MemberQualification;
import cn.leanshi.model.RdBonusMaster;
import cn.leanshi.model.RdBonusPayment;

import java.math.BigDecimal;
import java.util.List;

public interface BonusService {
    List<RdBonusMaster> searchByPeriodCode(String periodCode);

    List<RdBonusPayment> searchBonusPaymentByCodeAndName(String mCode, String mName);

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
}
