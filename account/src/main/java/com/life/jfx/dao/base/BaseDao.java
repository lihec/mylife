package com.life.jfx.dao.base;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.life.jfx.mybatis.MyBatisConfig;

public class BaseDao {

	private SqlSessionFactory sqlSessionFactory;

	public BaseDao() {
		sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
	}

	private SqlSession openSession() {
		return sqlSessionFactory.openSession(true);
	}

	public <T> List<T> selectList(String sql, Object param) {
		SqlSession sqlSession = openSession();
		List<T> result = sqlSession.selectList(sql, param);
		sqlSession.close();
		return result;
	}

	public <T> List<T> selectList(String sql, Object param, RowBounds rowBounds) {
		SqlSession sqlSession = openSession();
		List<T> result = sqlSession.selectList(sql, param, rowBounds);
		sqlSession.close();
		return result;
	}

	public int update(String sql, Object param) {
		SqlSession sqlSession = openSession();
		int result = sqlSession.update(sql, param);
		sqlSession.close();
		return result;
	}

	public int insert(String sql, Object param) {
		SqlSession sqlSession = openSession();
		int result = sqlSession.insert(sql, param);
		sqlSession.close();
		return result;
	}

	public int delete(String sql, Object param) {
		SqlSession sqlSession = openSession();
		int result = sqlSession.delete(sql, param);
		sqlSession.close();
		return result;
	}

	public Object selectOne(String sql, Object param) {
		SqlSession sqlSession = openSession();
		Object result = sqlSession.selectOne(sql, param);
		sqlSession.close();
		return result;
	}

}
