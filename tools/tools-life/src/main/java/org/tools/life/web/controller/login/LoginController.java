package org.tools.life.web.controller.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tools.life.domain.user.User;
import org.tools.life.service.login.LoginService;
import org.tools.life.web.base.BaseController;
import org.tools.life.web.form.login.LoginForm;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@Validated LoginForm form,HttpServletRequest request,HttpSession session) {
		try {
			logger.info("开始登陆...");
			logger.info(form.toString());
			String usercode = form.getUsercode();
			String password = form.getPassword();
			User user = loginService.getUser(usercode);
			if(user==null){
				return getFailResult("用户不存在");
			}
			if(!password.equals(user.getPassword())){
				return getFailResult("用户名或密码错误");
			}
			session.setAttribute("user", user);
			return getSuccessResult();
		} catch (Exception e) {
			return getFailResult(UNKNOWNEXCEPTION);
		}
    }

}
