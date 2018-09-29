package cn.leanshi.controller;

import cn.leanshi.model.MemberAccount;
import cn.leanshi.model.MemberAddress;
import cn.leanshi.model.MemberBank;
import cn.leanshi.model.MemberEditReview;
import cn.leanshi.model.MemberQualification;
import cn.leanshi.model.MemberRelation;
import cn.leanshi.model.Member_basic;
import cn.leanshi.model.RdRaBinding;
import cn.leanshi.model.SysPeriod;
import cn.leanshi.model.SysPeriodLog;
import cn.leanshi.model.http.ResultMsg;
import cn.leanshi.model.util.DateConverter;
import cn.leanshi.model.util.FileUtil;
import cn.leanshi.service.MemberService;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author :ldq
 * @date:2018/7/30
 * @description:leanshi cn.leanshi.controller
 */
@Controller
@ResponseBody
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@Autowired
	private HttpSession session;

	/*
	 *条件查询
	 * */
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public ResultMsg search(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
							@RequestParam(required = false,defaultValue = "5",value = "pageSize") int pageSize,
							@RequestParam(value = "mCode",required = false) String mCode,
							@RequestParam(value = "mName",required = false) String mName,
							@RequestParam(value = "mobile",required = false) String mobile,
							@RequestParam(value = "mNickname",required = false) String mNickname){

		int size=pageSize;

		PageHelper.startPage(currentPage,size);

		ResultMsg<PageInfo<Member_basic>> resultMsg = new ResultMsg<PageInfo<Member_basic>>();

		List<Member_basic> list = memberService.search(mCode,mName,mobile,mNickname);

		PageInfo<Member_basic> pageInfo = new PageInfo<Member_basic>(list);

		resultMsg.setData(pageInfo);
		resultMsg.setCode(true);
		return resultMsg;
	}

	/*
	 * 根据编号查找会员
	 * */
	@RequestMapping(value = "/findByMCode",method = RequestMethod.GET)
	public ResultMsg findByMCode(@RequestParam String mCode){

		ResultMsg<Map<String,Object>> resultMsg = new ResultMsg<Map<String,Object>>();

		Map<String,Object> map = new HashMap<String,Object>();

		Member_basic memberBasic = memberService.findByMCode(mCode);

		if (memberBasic==null){

			return ResultMsg.newInstance(false,"未搜到该会员！");
		}



		map.put("memberBasic",memberBasic);

		String mName = memberBasic.getMName();


		//mName存到session中
		session.setAttribute("mName",mName);

		//根据编号查找该会员的收货地址
		List<MemberAddress> listAdd = memberService.findAddByMCode(mCode);
		map.put("listAdd",listAdd);

		resultMsg.setData(map);
		resultMsg.setCode(true);
		return resultMsg;
	}

	/*
	 * 添加新收货地址
	 * */
	@RequestMapping(value = "/addMemAdd",method = RequestMethod.POST)
	public ResultMsg addMemAdd(@RequestParam(value = "mCode",required = false) String mCode,
							   @RequestParam(value = "consigneeName",required = false) String consigneeName,
							   @RequestParam(value = "mobile",required = false) String mobile,
							   @RequestParam(value = "phone",required = false) String phone,
							   @RequestParam(value = "addProvinceCode",required = false) String addProvinceCode,
							   @RequestParam(value = "addCityCode",required = false) String addCityCode,
							   @RequestParam(value = "addCountryCode",required = false) String addCountryCode,
							   @RequestParam(value = "addDetial",required = false) String addDetial,
							   @RequestParam(value = "addPost",required = false) String addPost,
							   @RequestParam(value = "defaultAdd",required = false) Integer defaultAdd){



		if (mCode==null&&"".equals(mCode.toString().trim())){
			return ResultMsg.newInstance(false,"会员编号未知，添加收货地址失败！");
		}
		if (consigneeName==null&&"".equals(consigneeName.toString().trim())){
			return ResultMsg.newInstance(false,"收货人未填写！");
		}
		if (mobile==null&&"".equals(mobile.toString().trim())){
			return ResultMsg.newInstance(false,"手机号码未填写！");
		}
		if (phone==null&&"".equals(phone.toString().trim())){
			return ResultMsg.newInstance(false,"电话未填写！");
		}
		if (addProvinceCode==null&&"".equals(addProvinceCode.toString().trim())){
			return ResultMsg.newInstance(false,"省份未填写！");
		}
		if (addCityCode==null&&"".equals(addCityCode.toString().trim())){
			return ResultMsg.newInstance(false,"市未填写！");
		}
		if (addCountryCode==null&&"".equals(addCountryCode.toString().trim())){
			return ResultMsg.newInstance(false,"区未填写！");
		}
		if (addDetial==null&&"".equals(addDetial.toString().trim())){
			return ResultMsg.newInstance(false,"详细地址未填写！");
		}
		if (addPost==null&&"".equals(addPost.toString().trim())){
			return ResultMsg.newInstance(false,"邮编未填写！");
		}

		MemberAddress memberAddress = new MemberAddress();
		memberAddress.setMCode(mCode);
		memberAddress.setConsigneeName(consigneeName);
		memberAddress.setMobile(mobile);
		memberAddress.setPhone(phone);
		memberAddress.setAddProvinceCode(addProvinceCode);
		memberAddress.setAddCityCode(addCityCode);
		memberAddress.setAddCountryCode(addCountryCode);
		memberAddress.setAddDetial(addDetial);
		memberAddress.setAddPost(addPost);
		memberAddress.setDefaultAdd(defaultAdd);

		int i = memberService.addMemAdd(memberAddress);
		if (i==1){
			return ResultMsg.newInstance(true,"添加成功！");
		}
		return ResultMsg.newInstance(false,"添加失败！");
	}

	/*
	 * 根据mCode查询会员所有收货地址（修改基本信息）
	 * */
	@RequestMapping(value = "/findAddAllByMCode",method = RequestMethod.GET)
	public ResultMsg findAddAllByMCode(@RequestParam String mCode){
		ResultMsg<List<MemberAddress>> resultMsg = new ResultMsg<List<MemberAddress>>();

		if (mCode==null&&"".equals(mCode.toString().trim())){
			return ResultMsg.newInstance(false,"找不到该会员收货地址！");
		}

		List<MemberAddress> addressList = memberService.findAddAllByMCode(mCode);
		if (addressList==null){
			return ResultMsg.newInstance(false,"找不到该会员收货地址！");
		}

		resultMsg.setCode(true);
		resultMsg.setData(addressList);
		return resultMsg;

	}

	/*
	 * 根据aid和mCode查询收货地址（修改基本信息）
	 * */
	@RequestMapping(value = "/findMemAddByMCode",method = RequestMethod.GET)
	public ResultMsg findMemAddByMCode(@RequestParam String mCode,@RequestParam Integer aId){

		ResultMsg<MemberAddress> resultMsg = new ResultMsg<MemberAddress>();

		if (mCode==null&&"".equals(mCode.toString().trim())&&aId==null&&"".equals(aId.toString().trim())){
			return ResultMsg.newInstance(false,"找不到该会员收货地址！");
		}

		MemberAddress address = memberService.findMemAddByMCode(mCode,aId);

		if (address==null){
			return ResultMsg.newInstance(false,"找不到该会员收货地址！");
		}

		resultMsg.setCode(true);
		resultMsg.setData(address);

		return resultMsg;

	}

	/*
	 * 修改收货地址（修改基本信息）
	 * */
	@RequestMapping(value = "/editMemAdd",method = RequestMethod.POST)
	public ResultMsg editMemAdd(@RequestParam(value = "aId",required = false) Integer aId,
								@RequestParam(value = "mCode",required = false) String mCode,
								@RequestParam(value = "consigneeName",required = false) String consigneeName,
								@RequestParam(value = "mobile",required = false) String mobile,
								@RequestParam(value = "phone",required = false) String phone,
								@RequestParam(value = "addProvinceCode",required = false) String addProvinceCode,
								@RequestParam(value = "addCityCode",required = false) String addCityCode,
								@RequestParam(value = "addCountryCode",required = false) String addCountryCode,
								@RequestParam(value = "addDetial",required = false) String addDetial,
								@RequestParam(value = "addPost",required = false) String addPost,
								@RequestParam(value = "defaultAdd",required = false) Integer defaultAdd){


		if (aId==null&&"".equals(aId.toString().trim())){
			return ResultMsg.newInstance(false,"收货地址id未获取,修改失败！");
		}
		if (mCode==null&&"".equals(mCode.toString().trim())){
			return ResultMsg.newInstance(false,"会员编号未知，添加收货地址失败！");
		}
		if (consigneeName==null&&"".equals(consigneeName.toString().trim())){
			return ResultMsg.newInstance(false,"收货人未填写！");
		}
		if (mobile==null&&"".equals(mobile.toString().trim())){
			return ResultMsg.newInstance(false,"手机号码未填写！");
		}
		if (phone==null&&"".equals(phone.toString().trim())){
			return ResultMsg.newInstance(false,"电话未填写！");
		}
		if (addProvinceCode==null&&"".equals(addProvinceCode.toString().trim())){
			return ResultMsg.newInstance(false,"省份未填写！");
		}
		if (addCityCode==null&&"".equals(addCityCode.toString().trim())){
			return ResultMsg.newInstance(false,"市未填写！");
		}
		if (addCountryCode==null&&"".equals(addCountryCode.toString().trim())){
			return ResultMsg.newInstance(false,"区未填写！");
		}
		if (addDetial==null&&"".equals(addDetial.toString().trim())){
			return ResultMsg.newInstance(false,"详细地址未填写！");
		}
		if (addPost==null&&"".equals(addPost.toString().trim())){
			return ResultMsg.newInstance(false,"邮编未填写！");
		}


		MemberAddress memberAddress = new MemberAddress();
		memberAddress.setAId(aId);
		memberAddress.setMCode(mCode);
		memberAddress.setConsigneeName(consigneeName);
		memberAddress.setMobile(mobile);
		memberAddress.setPhone(phone);
		memberAddress.setAddProvinceCode(addProvinceCode);
		memberAddress.setAddCityCode(addCityCode);
		memberAddress.setAddCountryCode(addCountryCode);
		memberAddress.setAddDetial(addDetial);
		memberAddress.setAddPost(addPost);
		memberAddress.setDefaultAdd(defaultAdd);

		int i = memberService.editMemAdd(memberAddress);
		if (i==1){
			return ResultMsg.newInstance(true,"修改会员收货地址成功！");
		}
		return ResultMsg.newInstance(false,"修改会员收货地址失败！");

	}


	/*
	 * 删除收货地址（修改基本信息）
	 * */
	@RequestMapping(value = "/delMemAddByAId",method = RequestMethod.GET)
	public ResultMsg delMemAddByAId(@RequestParam(value = "aId",required = false) Integer aId,
									@RequestParam(value = "mCode",required = false) String mCode){

		int i = memberService.delMemAddByAId(aId,mCode);
		if (i==1){
			return ResultMsg.newInstance(true,"修改会员收货地址成功！");
		}
		return ResultMsg.newInstance(false,"修改会员收货地址失败！");
	}


	/*
	 * 设置默认收货地址（修改基本信息）
	 * */
	@RequestMapping(value = "/defAddByAIdAndMCode",method = RequestMethod.POST)
	public ResultMsg defAddByAIdAndMCode(@RequestParam(value = "aId",required = false) Integer aId,
										 @RequestParam(value = "mCode",required = false) String mCode,
										 @RequestParam(value = "defaultAdd",required = false) Integer defaultAdd){

		int i = memberService.defAddByAIdAndMCode(aId,mCode,defaultAdd);
		if (i==1){
			return ResultMsg.newInstance(true,"设置默认收货地址成功！");
		}
		return ResultMsg.newInstance(false,"设置默认收货地址失败！");
	}


	/*
	 * 根据编和姓名修改会员基本信息（修改基本信息）
	 * */
	@RequestMapping(value = "/updateByMCodeAndMName",method = RequestMethod.POST)
	public ResultMsg updateByMCodeAndMName(@RequestParam(value = "mCode",required = false) String mCode,@RequestParam(value = "mName",required = false) String mName,
										   @RequestParam(value = "mNickname",required = false) String mNickname,@RequestParam(value = "gender",required = false) Integer gender,
										   @RequestParam(value = "email",required = false) String email,@RequestParam(value = "province",required = false) String province,
										   @RequestParam(value = "city",required = false) String city,@RequestParam(value = "country",required = false) String country,
										   @RequestParam(value = "detial",required = false) String detial,@RequestParam(value = "addPost",required = false) String addPost,
										   @RequestParam(value = "mDesc",required = false) String mDesc){

		if (gender==null){
			return ResultMsg.newInstance(false,"修改会员信息失败！");
		}

		Member_basic mem1 = memberService.findByMCode(mCode);
		if (mem1.getMNickname()!=mNickname){
			List<Member_basic> mem2 = memberService.findMemByMNickName(mCode,mNickname);
			if (mem2!=null&&!mem2.isEmpty()){
				return ResultMsg.newInstance(false,"昵称已存在，修改会员信息失败！");
			}
		}


		Member_basic member = new Member_basic();
		member.setMCode(mCode);
		member.setMName(mName);
		member.setMNickname(mNickname);
		member.setGender(gender);
		member.setEmail(email);
		member.setProvince(province);
		member.setCity(city);
		member.setCountry(country);
		member.setDetial(detial);
		member.setAddPost(addPost);

		int i =memberService.updateByMCodeAndMName(member,mDesc);
		if (i==1){
			return ResultMsg.newInstance(true,"修改会员信息成功！");
		}
		return ResultMsg.newInstance(false,"修改会员信息失败！");
	}


	/*
	 * 根据编号查找会员银行信息
	 * */
	@RequestMapping(value = "/findMBankByMCode",method = RequestMethod.GET)
	public ResultMsg findMBankByMCode(@RequestParam String mCode){
		ResultMsg<Map<String,Object>> resultMsg = new ResultMsg<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		//查询银行卡信息
		List<MemberBank> memberBank = memberService.findMBankByMCode(mCode);
		map.put("memberBank",memberBank);
		//查询基本信息
		Member_basic memberBasic = memberService.findByMCode(mCode);
		map.put("memberBasic",memberBasic);

		resultMsg.setCode(true);
		resultMsg.setData(map);

		return resultMsg;
	}

	/*
	 * 添加银行卡
	 * */
	@RequestMapping(value = "/addBankByMCode",method = RequestMethod.POST)
	public ResultMsg addBankByMCode(@RequestParam String mCode,@RequestParam String bankCode,
									@RequestParam String accName,@RequestParam String accCode){

		if(mCode==null&&"".equals(mCode.toString().trim())){
			return ResultMsg.newInstance(false,"会员编号不能为空！");
		}
		if(bankCode==null&&"".equals(bankCode.toString().trim())){
			return ResultMsg.newInstance(false,"开户行不能为空！");
		}
		if(accName==null&&"".equals(accName.toString().trim())){
			return ResultMsg.newInstance(false,"户名不能为空！");
		}
		if(accCode==null&&"".equals(accCode.toString().trim())){
			return ResultMsg.newInstance(false,"银行卡账号不能为空！");
		}

		MemberBank bank = new MemberBank();
		bank.setMCode(mCode);
		bank.setBankCode(bankCode);
		bank.setAccName(accName);
		bank.setAccCode(accCode);
		int i = memberService.addBankByMCode(bank);
		if (i==1){
			return ResultMsg.newInstance(true,"添加成功！");
		}
		return ResultMsg.newInstance(false,"添加失败！");
	}

	/*
	 * 根据oid删除会员银行信息（解绑银行卡）
	 * */
	@RequestMapping(value = "/delBankByOid",method = RequestMethod.GET)
	public ResultMsg delBankByOid(@RequestParam Integer oId,@RequestParam String mCode){


		int i = memberService.delBankByOid(oId,mCode);
		if (i==1){
			return ResultMsg.newInstance(true,"解绑成功！");
		}
		return ResultMsg.newInstance(false,"解绑失败！");
	}

	/*
	 * 根据编号和姓名修改银行卡信息（修改敏感信息）
	 * */
	@RequestMapping(value = "/updateByMBank",method = RequestMethod.POST)
	public ResultMsg updateByMBank(@RequestParam String mCode,@RequestParam String mName,
								   @RequestParam String mobile,@RequestParam String mNickname,
								   @RequestParam String withdrawDefault,@RequestParam String mDesc){

		Member_basic mem1 = memberService.findByMCode(mCode);
		if (mem1.getMobile()!=mobile){
			List<Member_basic> mem2 = memberService.findMemByMobile(mCode,mobile);
			if (mem2!=null&&!mem2.isEmpty()){
				return ResultMsg.newInstance(false,"该手机号已绑定其他会员，修改会员信息失败！");
			}
		}

		if (mem1.getMNickname()!=mNickname){
			List<Member_basic> mem2 = memberService.findMemByMNickName(mCode,mNickname);
			if (mem2!=null&&!mem2.isEmpty()){
				return ResultMsg.newInstance(false,"昵称已存在，修改会员信息失败！");
			}
		}


		int updateType =1 ;
		//根据mCode和修改类型查找是否有正在审核的数据
		List<MemberEditReview> editReviewList = memberService.findEditByMCode(mCode,updateType);
		if (!editReviewList.isEmpty()||editReviewList.size()!=0){
			return ResultMsg.newInstance(false,"已有在待审信息，请等待待审通过后才能再次修改！");
		}


		int i = memberService.updateByMBank(mCode,mName,mobile,mNickname,withdrawDefault,mDesc);
		if (i==1){
			return ResultMsg.newInstance(true,"修改成功！");
		}
		return ResultMsg.newInstance(false,"修改失败！");
	}


	/*
	 * 根据mCode查询会员账户信息（会员更名）
	 * */
	@RequestMapping(value = "/findMemAccountByMCode",method = RequestMethod.GET)
	public ResultMsg findMemAccountByMCode(@RequestParam(value = "mCode",required = false) String mCode){
		ResultMsg<MemberAccount> resultMsg = new ResultMsg<MemberAccount>();

		MemberAccount account = memberService.findMemAccountByMCode(mCode);
		if (account==null){
			resultMsg.setCode(false);
			return resultMsg;
		}

		resultMsg.setCode(true);
		resultMsg.setData(account);

		return resultMsg;
	}


	/*
	 * 根据编和姓名修改会员证件信息（会员更名）
	 * */
	@RequestMapping(value = "/updateIdByMCodeAndMName",method = RequestMethod.POST)
	public ResultMsg updateIdByMCodeAndMName(@RequestParam(value = "mCode",required = false) String mCode,
											 @RequestParam(value = "mName",required = false) String mName,
											 @RequestParam(value = "newMName",required = false) String newMName,
											 @RequestParam(value = "idType",required = false) Integer idType,
											 @RequestParam(value = "idCode",required = false) String idCode,
											 @RequestParam(value = "mNickname",required = true) String mNickname,
											 @RequestParam(value = "mobile",required = true) String mobile,
											 @RequestParam(value = "uploadPath",required = false) String uploadPath,
											 @RequestParam(value = "mDesc",required = false) String mDesc){

		if (mCode==null&&"".equals(mCode.toString().trim())){
			return ResultMsg.newInstance(false,"会员编号未知，修改失败！");
		}

		if (newMName==null&&"".equals(newMName.toString().trim())){
			return ResultMsg.newInstance(false,"新姓名未填写！");
		}
		if (idType==null&&"".equals(idType.toString().trim())){
			return ResultMsg.newInstance(false,"证件类型未填写！");
		}
		if (idCode==null&&"".equals(idCode.toString().trim())){
			return ResultMsg.newInstance(false,"证件号码未填写！");
		}
		if (uploadPath==null&&"".equals(uploadPath.toString().trim())){
			return ResultMsg.newInstance(false,"没上传图片！");
		}

		Member_basic mem1 = memberService.findByMCode(mCode);
		if (mem1.getMobile()!=mobile){
			List<Member_basic> mem2 = memberService.findMemByMobile(mCode,mobile);
			if (mem2!=null&&!mem2.isEmpty()){
				return ResultMsg.newInstance(false,"该手机号已绑定其他会员，修改会员信息失败！");
			}
		}
		if (mem1.getMNickname()!=mNickname){
			List<Member_basic> mem2 = memberService.findMemByMNickName(mCode,mNickname);
			if (mem2!=null&&!mem2.isEmpty()){
				return ResultMsg.newInstance(false,"昵称已存在，修改会员信息失败！");
			}
		}

		int updateType =2 ;
		//根据mCode和修改类型查找是否有正在审核的数据
		List<MemberEditReview> editReviewList = memberService.findEditByMCode(mCode,updateType);
		if (!editReviewList.isEmpty()||editReviewList.size()!=0){
			return ResultMsg.newInstance(false,"已有在待审信息，请等待待审通过后才能再次修改！");
		}

		int i =memberService.updateIdByMCodeAndMName(mCode,mName,newMName,idType,idCode,mNickname,mobile,uploadPath,mDesc);
		if (i==1){
			return ResultMsg.newInstance(true,"修改成功,等待审核！");
		}
		return ResultMsg.newInstance(false,"修改失败！");
	}



	@Value("${upload.path}")
	private String uploadPath;

	//图片上传
	@RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
	public ResultMsg uploadFile(@RequestParam("file") MultipartFile file) {

		// 获取文件名
		String fileName = file.getOriginalFilename();

		// 设置文件存储路径
		String filePath = uploadPath + "//" +"img//";

		//设置随机文件名
		String UUIDFileName = FileUtil.getFileName(fileName);
		String path = filePath + UUIDFileName;

		File dest = new File(path);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();// 新建文件夹
		}

		try {
			file.transferTo(dest);// 文件写入
		} catch (IOException e) {
			e.printStackTrace();
		}

		ResultMsg<String> resultMsg = new ResultMsg<String>();
		resultMsg.setCode(true);
		resultMsg.setData("/img/"+UUIDFileName);
		return resultMsg;
	}



	/*
	 *禁用所有已绑定银行卡
	 * */
	@RequestMapping(value = "/outAllBankByMCode",method = RequestMethod.GET)
	public ResultMsg outAllBankByMCode(@RequestParam String mCode){
		boolean f = memberService.outAllBankByMCode(mCode);

		if (f==true){
			return ResultMsg.newInstance(true,"删除所有绑定银行信息成功！");
		}
		return ResultMsg.newInstance(false,"删除所有绑定银行信息失败！");
	}

	/*
	 *禁用所有收货地址
	 * */
	@RequestMapping(value = "/outAllAddByMCode",method = RequestMethod.GET)
	public ResultMsg outAllAddByMCode(@RequestParam String mCode){
		boolean f = memberService.outAllAddByMCode(mCode);
		if (f==true){
			return ResultMsg.newInstance(true,"删除所有收货地址成功！");
		}
		return ResultMsg.newInstance(false,"删除所有收货地址失败！");
	}


	/*
	 * 根据编号查找会员状态、级别及关系信息（更改推荐人）（更改会员级别）
	 * */
	@RequestMapping(value = "/findRelationByMCode",method = RequestMethod.GET)
	public ResultMsg findRelationByMCode(@RequestParam(value = "mCode",required = false) String mCode){

		if (mCode==null){
			return ResultMsg.newInstance(false,"会员编号为空！");
		}

		ResultMsg<Map<String,Object>> resultMsg = new ResultMsg<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();


		MemberRelation memberRelation = memberService.findRelationByMCode(mCode);
		if (memberRelation==null){
			return ResultMsg.newInstance(false,"没有该会员状态级别信息！");
		}

		int rank = memberRelation.getRank();
		String rankName = memberService.findRank(rank);

		map.put("memberRelation",memberRelation);
		map.put("rankName",rankName);

		resultMsg.setData(map);
		resultMsg.setCode(true);
		return resultMsg;
	}


	/*
	 * 修改会员状态、级别及关系信息（更改推荐人）
	 * */
	@RequestMapping(value = "/updateRelaSponByMCode",method = RequestMethod.POST)
	public ResultMsg updateRelaSponByMCode(@RequestParam String mCode,@RequestParam String mName,
										   @RequestParam String sponsorCode,@RequestParam String sponsorName,
										   @RequestParam String sponsorCodeNew,@RequestParam String sponsorNameNew,
										   @RequestParam String mDesc){

		if (sponsorCode.equals(sponsorCodeNew)){
			return ResultMsg.newInstance(false,"新推荐人与旧推荐人相同,修改失败！");
		}

		Member_basic mem = memberService.findByMCode(sponsorCode);
		if (mem==null){
			return ResultMsg.newInstance(false,"输入的推荐人不存在！");
		}

		int updateType =3 ;
		//根据mCode和修改类型查找是否有正在审核的数据
		List<MemberEditReview> editReviewList = memberService.findEditByMCode(mCode,updateType);
		if (!editReviewList.isEmpty()||editReviewList.size()!=0){
			return ResultMsg.newInstance(false,"已有在待审信息，请等待待审通过后才能再次修改！");
		}

		int i = memberService.updateRelaSponByMCode(mCode,mName,sponsorCode,sponsorName,sponsorCodeNew,sponsorNameNew,mDesc);
		if (i==1){
			return ResultMsg.newInstance(true,"修改成功,等待审核！");
		}
		return ResultMsg.newInstance(false,"修改失败！");

	}


	/*
	 * 根据编号和姓名修改会员状态、级别及关系信息（更改会员级别）
	 * */
	@RequestMapping(value = "/updateRelationByMCode",method = RequestMethod.POST)
	public ResultMsg updateRelationByMCode(@RequestParam String mCode,@RequestParam String mName,
										   @RequestParam String rankName,@RequestParam String rankNameNew,
										   @RequestParam String mDesc){

		int updateType =4 ;
		//根据mCode和修改类型查找是否有正在审核的数据
		List<MemberEditReview> editReviewList = memberService.findEditByMCode(mCode,updateType);
		if (!editReviewList.isEmpty()||editReviewList.size()!=0){
			return ResultMsg.newInstance(false,"已有在待审信息，请等待待审通过后才能再次修改！");
		}

		Integer rank = memberService.findRankByRankName(rankName);
		Integer rankNew = memberService.findRankByRankName(rankNameNew);

		if (rank==null||rankNew==null){
			return ResultMsg.newInstance(false,"没有该等级,修改失败！");
		}

		int i = memberService.updateRelationByMCode(mCode,mName,rank,rankNew,mDesc);
		if (i==1){
			return ResultMsg.newInstance(true,"修改成功！");
		}
		return ResultMsg.newInstance(false,"修改失败！");
	}



	/*
	 * 根据编号查找会员及与老会员关系信息（与老会员绑定）
	 * */
	@RequestMapping(value = "/findBindingByMCode",method = RequestMethod.GET)
	public ResultMsg findBindingByMCode(@RequestParam String mCode){

		ResultMsg<Map<String,Object>> resultMsg = new ResultMsg<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		RdRaBinding rdRaBinding = memberService.findBindingByMCode(mCode);

		//查找会员基本数据
		Member_basic mem = memberService.findByMCode(mCode);
		map.put("member",mem);

		if(rdRaBinding==null){
			//未绑定
			//查找会员推荐人资料
			MemberRelation relation = memberService.findRelationByMCode(mCode);
			if (relation!=null){
				String sponsorCode = relation.getSponsorCode();
				map.put("sponsorCode",sponsorCode);
				//根据推荐人编号查找推荐人基本信息
				Member_basic sponsor = memberService.findByMCode(sponsorCode);
				String sponsorName = sponsor.getMName();
				String sponsorIdCode = sponsor.getIdCode();
				map.put("sponsorName",sponsorName);
				map.put("sponsorIdCode",sponsorIdCode);
			}

			resultMsg.setCode(false);
			resultMsg.setMsg("该会员没有绑定老会员");



		}else{
			//已绑定
			map.put("rdRaBinding",rdRaBinding);
			resultMsg.setCode(true);
		}

		resultMsg.setData(map);
		return resultMsg;

	}





	/*
	 * 修改会员及与老会员信息（与老会员绑定）（已绑定）
	 * */
	@RequestMapping(value = "/updateBindingByMCode",method = RequestMethod.POST)
	public ResultMsg updateBindingByMCode(@RequestParam(value = "mCode",required = false) String mCode,
										  @RequestParam(value = "mName",required = false) String mName,
										  @RequestParam(value = "raCode",required = false) String raCode,
										  @RequestParam(value = "raName",required = false) String raName,
										  @RequestParam(value = "raCodeNew",required = true) String raCodeNew,
										  @RequestParam(value = "raNameNew",required = true) String raNameNew,
										  @RequestParam(value = "raIdType",required = true) Integer raIdType,
										  @RequestParam(value = "raIdCode",required = true) String raIdCode,
										  @RequestParam(value = "mDesc",required = false) String mDesc){

		if (raCodeNew==null&&"".equals(raCodeNew.toString().trim())||raNameNew==null&&"".equals(raNameNew.toString().trim())){
			return ResultMsg.newInstance(false,"输入的新绑定老会员账号或新绑定新绑定老会员姓名为空！");
		}

		if (raCode==raCodeNew) {
			return ResultMsg.newInstance(false,"与已绑定的老会员相同，请输入别的老会员编号！");
		}

		int updateType =5 ;
		//根据mCode和修改类型查找是否有正在审核的数据
		List<MemberEditReview> editReviewList = memberService.findEditByMCode(mCode,updateType);
		if (!editReviewList.isEmpty()||editReviewList.size()!=0){
			return ResultMsg.newInstance(false,"已有在待审信息，请等待待审通过后才能再次修改！");
		}

		int i = memberService.updateBindingByMCode(mCode,mName,raCode,raName,raCodeNew,raNameNew,raIdType,raIdCode,mDesc);
		if (i==1){
			return ResultMsg.newInstance(true,"修改绑定成功！");
		}else {
			return ResultMsg.newInstance(false,"修改绑定失败！");
		}

	}

	/*
	 * 修改会员及与老会员信息（与老会员绑定）（未绑定）
	 * */
	@RequestMapping(value = "/addBinding",method = RequestMethod.POST)
	public ResultMsg addBinding(@RequestParam(value = "mCode",required = false) String mCode,
								@RequestParam(value = "raCode",required = true) String raCode,
								@RequestParam(value = "raName",required = true) String raName,
								@RequestParam(value = "raIdType",required = true) Integer raIdType,
								@RequestParam(value = "raIdCode",required = true) String raIdCode,
								@RequestParam(value = "raSponsorName",required = true) String raSponsorName,
								@RequestParam(value = "raSponsorCode",required = true) String raSponsorCode,
								@RequestParam(value = "raSponsorIdCode",required = true) String raSponsorIdCode){

		if (raCode==null&&"".equals(raCode.toString().trim())) {
			return ResultMsg.newInstance(false,"请输入正确的会员编号！");
		}


		int i = memberService.addBinding(mCode,raCode,raName,raIdType,raIdCode,raSponsorName,raSponsorCode,raSponsorIdCode);
		if (i==1){
			return ResultMsg.newInstance(true,"绑定成功！");
		}else {
			return ResultMsg.newInstance(false,"绑定失败！");
		}
	}

	/*
	*查找批量绑定数据
	 */
	@RequestMapping(value = "/findBindingAll",method = RequestMethod.GET)
	public ResultMsg findBindingAll(@RequestParam(value = "mCode",required = false) String mCode){
		if (mCode==null){
			return ResultMsg.newInstance(false,"mCode会员编号为空，查找失败！");
		}
		ResultMsg<Map<String,Object>> resultMsg = new ResultMsg<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();


		Member_basic byMCode = memberService.findByMCode(mCode);
		if (byMCode==null){
			return ResultMsg.newInstance(false,"mCode会员编号不存在，查找失败！");
		}
		map.put("mName",byMCode.getMName());
		map.put("idType",byMCode.getIdType());
		map.put("idCode",byMCode.getIdCode());


		MemberRelation relation = memberService.findRelationByMCode(mCode);
		if (relation==null){
			return ResultMsg.newInstance(false,"mCode会员编号状态表不存在，查找失败！");
		}

		Member_basic sponsor = memberService.findByMCode(relation.getSponsorCode());
		if (sponsor==null){
			return ResultMsg.newInstance(false,"mCode会员编号的推荐人不存在，查找失败！");
		}
		map.put("sponsorName",sponsor.getMName());
		map.put("sponsorIdType",sponsor.getIdType());
		map.put("sponsorIdCode",sponsor.getIdCode());

		RdRaBinding binding = memberService.findBindingByMCode(mCode);
		if (binding==null){
			map.put("binding","未绑定老会员");
		}else{
			map.put("raCode",binding.getRaCode());
			map.put("raName",binding.getRaName());
			map.put("raIdType",binding.getRaIdType());
			map.put("raIdCode",binding.getRaIdCode());

			map.put("raSponsorName",binding.getRaSponsorName());
			map.put("raSponsorCode",binding.getRaSponsorCode());
			/*Member_basic raSponsor = memberService.findByMCode(binding.getRaSponsorCode());
			if (raSponsor==null){
				return ResultMsg.newInstance(false,"mCode会员绑定的老会员推荐人不存在，查找失败！");
			}*/
			map.put("rasponsorIdType","");
			map.put("raSponsorIdCode","");
		}

		resultMsg.setData(map);
		resultMsg.setCode(true);

		return resultMsg;
	}



	/*
	 *批量绑定
	 */
	@RequestMapping(value = "/addBindingAll",method = RequestMethod.POST)
	public ResultMsg addBindingAll(@RequestParam(value = "mCode",required = false) String mCode,
								   @RequestParam(value = "mName",required = false) String mName,
								   @RequestParam(value = "idType",required = false) Integer idType,
								   @RequestParam(value = "idCode",required = false) String idCode,
								   @RequestParam(value = "sponsorName",required = false) String sponsorName,
								   @RequestParam(value = "sponsorIdType",required = false) String sponsorIdType,
								   @RequestParam(value = "sponsorIdCode",required = false) String sponsorIdCode,
								   @RequestParam(value = "raCode",required = false) String raCode,
								   @RequestParam(value = "raName",required = false) String raName,
								   @RequestParam(value = "raIdType",required = false) Integer raIdType,
								   @RequestParam(value = "raIdCode",required = false) String raIdCode,
								   @RequestParam(value = "raSponsorName",required = false) String raSponsorName,
								   @RequestParam(value = "raSponsorCode",required = false) String raSponsorCode,
								   @RequestParam(value = "rasponsorIdType",required = false) String rasponsorIdType,
								   @RequestParam(value = "raSponsorIdCode",required = false) String raSponsorIdCode){
		if (mCode==null||raCode==null){
			return ResultMsg.newInstance(false,"新会员编号或老会员编号为空，绑定失败！");
		}

		if (mCode!=null&&raCode!=null&&mCode!=raCode){
			return ResultMsg.newInstance(false,"新会员编号和老会员编号不一致，绑定失败！");
		}

		if (mName!=null&&raName!=null&&mName!=raName){
			return ResultMsg.newInstance(false,"新会员姓名和老会员姓名不一致，绑定失败！");
		}

		if (idType!=null&&raIdType!=null&&idType!=raIdType){
			return ResultMsg.newInstance(false,"新会员证件类型和老会员证件类型不一致，绑定失败！");
		}

		if (idCode!=null&&raIdCode!=null&&idCode!=raIdCode){
			return ResultMsg.newInstance(false,"新会员证件号码和老会员证件号码不一致，绑定失败！");
		}

		if (sponsorName!=null&&raSponsorName!=null&&sponsorName!=raSponsorName){
			return ResultMsg.newInstance(false,"新会员推荐人和老会员推荐人不一致，绑定失败！");
		}

		if (sponsorIdType!=null&&rasponsorIdType!=null&&sponsorIdType!=rasponsorIdType){
			return ResultMsg.newInstance(false,"新会员推荐人证件类型和老会员推荐人证件类型不一致，绑定失败！");
		}

		if (sponsorIdCode!=null&&raSponsorIdCode!=null&&sponsorIdCode!=raSponsorIdCode){
			return ResultMsg.newInstance(false,"新会员推荐人证件号码和老会员推荐人证件号码不一致，绑定失败！");
		}


		int i = memberService.addBinding(mCode,raCode,raName,raIdType,raIdCode,raSponsorName,raSponsorCode,raSponsorIdCode);
		if (i==1){
			return ResultMsg.newInstance(true,"绑定成功！");
		}else {
			return ResultMsg.newInstance(false,"绑定失败！");
		}
	}






	/*
	 * 根据条件查找所有修改记录
	 * */
	@RequestMapping(value = "/findEditAll",method = RequestMethod.POST)
	public ResultMsg findEditAll(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
								 @RequestParam(required = false,defaultValue = "5",value = "pageSize") int pageSize,
								 @RequestParam String mCode, @RequestParam String mName,
								 @RequestParam Integer updateType, @RequestParam Integer reviewStatus,
								 @RequestParam String updateTimeStar, @RequestParam String updateTimeEnd){

		int size=pageSize;

		PageHelper.startPage(currentPage,size);


		ResultMsg<PageInfo<MemberEditReview>> resultMsg = new ResultMsg<PageInfo<MemberEditReview>>();

		DateConverter dateConverter = new DateConverter();
		Date timeStar = dateConverter.convert(updateTimeStar);
		Date timeEnd = dateConverter.convert(updateTimeEnd);

		List<MemberEditReview> list = memberService.findEditAll(mCode,mName,updateType,reviewStatus,timeStar,timeEnd);

		PageInfo<MemberEditReview> pageInfo = new PageInfo<MemberEditReview>(list);

		resultMsg.setData(pageInfo);
		resultMsg.setCode(true);
		return resultMsg;
	}


	/*
	 * 根据id查找会员修改信息
	 * */
	@RequestMapping(value = "/findEditById",method = RequestMethod.GET)
	public ResultMsg findEditById(@RequestParam Integer rId){

		if (rId==null||"".equals(rId.toString().trim())||!"java.lang.Integer".equals(rId.getClass().getName().toString())||rId==0){
			return ResultMsg.newInstance(false,"没有该条修改信息！");
		}
		ResultMsg<MemberEditReview> resultMsg = new ResultMsg<MemberEditReview>();
		MemberEditReview memberEditReview = memberService.findEditById(rId);
		resultMsg.setData(memberEditReview);

		System.out.println(resultMsg);

		return resultMsg;
	}

	/*
	 * 根据id修改会员修改信息
	 * */
	@RequestMapping(value = "/updateEditById",method = RequestMethod.POST)
	public ResultMsg updateEditById(@RequestParam Integer rId,@RequestParam Integer reviewStatus,@RequestParam String reviewMemo){

		if (rId==null||"".equals(rId.toString().trim())||rId==0){
			return ResultMsg.newInstance(false,"审核失败！");
		}

		if (reviewStatus>3){
			return ResultMsg.newInstance(false,"审核失败！");
		}

		if (reviewStatus==3){
			return ResultMsg.newInstance(false,"无需审核！");
		}

		try {
			int i = memberService.updateEditById(rId,reviewStatus,reviewMemo);
			if (i==1){
				return ResultMsg.newInstance(true,"审核成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResultMsg.newInstance(false,"审核失败！");
	}

	/*
	 * 根据条件查找所有待审修改信息
	 * */
	@RequestMapping(value = "/findEditStatus",method = RequestMethod.POST)
	public ResultMsg findEditStatus(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
									@RequestParam(required = false,defaultValue = "5",value = "pageSize") int pageSize,
									@RequestParam String mCode,
									@RequestParam String mName,
									@RequestParam String updateTimeStar, @RequestParam String updateTimeEnd){

		int size=pageSize;

		PageHelper.startPage(currentPage,size);

		ResultMsg<PageInfo<MemberEditReview>> resultMsg = new ResultMsg<PageInfo<MemberEditReview>>();
		DateConverter dateConverter = new DateConverter();
		Date timeStar = dateConverter.convert(updateTimeStar);
		Date timeEnd = dateConverter.convert(updateTimeEnd);
		List<MemberEditReview> list = memberService.findEditStatus(mCode,mName,timeStar,timeEnd);

		PageInfo<MemberEditReview> pageInfo = new PageInfo<MemberEditReview>(list);

		resultMsg.setData(pageInfo);
		resultMsg.setCode(true);

		return resultMsg;
	}


	/*
	 * 查看所有业务周期
	 * */
		@RequestMapping(value = "/findPeriodAll",method = RequestMethod.GET)
	public ResultMsg findPeriodAll(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
									@RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize){

		int size=pageSize;

		PageHelper.startPage(currentPage,size);

		ResultMsg<PageInfo<SysPeriod>> resultMsg = new ResultMsg<PageInfo<SysPeriod>>();

		List<SysPeriod> list = memberService.findPeriodAll();

		PageInfo<SysPeriod> pageInfo = new PageInfo<SysPeriod>(list);
		resultMsg.setCode(true);
		resultMsg.setData(pageInfo);
		return resultMsg;
	}


	/*
	 * 添加周期
	 * */
	@RequestMapping(value = "/addPeriod",method = RequestMethod.POST)
	public ResultMsg addPeriod(@RequestParam(value = "periodCode",required = false) String periodCode,
							   @RequestParam(value = "prePeriod",required = false) String prePeriod,
							   @RequestParam(value = "beginDateS",required = false) String beginDateS,
							   @RequestParam(value = "endDateS",required = false) String endDateS){
		if (periodCode==null||"".equals(periodCode)){
			return ResultMsg.newInstance(false,"添加周期失败，请输入正确的周期！");
		}
		DateConverter dateConverter = new DateConverter();
		if (beginDateS==null&&"".equals(beginDateS.toString().trim())&&endDateS==null&&"".equals(endDateS.toString().trim())){

			return ResultMsg.newInstance(false,"添加周期失败，请输入正确的日期！");

		}

		SysPeriod period1 = memberService.findPeriod(periodCode);
		if (period1!=null){
			return ResultMsg.newInstance(false,"已有该月周期,添加周期失败");
		}

		//开始时间
		Date beginDate = dateConverter.convert(beginDateS+" "+"00:00:00");
		//结束时间
		Date endDate = dateConverter.convert(endDateS+" "+"23:59:59");

		//上一周期
		SysPeriod periodLast = memberService.findPeriod(prePeriod);
		//上一期结束时间
		Date endDateBefore=null;
		if (periodLast!=null){
			if (periodLast.getSalesStatus()==0){
				return ResultMsg.newInstance(false,prePeriod+"周期还未开始,不可多次添加周期");
			}
			endDateBefore = periodLast.getEndDate();
		}

		if (endDateBefore!=null){
			if (beginDate.getTime()<endDateBefore.getTime()){
				return ResultMsg.newInstance(false,"开始时间不能超过上一周期结束是时间，添加周期失败！");
			}
		}

		int i = memberService.addPeriod(periodCode,prePeriod,beginDate,endDate);
		if (i==1){
			if (periodLast!=null){
				//把本周期添加到上一周期的下一业绩周期
				memberService.updatePeriodAddNext(prePeriod,periodCode);
			}
			return ResultMsg.newInstance(true,"添加成功！");
		}else {
			return ResultMsg.newInstance(false,"添加失败！");
		}
	}


	/*
	 * 修改周期
	 * */
	@RequestMapping(value = "/editPeriod",method = RequestMethod.POST)
	public ResultMsg editPeriod(@RequestParam(value = "periodCode",required = false) String periodCode,
								@RequestParam(value = "beginDateS",required = false) String beginDateS,
								@RequestParam(value = "endDateS",required = false) String endDateS){

		if (periodCode==null||"".equals(periodCode)){
			return ResultMsg.newInstance(false,"修改周期失败，请输入正确的周期！");
		}

		SysPeriod period = memberService.findPeriod(periodCode);
		if (period==null){
			return ResultMsg.newInstance(false,"没有找到该月周期");
		}

		DateConverter dateConverter = new DateConverter();

		int salesStatus = period.getSalesStatus();
		if (salesStatus<3){
			if(salesStatus>0){

				if (endDateS==null&&"".equals(endDateS.toString().trim())){
					return ResultMsg.newInstance(false,"修改周期失败，请输入正确的结束日期！");
				}

				Date endDate = dateConverter.convert(endDateS+" "+"23:59:59");

				int i = memberService.editPeriod(periodCode,endDate);
				if (i==1){
					return ResultMsg.newInstance(true,"修改周期成功！");
				}else {
					return ResultMsg.newInstance(false,"修改周期失败！");
				}

			}else{
				if (beginDateS==null&&"".equals(beginDateS.toString().trim())&&endDateS==null&&"".equals(endDateS.toString().trim())){
					return ResultMsg.newInstance(false,"修改周期失败，请输入正确的日期！");
				}

				Date beginDate = dateConverter.convert(beginDateS+" "+"00:00:00");
				Date endDate = dateConverter.convert(endDateS+" "+"23:59:59");

				//根据这一周期查找是否有上一期周期
				SysPeriod periodLast = memberService.findPrePeriod(periodCode);
				//上一期结束时间
				String nextPeriodBefore = "";
				if (periodLast!=null){
					nextPeriodBefore = periodLast.getNextPeriod();
				}

				if (!"".equals(nextPeriodBefore)){
					Date endDateBefore = dateConverter.convert(nextPeriodBefore);
					if (beginDate.getTime()<endDateBefore.getTime()){
						return ResultMsg.newInstance(false,"开始时间不能超过上一周期结束是时间，添加周期失败！");
					}
				}

				int i = memberService.updatePeriod(periodCode,beginDate,endDate);
				if (i==1){
					return ResultMsg.newInstance(true,"修改周期成功！");
				}else {
					return ResultMsg.newInstance(false,"修改周期失败！");
				}

			}


		}else{
			return ResultMsg.newInstance(false,"业绩状态已关闭，不可修改周期！");
		}


	}


	/*
	 * 切换周期状态
	 * */
	@RequestMapping(value = "/editPeriodStatu",method = RequestMethod.POST)
	public ResultMsg editPeriodStatu(@RequestParam(value = "periodCode",required = false) String periodCode,
									 @RequestParam(value = "updateMemo",required = false) String updateMemo){

		if (periodCode==null||"".equals(periodCode)){
			return ResultMsg.newInstance(false,"切换周期状态失败，请输入正确的周期！");
		}

		if (updateMemo==null){
			return ResultMsg.newInstance(false,"修改备注不能为null！");
		}

		SysPeriod period = memberService.findPeriod(periodCode);
		if (period==null){
			return ResultMsg.newInstance(false,"切换周期状态失败，请输入正确的周期！");
		}

		int salesStatus = period.getSalesStatus();
		int calStatus = period.getCalStatus();
		int bonusStatus = period.getBonusStatus();

		String actionCode ="";//影响字段
		int valBefoer = 0;//修改前值
		String valBefoerDesc ="";//修改前值描述
		int valAfter = 0;//修改后值
		String valAfterDesc = "";//修改后值描述



		if (salesStatus<3){//业绩状态
			//影响字段 SALES_STATUS
			actionCode = "SALES_STATUS";

			if (salesStatus==0){//0：未开始

				valBefoer=salesStatus;
				valBefoerDesc="未开始";

				salesStatus = 1;//1：已开始

				valAfter =salesStatus;
				valAfterDesc ="已开始";

			}else if (salesStatus==1){//1：已开始

				valBefoer=salesStatus;
				valBefoerDesc="已开始";

				salesStatus = 2;//2：外部关闭补录中

				valAfter =salesStatus;
				valAfterDesc ="外部关闭补录中";

			}else{//2：外部关闭补录中
				valBefoer=salesStatus;
				valBefoerDesc="外部关闭补录中";

				salesStatus = 3;//3.已关闭

				valAfter =salesStatus;
				valAfterDesc ="已关闭";
			}

		}else{//业绩状态 3.已关闭

			if(calStatus<3){//奖金状态
				//影响字段 CAL_STATUS
				actionCode = "CAL_STATUS";
				if (calStatus==0){

					valBefoer=calStatus;
					valBefoerDesc="未开始";

					calStatus = 1;//1：已开始

					valAfter =calStatus;
					valAfterDesc ="计算中";

				}else if (calStatus==1){//1：计算中

					valBefoer=calStatus;
					valBefoerDesc="计算中";

					calStatus = 2;//2：临时发布核对中

					valAfter =calStatus;
					valAfterDesc ="临时发布核对中";

				}else{//2：临时发布核对中
					valBefoer=calStatus;
					valBefoerDesc="临时发布核对中";

					calStatus = 3;//3.已关闭

					valAfter =calStatus;
					valAfterDesc ="正式发布";
				}

			}else{//奖金状态 3：正式发布'
				//影响字段 BONUS_STATUS
				actionCode = "BONUS_STATUS";
				if (bonusStatus==0){//发放状态

					valBefoer=bonusStatus;
					valBefoerDesc="未发出";

					bonusStatus = 1;//1.已发出

					valAfter =bonusStatus;
					valAfterDesc ="已发出";

				}else{//发放状态 1.已发出
					return ResultMsg.newInstance(false,"切换周期状态失败，本周期已完成！");
				}
			}
		}

		//修改周期表状态
		int i = memberService.editPeriodStatu(periodCode,salesStatus,calStatus,bonusStatus);

		//记录周期变更日志
		int j = memberService.addPeriodLog(periodCode,actionCode,valBefoer,valBefoerDesc,valAfter,valAfterDesc,updateMemo);

		if (i==1&&i==j){
			return ResultMsg.newInstance(true,"切换周期成功！");
		}else {
			return ResultMsg.newInstance(false,"切换周期失败！");
		}
	}

	/*查看所有周期切换日志*/
	@RequestMapping(value = "/findPeriodLogAll",method = RequestMethod.POST)
	public ResultMsg findPeriodLogAll(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
									  @RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize,
									  @RequestParam(value = "periodCode",required = false) String periodCode){

		int size=pageSize;

		PageHelper.startPage(currentPage,size);

		ResultMsg<PageInfo<SysPeriodLog>> resultMsg = new ResultMsg<PageInfo<SysPeriodLog>>();

		List<SysPeriodLog> list = memberService.findPeriodLogAll(periodCode);

		PageInfo<SysPeriodLog> pageInfo = new PageInfo<SysPeriodLog>(list);
		resultMsg.setCode(true);
		resultMsg.setData(pageInfo);
		return resultMsg;

	}


	/*
	 * 关闭业绩
	 * */
	@RequestMapping(value = "/closePeriodSales",method = RequestMethod.GET)
	public ResultMsg closePeriodSales(@RequestParam(value = "periodCode",required = false) String periodCode){
		//查找本期业务周期
		SysPeriod period = memberService.findPeriod(periodCode);
		if (period==null){
			return ResultMsg.newInstance(false,"请传入正确的周期！");
		}

		if (period.getSalesStatus()<3){
			int i = memberService.ClosePeriodSales(periodCode);
			if (i==1){
				return ResultMsg.newInstance(true,"关闭业绩成功！");
			}else {
				return ResultMsg.newInstance(false,"关闭业绩失败！");
			}

		}else {
			return ResultMsg.newInstance(false,"业绩状态已关闭，请不要重复操作！");
		}

	}

	/*
	 * 删除周期
	 * */
	@RequestMapping(value = "/delPeriod",method = RequestMethod.GET)
	public ResultMsg delPeriod(@RequestParam(value = "periodCode",required = false) String periodCode){

		//查找本期业务周期
		SysPeriod period = memberService.findPeriod(periodCode);
		if (period==null){
			return ResultMsg.newInstance(false,"请传入正确的周期！");
		}

		if (period.getSalesStatus()!=0){
			return ResultMsg.newInstance(false,"周期一开始，本周期不能删除！");
		}

		int i = memberService.delPeriod(periodCode);
		if (i==1){
			return ResultMsg.newInstance(true,"删除周期成功！");
		}else {
			return ResultMsg.newInstance(false,"删除周期失败！");
		}
	}


	/*
	 * 查看本期会员资格表
	 * */
	@RequestMapping(value = "/findQualificationAll",method = RequestMethod.POST)
	public ResultMsg findQualificationAll(@RequestParam(required = false,defaultValue = "1",value = "currentPage")Integer currentPage,
										  @RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize,
										  @RequestParam(value = "periodCode",required = false) String periodCode){

		int size=pageSize;

		PageHelper.startPage(currentPage,size);

		ResultMsg<PageInfo<MemberQualification>> resultMsg = new ResultMsg<PageInfo<MemberQualification>>();
		//查到当前周期的所有会员资格数据
		List<MemberQualification> list = memberService.findQualificationByPeriod(periodCode);

		if (list==null||list.size()==0||list.isEmpty()){
			return ResultMsg.newInstance(false,"本期资格信息还未计算！");
		}

		PageInfo<MemberQualification> pageInfo = new PageInfo<MemberQualification>(list);
		resultMsg.setCode(true);
		resultMsg.setData(pageInfo);

		return resultMsg;
	}


	/*
	 * 计算本期会员资格
	 * */
	@RequestMapping(value = "/countNowQualification",method = RequestMethod.GET)
	public ResultMsg countNowQualification(@RequestParam(value = "periodCode",required = false) String periodCode){

		List<MemberQualification> qualificationList = memberService.findQualificationMCodeByPeriod(periodCode);
		if (qualificationList.size()!=0){
			return ResultMsg.newInstance(false,"本期资格信息已计算，请不要重复计算！");
		}

		//判断当前业务周期业绩状态是否已关闭
		SysPeriod period = memberService.findPeriod(periodCode);
		if (period.getSalesStatus()==3&&period.getCalStatus()<3){ //已关闭

			int j = 0;
			List<Member_basic> MemberList = memberService.findMemAll();
			for (Member_basic basic : MemberList) {
				j = 0;
				MemberQualification mqlf = new MemberQualification();

				String mCode = basic.getMCode();//会员编号
				String mName = basic.getMName();//会员姓名

				int ppv = 0;//当期个人购买的PV
				int ppvRetail = 0;//当期个人零售的PV，包括分享给普通顾客购买的PV
				int retailInit =0;//个人零售购买的期初值(期初金额)
				int retail = 0;//个人零售购买额(当月金额)
				int retailFinal = retailInit + retail;//个人零售购买的期末值(期初金额+当月金额)

				int appvInit =0;//期初个人累计PV
				//上一期周期
				String prePeriod = period.getPrePeriod();
				if (!"".equals(prePeriod)){
					MemberQualification qualification = memberService.findQualificationByPeriodAndMCode(prePeriod, mCode);
					if (qualification!=null){
						appvInit = qualification.getAppvFinal();
					}
				}
				int appvFinal = ppv+ppvRetail+appvInit;//期末个人累计PV

				String sponsorCode = "";//推荐人编号
				String sponsorName = "";//推荐人姓名
				int mStatus = 0;//当期会员的状态
				int raStatus = 0;//关联公司绑定状态 0:未绑定 1：已绑定
				int raShopYn = 0;//老系统会员开店状态 0：未开店 1：已开店
				int rankInit = 0;//期初个人级别
				int orphan = 0;//是否孤儿 0：是  1：不是

				MemberRelation relation = memberService.findRelationByMCode(mCode);
				if (relation!=null){
					sponsorCode = relation.getSponsorCode();//推荐人编号
					sponsorName = relation.getSponsorName();//推荐人姓名
					if (sponsorCode!=null){
						orphan = 1;
					}
					mStatus = relation.getMStatus();//当期会员的状态
					raStatus = relation.getRaStatus();//关联公司绑定状态 0:未绑定 1：已绑定
					raShopYn = relation.getRaShopYn();//老系统会员开店状态 0：未开店 1：已开店
					rankInit = relation.getRank();//期初个人级别
					retailInit =relation.getReTail();//个人零售购买的期初值(期初金额)
					//判断关联公司绑定状态
					if (raStatus==1){//1：已绑定
						//判断是否开店
						if(raShopYn==1){//1:开店
							//判断是否可以升级为代理店
							//当期计算后个人级别
							if(rankInit<3){
								//普通会员
								if(rankInit==0){
									if (retailFinal>=360){
										int rank = 1;
										mqlf.setRank(rank);
									}
								}
								//vip会员
								if(rankInit==1){
									if(retailFinal>=360&&appvFinal>=100){
										int rank = 3;
										mqlf.setRank(rank);
									}
								}
								//代理会员
								if(rankInit==2){
									if(retailFinal>=360&&appvFinal>=100){
										int rank = 3;
										mqlf.setRank(rank);
									}
								}
							}
							int rank = rankInit;
							mqlf.setRank(rank);
						}else{//0.没开店
							//向老系统查是否开店
							//当期计算后个人级别
							if(rankInit<2){
								//普通会员
								if(rankInit==0){
									if (retailFinal>=360){
										int rank = 1;
										mqlf.setRank(rank);
									}
								}
								//vip会员
								if(rankInit==1){
									if(retailFinal>=360&&appvFinal>=100){
										int rank = 2;
										mqlf.setRank(rank);
									}
								}
							}
							int rank = rankInit;
							mqlf.setRank(rank);
						}
					}else{//0：未绑定
						//当期计算后个人级别
						if(rankInit<3){
							//普通会员
							if(rankInit==0){
								if (retailFinal>=360){
									int rank = 1;
									mqlf.setRank(rank);
								}
							}
							//vip会员
							if(rankInit==1){
								if(retailFinal>=360&&appvFinal>=100){
									int rank = 2;
									mqlf.setRank(rank);
								}
							}
						}
						int rank = rankInit;
						mqlf.setRank(rank);
					}

				}

				mqlf.setPeriodCode(periodCode);
				mqlf.setMCode(mCode);
				mqlf.setMName(mName);
				mqlf.setSponsorCode(sponsorCode);
				mqlf.setSponsorName(sponsorName);
				mqlf.setOrphan(orphan);
				mqlf.setMStatus(mStatus);
				mqlf.setRaStatus(raStatus);
				mqlf.setRaShopYn(raShopYn);
				mqlf.setRankInit(rankInit);
				mqlf.setPpv(ppv);
				mqlf.setPpvRetail(ppvRetail);
				mqlf.setRetailInit(retailInit);
				mqlf.setRetail(retail);
				mqlf.setRetailFinal(retailFinal);
				mqlf.setAppvInit(appvInit);
				mqlf.setAppvFinal(appvFinal);

				int i = memberService.addMqlf(mqlf);
				j=i;
			}

			if (j==1){
				return ResultMsg.newInstance(true,"计算成功！");
			}else {
				return ResultMsg.newInstance(false,"计算失败！");
			}
		}else{
			return ResultMsg.newInstance(false,"本周期会员资格表已计算或者还未可以开始计算！");
		}
	}


}
