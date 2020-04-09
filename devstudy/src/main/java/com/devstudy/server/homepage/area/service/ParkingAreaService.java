package com.zoomansa.homepage.area.service;

import java.util.List;

import com.zoomansa.homepage.area.model.Area;
import com.zoomansa.homepage.area.model.Map;
import com.zoomansa.homepage.area.model.SharedParkingSlot;

public interface ParkingAreaService {
	List<SharedParkingSlot> getSharedParkingSlot();
	List<SharedParkingSlot> getSharedParkingSlot(Map map);
	List<Area> getServiceArea();
}
