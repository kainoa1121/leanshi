package cn.leanshi.service.serviceImpl;

import cn.leanshi.mapper.MemberAccountMapper;
import cn.leanshi.mapper.MemberAddressMapper;
import cn.leanshi.mapper.MemberBankMapper;
import cn.leanshi.mapper.MemberEditReviewMapper;
import cn.leanshi.mapper.MemberMapper;
import cn.leanshi.mapper.MemberQualificationMapper;
import cn.leanshi.mapper.MemberRelationMapper;
import cn.leanshi.mapper.RankMapper;
import cn.leanshi.mapper.RdRaBindingMapper;
import cn.leanshi.mapper.SysPeriodMapper;
import cn.leanshi.model.MemberAccount;
import cn.leanshi.model.MemberAddress;
import cn.leanshi.model.MemberBank;
import cn.leanshi.model.MemberEditReview;
import cn.leanshi.model.MemberQualification;
import cn.leanshi.model.MemberRelation;
import cn.leanshi.model.Member_basic;
import cn.leanshi.model.Rank;
import cn.leanshi.model.RdRaBinding;
import cn.leanshi.model.SysPeriod;
import cn.leanshi.service.MemberService;
import lombok.RequiredArgsConstructor;

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
	private final RankMapper rankMapper;
	private final MemberAddressMapper addressMapper;
	private final MemberAccountMapper accountMapper;


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
				editReview.setReviewMemo("会员基本信息修改，不需提交材料");
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
				editReview.setReviewMemo("未提供材料");
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
	public int updateIdByMCodeAndMName(String mCode,String mName,String newMName,Integer idType,String idCode,String mNickname,String mobile,String province,String city,String country,String detial,String mDesc) {
		MemberEditReview last = editReviewMapper.selectLastByMCode(mCode);

		MemberEditReview editReview = new MemberEditReview();
		if(last!=null){
			if (last.getMCode().equals(mCode)){
				editReview.setMCode(last.getMCode());

				editReview.setMNameBefore(last.getMName());
				if (newMName==null&&"".equals(newMName.toString().trim())){
					editReview.setMName(last.getMName());
				}else{
					editReview.setMName(newMName);

				}

				editReview.setIdTypeBefore(last.getIdType());
				if (idType==null&&"".equals(idType.toString().trim())){
					editReview.setIdType(last.getIdType());
				}else{
					editReview.setIdType(idType);
				}

				editReview.setIdCodeBefore(last.getIdCode());
				if (idCode==null&&"".equals(idCode.toString().trim())){
					editReview.setIdCode(last.getIdCode());
				}else{
					editReview.setIdCode(idCode);
				}

				editReview.setMNicknameBefore(last.getMNickname());
				if (mNickname==null&&"".equals(mNickname.toString().trim())){
					editReview.setMNickname(last.getMNickname());

				}else{
					editReview.setMNickname(mNickname);
				}

				editReview.setGenderBefore(last.getGender());
				editReview.setGender(last.getGender());

				editReview.setMobileBefore(last.getMobile());
				if (mNickname==null&&"".equals(mNickname.toString().trim())){
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
				editReview.setCityBefore(last.getCity());
				editReview.setCountryBefore(last.getCountry());
				editReview.setDetialBefore(last.getDetial());
				if (province==null&&"".equals(province.toString().trim())
						&&city==null&&"".equals(city.toString().trim())
						&&country==null&&"".equals(country.toString().trim())
						&&detial==null&&"".equals(detial.toString().trim())){
					editReview.setProvince(last.getProvince());
					editReview.setCity(last.getCity());
					editReview.setCountry(last.getCountry());
					editReview.setDetial(last.getDetial());
				}else{
					editReview.setProvince(province);
					editReview.setCity(city);
					editReview.setCountry(country);
					editReview.setDetial(detial);
				}

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
				editReview.setReviewMemo("未提供材料");
				editReview.setReviewStatus(0); //审核
			}

			return editReviewMapper.saveEdit(editReview);

		}else{
			return 0;//修改失败，找不到该会员信息
		}

	}

	/*
	 * 根据编号查找会员资格信息
	 * */
	@Override
	public MemberQualification findQualificationByMCode(String mCode) {
		return qualificationMapper.findQualificationByMCode(mCode);
	}

	/*
	 * 根据编号和姓名修改会员资格表信息（更改推荐人）(弃用)
	 * */
	@Override
	public int updateQualificationByMCode(String mCode, String mName, String sponsorCode, String sponsorName, String sponsorCodeNew, String sponsorNameNew, String mDesc) {
		/*MemberEditReview last = editReviewMapper.selectLastByMCode(mCode);

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
				editReview.setReviewMemo("未提供材料");
				editReview.setReviewStatus(0); //审核
			}
			return editReviewMapper.saveEdit(editReview);
		}else{
			return 0;//修改失败，找不到该会员信息
		}*/


		return 0;
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
				editReview.setReviewMemo("未提供材料");
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
				editReview.setReviewMemo("未提供材料");
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
				editReview.setReviewMemo("未提供材料");
				editReview.setReviewStatus(0); //审核
			}
			return editReviewMapper.saveEdit(editReview);
		}else{
			return 0;//修改失败，找不到该会员信息
		}
	}

	@Override
	public List<MemberEditReview> findEditAll(String mCode, String mName, Integer updateType, Integer reviewStatus, Date updateTime) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mName",mName);
		map.put("updateType",updateType);
		map.put("reviewStatus",reviewStatus);
		map.put("updateTime",updateTime);
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
	public int updateEditById(Integer rId,Integer reviewStatus) {

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

					Map<String,Object> map = new HashMap<>();
					map.put("mCode",memberEditReview.getMCode());
					map.put("withdrawDefault",memberEditReview.getWithdrawDefault());
					//更改会员银行信息表
					int i = bankMapper.updateByMBank(map);
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
					memberBasic.setProvince(memberEditReview.getProvince());
					memberBasic.setCity(memberEditReview.getCity());
					memberBasic.setCountry(memberEditReview.getCountry());
					memberBasic.setDetial(memberEditReview.getDetial());

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
		return editReviewMapper.updateEditById(map);
	}

	@Override
	public List<MemberEditReview> findEditStatus(String mCode, String mName, Date updateTime) {
		Map<String,Object> map = new HashMap<>();
		map.put("mCode",mCode);
		map.put("mName",mName);
		map.put("updateTime",updateTime);
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

	@Override
	public int addPeriod(String periodCode, Date beginDate, Date endDate) {
		SysPeriod period = new SysPeriod();
		period.setPeriodCode(periodCode);
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




}
