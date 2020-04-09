package com.zoomansa.homepage.stats.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zoomansa.homepage.stats.model.ParkingGraph;
import com.zoomansa.homepage.stats.service.ParkingStatsByAreaService;

/**
 * ParkingStatsByAreaController
 * 
 * 통계 정보 API
 */
@RestController
public class ParkingStatsByAreaController {
	
	private static final Logger log = LoggerFactory.getLogger(ParkingStatsByAreaController.class);

	@Autowired
	private ParkingStatsByAreaService parkingStatsByAreaService;
	

	/**
	 * ParkingStatsByAreaController.getUseSharedList
	 * 
	 * 공유 구획 수를 조회
	 */
	@GetMapping(value= "/stats/useShared", produces="application/json" )
	public String getUseSharedList() {
		return new Gson().toJson( parkingStatsByAreaService.getTodayStatsByAreaListByStatsUID(1) , new TypeToken<ParkingGraph>(){}.getType() );
	}
	
	
	/**
	 * ParkingStatsByAreaController.getMonthParkingList
	 * 
	 * 월주차건수를 조회한다.
	 */
	@GetMapping(value= "/stats/monthParking", produces="application/json" )
	public String getMonthParkingList() {
		return new Gson().toJson( parkingStatsByAreaService.getTodayStatsByAreaListByStatsUID(2) , new TypeToken<ParkingGraph>(){}.getType() );
	}
	
	/**
	 * ParkingStatsByAreaController.getParkingCarList
	 * 
	 * 총 이용 차량을 조회한다.
	 */
	@GetMapping(value= "/stats/parkingCar", produces="application/json")
	public String getParkingCarList() {
		return new Gson().toJson( parkingStatsByAreaService.getTodayStatsByAreaListByStatsUID(3) , new TypeToken<ParkingGraph>(){}.getType() );
	}

	/**
	 * ParkingStatsByAreaController.getTodayStatsByAreaList
	 * 등록된 통계를 조회한다.
	 */
	@GetMapping(value= "/stats", produces="application/json")
	public @ResponseBody List<ParkingGraph> getTodayStatsByAreaList() {
		return parkingStatsByAreaService.getTodayStatsByAreaList();
	}

	/**
	 * ParkingStatsByAreaController.getTodayStatsByAreaListByStatsUID
	 * id에 해당하는 등록된 통계를 조회한다. 
	 */
	@GetMapping(value= "/stats/{statsUID}", produces="application/json")
	public @ResponseBody ParkingGraph getTodayStatsByAreaListByStatsUID(@PathVariable(value="statsUID") int statsUID) {
		return parkingStatsByAreaService.getTodayStatsByAreaListByStatsUID(statsUID);
	}


	/**
	 * ParkingStatsByAreaController.clearStatsCache
	 * 통계 서비스에 cache를 날린다.
	 * 현재 의미없음
	 */
	@GetMapping(value= "/stats/clearStatsCache", produces="application/json")
	public void clearStatsCache() {
		log.debug("======== cache clear =======");
		parkingStatsByAreaService.clearStatsCache();
	}
	
}
