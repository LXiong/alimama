package util;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpHost;

public class IpPoolUtil {
	
	static BlockingQueue<HttpHost> blockingQueue = new ArrayBlockingQueue<HttpHost>(100);
	static String proxyURL="http://ip.memories1999.com/api.php?dh=2764810913906166&sl=10&xl=%E5%9B%BD%E5%86%85&gl=1";
	
	static{
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HttpHost getHttpHost(){
		for(int i=0;i<12;i++){
			System.out.println("开始获取从队列里面获取代理ip>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			HttpHost host=null;
			try {
				host = blockingQueue.poll(5,TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(host!=null){
				System.out.println("成功从ip池获取代理ip>>>>>>>>>>>>>>>>>>>>");
				return host;
			}else{
				System.out.println("获取代理ip失败》》》》》》当前代理ip池大小："+blockingQueue.size());
			}
			
		}
		return null;
		
		
	}
	
	public static void init()throws Exception{

		 Runnable runnable = new Runnable() {  
	            public void run() {  
	                // task to run goes here
	            	//synchronized(ZhuCeSelenium.class){
	            		try{
		            		 System.out.println("开始检测ip池ip数据数量>>>>>>>>>>>>>>>>>>");
		 	                int size = blockingQueue.size();
		 	                System.out.println("当前ip池数量wei:  "+size);
		 	                if(size<5){
		 	                	System.out.println("开始获取代理ip>>>>>>>>>>>>>>>>>>>>>>>>>");
		 	        			//proxyURLList.get(new Random().nextInt(3))
		 	        			List<HttpHost> hosts = IpUtils.getips(proxyURL);
		 	        			 if(CollectionUtils.isEmpty(hosts)){
		 	        				 System.out.println("获取ip为kong>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 	        			 }else{
		 	        				 for(final HttpHost h:hosts){
		 	        					 new Thread(){
		 	        						 public void run() {
		 	        							 boolean flag = IpUtils.createIPAddress(h.getHostName(),h.getPort());
				 	        					 if(flag){
				 	        						 System.out.println("ip==="+h+" 有效加入ip池 "+blockingQueue.offer(h));
				 	        					 }else{
				 	        						 System.out.println("ip==="+h+" 无效");
				 	        					 }
		 	        						 };
		 	        					 }.start();
		 	        				 }
		 	        				// System.out.println("获取代理ip成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ip="+hosts.get(0).getHostName()+" prot:"+hosts.get(0).getPort());
		 	        			 }
		 	                }else{
		 	                	System.out.println("当前ip池数据量充足>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 	                }
		            	}catch(Exception e){
		            		e.printStackTrace();
		            	}
	            		
	            	//}
	            }  
	        };  
	        ScheduledExecutorService service = Executors  
	                .newSingleThreadScheduledExecutor();  
	        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
	        service.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);
	}

}
