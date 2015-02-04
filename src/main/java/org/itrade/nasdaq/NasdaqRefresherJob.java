package org.itrade.nasdaq;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dmytro Podyachiy on 08/11/14.
 * <p>
 * Job refreshing today's Nasdaq companies
 */
@Service
public class NasdaqRefresherJob implements Job {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NasdaqService nasdaqService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.debug("Fire NasdaqRefresherJob");
        nasdaqService.update();
    }

}
