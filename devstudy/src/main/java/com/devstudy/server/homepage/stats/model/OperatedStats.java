package com.zoomansa.homepage.stats.model;

/**
 * OperatedStats
 * 제공하는 통계 model
 */
public class OperatedStats {
	
	//통계ID
	private int statsUID;
	//통계이름
	private String statsName;
	
	@Override
	public String toString() {
		return "ParkingStats [statsUID=" + statsUID + ", statsName=" + statsName + "]";
	}
	
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

}
