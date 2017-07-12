package dataoke;

import github.GitHubUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import util.CheckUtils;
import util.IpMocUtils;

public class ShuaOlineShouQuan {
	
	static  WebDriver webDriver = util.SeleniumUtil.initChromeDriver();
	
	@SuppressWarnings("deprecation")
	public static void printLog(String str){
         try {
			FileUtils.write(new File("c:\\ShuaOnlineLog.txt"), str+"\r\n", true);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	final static String mac = "1C-1B-0D-78-F0-93";
	@SuppressWarnings("deprecation")
	public static void main(String[] args)throws Exception {
		String url = null;
		int len = 10000000;
		if(args!=null && args.length >= 1){
			 url = args[0];
		}
		
		if(args!=null && args.length >= 2){
			len = Integer.valueOf(args[1]);
		    printLog("当前要刷====="+len);
		}
		
		if(args!=null && args.length >= 3){
			Integer sleep = Integer.parseInt(args[2].trim());
			printLog("睡眠 ："+sleep+"秒 在执行程序>>>>>>>>>>>>>>>");
			try {
				Thread.sleep(1000 * sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		  if(StringUtils.isBlank(url)){
			  url = FileUtils.readFileToString(new File("d:\\shuaOnline.txt"));
		  }
		  
		  if(!mac.equalsIgnoreCase(IpMocUtils.getMACAddress())){
			  printLog("mac校验失败>>>>>>>>>>>>>>>>>>>>>>>>>>清联系管理员");
				return ;
		  }else{
			  printLog("mac 校验成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  }
		  
		  
		  url = url.trim();
		  
		  printLog("url====="+url);
		  printLog("当前要刷====="+len);
		
		if(!CheckUtils.check("http://m635674608.iteye.com/blog/2384157","shuaonline")){
			printLog("校验失败>>>>>>>>>>>>>>>>>>>>>>>>>>清联系管理员");
			return ;
		}
		
		webGet("https://login.taobao.com");
		//System.in.read();
		Thread.sleep(30000);
		printLog(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		
		
		final String  u = url;
		new Thread(){
			public void run() {
				GitHubUtils.commitOlineShouQuan("mac=="+mac+" url="+u);
			};
		}.start();
		
		for(int i=0;i<len;i++){
			//https://detail.m.tmall.com/item.htm?id=26304648306
			webGet(url);
			printLog("当前已刷>>>>>>>>>>>>>>>>>"+i);
		}
		printLog(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
	}
	
	public static void webGet(String url){
		try {
			webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			webDriver.get(url);
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
	        js.executeScript("window.stop();");  
	        printLog("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
		  }
		}
	

}
