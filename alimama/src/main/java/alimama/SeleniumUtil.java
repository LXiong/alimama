package alimama;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * 浏览器util
 */

public class SeleniumUtil {

	private static final Logger logger = Logger.getLogger(SeleniumUtil.class);

	private static FirefoxProfile profile = null;
	private static DesiredCapabilities capability = null;
	
	
	public static void setDesiredCapabilities(){
		logger.info("start init Firefox profile!");
		String plugin = SeleniumUtil.class.getResource(
				"/plugin/killspinners-1.2.1-fx.xpi").getPath();
		try {
			profile = new FirefoxProfile();
			//profile = new ProfilesIni().getProfile("default");
			profile.addExtension(new File(plugin));
			// 去掉css
			//profile.setPreference("permissions.default.stylesheet", 2);
			// 去掉图片
			//profile.setPreference("permissions.default.image", 2);
			// 去掉flash
			profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so",
					false);
			capability = DesiredCapabilities.firefox();
			capability.setCapability("firefox_profile", profile);

		} catch (Exception e) {
			logger.error("init firefox plugin(killspinnners) is error! ", e);
		}
		logger.info("init Firefox profile is success!");
	}

	/**
	 * 初始化浏览器的profile文件
	 */
	static {
		setDesiredCapabilities();
	}

	/**
	 * 初始化浏览器
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
	 * 初始化浏览器
	 * 
	 * @param server
	 * @return
	 */
	public static WebDriver initWebDriver(String url) {
		logger.info("start init WebDriver!");
		WebDriver driver = null;
		try {
			 driver = new RemoteWebDriver(new
			 URL(url),
			 capability);
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
		// 去掉css
		//firefoxProfile.setPreference("permissions.default.stylesheet", 2);
		// 去掉图片
		//firefoxProfile.setPreference("permissions.default.image", 2);
		// 去掉flash
		firefoxProfile.setPreference(
				"dom.ipc.plugins.enabled.libflashplayer.so", false);

		capability.setCapability("firefox_profile", firefoxProfile);
		WebDriver driver = new FirefoxDriver(capability);
		
		//WebDriver driver = new HtmlUnitDriver();
		// WebDriver driver = new HtmlUnitDriver();
		// 如果3s内还定位不到则抛出异�?
		// driver.manage().timeouts().implicitlyWait(IMPLICITLYWAIT,
		// TimeUnit.SECONDS);
		// 页面加载超时时间设置�?5s
		// driver.manage().timeouts().pageLoadTimeout(PAGELOADTIMEOUT,
		// TimeUnit.SECONDS);
		 //driver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);
		return driver;

	}

	/**
	 * 截图
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
			File screenshot = ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);
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
	 * 截图
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
	 * 关闭浏览�?
	 * 
	 * @param driver
	 */
	public static void CloseWebDriver(WebDriver driver) {
		logger.info("start Destory WebDriver!");
		driver.quit();
		logger.info("Destory WebDriver is success!");
	}
}
