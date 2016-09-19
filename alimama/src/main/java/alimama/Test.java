package alimama;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class Test {

	public static void main(String[] args) throws Exception {

		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File("E:\\app\\chromedriver\\chromedriver.exe"))
				.usingAnyFreePort().build();
		service.start();

		// 设置访问ChromeDriver的路径
		System.setProperty("webdriver.chrome.driver", "E:\\app\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
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
			System.out.println(Main.getRandom(1, 95));
		}
	}

}
