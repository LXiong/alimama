package dataoke;

import github.GitHubUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ShuaOline {
	
	static  WebDriver webDriver = util.SeleniumUtil.initChromeDriver();
	
	public static void main(String[] args)throws Exception {
		final String url = args[0];
		System.out.println("url====="+url);
		int len = 10000000;
		if(args.length >= 2){
			len = Integer.valueOf(args[1]);
		    System.out.println("当前要刷====="+len);
		}
		
		if(args.length >= 3){
			Integer sleep = Integer.parseInt(args[2].trim());
			System.out.println("睡眠 ："+sleep+"秒 在执行程序>>>>>>>>>>>>>>>");
			try {
				Thread.sleep(1000 * sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(!Test.check()){
			System.out.println("校验失败>>>>>>>>>>>>>>>>>>>>>>>>>>清联系管理员");
			return ;
		}
		
		webGet("https://login.taobao.com");
		System.in.read();
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		
		new Thread(){
			public void run() {
				GitHubUtils.commitOline(url);
			};
		}.start();
		
		for(int i=0;i<len;i++){
			//https://detail.m.tmall.com/item.htm?id=26304648306
			webGet(url);
			System.out.println("当前已刷>>>>>>>>>>>>>>>>>"+i);
		}
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
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
	

}
