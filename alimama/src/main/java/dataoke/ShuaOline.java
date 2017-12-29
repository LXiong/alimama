package dataoke;

import java.io.File;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

import github.GitHubUtils;

public class ShuaOline {

	static WebDriver webDriver = null;

	public static boolean switchToWindow(String windowTitle, WebDriver dr) {
		boolean flag = false;
		try {
			// 将页面上所有的windowshandle放在入set集合当中
			String currentHandle = dr.getWindowHandle();
			Set<String> handles = dr.getWindowHandles();
			for (String s : handles) {
				if (s.equals(currentHandle))
					continue;
				else {
					dr.switchTo().window(s);
					// 和当前的窗口进行比较如果相同就切换到windowhandle
					// 判断title是否和handles当前的窗口相同
					if (dr.getTitle().contains(windowTitle)) {
						flag = true;
						System.out.println("Switch to window: " + windowTitle + " successfully!");
						break;
					} else
						continue;
				}
			}
		} catch (Exception e) {
			System.out.printf("Window: " + windowTitle + " cound not found!", e.fillInStackTrace());
			flag = false;
		}
		return flag;
	}

	public static void getWindowMethod2(String url) {
		JavascriptExecutor oJavaScriptExecutor = (JavascriptExecutor) webDriver;
		// oJavaScriptExecutor.executeScript("window.open('"+url+"',
		// '_blank');");
		System.out.println("there are " + webDriver.getWindowHandles().size() + " windows");
	}

	static String url = null;

	static int len = 10000000;
	static Integer sleep = 3000;

	static AtomicInteger atomicInteger = new AtomicInteger();

	public static void main(String[] args) throws Exception {
		url = "https://detail.m.tmall.com/item.htm?id=541180914384";
		if (args != null && args.length >= 1) {
			url = args[0];
		}

		if (args != null && args.length >= 2) {
			len = Integer.valueOf(args[1]);
			System.out.println("当前要刷=====" + len);
		}

		if (args != null && args.length >= 3) {
			sleep = Integer.parseInt(args[2].trim());
			// System.out.println("睡眠 ："+sleep+"秒 在执行程序>>>>>>>>>>>>>>>");
		}
		String path = null;
		if (args != null && args.length >= 4) {
			path = args[3].trim();
			System.out.println("浏览器date ：" + path);
		}
		 webDriver = util.SeleniumUtil.initChromeDriver2222(null);
		/*if (!StringUtils.isBlank(path)) {
			webDriver = util.SeleniumUtil.initFirefoxDriver();
		} else {
			webDriver = util.SeleniumUtil.initChromeDriver2222(null);
		}*/

		webDriver.manage().timeouts().pageLoadTimeout(sleep, TimeUnit.MILLISECONDS);

		if (StringUtils.isBlank(url)) {
			url = FileUtils.readFileToString(new File("d:\\shuaOnline.txt"));
		}

		url = url.trim();

		System.out.println("url=====" + url);
		System.out.println("当前要刷=====" + len);

		if (!Test.check()) {
			System.out.println("校验失败>>>>>>>>>>>>>>>>>>>>>>>>>>清联系管理员");
			return;
		}

		webGet(webDriver, "https://login.taobao.com");

		System.in.read();

		// Thread.sleep(60000);
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));

		final String u = url;
		new Thread() {
			public void run() {
				GitHubUtils.commitOline(u);
			};
		}.start();

		execte(webDriver);
		/*
		 * Set<String> handles = webDriver.getWindowHandles(); for(;;){
		 * System.out.println("handles:"+handles); for (final String s :
		 * handles) { try{ new Thread(){ public void run() { try{ long start =
		 * System.currentTimeMillis(); System.out.println("sname==="+s); final
		 * WebDriver webDriver2 = webDriver.switchTo().window(s);
		 * System.out.println("time======"+(System.currentTimeMillis()-start));
		 * JavascriptExecutor oJavaScriptExecutor =
		 * (JavascriptExecutor)webDriver2;
		 * oJavaScriptExecutor.executeScript("window.location.href='"+url+"';");
		 * System.out.println(s+"  当前已刷>>>>>>>>>>>>>>>>>"+atomicInteger.
		 * incrementAndGet()); Thread.sleep(3000); // webGet(webDriver2, url);
		 * webDriver2.navigate().refresh(); }catch(Exception e){
		 * e.printStackTrace(); try { Thread.sleep(1000); } catch
		 * (InterruptedException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } }
		 * 
		 * }; }.start(); }catch(Exception e){ e.printStackTrace(); } } handles =
		 * webDriver.getWindowHandles(); }
		 */
	}

	// }

	public static void execte(WebDriver webDriver) {
		for (int i = 0; i < len; i++) {
			// https://detail.m.tmall.com/item.htm?id=26304648306
			try {
				webGet(webDriver, url);
				// Thread.sleep(sleep);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			System.out.println("当前已刷>>>>>>>>>>>>>>>>>" + i);
		}
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
	}

	public static void webGet2(WebDriver webDriver, String url) {
		try {
			webDriver.get(url);
			webGet2(webDriver, url);
		} catch (Exception e) {
			// e.printStackTrace();
			if (e instanceof NoSuchSessionException) {
				System.out.println("浏览器关闭，程序退出");
				System.exit(0);
			}
			/*
			 * try { Thread.sleep(100); } catch (InterruptedException e1) {
			 * e1.printStackTrace(); } JavascriptExecutor js =
			 * (JavascriptExecutor) webDriver;
			 * js.executeScript("window.stop();");
			 * System.out.println("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
			 */
		}
	}

	public static void webGet(WebDriver webDriver, String url) {
		/*try{
			String title = webDriver.getTitle();
		}catch(Exception e){
			System.out.println("浏览器关闭，程序退出");
			System.exit(0);
		}*/
		
		
		try {
			webDriver.get(url);
		} catch (Exception e) {
			// e.printStackTrace();
			if (e instanceof NoSuchSessionException) {
				System.out.println("浏览器关闭，程序退出");
				System.exit(0);
			}
			String msg = e.getMessage();
			if(msg.contains("chrome not reachable")){
				System.out.println("浏览器关闭，程序退出");
				System.exit(0);
			}
			/*
			 * try { Thread.sleep(100); } catch (InterruptedException e1) {
			 * e1.printStackTrace(); } JavascriptExecutor js =
			 * (JavascriptExecutor) webDriver;
			 * js.executeScript("window.stop();");
			 * System.out.println("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
			 */
		}
	}

}
