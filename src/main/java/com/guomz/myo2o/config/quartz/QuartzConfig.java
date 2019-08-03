package com.guomz.myo2o.config.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.guomz.myo2o.service.ProductSellDailyService;

@Configuration
public class QuartzConfig {

	@Autowired
	private ProductSellDailyService productSellDailyService;
	@Autowired
	private MethodInvokingJobDetailFactoryBean jobDetailFactory;
	@Autowired
	private CronTriggerFactoryBean productSellDailyTriggerFactory;
	
	@Bean(name="jobDetailFactory")
	public MethodInvokingJobDetailFactoryBean createJobDetail()
	{
		//制做任务的工厂
		MethodInvokingJobDetailFactoryBean jobDetailFactoryBean=new MethodInvokingJobDetailFactoryBean();
		jobDetailFactoryBean.setName("product_sell_daily_job");
		jobDetailFactoryBean.setGroup("job_product_sell_daily-group");
		//防止同一个job由于多个trigger而并发执行
		jobDetailFactoryBean.setConcurrent(false);
		//设置运行任务的类与方法
		jobDetailFactoryBean.setTargetObject(productSellDailyService);
		jobDetailFactoryBean.setTargetMethod("dailyCalculate");
		return jobDetailFactoryBean;
	}
	
	@Bean(name="productSellDailyTriggerFactory")
	public CronTriggerFactoryBean createProductSellDailyTrigger()
	{
		CronTriggerFactoryBean triggerFactory=new CronTriggerFactoryBean();
		triggerFactory.setName("product_sell_daily_trigger");
		triggerFactory.setGroup("job_product_sell_daily_group");
		//绑定任务
		triggerFactory.setJobDetail(jobDetailFactory.getObject());
		//设置日期表达式
		triggerFactory.setCronExpression("0 0 0 * * ?");
		return triggerFactory;
	}
	
	/**
	 * 创建调度工厂
	 * @return
	 */
	@Bean("schedulerFactory")
	public SchedulerFactoryBean createSchedulerFactory()
	{
		SchedulerFactoryBean schedulerFactory=new SchedulerFactoryBean();
		schedulerFactory.setTriggers(productSellDailyTriggerFactory.getObject());
		return schedulerFactory;
	}
}
