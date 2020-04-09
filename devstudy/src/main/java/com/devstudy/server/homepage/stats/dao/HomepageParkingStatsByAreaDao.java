package com.zoomansa.homepage.stats.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zoomansa.arsparking.stats.model.ArsparkingStatsByArea;
import com.zoomansa.homepage.stats.model.ParkingStatsByArea;


/**
 * HomepageParkingStatsByAreaDao
 * 지역별 통계 정보 dao
 */
@Mapper
public interface HomepageParkingStatsByAreaDao {
	
	/**
	 * HomepageParkingStatsByAreaDao.getTodayStatsByAreaList
	 * 금일 통계 데이터 조회
	 */
	@Select("select areaNo, areasiNm, areasggNm, state, statsUID, statsName, cnt \r\n" + 
			"from (\r\n" + 
			"	SELECT areaNo, b.areaUID, a.areasiNm, a.areasggNm, state, statsUID, statsName, cnt\r\n" + 
			"	FROM homepage.AreaDB a\r\n" + 
			"	join (\r\n" + 
			"		select *\r\n" + 
			"		from homepage.ParkingStatsByArea\r\n" + 
			"		where date_format(regDate, '%Y-%m-%d') = date_format(now() , '%Y-%m-%d')\r\n" + 
			"	) b\r\n" + 
			"	on a.areasggNm = b.areasggNm\r\n" + 
			"	union \r\n" + 
			"	select areaNo, areaUID, areasiNm, areasggNm, state, statsUID, statsName, 0 cnt\r\n" + 
			"	FROM homepage.AreaDB, homepage.OperatedStats\r\n" + 
			"	where areaUID not in (\r\n" + 
			"						select areaUID\r\n" + 
			"						from homepage.ParkingStatsByArea \r\n" + 
			"                        where date_format(regDate, '%Y-%m-%d') = date_format(now(), '%Y-%m-%d')\r\n" +
			"						group by areaUID, areasggNm \r\n" + 
			"                    )\r\n" + 
			") va\r\n" + 
			"order by areaNo")
	List<ParkingStatsByArea> getTodayStatsByAreaList();

	
	/**
	 * HomepageParkingStatsByAreaDao.getTodayStatsByAreaList
	 * 통계id별 금일 통계 데이터 조회
	 */
	@Select("select areaNo, areasiNm, areasggNm, state, statsUID, statsName, cnt \r\n" + 
			"from (\r\n" + 
			"	SELECT areaNo, b.areaUID, a.areasiNm, a.areasggNm, state, statsUID, statsName, cnt\r\n" + 
			"	FROM homepage.AreaDB a\r\n" + 
			"	join (\r\n" + 
			"		select *\r\n" + 
			"		from homepage.ParkingStatsByArea\r\n" + 
			"		where statsUID = #{statsUID}" +
			"       and date_format(regDate, '%Y-%m-%d') = date_format(now() , '%Y-%m-%d')\r\n" + 
			"	) b\r\n" + 
			"	on a.areasggNm = b.areasggNm \r\n" + 
			"	union \r\n" + 
			"	select areaNo, areaUID, areasiNm, areasggNm, state, statsUID, statsName, 0 cnt\r\n" + 
			"	FROM homepage.AreaDB, homepage.OperatedStats\r\n" + 
			"	where areaUID not in (\r\n" + 
			"						select areaUID\r\n" + 
			"						from homepage.ParkingStatsByArea \r\n" + 
			"                       where date_format(regDate, '%Y-%m-%d') = date_format(now(), '%Y-%m-%d')\r\n" +
			"						group by areaUID, areasggNm \r\n" + 
			"                    )\r\n" + 
			"	and statsUID = #{statsUID} " +
			") va\r\n" + 
			"order by areaNo")
	List<ParkingStatsByArea> getTodayStatsByAreaListByStatsUID(@Param("statsUID") int statsUID);
	

	
	/**
	 * HomepageParkingStatsByAreaDao.insertStats
	 * 지역별 통계 데이터 저장
	 */
	@Insert("INSERT INTO homepage.ParkingStatsByArea(statsUID, statsName, areaUID, areasiNm, areasggNm, cnt, regDate)"
			+ "VALUE( #{statsUID}, #{statsName}, #{areaUID}, #{areasiNm}, #{areasggNm}, #{cnt}, now() ) ")
	int insertStats(ArsparkingStatsByArea arsparkingStatsByArea);

}
