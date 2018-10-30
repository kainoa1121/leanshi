package cn.leanshi.controller;

import cn.leanshi.model.*;
import cn.leanshi.model.http.ResultMsg;
import cn.leanshi.service.BonusService;
import com.github.pagehelper.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/bonus")
@RequiredArgsConstructor
public class BonusController {
    @Autowired
    private HttpSession session;

    private final BonusService bonusService;

    /*
     *查询当期奖金表
     * */
    @RequestMapping(value = "/findBonus",method = RequestMethod.POST)
    public ResultMsg findBonus(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
                               @RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize,
                               @RequestParam(value = "periodCode",required = false) String periodCode){

        int size=pageSize;

        PageHelper.startPage(currentPage,size);
        ResultMsg<PageInfo<RdBonusMaster>> resultMsg = new ResultMsg<PageInfo<RdBonusMaster>>();
        List<RdBonusMaster> rdBonusMasterList=bonusService.searchByPeriodCode(periodCode);
        if(rdBonusMasterList!=null&&rdBonusMasterList.size()>0){
            PageInfo<RdBonusMaster> pageInfo = new PageInfo<RdBonusMaster>(rdBonusMasterList);
            resultMsg.setData(pageInfo);
            resultMsg.setCode(true);
            return resultMsg;
        }
        return ResultMsg.newInstance(false,"该期奖金未计算或当前周期不存在") ;
    }
    /*
     *奖金发放明细表查询
     * */
    @RequestMapping(value = "/searchBonusPayment",method = RequestMethod.POST)
    public ResultMsg searchBonusPayment(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
                                        @RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize,
                                        @RequestParam(value = "periodCodeLeft",required = false) String periodCodeLeft,
                                        @RequestParam(value = "periodCodeRight",required = false) String periodCodeRight,
                                        @RequestParam(value = "mCode",required = false) String mCode,
                                        @RequestParam(value = "mNickName",required = false) String mNickName,
                                        @RequestParam(value = "payStatus",required = false) String payStatus
                            ){
        int size=pageSize;
        BigDecimal totalBonus = new BigDecimal("0.00");
        List<RdBonusPayment> rdBonusPayments = new ArrayList<>();
        PageHelper.startPage(currentPage,size);
        if(mCode!=null&&!"".equals(mCode.trim())&&mNickName!=null&&!"".equals(mNickName.trim())){
            List<RdBonusPayment> list=bonusService.searchBonusPaymentByCodeAndName(mCode,mNickName);
            if(list!=null&&list.size()>0){
                //System.out.println("姓名和会员号没有冲突");
                rdBonusPayments=bonusService.searchBonusPayment(periodCodeLeft,periodCodeRight,mCode,mNickName,payStatus);
                if(rdBonusPayments==null||rdBonusPayments.size()==0){
                    return ResultMsg.newInstance(false,"没有符合条件的数据");
                }
                for (RdBonusPayment rdBonusPayment : rdBonusPayments) {
                    totalBonus.add(rdBonusPayment.getPayableSum());
                }
                ResultMsg<PageInfo<RdBonusPayment>> resultMsg = new ResultMsg<>();
                PageInfo<RdBonusPayment> pageInfo = new PageInfo<>(rdBonusPayments);
                resultMsg.setCode(true);
                resultMsg.setData(pageInfo);
                resultMsg.setMsg("应付总奖金："+totalBonus);
                return resultMsg;
            }
            return ResultMsg.newInstance(false,"会员编号和姓名不匹配，请重新输入");
        }
        rdBonusPayments=bonusService.searchBonusPayment(periodCodeLeft,periodCodeRight,mCode,mNickName,payStatus);
        if(rdBonusPayments==null||rdBonusPayments.size()==0){
            return ResultMsg.newInstance(false,"没有符合条件的数据");
        }
        for (RdBonusPayment rdBonusPayment : rdBonusPayments) {
            totalBonus.add(rdBonusPayment.getPayableSum());
        }
        ResultMsg<PageInfo<RdBonusPayment>> resultMsg = new ResultMsg<>();
        PageInfo<RdBonusPayment> pageInfo = new PageInfo<>(rdBonusPayments);
        resultMsg.setCode(true);
        resultMsg.setData(pageInfo);
        resultMsg.setMsg("应付总奖金："+totalBonus);
        return resultMsg;
    }
    @RequestMapping(value = "/searchBounsMaster",method = RequestMethod.POST)
    public ResultMsg searchBounsMaster(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
                                       @RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize,
                                       @RequestParam(value = "periodCodeLeft",required = false) String periodCodeLeft,
                                       @RequestParam(value = "periodCodeRight",required = false) String periodCodeRight,
                                       @RequestParam(value = "mCode",required = false) String mCode,
                                       @RequestParam(value = "mNickName",required = false) String mNickName){
        int size=pageSize;
        BigDecimal totalBonus = new BigDecimal("0.00");
        PageHelper.startPage(currentPage,size);
        //mCode与mNickName都有
        if(mCode!=null&&!"".equals(mCode.trim())&&mNickName!=null&&!"".equals(mNickName.trim())){
            List<Member_basic> list=bonusService.searchMemberByMcodeAndNickName(mCode,mNickName);
            if(list!=null&&list.size()>0){
                //System.out.println("姓名和会员号没有冲突");
                List<RdBonusMaster> rdBonusMasterList=bonusService.searchBonusMasterByCerteria(periodCodeLeft,periodCodeRight,mCode);
                if(rdBonusMasterList==null||rdBonusMasterList.size()==0){
                    return ResultMsg.newInstance(false,"没有符合条件的数据");
                }
                for (RdBonusMaster rdBonusMaster : rdBonusMasterList) {
                    totalBonus.add(rdBonusMaster.getBonusSum());
                }
                ResultMsg<PageInfo<RdBonusMaster>> resultMsg = new ResultMsg<>();
                PageInfo<RdBonusMaster> pageInfo = new PageInfo<>(rdBonusMasterList);
                resultMsg.setCode(true);
                resultMsg.setData(pageInfo);
                resultMsg.setMsg("总奖励PV："+totalBonus);
                return resultMsg;
            }
            return ResultMsg.newInstance(false,"会员编号和会员昵称不匹配");
        }
        //mNickName存在
        if(mNickName!=null&&!"".equals(mNickName.trim())){
            //根据mNickName找到对应的mCode
            String code=bonusService.findMemberMcodeByNickName(mNickName);
            List<RdBonusMaster> rdBonusMasterList=bonusService.searchBonusMasterByCerteria(periodCodeLeft,periodCodeRight,code);
            if(rdBonusMasterList==null||rdBonusMasterList.size()==0){
                return ResultMsg.newInstance(false,"没有符合条件的数据");
            }
            for (RdBonusMaster rdBonusMaster : rdBonusMasterList) {
                totalBonus.add(rdBonusMaster.getBonusSum());
            }
            ResultMsg<PageInfo<RdBonusMaster>> resultMsg = new ResultMsg<>();
            PageInfo<RdBonusMaster> pageInfo = new PageInfo<>(rdBonusMasterList);
            resultMsg.setCode(true);
            resultMsg.setData(pageInfo);
            resultMsg.setMsg("总奖励PV："+totalBonus);
            return resultMsg;
        }
        List<RdBonusMaster> rdBonusMasterList=bonusService.searchBonusMasterByCerteria(periodCodeLeft,periodCodeRight,mCode);
        if(rdBonusMasterList==null||rdBonusMasterList.size()==0){
            return ResultMsg.newInstance(false,"没有符合条件的数据");
        }
        for (RdBonusMaster rdBonusMaster : rdBonusMasterList) {
            totalBonus.add(rdBonusMaster.getBonusSum());
        }
        ResultMsg<PageInfo<RdBonusMaster>> resultMsg = new ResultMsg<>();
        PageInfo<RdBonusMaster> pageInfo = new PageInfo<>(rdBonusMasterList);
        resultMsg.setCode(true);
        resultMsg.setData(pageInfo);
        resultMsg.setMsg("总奖励PV："+totalBonus);
        return resultMsg;
    }

    /**
     * 根据周期计算奖金
     * @param periodCode
     * @return
     */
    @RequestMapping(value = "/countBonusByPeriod",method =RequestMethod.GET )
    public ResultMsg countBonusByPeriod(@RequestParam(value = "periodCode",required = true)String periodCode){
        //1.由于当前方法不得重复调用，如重复调用，数据库核算数据翻倍，每次调用需要删除该周期中RdBonusMaster
        bonusService.deleteByPeriodCode(periodCode);
        //2.判断当前传递周期是否存在或者该周期业绩是否统计录入系统
        List<MemberQualification> list=bonusService.checkPeriodBe(periodCode);
        if(list!=null&&list.size()>0){
            //3.如果该期业绩表已经统计完毕，根据业绩表初始化该期奖金表数据
            for (MemberQualification memberQualification : list) {
                bonusService.insertRdBonusMaster(memberQualification);
            }
            //4.根据周期编号查询该周期内会员资格表中所处层次最大的数值 并逐层计算市场拓展奖
            int maxLayer=bonusService.findMaxLayerByPeriodCode(periodCode);
            for(int i=maxLayer;i>1;i--){
                //计算每一层每一个节点所产生的拓展奖（拓展奖包括一代拓展奖，二代拓展奖，单一节点拓展奖分红基数等）
                getBonusDevp1(i,periodCode);
                getBonusDevp2(i,periodCode);
                getBonusDevpShare(i,periodCode);
            }
            for(int i=maxLayer;i>=1;i--){
                getBonusLD(i,periodCode);
            }
            //5.VIP顾客辅导奖（200） 查找出当期升级成为vip的客户（且改客户不是孤儿） 根据vip客户判断其推荐人级别，以及推荐人的推荐人是否可以获得vip顾客辅导奖
            List<MemberQualification> newVipList=bonusService.findNewVip(periodCode);
            if(newVipList!=null&&newVipList.size()>0){
                for (MemberQualification vipMemberQualification : newVipList) {
                    String sponsorCode = vipMemberQualification.getSponsorCode();
                    if(sponsorCode!=null){
                        MemberQualification whereVipGet = bonusService.findBySponsorCode(sponsorCode, periodCode);
                        if(whereVipGet!=null){
                            getVip(whereVipGet);
                        }
                    }
                }
            }
            //6.查询当期公司总pv 公司总pv即当期所有用户ppv总和
            int sumPv=bonusService.findSumPv(periodCode);
            //查询当前周期拓展奖分红总基数
            int sumShare=bonusService.findSumShare(periodCode);
            if(sumShare==0){
                return ResultMsg.newInstance(false,"当前周期不存在或该周期会员资格信息未统计完成");
            }
            //拓展奖分红系数
            double coefficient=sumPv*0.18/sumShare;
            BigDecimal coefficient1 = new BigDecimal(Double.toString(coefficient));
            //   根据周期号查询出每一个客户信息，将拓展奖奖项求和，领导奖奖项求和，以及总奖励求和
            List<RdBonusMaster> rdBonusMasterList= bonusService.selectAll(periodCode);
            if(rdBonusMasterList!=null&&rdBonusMasterList.size()>0){
                for (RdBonusMaster rdBonusMaster : rdBonusMasterList) {
                    BigDecimal bonusDevpShare = rdBonusMaster.getBonusDevpShare();
                    bonusDevpShare = bonusDevpShare.multiply(coefficient1);
                    BigDecimal bonusDevpDum = rdBonusMaster.getBonusDevp1().add(rdBonusMaster.getBonusDevp2()).add(bonusDevpShare);
                    BigDecimal bonusLdSum=rdBonusMaster.getBonusLdDirect().subtract(rdBonusMaster.getBonusLdIndirect()).add(rdBonusMaster.getBonusLdSupport());
                    BigDecimal bonusSum=bonusDevpDum.add(bonusLdSum).subtract(rdBonusMaster.getChargeService());
                    bonusService.updateBonusSum(bonusDevpShare,bonusDevpDum,bonusLdSum,bonusSum,rdBonusMaster);
                }
            }
            return ResultMsg.newInstance(true,periodCode+"期奖金奖已核对完毕，请等待审核");
        }
        return ResultMsg.newInstance(false,"当前周期不存在或该周期会员资格信息未统计完成");
    }

    /**
     * 根据当前所处层次以及周期计算该层所产生的领导奖，包括直接领导奖，间接领导奖和同级领导奖
     * @param num
     * @param periodCode
     */
    private void getBonusLD(int num,String periodCode){
        //找出period周期，num层里面所有的等级>=7的客户
        List<MemberQualification> list=bonusService.findRankGE7(num,periodCode);
        if(list!=null&&list.size()>0){
            for (MemberQualification memberQualification : list) {
                //对应于每一层的每一个符合条件的客户节点计算其领导奖（或者其对应于其他节点产生的影响，比如间接领导奖，同级领导奖）
                int rank = memberQualification.getRank();
                int gpvFlagship = memberQualification.getGpvFlagship();
                double bonusLDDirectD=0;
                Integer orphan = memberQualification.getOrphan();
                //获得节点的rank等级，判断节点具体获得领导奖的比例
                if(rank==7){
                    //修改数据库中奖金主表直接领导奖
                    bonusLDDirectD=gpvFlagship*2/100;
                    BigDecimal bonusLDDirect = new BigDecimal(Double.toString(bonusLDDirectD));
                    bonusService.updateBonusLDDirect(bonusLDDirect,memberQualification.getPeriodCode(),memberQualification.getMCode());
                }
                if(rank==8){
                    bonusLDDirectD=gpvFlagship*3/100;
                    BigDecimal bonusLDDirect = new BigDecimal(Double.toString(bonusLDDirectD));
                    bonusService.updateBonusLDDirect(bonusLDDirect,memberQualification.getPeriodCode(),memberQualification.getMCode());
                }
                if(rank==9){
                    bonusLDDirectD=gpvFlagship*4/100;
                    BigDecimal bonusLDDirect = new BigDecimal(Double.toString(bonusLDDirectD));
                    bonusService.updateBonusLDDirect(bonusLDDirect,memberQualification.getPeriodCode(),memberQualification.getMCode());
                }
                //判断节点是否为孤儿，如果为孤儿，不会对上层节点产生间接领导奖以及领导奖同级奖
                if(orphan==1){//不是孤儿 往上计算领导奖间接奖以及领导同级奖
                    BigDecimal bonusLDDirect = new BigDecimal(Double.toString(bonusLDDirectD));
                    getBonusLDIndirect(memberQualification,bonusLDDirect);//TODO
                    getBonusLDSupport(memberQualification,gpvFlagship,rank,0);
                }
            }
        }
    }

    /**
     * 计算当前客户对应产生的领导奖同级奖
     * @param memberQualification
     * @param gpvFlagship
     */
    private void getBonusLDSupport(MemberQualification memberQualification,int gpvFlagship,int rank,int count){
        if(rank==7||rank==8){//如果当前节点的等级为7或者8，需要往上找一层领导奖同级奖
            String sponsorCode = memberQualification.getSponsorCode();
            if(sponsorCode!=null){
                MemberQualification memberQualification1 = bonusService.findBySponsorCode(sponsorCode, memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    int rank1 = memberQualification1.getRank();
                    if(rank==rank1){
                        BigDecimal bonusLDSupport = new BigDecimal(Double.toString(gpvFlagship * 1 / 100));
                        bonusService.updateBonusLDSupport(bonusLDSupport,memberQualification1.getPeriodCode(),memberQualification1.getMCode());
                    }
                    if(rank>rank1){
                        getBonusLDSupport(memberQualification1,gpvFlagship,rank,count);
                    }
                    if (rank<rank1){
                        return;
                    }
                }
            }
        }
        if(rank==9){//如果当前节点的等级为9，需要往上找两层领导奖同级奖
            if(count<2){
                String sponsorCode = memberQualification.getSponsorCode();
                if(sponsorCode!=null){
                    MemberQualification memberQualification1 = bonusService.findBySponsorCode(sponsorCode, memberQualification.getPeriodCode());
                    if(memberQualification1!=null){
                        int rank1 = memberQualification1.getRank();
                        if(rank==rank1){
                            BigDecimal bonusLDSupport = new BigDecimal(Double.toString(gpvFlagship * 1 / 100));
                            bonusService.updateBonusLDSupport(bonusLDSupport,memberQualification1.getPeriodCode(),memberQualification1.getMCode());
                            //找到了第一个符合领导奖同级奖的节点  根据这个节点继续往上找第二个领导奖同级奖的节点
                            count=count+1;//使用一个int值count来确保只找两层 否则会陷入递归死循环
                            getBonusLDSupport(memberQualification1,gpvFlagship,rank,count);
                        }
                        if(rank>rank1){
                            getBonusLDSupport(memberQualification1,gpvFlagship,rank,count);
                        }
                        if (rank<rank1){
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * 计算当前客户对应产生的间接领导奖
     * @param memberQualification
     * @param bonusLDDirect
     */
    private void getBonusLDIndirect(MemberQualification memberQualification,BigDecimal bonusLDDirect){//TODO
        int rank = memberQualification.getRank();
        String sponsorCode = memberQualification.getSponsorCode();
        if(sponsorCode!=null){
            MemberQualification memberQualification1 = bonusService.findBySponsorCode(sponsorCode, memberQualification.getPeriodCode());
            if(memberQualification1!=null){
                int rank1 = memberQualification1.getRank();
                if(rank<rank1){
                    BigDecimal bonusLDIndirect=bonusLDDirect;//TODO ???BigDecimal是否可以通过这种形式赋值
                    bonusService.updateBonusLDIndirect(bonusLDIndirect,memberQualification1.getPeriodCode(),memberQualification1.getMCode());
                }
                else {
                    getBonusLDIndirect(memberQualification1,bonusLDDirect);
                }
            }
        }
        return;
    }

    /**
     * 根据当前所处层次以及周期计算该层次所产生的所有的一代拓展奖
     * @param num
     * @param periodCode
     */
    private void getBonusDevp1(int num,String periodCode){
        //从num层开始，找出一层里面符合计算拓展奖条件的节点（ppv>0  且不是孤儿）
        List<MemberQualification> layerList=bonusService.findByLayer(num,periodCode);
        if(layerList!=null&&layerList.size()>0){
            //遍历符合条件的最底层的会员
            for (MemberQualification memberQualification : layerList) {
                String sponsorCode = memberQualification.getSponsorCode();
                MemberQualification lastMemberQualification =bonusService.findBySponsorCode(sponsorCode,periodCode);
                if(lastMemberQualification!=null){
                    int ppv = memberQualification.getPpv();
                    judgeQualified(lastMemberQualification,ppv);
                }
            }
        }
    }

    /**
     *
     * 根据当前所处层次以及周期计算该层所产生的市场拓展奖分红
     * @param num
     * @param periodCode
     */
    private void getBonusDevpShare(int num,String periodCode){
        List<MemberQualification> layerList=bonusService.findByLayer(num,periodCode);
        if(layerList!=null&&layerList.size()>0){
            for (MemberQualification memberQualification : layerList) {
                int ppv=memberQualification.getPpv();
                BigDecimal add=new BigDecimal(Double.toString(ppv*5/100));
                String sponsorCode = memberQualification.getSponsorCode();
                if(sponsorCode!=null){
                    MemberQualification lastMemberQualification = bonusService.findBySponsorCode(sponsorCode, periodCode);//寻找当前节点的推荐人
                    if(lastMemberQualification==null){
                        continue;
                    }
                    MemberQualification memberQualification1 = makeTrueFirst(lastMemberQualification);//获得了一代拓展奖获得者
                    if(memberQualification1==null){
                        continue;
                    }
                    //根据一代拓展奖获得者查找二代拓展奖获得者，判断是否存在二代拓展奖获得者，如果存在，根据二代拓展奖获得者，往上找3-7代拓展奖分红获得者（memberQualification1为一代拓展奖获得者）
                    String sponsorCode1 = memberQualification1.getSponsorCode();
                    if(sponsorCode1==null){
                        continue;
                    }
                    MemberQualification memberQualification2 = bonusService.findBySponsorCode(sponsorCode1, periodCode);
                    if(memberQualification2==null){
                        continue;
                    }
                    MemberQualification memberQualification3 = makeTrueSecond(memberQualification2);//memberQualification3 二代拓展奖获得者
                    if(memberQualification3==null){
                        continue;
                    }
                    //根据二代拓展奖获得者查询第三代拓展奖分红获得者，其获得分红的比例为5% 条件为rank>3并且当期业绩合格
                    String sponsorCode2 = memberQualification3.getSponsorCode();
                    if(sponsorCode2==null){
                        continue;
                    }
                    MemberQualification memberQualification4 = bonusService.findBySponsorCode(sponsorCode2, periodCode);
                    if(memberQualification4==null){//TODO
                        continue;
                    }
                    MemberQualification memberQualification5 = makeTrueShare3(memberQualification4);
                    if(memberQualification5==null){//memberQualification5为三代拓展奖获得者
                        continue;
                    }
                    //确认了三代拓展奖奖金分红的获得者，根据底层节点当期购买的ppv修改三代拓展奖奖金分红在数据库中的数据
                    bonusService.updateBonusDevpShare(add,memberQualification5.getMCode(),memberQualification5.getPeriodCode());
                    String sponsorCode3 = memberQualification5.getSponsorCode();
                    if(sponsorCode3==null){
                        continue;
                    }
                    MemberQualification memberQualification6 = bonusService.findBySponsorCode(sponsorCode3, periodCode);
                    if(memberQualification6==null){
                        continue;
                    }
                    MemberQualification memberQualification7 = makeTrueShare4(memberQualification6);
                    if(memberQualification7==null){//memberQualification7为四代拓展奖获得者
                        continue;
                    }
                    bonusService.updateBonusDevpShare(add,memberQualification7.getMCode(),memberQualification7.getPeriodCode());
                    String sponsorCode4 = memberQualification7.getSponsorCode();
                    if(sponsorCode4==null){
                        continue;
                    }
                    MemberQualification memberQualification8 = bonusService.findBySponsorCode(sponsorCode4, periodCode);
                    if(memberQualification8==null){
                        continue;
                    }
                    MemberQualification memberQualification9 = makeTrueShare5(memberQualification8);
                    if(memberQualification9==null){//memberQualification9为五代拓展奖获得者
                        continue;
                    }
                    bonusService.updateBonusDevpShare(add,memberQualification9.getMCode(),memberQualification9.getPeriodCode());
                    String sponsorCode5 = memberQualification9.getSponsorCode();
                    if(sponsorCode5==null){
                        continue;
                    }
                    MemberQualification memberQualification10 = bonusService.findBySponsorCode(sponsorCode5, periodCode);
                    if(memberQualification10==null){
                        continue;
                    }
                    MemberQualification memberQualification11 = makeTrueShare6(memberQualification10);
                    if(memberQualification11==null){//memberQualification11为六代拓展奖获得者
                        continue;
                    }
                    bonusService.updateBonusDevpShare(add,memberQualification11.getMCode(),memberQualification11.getPeriodCode());
                    String sponsorCode6 = memberQualification11.getSponsorCode();
                    if(sponsorCode6==null){
                        continue;
                    }
                    MemberQualification memberQualification12 = bonusService.findBySponsorCode(sponsorCode6, periodCode);
                    if(memberQualification12==null){
                        continue;
                    }
                    MemberQualification memberQualification13 = makeTrueShare7(memberQualification12);
                    if(memberQualification13==null){//memberQualification13为七代拓展奖获得者
                        continue;
                    }
                    bonusService.updateBonusDevpShare(add,memberQualification13.getMCode(),memberQualification13.getPeriodCode());
                }
            }
        }
    }

    /**
     * 根据当前所处层次以及周期计算该层次所产生的所有的二代拓展奖
     * @param num
     * @param periodCode
     */
    private void getBonusDevp2(int num,String periodCode){
        int layer=num;
        //从num层开始，找出一层里面符合计算拓展奖条件的节点（ppv>0  且不是孤儿）
        List<MemberQualification> layerList=bonusService.findByLayer(num,periodCode);
        if(layerList!=null&&layerList.size()>0){
            for (MemberQualification memberQualification : layerList) {
                int ppv=memberQualification.getPpv();
                String sponsorCode = memberQualification.getSponsorCode();
                if(sponsorCode!=null){
                    MemberQualification lastMemberQualification = bonusService.findBySponsorCode(sponsorCode, periodCode);//所处层次为layer-1  判断其是否符合一代拓展奖
                    //判断直接推荐人是否符合一代拓展奖条件，找到一代拓展奖获得者，以及其所在层次，在该层次的基础上，往上查找二代拓展奖获得者以及层次
                    MemberQualification memberQualification1 = makeTrueFirst(lastMemberQualification);//获得了一代拓展奖获得者
                    if(memberQualification1==null){
                        return;//如果一代拓展奖获得者都没有，那么肯定不存在二代拓展奖获得者
                    }
                    String sponsorCode1 = memberQualification1.getSponsorCode();
                    //判断一代拓展奖获得者是不是孤儿，如果他是孤儿，则必然不会有二代拓展奖以及拓展奖分红奖
                    if(sponsorCode1!=null){
                        MemberQualification memberQualification2 = bonusService.findBySponsorCode(sponsorCode1, periodCode);//判断memberQualification2是否符合获得二代拓展奖的资格
                        if(memberQualification2!=null){
                            judgeQualified2(memberQualification2,ppv);
                        }
                    }
                    else {
                        //说明一代拓展奖获得者是孤儿，不计算当前节点的二代拓展奖
                        return;
                    }
                }
            }
        }
    }

    /**
     * 确认符合条件的拓展奖一代
     * @param memberQualification
     * @return
     */
    private MemberQualification makeTrueFirst(MemberQualification memberQualification){
        if(memberQualification.getRank()>=2&&memberQualification.getPpvqualified()==1&&memberQualification.getLayer()>=1){
            return memberQualification;
        }
        else {
            String sponsorCode = memberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取一代拓展奖
            int layer = memberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification memberQualification1 =bonusService.findBySponsorCode(sponsorCode,memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    return makeTrueFirst(memberQualification1);
                }
            }
        }
        return null;
    }
    private void judgeQualified2(MemberQualification lastMemberQualification,int ppv) {
        if(lastMemberQualification.getRank()>=2&& lastMemberQualification.getPpvqualified() == 1&&lastMemberQualification.getLayer()>=1){
            BigDecimal add = new BigDecimal(Double.toString(ppv * 5 / 100));
            bonusService.updateBonusDevp2(add,lastMemberQualification.getMCode(),lastMemberQualification.getPeriodCode());
        }else {
            String sponsorCode = lastMemberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取二代拓展奖
            int layer = lastMemberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification MemberQualification =bonusService.findBySponsorCode(sponsorCode,lastMemberQualification.getPeriodCode());
                if(MemberQualification!=null){
                    judgeQualified2(MemberQualification,ppv);
                }
            }
        }
    }
    private void judgeQualified(MemberQualification lastMemberQualification,int ppv) {
        if (lastMemberQualification.getRank() >= 2 && lastMemberQualification.getPpvqualified() == 1&&lastMemberQualification.getLayer()>=1) {
            int ddRank2Number = lastMemberQualification.getDdRank2Number();
            double add = 0;
            if (ddRank2Number == 1) {
                //推荐人数=1，计算系数10%
                add = ppv * 10 / 100;
                BigDecimal bigDecimal = new BigDecimal(Double.toString(add));
                bonusService.updateBonusDevp1(bigDecimal, lastMemberQualification.getMCode(), lastMemberQualification.getPeriodCode());
            }
            if (ddRank2Number == 2 || ddRank2Number == 3) {
                add = ppv * 12 / 100;
                BigDecimal bigDecimal = new BigDecimal(Double.toString(add));
                bonusService.updateBonusDevp1(bigDecimal, lastMemberQualification.getMCode(), lastMemberQualification.getPeriodCode());
            }
            if (ddRank2Number == 4 || ddRank2Number == 5) {
                add = ppv * 14 / 100;
                BigDecimal bigDecimal = new BigDecimal(Double.toString(add));
                bonusService.updateBonusDevp1(bigDecimal, lastMemberQualification.getMCode(), lastMemberQualification.getPeriodCode());
            }
            if (ddRank2Number == 6 || ddRank2Number == 7) {
                add = ppv * 16 / 100;
                BigDecimal bigDecimal = new BigDecimal(Double.toString(add));
                bonusService.updateBonusDevp1(bigDecimal, lastMemberQualification.getMCode(), lastMemberQualification.getPeriodCode());
            }
            if (ddRank2Number == 8 || ddRank2Number == 9) {
                add = ppv * 18 / 100;
                BigDecimal bigDecimal = new BigDecimal(Double.toString(add));
                bonusService.updateBonusDevp1(bigDecimal, lastMemberQualification.getMCode(), lastMemberQualification.getPeriodCode());
            }
            if (ddRank2Number >= 10) {
                add = ppv * 20 / 100;
                BigDecimal bigDecimal = new BigDecimal(Double.toString(add));
                bonusService.updateBonusDevp1(bigDecimal, lastMemberQualification.getMCode(), lastMemberQualification.getPeriodCode());
            }
            if (ddRank2Number <= 0) {
                BigDecimal bigDecimal = new BigDecimal(Double.toString(add));
                bonusService.updateBonusDevp1(bigDecimal, lastMemberQualification.getMCode(), lastMemberQualification.getPeriodCode());
            }
        }
        else {
            String sponsorCode = lastMemberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取一代拓展奖
            int layer = lastMemberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification MemberQualification =bonusService.findBySponsorCode(sponsorCode,lastMemberQualification.getPeriodCode());
                if(MemberQualification!=null){
                    judgeQualified(MemberQualification,ppv);
                }
            }
        }
    }

    /**
     * 确认符合条件的拓展奖二代
     * @param memberQualification
     * @return
     */
    private MemberQualification makeTrueSecond(MemberQualification memberQualification){
        if(memberQualification.getRank()>=2&&memberQualification.getPpvqualified()==1&&memberQualification.getLayer()>=1){
            return memberQualification;
        }
        else {
            String sponsorCode = memberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取二代拓展奖
            int layer = memberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification memberQualification1 =bonusService.findBySponsorCode(sponsorCode,memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    return makeTrueSecond(memberQualification1);
                }
            }
            return null;
        }
    }

    /**
     * 确认符合条件的拓展奖分红第三层
     * @param memberQualification
     * @return
     */
    private MemberQualification makeTrueShare3(MemberQualification memberQualification){
        if(memberQualification.getRank()>=3&&memberQualification.getPpvqualified()==1&&memberQualification.getLayer()>=1){
            return memberQualification;
        }
        else {
            String sponsorCode = memberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取二代拓展奖
            int layer = memberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification memberQualification1 =bonusService.findBySponsorCode(sponsorCode,memberQualification.getPeriodCode());
                if (memberQualification1!=null){
                    return makeTrueShare3(memberQualification1);
                }
            }
            return null;
        }
    }

    /**
     * 确认符合条件的拓展奖分红第四层
     * @param memberQualification
     * @return
     */
    private MemberQualification makeTrueShare4(MemberQualification memberQualification){
        if(memberQualification.getRank()>=3&&memberQualification.getPpvqualified()==1&&memberQualification.getLayer()>=1){
            return memberQualification;
        }
        else {
            String sponsorCode = memberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取二代拓展奖
            int layer = memberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification memberQualification1 =bonusService.findBySponsorCode(sponsorCode,memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    return makeTrueShare4(memberQualification1);
                }
            }
            return null;
        }
    }

    /**
     * 确认符合条件的拓展奖分红第五层
     * @param memberQualification
     * @return
     */
    private MemberQualification makeTrueShare5(MemberQualification memberQualification){
        if(memberQualification.getRank()>=4&&memberQualification.getPpvqualified()==1&&memberQualification.getLayer()>=1){
            return memberQualification;
        }
        else {
            String sponsorCode = memberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取二代拓展奖
            int layer = memberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification memberQualification1 =bonusService.findBySponsorCode(sponsorCode,memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    return makeTrueShare5(memberQualification1);
                }
            }
            return null;
        }
    }

    /**
     * 确认符合条件的拓展奖分红第六层
     * @param memberQualification
     * @return
     */
    private MemberQualification makeTrueShare6(MemberQualification memberQualification){
        if(memberQualification.getRank()>=5&&memberQualification.getPpvqualified()==1&&memberQualification.getLayer()>=1){
            return memberQualification;
        }
        else {
            String sponsorCode = memberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取二代拓展奖
            int layer = memberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification memberQualification1 =bonusService.findBySponsorCode(sponsorCode,memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    return makeTrueShare6(memberQualification1);
                }
            }
            return null;
        }
    }

    /**
     * 确认符合条件的拓展奖分红第七层
     * @param memberQualification
     * @return
     */
    private MemberQualification makeTrueShare7(MemberQualification memberQualification){
        if(memberQualification.getRank()>=6&&memberQualification.getPpvqualified()==1&&memberQualification.getLayer()>=1){
            return memberQualification;
        }
        else {
            String sponsorCode = memberQualification.getSponsorCode();
            //如果所处层次已经0层即公司节点了，不再获取二代拓展奖
            int layer = memberQualification.getLayer();
            if(sponsorCode!=null&&layer>=1){
                MemberQualification memberQualification1 =bonusService.findBySponsorCode(sponsorCode,memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    return makeTrueShare7(memberQualification1);
                }
            }
            return null;
        }
    }

    /**
     * 计算vip辅导奖
     * @param memberQualification
     */
    private void getVip(MemberQualification memberQualification){
        int rank = memberQualification.getRank();
        if(rank==1){//如果推荐人是vip级别客户 则vip客户奖励100元
            BigDecimal add = new BigDecimal(Double.toString(100));
            bonusService.updateBonusNewVip(add,memberQualification.getMCode(),memberQualification.getPeriodCode());
            String sponsorCode = memberQualification.getSponsorCode();
            if(sponsorCode!=null){
                MemberQualification memberQualification1 = bonusService.findBySponsorCode(sponsorCode, memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    int rank1 = memberQualification1.getRank();
                    if(rank1<=1){
                        return;
                    }
                    if(rank==2){
                        BigDecimal add2 = new BigDecimal(Double.toString(50));
                        bonusService.updateBonusNewVip(add2,memberQualification1.getMCode(),memberQualification1.getPeriodCode());
                        String sponsorCode1 = memberQualification1.getSponsorCode();
                        if(sponsorCode1!=null){
                            MemberQualification memberQualification2 = bonusService.findBySponsorCode(sponsorCode1, memberQualification1.getPeriodCode());
                            int rank2 = memberQualification2.getRank();
                            if(rank2>2){
                                BigDecimal add3 = new BigDecimal(Double.toString(50));
                                bonusService.updateBonusNewVip(add3,memberQualification2.getMCode(),memberQualification2.getPeriodCode());
                            }
                        }
                    }
                    if(rank>=3){
                        BigDecimal add2 = new BigDecimal(Double.toString(100));
                        bonusService.updateBonusNewVip(add2,memberQualification1.getMCode(),memberQualification1.getPeriodCode());
                    }
                }
            }
        }
        if(rank==2){
            BigDecimal add = new BigDecimal(Double.toString(150));
            bonusService.updateBonusNewVip(add,memberQualification.getMCode(),memberQualification.getPeriodCode());
            String sponsorCode = memberQualification.getSponsorCode();
            if(sponsorCode!=null){
                MemberQualification memberQualification1 = bonusService.findBySponsorCode(sponsorCode, memberQualification.getPeriodCode());
                if(memberQualification1!=null){
                    int rank1 = memberQualification1.getRank();
                    if(rank1>=3){
                        BigDecimal add2 = new BigDecimal(Double.toString(50));
                        bonusService.updateBonusNewVip(add2,memberQualification1.getMCode(),memberQualification1.getPeriodCode());
                    }
                }
            }
        }
        if(rank>=3){
            BigDecimal add = new BigDecimal(Double.toString(200));
            bonusService.updateBonusNewVip(add,memberQualification.getMCode(),memberQualification.getPeriodCode());
        }
    }

    /**
     * 奖金结果审核
     */
    @RequestMapping(value = "/auditBonus",method =RequestMethod.GET )
    public ResultMsg auditBonus(@RequestParam(required = true,value = "periodCode")String periodCode){
        //根据周期查询RdBonusAudit是否有当前周期对应的奖金结果审核表，如果没有，根据周期查询业绩表以及奖金表，初始化奖金审核表
        if(periodCode==null||"".equals(periodCode.trim())){
            return ResultMsg.newInstance(false,"请选择正确的周期编号");
        }
        List<RdBonusAudit> passedList=bonusService.findAuditPassed(periodCode);
        if(passedList!=null&&passedList.size()>0){
            List<RdBonusAudit> list=bonusService.findBonusAuditAll(periodCode);
            ResultMsg<List<RdBonusAudit>> resultMsg = new ResultMsg<>();
            resultMsg.setCode(false);
            resultMsg.setData(list);
            resultMsg.setMsg(periodCode+"周期已经通过奖金审核，请勿重复操作");
            return resultMsg;
        }
        //RdBonusAudit rdBonusAudit=bonusService.findAuditByPeriodCode(periodCode);
        //查询出对应周期未审核状态的RdBonusAudit数据   如果有未审核的RdBonusAudit数据，不允许再次提交审核
        List<RdBonusAudit> rdBonusAuditList=bonusService.findAuditByPeriodCodeAndStatus(periodCode);
        if(rdBonusAuditList!=null&&rdBonusAuditList.size()>0){
            List<RdBonusAudit> list=bonusService.findBonusAuditAll(periodCode);
            ResultMsg<List<RdBonusAudit>> resultMsg = new ResultMsg<>();
            resultMsg.setCode(false);
            resultMsg.setData(list);
            resultMsg.setMsg(periodCode+"周期尚有未审核通过的数据，请完成审核后再进行提交审核操作");
            return resultMsg;
        }
            //如果当前周期的rdBonusAudit为空，根据当前周期的奖金表，初始化RdBonusAudit表数据内容
            int customerNum=bonusService.findCustomerNum(periodCode);
            BigDecimal performance=bonusService.findPerformance(periodCode);
            BigDecimal bonusNewVip= bonusService.findBonusNewVip(periodCode);
            BigDecimal bonusRetail=bonusService.findBonusRetail(periodCode);
            BigDecimal bonusDevp=bonusService.findBonusDevp(periodCode);
            BigDecimal bonusLd=bonusService.findBonusLd(periodCode);
            //总奖金=总业绩*18%   performance*18%
            BigDecimal bigDecimal = new BigDecimal(Double.toString(0.18));
            BigDecimal bounsSumPv = performance.multiply(bigDecimal);
            //拓展奖占比
            BigDecimal bonusDevpPercentage=bonusDevp.divide(bounsSumPv,2).multiply(new BigDecimal(Integer.toString(100)));
            //领导奖占比
            BigDecimal bonusLdPercentage = bonusLd.divide(bounsSumPv, 2).multiply(new BigDecimal(Integer.toString(100)));
            //特别奖为预留拓展字段 默认设置为0
            BigDecimal bonusSpecial = new BigDecimal("0.00");
            BigDecimal bonusSpecialPercentage=bonusSpecial.divide(bounsSumPv, 2).multiply(new BigDecimal(Integer.toString(100)));
            BigDecimal add = bonusDevp.add(bonusLd).add(new BigDecimal("0.00"));
            BigDecimal allocatePercentage = add.divide(bounsSumPv, 2).multiply(new BigDecimal("100"));
            String rate= cn.leanshi.model.util.Constant.PV_CNY_RATE;
            BigDecimal bigDecimal1 = new BigDecimal(rate);
            BigDecimal bounsSumCurrency=bounsSumPv.multiply(bigDecimal1);
            RdBonusAudit rdBonusAuditNew = new RdBonusAudit();
            rdBonusAuditNew.setPeriodCode(periodCode);
            rdBonusAuditNew.setCustomerNum(customerNum);
            //当前总订单数与前台系统交互，预留//TODO
            rdBonusAuditNew.setOrderNum(0);
            rdBonusAuditNew.setPerformance(performance);
            rdBonusAuditNew.setBonusNewVip(bonusNewVip);
            rdBonusAuditNew.setBonusRetail(bonusRetail);
            rdBonusAuditNew.setBonusDevp(bonusDevp);
            rdBonusAuditNew.setBonusDevpPercentage(bonusDevpPercentage);
            rdBonusAuditNew.setBonusLd(bonusLd);
            rdBonusAuditNew.setBonusLdPercentage(bonusLdPercentage);
            rdBonusAuditNew.setBonusSpecial(bonusSpecial);
            rdBonusAuditNew.setBonusSpecialPercentage(bonusSpecialPercentage);
            rdBonusAuditNew.setBounsSumPv(bounsSumPv);
            rdBonusAuditNew.setAllocatePercentage(allocatePercentage);
            rdBonusAuditNew.setCurrencyCode(cn.leanshi.model.util.Constant.CURRENCY_CODE_CHINA);
            rdBonusAuditNew.setBounsSumCurrency(bounsSumCurrency);
            //设置审核人

            rdBonusAuditNew.setStaus(cn.leanshi.model.util.Constant.STATUS_NOT_BEGAIN);
            rdBonusAuditNew.setAuditTime(null);
            bonusService.insertRdBonusAudit(rdBonusAuditNew);
        List<RdBonusAudit> list=bonusService.findBonusAuditAll(periodCode);
        ResultMsg<List<RdBonusAudit>> resultMsg = new ResultMsg<>();
        resultMsg.setCode(true);
        resultMsg.setData(list);
        return resultMsg;
    }

    /**
     * 根据周期，对已经通过奖金发放比例的周期进行会员欠款明细表的记录以及奖金发放表生成
     * @param periodCode
     * @return
     */
    @RequestMapping(value = "/advanceDeductions",method =RequestMethod.GET)
    public ResultMsg advanceDeductions(@RequestParam(value = "periodCode",required = true)String periodCode){
        if(periodCode==null||"".equals(periodCode.trim())){
            return ResultMsg.newInstance(false,"请选择正确的周期编号");
        }

        List<RdBonusMaster> list=bonusService.searchByPeriodCode(periodCode);
        if(list==null||list.size()==0){
            return ResultMsg.newInstance(false,periodCode+"周期奖金表尚未统计完成");
        }
        for (RdBonusMaster rdBonusMaster : list) {
            String mCode = rdBonusMaster.getmCode();
            RdReceivableMaster rdReceivableMaster=bonusService.findRdReceivableMaster(mCode);
            RdReceivableDetail rdReceivableDetail=bonusService.findRdReceivableDetailLast(mCode);
            if(rdReceivableMaster==null){
                continue;
            }
            if(rdReceivableDetail==null){
                continue;
            }
            String transPeriod = rdReceivableDetail.getTransPeriod();
            //如果最新一期的会员欠款明细和传入周期一致，说明该会员在该周期的奖金以及欠款表扣款已经完成，不需要再次进行操作欠款表明细表
            if(transPeriod.equals(periodCode.trim())){
                continue;
            }
            //初始化本期欠款明细表
            RdReceivableDetail rdReceivableDetail1 = new RdReceivableDetail();
            //生成交易流水号，根据当前时间精确到秒，再加上一个三位的递增数值
            rdReceivableDetail1.setTransNumber((int) new Date().getTime());
            rdReceivableDetail1.setBatchNumber(rdReceivableDetail.getBatchNumber()+1);
            rdReceivableDetail1.setMCode(rdReceivableDetail.getMCode());
            rdReceivableDetail1.setTrTypeCode("RR");
            rdReceivableDetail1.setTrSourceType("SBB");
            rdReceivableDetail1.setCurrencyCode(cn.leanshi.model.util.Constant.CURRENCY_CODE_CHINA);
            rdReceivableDetail1.setBlanceBefore(rdReceivableDetail.getBlanceAfter());
            //交易金额计算，要查询当前用户欠款主表，看其奖金还款比例
            int bnsDeductPecent = rdReceivableMaster.getBnsDeductPecent();
            BigDecimal bigDecimal = new BigDecimal(Double.toString(bnsDeductPecent / 100));
            BigDecimal decimal = rdBonusMaster.getBonusSum().multiply(new BigDecimal(cn.leanshi.model.util.Constant.PV_CNY_RATE)).multiply(bigDecimal);//TODO 奖金主表BonusRetail为拓展字段，无法确定单位是pv还是人民币，在此以人民币计算.add(rdBonusMaster.getBonusRetail())
            rdReceivableDetail1.setAmount(decimal);
            rdReceivableDetail1.setBlanceAfter(rdReceivableDetail.getBlanceAfter().subtract(decimal));
            rdReceivableDetail1.setTransPeriod(rdReceivableDetail.getTransPeriod());
            rdReceivableDetail1.setStatus(1);
            rdReceivableDetail1.setCreationTime(new Date());
            //将初始化欠款明细对象插入数据库中
            bonusService.insertRdReceivableDetail(rdReceivableDetail1);
            //根据欠款表以及奖金表数据，计算奖金发放表
            RdBonusPayment rdBonusPayment = new RdBonusPayment();
            rdBonusPayment.setPeriodCode(periodCode);
            rdBonusPayment.setmCode(rdReceivableDetail.getMCode());
            String nickname=bonusService.findMemberNickNameByMcode(rdReceivableDetail.getMCode());
            rdBonusPayment.setmNickName(nickname);
            rdBonusPayment.setBonusSum(rdBonusMaster.getBonusSum());
            rdBonusPayment.setCurrencyCode(cn.leanshi.model.util.Constant.CURRENCY_CODE_CHINA);
            rdBonusPayment.setBonusSumMoney(rdBonusMaster.getBonusSum().multiply(new BigDecimal(cn.leanshi.model.util.Constant.PV_CNY_RATE)));
            rdBonusPayment.setBonusReissue(new BigDecimal("0.00"));
            rdBonusPayment.setChargeSum(rdReceivableDetail1.getAmount());
            rdBonusPayment.setPayableSum(rdBonusMaster.getBonusSum().multiply(new BigDecimal(cn.leanshi.model.util.Constant.PV_CNY_RATE)).add(new BigDecimal("0.00")).subtract(rdReceivableDetail1.getAmount()));
            rdBonusPayment.setPayStatus(0);
            bonusService.insertRdBonusPayment(rdBonusPayment);
        }
        //修改业务周期表CAL_STATUS为2：临时发布核对中
        bonusService.updateCalStatusByPeriodCode(periodCode);
        RdBonusPaymentStatistical rdBonusPaymentStatistical = new RdBonusPaymentStatistical();
        rdBonusPaymentStatistical.setPeriodCode(periodCode);
        int newNum=bonusService.selectNewMember(periodCode);
        rdBonusPaymentStatistical.setNewNum(newNum);
        int customerNum=bonusService.findCountPpvGtZero(periodCode);
        rdBonusPaymentStatistical.setCustomerNum(customerNum);
        BigDecimal bounsSumPv=bonusService.findSumBouns(periodCode);
        rdBonusPaymentStatistical.setBounsSumPv(bounsSumPv);
        rdBonusPaymentStatistical.setBonusSumMoney(bounsSumPv.multiply(new BigDecimal(cn.leanshi.model.util.Constant.PV_CNY_RATE)));
        BigDecimal bounsReissue=bonusService.findSumBounsReissue(periodCode);
        rdBonusPaymentStatistical.setBonusReissue(bounsReissue);
        BigDecimal chargeSum=bonusService.findSumChargeSum(periodCode);
        rdBonusPaymentStatistical.setChargeSum(chargeSum);
        BigDecimal payableSum=bonusService.findSumPayableSum(periodCode);
        rdBonusPaymentStatistical.setPayableSum(payableSum);

        RdBonusAudit rdBonusAudit=bonusService.findAuditByPeriodCodeAndStatysAndLastTime(periodCode);
        if(rdBonusAudit==null){
            System.out.println("奖金主表审核未通过，不存在奖金计算表");
        }
        rdBonusPaymentStatistical.setBonusNewVip(rdBonusAudit.getBonusNewVip());
        rdBonusPaymentStatistical.setBonusRetail(rdBonusAudit.getBonusRetail());
        rdBonusPaymentStatistical.setBonusDevp(rdBonusAudit.getBonusDevp());
        rdBonusPaymentStatistical.setBonusDevpPercentage(rdBonusAudit.getBonusDevpPercentage());
        rdBonusPaymentStatistical.setBonusLD(rdBonusAudit.getBonusLd());
        rdBonusPaymentStatistical.setBonusLDPercentage(rdBonusAudit.getBonusLdPercentage());
        rdBonusPaymentStatistical.setBonusSpec(rdBonusAudit.getBonusSpecial());
        rdBonusPaymentStatistical.setBonusSpecPercentage(rdBonusAudit.getBonusSpecialPercentage());
        rdBonusPaymentStatistical.setBonusTotalPercentage(rdBonusAudit.getAllocatePercentage());
        rdBonusPaymentStatistical.setCurrencyCode(rdBonusAudit.getCurrencyCode());
        rdBonusPaymentStatistical.setPayStatus(0);
        bonusService.insertRdBonusPaymentStatistical(rdBonusPaymentStatistical);
        return ResultMsg.newInstance(true,"当前周期会员欠款明细表以及奖金发放表已生成，请等待审核");
    }


    @RequestMapping(value = "/viewBonusPaymentStatistical",method = RequestMethod.POST)
    public ResultMsg viewBonusPaymentStatistical(@RequestParam(required = false,value = "periodCodeLeft")String periodCodeLeft,
                                                 @RequestParam(required = false,value = "periodCodeRight")String periodCodeRight,
                                      @RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
                                      @RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize){
        int size=pageSize;
        PageHelper.startPage(currentPage,size);
        ResultMsg<PageInfo<RdBonusPaymentStatistical>> pageInfoResultMsg = new ResultMsg<>();
        List<RdBonusPaymentStatistical> list=bonusService.findRdBonusPaymentStatisticalByLR(periodCodeLeft,periodCodeRight);
        if(list==null||list.size()==0){
            return ResultMsg.newInstance(false,"没有指定周期区间的历史奖金发放表信息");
        }
        PageInfo<RdBonusPaymentStatistical> rdBonusPaymentPageInfo = new PageInfo<>(list);
        pageInfoResultMsg.setData(rdBonusPaymentPageInfo);
        pageInfoResultMsg.setCode(true);
        return pageInfoResultMsg;
    }

}
