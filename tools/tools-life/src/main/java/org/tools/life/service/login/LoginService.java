package org.tools.life.service.login;

import org.tools.life.domain.user.User;

public interface LoginService {
	
	User getUser(String usercode);
}
