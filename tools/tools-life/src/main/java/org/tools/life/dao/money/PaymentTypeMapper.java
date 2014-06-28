package org.tools.life.dao.money;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.tools.life.domain.money.PaymentType;

public interface PaymentTypeMapper {
	
	List<PaymentType> getPayTypeData(String ptype);
	
	void insertPayType(PaymentType paymentType);
	
	int updatePayType(PaymentType paymentType);
	
	int deletePayType(int pid);
	
	PaymentType getPayTypeById(int pid);


    List<PaymentType> getChildPayType(@Param("topPid") String topPid,@Param("ptype") String ptype);
	
}
