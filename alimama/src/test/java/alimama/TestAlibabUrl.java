package alimama;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestAlibabUrl {

	public static void main(String[] args) throws Exception {
		String urlStr = "http://s.click.taobao.com/PHRZpRx";

		webDriver = SeleniumUtil.initChromeDriver();

		login2();

		String c = getClickURL(urlStr);

		System.out.println(c);

	}

	/**
	 * 根据短链接还原商品地址
	 * 
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	public static String getShangpingUrl(String urlStr) throws Exception {

		webDriver.get(urlStr);

		Thread.sleep(1000);

		String url = webDriver.getCurrentUrl();

		System.out.println("别人淘宝客地址：" + url);

		url = url.split("\\&")[0];

		System.out.println("原商品地址：" + url);

		return url;

	}

	/**
	 * 根据商品链接，或者短链接，生成自己的推广链接
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getClickURL(String url) throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		if (url.contains("s.click.taobao.com")) {
			url = getShangpingUrl(url);
		}

		webDriver.get(
				"http://pub.alimama.com/myunion.htm?spm=a219t.7900221/1.1998910419.dbb742793.6paAcU#!/promo/self/links");

		Thread.sleep(3000);

		WebElement element = webDriver.findElement(By.id("J_originUrl"));
		element.click();
		element.sendKeys(url);

		Thread.sleep(1000);

		js.executeScript("document.querySelectorAll(\"button[class='btn btn-size25 btn-blue']\")[0].click();");

		Thread.sleep(1000);

		js.executeScript("document.querySelectorAll(\"*[class='btn btn-size28 mr10']\")[0].click();");

		// <textarea class="textarea" bx-name="clipboard"
		// data-spm-click="gostr=/tblm.88.1;locaid=d4314d048"
		// bx-path="components/clipboard/" id="brix_brick_2426"
		// bx-behavior="true">http://s.click.taobao.com/7PcDpRx</textarea>
		Thread.sleep(2000);

		String duanLink = (String) js
				.executeScript("return document.querySelectorAll(\"*[class='textarea']\")[2].value;");

		System.out.println("生成短链接：" + duanLink);

		return duanLink;

	}

	public static WebDriver webDriver = null;

	public static void init() {
		System.out.println("webDriver init start >>>>>>>>>>>>>>>>>");

		webDriver = SeleniumUtil.initChromeDriver();
		webDriver.manage().window().maximize();

		System.out.println("webDriver init end <<<<<<<<<<<<<<<<<<<<<<<<<<<<");

	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean login2() throws Exception {
		try {
			webDriver
					.get("https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&qq-pf-to=pcqq.c2c");
			Thread.sleep(5000);
			// webDriver.get("https://login.taobao.com/member/login.jhtml?style=mini&amp;newMini2=true&amp;from=alimama&amp;redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&amp;full_redirect=true&amp;disableQuickLogin=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.in.read();
		return true;
	}

}
