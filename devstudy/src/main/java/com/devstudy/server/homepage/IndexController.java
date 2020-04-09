package com.devstudy.server.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value="/")
    public String root() {
		return "/index";    
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
