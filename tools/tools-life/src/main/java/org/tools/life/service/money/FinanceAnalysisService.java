package org.tools.life.service.money;

import java.util.List;

import org.tools.life.domain.base.BaseListBO;
import org.tools.life.domain.money.*;

public interface FinanceAnalysisService {
	
	List<PaymentType> getPayTypeData(String ptype);
	
	void insertPayType(PaymentType paymentType);
	
	int updatePayType(PaymentType paymentType);
	
	int deletePayType(int pid);
	
	PaymentType getPayTypeById(int pid);

	BaseListBO getTransRecordList(TransRecordListVo transRecordListVo);


    List<PaymentType> getPayTypeWithChild(String ptype);

    int insert(TransDetailsVo transDetailsVo);

    int deleteTransDetail(String tid);

}
