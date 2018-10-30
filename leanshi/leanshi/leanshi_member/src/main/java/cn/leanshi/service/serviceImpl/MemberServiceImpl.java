package cn.leanshi.service.serviceImpl;

import cn.leanshi.mapper.MemberAccountMapper;
import cn.leanshi.mapper.MemberAddressMapper;
import cn.leanshi.mapper.MemberBankMapper;
import cn.leanshi.mapper.MemberEditReviewMapper;
import cn.leanshi.mapper.MemberIntegralRuleMapper;
import cn.leanshi.mapper.MemberMapper;
import cn.leanshi.mapper.MemberQualificationMapper;
import cn.leanshi.mapper.MemberRelationMapper;
import cn.leanshi.mapper.RankMapper;
import cn.leanshi.mapper.RdBonusMasterMapper;
import cn.leanshi.mapper.RdMemberAccountLogMapper;
import cn.leanshi.mapper.RdRaBindingMapper;
import cn.leanshi.mapper.RdReceivableDetailMapper;
import cn.leanshi.mapper.RdReceivableMasterMapper;
import cn.leanshi.mapper.RdStatusDetailMapper;
import cn.leanshi.mapper.SysPeriodLogMapper;
import cn.leanshi.mapper.SysPeriodMapper;
import cn.leanshi.model.MemberAccount;
import cn.leanshi.model.MemberAddress;
import cn.leanshi.model.MemberBank;
import cn.leanshi.model.MemberEditReview;
import cn.leanshi.model.MemberIntegralRule;
import cn.leanshi.model.MemberQualification;
import cn.leanshi.model.MemberRelation;
import cn.leanshi.model.Member_basic;
import cn.leanshi.model.Rank;
import cn.leanshi.model.RdBonusMaster;
import cn.leanshi.model.RdMemberAccountLog;
import cn.leanshi.model.RdRaBinding;
import cn.leanshi.model.RdReceivableDetail;
import cn.leanshi.model.RdReceivableMaster;
import cn.leanshi.model.RdStatusDetail;
import cn.leanshi.model.SysPeriod;
import cn.leanshi.model.SysPeriodLog;
import cn.leanshi.service.MemberService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author :ldq
 * @date:2018/7/30
 * @description:leanshi cn.leanshi.service.serviceImpl
 */
@RequiredArgsConstructor  //对所有带有@NonNull注解的或者带有final修饰的成员变量生成对应的构造方法
@Service
@Transactional
public class MemberServiceImpl implements MemberService {


	private final MemberMapper mapper;
	private final MemberBankMapper bankMapper;
	private final MemberEditReviewMapper editReviewMapper;
	private final MemberQualificationMapper qualificationMapper;
	private final MemberRelationMapper relationMapper;
	private final RdRaBindingMapper rdRaBindingMapper;
	private final SysPeriodMapper sysPeriodMapper;
	private final SysPeriodLogMapper sysPeriodLogMapper;
	private final RankMapper rankMapper;
	private final MemberAddressMapper addressMapper;
	private final MemberAccountMapper accountMapper;
	private final RdBonusMasterMapper masterMapper;
	private final RdReceivableMasterMapper receivableMapper;
	private final RdReceivableDetailMapper receivableDetailMapper;
	private final RdStatusDetailMapper statusDetailMapper;
	private final RdMemberAccountLogMapper accountLogMapper;
	private final MemberIntegralRuleMapper ruleMapper;

	/*
	 *条件模糊查询
	 * */
	@Override
	public List<Member_basic> search(String mCode,String mName,String mobile,String mNickname) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mName",mName);
		map.put("mobile",mobile);
		map.put("mNickname",mNickname);
		return mapper.selectBySearchMap(map);
	}

	/*
	 * 根据编号查找会员
	 * */
	@Override
	public Member_basic findByMCode(String mCode) {
		return mapper.selectByMCode(mCode);
	}

	/*
	 * 根据编和姓名修改会员基本信息
	 * */
	@Override
	public int updateByMCodeAndMName(Member_basic member,String mDesc) {

		MemberEditReview last = editReviewMapper.selectLastByMCode(member.getMCode());

		MemberEditReview editReview = new MemberEditReview();
		if(last!=null){

			if (last.getMCode().equals(member.getMCode())){
				editReview.setMCode(last.getMCode());
				editReview.setMNameBefore(last.getMName());
				editReview.setMName(last.getMName());
				editReview.setIdTypeBefore(last.getIdType());
				editReview.setIdType(last.getIdType());
				editReview.setIdCodeBefore(last.getIdCode());
				editReview.setIdCode(last.getIdCode());
				editReview.setMNicknameBefore(last.getMNickname());
				editReview.setMNickname(member.getMNickname());
				editReview.setGenderBefore(last.getGender());
				editReview.setGender(member.getGender());
				editReview.setMobileBefore(last.getMobile());
				editReview.setMobile(last.getMobile());
				editReview.setWeChatBefore(last.getWeChat());
				editReview.setWeChat(last.getWeChat());
				editReview.setEmailBefore(last.getEmail());
				editReview.setEmail(member.getEmail());
				editReview.setAddPostBefore(last.getAddPost());
				editReview.setAddPost(member.getAddPost());
				editReview.setProvinceBefore(last.getProvince());
				editReview.setProvince(member.getProvince());
				editReview.setCityBefore(last.getCity());
				editReview.setCity(member.getCity());
				editReview.setCountryBefore(last.getCountry());
				editReview.setCountry(member.getCountry());
				editReview.setDetialBefore(last.getDetial());
				editReview.setDetial(member.getDetial());
				editReview.setBankdetailBefore(last.getBankdetail());
				editReview.setBankdetail(last.getBankdetail());
				editReview.setAccNameBefore(last.getAccName());
				editReview.setAccName(last.getAccName());
				editReview.setAccCodeBefore(last.getAccCode());
				editReview.setAccCode(last.getAccCode());
				editReview.setWithdrawDefaultBefore(last.getWithdrawDefault());
				editReview.setWithdrawDefault(last.getWithdrawDefault());
				editReview.setSponsorCodeBefore(last.getSponsorCode());
				editReview.setSponsorCode(last.getSponsorCode());
				editReview.setSponsorNameBefore(last.getSponsorName());
				editReview.setSponsorName(last.getSponsorName());
				editReview.setRankBefore(last.getRank());
				editReview.setRank(last.getRank());
				editReview.setRaCodeBefore(last.getRaCode());
				editReview.setRaCode(last.getRaCode());
				editReview.setRaNameBefore(last.getRaName());
				editReview.setRaName(last.getRaName());
				editReview.setRaIdTypeBefore(last.getRaIdType());
				editReview.setRaIdType(last.getRaIdType());
				editReview.setRaIdCodeBefore(last.getRaIdCode());
				editReview.setRaIdCode(last.getRaIdCode());
				editReview.setUpdateBy("无名");
				editReview.setUpdateMemo(mDesc);
				editReview.setUpdateType(0);
				editReview.setUpdateTime(new Date());
				editReview.setReviewMemo("");
				editReview.setReviewStatus(3); //无需审核

				int i = editReviewMapper.saveEdit(editReview);
			}


			return mapper.updateByMCodeAndMName(member);

		}else{
			return 0;//修改失败，找不到该会员信息
		}
	}

	/*
	 * 根据编号查找会员银行信息
	 * */
	@Override
	public List<MemberBank> findMBankByMCode(String mCode) {
		return bankMapper.findMBankByMCode(mCode);
	}

	/*
	 * 根据编号和姓名修改银行卡信息
	 * */
	@Override
	public int updateByMBank(String mCode,String mName,String mobile,String mNickname,String withdrawDefault,String mDesc) {

		MemberEditReview last = editReviewMapper.selectLastByMCode(mCode);

		MemberEditReview editReview = new MemberEditReview();
		if(last!=null){
			if (last.getMCode().equals(mCode)) {
				editReview.setMCode(last.getMCode());
				editReview.setMNameBefore(last.getMName());
				editReview.setMName(last.getMName());
				editReview.setIdTypeBefore(last.getIdType());
				editReview.setIdType(last.getIdType());
				editReview.setIdCodeBefore(last.getIdCode());
				editReview.setIdCode(last.getIdCode());
				editReview.setMNicknameBefore(last.getMNickname());
				editReview.setMNickname(mNickname);
				editReview.setGenderBefore(last.getGender());
				editReview.setGender(last.getGender());
				editReview.setMobileBefore(last.getMobile());
				editReview.setMobile(mobile);
				editReview.setWeChatBefore(last.getWeChat());
				editReview.setWeChat(last.getWeChat());
				editReview.setEmailBefore(last.getEmail());
				editReview.setEmail(last.getEmail());
				editReview.setAddPostBefore(last.getAddPost());
				editReview.setAddPost(last.getAddPost());
				editReview.setProvinceBefore(last.getProvince());
				editReview.setProvince(last.getProvince());
				editReview.setCityBefore(last.getCity());
				editReview.setCity(last.getCity());
				editReview.setCountryBefore(last.getCountry());
				editReview.setCountry(last.getCountry());
				editReview.setDetialBefore(last.getDetial());
				editReview.setDetial(last.getDetial());
				editReview.setBankdetailBefore(last.getBankdetail());
				editReview.setBankdetail(last.getBankdetail());
				editReview.setAccNameBefore(last.getAccName());
				editReview.setAccName(last.getAccName());
				editReview.setAccCodeBefore(last.getAccCode());
				editReview.setAccCode(last.getAccCode());
				editReview.setWithdrawDefaultBefore(last.getWithdrawDefault());
				editReview.setWithdrawDefault(withdrawDefault);
				editReview.setSponsorCodeBefore(last.getSponsorCode());
				editReview.setSponsorCode(last.getSponsorCode());
				editReview.setSponsorNameBefore(last.getSponsorName());
				editReview.setSponsorName(last.getSponsorName());
				editReview.setRankBefore(last.getRank());
				editReview.setRank(last.getRank());
				editReview.setRaCodeBefore(last.getRaCode());
				editReview.setRaCode(last.getRaCode());
				editReview.setRaNameBefore(last.getRaName());
				editReview.setRaName(last.getRaName());
				editReview.setRaIdTypeBefore(last.getRaIdType());
				editReview.setRaIdType(last.getRaIdType());
				editReview.setRaIdCodeBefore(last.getRaIdCode());
				editReview.setRaIdCode(last.getRaIdCode());
				editReview.setUpdateBy("无名");
				editReview.setUpdateMemo(mDesc);
				editReview.setUpdateType(1);
				editReview.setUpdateTime(new Date());
				editReview.setReviewMemo("");
				editReview.setReviewStatus(0); //审核
			}
			return editReviewMapper.saveEdit(editReview);

		}else{
			return 0;//修改失败，找不到该会员信息
		}


	}

	/*
	 * 根据编和姓名修改会员证件信息（会员更名）
	 * */
	@Override
	public int updateIdByMCodeAndMName(String mCode,String mName,String newMName,Integer idType,String idCode,String mNickname,String mobile,String uploadPath,String mDesc) {
		MemberEditReview last = editReviewMapper.selectLastByMCode(mCode);

		MemberEditReview editReview = new MemberEditReview();
		if(last!=null){
			if (last.getMCode().equals(mCode)){
				editReview.setMCode(last.getMCode());

				editReview.setMNameBefore(last.getMName());
				if (newMName==null||"".equals(newMName.toString().trim())){
					editReview.setMName(last.getMName());
				}else{
					editReview.setMName(newMName);

				}

				editReview.setIdTypeBefore(last.getIdType());
				if (idType==null||"".equals(idType.toString().trim())){
					editReview.setIdType(last.getIdType());
				}else{
					editReview.setIdType(idType);
				}

				editReview.setIdCodeBefore(last.getIdCode());
				if (idCode==null||"".equals(idCode.toString().trim())){
					editReview.setIdCode(last.getIdCode());
				}else{
					editReview.setIdCode(idCode);
				}

				editReview.setMNicknameBefore(last.getMNickname());
				if (mNickname==null||"".equals(mNickname.toString().trim())){
					editReview.setMNickname(last.getMNickname());

				}else{
					editReview.setMNickname(mNickname);
				}

				editReview.setGenderBefore(last.getGender());
				editReview.setGender(last.getGender());

				editReview.setMobileBefore(last.getMobile());
				if (mobile==null||"".equals(mobile.toString().trim())){
					editReview.setMobile(last.getMobile());
				}else {
					editReview.setMobile(mobile);
				}
				editReview.setWeChatBefore(last.getWeChat());
				editReview.setWeChat(last.getWeChat());
				editReview.setEmailBefore(last.getEmail());
				editReview.setEmail(last.getEmail());
				editReview.setAddPostBefore(last.getAddPost());
				editReview.setAddPost(last.getAddPost());

				editReview.setProvinceBefore(last.getProvince());
				editReview.setProvince(last.getProvince());

				editReview.setCityBefore(last.getCity());
				editReview.setCity(last.getCity());

				editReview.setCountryBefore(last.getCountry());
				editReview.setCountry(last.getCountry());

				editReview.setDetialBefore(last.getDetial());
				editReview.setDetial(last.getDetial());

				editReview.setBankdetailBefore(last.getBankdetail());
				editReview.setBankdetail(last.getBankdetail());
				editReview.setAccNameBefore(last.getAccName());
				editReview.setAccName(last.getAccName());
				editReview.setAccCodeBefore(last.getAccCode());
				editReview.setAccCode(last.getAccCode());
				editReview.setWithdrawDefaultBefore(last.getWithdrawDefault());
				editReview.setWithdrawDefault(last.getWithdrawDefault());
				editReview.setSponsorCodeBefore(last.getSponsorCode());
				editReview.setSponsorCode(last.getSponsorCode());
				editReview.setSponsorNameBefore(last.getSponsorName());
				editReview.setSponsorName(last.getSponsorName());
				editReview.setRankBefore(last.getRank());
				editReview.setRank(last.getRank());
				editReview.setRaCodeBefore(last.getRaCode());
				editReview.setRaCode(last.getRaCode());
				editReview.setRaNameBefore(last.getRaName());
				editReview.setRaName(last.getRaName());
				editReview.setRaIdTypeBefore(last.getRaIdType());
				editReview.setRaIdType(last.getRaIdType());
				editReview.setRaIdCodeBefore(last.getRaIdCode());
				editReview.setRaIdCode(last.getRaIdCode());
				editReview.setUpdateBy("无名");
				editReview.setUpdateMemo(mDesc);
				editReview.setUpdateType(2);
				editReview.setUpdateTime(new Date());
				editReview.setUploadPath(uploadPath);
				editReview.setReviewMemo("");
				editReview.setReviewStatus(0); //审核
			}

			return editReviewMapper.saveEdit(editReview);

		}else{
			return 0;//修改失败，找不到该会员信息
		}

	}

	/*
	 * 根据编号查找会员状态、级别及关系信息（更改推荐人）
	 * */
	@Override
	public MemberRelation findRelationByMCode(String mCode) {

		MemberRelation relation = relationMapper.findRelationByMCode(mCode);
		String sponsorCode = null;
		if (relation!=null){
			sponsorCode = relation.getSponsorCode();
		}


		Member_basic sponsor = mapper.selectByMCode(sponsorCode);
		if (sponsor!=null&&!sponsor.getMName().equals(relation.getSponsorName())){
			int i = relationMapper.updateRelaSponNameByMCode(mCode);

			relation.setSponsorName(sponsor.getMName());

		}

		return relation;
	}

	/*
	 * 修改会员状态、级别及关系信息（更改推荐人）
	 * */
	@Override
	public int updateRelaSponByMCode(String mCode, String mName, String sponsorCode, String sponsorName, String sponsorCodeNew, String sponsorNameNew, String mDesc) {
		MemberEditReview last = editReviewMapper.selectLastByMCode(mCode);
		MemberEditReview editReview = new MemberEditReview();
		if(last!=null){
			if (last.getMCode().equals(mCode)){
				editReview.setMCode(last.getMCode());
				editReview.setMNameBefore(last.getMName());
				editReview.setMName(last.getMName());
				editReview.setIdTypeBefore(last.getIdType());
				editReview.setIdType(last.getIdType());
				editReview.setIdCodeBefore(last.getIdCode());
				editReview.setIdCode(last.getIdCode());
				editReview.setMNicknameBefore(last.getMNickname());
				editReview.setMNickname(last.getMNickname());
				editReview.setGenderBefore(last.getGender());
				editReview.setGender(last.getGender());
				editReview.setMobileBefore(last.getMobile());
				editReview.setMobile(last.getMobile());
				editReview.setWeChatBefore(last.getWeChat());
				editReview.setWeChat(last.getWeChat());
				editReview.setEmailBefore(last.getEmail());
				editReview.setEmail(last.getEmail());
				editReview.setAddPostBefore(last.getAddPost());
				editReview.setAddPost(last.getAddPost());
				editReview.setProvinceBefore(last.getProvince());
				editReview.setProvince(last.getProvince());
				editReview.setCityBefore(last.getCity());
				editReview.setCity(last.getCity());
				editReview.setCountryBefore(last.getCountry());
				editReview.setCountry(last.getCountry());
				editReview.setDetialBefore(last.getDetial());
				editReview.setDetial(last.getDetial());
				editReview.setBankdetailBefore(last.getBankdetail());
				editReview.setBankdetail(last.getBankdetail());
				editReview.setAccNameBefore(last.getAccName());
				editReview.setAccName(last.getAccName());
				editReview.setAccCodeBefore(last.getAccCode());
				editReview.setAccCode(last.getAccCode());
				editReview.setWithdrawDefaultBefore(last.getWithdrawDefault());
				editReview.setWithdrawDefault(last.getWithdrawDefault());

				editReview.setSponsorCodeBefore(sponsorCode);
				editReview.setSponsorCode(sponsorCodeNew);
				editReview.setSponsorNameBefore(sponsorName);
				editReview.setSponsorName(sponsorNameNew);

				editReview.setRankBefore(last.getRank());
				editReview.setRank(last.getRank());
				editReview.setRaCodeBefore(last.getRaCode());
				editReview.setRaCode(last.getRaCode());
				editReview.setRaNameBefore(last.getRaName());
				editReview.setRaName(last.getRaName());
				editReview.setRaIdTypeBefore(last.getRaIdType());
				editReview.setRaIdType(last.getRaIdType());
				editReview.setRaIdCodeBefore(last.getRaIdCode());
				editReview.setRaIdCode(last.getRaIdCode());
				editReview.setUpdateBy("无名");
				editReview.setUpdateMemo(mDesc);
				editReview.setUpdateType(3);
				editReview.setUpdateTime(new Date());
				editReview.setReviewMemo("");
				editReview.setReviewStatus(0); //审核
			}
			return editReviewMapper.saveEdit(editReview);
		}else{
			return 0;//修改失败，找不到该会员信息
		}
	}

	/*
	添加银行卡
	* */
	@Override
	public int addBankByMCode(MemberBank bank) {
		bank.setAccType("储蓄卡");
		return bankMapper.addBankByMCode(bank);
	}

	@Override
	public int addBinding(String mCode, String raCode, String raName, Integer raIdType, String raIdCode, String raSponsorName, String raSponsorCode, String raSponsorIdCode) {
		RdRaBinding binding = new RdRaBinding();
		binding.setRaCode(raCode);
		binding.setRaName(raName);
		binding.setRaIdType(raIdType);
		binding.setRaIdCode(raIdCode);
		binding.setRaSponsorCode(raSponsorCode);
		binding.setRaSponsorName(raSponsorName);
		binding.setRdCode(mCode);
		binding.setBindingDate(new Date());
		binding.setBindingBy("无");
		return rdRaBindingMapper.addBinding(binding);
	}

	/*
	 * 查找是否有相同昵称的会员
	 * */
	@Override
	public List<Member_basic> findMemByMNickName(String mCode,String mNickname) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mNickname",mNickname);
		return mapper.findMemByMNickName(map);
	}

	/*
	 * 查找是否已绑定该手机的会员
	 * */
	@Override
	public List<Member_basic> findMemByMobile(String mCode,String mobile) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mobile",mobile);
		return mapper.findMemByMobile(map);
	}

	@Override
	public List<MemberEditReview> findEditByMCode(String mCode, int updateType) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("updateType",updateType);
		return editReviewMapper.findEditByMCode(map);
	}

	@Override
	public String findRank(int rank) {
		return rankMapper.findRank(rank);
	}

	@Override
	public int findRankByRankName(String rankName) {
		return rankMapper.findRankByRankName(rankName);
	}

	/*
	* 根据这一周期查找是否有上一期周期
	* */
	@Override
	public SysPeriod findPrePeriod(String periodCode) {
		return sysPeriodMapper.findPrePeriod(periodCode);
	}


	/*
	 * 修改会员状态、级别及关系信息（更改会员级别）
	 * */
	@Override
	public int updateRelationByMCode(String mCode, String mName, int rank, int rankNew, String mDesc) {
		MemberEditReview last = editReviewMapper.selectLastByMCode(mCode);

		MemberEditReview editReview = new MemberEditReview();
		if(last!=null){
			if (last.getMCode().equals(mCode)){
				editReview.setMCode(last.getMCode());
				editReview.setMNameBefore(last.getMName());
				editReview.setMName(last.getMName());
				editReview.setIdTypeBefore(last.getIdType());
				editReview.setIdType(last.getIdType());
				editReview.setIdCodeBefore(last.getIdCode());
				editReview.setIdCode(last.getIdCode());
				editReview.setMNicknameBefore(last.getMNickname());
				editReview.setMNickname(last.getMNickname());
				editReview.setGenderBefore(last.getGender());
				editReview.setGender(last.getGender());
				editReview.setMobileBefore(last.getMobile());
				editReview.setMobile(last.getMobile());
				editReview.setWeChatBefore(last.getWeChat());
				editReview.setWeChat(last.getWeChat());
				editReview.setEmailBefore(last.getEmail());
				editReview.setEmail(last.getEmail());
				editReview.setAddPostBefore(last.getAddPost());
				editReview.setAddPost(last.getAddPost());
				editReview.setProvinceBefore(last.getProvince());
				editReview.setProvince(last.getProvince());
				editReview.setCityBefore(last.getCity());
				editReview.setCity(last.getCity());
				editReview.setCountryBefore(last.getCountry());
				editReview.setCountry(last.getCountry());
				editReview.setDetialBefore(last.getDetial());
				editReview.setDetial(last.getDetial());
				editReview.setBankdetailBefore(last.getBankdetail());
				editReview.setBankdetail(last.getBankdetail());
				editReview.setAccNameBefore(last.getAccName());
				editReview.setAccName(last.getAccName());
				editReview.setAccCodeBefore(last.getAccCode());
				editReview.setAccCode(last.getAccCode());
				editReview.setWithdrawDefaultBefore(last.getWithdrawDefault());
				editReview.setWithdrawDefault(last.getWithdrawDefault());
				editReview.setSponsorCodeBefore(last.getSponsorCode());
				editReview.setSponsorCode(last.getSponsorCode());
				editReview.setSponsorNameBefore(last.getSponsorName());
				editReview.setSponsorName(last.getSponsorName());

				editReview.setRankBefore(rank);
				editReview.setRank(rankNew);

				editReview.setRaCodeBefore(last.getRaCode());
				editReview.setRaCode(last.getRaCode());
				editReview.setRaNameBefore(last.getRaName());
				editReview.setRaName(last.getRaName());
				editReview.setRaIdTypeBefore(last.getRaIdType());
				editReview.setRaIdType(last.getRaIdType());
				editReview.setRaIdCodeBefore(last.getRaIdCode());
				editReview.setRaIdCode(last.getRaIdCode());
				editReview.setUpdateBy("无名");
				editReview.setUpdateMemo(mDesc);
				editReview.setUpdateType(4);
				editReview.setUpdateTime(new Date());
				editReview.setReviewMemo("");
				editReview.setReviewStatus(0); //审核
			}
			return editReviewMapper.saveEdit(editReview);
		}else{
			return 0;//修改失败，找不到该会员信息
		}
	}


	/*
	 * 根据编号查找会员及与老会员关系信息
	 * */
	@Override
	public RdRaBinding findBindingByMCode(String mCode) {

		return rdRaBindingMapper.findBindingByMCode(mCode);
	}

	@Override
	public int updateBindingByMCode(String mCode, String mName, String raCode, String raName, String raCodeNew, String raNameNew, Integer raIdType, String raIdCode, String mDesc) {
		MemberEditReview last = editReviewMapper.selectLastByMCode(mCode);

		MemberEditReview editReview = new MemberEditReview();
		if(last!=null){
			if (last.getMCode().equals(mCode)){
				editReview.setMCode(last.getMCode());
				editReview.setMNameBefore(last.getMName());
				editReview.setMName(last.getMName());
				editReview.setIdTypeBefore(last.getIdType());
				editReview.setIdType(last.getIdType());
				editReview.setIdCodeBefore(last.getIdCode());
				editReview.setIdCode(last.getIdCode());
				editReview.setMNicknameBefore(last.getMNickname());
				editReview.setMNickname(last.getMNickname());
				editReview.setGenderBefore(last.getGender());
				editReview.setGender(last.getGender());
				editReview.setMobileBefore(last.getMobile());
				editReview.setMobile(last.getMobile());
				editReview.setWeChatBefore(last.getWeChat());
				editReview.setWeChat(last.getWeChat());
				editReview.setEmailBefore(last.getEmail());
				editReview.setEmail(last.getEmail());
				editReview.setAddPostBefore(last.getAddPost());
				editReview.setAddPost(last.getAddPost());
				editReview.setProvinceBefore(last.getProvince());
				editReview.setProvince(last.getProvince());
				editReview.setCityBefore(last.getCity());
				editReview.setCity(last.getCity());
				editReview.setCountryBefore(last.getCountry());
				editReview.setCountry(last.getCountry());
				editReview.setDetialBefore(last.getDetial());
				editReview.setDetial(last.getDetial());
				editReview.setBankdetailBefore(last.getBankdetail());
				editReview.setBankdetail(last.getBankdetail());
				editReview.setAccNameBefore(last.getAccName());
				editReview.setAccName(last.getAccName());
				editReview.setAccCodeBefore(last.getAccCode());
				editReview.setAccCode(last.getAccCode());
				editReview.setWithdrawDefaultBefore(last.getWithdrawDefault());
				editReview.setWithdrawDefault(last.getWithdrawDefault());
				editReview.setSponsorCodeBefore(last.getSponsorCode());
				editReview.setSponsorCode(last.getSponsorCode());
				editReview.setSponsorNameBefore(last.getSponsorName());
				editReview.setSponsorName(last.getSponsorName());
				editReview.setRankBefore(last.getRank());
				editReview.setRank(last.getRank());

				editReview.setRaCodeBefore(raCode);
				editReview.setRaCode(raCodeNew);
				editReview.setRaNameBefore(raName);
				editReview.setRaName(raNameNew);
				editReview.setRaIdTypeBefore(last.getRaIdType());
				editReview.setRaIdType(raIdType);
				editReview.setRaIdCodeBefore(last.getRaIdCode());
				editReview.setRaIdCode(raIdCode);

				editReview.setUpdateBy("无名");
				editReview.setUpdateMemo(mDesc);
				editReview.setUpdateType(5);
				editReview.setUpdateTime(new Date());
				editReview.setReviewMemo("");
				editReview.setReviewStatus(0); //审核
			}
			return editReviewMapper.saveEdit(editReview);
		}else{
			return 0;//修改失败，找不到该会员信息
		}
	}

	@Override
	public List<MemberEditReview> findEditAll(String mCode, String mName, Integer updateType, Integer reviewStatus, Date timeStar,Date timeEnd) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mName",mName);
		map.put("updateType",updateType);
		map.put("reviewStatus",reviewStatus);
		map.put("timeStar",timeStar);
		map.put("timeEnd",timeEnd);
		return editReviewMapper.findEditAll(map);
	}

	@Override
	public MemberEditReview findEditById(Integer rId) {
		return editReviewMapper.findEditById(rId);
	}

	/*
	 * 审核
	 * */
	@Override
	public int updateEditById(Integer rId,Integer reviewStatus,String reviewMemo) {

		//审核通过
		if (reviewStatus==2){
			MemberEditReview memberEditReview = editReviewMapper.findEditById(rId);
			if(memberEditReview==null){
				return 0;
			}
			Integer status = memberEditReview.getReviewStatus();
			//待审
			if (status==0&&status!=reviewStatus){
				//得到更新类型
				Integer updateType = memberEditReview.getUpdateType();

				//修改敏感信息
				if (updateType==1){
					//获取手机号码、昵称、默认收款方式选择
					Member_basic memberBasic = new Member_basic();
					memberBasic.setMCode(memberEditReview.getMCode());
					memberBasic.setMobile(memberEditReview.getMobile());
					memberBasic.setMNickname(memberEditReview.getMNickname());
					//更改基本信息表
					int j = mapper.updateMobAndNickByMCode(memberBasic);

					int i;
					List<MemberBank> bank = bankMapper.findMBankByMCode(memberEditReview.getMCode());
					if (bank.size()==0){
						i=1;
					}else {
						Map<String,Object> map = new HashMap<>();
						map.put("mCode",memberEditReview.getMCode());
						map.put("withdrawDefault",memberEditReview.getWithdrawDefault());
						//更改会员银行信息表
						i = bankMapper.updateByMBank(map);
					}
					if (i!=1||j!=1){
						//修改失败---审核失败
						return 0;
					}

				}

				//会员更名 姓名，证件类型，证件号码，昵称，手机号码，地址
				if (updateType==2){
					Member_basic memberBasic = new Member_basic();
					memberBasic.setMCode(memberEditReview.getMCode());
					memberBasic.setMName(memberEditReview.getMName());
					memberBasic.setIdType(memberEditReview.getIdType());
					memberBasic.setIdCode(memberEditReview.getIdCode());
					memberBasic.setMNickname(memberEditReview.getMNickname());
					memberBasic.setMobile(memberEditReview.getMobile());


					int i = mapper.updateNameByMCode(memberBasic);
					if (i!=1){
						//修改失败---审核失败
						return 0;
					}
				}

				//更改推荐人 推荐人编号 推荐人姓名
				if (updateType==3){
					MemberRelation relation = new MemberRelation();
					relation.setMCode(memberEditReview.getMCode());
					relation.setSponsorCode(memberEditReview.getSponsorCode());
					relation.setSponsorName(memberEditReview.getSponsorName());

					int i = relationMapper.updateSponsorByMCode(relation);
					if (i!=1){
						//修改失败---审核失败
						return 0;
					}
				}

				//更改会员级别
				if (updateType==4){
					MemberRelation relation = new MemberRelation();
					relation.setMCode(memberEditReview.getMCode());
					relation.setRank(memberEditReview.getRank());

					int i = relationMapper.updateRankByMCode(relation);
					if (i!=1){
						//修改失败---审核失败
						return 0;
					}
				}

				//与老会员绑定 老会员编号，老会员姓名，老系统身份证类型，老系统身份证号码
				if (updateType==5){

					RdRaBinding binding = new RdRaBinding();
					binding.setRaCode(memberEditReview.getRaCode());
					binding.setRaName(memberEditReview.getRaName());
					binding.setRdCode(memberEditReview.getMCode());
					binding.setRaIdType(memberEditReview.getRaIdType());
					binding.setRaIdCode(memberEditReview.getRaIdCode());


					int i = rdRaBindingMapper.updateRaByMCode(binding);
					if (i!=1){
						//修改失败---审核失败
						return 0;
					}
				}

			}
		}

		Map<String,Object> map = new HashMap<>();
		map.put("rId",rId);
		map.put("reviewStatus",reviewStatus);
		map.put("reviewMemo",reviewMemo);
		return editReviewMapper.updateEditById(map);
	}

	@Override
	public List<MemberEditReview> findEditStatus(String mCode, String mName, Date timeStar,Date timeEnd) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mName",mName);
		map.put("timeStar",timeStar);
		map.put("timeEnd",timeEnd);
		return editReviewMapper.findEditStatus(map);
	}

	@Override
	public SysPeriod findPeriod(String date) {
		return sysPeriodMapper.findPeriodByDate(date);
	}

	@Override
	public List<MemberQualification> findQualificationByPeriod(String periodCode) {
		return qualificationMapper.findQualificationByPeriod(periodCode);
	}

	@Override
	public MemberQualification findQualificationByPeriodAndMCode(String statusPeriod, String mCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("statusPeriod",statusPeriod);
		map.put("mCode",mCode);
		return qualificationMapper.findQualificationByPeriodAndMCode(map);
	}

	/*/
	 *查询所有级别
	 */
	@Override
	public List<Rank> fingRankAll() {
		return rankMapper.fingRankAll();
	}


	/*
	 *添加周期
	 * */
	@Override
	public int addPeriod(String periodCode,String prePeriod, Date beginDate, Date endDate) {
		SysPeriod period = new SysPeriod();
		period.setPeriodCode(periodCode);
		period.setPrePeriod(prePeriod);
		period.setBeginDate(beginDate);
		period.setEndDate(endDate);
		period.setSalesStatus(0);//业绩状态 0：未开始
		period.setCalStatus(0);//奖金状态 0：未开始
		period.setBonusStatus(0);//发放状态 0：未发出
		period.setCalTimes(0);//计算次数：0
		return sysPeriodMapper.addPeriod(period);
	}

	@Override
	public List<MemberAddress> findAddByMCode(String mCode) {
		return addressMapper.findAddByMCode(mCode);
	}

	/*
	 * 添加会员地址
	 * */
	@Override
	public int addMemAdd(MemberAddress memberAddress) {
		Integer defaultAdd = memberAddress.getDefaultAdd();
		if (defaultAdd==1){
			try {
				//把相同编号的其他收货地址的默认方式设为0
				int countEdit = addressMapper.updateAddDefaultByMCode(memberAddress.getMCode());

				//查询该会员有多少个收货地址
				int count = addressMapper.selectCount(memberAddress.getMCode());

				if (countEdit==count){
					//添加收货地址
					int i = addressMapper.addMemAdd(memberAddress);
					if(i==1){
						return i;
					}
					return 0;
				}else {

					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			int i = addressMapper.addMemAdd(memberAddress);
			if(i==1){
				return i;
			}
			return 0;
		}

		return 0;
	}

	@Override
	public int delBankByOid(Integer oId,String mCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("oId",oId);
		return bankMapper.delBankByOid(map);
	}

	@Override
	public MemberAddress findMemAddByMCode(String mCode, Integer aId) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("aId",aId);
		return addressMapper.findMemAddByMCode(map);
	}

	@Override
	public int editMemAdd(MemberAddress memberAddress) {

		Integer defaultAdd = memberAddress.getDefaultAdd();
		if (defaultAdd==1){
			try {
				//把相同编号的其他收货地址的默认方式设为0
				int countEdit = addressMapper.updateAddDefaultByMCode(memberAddress.getMCode());

				//查询该会员有多少个收货地址
				int count = addressMapper.selectCount(memberAddress.getMCode());

				if (countEdit==count){
					//修改收货地址
					int i = addressMapper.editMemAdd(memberAddress);
					if(i==1){
						return i;
					}
					return 0;
				}else {
					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			int i = addressMapper.editMemAdd(memberAddress);
			if(i==1){
				return i;
			}
			return 0;
		}
		return 0;
	}

	@Override
	public int delMemAddByAId(Integer aId, String mCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("aId",aId);
		map.put("mCode",mCode);
		return addressMapper.delMemAddByAId(map);
	}

	@Override
	public int defAddByAIdAndMCode(Integer aId, String mCode, Integer defaultAdd) {
		if (defaultAdd==1){
			try {
				//把相同编号的其他收货地址的默认方式设为0
				int countEdit = addressMapper.updateAddDefaultByMCode(mCode);

				//查询该会员有多少个收货地址
				int count = addressMapper.selectCount(mCode);

				if (countEdit==count){
					//设置默认收货地址
					Map<String,Object> map = new HashMap<>();
					map.put("aId",aId);
					map.put("mCode",mCode);
					map.put("defaultAdd",defaultAdd);
					int i = addressMapper.defAddByAIdAndMCode(map);
					if(i==1){
						return i;
					}
					return 0;
				}else {

					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return 0;
	}

	@Override
	public MemberAccount findMemAccountByMCode(String mCode) {
		return accountMapper.findMemAccountByMCode(mCode);
	}

	@Override
	public boolean outAllBankByMCode(String mCode) {
		//计算绑定多少个银行信息
		int j = bankMapper.countMBankByMCode(mCode);

		//删除绑定银行信息个数
		int i = bankMapper.outAllBankByMCode(mCode);

		if (i==j){
			return true;
		}

		return false;
	}

	@Override
	public List<MemberAddress> findAddAllByMCode(String mCode) {
		return addressMapper.findAddByMCode(mCode);
	}

	@Override
	public boolean outAllAddByMCode(String mCode) {

		//计算多少个收货
		int j = addressMapper.selectCount(mCode);

		//删除收货地址个数
		int i = addressMapper.outAllAddByMCode(mCode);

		if (i==j){
			return true;
		}
		return false;
	}

	@Override
	public int editPeriodStatu(String periodCode,int salesStatus, int calStatus, int bonusStatus) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("salesStatus",salesStatus);
		map.put("calStatus",calStatus);
		map.put("bonusStatus",bonusStatus);
		return sysPeriodMapper.editPeriodStatu(map);
	}

	@Override
	public int addPeriodLog(String periodCode, String actionCode, int valBefoer, String valBefoerDesc, int valAfter, String valAfterDesc, String updateMemo) {
		SysPeriodLog sysPeriodLog = new SysPeriodLog();
		sysPeriodLog.setPeriodCode(periodCode);
		sysPeriodLog.setActionCode(actionCode);
		sysPeriodLog.setValBefoer(valBefoer);
		sysPeriodLog.setValBefoerDesc(valBefoerDesc);
		sysPeriodLog.setValAfter(valAfter);
		sysPeriodLog.setValAfterDesc(valAfterDesc);
		sysPeriodLog.setUpdateTime(new Date());
		sysPeriodLog.setUpdateBy("无名");
		sysPeriodLog.setUpdateMemo(updateMemo);
		return sysPeriodLogMapper.addPeriodLog(sysPeriodLog);
	}

	@Override
	public int ClosePeriodSales(String periodCode) {

		int salesStatus = 3;
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("salesStatus",salesStatus);
		return sysPeriodMapper.ClosePeriodSales(map);
	}

	@Override
	public List<SysPeriod> findPeriodAll() {
		return sysPeriodMapper.findPeriodAll();
	}

	@Override
	public int editPeriod(String periodCode, Date endDate) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("endDate",endDate);
		return sysPeriodMapper.editPeriod(map);
	}

	@Override
	public int delPeriod(String periodCode) {
		return sysPeriodMapper.delPeriod(periodCode);
	}

	@Override
	public List<SysPeriodLog> findPeriodLogAll(String periodCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		return sysPeriodLogMapper.findPeriodLogAll(map);
	}

	@Override
	public int updatePeriod(String periodCode, Date beginDate, Date endDate) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("beginDate",beginDate);
		map.put("endDate",endDate);
		return sysPeriodMapper.updatePeriod(map);
	}

	@Override
	public void updatePeriodAddNext(String prePeriod,String periodCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",prePeriod);
		map.put("nextPeriod",periodCode);
		sysPeriodMapper.updatePeriodAddNext(map);
	}

	@Override
	public List<MemberQualification> findQualificationMCodeByPeriod(String periodCode) {
		return qualificationMapper.findQualificationMCodeByPeriod(periodCode);
	}

	@Override
	public List<Member_basic> findMemAll() {
		return mapper.findMemAll();
	}

	@Override
	public int addMqlf(MemberQualification mqlf) {
		return qualificationMapper.addMqlf(mqlf);
	}

	@Override
	public int delQulfByPeriod(String periodCode) {
		return qualificationMapper.delQulfByPeriod(periodCode);
	}

	@Override
	public List<MemberRelation> findRelationBySponsorCode(String sponsorCode) {
		return relationMapper.findRelationBySponsorCode(sponsorCode);
	}

	@Override
	public int updateQualifi(String periodCode, String code, int layerNext) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",code);
		map.put("layer",layerNext);
		return qualificationMapper.updateQualifi(map);
	}

	@Override
	public int updateQualifiLeafYn(String mCode, String periodCode,int leafYn) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",mCode);
		map.put("leafYn",leafYn);
		return qualificationMapper.updateQualifiLeafYn(map);
	}

	@Override
	public int findLayerMax(String periodCode) {
		return qualificationMapper.findLayerMax(periodCode);
	}

	@Override
	public List<MemberQualification> findQualifiByLayer(String periodCode, int i) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("layer",i);
		return qualificationMapper.findQualifiByLayer(map);
	}

	@Override
	public int updateQlfNPV(String periodCode, String mCode, int npv) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",mCode);
		map.put("npv",npv);
		return qualificationMapper.updateQlfNPV(map);
	}

	@Override
	public int updateQlfG7PV(String periodCode, String mCode, int g7pv) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",mCode);
		map.put("g7pv",g7pv);
		return qualificationMapper.updateQlfG7PV(map);
	}

	@Override
	public int updateQulfGPV(String periodCode, String mCode, int npv) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",mCode);
		map.put("gpvFlagship",npv);
		return qualificationMapper.updateQulfGPV(map);
	}

	@Override
	public int updateQulfRank(String periodCode, String mCode, int rank) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",mCode);
		map.put("rank",rank);
		return qualificationMapper.updateQulfRank(map);
	}

	@Override
	public int findRltCountRank2BySponsorCode(String sponsorCode) {
		return relationMapper.findRltCountRank2BySponsorCode(sponsorCode);
	}

	@Override
	public int findQulfCountRankBySponsorCode(String periodCode, String mCode, int groupRankMax) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("sponsorCode",mCode);
		map.put("groupRankMax",groupRankMax);
		return qualificationMapper.findQulfCountRankBySponsorCode(map);
	}

	@Override
	public List<MemberQualification> findQulfGroup(String periodCode, String mCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("sponsorCode",mCode);
		return qualificationMapper.findQulfGroup(map);
	}

	@Override
	public int updateQulfGroup(String periodCode, String mCode, int groupRankMax) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",mCode);
		map.put("groupRankMax",groupRankMax);
		return qualificationMapper.updateQulfGroup(map);
	}

	@Override
	public int updateQulfD(String periodCode, String mCode,int count2, int count6, int count7, int count8) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",mCode);
		map.put("ddRank2Number",count2);
		map.put("dlRank6Number",count6);
		map.put("dlRank7Number",count7);
		map.put("dlRank8Number",count8);
		return qualificationMapper.updateQulfD(map);
	}

	@Override
	public int updateQulfRankHight(String periodCode, String mCode, int rankRecordHigh) {
		Map<String,Object> map = new HashMap<>();
		map.put("periodCode",periodCode);
		map.put("mCode",mCode);
		map.put("rankRecordHigh",rankRecordHigh);
		return qualificationMapper.updateQulfRankHight(map);
	}

	@Override
	public List<RdBonusMaster> findMasterByPeriod(String periodCode) {
		return masterMapper.findMasterByPeriod(periodCode);
	}

	@Override
	public int delQulfPV(MemberQualification qualification) {
		if (qualification.getRankInit()>=2){
			qualification.setRank(0);
		}
		qualification.setRankRecordHigh(qualification.getRankInit());
		qualification.setLeafYn(0);
		qualification.setLayer(null);
		qualification.setG7pv(0);
		qualification.setNpv(0);
		qualification.setGpvFlagship(0);
		qualification.setGroupRankMax(0);
		qualification.setDdRank2Number(0);
		qualification.setDlRank6Number(0);
		qualification.setDlRank7Number(0);
		qualification.setDlRank8Number(0);

		return qualificationMapper.delQulfPV(qualification);
	}

	@Override
	public List<RdReceivableMaster> findReceivableAll(String mCode, String mNickname, int status) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mNickname",mNickname);
		map.put("status",status);
		return receivableMapper.findReceivableAll(map);
	}

	@Override
	public int defBankByOid(Integer oId, String mCode, Integer defaultBank) {
		if (defaultBank==1){
			try {
				//把相同编号的其他银行卡的默认方式设为0
				int countEdit = bankMapper.updateBankDefaultByMCode(mCode);

				//查询该会员有多少个银行卡
				int count = bankMapper.selectCount(mCode);

				if (countEdit==count){
					//设置默认提现银行卡
					Map<String,Object> map = new HashMap<>();
					map.put("oId",oId);
					map.put("mCode",mCode);
					map.put("defaultBank",defaultBank);
					int i = bankMapper.defBankByOid(map);
					if(i==1){
						return i;
					}
					return 0;
				}else {

					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return 0;
	}

	/**
	 * 根据mCode和nickName查询会员表
	 * @param mCode
	 * @param mNikcName
	 * @return
	 */
	@Override
	public Member_basic findMemberBasicByMcodeAndNickName(String mCode, String mNikcName) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mNikcName",mNikcName);
		return mapper.findMemberBasicByMcodeAndNickName(map);
	}

	/**
	 * 根据昵称查询会员基础信息
	 * @param mNickName
	 * @return
	 */
	@Override
	public Member_basic findByNickName(String mNickName) {
		return mapper.findByNickName(mNickName);
	}

	/**
	 * 根据证件类型和证件号码查询会员信息
	 * @param idType
	 * @param idCode
	 * @return
	 */
	@Override
	public Member_basic findMemberBasicByIdTypeAndIdCode(int idType, String idCode) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("idType",idType);
		map.put("idCode",idCode);
		return mapper.findMemberBasicByIdTypeAndIdCode(map);
	}

	/**
	 * 根据手机号码查询会员信息
	 * @param mobile
	 * @return
	 */
	@Override
	public Member_basic findMemberBasicByMobile(String mobile) {
		return mapper.findMemberBasicByMobile(mobile);
	}

	@Override
	public List<RdReceivableDetail> findReceivableDetailAll(String mCode, String mNickname, Integer transNumber, String trTypeCode, int status, Date timeStar, Date timeEnd) {
		Map<String,Object> map = new HashMap<>();
		map.put("transNumber",transNumber);
		map.put("mCode",mCode);
		map.put("mNickname",mNickname);
		map.put("trTypeCode",trTypeCode);
		map.put("status",status);
		map.put("timeStar",timeStar);
		map.put("timeEnd",timeEnd);
		return receivableDetailMapper.findReceivableDetailAll(map);
	}

	@Override
	public int updateRD(String mCode, int transNumber, int status) {
		Map<String,Object> map = new HashMap<>();
		map.put("transNumber",transNumber);
		map.put("mCode",mCode);
		map.put("status",status);
		return receivableDetailMapper.updateRD(map);
	}

	@Override
	public RdReceivableDetail findRD(String mCode, int transNumber) {
		Map<String,Object> map = new HashMap<>();
		map.put("transNumber",transNumber);
		map.put("mCode",mCode);
		return receivableDetailMapper.findRD(map);
	}

	@Override
	public RdReceivableMaster findReceivableByMCode(String mCode) {
		return receivableMapper.findReceivableByMCode(mCode);
	}

	@Override
	public int addReceivableM(RdReceivableMaster master) {
		return receivableMapper.addReceivableM(master);
	}

	@Override
	public int updateRM(String mCode, BigDecimal blanceAfter, int bnsDeductPecent) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("receivableBlance",blanceAfter);
		map.put("bnsDeductPecent",bnsDeductPecent);
		return receivableMapper.updateRM(map);
	}

	@Override
	public MemberBank findMBankByMCodeAndDefual(String mCode) {
		return bankMapper.findMBankByMCodeAndDefual(mCode);
	}

	@Override
	public int addRDNR(RdReceivableDetail detail) {
		return receivableDetailMapper.addRDNR(detail);
	}

	@Override
	public int addRDRR(RdReceivableDetail detail) {
		return receivableDetailMapper.addRDRR(detail);
	}

	@Override
	public int updateRelaStatus(String mCode, int statusAfter) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mStatus",statusAfter);
		return relationMapper.updateRelaStatus(map);
	}

	@Override
	public int updateAccountStatus(String mCode, int statusAfter) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("Status",statusAfter);
		relationMapper.updateMPointStatus(map);
		return accountMapper.updateAccountStatus(map);
	}

	@Override
	public int addStatusD(String mCode, String mNickname, String statusType, int statusBefore, int statusAfter, String updateDesc) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mNickname",mNickname);
		map.put("statusType",statusType);
		map.put("statusBefore",statusBefore);
		map.put("statusAfter",statusAfter);
		map.put("updateBy","无名");
		map.put("updateTime",new Date());
		map.put("updateDesc",updateDesc);
		return statusDetailMapper.addStatusD(map);
	}

	@Override
	public List<RdStatusDetail> findStatusDetailAll(String statusType, Date timeStar, Date timeEnd, String mCode, String mNickname) {
		Map<String,Object> map = new HashMap<>();
		map.put("statusType",statusType);
		map.put("mCode",mCode);
		map.put("mNickname",mNickname);
		map.put("timeStar",timeStar);
		map.put("timeEnd",timeEnd);
		return statusDetailMapper.findStatusDetailAll(map);
	}

	@Override
	public List<RdMemberAccountLog> findRdAccLog1(Date timeStar, Date timeEnd, String mCode, String mNickname, Integer transNumber, Integer batchNumber, String typeS) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mNickname",mNickname);
		map.put("transNumber",transNumber);
		map.put("batchNumber",batchNumber);
		map.put("timeStar",timeStar);
		map.put("timeEnd",timeEnd);
		if (typeS.equals("BOP")){//奖金积分
			return accountLogMapper.findRdAccLogBOP(map);
		}
		if (typeS.equals("SHP")){//购物积分
			return accountLogMapper.findRdAccLogSHP(map);
		}
		if (typeS.equals("PUI")){//换购积分
			return accountLogMapper.findRdAccLogPUI(map);
		}
		return null;
	}

	@Override
	public List<RdMemberAccountLog> findRdAccLog2(Date timeStar, Date timeEnd, String mCode, String mNickname, Integer transNumber, Integer batchNumber, String transTypeCode, String typeS) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mNickname",mNickname);
		map.put("transNumber",transNumber);
		map.put("batchNumber",batchNumber);
		map.put("timeStar",timeStar);
		map.put("timeEnd",timeEnd);
		if (transTypeCode.equals("INTOALL")){//全部转入
			if (typeS.equals("BOP")){//奖金积分
				return accountLogMapper.findRdAccLogBOPINTO(map);
			}
			if (typeS.equals("SHP")){//购物积分
				return accountLogMapper.findRdAccLogSHPINTO(map);
			}
			if (typeS.equals("PUI")){//换购积分
				return accountLogMapper.findRdAccLogPUIINTO(map);
			}
		}

		if (transTypeCode.equals("OUTALL")){//全部转出
			if (typeS.equals("BOP")){//奖金积分
				return accountLogMapper.findRdAccLogBOPOUT(map);
			}
			if (typeS.equals("SHP")){//购物积分
				return accountLogMapper.findRdAccLogSHPOUT(map);
			}
			if (typeS.equals("PUI")){//换购积分
				return accountLogMapper.findRdAccLogPUIOUT(map);
			}
		}

		map.put("transTypeCode",transTypeCode);
		return accountLogMapper.findRdAccLog2(map);
	}

	@Override
	public MemberIntegralRule findRule() {
		return ruleMapper.findRule();
	}

	@Override
	public List<RdMemberAccountLog> findAccountLogWD() {
		return accountLogMapper.findAccountLogWD();
	}

	@Override
	public MemberBank findMBankByOId(Integer oId) {
		return bankMapper.findMBankByOId(oId);
	}

	@Override
	public int updateAccLogWDOne(String mCode, Integer transNumber, int status) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("transNumber",transNumber);
		map.put("status",status);
		return accountLogMapper.updateAccLogWDOne(map);
	}

	@Override
	public List<RdMemberAccountLog> findAccountLogWDALL(Integer transNumber, Date timeStar, Date timeEnd, String mCode, String mNickname, int status) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mNickname",mNickname);
		map.put("timeStar",timeStar);
		map.put("timeEnd",timeEnd);
		map.put("transNumber",transNumber);
		map.put("status",status);
		return accountLogMapper.findAccountLogWDALL(map);
	}

	@Override
	public int updateRule(MemberIntegralRule memberIntegralRule) {
		return ruleMapper.updateRule(memberIntegralRule);
	}

}
