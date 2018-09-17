package cn.leanshi.mapper;

import cn.leanshi.model.Rank;

import java.util.List;

/**
 * @author :ldq
 * @date:2018/9/5
 * @description:leanshi_member cn.leanshi.mapper
 */
public interface RankMapper {
	List<Rank> fingRankAll();

	String findRank(int rankId);

	int findRankByRankName(String rankName);
}
