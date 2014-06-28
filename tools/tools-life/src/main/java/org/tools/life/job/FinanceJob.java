package org.tools.life.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.tools.life.job.task.CreditCardTask;

@Service
public class FinanceJob {
	@Autowired
	private CreditCardTask creditCardTask;
	
	@Scheduled(cron = "0 30 10 * * ?")
    public void job1() {
        creditCardTask.notice();
    }

}
