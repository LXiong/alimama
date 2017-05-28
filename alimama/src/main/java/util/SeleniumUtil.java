package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import dataoke.Cmd;

/**
 * 娴忚鍣╱til
 */

public class SeleniumUtil {

	private static final Logger logger = Logger.getLogger(SeleniumUtil.class);

	private static FirefoxProfile profile = null;
	private static DesiredCapabilities capability = null;

	public static void setDesiredCapabilities() {
		logger.info("start init Firefox profile!");
		String plugin = SeleniumUtil.class.getResource("/plugin/killspinners-1.2.1-fx.xpi").getPath();
		try {
			profile = new FirefoxProfile();
			// profile = new ProfilesIni().getProfile("default");
			profile.addExtension(new File(plugin));
			// 鍘绘帀css
			// profile.setPreference("permissions.default.stylesheet", 2);
			// 鍘绘帀鍥剧墖
			// profile.setPreference("permissions.default.image", 2);
			// 鍘绘帀flash
			profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
			capability = DesiredCapabilities.firefox();
			capability.setCapability("firefox_profile", profile);

		} catch (Exception e) {
			logger.error("init firefox plugin(killspinnners) is error! ", e);
		}
		logger.info("init Firefox profile is success!");
	}

	/**
	 * 鍒濆鍖栨祻瑙堝櫒鐨刾rofile鏂囦欢
	 */
	static {
		setDesiredCapabilities();
	}

	/**
	 * 如果你没找到 NPAPI 项，试试输入：chrome://flags/#enable-npapi 如果仍然没找到，那么，你需要升级你的
	 * Chrome 浏览器到最新版，我的原来是 40.0版本，升级到42.0版本，结果就出现了。 按“启用”后，关闭 Chrome
	 * 程序，然后再重新打开，就可以正常呼出阿里旺旺了。
	 * 
	 * @return
	 */
	public static WebDriver initChromeDriver() {
		logger.info("start init WebDriver!");
		WebDriver driver = null;
		try {
			/*ChromeDriverService service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File("D:\\workspace\\alimama\\alimama\\chromedriver\\chromedriver.exe")).usingAnyFreePort()
					.build();
			service.start();*/
			System.setProperty("webdriver.chrome.driver", "D:\\workspace\\alimama\\alimama\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			//driver = new HtmlUnitDriver(true);
		} catch (Exception e) {
			logger.error("Init WebDriver is error!", e);
			throw new RuntimeException(e);
		}
		logger.info("init WebDriver is success!");
		return driver;
	}
	
	
	public static WebDriver initChromeDriver(String ip,int prot) {
		logger.info("start init WebDriver!");
		WebDriver driver = null;
		String proxyIpAndProt = ip+":"+prot;
		try {
			/*ChromeDriverService service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File("e:\\app\\chromedriver\\chromedriver.exe")).usingAnyFreePort()
					.build();
			service.start();*/
			//System.setProperty("webdriver.chrome.driver", "e:\\app\\chromedriver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", "D:\\workspace\\alimama\\alimama\\chromedriver\\chromedriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			Proxy proxy = new Proxy();
			proxy.setHttpProxy(proxyIpAndProt).setFtpProxy(proxyIpAndProt).setSocksProxy(proxyIpAndProt).setSslProxy(proxyIpAndProt);
			capabilities.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			
			driver = new ChromeDriver(capabilities);
			//driver = new HtmlUnitDriver(true);
		} catch (Exception e) {
			logger.error("Init WebDriver is error!", e);
			throw new RuntimeException(e);
		}
		logger.info("init WebDriver is success!");
		return driver;
	}

	/**
	 * 鍒濆鍖栨祻瑙堝櫒
	 * 
	 * @param server
	 * @return
	 */
	public static WebDriver initWebDriver() {
		logger.info("start init WebDriver!");
		WebDriver driver = null;
		try {
			// driver = new RemoteWebDriver(new
			// URL(SystemConfigProper.SELENIUM_SERVER_HTTP+"/wd/hub"),
			// capability);
			driver = new FirefoxDriver(capability);
		} catch (Exception e) {
			logger.error("Init WebDriver is error!", e);
			throw new RuntimeException(e);
		}
		logger.info("init WebDriver is success!");
		return driver;
	}

	/**
	 * 鍒濆鍖栨祻瑙堝櫒
	 * 
	 * @param server
	 * @return
	 */
	public static WebDriver initWebDriver(String url) {
		logger.info("start init WebDriver!");
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(url), capability);
		} catch (Exception e) {
			logger.error("Init WebDriver is error!", e);
			throw new RuntimeException(e);
		}
		logger.info("init WebDriver is success!");
		return driver;
	}

	public static WebDriver createWebDriver() throws Exception {
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setJavascriptEnabled(true);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		// 鍘绘帀css
		// firefoxProfile.setPreference("permissions.default.stylesheet", 2);
		// 鍘绘帀鍥剧墖
		// firefoxProfile.setPreference("permissions.default.image", 2);
		// 鍘绘帀flash
		firefoxProfile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);

		capability.setCapability("firefox_profile", firefoxProfile);
		WebDriver driver = new FirefoxDriver(capability);

		// WebDriver driver = new HtmlUnitDriver();
		// WebDriver driver = new HtmlUnitDriver();
		// 濡傛灉3s鍐呰繕瀹氫綅涓嶅埌鍒欐姏鍑哄紓甯?
		// driver.manage().timeouts().implicitlyWait(IMPLICITLYWAIT,
		// TimeUnit.SECONDS);
		// 椤甸潰鍔犺浇瓒呮椂鏃堕棿璁剧疆涓?5s
		// driver.manage().timeouts().pageLoadTimeout(PAGELOADTIMEOUT,
		// TimeUnit.SECONDS);
		// driver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);
		return driver;

	}

	/**
	 * 鎴浘
	 * 
	 * @param driver
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static String capture(WebDriver driver, String url, String filePath) {
		if (driver == null) {
			return null;
		}
		if (!url.startsWith("http:") && !url.startsWith("https:")) {
			return null;
		}
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		try {
			driver.get(url);
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			File file = new File(filePath);
			FileUtils.copyFile(screenshot, file);
			logger.info("capture success!");
		} catch (IOException e) {
			logger.error("browser capture is error!", e);
			filePath = null;
			throw new RuntimeException(e);
		}
		return filePath;
	}
	
	/**
	 * 鎴浘
	 * 
	 * @param driver
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static String captureDataoke(WebDriver driver, String url, String filePath) {
		if (driver == null) {
			return null;
		}
		if (!url.startsWith("http:") && !url.startsWith("https:")) {
			return null;
		}
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		try {
			driver.get(url);
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			File file = new File(filePath);
			FileUtils.copyFile(screenshot, file);
			logger.info("capture success!");
		} catch (IOException e) {
			logger.error("browser capture is error!", e);
			filePath = null;
			throw new RuntimeException(e);
		}
		return filePath;
	}
	
	public static void main(String[] args)throws Exception {
		WebDriver driver = initChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.dataoke.com/login/?user=reg");
		//capture(driver,"http://www.dataoke.com/login/?user=reg","d:\\dataoke.jpg");
		
		
          JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		System.out.println("开始输入手机号码>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
		WebElement webElement = null;
		//WebElement webElement = webDriver.findElement(By.id("phone"));
		//webElement.click();
	    //webElement.sendKeys(num);
		//js.executeScript("document.querySelectorAll(\"input[id='phone']\")[0].click()");
		//Thread.sleep(200);
		String num = "131"+Cmd.getSleepTime(10000000, 90000000);
		js.executeScript("document.querySelectorAll(\"input[id='phone']\")[0].value='"+num+"'");
		
		
		
		System.out.println("点击获取短信按钮》》》》》》》》》》》》》》》》》》》");
	    //webElement = webDriver.findElement(By.xpath("//button[@class='get-phone-verify get-phone-verify-fn']"));
		//webElement.click();
	    js.executeScript("document.querySelectorAll(\"button[class='get-phone-verify get-phone-verify-fn']\")[0].click();");
		
		Thread.sleep(2000);
		
		
		   // 选取frame  
		driver.switchTo().frame("captcha_widget");;  
		       
				System.out.println("点击人机识别验证》》》》》》》》》》》》》》》》》》》");
				webElement = driver.findElement(By.xpath("//span[@class='captcha-widget-text']"));
				webElement.click();
		
				Thread.sleep(3000);
		
				
		//String str =driver.getPageSource();		
				
		//System.out.println(str);
		
				// 跳出iframe  
		driver.switchTo().defaultContent();  
		
		//
		WebElement keyWord = driver.findElement(By.id("captcha_frame"));
		
		BufferedImage inputbig = createElementImage(driver,keyWord);  
		
		ImageIO.write(inputbig, "jpg", new File("d://dataoke.jpg"));   
		
		
		
		
	}
	
	 public static BufferedImage createElementImage(WebDriver driver,WebElement webElement)  
             throws IOException {  
             // 获得webElement的位置和大小。  
             Point location = webElement.getLocation();  
             Dimension size = webElement.getSize();  
             // 创建全屏截图。  
             BufferedImage originalImage =  
             ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));  
             // 截取webElement所在位置的子图。  
             BufferedImage croppedImage = originalImage.getSubimage(  
             location.getX(),  
             location.getY(),  
             size.getWidth(),  
             size.getHeight());  
             return croppedImage;  
             }  
	 
	 public static byte[] takeScreenshot(WebDriver driver) throws IOException {  
	        WebDriver augmentedDriver = new Augmenter().augment(driver);  
	      return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);  
	        }

	/**
	 * 鎴浘
	 * 
	 * @param driver
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static String capture(WebDriver driver, String url) {
		if (driver == null) {
			return null;
		}
		if (!url.startsWith("http:") && !url.startsWith("https:")) {
			return null;
		}
		String response;
		try {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.get(url);
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			response = augmentedDriver.getPageSource();
			logger.info("content success!");
		} catch (Exception e) {
			logger.error("browser content is error!", e);
			throw new RuntimeException(e);
		}
		return response;
	}

	public static boolean contentIsIn(WebDriver driver, String url, String text) {
		if (driver == null) {
			return false;
		}
		if (!url.startsWith("http:") && !url.startsWith("https:")) {
			return false;
		}
		text = text.trim();
		if (StringUtils.isBlank(text)) {
			return false;
		}
		try {
			driver.get(url);
			String test = driver.getPageSource();
			String[] txts = text.split(" ++");
			for (String txt : txts) {
				if (test.indexOf(txt) < 0) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("browser content is error!", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 鍏抽棴娴忚鍣?
	 * 
	 * @param driver
	 */
	public static void CloseWebDriver(WebDriver driver) {
		logger.info("start Destory WebDriver!");
		driver.quit();
		logger.info("Destory WebDriver is success!");
	}
}
