package dataoke;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.SeleniumUtil;

public class ShouQuan {

	static String tname = "xiaomi1991222";
	static String tpwd = "xiaomin0322";

	public static void main(String[] args) throws Exception {
		File shouquanDir = new File("D:\\dataoke\\shouquan");
		// execute("13283422058", "gk3qhvz",tname, tpwd);
		executeAll(shouquanDir.listFiles());
	}

	public static void executeAll(File... files) throws Exception {
		for (File f : files) {
			executeDeleteHttpClient(f);
		}
	}

	static int count = 0;
	static int tuiguangOk = 0;
	static HttpHost proxy = null;

	public static void executeDeleteHttpClient(File file) throws Exception {
		List<String> lists = FileUtils.readLines(file);
		for (String s : lists) {
			if (StringUtils.isBlank(s)) {
				continue;
			}
			count++;

			try {

				String uname = s.split("\\----")[0].trim();
				String pwd = s.split("\\----")[1].trim();
				System.out.println("u = " + uname + "p = " + pwd + " 开始登陆  当前已刷>>>>>>>>>>>>>>>" + count + "当前 文件名称："
						+ file.getName());

				boolean flag = login(uname, pwd);
				System.out.println("u = " + uname + "登陆>>>>>>>>>>>>>" + flag);
				Thread.sleep(1000);
				if (!isShouquan()) {
					flag = shouquan(uname, pwd, tname, tpwd);
					System.out.println("授权结果>>>>>>>>>>>" + flag);
					if (flag) {
						tuiguangOk += 1;
						System.out.println("授權成功》》》》》》》》》》》》》》》》》》》 uname=" + uname + " 当前删除成功：" + tuiguangOk
								+ " 当前ip==" + (proxy == null ? "无" : proxy.getHostName()));
					} else {
						System.out.println("授權失败》》》》》》》》》》》》》》》》》》uname=" + uname);
					}
				}else{
					tuiguangOk += 1;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				proxy = null;
				try{
					webGet("http://www.dataoke.com/logout");
					Thread.sleep(Cmd.getSleepTime(2000, 3000));
				}catch(Exception e){
					
				}
			}
		}
	}
	
	public static void webGet(String url){
		try {
			webGet(url);
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
	        js.executeScript("window.stop();");  
	        System.out.println("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
		  }
		}

	static WebDriver webDriver = SeleniumUtil.initChromeDriver();

	public static void execute(String uname, String pwd, String tuanme, String tpwd) throws Exception {
		boolean flag = login(uname, pwd);
		if (flag) {
			if (!isShouquan()) {
				flag = shouquan(uname, pwd, tuanme, tpwd);
				System.out.println("授权结果>>>>>>>>>>>" + flag);
			}
		}

	}

	public static boolean shouquan(String uname, String pwd, String tuanme, String tpwd) throws Exception {

		webGet("http://www.dataoke.com/ucenter/mypid.asp");

		WebElement element = webDriver.findElement(By.id("update_shouquan"));
		element.click();

		Thread.sleep(5000);

		webDriver = webDriver.switchTo().frame("layui-layer-iframe1");

		Thread.sleep(2000);

		webDriver = webDriver.switchTo().frame("J_loginIframe");

		element = webDriver.findElement(By.id("TPL_username_1"));
		element.sendKeys(tuanme);

		Thread.sleep(1000);

		element = webDriver.findElement(By.id("TPL_password_1"));
		element.sendKeys(tpwd);

		Thread.sleep(1000);

		element = webDriver.findElement(By.id("J_SubmitStatic"));
		element.click();

		Thread.sleep(5000);

		return isShouquan();

	}

	public static boolean isShouquan() throws Exception {
		webGet("http://www.dataoke.com/ucenter/mypid.asp");
		Thread.sleep(2000);
		String str = webDriver.getPageSource();
		if (str.contains("你还没有授权")) {
			System.out.println("点击进行授权登录》》》》》》》》》》》》");
			return false;
		} else {
			System.out.println("授权成功>>>>>>>>>>>>>>>>>>>>");
		}
		return true;
	}

	public static boolean login(String uname, String pwd) throws Exception {
		try {
			webGet("http://www.dataoke.com/login");
			Thread.sleep(Cmd.getSleepTime(1000, 2000));
			WebElement element = webDriver.findElement(By.xpath("//input[@data-id='email']"));
			element.sendKeys(uname);

			element = webDriver.findElement(By.xpath("//input[@data-id='pwd']"));
			element.sendKeys(pwd);

			element = webDriver.findElement(By.xpath("//a[@class='submit-btn login-btn']"));
			element.click();

			Thread.sleep(Cmd.getSleepTime(3000, 5000));

			String str = webDriver.getPageSource();

			if (str.contains("")) {

			}
			// Thread.sleep(Cmd.getSleepTime());
			// webGet("http://www.dataoke.com/ucenter/favorites_quan.asp");
			// Thread.sleep(Cmd.getSleepTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
