package com.zoomansa.homepage.area.model;

import java.util.List;

public class SharedAPI {

	private List<SharedParkingSlot> result;
	private String statusCode;
	private String resultType;
	
	@Override
	public String toString() {
		return "SharedAPI [result=" + result + ", statusCode=" + statusCode + ", resultType=" + resultType + "]";
	}
	
	public List<SharedParkingSlot> getResult() {
		return result;
	}
	public void setResult(List<SharedParkingSlot> result) {
		this.result = result;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
}
