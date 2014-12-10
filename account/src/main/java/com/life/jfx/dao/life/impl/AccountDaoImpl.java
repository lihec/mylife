package com.life.jfx.dao.life.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.life.jfx.dao.base.BaseDao;
import com.life.jfx.dao.life.AccountDao;
import com.life.jfx.pojo.account.Account;
import com.life.jfx.pojo.account.QueryAccountList;

public class AccountDaoImpl extends BaseDao implements AccountDao {

	@Override
	public List<Account> getAccountList(QueryAccountList queryAccountList) {
		int pageno = queryAccountList.getPageno();
		int pagesize = queryAccountList.getPagesize();
		RowBounds rowBounds = new RowBounds(pageno * pagesize, (pageno + 1)
				* pagesize);
		return selectList("com.life.jfx.dao.life.AccountDao.getAccountList",
				queryAccountList, rowBounds);
	}
	
	public static void main(String[] args) {
		AccountDao accountDao = new AccountDaoImpl();
		QueryAccountList queryAccountList = new QueryAccountList();
		queryAccountList.setPageno(0);
		queryAccountList.setPagesize(10);	
		List<Account> list = accountDao.getAccountList(queryAccountList);
		System.out.println(list);
	}

}
