package org.itrade.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

/**
 * Created by Dmytro Podyachiy on 08/11/14.
 */
@Service
public class ExampleService implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println(" >>>>>>> SPRING JOB >>>>>>>>: ");
    }

}
