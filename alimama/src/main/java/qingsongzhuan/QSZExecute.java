package qingsongzhuan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;

import fx.IpUtils;

public class QSZExecute {

	public static int poolSize = 10;
	
	static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors
			.newFixedThreadPool(poolSize);
	
	public static List<HttpHost> getIps(){
		List<HttpHost> hosts = new ArrayList<HttpHost>();
		/*HttpHost e = new HttpHost("112.74.163.60", 139);
		hosts.add(e);
		*/
		hosts = IpUtils.getIpsmemories1999();
		return hosts;
	}

	public static void main(String[] args) {
		
		final QSZMain QSZMain = new QSZMain();
		
		

		Runnable runnable = new Runnable() {
			public void run() {
				
				try{
				// task to run goes here
				System.out.println("Hello !!");
				int queueSize = threadPoolExecutor.getQueue().size();
				System.out.println("queueSize : " + queueSize);

				if(queueSize < poolSize){
					for (final HttpHost host:getIps()) {
						threadPoolExecutor.execute(new Runnable() {
							public void run() {
								try {
									System.out.println("host : "+host);
									//Thread.sleep(10000);
									QSZMain.executeAll(host);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}else{
					System.out.println("线程池队列容量充足>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		
		
		
		
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runnable, 2, 10, TimeUnit.SECONDS);
	}
}
