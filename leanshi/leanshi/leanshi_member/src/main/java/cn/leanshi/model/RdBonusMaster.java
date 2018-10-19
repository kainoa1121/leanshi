package cn.leanshi.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 奖金主表
 * @author :zc
 */
public class RdBonusMaster implements Serializable {
    private String periodCode;//业务周期
    private String mCode;//会员编号
    private BigDecimal bonusRetail;//当期全部零售产生的奖励

    private BigDecimal  bonusNewVip;//当期推荐新的VIP产生奖励

    private BigDecimal  bonusDevp1;//市场拓展奖第1代
    private BigDecimal  bonusDevp2;//市场拓展奖第2代
    private BigDecimal  bonusDevpShare;//市场拓展奖分红
    private BigDecimal  bonusDevpSum;//市场拓展奖合计

    private BigDecimal  bonusLdDirect;//领导奖直接奖励
    private BigDecimal  bonusLdIndirect;//领导奖间接奖励
    private BigDecimal  bonusLdSupport;//领导奖同级支持奖
    private BigDecimal  bonusLdSum;//领导奖合计

    private BigDecimal  chargeService;//服务费扣款

    private BigDecimal  bonusSum;//总奖励合计

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public BigDecimal getBonusRetail() {
        return bonusRetail;
    }

    public void setBonusRetail(BigDecimal bonusRetail) {
        this.bonusRetail = bonusRetail;
    }

    public BigDecimal getBonusNewVip() {
        return bonusNewVip;
    }

    public void setBonusNewVip(BigDecimal bonusNewVip) {
        this.bonusNewVip = bonusNewVip;
    }

    public BigDecimal getBonusDevp1() {
        return bonusDevp1;
    }

    public void setBonusDevp1(BigDecimal bonusDevp1) {
        this.bonusDevp1 = bonusDevp1;
    }

    public BigDecimal getBonusDevp2() {
        return bonusDevp2;
    }

    public void setBonusDevp2(BigDecimal bonusDevp2) {
        this.bonusDevp2 = bonusDevp2;
    }

    public BigDecimal getBonusDevpShare() {
        return bonusDevpShare;
    }

    public void setBonusDevpShare(BigDecimal bonusDevpShare) {
        this.bonusDevpShare = bonusDevpShare;
    }

    public BigDecimal getBonusDevpSum() {
        return bonusDevpSum;
    }

    public void setBonusDevpSum(BigDecimal bonusDevpSum) {
        this.bonusDevpSum = bonusDevpSum;
    }

    public BigDecimal getBonusLdDirect() {
        return bonusLdDirect;
    }

    public void setBonusLdDirect(BigDecimal bonusLdDirect) {
        this.bonusLdDirect = bonusLdDirect;
    }

    public BigDecimal getBonusLdIndirect() {
        return bonusLdIndirect;
    }

    public void setBonusLdIndirect(BigDecimal bonusLdIndirect) {
        this.bonusLdIndirect = bonusLdIndirect;
    }

    public BigDecimal getBonusLdSupport() {
        return bonusLdSupport;
    }

    public void setBonusLdSupport(BigDecimal bonusLdSupport) {
        this.bonusLdSupport = bonusLdSupport;
    }

    public BigDecimal getBonusLdSum() {
        return bonusLdSum;
    }

    public void setBonusLdSum(BigDecimal bonusLdSum) {
        this.bonusLdSum = bonusLdSum;
    }

    public BigDecimal getChargeService() {
        return chargeService;
    }

    public void setChargeService(BigDecimal chargeService) {
        this.chargeService = chargeService;
    }

    public BigDecimal getBonusSum() {
        return bonusSum;
    }

    public void setBonusSum(BigDecimal bonusSum) {
        this.bonusSum = bonusSum;
    }
}
