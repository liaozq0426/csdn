package csdn.browser;

import java.awt.Desktop;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Run {
	
	private static String[] refreshUrls = new String[] {
		"https://blog.csdn.net/u012693016/article/details/94404803"
		,"https://blog.csdn.net/u012693016/article/details/94400581"
		,"https://blog.csdn.net/u012693016/article/details/93623427"
		,"https://blog.csdn.net/u012693016/article/details/93502597"
		,"https://blog.csdn.net/u012693016/article/details/93350965"
		,"https://blog.csdn.net/u012693016/article/details/93209268"
		,"https://blog.csdn.net/u012693016/article/details/94600158"
		,"https://blog.csdn.net/u012693016/article/details/94644085"
		,"https://blog.csdn.net/u012693016/article/details/95047722"
		,"https://blog.csdn.net/u012693016/article/details/95060633"
		,"https://blog.csdn.net/u012693016/article/details/96702309"
		,"https://blog.csdn.net/u012693016/article/details/97272141"
		,"https://blog.csdn.net/u012693016/article/details/98059766"
	};

	@SuppressWarnings("static-access")
	public static void runBroswer() {
        try {
        	for(String url : refreshUrls){        		
        		Desktop desktop = Desktop.getDesktop();
        		if (desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
        			URI uri = new URI(url);
        			desktop.browse(uri); //使用系统默认的浏览器执行这个url
        			//Runtime.getRuntime().exec("taskkill  /IM firefox.exe"); //因为我系统默认的是火狐,然后关闭火狐浏览器
        		}
        	}
        	int closeInterval = refreshUrls.length * 1000;
        	Thread.sleep(closeInterval);
            Runtime.getRuntime().exec("taskkill /F /IM Iexplore.exe");
            System.out.println("ok");
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	public static String currentTime(){
		Date currentTime = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(currentTime);
	}
	
	public static void main(String[] args) throws Exception {
		int count = 0;
		while(true){	
			String currentTime = currentTime();
			count += 1;
			System.out.println("第:" + count + "次刷新开始,当前时间" + currentTime);
			runBroswer();
			Thread.sleep(20 * 60 * 1000);
		}
	}
}
