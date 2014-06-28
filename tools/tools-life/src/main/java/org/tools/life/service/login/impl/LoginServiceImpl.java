package org.tools.life.service.login.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tools.life.dao.user.UserMapper;
import org.tools.life.domain.user.User;
import org.tools.life.service.login.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(String usercode) {
		return userMapper.getUser(usercode);
	}

}
