package org.tools.life.job.task;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tools.life.dao.money.BankCardMapper;
import org.tools.life.domain.money.BankCard;
import org.tools.util.mail.commons.CommonMailSender;

@Service
public class CreditCardTask {
	@Autowired
	private BankCardMapper bankCardMapper;

	public void notice() {
		List<BankCard> cardList = bankCardMapper.getCreditCard();
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < cardList.size(); i++) {
			BankCard creditCard = cardList.get(i);
			int payDay = creditCard.getPayDay();
			int billDay = creditCard.getBillDay();
			if (day - billDay == 1) {
				CommonMailSender.sentNoticeSlice(creditCard.getBankName()
						+ "账单日为" + billDay + "，可用额度为"
						+ creditCard.getAvailableCredit());
			}
			if (payDay - day == 2) {
				CommonMailSender.sentNoticeSlice(creditCard.getBankName()
						+ "还款日为" + payDay + "，欠款为" + creditCard.getBalance());
			}
		}
	}

}
