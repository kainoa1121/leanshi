package cn.leanshi.mapper;

import cn.leanshi.model.RdRaBinding;

import java.util.Map;

/**
 * @author :ldq
 * @date:2018/8/9
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface RdRaBindingMapper {
	RdRaBinding findBindingByMCode(String rdCode);

	RdRaBinding findBindingByMNameAndIdCode(Map<String,Object> map);

	int updateRaByMCode(RdRaBinding binding);

	int addBinding(RdRaBinding binding);
}
