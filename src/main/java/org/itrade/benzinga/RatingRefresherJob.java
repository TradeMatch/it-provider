package org.itrade.benzinga;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by Dmytro Podyachiy on 08/11/14.
 *
 * Job refreshing today's benzinga ratings
 */
@Service
public class RatingRefresherJob implements Job {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public BenzingaService benzingaService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        benzingaService.updateRatings(LocalDate.of(2014, 11, 7));
    }

}
