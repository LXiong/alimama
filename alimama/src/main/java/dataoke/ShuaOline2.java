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
			webGet("https://detail.m.tmall.com/item.htm?id=39432153632");
			//webGet("https://detail.tmall.com/item.htm?spm=a220o.1000855.1998025129.3.4732a2bdJAnTs3&abtest=_AB-LR32-PR32&pvid=e2ce3330-0241-428d-ae6c-ed39ad092ac9&pos=3&abbucket=_AB-M32_B3&acm=03054.1003.1.1539344&id=39432153632&scm=1007.12144.81309.23864_0&skuId=3114861259084");
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
