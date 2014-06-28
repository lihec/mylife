package org.tools.life.service.money;

import org.tools.life.domain.base.BaseListBO;
import org.tools.life.domain.money.BankCard;
import org.tools.life.domain.money.CardListVo;
import org.tools.life.domain.money.FinanceView;

public interface BankCardService {

	BaseListBO getCardList(CardListVo cardListVo);

	void addCard(BankCard bankCard);

	int deleteCard(Integer id);

	int updateCard(BankCard bankCard);

	BankCard getCardById(Integer id);
	
	FinanceView getFinanceView();
}
