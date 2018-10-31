package cn.leanshi.mapper;

import cn.leanshi.model.RdBonusMaster;
import cn.leanshi.model.RdBonusPayment;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RdBonusMasterMapper {
    /**
     * 根据周期查询该周期所有会员的奖金表
     * @param periodCode
     * @return
     */
    List<RdBonusMaster> searchByPeriodCode(String periodCode);

    /**
     * 根据周期和mcode查询RdBonusMaster表
     * @param map
     * @return
     */
    RdBonusMaster searchByPeriodCodeAndMcode(HashMap<String,Object> map);

    /**
     * 根据周期和mcode添加RdBonusMaster表，其他数值默认设置为0
     * @param rdBonusMaster1
     */
    void insertRdBonusMaster(RdBonusMaster rdBonusMaster1);
    /**
     * 根据会员编号，周期和单一子节点所得拓展奖修改RdBonusMaster表一代拓展奖
     * @param map
     *
     */
    void updateBonusDevp1(HashMap<String,Object> map);
    /**
     * 根据周期删除该周期计算的奖金表（二次计算）
     * @param periodCode
     */
    void deleteByPeriodCode(String periodCode);
    /**
     * 根据会员编号，周期和单一子节点所得拓展奖修改RdBonusMaster表二代拓展奖
     * @param map
     *
     */
    void updateBonusDevp2(HashMap<String,Object> map);

    void updateBonusDevpShare(HashMap<String,Object> map);

    void updateBonusNewVip(HashMap<String,Object> map);

    List<RdBonusMaster> selectAll(String periodCode);

    void updateBonusSum(HashMap<String,Object> map);

    int findSumShare(String periodCode);

    void updateBonusLDDirect(HashMap<String,Object> map);

    void updateBonusLDIndirect(HashMap<String,Object> map);

    void updateBonusLDSupport(HashMap<String, Object> map);

    List<RdBonusMaster> findMasterByPeriod(String periodCode);

    int findCustomerNum(String periodCode);

    BigDecimal findBonusNewVip(String periodCode);

    BigDecimal findBonusRetail(String periodCode);

    BigDecimal findBonusDevp(String periodCode);

    BigDecimal findBonusLd(String periodCode);

    List<RdBonusMaster> searchBonusMasterByCerteria(HashMap<String,Object> map);
}
