package cn.leanshi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RdBonusAudit implements Serializable {

    private String periodCode;//周期编号
    private int customerNum;//当期总人数
    private int orderNum;//当期总订单数
    private BigDecimal performance;//当期总业绩
    private BigDecimal bonusNewVip;//vip辅导奖
    private BigDecimal bonusRetail;//零售奖

    private BigDecimal  bonusDevp;//市场拓展奖
    private BigDecimal bonusDevpPercentage;//拓展奖占比
    private BigDecimal  bonusLd;//领导奖
    private BigDecimal bonusLdPercentage;//领导奖占比

    private BigDecimal bonusSpecial;//特别奖
    private BigDecimal bonusSpecialPercentage;//特别奖占比

    private BigDecimal bounsSumPv;//总奖金(pv)
    private BigDecimal allocatePercentage;//总奖金拨出率
    private String currencyCode;//币种
    private BigDecimal bounsSumCurrency;//总奖金货币
    private String reviewer;//审核人
    private Integer staus;//审核状态  0 ：未审核   1：审核通过  -1：审核失败   默认为未审核
    private Date auditTime;//审核时间  默认为null，修改审核状态时候记录当前系统时间

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getPerformance() {
        return performance;
    }

    public void setPerformance(BigDecimal performance) {
        this.performance = performance;
    }

    public BigDecimal getBonusNewVip() {
        return bonusNewVip;
    }

    public void setBonusNewVip(BigDecimal bonusNewVip) {
        this.bonusNewVip = bonusNewVip;
    }

    public BigDecimal getBonusRetail() {
        return bonusRetail;
    }

    public void setBonusRetail(BigDecimal bonusRetail) {
        this.bonusRetail = bonusRetail;
    }

    public BigDecimal getBonusDevp() {
        return bonusDevp;
    }

    public void setBonusDevp(BigDecimal bonusDevp) {
        this.bonusDevp = bonusDevp;
    }

    public BigDecimal getBonusDevpPercentage() {
        return bonusDevpPercentage;
    }

    public void setBonusDevpPercentage(BigDecimal bonusDevpPercentage) {
        this.bonusDevpPercentage = bonusDevpPercentage;
    }

    public BigDecimal getBonusLd() {
        return bonusLd;
    }

    public void setBonusLd(BigDecimal bonusLd) {
        this.bonusLd = bonusLd;
    }

    public BigDecimal getBonusLdPercentage() {
        return bonusLdPercentage;
    }

    public void setBonusLdPercentage(BigDecimal bonusLdPercentage) {
        this.bonusLdPercentage = bonusLdPercentage;
    }

    public BigDecimal getBonusSpecial() {
        return bonusSpecial;
    }

    public void setBonusSpecial(BigDecimal bonusSpecial) {
        this.bonusSpecial = bonusSpecial;
    }

    public BigDecimal getBonusSpecialPercentage() {
        return bonusSpecialPercentage;
    }

    public void setBonusSpecialPercentage(BigDecimal bonusSpecialPercentage) {
        this.bonusSpecialPercentage = bonusSpecialPercentage;
    }

    public BigDecimal getBounsSumPv() {
        return bounsSumPv;
    }

    public void setBounsSumPv(BigDecimal bounsSumPv) {
        this.bounsSumPv = bounsSumPv;
    }

    public BigDecimal getAllocatePercentage() {
        return allocatePercentage;
    }

    public void setAllocatePercentage(BigDecimal allocatePercentage) {
        this.allocatePercentage = allocatePercentage;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBounsSumCurrency() {
        return bounsSumCurrency;
    }

    public void setBounsSumCurrency(BigDecimal bounsSumCurrency) {
        this.bounsSumCurrency = bounsSumCurrency;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Integer getStaus() {
        return staus;
    }

    public void setStaus(Integer staus) {
        this.staus = staus;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
