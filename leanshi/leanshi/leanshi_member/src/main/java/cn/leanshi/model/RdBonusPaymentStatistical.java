package cn.leanshi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RdBonusPaymentStatistical implements Serializable {
    private String periodCode;//周期编号
    private int newNum;//新人数（当前注册的人数）
    private int customerNum;
    private BigDecimal bounsSumPv;
    private BigDecimal bonusSumMoney;
    private BigDecimal bonusReissue;//应补发奖金
    private BigDecimal chargeSum;//扣款金额
    private BigDecimal payableSum;//应该发放奖金

    private int orderNum;//总订单数
    private BigDecimal totalPerformance;//总业绩
    private BigDecimal bonusNewVip;//vip辅导奖
    private BigDecimal bonusRetail;//零售奖
    private BigDecimal bonusDevp;//拓展奖
    private BigDecimal bonusDevpPercentage;//拓展奖占比
    private BigDecimal bonusLD;//领导奖
    private BigDecimal bonusLDPercentage;//领导奖占比
    private BigDecimal bonusSpec;//特别奖
    private BigDecimal bonusSpecPercentage;//特别奖占比
    private BigDecimal bonusTotalPercentage;//总奖金拨出率
    private String currencyCode;//币种
    private Integer payStatus;//发放状态  0未发放  1已发放  -1已废除
    private String payPeople;//发放人
    private Date payTime;//发放时间

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public int getNewNum() {
        return newNum;
    }

    public void setNewNum(int newNum) {
        this.newNum = newNum;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public BigDecimal getBounsSumPv() {
        return bounsSumPv;
    }

    public void setBounsSumPv(BigDecimal bounsSumPv) {
        this.bounsSumPv = bounsSumPv;
    }

    public BigDecimal getBonusSumMoney() {
        return bonusSumMoney;
    }

    public void setBonusSumMoney(BigDecimal bonusSumMoney) {
        this.bonusSumMoney = bonusSumMoney;
    }

    public BigDecimal getBonusReissue() {
        return bonusReissue;
    }

    public void setBonusReissue(BigDecimal bonusReissue) {
        this.bonusReissue = bonusReissue;
    }

    public BigDecimal getChargeSum() {
        return chargeSum;
    }

    public void setChargeSum(BigDecimal chargeSum) {
        this.chargeSum = chargeSum;
    }

    public BigDecimal getPayableSum() {
        return payableSum;
    }

    public void setPayableSum(BigDecimal payableSum) {
        this.payableSum = payableSum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getTotalPerformance() {
        return totalPerformance;
    }

    public void setTotalPerformance(BigDecimal totalPerformance) {
        this.totalPerformance = totalPerformance;
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

    public BigDecimal getBonusLD() {
        return bonusLD;
    }

    public void setBonusLD(BigDecimal bonusLD) {
        this.bonusLD = bonusLD;
    }

    public BigDecimal getBonusLDPercentage() {
        return bonusLDPercentage;
    }

    public void setBonusLDPercentage(BigDecimal bonusLDPercentage) {
        this.bonusLDPercentage = bonusLDPercentage;
    }

    public BigDecimal getBonusSpec() {
        return bonusSpec;
    }

    public void setBonusSpec(BigDecimal bonusSpec) {
        this.bonusSpec = bonusSpec;
    }

    public BigDecimal getBonusSpecPercentage() {
        return bonusSpecPercentage;
    }

    public void setBonusSpecPercentage(BigDecimal bonusSpecPercentage) {
        this.bonusSpecPercentage = bonusSpecPercentage;
    }

    public BigDecimal getBonusTotalPercentage() {
        return bonusTotalPercentage;
    }

    public void setBonusTotalPercentage(BigDecimal bonusTotalPercentage) {
        this.bonusTotalPercentage = bonusTotalPercentage;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayPeople() {
        return payPeople;
    }

    public void setPayPeople(String payPeople) {
        this.payPeople = payPeople;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
