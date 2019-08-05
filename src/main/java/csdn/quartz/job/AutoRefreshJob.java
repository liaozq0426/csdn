package csdn.quartz.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

@DisallowConcurrentExecution // 防止当任务执行时间（假如5秒）长于任务设定的间隔时间（假如3秒），本次任务还未执行完毕又开始下一次任务调度。
public class AutoRefreshJob implements Job{
	
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	private static int refreshCount = 0;
	
	public static String currentTime(){
		Date currentTime = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(currentTime);
	}
	
	
	private static String[] refreshUrls = new String[] {
		"https://blog.csdn.net/u012693016/article/details/94404803"
		,"https://blog.csdn.net/u012693016/article/details/94400581"
		,"https://blog.csdn.net/u012693016/article/details/93623427"
		,"https://blog.csdn.net/u012693016/article/details/93502597"
		,"https://blog.csdn.net/u012693016/article/details/93350965"
		,"https://blog.csdn.net/u012693016/article/details/93209268"
	};

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		refreshCount += 1;
		System.out.println("第" + refreshCount + "次刷新开始....");
		System.out.println("刷新时间:" + currentTime());
		for(String url : refreshUrls){
			
			int random = (int) (1 + Math.random() * 4);
			int sleepTime = random * 1000;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String content = restTemplate.getForObject(url, String.class);
			System.out.println("睡眠时间:"+ sleepTime + ",数据长度为:" + content.length());
		}
		System.out.println("第" + refreshCount + "次刷新结束....");
	}
}
