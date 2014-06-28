package org.tools.life.dao.user;

import org.tools.life.domain.user.User;

public interface UserMapper {

	User getUser(String usercode);
}
