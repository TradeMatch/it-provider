package org.itrade;

import org.itrade.jobs.SimpleJob;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {

//    @Value("${mongo.configuration.host:localhost}")
//    private String mongoHost;

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(SimpleJob.class);
        jobDetailFactoryBean.setDurability(true);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("key", "value");
        jobDetailFactoryBean.setJobDataMap(jobDataMap);
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression("*/10 0 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobDetails(jobDetailFactoryBean().getObject());
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean().getObject());
        return schedulerFactoryBean;
    }

}
