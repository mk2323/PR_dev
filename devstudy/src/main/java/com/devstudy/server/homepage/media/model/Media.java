package com.zoomansa.homepage.media.model;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "regId", "updateId", "regDate" })
public class Media {

	private int mediaUID;
	private String mediaTitle = "";
	private String mediaLink = "";
	private int useState ;
	private String contents = "";
	private String imgURL = "";
	private String imgName = "";
	private String imgRealName = "";
	private String regId;
	private Date regDate;
	private String updateId;
	private Date updateDate;


	@Override
	public String toString() {
		return "Media [mediaUID=" + mediaUID + ", mediaTitle=" + mediaTitle + ", mediaLink=" + mediaLink + ", useState="
				+ useState + ", contents=" + contents + ", imgURL=" + imgURL + ", imgName=" + imgName + ", imgRealName="
				+ imgRealName + ", regId=" + regId + ", regDate=" + regDate + ", updateId=" + updateId + ", updateDate="
				+ updateDate + "]";
	}

	public int getMediaUID() {
		return mediaUID;
	}
	public void setMediaUID(int mediaUID) {
		this.mediaUID = mediaUID;
	}
	public String getMediaTitle() {
		return mediaTitle;
	}
	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}
	public String getMediaLink() {
		return mediaLink;
	}
	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}
	public int getUseState() {
		return useState;
	}
	public void setUseState(int useState) {
		this.useState = useState;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgRealName() {
		return imgRealName;
	}
	public void setImgRealName(String imgRealName) {
		this.imgRealName = imgRealName;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
