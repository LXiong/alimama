package dataoke;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ShuaOline2 {
	
	static  WebDriver webDriver = util.SeleniumUtil.initChromeDriver();
	
	public static void main(String[] args)throws Exception {
		webGet("https://login.m.taobao.com/login.htm");
		System.in.read();
		
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		for(int i=0;i<10000000;i++){
			webGet("https://detail.m.tmall.com/item.htm?spm=0.0.0.0.5FpUFm&id=543557417955&abtest=21&rn=bbe5ec2e6fea4193aa04dbb1b8754dfd&sid=eb80b81938bb970e9c61caa81b4baf51");
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
