package com.zoomansa.homepage.media.api;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.zoomansa.homepage.media.model.Media;
import com.zoomansa.homepage.media.service.MediaCenterService;


@CrossOrigin
@RestController
public class MediaCenterController {
	
	private static final Logger log = LoggerFactory.getLogger(MediaCenterController.class);
	
	@Autowired
	MediaCenterService mediaCenterService;

	@CrossOrigin(origins = {"http://ars.zoomansa.com", "http://parking.zoomansa.com"})
	@GetMapping(value="/media/{mediaUID}", produces = "application/json")
	public Media getMedia(@PathVariable(value="mediaUID") int mediaUID) {
		return mediaCenterService.getMedia(mediaUID);
	}
	
	@CrossOrigin(origins = {"http://ars.zoomansa.com", "http://parking.zoomansa.com"})
	@GetMapping(value="/media", produces = "application/json")
	@ResponseBody
	public HashMap<String, Object> getMediaListByPage(@RequestParam(value = "pageNo", required = false, defaultValue="0") Integer pageNo,
											@RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize ) {

		Page<Media> page = mediaCenterService.getMediaListByPage(pageNo, pageSize);
		
		HashMap<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("list", page);
		resultMap.put("pages", page.getPages());
		resultMap.put("total", page.getTotal());
		resultMap.put("pageNo", page.getPageNum());
		resultMap.put("pageSize", page.getPageSize());
		resultMap.put("startRow", page.getStartRow());
		resultMap.put("endRow", page.getEndRow());
						
		return resultMap;
	}
	
	
	@CrossOrigin(origins = {"http://ars.zoomansa.com", "http://parking.zoomansa.com"})
	@PostMapping("/media")
	public int insertMedia(Media media, @RequestParam("file") MultipartFile file) {

//		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//		if(!( !"52.79.225.171".equals(ClientUtils.getRemoteIP(req))
//				&& !"13.209.199.241".equals(ClientUtils.getRemoteIP(req)) )){
//			return 0;
//		}
		
		return mediaCenterService.insertMedia(media, file);
	}

	@CrossOrigin(origins = {"http://ars.zoomansa.com", "http://parking.zoomansa.com"})
	@PostMapping("/media/{mediaUID}")
	public int updateMedia(Media media, @PathVariable(value="mediaUID") int mediaUID
						, @RequestParam("file") MultipartFile file) {

//		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//		if(!( !"52.79.225.171".equals(ClientUtils.getRemoteIP(req))
//				&& !"13.209.199.241".equals(ClientUtils.getRemoteIP(req)) )){
//			return 0;
//		}
		
		return mediaCenterService.updateMedia(media, file);
	}
	
		
	@CrossOrigin(origins = {"http://ars.zoomansa.com", "http://parking.zoomansa.com"})
	@PutMapping(value="/media/state" , produces = "application/json")
	public int updateMediaState(Media media) {
		
//		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//		if(!( !"52.79.225.171".equals(ClientUtils.getRemoteIP(req))
//				&& !"13.209.199.241".equals(ClientUtils.getRemoteIP(req)) )){
//			return 0;
//		}
		
		int result = mediaCenterService.updateMediaState(media);
		return result;			
	}
	
	@CrossOrigin(origins = {"http://ars.zoomansa.com", "http://parking.zoomansa.com"})
	@DeleteMapping(value="/media/{mediaUID}", produces = "application/json")
	public int deleteMedia(@PathVariable(value="mediaUID") int mediaUID) {
		
//		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//		if(!( !"52.79.225.171".equals(ClientUtils.getRemoteIP(req))
//				&& !"13.209.199.241".equals(ClientUtils.getRemoteIP(req)) )){
//			return 0;
//		}
		
//		System.out.println(mediaUID);
		if(0 == mediaUID ) {
			return 0;
		}
		
		int result = mediaCenterService.deleteMedia(mediaUID);
//		System.out.println(result);
		return result;
	}
	

}