package cn.leanshi.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 奖金发放表
 * @author zc
 */
public class RdBonusPayment implements Serializable {
    private String periodCode;//业务周期
    private String mNickName;//会员昵称
    private String mCode;//会员编号
    private BigDecimal bonusSum;//当期计算出来的奖金(PV)
    private String currencyCode;//币种
    private BigDecimal bonusSumMoney;//当期计算出来的奖金(人民币)
    private BigDecimal bonusReissue;//应补发的奖金
    private BigDecimal chargeSum;//本期处理的扣款合计
    private BigDecimal payableSum;//应付奖金（总奖金-各项扣款）
    private int payStatus;//发放标志  1.已发放  0.未发放

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public String getmNickName() {
        return mNickName;
    }

    public void setmNickName(String mNickName) {
        this.mNickName = mNickName;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public BigDecimal getBonusSum() {
        return bonusSum;
    }

    public void setBonusSum(BigDecimal bonusSum) {
        this.bonusSum = bonusSum;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }
}

