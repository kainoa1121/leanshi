package cn.leanshi.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单实体类，默认一个订单只有一种商品，临时测试使用，不牵扯订单项
 */
public class Order implements Serializable {
    private String mCode;//订单所属会员编号
    private String goodsCode;
    private String goodsName;
    private int number;
    private BigDecimal priceRetail;
    private BigDecimal priceVip;
    private BigDecimal totalMoney;
    private BigDecimal pvPrice;
    private BigDecimal totalPv;
    private int deliveryMethod;//0自提  1快递（快递费用固定10块）

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public int getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(int deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPriceRetail() {
        return priceRetail;
    }

    public void setPriceRetail(BigDecimal priceRetail) {
        this.priceRetail = priceRetail;
    }

    public BigDecimal getPriceVip() {
        return priceVip;
    }

    public void setPriceVip(BigDecimal priceVip) {
        this.priceVip = priceVip;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getPvPrice() {
        return pvPrice;
    }

    public void setPvPrice(BigDecimal pvPrice) {
        this.pvPrice = pvPrice;
    }

    public BigDecimal getTotalPv() {
        return totalPv;
    }

    public void setTotalPv(BigDecimal totalPv) {
        this.totalPv = totalPv;
    }
}
