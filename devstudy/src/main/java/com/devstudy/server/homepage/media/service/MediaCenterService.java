package com.zoomansa.homepage.media.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.zoomansa.homepage.media.model.Media;

public interface MediaCenterService {
	Media getMedia(int mediaUID);
	List<Media> getMediaList();
	Page<Media> getMediaListByPage(int pageNo, int pageSize);
	List<Media> getUsedMediaList();
	
	int insertMedia(Media media, MultipartFile file);
	int updateMedia(Media media, MultipartFile file);
	int deleteMedia(int mediaUID);

	int updateMediaState(Media media);
	
}
