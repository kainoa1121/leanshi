package cn.leanshi.mapper;

import cn.leanshi.model.RdReceivableDetail;



import java.util.List;
import java.util.Map;
/**

 * @author :ldq
 * @date:2018/10/16
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface RdReceivableDetailMapper {

    /**
     * autor zc 根据会员编号，查询当前会员已发布的最新一期的会员欠款明细表记录
     * @param mCode
     * @return
     */
    RdReceivableDetail findRdReceivableDetailLast(String mCode);

    /**
     * autor zc 添加会员欠款明细表
     *
     * @param rdReceivableDetail1
     */
    void insertRdReceivableDetail(RdReceivableDetail rdReceivableDetail1);

	List<RdReceivableDetail> findReceivableDetailAll(Map<String, Object> map);

	int updateRD(Map<String, Object> map);

	RdReceivableDetail findRD(Map<String, Object> map);

	int addRDNR(RdReceivableDetail detail);

	int addRDRR(RdReceivableDetail detail);

}
