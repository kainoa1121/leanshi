package cn.leanshi.mapper;

import cn.leanshi.model.RdBonusPayment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RdBonusPaymentMapper {
    /**
     * 根据会员编号和姓名查询奖金支付详情
     * @return
     */
    List<RdBonusPayment> searchBonusPaymentByCodeAndName(Map<String, Object> map);


    /**
     * 根据组合条件查询奖金发放明细表
     * @param map
     * @return
     */
    List<RdBonusPayment> searchBonusPaymentByCriteria(HashMap<String, Object> map);

    /**
     * 往奖金发放表中插入数据
     * @param rdBonusPayment
     */
    void insertRdBonusPayment(RdBonusPayment rdBonusPayment);

    /**
     * 查询当前周期总奖金和pv
     * @param periodCode
     * @return
     */
    BigDecimal findSumBouns(String periodCode);

    /**
     * 查询当前周期应补发奖金合计
     * @param periodCode
     * @return
     */
    BigDecimal findSumBounsReissue(String periodCode);

    /**
     * 查询当前周期扣款总合计
     * @param periodCode
     * @return
     */
    BigDecimal findSumChargeSum(String periodCode);

    /**
     * 查询当前周期可得奖金合计
     * @param periodCode
     * @return
     */
    BigDecimal findSumPayableSum(String periodCode);

    /**
     * 查询当前周期所有奖金发放表
     * @param periodCode
     * @return
     */
    List<RdBonusPayment> findBounsPaymentAll(String periodCode);
}
