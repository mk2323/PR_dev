package com.zoomansa.homepage.media.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.zoomansa.homepage.media.model.Media;

@Mapper
public interface MediaCenterDao {

	
	@Select("SELECT mediaUID, mediaTitle, mediaLink, contents, imgURL, imgName, imgRealName, regId, regDate, updateId, date_format(updateDate, '%Y-%m-%d %h:%i:%s')  updateDate, useState "
			+ " FROM homepage.MediaCenter "
			+ " where mediaUID = #{mediaUID} ")
	Media getMedia(@Param("mediaUID") int mediaUID);
	
	@Select("SELECT mediaUID, mediaTitle, mediaLink, contents, imgURL, imgName, imgRealName, regId, regDate, updateId, date_format(updateDate, '%Y-%m-%d %h:%i:%s') updateDate, useState \r\n"
			+ "FROM homepage.MediaCenter \r\n"
			+ "Where useState <> 3 \r\n"
			+ "order by useState, updateDate desc \r\n")
	List<Media> getMediaList();
	
	
	@Select("SELECT mediaUID, mediaTitle, mediaLink, contents, imgURL, imgName, imgRealName, regId, regDate, updateId, date_format(updateDate, '%Y-%m-%d %h:%i:%s') updateDate, useState \r\n"
			+ "FROM homepage.MediaCenter \r\n"
			+ "Where useState <> 3 \r\n"
			+ "order by useState, updateDate desc \r\n")
	Page<Media> getMediaListByPage();
	

	@Select("SELECT mediaUID, mediaTitle, mediaLink, contents, imgURL, imgName, imgRealName, regId, regDate, updateId, date_format(updateDate, '%Y-%m-%d %h:%i:%s') updateDate, useState \r\n" + 
			"FROM homepage.MediaCenter \r\n" + 
			"where useState = 0 \r\n" + 
			"order by updateDate desc")
	List<Media> getUsedMediaList();
	
	
	@Insert("INSERT INTO homepage.MediaCenter( mediaTitle, mediaLink, useState, contents, imgURL, imgName, imgRealName, regId, regDate, updateDate ) "
		+ " VALUE( #{mediaTitle}, #{mediaLink}, #{useState}, #{contents}, #{imgURL}, #{imgName}, #{imgRealName}, #{regId}, now(), now()  ) ")
	int insertMedia(Media media);
	
	
	@Update("UPDATE homepage.MediaCenter "
			+ " SET mediaTitle = #{mediaTitle}, \r\n" + 
			"	mediaLink = #{mediaLink}, \r\n" + 
			"	useState = #{useState}, \r\n" + 
			"	contents = #{contents}, \r\n" + 
			"	imgURL = #{imgURL}, \r\n" + 
			"	imgName = #{imgName}, \r\n" + 
			"	imgRealName = #{imgRealName}, \r\n" + 
			"	updateDate = now() \r\n"
			+ " where mediaUID = #{mediaUID} ")
	int updateMedia(Media media);
	
	
//	@Update("DELETE FROM homepage.MediaCenter WHERE mediaUID = #{mediaUID}")
	@Update("UPDATE homepage.MediaCenter \r\n"
			+ " SET useState = 3 , \r\n" + 
			"	updateDate = now() \r\n"
			+ " where mediaUID = #{mediaUID} ")
	int deleteMedia(@Param("mediaUID") int mediaUID);
	
	
	@Update("UPDATE homepage.MediaCenter "
			+ " SET useState = #{useState}, \r\n" + 
			"	updateDate = now() \r\n"
			+ " where mediaUID = #{mediaUID} ")
	int updateMediaState(Media media);

	
	
}
