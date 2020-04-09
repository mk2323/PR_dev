package com.zoomansa.homepage.stats.model;


/**
 * ParkingStatsByArea
 * 지역별 통계 데이터 model
 */

public class ParkingStatsByArea {

	//통계ID
	private int statsUID;
	//통계 이름
	private String statsName;
	//지역번호
	private int areaNo;
	//지역구
	private String areasggNm;
	//서비스지역 유무
	private int state;
	//건수
	private int cnt;
	//지도 높이
	private int level;

	@Override
	public String toString() {
		return "ParkingStatsByArea [statsUID=" + statsUID + ", statsName=" + statsName + ", areaNo=" + areaNo
				+ ", areasggNm=" + areasggNm + ", state=" + state + ", cnt=" + cnt + ", level=" + level + "]";
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
	public int getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}
	public String getAreasggNm() {
		return areasggNm;
	}
	public void setAreasggNm(String areasggNm) {
		this.areasggNm = areasggNm;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
