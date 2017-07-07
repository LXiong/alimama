package dataoke;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jodd.http.Cookie;
import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.IpPoolUtil;
import util.SeleniumUtil;

public class TuiJianSelenium {
	
	static String pids = "";
	static int maxOkSize = 200;
	static int allOkSize = 0;
	public static void main(String[] args)throws Exception {
		
	   
	}
	
	static WebDriver webDriver = SeleniumUtil.initChromeDriver();;
	static String tnum = "";
	static int errorSize = 0;
	
	
	public static void execteAll(String[] pid,File... files)throws Exception{
		for(File f:files){
			try{
				execute(pid, f);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				proxy = null;
			}
			
		}
	}
	
	
	static int okSize = 0;
	
	
	static HttpHost host = null;
	
	static String proxyURL="http://ip.memories1999.com/api.php?dh=2764810913906166&sl=10&xl=%E5%9B%BD%E5%86%85&gl=1";
	//static String proxyURL="http://www.xsdaili.com/get?orderid=104948606338185&num=10&an_ha=1&an_an=1&sp1=1&sp2=1&dedup=1&gj=1";
	
	//static String proxyURL="http://www.56pu.com/api?orderId=564127255497792544&quantity=10&line=all&region=&regionEx=&beginWith=&ports=&vport=&speed=&anonymity=2,3&scheme=&duplicate=2&sarea=";
	
	//static String proxyURL="http://dev.kuaidaili.com/api/getproxy/?orderid=999596535183415&num=20&b_pcchrome=1&b_pcie=1&b_pcff=1&protocol=1&method=2&an_an=1&an_ha=1&sp1=1&sp2=1&dedup=1&sep=1";
	
	
	static List<String> proxyURLList = new ArrayList<String>();
	
	static{
		proxyURLList.add(proxyURL);
		proxyURLList.add("http://ip.memories1999.com/api.php?dh=2764810913906166&sl=1&xl=%E5%9B%BD%E5%86%85&gl=1");
		proxyURLList.add("http://ip.memories1999.com/api.php?dh=2764810913906166&sl=1&xl=%E5%9B%BD%E5%86%85&gl=1");
	}
	
	static BlockingQueue<HttpHost> blockingQueue = new ArrayBlockingQueue<HttpHost>(100);
	
	public static void getPrxoyIp()throws Exception{
		try{
			/*if(!blockingQueue.isEmpty()){
				System.out.println("当前ip池大小》》》》》》》》》》》》》》》》》》》"+blockingQueue.size());
				host = blockingQueue.poll(20,TimeUnit.SECONDS);
				return ;
			}*/
			
			/*for(int i=0;i<12;i++){
				System.out.println("开始获取从队列里面获取代理ip>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				host = blockingQueue.poll(5,TimeUnit.SECONDS);
				if(host!=null){
					return;
				}else{
					System.out.println("获取代理ip失败》》》》》》当前代理ip池大小："+blockingQueue.size());
				}
			}*/
			host = IpPoolUtil.getHttpHost();
			
			System.out.println("成功从ip池获取代理ip>>>>>>>>>>>>>>>>>>>>");
			
			/*System.out.println("开始获取代理ip>>>>>>>>>>>>>>>>>>>>>>>>>");
			//proxyURLList.get(new Random().nextInt(3))
			List<HttpHost> hosts = IpUtils.getips(proxyURL);
			 if(CollectionUtils.isEmpty(hosts)){
				 System.out.println("获取ip为kong>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				 Thread.sleep(3000);
			 }else{
				 for(HttpHost h:hosts){
					 boolean flag = IpUtils.createIPAddress(h.getHostName(),h.getPort());
					 if(flag){
 						 //System.out.println("ip==="+h+" 有效加入ip池");
 						 blockingQueue.add(h);
 					 }else{
 						 //System.out.println("ip==="+h+" 无效");
 					 }
					
				 }
				// System.out.println("获取代理ip成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ip="+hosts.get(0).getHostName()+" prot:"+hosts.get(0).getPort());
			 }
			if(hosts==null){
				getPrxoyIp();
			}else{
				host=blockingQueue.take();
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	static ExecutorService exec = Executors.newCachedThreadPool();
	
	public static Callable<Boolean> call(){
		   Callable<Boolean> call = new Callable<Boolean>() {
	            public Boolean call() throws Exception {
	            	try{
	            		  webDriver.close();
	            		  return true;
	            	}catch(Exception e){
	            		e.printStackTrace();
	            	}
	            	return false;
	            }
	        };
		return call;
		
	}
	
	public static void getProxyIpSetWebDriver()throws Exception{
		if(errorSize >=2 || okSize >=3){
			System.out.println("该ip已经注册了3个或者已经错误了2个>>>>>>>>>>>>>>>");
			
			//HttpHost host = new HttpHost("123.125.212.171", 8080);
			if(webDriver!=null){
				System.out.println("关闭浏览器>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				//webDriver.quit();
				//webDriver.close();
				 try{
					 Future<Boolean> future = exec.submit(call());
					 future.get(1000 * 5, TimeUnit.MILLISECONDS);
				 }catch(Exception e){
					 e.printStackTrace();
				 }
				webDriver = null;
			}
			System.out.println("从新打开浏览器>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			getPrxoyIp();
			webDriver = SeleniumUtil.initChromeDriver(host.getHostName(),host.getPort());
			okSize = 0;
			errorSize = 0;
		}else{
			if(webDriver==null){
				 getPrxoyIp();
				 webDriver = SeleniumUtil.initChromeDriver(host.getHostName(),host.getPort());
			}
		}
		
	}
	static int count;
	static int tuiguangOk;
	static  HttpHost proxy = null;
	public static void execute(String[] pids,File file)throws Exception{
		System.out.println("开始读取文件>>>>>>>>>>"+file.getAbsolutePath());
		List<String> lists=FileUtils.readLines(file);
		for(String s:lists){
			if(StringUtils.isBlank(s)){
				continue;
			}
			count++;
			
			try{
				   //proxy  = IpPoolUtil.getHttpHost();
			
					String uname = s.split("\\----")[0].trim();
					String pwd = s.split("\\----")[1].trim();
					System.out.println("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count+" 当前 文件名称："+file.getName());
					
					boolean flag = login(uname,pwd);
					for(String pid:pids){
					    if(flag){
					    	String taoBaoId = getGoodsDatils(pid);
					    	Thread.sleep(Cmd.getSleepTime(500, 2000));
							boolean flagt = serachTuiJian(taoBaoId);
							Thread.sleep(Cmd.getSleepTime(500, 2000));
							getGoodsDatils(pid);
							Thread.sleep(Cmd.getSleepTime(500, 2000));
							flagt = biaoJiAndJiaLog(taoBaoId, pid);
							if(flagt){
								tuiguangOk += 1;
								System.out.println("推广成功》》》》》》》》》》》》》》》》》》》pid="+pid+" uname="+uname +" 当前已成功推广："+tuiguangOk+" 当前ip=="+(proxy==null?"无":proxy.getHostName()));
							}else{
								System.out.println("推广失败》》》》》》》》》》》》》》》》》》     pid="+pid+"   uname="+uname);
								FileUtils.write(new File("D:\\dataoke\\推荐失败。txt"), s+"\r\n", true);
							}
						}
					    Thread.sleep(Cmd.getSleepTime(1000, 2000));
					}
			
           }catch(Exception e){
				e.printStackTrace();
				FileUtils.write(new File("D:\\dataoke\\推荐失败.txt"), s+"\r\n", true);
			}finally {
				proxy=null;
				try{
					webGet("http://www.dataoke.com/logout");
					Thread.sleep(Cmd.getSleepTime(1000, 3000));
				}catch(Exception e){
					
				}
			}
			
			
		}
	}
	
	public static void webGet(String url){
		try {
			webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			webDriver.get(url);
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
	        js.executeScript("window.stop();");  
	        System.out.println("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
		  }
		}
	
	
	public static boolean login(String uname,String pwd)throws Exception{
		try{
			 webGet("http://www.dataoke.com/login");
			Thread.sleep(Cmd.getSleepTime(1000, 2000));
			WebElement element =webDriver.findElement(By.xpath("//input[@data-id='email']"));
		    element.sendKeys(uname);
		    
		    element =webDriver.findElement(By.xpath("//input[@data-id='pwd']"));
		    element.sendKeys(pwd);
		    
		    element =webDriver.findElement(By.xpath("//a[@class='submit-btn login-btn']"));
		    element.click();
		    
		    Thread.sleep(Cmd.getSleepTime(1000, 2000));
		    
		    String str = webDriver.getPageSource();
		    
		    if(str.contains("")){
		    	
		    }
		    //Thread.sleep(Cmd.getSleepTime());
		    //webDriver.get("http://www.dataoke.com/ucenter/favorites_quan.asp");
		    //Thread.sleep(Cmd.getSleepTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

	static   Map<String,Integer> map= new ConcurrentHashMap<String,Integer>();
	
	public static boolean sreachGoods(String taobaoId)throws Exception{
		try{
			webGet("http://www.dataoke.com/search/?keywords="+taobaoId+"&xuan=spid");
			Thread.sleep(Cmd.getSleepTime(5000, 8000));
			WebElement element =webDriver.findElement(By.xpath("//*[@class='add-tui J_add_tui']"));
		    element.click();
		    Thread.sleep(Cmd.getSleepTime(1000,2000));
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	
	
	
	/**
	 * 页面详情。根据页面详情返回淘宝id
	 * @param keywords
	 */
	public static String  getGoodsDatils(String pid){
		String href = "";
		try {
			String uri = "http://www.dataoke.com/item?id="+pid;
			webGet(uri);
			Thread.sleep(2000);
			//
			WebElement webElement = webDriver.findElement(By.xpath("//a[@rel='nofollow']"));
			 href = webElement.getAttribute("href");
			if(StringUtils.isNotBlank(href)){
				return alimama.qq.MatcherUtil.getURIByQueryVal(href,"id");
			}
		} catch (InterruptedException e) {
			System.out.println("getGoodsDatils 出异常了>>>>>>>>>>>>>>>>>>>>>>>");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			//e.printStackTrace();
			return getGoodsDatils(pid);
		}
		
		return href;
	}
	
	
	/**
	 * 搜索页面查找推广
	 * @param keywords
	 */
	public static boolean serachTuiJian(String keywords){
		String uri = "http://www.dataoke.com/search/?keywords="+keywords+"&xuan=spid";
		//webGet(uri);
		try {
			webDriver.get(uri);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		//WebElement webElement = webDriver.findElement(By.xpath("//*[@class='quan_add_u go_info']"));
		//webElement.click();
		js.executeScript("document.querySelectorAll(\"*[class='quan_add_u go_info']\")[0].click();");
		System.out.println("keywords推荐成功 ==="+keywords);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 标记时间，加入日志
	 * @param taoBaoId
	 * @param pid
	 * @throws Exception
	 */
	public static boolean biaoJiAndJiaLog(String taoBaoId,String pid)throws Exception{
			String str = "http://www.dataoke.com/ucenter/favorites_quan.asp";
			
			//今日推广fav_sendtime_22334731828
			webGet(str);
			Thread.sleep(Cmd.getSleepTime(2000, 3000));
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("document.querySelectorAll(\"*[id='fav_sendtime_"+taoBaoId+"']\")[0].click();");
			
			//WebElement webElement = webDriver.findElement(By.xpath("//*[@id='fav_sendtime_"+taoBaoId+"']"));
			//webElement.click();
			
			System.out.println("taoBaoId 今日推广标记成功 =="+taoBaoId);
			
			Thread.sleep(1000);
			
			//webElement = webDriver.findElement(By.xpath("//*[@id='fav_add_rz_"+pid+"']"));
			//webElement.click();
			
			js.executeScript("document.querySelectorAll(\"*[id='fav_add_rz_"+pid+"']\")[0].click();");
			
			System.out.println("taoBaoId 标记时间标记成功 =="+taoBaoId);
			return true;
			
	}
	
	public static boolean tuijian(String id)throws Exception{
        System.out.println("当前推荐>>>>>>>>>"+map);
		try{
			try{
				//webGet("http://www.dataoke.com");
				Thread.sleep(Cmd.getSleepTime(2000, 3000));
			}catch(Exception e){
				
			}
			webGet("http://www.dataoke.com/item?id="+id);
			 
			
			Thread.sleep(Cmd.getSleepTime(5000, 8000));
			WebElement element =webDriver.findElement(By.xpath("//*[@class='add-tui J_add_tui']"));
		    element.click();
		    //Thread.sleep(Cmd.getSleepTime());
		    //webGet("http://www.dataoke.com/ucenter/favorites_quan.asp");
		    //Thread.sleep(Cmd.getSleepTime(1000, 2000));
		    if(map.containsKey(id)){
		    	map.put(id, map.get(id)+1);
		    }else{
		    	map.put(id, 1);
		    }
		    Thread.sleep(Cmd.getSleepTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	static HttpBrowser browser = null;
	
	public static boolean reg(String username,String password,String code)throws Exception{
		 String url = "http://www.dataoke.com/login?user=reg";
		 HttpRequest httpRequest =HttpRequest.post(url).timeout(10000);
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/login?user=reg");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 
		 httpRequest.form("username", username);
		 httpRequest.form("password", password);
		 httpRequest.form("code", code);
		 
		 HttpResponse response = browser.sendRequest(httpRequest);
		 
		 String rc = response.body();
		 System.out.println(rc);
		 
		 if(rc.contains("1")){
			System.out.println("注册成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			return true;
		 }else{
			 System.out.println("注册失败>>>>>>>>>>>>>>>>>>>>>>>>");
			 return false;
		 }
	}
	

	
	public static void setCookis(String uname,WebDriver webDriver)throws Exception{
		Map<String, String> cookisMap = new HashMap<String, String>();
		String cookisStr = "random=8696; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492777462; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF; ASPSESSIONIDQSBTQSCS=OHDGKNPACFIHDDFNANILEPKF; token=883f5a1f6733a23954015a6672a70099; ASPSESSIONIDSQBQSSDT=KNFMMBABCBPCEFDLDGGAGLJO; ASPSESSIONIDQSASQTDT=CDAFIBABKOLMLCOGGMEINGBM";
		for (String str : cookisStr.split("\\;")) {
			cookisMap.put(str.split("\\=")[0].trim(), str.split("\\=")[1]);
		}

		for (Cookie c : Test.getObjToFile(uname)) {
			// System.out.println(c.getName()+"===="+c.getValue());
			// buffer.append(c.getName()).append("=").append(c.getValue()).append("; ");
			cookisMap.put(c.getName().trim(), c.getValue());
		}

		StringBuffer buffer = new StringBuffer();

		for (Entry<String, String> en : cookisMap.entrySet()) {
			buffer.append(en.getKey()).append("=").append(en.getValue())
					.append("; ");
			
			org.openqa.selenium.Cookie cookie = new org.openqa.selenium.Cookie(en.getKey().trim(),en.getValue());
					
			webDriver.manage().addCookie(cookie);
					
		}

		//System.out.println("====="+buffer.toString());

	}

}
