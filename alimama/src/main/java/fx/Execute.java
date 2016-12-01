package fx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;

public class Execute {

	static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors
			.newFixedThreadPool(5);
	
	public static List<HttpHost> getIps(){
		List<HttpHost> hosts = new ArrayList<HttpHost>();
		HttpHost e = new HttpHost("183.141.156.227", 3128);
		hosts.add(e);
		return hosts;
	}

	public static void main(String[] args) {
		
		final Main main = new Main();

		Runnable runnable = new Runnable() {
			public void run() {
				// task to run goes here
				System.out.println("Hello !!");
				int queueSize = threadPoolExecutor.getQueue().size();
				System.out.println("queueSize : " + queueSize);

				if(queueSize < 10){
					for (final HttpHost host:getIps()) {
						threadPoolExecutor.execute(new Runnable() {
							public void run() {
								try {
									System.out.println("host : "+host);
									//Thread.sleep(10000);
									main.executeAll(host);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}else{
					System.out.println("线程池队列容量充足>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				}
			}
		};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runnable, 2, 20, TimeUnit.SECONDS);
	}
}
