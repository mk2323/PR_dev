package com.zoomansa.homepage.area.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zoomansa.arsparking.stats.model.ArsparkingArea;
import com.zoomansa.homepage.area.model.Area;


/**
 * 
 * HomepageAreaDao
 * 
 * homepage db의 AreaDB  dao
 * 
 */
@Mapper
public interface HomepageAreaDao {
	
	/**
	 * 지역 리스트를 가져온다.
	 * @return list<지역번호, 지역ID, 시이름, 구이름, 상태>
	 */
	@Select("SELECT areaNo, areaUID, areasiNm, areasggNm, state, areaLat, areaLon FROM homepage.AreaDB")
	List<Area> getAreaList();

	
	/**
	 * 지역 상태,지역 ID 값을 변경한다.
	 */
	@Update(" update homepage.AreaDB "
			+ " set areaUID = #{areaUID}, "
			+ "     state = #{state}, "
			+ "     updateDate = now() "
			+ " where areasggNm = #{areasggNm} ")
	int updateArea(ArsparkingArea homepageArea);

}
