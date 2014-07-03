package org.tools.life.service.money.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tools.life.dao.money.BankCardMapper;
import org.tools.life.domain.base.BaseListBO;
import org.tools.life.domain.base.Page;
import org.tools.life.domain.money.BankCard;
import org.tools.life.domain.money.CardListVo;
import org.tools.life.domain.money.FinanceView;
import org.tools.life.service.common.SafeService;
import org.tools.life.service.money.BankCardService;
import org.tools.life.web.base.LifeExcepiton;

@Service
public class BankCardServiceImpl implements BankCardService {
	@Autowired
	private BankCardMapper bankCardMapper;
	@Autowired
	private SafeService safeService;

	@Override
	public BaseListBO getCardList(CardListVo cardListVo) {
		Page page = Page.getInstance(cardListVo);
		List<BankCard> list = bankCardMapper.getCardList(page);
		for (BankCard bankCard : list) {
			decodeCard(bankCard);
		}
		return BaseListBO.getInstance(page, list);
	}

	@Override
	public void addCard(BankCard bankCard) {
		encodeCard(bankCard);
		bankCardMapper.insertCard(bankCard);
	}

	@Override
	public int deleteCard(Integer id) {
		return bankCardMapper.deleteCard(id);
	}

	@Override
	public int updateCard(BankCard bankCard) {
		encodeCard(bankCard);
		return bankCardMapper.updateCard(bankCard);
	}

	@Override
	public BankCard getCardById(Integer id) {
		BankCard bankCard = bankCardMapper.getCardById(id);
		decodeCard(bankCard);
		return bankCard;
	}

	private void encodeCard(BankCard bankCard) {
		if (bankCard.getType() == 1) {
			// 计算信用卡可以额度
			bankCard.setAvailableCredit(new BigDecimal(bankCard.getFixCredit()).subtract(bankCard.getBalance()));
		}
		try {
			bankCard.setNetPwd(safeService.encodeToHexString(bankCard
					.getNetPwd()));
			bankCard.setUdPwd(safeService.encodeToHexString(bankCard.getUdPwd()));
			bankCard.setSafeCode(safeService.encodeToHexString(bankCard
					.getSafeCode()));
		} catch (Exception e) {
			throw new LifeExcepiton("加密数据出错", e);
		}
	}

	private void decodeCard(BankCard bankCard) {
		try {
			bankCard.setNetPwd(safeService.decode(bankCard.getNetPwd()));
			bankCard.setUdPwd(safeService.decode(bankCard.getUdPwd()));
			bankCard.setSafeCode(safeService.decode(bankCard.getSafeCode()));
		} catch (Exception e) {
			throw new LifeExcepiton("解密数据出错", e);
		}
	}

	@Override
	public FinanceView getFinanceView() {
		FinanceView financeView = new FinanceView();
		List<BankCard> list = bankCardMapper.getCardInfoTotal();
		for (BankCard bankCard : list) {
			int type = bankCard.getType();
			if (type == 0) {
				financeView.setBankAssets(bankCard.getBalance());
			} else if (type == 1) {
				financeView.setCreditCardDebt(bankCard.getBalance());
				financeView.setCreditCardLimit(bankCard.getFixCredit());
				financeView.setAvlCreditCardLimit(bankCard.getAvailableCredit());
			} else if (type == 2) {
				financeView.setOtherAssets(bankCard.getBalance());
			}
		}
		financeView.setTotalAssets(financeView.getBankAssets().add(financeView.getOtherAssets()));
		financeView.setNetAssets(financeView.getTotalAssets().subtract(financeView.getCreditCardDebt()));
		return financeView;
	}

    @Override
    public List<BankCard> getCardByType(String type) {
        return bankCardMapper.getCardByType(type);
    }

}
