package cn.leanshi.mapper;

import cn.leanshi.model.RdBonusPayment;

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
}
