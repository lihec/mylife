package org.tools.life.service.money;

import org.tools.life.domain.base.BaseListBO;
import org.tools.life.domain.money.BankCard;
import org.tools.life.domain.money.CardListVo;
import org.tools.life.domain.money.FinanceView;

import java.util.List;

public interface BankCardService {

	BaseListBO getCardList(CardListVo cardListVo);

	void addCard(BankCard bankCard);

	int deleteCard(Integer id);

	int updateCard(BankCard bankCard);

	BankCard getCardById(Integer id);
	
	FinanceView getFinanceView();

    /**
     * 根据类型获取卡
     * @param type -1时查询非信用卡，其他直接按type值查询
     * @return
     */
    List<BankCard> getCardByType(String type);
}
