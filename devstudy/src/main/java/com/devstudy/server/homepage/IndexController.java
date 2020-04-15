package com.devstudy.server.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/")
    public String root() {
		return "/index";    
    }
    
    @RequestMapping(value="/team")
    public String team() {
        return "/team";    
    }

    @RequestMapping(value="/home")
    public String home() {
    	return "redirect:/";    
    }
    
}
