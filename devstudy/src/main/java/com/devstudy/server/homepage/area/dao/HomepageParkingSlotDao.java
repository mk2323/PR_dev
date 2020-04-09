package com.zoomansa.homepage.area.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.zoomansa.arsparking.slot.model.ParkingSlotDB;
import com.zoomansa.homepage.area.model.SharedParkingSlot;



/**
 * 
 * HomepageParkingSlotDao
 * 
 * homepage db의 ParkingSlot에 dao
 * 
 */
@Mapper
public interface HomepageParkingSlotDao {
	
	/**
	 * HomepageParkingSlotDao.getSharedParkingSlot

	 * 공유된 주차 구획 정보를 조회
	 */
	@Select("SELECT regionCode, 'zoomansa' regionType, latitude lat, longitude lon FROM homepage.ParkingSlot where useShared = 0")
	List<SharedParkingSlot> getSharedParkingSlot();

	/**
	 * HomepageParkingSlotDao.insertParkingSlot

	 * 공유된 주차 구획 정보를 저장
	 */
	@Insert("insert into homepage.ParkingSlot (regionCode, slotCode, slotUniqueCode, latitude, longitude, useShared, startShareDate, endShareDate, shareStartTime, shareEntTime) "
			+ "values( #{regionCode} , #{slotCode}, #{slotUniqueCode}, #{latitude}, #{longitude}, #{useShared}, #{startShareDate}, #{endShareDate}, #{shareStartTime}, #{shareEntTime} )")
	int insertParkingSlot(ParkingSlotDB parkingSlotdb);
	
	
	/**
	 * HomepageParkingSlotDao.deleteParkingSlot

	 * 공유된 주차 구획 정보를 전체 삭제
	 */
	@Delete("delete from homepage.ParkingSlot")
	void deleteParkingSlot();
}
