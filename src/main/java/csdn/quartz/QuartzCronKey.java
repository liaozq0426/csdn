package csdn.quartz;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title quartz cron 表达式配置bean
 * @author gavin
 * @date 2019年5月10日
 */
@Component
@ConfigurationProperties(prefix="quartz.expression")
public class QuartzCronKey {
	
	private String autoRefresh;

	public String getAutoRefresh() {
		return autoRefresh;
	}

	public void setAutoRefresh(String autoRefresh) {
		this.autoRefresh = autoRefresh;
	}

	
}
