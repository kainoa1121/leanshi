package cn.leanshi.service.serviceImpl;

import cn.leanshi.mapper.MemberQualificationMapper;
import cn.leanshi.mapper.RdBonusMasterMapper;
import cn.leanshi.mapper.RdBonusPaymentMapper;
import cn.leanshi.model.MemberQualification;
import cn.leanshi.model.RdBonusMaster;
import cn.leanshi.model.RdBonusPayment;
import cn.leanshi.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor  //对所有带有@NonNull注解的或者带有final修饰的成员变量生成对应的构造方法
@Service
@Transactional
public class BonusServiceImpl implements BonusService {
    private final RdBonusMasterMapper rdBonusMasterMapper;
    private final RdBonusPaymentMapper rdBonusPaymentMapper;
    private final MemberQualificationMapper memberQualificationMapper;
    /**
     * 根据周期查询当前周期内会员奖金集合
     * @param periodCode
     * @return
     */
    @Override
    public List<RdBonusMaster> searchByPeriodCode(String periodCode) {
        return rdBonusMasterMapper.searchByPeriodCode(periodCode);
    }
    /**
     * 根据会员编号和会员姓名查询会员奖金集合
     * @param mCode,mName
     * @return
     */
    @Override
    public List<RdBonusPayment> searchBonusPaymentByCodeAndName(String mCode, String mName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("mCode",mCode);
        map.put("mName",mName);
        return rdBonusPaymentMapper.searchBonusPaymentByCodeAndName(map);
    }


    /**
     * 根据条件查询奖金发放明细表
     * @param periodCodeLeft
     * @param periodCodeRight
     * @param mCode
     * @param mName
     * @param payStatus
     * @return
     */
    @Override
    public List<RdBonusPayment> searchBonusPayment(String periodCodeLeft, String periodCodeRight, String mCode, String mName, String payStatus) {
        List<RdBonusPayment> rdBonusPayments = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("periodCodeLeft",periodCodeLeft);
        map.put("periodCodeRight",periodCodeRight);
        map.put("mCode",mCode);
        map.put("mName",mName);
        if(payStatus!=null&&!"".equals(payStatus.trim())){
            map.put("payStatus",Integer.parseInt(payStatus));
        }
        rdBonusPayments=rdBonusPaymentMapper.searchBonusPaymentByCriteria(map);
        return rdBonusPayments;
    }

    /**
     * 根据周期查询当前周期下所有会员在内最大的所处层次
     * @param periodCode
     * @return
     */
    @Override
    public int findMaxLayerByPeriodCode(String periodCode) {
        int maxLayer = memberQualificationMapper.findMaxLayerByPeriodCode(periodCode);
        if(maxLayer>=0){
            return maxLayer;
        }
        return -1;
    }

    /**
     * 根据周期查询会员资格表，确认是否存在该周期或者该周期的会员资格表（即业务信息是否统计完毕）
     * @param periodCode
     * @return
     */
    @Override
    public List<MemberQualification> checkPeriodBe(String periodCode) {
        return memberQualificationMapper.findQualificationByPeriod(periodCode);
    }

    /**
     * 查找符合所处层次最大的符合条件能进行拓展奖计算的节点会员集合
     * @param maxLayer
     * @return
     */
    @Override
    public List<MemberQualification> findMaxLayerList(int maxLayer,String periodCode) {

        Map<String, Object> map=new HashMap<>();
        map.put("layer",maxLayer);
        map.put("periodCode",periodCode);
        return  memberQualificationMapper.findByLayer(map);
    }

    @Override
    public MemberQualification findBySponsorCode(String sponsorCode,String periodCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("periodCode",periodCode);
        map.put("mCode",sponsorCode);
        return memberQualificationMapper.findQualificationByPeriodAndMCode(map);
    }

    /**
     * 根据周期和mcode查询RdBonusMaster表
     * @param mCode
     * @param periodCode
     * @return
     */
    @Override
    public RdBonusMaster searchBonusMasterByMCode(String mCode, String periodCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("mCode",mCode);
        map.put("periodCode",periodCode);
        return rdBonusMasterMapper.searchByPeriodCodeAndMcode(map);
    }

    /**
     * 添加RdBonusMaster到奖金主表
     * @param rdBonusMaster1
     */
    @Override
    public void insertRdBonusMaster(RdBonusMaster rdBonusMaster1) {
        rdBonusMasterMapper.insertRdBonusMaster(rdBonusMaster1);
    }

    /**
     * 根据会员编号，周期和单一子节点所得拓展奖修改RdBonusMaster表
     * @param add
     * @param mCode
     * @param periodCode
     */
    @Override
    public void updateBonusDevp1(BigDecimal add, String mCode, String periodCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("add",add);
        map.put("mCode",mCode);
        map.put("periodCode",periodCode);
        rdBonusMasterMapper.updateBonusDevp1(map);
    }

    /**
     * 根据周期删除该周期计算的奖金表（二次计算）
     * @param periodCode
     */
    @Override
    public void deleteByPeriodCode(String periodCode) {
        rdBonusMasterMapper.deleteByPeriodCode(periodCode);
    }

    /**
     * 修改奖金表中拓展奖二代
     * @param add
     * @param mCode
     * @param periodCode
     */
    @Override
    public void updateBonusDevp2(BigDecimal add, String mCode, String periodCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("add",add);
        map.put("mCode",mCode);
        map.put("periodCode",periodCode);
        rdBonusMasterMapper.updateBonusDevp2(map);
    }

    /**
     * 根据周期和所处最大层数查询购买ppv>0的MemberQualification
     * @param num
     * @param periodCode
     * @return
     */
    @Override
    public List<MemberQualification> findByLayer(int num, String periodCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("layer",num);
        map.put("periodCode",periodCode);
        return memberQualificationMapper.findByLayer(map);
    }

    /**
     * 根据业绩表数据初始化奖金表数据   2018/10/15
     * @param memberQualification
     */
    @Override
    public void insertRdBonusMaster(MemberQualification memberQualification) {
        RdBonusMaster rdBonusMaster = new RdBonusMaster();
        rdBonusMaster.setPeriodCode(memberQualification.getPeriodCode());
        rdBonusMaster.setmCode(memberQualification.getMCode());
        rdBonusMasterMapper.insertRdBonusMaster(rdBonusMaster);
    }

    /**
     * 根据当期节点ppv修改对应符合条件节点获得拓展奖分红金额
     * @param add
     * @param mCode
     * @param periodCode
     */
    @Override
    public void updateBonusDevpShare(BigDecimal add, String mCode, String periodCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("add",add);
        map.put("mCode",mCode);
        map.put("periodCode",periodCode);
        rdBonusMasterMapper.updateBonusDevpShare(map);
    }

    /**
     * 根据周期号查询当前周期内新晋升成为vip的客户list集合
     * @param periodCode
     * @return
     */
    @Override
    public List<MemberQualification> findNewVip(String periodCode) {
        return memberQualificationMapper.findNewVip(periodCode);
    }

    /**
     * 根据周期编号，会员编号，以及金额修改该会员该周期的vip新人辅导奖
     * @param add
     * @param mCode
     * @param periodCode
     */
    @Override
    public void updateBonusNewVip(BigDecimal add, String mCode, String periodCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("add",add);
        map.put("mCode",mCode);
        map.put("periodCode",periodCode);
        rdBonusMasterMapper.updateBonusNewVip(map);
    }

    /**
     * 查询一个周期内所有客户奖金信息
     * @param periodCode
     * @return
     */
    @Override
    public List<RdBonusMaster> selectAll(String periodCode) {
        return rdBonusMasterMapper.selectAll(periodCode);
    }

    /**
     * 更新各项奖项合计
     * @param bonusDevpDum
     * @param bonusLdSum
     * @param bonusSum
     */
    @Override
    public void updateBonusSum(BigDecimal bounsDevpShare, BigDecimal bonusDevpDum, BigDecimal bonusLdSum, BigDecimal bonusSum, RdBonusMaster rdBonusMaster) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bounsDevpShare",bounsDevpShare);
        map.put("bonusDevpDum",bonusDevpDum);
        map.put("bonusLdSum",bonusLdSum);
        map.put("bonusSum",bonusSum);
        map.put("periodCode",rdBonusMaster.getPeriodCode());
        map.put("mCode",rdBonusMaster.getmCode());
        rdBonusMasterMapper.updateBonusSum(map);
    }

    /**
     * 根据周期查找当期的总pv值
     * @param periodCode
     * @return
     */
    @Override
    public int findSumPv(String periodCode) {
        return memberQualificationMapper.findSumPv(periodCode);
    }

    @Override
    public int findSumShare(String periodCode) {
        return rdBonusMasterMapper.findSumShare(periodCode);
    }

    /**
     * 根据周期以及所处层次，查询该层次内，符合获得领导奖的所有客户的业绩表对象集合
     * @param num
     * @param periodCode
     * @return
     */
    @Override
    public List<MemberQualification> findRankGE7(int num, String periodCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("num",num);
        map.put("periodCode",periodCode);
        return memberQualificationMapper.findRankGE7(map);
    }

    /**
     * 修改直接领导奖
     * @param i
     * @param periodCode
     * @param mCode
     */
    @Override
    public void updateBonusLDDirect(BigDecimal i, String periodCode, String mCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bonusLDDirect",i);
        map.put("periodCode",periodCode);
        map.put("mCode",mCode);
        rdBonusMasterMapper.updateBonusLDDirect(map);
    }

    /**
     * 修改间接领导奖
     * @param bonusLDIndirect
     * @param periodCode
     * @param mCode
     */
    @Override
    public void updateBonusLDIndirect(BigDecimal bonusLDIndirect, String periodCode, String mCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bonusLDIndirect",bonusLDIndirect);
        map.put("periodCode",periodCode);
        map.put("mCode",mCode);
        rdBonusMasterMapper.updateBonusLDIndirect(map);
    }

    /**
     * 修改领导奖同级奖
     * @param i
     * @param periodCode
     * @param mCode
     */
    @Override
    public void updateBonusLDSupport(BigDecimal i, String periodCode, String mCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bonusLDSupport",i);
        map.put("periodCode",periodCode);
        map.put("mCode",mCode);
        rdBonusMasterMapper.updateBonusLDSupport(map);
    }

}

