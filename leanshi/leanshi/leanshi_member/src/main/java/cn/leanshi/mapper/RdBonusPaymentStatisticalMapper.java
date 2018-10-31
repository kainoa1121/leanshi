package cn.leanshi.mapper;

import cn.leanshi.model.RdBonusPaymentStatistical;

import java.util.HashMap;
import java.util.List;

public interface RdBonusPaymentStatisticalMapper {
    /**
     * 往RdBonusPaymentStatistical中插入数据
     * @param rdBonusPaymentStatistical
     */
    void insertRdBonusPaymentStatistical(RdBonusPaymentStatistical rdBonusPaymentStatistical);

    /**
     * 根据周期区间查询历史奖金表
     * @param map
     * @return
     */
    List<RdBonusPaymentStatistical> findRdBonusPaymentStatisticalByLR(HashMap map);
}
