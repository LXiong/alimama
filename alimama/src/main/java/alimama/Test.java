package alimama;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class Test {
	
	
	//chrome://flags/#enable-npapi
		public static void main3(String[] args) throws Exception {

			FirefoxProfile fp = new FirefoxProfile();
			ProfilesIni allProfiles = new ProfilesIni();
			fp = allProfiles.getProfile("default");
			WebDriver driver = new FirefoxDriver(fp);
			driver.get("http://www.baidu.com/");

			
		}

	//chrome://flags/#enable-npapi
	public static void main(String[] args) throws Exception {

		/*ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File("f:\\app\\chromedriver\\chromedriver.exe"))
				.usingAnyFreePort().build();
		service.start();*/
		
		ChromeOptions options = new ChromeOptions();
		//options.addArguments(“–user-data-dir=C:/Users/xxx/AppData/Local/Google/Chrome/User Data/Default”);
		String userDateDir = "C:/Users/ThinkPad/AppData/Local/Google/Chrome/User Data";
		options.addArguments("--user-data-dir="+userDateDir);
		// 璁剧疆璁块棶ChromeDriver鐨勮矾寰�
		System.setProperty("webdriver.chrome.driver", "f:\\app\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.baidu.com/");

		
	}

	public static void main2(String[] args) throws Exception {

		WebDriver driver = null;
		try {
			driver = new ChromeDriver();
			driver.get("https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&qq-pf-to=pcqq.c2c");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		System.in.read();
		for (int i = 0; i < 55; i++) {
			System.out.println(MainBak.getRandom(1, 95));
		}
	}

}
