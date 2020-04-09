package com.zoomansa.homepage.area.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zoomansa.homepage.area.dao.HomepageAreaDao;
import com.zoomansa.homepage.area.dao.HomepageParkingSlotDao;
import com.zoomansa.homepage.area.model.Area;
import com.zoomansa.homepage.area.model.Map;
import com.zoomansa.homepage.area.model.SharedParkingSlot;


/**
 * ParkingAreaServiceImpl
 * 
 * 지역 정보 서비스
 */
@Service
public class ParkingAreaServiceImpl implements ParkingAreaService {

	@Autowired
	HomepageParkingSlotDao homepageParkingSlotDao;
	
	@Autowired
	HomepageAreaDao homepageAreaDao;
	
	
	/**
	 * ParkingAreaServiceImpl.getSharedParkingSlot
	 * 공유 구획 정보 조회
	 */
	@Override
	@Cacheable
	public List<SharedParkingSlot> getSharedParkingSlot() {
		return homepageParkingSlotDao.getSharedParkingSlot();
	}
	
	/**
	 * ParkingAreaServiceImpl.getSharedParkingSlot
	 * 공유 지도 정보에 따른 공유 구획 정보 조회
	 */
	@Override
	public List<SharedParkingSlot> getSharedParkingSlot(Map map) {
		ArrayList<SharedParkingSlot> resultList = new ArrayList<>();
		
		double bottomLeftLat = map.getBottomLeftLat();
		double bottomLeftLon = map.getBottomLeftLon();
		double topRightLat = map.getTopRightLat();
		double topRightLon = map.getTopRightLon();


		//캐시 사용??
		List<SharedParkingSlot> slotList = getSharedParkingSlot();
		
		for(SharedParkingSlot temp : slotList) {
			boolean latFlag = bottomLeftLat <= temp.getLat()? (temp.getLat() <= topRightLat) : false;
			boolean lonFlag = bottomLeftLon <= temp.getLon()? (temp.getLon() <= topRightLon) : false;

			if(latFlag && lonFlag) {
				resultList.add(temp);
			}
		}
		
		return resultList;
	}

	/**
	 * ParkingAreaServiceImpl.getServiceArea
	 * 서비스 제공 지역 정보 조회
	 */
	@Override
	public List<Area> getServiceArea() {
		List<Area> resultList = new ArrayList<>();
		
		List<Area> areaList = homepageAreaDao.getAreaList();
		for(Area area : areaList) {
			//서비스 중일때
			if(0 == area.getState()) {
				resultList.add(area);
			}
		}
		return resultList;
	}

}
