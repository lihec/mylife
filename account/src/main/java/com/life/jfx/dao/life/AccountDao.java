package com.life.jfx.dao.life;

import java.util.List;

import com.life.jfx.pojo.account.Account;
import com.life.jfx.pojo.account.QueryAccountList;

public interface AccountDao {
	
	List<Account> getAccountList(QueryAccountList queryAccountList);
	
	  
}