package cn.leanshi.mapper;

import cn.leanshi.model.RdBonusAudit;

import java.util.List;

/**
 * autor zc
 * 奖金审核
 */
public interface RdBonusAuditMapper {
    List<RdBonusAudit> findAuditByPeriodCode(String periodCode);

    void insertRdBonusAudit(RdBonusAudit rdBonusAuditNew);

    List<RdBonusAudit> findAuditByPeriodCodeAndStatus(String periodCode);

    List<RdBonusAudit> findAuditPassed(String periodCode);

    RdBonusAudit findAuditByPeriodCodeAndStatysAndLastTime(String periodCode);
}
