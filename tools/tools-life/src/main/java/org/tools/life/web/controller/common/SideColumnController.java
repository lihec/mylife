package org.tools.life.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tools.life.web.base.BaseController;
@Controller
@RequestMapping("/sideColumn")
public class SideColumnController extends BaseController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "/common/sideColumnMenu";
	}

}
