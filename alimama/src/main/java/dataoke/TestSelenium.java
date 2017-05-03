package dataoke;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import jodd.http.Cookie;
import util.SeleniumUtil;

public class TestSelenium {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		//Test.login("13411679603", "1qaz2wsx");
		test();
		
	    
	}
	
	static WebDriver webDriver = null;
	
	public static void test()throws Exception{
		if(webDriver==null){
			SeleniumUtil.initChromeDriver();
		}

		webDriver.get("http://www.dataoke.com/item?id=2318180");
		setCookis("15989716682", webDriver);
		webDriver.get("http://www.dataoke.com/item?id=2318180");
		
		WebElement element =webDriver.findElement(By.xpath("//*[@class='add-tui J_add_tui']"));
	    element.click();
	    
	    Thread.sleep(5000);
	    
		setCookis("13713419479", webDriver);
		webDriver.get("http://www.dataoke.com/item?id=2318180");
		
		 element =webDriver.findElement(By.xpath("//*[@class='add-tui J_add_tui']"));
	    element.click();
	    
	    
	    
	    //webDriver.close();
	}
	
	public static boolean execute(String id,String uname)throws Exception{
		if(webDriver==null){
			SeleniumUtil.initChromeDriver();
		}

		webDriver.get("http://www.dataoke.com/item?id="+id);
		setCookis(uname, webDriver);
		webDriver.get("http://www.dataoke.com/item?id="+id);
		
		WebElement element =webDriver.findElement(By.xpath("//*[@class='add-tui J_add_tui']"));
	    element.click();
	    Thread.sleep(2000);
	    return true;
	}
	
	
	public static void setCookis(String uname,WebDriver webDriver)throws Exception{
		Map<String, String> cookisMap = new HashMap<String, String>();
		String cookisStr = "random=8696; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492777462; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF; ASPSESSIONIDQSBTQSCS=OHDGKNPACFIHDDFNANILEPKF; token=883f5a1f6733a23954015a6672a70099; ASPSESSIONIDSQBQSSDT=KNFMMBABCBPCEFDLDGGAGLJO; ASPSESSIONIDQSASQTDT=CDAFIBABKOLMLCOGGMEINGBM";
		for (String str : cookisStr.split("\\;")) {
			cookisMap.put(str.split("\\=")[0].trim(), str.split("\\=")[1]);
		}

		for (Cookie c : Test.getObjToFile(uname)) {
			// System.out.println(c.getName()+"===="+c.getValue());
			// buffer.append(c.getName()).append("=").append(c.getValue()).append("; ");
			cookisMap.put(c.getName().trim(), c.getValue());
		}

		StringBuffer buffer = new StringBuffer();

		for (Entry<String, String> en : cookisMap.entrySet()) {
			buffer.append(en.getKey()).append("=").append(en.getValue())
					.append("; ");
			
			org.openqa.selenium.Cookie cookie = new org.openqa.selenium.Cookie(en.getKey().trim(),en.getValue());
					
			webDriver.manage().addCookie(cookie);
					
		}

		//System.out.println("====="+buffer.toString());

	}

}
