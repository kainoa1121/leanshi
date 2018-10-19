package cn.leanshi.mapper;

import cn.leanshi.model.RdReceivableDetail;

import java.util.List;
import java.util.Map; /**
 * @author :ldq
 * @date:2018/10/16
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface RdReceivableDetailMapper {
	List<RdReceivableDetail> findReceivableDetailAll(Map<String, Object> map);

	int updateRD(Map<String, Object> map);

	RdReceivableDetail findRD(Map<String, Object> map);

	int addRDNR(RdReceivableDetail detail);

	int addRDRR(RdReceivableDetail detail);
}
