package cn.leanshi.model;

import java.io.Serializable;

/**
 * 奖金发放表
 * @author zc
 */
public class RdBonusPayment implements Serializable {
    private String periodCode;//业务周期
    private String mName;//会员姓名
    private String mCode;//会员编号
    private int bonusSum;//当期计算出来的奖金(PV)
    private String currencyCode;//币种
    private int bonusSumMoney;//当期计算出来的奖金(PV)
    private int bonusReissue;//应补发的奖金
    private int chargeSum;//本期处理的扣款合计
    private int payableSum;//应付奖金（总奖金-各项扣款）
    private int payStatus;//发放标志  1.已发放  0.未发放

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

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

    public int getBonusSum() {
        return bonusSum;
    }

    public void setBonusSum(int bonusSum) {
        this.bonusSum = bonusSum;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getBonusSumMoney() {
        return bonusSumMoney;
    }

    public void setBonusSumMoney(int bonusSumMoney) {
        this.bonusSumMoney = bonusSumMoney;
    }

    public int getBonusReissue() {
        return bonusReissue;
    }

    public void setBonusReissue(int bonusReissue) {
        this.bonusReissue = bonusReissue;
    }

    public int getChargeSum() {
        return chargeSum;
    }

    public void setChargeSum(int chargeSum) {
        this.chargeSum = chargeSum;
    }

    public int getPayableSum() {
        return payableSum;
    }

    public void setPayableSum(int payableSum) {
        this.payableSum = payableSum;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public RdBonusPayment(String periodCode, String mName, String mCode, int bonusSum, String currencyCode, int bonusSumMoney, int bonusReissue, int chargeSum, int payableSum, int payStatus) {
        this.periodCode = periodCode;
        this.mName = mName;
        this.mCode = mCode;
        this.bonusSum = bonusSum;
        this.currencyCode = currencyCode;
        this.bonusSumMoney = bonusSumMoney;
        this.bonusReissue = bonusReissue;
        this.chargeSum = chargeSum;
        this.payableSum = payableSum;
        this.payStatus = payStatus;
    }

    public RdBonusPayment() {
    }
}

