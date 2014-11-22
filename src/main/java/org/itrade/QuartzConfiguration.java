package org.itrade;

import org.itrade.benzinga.RatingRefresherJob;
import org.itrade.jobs.AutowiringSpringBeanJobFactory;
import org.itrade.yahoo.YahooHistoricRefresherJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfiguration {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${quartz.job.rating.cron:0 0 7-22 * * ?}")
    private String ratingCron;

    @Value("${quartz.job.yahoohistoric.cron:0 0 20 ? * MON-FRI}")
    private String yahooHistoricCron;

    @Bean
    public JobDetailFactoryBean jobRatingDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(RatingRefresherJob.class);
        jobDetailFactoryBean.setName("RatingRefresher");
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setGroup("group-provider");
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean cronRatingTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobRatingDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression(ratingCron);
        cronTriggerFactoryBean.setGroup("group-provider");
        return cronTriggerFactoryBean;
    }

    @Bean
    public JobDetailFactoryBean jobYahooHistoricDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(YahooHistoricRefresherJob.class);
        jobDetailFactoryBean.setName("YahooHistoricRefresher");
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setGroup("group-provider");
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean cronYahooHictoricTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobYahooHistoricDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression(yahooHistoricCron);
        cronTriggerFactoryBean.setGroup("group-provider");
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean quartzScheduler() throws SchedulerException {
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
        quartzScheduler.setOverwriteExistingJobs(true);
        quartzScheduler.setSchedulerName("provider-ratings-scheduler");

        // custom job factory of spring with DI support for @Autowired!
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        quartzScheduler.setJobFactory(jobFactory);

        quartzScheduler.setQuartzProperties(quartzProperties());

        Trigger[] triggers = {
                cronRatingTriggerFactoryBean().getObject(),
                cronYahooHictoricTriggerFactoryBean().getObject(),
        };
        quartzScheduler.setTriggers(triggers);

        return quartzScheduler;
    }

    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        Properties properties = null;
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();

        } catch (IOException e) {
            logger.warn("Cannot load quartz.properties.");
        }
        return properties;
    }

}
