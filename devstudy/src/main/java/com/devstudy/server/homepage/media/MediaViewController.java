package com.zoomansa.homepage.media;

import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zoomansa.homepage.media.model.Media;
import com.zoomansa.homepage.media.service.MediaCenterService;


@Controller
//@RequestMapping("/setting")
public class MediaViewController {
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	MediaCenterService mediaCenterService;
	
	
	@RequestMapping("/MediaCenter")
	public ModelAndView getMediaCenterView (@RequestParam(value = "pageNo", required = false, defaultValue="0") Integer pageNo,
									@RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize) {

		ModelAndView mav = new ModelAndView("/admin/mediaCenter");

		Page<Media> page = mediaCenterService.getMediaListByPage(pageNo, pageSize);
		mav.addObject("list", page);
		mav.addObject("pages", page.getPages());
		mav.addObject("total", page.getTotal());
		mav.addObject("pageNo", page.getPageNum());
		mav.addObject("pageSize", page.getPageSize());
		mav.addObject("startRow", page.getStartRow());
		mav.addObject("endRow", page.getEndRow());
		
		mav.addObject("pageNo", pageNo);
		
		return mav;
	}
	
	@RequestMapping("/MediaCenter/create")
	public ModelAndView getCreateMediaView() {
		ModelAndView mav = new ModelAndView("/admin/create_media");
		return mav;
	}

	@RequestMapping("/MediaCenter/{mediaUID}")
	public ModelAndView getUpdateMediaView(@PathVariable(value="mediaUID") int mediaUID) {
		ModelAndView mav = new ModelAndView("/admin/update_media");
		mav.addObject("media", mediaCenterService.getMedia(mediaUID));
		return mav;
	}
	
	
}
