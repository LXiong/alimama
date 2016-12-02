package fx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;

public class Execute {

	public static int poolSize = 10;
	
	static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors
			.newFixedThreadPool(poolSize*10);
	
	static ExecutorService threadPoolExecutorRS = Executors.newCachedThreadPool();
	
	public static List<HttpHost> getIps(){
		List<HttpHost> hosts = new ArrayList<HttpHost>();
		/*HttpHost e = new HttpHost("112.74.163.60", 139);
		hosts.add(e);
		*/
		hosts = IpUtils.getIpsmemories1999();
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

				if(queueSize < poolSize){
					for (final HttpHost host:getIps()) {
						
						final Future<Boolean> future  = threadPoolExecutor.submit(new Callable<Boolean>() {
							public Boolean call() throws Exception {
								try {
									System.out.println("host : "+host);
									main.executeAll(host);
								} catch (Exception e) {
									e.printStackTrace();
								}
								return true;
							};
							
						});
						 
						 threadPoolExecutorRS.submit(new Callable<Boolean>() {
							 public Boolean call() throws Exception {
								 try{
									future.get(80000, TimeUnit.SECONDS);
								 }catch(Exception e){
									 System.out.println("timeOut>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
									 e.printStackTrace();
								 }
								 return true;
							 };
						}) ;
						 
						 
					}
				}else{
					System.out.println("线程池队列容量充足>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				}
			}
		};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runnable, 2, 10, TimeUnit.SECONDS);
	}
}
