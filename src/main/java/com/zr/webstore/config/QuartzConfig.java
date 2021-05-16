package com.zr.webstore.config;


import com.zr.webstore.job.SeckillHtmlJob;
import com.zr.webstore.job.SeckillJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

@Configuration
public class QuartzConfig {
    private static String JOB_GROUP_NAME = "PJB_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "PJB_TRIGGERGROUP_NAME";

    /**
     * 定时任务1：
     * 同步用户信息Job（任务详情）
     */
    @Bean
    public JobDetail syncUserJobDetail()
    {
        JobDetail jobDetail = JobBuilder.newJob(SeckillJob.class)
                .withIdentity("syncUserJobDetail",JOB_GROUP_NAME)
                .storeDurably() //即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
        return jobDetail;
    }

    /**
     * 定时任务1：
     * 同步用户信息Job（触发器）
     */
    @Bean
    public Trigger syncUserJobTrigger()
    {
        //每隔30秒执行一次
//        0 0 0 * * ?  每天零点重置
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/50 * * * * ?");

        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(syncUserJobDetail())//关联上述的JobDetail
                .withIdentity("syncUserJobTrigger",TRIGGER_GROUP_NAME)//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
        return trigger;
    }
    @Bean
    public JobDetail seckillJobDetail()
    {
        JobDetail jobDetail = JobBuilder.newJob(SeckillHtmlJob.class)
                .withIdentity("seckillJobDetail",JOB_GROUP_NAME)
                .storeDurably() //即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
        return jobDetail;
    }

    @Bean
    public Trigger seckillTrigger()
    {
        //每隔10分钟执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 */10 * * * ?");

        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(seckillJobDetail())//关联上述的JobDetail
                .withIdentity("seckillTrigger",TRIGGER_GROUP_NAME)//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
        return trigger;
    }

}
