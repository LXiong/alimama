package taobao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataoke.Cmd;

public class DaTaoKe {
	static WebDriver webDriver = Main.webDriver;
	
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
	
	
	public static boolean shouquan(String uname, String pwd, String tuanme, String tpwd) throws Exception {
        try{ 
		webGet("http://www.dataoke.com/ucenter/mypid.asp");

		WebElement element = webDriver.findElement(By.id("update_shouquan"));
		element.click();

		Thread.sleep(5000);

		webDriver = webDriver.switchTo().frame("layui-layer-iframe1");

		Thread.sleep(2000);

		
		String str= webDriver.getPageSource();
		
		if(str.contains("检测到您已登录淘宝")){
		System.out.println("检测到您已登录淘宝>>>>>>>>>>>>>>>>");
			//sub 已檢測到登錄
			try{
				element = webDriver.findElement(By.id("sub"));
				if(element!=null && element.isDisplayed()){
					element.click();
				}
			}catch(Exception e){
			 e.printStackTrace();	
			}	
		}else{
		
		webDriver = webDriver.switchTo().frame("J_loginIframe");
		Thread.sleep(2000);
		
		element = webDriver.findElement(By.id("TPL_username_1"));
		element.sendKeys(tuanme);

		Thread.sleep(1000);

		element = webDriver.findElement(By.id("TPL_password_1"));
		element.sendKeys(tpwd);

		Thread.sleep(1000);

		element = webDriver.findElement(By.id("J_SubmitStatic"));
		element.click();
		
		}

		Thread.sleep(5000);

		return isShouquan();
        }catch(Exception e){
       	 e.printStackTrace();
        }
        return false;

	}
	
	public static boolean isShouquan() throws Exception {
		/*webGet("http://www.dataoke.com/ucenter/mypid.asp");
		Thread.sleep(2000);
		String str = webDriver.getPageSource();
		if (str.contains("你还没有授权")) {
			System.out.println("点击进行授权登录》》》》》》》》》》》》");
			return false;
		} else {
			System.out.println("授权成功>>>>>>>>>>>>>>>>>>>>");
		}*/
		/*webGet("http://www.dataoke.com/ucenter/mypid.asp");
		Thread.sleep(2000);
		String str = webDriver.getPageSource();
		if (!str.contains(taobaoName)) {
			System.out.println("点击进行授权登录》》》》》》》》》》》》");
			return false;
		} else {
			System.out.println("授权成功>>>>>>>>>>>>>>>>>>>>");
		}*/
		
		return true;
	}

}
