package com.zoomansa.homepage.area.model;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * 
 * ParkingSlot
 * 
 * 공유되고 있는 구획 위치 정보
 * 
 */
public class SharedParkingSlot {
		
	//지역코드
	private int regionCode;
	//지역타입
	private String regionType;
	//위도
	private double lat;
	//경도
	private double lon;
	
	@Override
	public String toString() {
		return "SharedParkingSlot [regionCode=" + regionCode + ", regionType=" + regionType + ", lat=" + lat + ", lon="
				+ lon + "]";
	}
	public int getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionType() {
		return regionType;
	}
	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	

}