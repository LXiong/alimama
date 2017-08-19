package dataoke;

import github.GitHubUtils;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ShuaOline {
	
	static  WebDriver webDriver = null;
	
	public static void main(String[] args)throws Exception {
		String url = null;
		url = "https://detail.tmall.com/item.htm?id=547204429776";
		int len = 10000000;
		Integer sleep = 1;
		if(args!=null && args.length >= 1){
			 url = args[0];
		}
		
		if(args!=null && args.length >= 2){
			len = Integer.valueOf(args[1]);
		    System.out.println("当前要刷====="+len);
		}
		
		if(args!=null && args.length >= 3){
			sleep = Integer.parseInt(args[2].trim());
			System.out.println("睡眠 ："+sleep+"秒 在执行程序>>>>>>>>>>>>>>>");
			try {
				Thread.sleep(1000 * sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String path = null;
		if(args!=null && args.length >= 4){
			path= args[3].trim();
			System.out.println("浏览器date ："+path);
		}
		//webDriver = util.SeleniumUtil.initChromeDriver2222(path);
		if(StringUtils.isBlank(path)){
			webDriver = util.SeleniumUtil.initFirefoxDriver();
		}else{
			webDriver = util.SeleniumUtil.initChromeDriver2222(null);
		}
		
		webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
		
		  if(StringUtils.isBlank(url)){
			  url = FileUtils.readFileToString(new File("d:\\shuaOnline.txt"));
		  }
		  
		  url = url.trim();
		  
		  System.out.println("url====="+url);
		  System.out.println("当前要刷====="+len);
		
		if(!Test.check()){
			System.out.println("校验失败>>>>>>>>>>>>>>>>>>>>>>>>>>清联系管理员");
			return ;
		}
		
		webGet("https://login.taobao.com");
		System.in.read();
		//Thread.sleep(60000);
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		
		
		final String  u = url;
		new Thread(){
			public void run() {
				GitHubUtils.commitOline(u);
			};
		}.start();
		
		for(int i=0;i<len;i++){
			//https://detail.m.tmall.com/item.htm?id=26304648306
			try{
				webGet(url);
				Thread.sleep(sleep);
			}catch(Exception e){
				e.printStackTrace();
				 Thread.sleep(1000);
			}
			System.out.println("当前已刷>>>>>>>>>>>>>>>>>"+i);
		}
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
	}
	
	public static void webGet(String url){
		try {
			webDriver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
	        js.executeScript("window.stop();");  
	        System.out.println("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
		  }
		}
	

}
