package cn.leanshi.controller;

import cn.leanshi.model.*;
import cn.leanshi.model.http.ResultMsg;
import cn.leanshi.model.util.Constant;
import cn.leanshi.model.util.MD5Util;
import cn.leanshi.model.util.RegexUtil;
import cn.leanshi.service.BonusService;
import cn.leanshi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@ResponseBody
@RequestMapping("/memberAdd")
@RequiredArgsConstructor
public class MemberAddController {
    @Autowired
    private HttpSession session;

    private final MemberService memberService;
    private final BonusService bonusService;

    /**
     * 异步查询推荐人是否存在已经是否注销
     * @param mCode
     * @param mNickName
     * @return
     */
    @RequestMapping(value = "/findReferrer",method = RequestMethod.GET)
    public ResultMsg findReferrer(@RequestParam(value = "mCode",required = false)String mCode,
                                  @RequestParam(value = "mNickName",required = false)String mNickName){
        if(mCode!=null&&!"".equals(mCode.trim())&&mNickName!=null&&!"".equals(mNickName.trim())){
            Member_basic memberBasic=memberService.findMemberBasicByMcodeAndNickName(mCode,mNickName);
            if(memberBasic!=null){
                //判断会员是否已经注销
                MemberRelation memberRelation = memberService.findRelationByMCode(mCode);
                if(memberRelation!=null){
                    int mStatus = memberRelation.getMStatus();
                    if(mStatus==2){//说明该会员已经注销
                        return ResultMsg.newInstance(false,"推荐人已注销，无法选择!");
                    }
                }
                ResultMsg<Member_basic> resultMsg = new ResultMsg<>();
                resultMsg.setCode(true);
                resultMsg.setData(memberBasic);
                return resultMsg;
            }
            return ResultMsg.newInstance(false,"未搜索到相关推荐人，请确认后重试!");
        }
        if(mCode!=null&&!"".equals(mCode.trim())){
            Member_basic memberBasic = memberService.findByMCode(mCode);
            if(memberBasic!=null){
                //判断会员是否已经注销
                MemberRelation memberRelation = memberService.findRelationByMCode(mCode);
                if(memberRelation!=null){
                    int mStatus = memberRelation.getMStatus();
                    if(mStatus==2){//说明该会员已经注销
                        return ResultMsg.newInstance(false,"推荐人已注销，无法选择!");
                    }
                }
                ResultMsg<Member_basic> resultMsg = new ResultMsg<>();
                resultMsg.setCode(true);
                resultMsg.setData(memberBasic);
                return resultMsg;
            }
            return ResultMsg.newInstance(false,"未搜索到相关推荐人，请确认后重试!");
        }
        if(mNickName!=null&&!"".equals(mNickName.trim())){
            Member_basic memberBasic =memberService.findByNickName(mNickName);
            if(memberBasic!=null){
                //判断会员是否已经注销
                MemberRelation memberRelation = memberService.findRelationByMCode(mCode);
                if(memberRelation!=null){
                    int mStatus = memberRelation.getMStatus();
                    if(mStatus==2){//说明该会员已经注销
                        return ResultMsg.newInstance(false,"推荐人已注销，无法选择!");
                    }
                }
                ResultMsg<Member_basic> resultMsg = new ResultMsg<>();
                resultMsg.setCode(true);
                resultMsg.setData(memberBasic);
                return resultMsg;
            }
            return ResultMsg.newInstance(false,"未搜索到相关推荐人，请确认后重试!");
        }
        return ResultMsg.newInstance(false,"请输入会员编号或者会员昵称中的至少一个进行查询");
    }

    /**
     * 异步查询昵称是否存在
     * @param mNickName
     * @return
     */
    @RequestMapping(value = "/checkNickName",method = RequestMethod.GET)
    public ResultMsg checkNickName(@RequestParam(value = "mNickName",required = true)String mNickName){
        if(mNickName==null||"".equals(mNickName.trim())){
            return ResultMsg.newInstance(false,"请输入正确的昵称查询");
        }
        Member_basic memberBasic = memberService.findByNickName(mNickName);
        if(memberBasic!=null){
            //根据昵称能查询到数据，说明该昵称已经被其他用户使用
            return ResultMsg.newInstance(false,"该昵称已经存在，请您选择其他昵称进行注册");
        }
        return ResultMsg.newInstance(true,"恭喜您，该昵称尚未被占用!");
    }

    /**
     * 异步查询证件信息是否存在
     * @param idType
     * @param idCode
     * @return
     */
    @RequestMapping(value = "/checkId",method = RequestMethod.GET)
    public ResultMsg checkId(@RequestParam(value = "idType",required = true)int idType,
                             @RequestParam(value = "idCode",required = true)String idCode){
        if(idType==1&&idCode!=null&&!"".equals(idCode.trim())){
            //证件类型为身份证，首先判断身份证位数以及格式是否正确，如果正确查询数据库看是否存在
            if(RegexUtil.isIDCard(idCode)){
                //查询数据库中是否已经存在该身份证号码
                Member_basic memberBasic=memberService.findMemberBasicByIdTypeAndIdCode(idType,idCode);
                if(memberBasic!=null){
                    return ResultMsg.newInstance(false,"该身份证已注册，如非本人操作注册，请联系客服人员");
                }
                return ResultMsg.newInstance(true,"该身份证信息可以使用");
            }
            return ResultMsg.newInstance(false,"请输入正确的身份证号码格式");
        }
        if(idType==2&&idCode!=null&&!"".equals(idCode.trim())){
            //证件类型为护照，判断护照格式是否正确，如果正确，查询是否存在
            if(RegexUtil.isPassPort(idCode)){
                Member_basic memberBasic=memberService.findMemberBasicByIdTypeAndIdCode(idType,idCode);
                if(memberBasic!=null){
                    return ResultMsg.newInstance(false,"该护照已注册，如非本人操作注册，请联系客服人员");
                }
                return ResultMsg.newInstance(true,"该护照信息可以使用");
            }
            return ResultMsg.newInstance(false,"请输入正确的护照格式");
        }
        if(idType==3&&idCode!=null&&!"".equals(idCode.trim())){
            //证件类型为军官证  判断格式预留
            Member_basic memberBasic=memberService.findMemberBasicByIdTypeAndIdCode(idType,idCode);
            if(memberBasic!=null){
                return ResultMsg.newInstance(false,"该军官证已注册，如非本人操作注册，请联系客服人员");
            }
            return ResultMsg.newInstance(true,"该军官证可以使用");
        }
        if(idType==4&&idCode!=null&&!"".equals(idCode.trim())){
            //证件类型为回乡证  判断格式预留
            Member_basic memberBasic=memberService.findMemberBasicByIdTypeAndIdCode(idType,idCode);
            if(memberBasic!=null){
                return ResultMsg.newInstance(false,"该回乡证已注册，如非本人操作注册，请联系客服人员");
            }
            return ResultMsg.newInstance(true,"该回乡证可以使用");
        }
        return ResultMsg.newInstance(false,"请提供证件类型以及对应的证件号码");
    }

    /**
     * 验证手机号码是否符合格式是否已经被使用
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/checkPhone",method = RequestMethod.GET)
    public ResultMsg checkPhone(@RequestParam(value = "mobile",required = true)String mobile){
        if(mobile==null||"".equals(mobile.trim())){
            return ResultMsg.newInstance(false,"请填入正确的手机号码");
        }
        if(RegexUtil.isPhoneNum(mobile)){
            Member_basic memberBasic=memberService.findMemberBasicByMobile(mobile);
            if(memberBasic!=null){
                return ResultMsg.newInstance(false,"该手机号码已经注册");
            }
            return ResultMsg.newInstance(true,"该手机号码可以使用");
        }
        return ResultMsg.newInstance(false,"请输入正确格式的手机号码");
    }
    @RequestMapping(value = "/addMember",method = RequestMethod.POST)
    public ResultMsg addMember(@RequestParam(value = "referrerCode",required = true)String referrerCode,
                               @RequestParam(value = "referrerNickName",required = true)String referrerNickName,
                               @RequestParam(value = "referrerName",required = true)String referrerName,
                               @RequestParam(value = "mName",required = true)String mName,
                               @RequestParam(value = "mNickName",required = true)String mNickName,
                               @RequestParam(value = "idType",required = true)int idType,
                               @RequestParam(value = "idCode",required = true)String idCode,
                               @RequestParam(value = "gender",required = true)int gender,
                               @RequestParam(value = "birthdate",required = false)Date birthdate,
                               @RequestParam(value = "mobile",required = true)String mobile,
                               @RequestParam(value = "province",required = false)String province,
                               @RequestParam(value = "city",required = false)String city,
                               @RequestParam(value = "country",required = false)String country,
                               @RequestParam(value = "detial",required = false)String detial,
                               @RequestParam(value = "addPost",required = false)String addPost,
                               @RequestParam(value = "email",required = false)String email,
                               @RequestParam(value = "weChat",required = false)String weChat,
                               @RequestParam(value = "qq",required = false)String qq,
                               @RequestParam(value = "bankCode",required = false)String bankCode,
                               @RequestParam(value = "accName",required = false)String accName,
                               @RequestParam(value = "accCode",required = false)String accCode,
                               @RequestParam(value = "goodsCode",required = true)String goodsCode,
                               @RequestParam(value = "goodsName",required = true)String goodsName,
                               @RequestParam(value = "number",required = true)int number,
                               @RequestParam(value = "priceRetail",required = true)BigDecimal priceRetail,
                               @RequestParam(value = "priceVip",required = true)BigDecimal priceVip,
                               @RequestParam(value = "totalMoney",required = true)BigDecimal totalMoney,
                               @RequestParam(value = "pvPrice",required = true)BigDecimal pvPrice,
                               @RequestParam(value = "totalPv",required = true)BigDecimal totalPv,
                               @RequestParam(value = "deliveryMethod",required = true)int deliveryMethod,
                               @RequestParam(value = "consigneeProvince",required = true)String consigneeProvince,
                               @RequestParam(value = "consigneeCity",required = true)String consigneeCity,
                               @RequestParam(value = "consigneeCountry",required = true)String consigneeCountry,
                               @RequestParam(value = "consigneeDetial",required = true)String consigneeDetial,
                               @RequestParam(value = "consigneeName",required = true)String consigneeName,
                               @RequestParam(value = "consigneeMobile",required = true)String consigneeMobile
                               ){
        //1.会员基础信息表添加  Member_basic
        Member_basic memberBasic = new Member_basic();
        //1.1查询出最新注册的一个会员的会员编号，根据该会员会员编号+1自增确定当前会员编号
        String mCode=memberService.findLastMemberMcode();
        if(mCode==null||"".equals(mCode.trim())){//说明还没有用户进行注册，默认会员编号初始值为80000000
            memberBasic.setMCode("80000000");
        }
        int newCode=Integer.parseInt(mCode)+1;
        memberBasic.setMCode(newCode+"");
        if(mName==null||"".equals(mName.trim())){
            return ResultMsg.newInstance(false,"请填写用户姓名");
        }
        memberBasic.setMName(mName);
        if(mNickName==null||"".equals(mNickName.trim())){
            return ResultMsg.newInstance(false,"请填写用户昵称");
        }
        memberBasic.setMNickname(mNickName);
        if(idType!=1&&idType!=2&&idType!=3&&idType!=4){
            return ResultMsg.newInstance(false,"请选择正确的证件类型");
        }
        memberBasic.setIdType(idType);
        if(idCode==null||"".equals(idCode.trim())){
            return ResultMsg.newInstance(false,"请填写正确的证件号码");
        }
        memberBasic.setIdCode(idCode);
        if(gender!=0&&gender!=1&&gender!=-1){
            memberBasic.setGender(-1);//没有选择性别默认保密
        }
        memberBasic.setGender(gender);
        if(birthdate==null||"".equals(birthdate.toString().trim())){
            memberBasic.setBirthdate(null);
        }
        memberBasic.setBirthdate(birthdate);
        if(mobile==null||"".equals(mobile.trim())){
            return ResultMsg.newInstance(false,"请填写正确的手机号码");
        }
        memberBasic.setMobile(mobile);
        memberBasic.setProvince(province);
        memberBasic.setCity(city);
        memberBasic.setCountry(country);
        memberBasic.setDetial(detial);
        memberBasic.setAddPost(addPost);
        memberBasic.setEmail(email);
        memberBasic.setWeChat(weChat);
        memberBasic.setQq(qq);
        memberBasic.setCreationData(new Date());
        int flag=memberService.addMember(memberBasic);
        if(flag!=1){
            return ResultMsg.newInstance(false,"添加会员失败，请检查填写信息后重试");
        }
        //2.会员地址信息添加   RD_MM_ADD_INFO  MemberAddress
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setMCode(memberBasic.getMCode());
        if(consigneeProvince==null||"".equals(consigneeProvince.trim())){
            return ResultMsg.newInstance(false,"收货人省份缺失");
        }
        memberAddress.setAddProvinceCode(consigneeProvince);
        if(consigneeCity==null||"".equals(consigneeCity.trim())){
            return ResultMsg.newInstance(false,"收货人城市缺失");
        }
        memberAddress.setAddCityCode(consigneeCity);
        if(consigneeCountry==null||"".equals(consigneeCountry.trim())){
            return ResultMsg.newInstance(false,"收货人区县缺失");
        }
        memberAddress.setAddCountryCode(consigneeCountry);
        if(consigneeDetial==null||"".equals(consigneeDetial.trim())){
            return ResultMsg.newInstance(false,"收货人详细地址缺失");
        }
        memberAddress.setAddDetial(consigneeDetial);
        if(consigneeName==null||"".equals(consigneeName.trim())){
            return ResultMsg.newInstance(false,"收货人姓名缺失");
        }
        memberAddress.setConsigneeName(consigneeName);
        if(consigneeMobile==null||"".equals(consigneeMobile.trim())){
            return ResultMsg.newInstance(false,"收货人联系电话缺失");
        }
        memberAddress.setMobile(consigneeMobile);
        memberAddress.setDefaultAdd(0);
        memberAddress.setValid(1);
        memberService.addMemAdd(memberAddress);
        //3会员状态级别及关系  RD_MM_RELATION   MemberRelation
        MemberRelation memberRelation = new MemberRelation();
        if(referrerCode==null||"".equals(referrerCode.trim())){
            return ResultMsg.newInstance(false,"请输入正确的推荐人会员编号");
        }
        memberRelation.setSponsorCode(referrerCode);
        if(referrerNickName==null||"".equals(referrerNickName.trim())){
            return ResultMsg.newInstance(false,"请输入正确的推荐人昵称");
        }
        if(referrerName==null||"".equals(referrerName.trim())){
            return ResultMsg.newInstance(false,"请输入正确的推荐人姓名");
        }
        memberRelation.setSponsorName(referrerName);
        memberRelation.setMCode(memberBasic.getMCode());
        memberRelation.setRank(0);
        memberRelation.setReTail(0);
        String password = MD5Util.MD5Encode("888888", "UTF-8");
        memberRelation.setLoginPwd(password);
        memberRelation.setPwdInitYn(0);
        memberRelation.setMStatus(3);//3：未激活    等待支付激活启动包订单修改状态
        memberRelation.setMPointStatus(2);
        memberService.addMemberRelation(memberRelation);
        //4.会员账户信息RD_MM_ACCOUNT_INFO  MemberAccount
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setMCode(memberBasic.getMCode());
        memberAccount.setBonusStatus(0);
        memberAccount.setBonusCurrency(Constant.CURRENCY_CODE_CHINA);
        memberAccount.setBonusBlance(0);
        memberAccount.setWalletStatus(0);
        memberAccount.setWalletCurrency(Constant.CURRENCY_CODE_CHINA);
        memberAccount.setWalletBlance(0);
        memberAccount.setRedemptionStatus(0);
        memberAccount.setRedemptionCurrency(Constant.CURRENCY_CODE_CHINA);
        memberAccount.setRedemptionBlance(0);
        memberAccount.setWeChatAcc(memberBasic.getWeChat());
        memberAccount.setPaymentPhone(memberBasic.getMobile());
        String payPassword = MD5Util.MD5Encode("888888", "UTF-8");
        memberAccount.setPaymentPWD(payPassword);
        memberService.addMemAccount(memberAccount);
        //5.会员银行卡信息生成RD_MM_BANK   MemberBank
        MemberBank memberBank = new MemberBank();
        memberBank.setMCode(memberBasic.getMCode());
        memberBank.setCurrency(Constant.CURRENCY_CODE_CHINA);
        if(accCode==null||"".equals(accCode.trim())){
            return ResultMsg.newInstance(false,"请输入正确的账户号码");
        }
        memberBank.setAccCode(accCode);
        if(accName==null||"".equals(accName.trim())){
            return ResultMsg.newInstance(false,"请输入正确的账户名");
        }
        memberBank.setAccName(accName);
        if(bankCode==null||"".equals(bankCode.trim())){
            return ResultMsg.newInstance(false,"请输入正确的开户行类型");
        }
        //对于银行判断预留，先将银行信息存入数据库
        memberBank.setBankCode(bankCode);
        memberBank.setDefaultBank(1);
        memberService.addMemBank(memberBank);
        //6会员信息修改记录表生成   MemberEditReview   当前期数据为注册时数据，上一期的数据为空白
        MemberEditReview memberEditReview = new MemberEditReview();
        memberEditReview.setMCode(memberBasic.getMCode());
        memberEditReview.setMName(memberBasic.getMName());
        memberEditReview.setIdType(memberBasic.getIdType());
        memberEditReview.setIdCode(memberBasic.getIdCode());
        memberEditReview.setMNickname(memberBasic.getMNickname());
        memberEditReview.setGender(memberBasic.getGender());
        memberEditReview.setMobile(memberBasic.getMobile());
        memberEditReview.setWeChat(memberBasic.getWeChat());
        memberEditReview.setEmail(memberBasic.getEmail());
        memberEditReview.setAddPost(memberBasic.getAddPost());
        return null;
    }
}
