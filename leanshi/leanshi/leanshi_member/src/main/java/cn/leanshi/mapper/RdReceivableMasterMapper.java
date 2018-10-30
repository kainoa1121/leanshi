package cn.leanshi.mapper;

import cn.leanshi.model.RdReceivableMaster;

import java.util.List;
import java.util.Map;

/**
 * @author :ldq
 * @date:2018/10/16
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface RdReceivableMasterMapper {
	List<RdReceivableMaster> findReceivableAll(Map<String, Object> map);

    /**
     * autor  zc 根据会员编号查询会员欠款主表
     * @param mCode
     * @return
     */
    RdReceivableMaster findRdReceivableMaster(String mCode);
}
