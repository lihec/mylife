package org.tools.life.dao.money;

import java.util.List;

import org.tools.life.domain.base.Page;
import org.tools.life.domain.money.TransDetails;
import org.tools.life.domain.money.TransDetailsBo;


public interface TransDetailsMapper {
	
	List<TransDetailsBo> getTransList(Page page);

    int insert(TransDetails record);

    int deleteByPrimaryKey(String tid);
	
   /*





    TransDetails selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(TransDetails record);

    int updateByPrimaryKey(TransDetails record);*/
}