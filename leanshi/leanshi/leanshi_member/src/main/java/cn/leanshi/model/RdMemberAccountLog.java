package cn.leanshi.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员账户交易日志表
 * @author :ldq
 * @date:2018/10/23
 * @description:leanshi_member cn.leanshi.model
 */
@Data
public class RdMemberAccountLog {

	private Integer transNumber;//交易流水号
	private Integer batchNumber;//批记录号
	private String mCode;//会员编号
	private String mNickname;//会员昵称
	private String transTypeCode;//交易类型代码 BA:奖金转入 RC:充值 RB:公司补发奖金 WD:取现 SP:转购物积分 RR:归还欠款 BT:奖励积分转换 TF:他人转入 OT:订单退款 OP:订单支付 TT:转给他人 PC:购买商品并评论 EG:换购商品
	private String accType;//进账账户类型 SBB：本人积分账户余额 SWB：本人购物积分账户余额 SRB：本人换购积分账户余额
	private String trSourceType;//交易对方类型 CMP：公司 OBB：其他会员奖金余额 OWB：其他会员购物积分 SBB：本人积分账户余额 SWB：本人购物积分余额 BNK：银行（包括第三方支付） CSH：现金
	private String trMmCode;//对方会员编号（如果是会员）
	private Integer trBankOId;//与交易有关的银行账户信息（RD_MM_BANK表）
	private Integer trOrderOId;//与交易有关的订单信息
	private String currencyCode;//币种
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
