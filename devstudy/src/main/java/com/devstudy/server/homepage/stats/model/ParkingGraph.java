package com.zoomansa.homepage.stats.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * ParkingGraph
 * 주차 그래프 model
 */
public class ParkingGraph {
	
	//통계ID
	private int statsUID;
	//통계이름
	private String statsName;

	//그래프 데이터
	@JsonIgnoreProperties({ "statsUID", "statsName" })
	private List<ParkingStatsByArea> statsByAreaList;
	
	
	public int getStatsUID() {
		return statsUID;
	}
	public void setStatsUID(int statsUID) {
		this.statsUID = statsUID;
	}
	public String getStatsName() {
		return statsName;
	}
	public void setStatsName(String statsName) {
		this.statsName = statsName;
	}
	public List<ParkingStatsByArea> getStatsByAreaList() {
		return statsByAreaList;
	}
	public void setStatsByAreaList(List<ParkingStatsByArea> statsByAreaList) {
		this.statsByAreaList = statsByAreaList;
	}

}
