package dataoke;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ShuaOline {
	
	static  WebDriver webDriver = util.SeleniumUtil.initChromeDriver();
	
	public static void main(String[] args)throws Exception {
		
		webGet("https://login.m.taobao.com/login.htm");
		System.in.read();
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		for(int i=0;i<10000000;i++){
			//https://detail.m.tmall.com/item.htm?id=26304648306
			
			webGet("https://detail.m.tmall.com/item.htm?id=39432153632");
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
