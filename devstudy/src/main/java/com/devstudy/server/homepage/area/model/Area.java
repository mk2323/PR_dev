package com.zoomansa.homepage.area.model;


/**
 * 
 * ArsparkingArea
 * 
 * arsparking db에서 지역 model
 * 
 */
public class Area {

	//지역ID
	private int areaUID;
	//시
	private String areasiNm;
	//지역구
	private String areasggNm;
	//서비스 상태 ( 0: 서비스중 , 1: 서비스안함 )
	private int state = 1;
	//위도
	private double areaLat;
	//경도
	private double areaLon;


	@Override
	public String toString() {
		return "Area [areaUID=" + areaUID + ", areasiNm=" + areasiNm + ", areasggNm=" + areasggNm + ", state=" + state
				+ ", areaLat=" + areaLat + ", areaLon=" + areaLon + "]";
	}

	public double getAreaLat() {
		return areaLat;
	}

	public void setAreaLat(double areaLat) {
		this.areaLat = areaLat;
	}

	public double getAreaLon() {
		return areaLon;
	}

	public void setAreaLon(double areaLon) {
		this.areaLon = areaLon;
	}

	public int getAreaUID() {
		return areaUID;
	}

	public void setAreaUID(int areaUID) {
		this.areaUID = areaUID;
	}

	public String getAreasiNm() {
		return areasiNm;
	}

	public void setAreasiNm(String areasiNm) {
		this.areasiNm = areasiNm;
	}

	public String getAreasggNm() {
		return areasggNm;
	}

	public void setAreasggNm(String areasggNm) {
		this.areasggNm = areasggNm;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
