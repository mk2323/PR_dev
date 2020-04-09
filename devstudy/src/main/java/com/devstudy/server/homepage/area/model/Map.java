package com.zoomansa.homepage.area.model;


/**
 * 
 * Map
 * 
 * 화면에 보이는 맵(공유지도) 모델
 * 
 */
public class Map {
	
	//하단왼쪽위도
	private double bottomLeftLat;
	//하단왼쪽경도
	private double bottomLeftLon;
	//상단오른쪽위도
	private double topRightLat;
	//상단오른쪽경도
	private double topRightLon;
	//지도 타입
	private String type;
	//지역 타입
	private String regionType;
	
	
	@Override
	public String toString() {
		return "Map [bottomLeftLat=" + bottomLeftLat + ", bottomLeftLon=" + bottomLeftLon + ", topRightLat="
				+ topRightLat + ", topRightLon=" + topRightLon + ", type=" + type + ", regionType=" + regionType + "]";
	}
	public double getBottomLeftLat() {
		return bottomLeftLat;
	}
	public void setBottomLeftLat(double bottomLeftLat) {
		this.bottomLeftLat = bottomLeftLat;
	}
	public double getBottomLeftLon() {
		return bottomLeftLon;
	}
	public void setBottomLeftLon(double bottomLeftLon) {
		this.bottomLeftLon = bottomLeftLon;
	}
	public double getTopRightLat() {
		return topRightLat;
	}
	public void setTopRightLat(double topRightLat) {
		this.topRightLat = topRightLat;
	}
	public double getTopRightLon() {
		return topRightLon;
	}
	public void setTopRightLon(double topRightLon) {
		this.topRightLon = topRightLon;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRegionType() {
		return regionType;
	}
	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}
}
