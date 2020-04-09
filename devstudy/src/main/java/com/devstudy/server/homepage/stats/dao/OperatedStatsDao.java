package com.zoomansa.homepage.stats.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zoomansa.homepage.stats.model.OperatedStats;


/**
 * OperatedStatsDao
 * 제공되고 있는 통계 서비스 DAO
 */
@Mapper
public interface OperatedStatsDao {
	
	/**
	 * getStatsList
	 * 통계 서비스 조회
	 */
	@Select("SELECT statsUID, statsName FROM homepage.OperatedStats")
	List<OperatedStats> getStatsList();

	
	/**
	 * getStatsListByStatsUID
	 * 통계ID에 따른 통계 서비스 조회
	 */
	@Select("SELECT statsUID, statsName FROM homepage.OperatedStats where statsUID = #{statsUID}")
	OperatedStats getStatsListByStatsUID(@Param("statsUID") int statsUID);

}
