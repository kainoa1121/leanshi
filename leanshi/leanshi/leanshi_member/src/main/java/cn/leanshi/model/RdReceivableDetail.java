package cn.leanshi.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author :ldq
 * @date:2018/10/16
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class RdReceivableDetail {

	private int transNumber;//交易流水号
	private int batchNumber;//批记录号
	private String mCode;//会员编号
	private String trTypeCode;//交易类型代码 NR:新增欠款 RR:归还欠款
	private String trSourceType;//交易渠道 CMP：公司 OBB：其他会员奖金余额 OWB：其他会员购物积分本人积分账户余额 SWB：本人购物积分余额 BNK：银行（包括第三方支付）CSH：现金'
	private int trBankOid;//与交易有关的银行账户信息（RD_MM_BANK表）
	private int currencyCode;//币种
	private BigDecimal blanceBefore;//交易前余额
	private BigDecimal amount;//交易金额
	private BigDecimal blanceAfter;//交易后余额
	private Date transDate;//交易时间
	private String transPeriod;//交易业务周期
	private String transDesc;//交易说明
	private int status;//交易标志 -1：取消 1：新单（保存状态） 2：已申请 3：已授权
	private int washedYn;//冲正标记 0：否 1：是（冲正单)
	private String orignTransNumber;//原始单号(冲正单用)
	private String creationBy;//创建人
	private Date creationTime;//创建时间
	private String updateBy;//更新人
	private Date updateTime;//更新时间
	private String autohrizeBy;//授权人
	private Date autohrizeTime;//授权时间
	private String autohrizeDesc;//授权说明（同意或不同意的理由）



}
