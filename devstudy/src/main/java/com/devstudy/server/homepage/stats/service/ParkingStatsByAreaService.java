package com.zoomansa.homepage.stats.service;

import java.util.List;

import com.zoomansa.homepage.stats.model.ParkingGraph;

public interface ParkingStatsByAreaService {
	public List<ParkingGraph> getTodayStatsByAreaList();
	public ParkingGraph getTodayStatsByAreaListByStatsUID(int statsUID);
	public void clearStatsCache();
}
