package org.tools.life.web.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tools.life.web.base.BaseController;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return "home";
	}

}
