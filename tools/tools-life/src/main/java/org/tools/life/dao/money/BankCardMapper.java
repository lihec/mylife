package org.tools.life.dao.money;

import java.util.List;

import org.tools.life.domain.base.Page;
import org.tools.life.domain.money.BankCard;

public interface BankCardMapper {
	
	List<BankCard> getCardList(Page page);
	
	void insertCard(BankCard bankCard);
	
	int deleteCard(Integer id);
	
	int updateCard(BankCard bankCard);
	
	BankCard getCardById(Integer id);
	
	/**
	 * 按分类统计总额
	 * @return
	 */
	List<BankCard> getCardInfoTotal();
	
	
	List<BankCard> getCreditCard();
}
