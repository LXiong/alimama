import java.io.IOException;

import org.openqa.selenium.WebDriver;

import util.LOG;
import util.SeleniumUtil;

public class Test2 {
	 public static WebDriver webDriver = SeleniumUtil.initChromeDriver();
	public static void main(String[] args) {
		try{
			System.out.println("11");
		}catch(Exception e){
			
		}finally {
			try {
				 LOG.printLog("按回车键后重启打开浏览器开始注册>>>>>>>>>>>>>>>>>>>>>>>");
				 System.in.read();
				 LOG.printLog("关闭浏览器");
				 webDriver.close();
				 webDriver = SeleniumUtil.initChromeDriver();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
