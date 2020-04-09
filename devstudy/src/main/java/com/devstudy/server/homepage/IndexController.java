package com.zoomansa.homepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zoomansa.homepage.area.service.ParkingAreaService;
import com.zoomansa.homepage.media.service.MediaCenterService;

@Controller
public class IndexController {

	@Autowired
	ParkingAreaService parkingAreaService;
	
	@Autowired
	MediaCenterService mediaCenterService;
	
	@RequestMapping(value="/")
    public ModelAndView root() {
		ModelAndView mav = new ModelAndView("/homepage/index");
		mav.addObject("serviceArea", parkingAreaService.getServiceArea());
		mav.addObject("mediaCenter", mediaCenterService.getUsedMediaList());
		return mav;    
    }
    
    @RequestMapping(value="/index")
    public String index() {
        return "redirect:/";    
    }

    @RequestMapping(value="/home")
    public String home() {
    	return "redirect:/";    
    }
    
}
