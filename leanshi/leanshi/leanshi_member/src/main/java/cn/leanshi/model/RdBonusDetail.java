package cn.leanshi.model;

import java.io.Serializable;

/**
 *  奖金明细表(pv为基数)
 *   @author :zc
 */
public class RdBonusDetail implements Serializable {
    private String periodCode;//业务周期
    private String mCodeFrom;//来源会员编号
    private String mCodeTo;//奖励会员编号
    private int rankTo;//获奖人级别
    private String pvCalBaseCode;//计算基数类型
    private int pvCalBase;//奖金计算基数
    private int rate;//奖金比例
    private int bonusCode;//奖项编号
    private int layer;//代数(发放计算的代数)
    private int shareBaseTotal;//分红奖的公司总基数
    private int shareBonusTotal;//分红奖的公司总奖金
    private int shareBase;//分红奖的个人基数
    private int shareRate;//分红奖的个人占比
    private int bonus;//奖金

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public String getmCodeFrom() {
        return mCodeFrom;
    }

    public void setmCodeFrom(String mCodeFrom) {
        this.mCodeFrom = mCodeFrom;
    }

    public String getmCodeTo() {
        return mCodeTo;
    }

    public void setmCodeTo(String mCodeTo) {
        this.mCodeTo = mCodeTo;
    }

    public int getRankTo() {
        return rankTo;
    }

    public void setRankTo(int rankTo) {
        this.rankTo = rankTo;
    }

    public String getPvCalBaseCode() {
        return pvCalBaseCode;
    }

    public void setPvCalBaseCode(String pvCalBaseCode) {
        this.pvCalBaseCode = pvCalBaseCode;
    }

    public int getPvCalBase() {
        return pvCalBase;
    }

    public void setPvCalBase(int pvCalBase) {
        this.pvCalBase = pvCalBase;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getBonusCode() {
        return bonusCode;
    }

    public void setBonusCode(int bonusCode) {
        this.bonusCode = bonusCode;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getShareBaseTotal() {
        return shareBaseTotal;
    }

    public void setShareBaseTotal(int shareBaseTotal) {
        this.shareBaseTotal = shareBaseTotal;
    }

    public int getShareBonusTotal() {
        return shareBonusTotal;
    }

    public void setShareBonusTotal(int shareBonusTotal) {
        this.shareBonusTotal = shareBonusTotal;
    }

    public int getShareBase() {
        return shareBase;
    }

    public void setShareBase(int shareBase) {
        this.shareBase = shareBase;
    }

    public int getShareRate() {
        return shareRate;
    }

    public void setShareRate(int shareRate) {
        this.shareRate = shareRate;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public RdBonusDetail(String periodCode, String mCodeFrom, String mCodeTo, int rankTo, String pvCalBaseCode, int pvCalBase, int rate, int bonusCode, int layer, int shareBaseTotal, int shareBonusTotal, int shareBase, int shareRate, int bonus) {
        this.periodCode = periodCode;
        this.mCodeFrom = mCodeFrom;
        this.mCodeTo = mCodeTo;
        this.rankTo = rankTo;
        this.pvCalBaseCode = pvCalBaseCode;
        this.pvCalBase = pvCalBase;
        this.rate = rate;
        this.bonusCode = bonusCode;
        this.layer = layer;
        this.shareBaseTotal = shareBaseTotal;
        this.shareBonusTotal = shareBonusTotal;
        this.shareBase = shareBase;
        this.shareRate = shareRate;
        this.bonus = bonus;
    }

    public RdBonusDetail() {
    }
}
