package csdn.browser;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Run2 {
	public static void main(String[] args) throws Exception{
		CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
		HttpGet httpGet=new HttpGet("https://blog.csdn.net/u012693016/article/details/94404803"); // 创建httpget实例
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0"); // 设置请求头消息User-Agent
		CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
		HttpEntity entity = response.getEntity(); // 获取返回实体
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("网页内容长度："+ content.length()); // 获取网页内容
		System.out.println(content);
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭
	}

}
