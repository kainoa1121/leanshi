package cn.leanshi.mapper;

import cn.leanshi.model.RdStatusDetail;

import java.util.List;
import java.util.Map;

/**
 * @author :ldq
 * @date:2018/10/22
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface RdStatusDetailMapper {
	int addStatusD(Map<String, Object> map);

	List<RdStatusDetail> findStatusDetailAll(Map<String, Object> map);
}
