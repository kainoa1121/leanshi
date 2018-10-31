package cn.leanshi.model.util;

public interface Constant {
    String CURRENCY_CODE_CHINA="CNY";
    //pv与人民币之间的比例  pv：rmb=7.1
    String PV_CNY_RATE = "7.1";
    //奖金审核状态码 0 表示未审核，-1表示审核未通过，1表示审核通过
    Integer STATUS_NOT_BEGAIN = 0;
    Integer STATUS_FAIL = -1;
    Integer STATUS_TRUE=1;
}
