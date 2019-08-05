package csdn.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import csdn.quartz.QuartzCronKey;
import csdn.quartz.job.AutoRefreshJob;

@Configuration
public class AutoRefreshTask {

	@Autowired
	private QuartzCronKey quartzCronKey;
	
	
	@Bean(name="autoRefreshFactory")
	public JobDetailFactoryBean jobDetailFactoryBean() {
		// 生成jobDetail
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		// 设置对应的Job类型
		factory.setJobClass(AutoRefreshJob.class);
		return factory;
	}
	
	
	@Bean("autoRefreshTrigger")
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
		// 设置jobDetail
		factory.setJobDetail(jobDetailFactoryBean().getObject());
		factory.setStartDelay(1000);
		// 定时任务cron表达式配置
		factory.setCronExpression(quartzCronKey.getAutoRefresh());
		return factory;
	}
}
