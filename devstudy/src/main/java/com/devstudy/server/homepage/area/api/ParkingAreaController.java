package com.zoomansa.homepage.area.api;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zoomansa.homepage.area.model.Area;
import com.zoomansa.homepage.area.model.Map;
import com.zoomansa.homepage.area.model.SharedAPI;
import com.zoomansa.homepage.area.model.SharedParkingSlot;
import com.zoomansa.homepage.area.service.ParkingAreaService;


/**
 * ParkingAreaController
 * 
 * 지역 정보 관련 API
 */
@RestController
@RequestMapping("/area")
public class ParkingAreaController {
	
	private static final Logger log = LoggerFactory.getLogger(ParkingAreaController.class);
	
	@Autowired
	ParkingAreaService parkingAreaService;
	
	/**
	 * ParkingAreaController.getSharedParkingSlot
	 * 공유 구획 정보를 조회
	 */
	@GetMapping(value= "/shared", produces="application/json" )
	public @ResponseBody SharedAPI getSharedParkingSlot(Map map) {
		log.debug("/area/shared");
		
		SharedAPI sharedAPI = new SharedAPI();
		
		if(null == map || "".equals(map.getBottomLeftLat()) || "".equals(map.getBottomLeftLon())
				|| "".equals(map.getTopRightLat()) || "".equals(map.getTopRightLon())) {
			sharedAPI.setStatusCode("1100");
		}else {
			sharedAPI.setStatusCode("0000");
			sharedAPI.setResultType("regionInfo");
			sharedAPI.setResult(parkingAreaService.getSharedParkingSlot(map));
		}
		return sharedAPI;
	}

	
	/**
	 * ParkingAreaController.getServiceArea
	 * 서비스 지역을 조회
	 */
	@GetMapping(value= "/service", produces="application/json")
	public @ResponseBody List<Area> getServiceArea() {
		log.debug("/area/service");
		return parkingAreaService.getServiceArea();
	}

	
}
