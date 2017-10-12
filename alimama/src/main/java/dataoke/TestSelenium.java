package dataoke;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.LOG;
import util.SeleniumUtil;


/**
 * ck登录大淘客，授权二合一
 * @author zzm
 *
 */
public class TestSelenium {

	public static void main(String[] args)throws Exception {
		/*for(Entry<String, String> m :CKUtils.getAll().entrySet()){
			String key = m.getKey();
			shouquanAndTuiGuang(new String[]{"4019565"},key);
		}*/
		shouquanAndTuiGuang(new String[]{"4007316"}, new File("D:\\dataoke\\cks\\cookies72.txt"));
		//shouquan(FileUtils.readLines(new File("D:\\dataoke\\邮箱账号\\20171011\\all.txt")));
		//post(new String[]{"4045325"});
		
	}
	
	static WebDriver webDriver = null;
	
	public static void post(String[] ids)throws Exception{
		for(Entry<String, String> m :CKUtils.getAll().entrySet()){
			try{
				LOG.printLog("开始刷name="+m.getKey()+" pids=="+Arrays.toString(ids));
				for(String id:ids){
					try{
						String name = m.getKey();
					 boolean flag = 	Test.tuijian(id,name );
					 System.out.println("name=="+name+" 结果："+flag+" pid="+id);
					 Cmd.getSleepTime();
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				Thread.sleep(Cmd.getSleepTime());
			}
		}
	}
	
	public static void shouquanAndTuiGuang(String[] ids,File... files)throws Exception{
		for(Entry<String, String> m :CKUtils.getAll(files).entrySet()){
			try{
				LOG.printLog("开始刷name="+m.getKey()+" pids=="+Arrays.toString(ids));
				shouquanAndTuiGuang(ids, m.getKey());
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				Thread.sleep(Cmd.getSleepTime());
			}
		}
	}
	static int count=0;
	public static void shouquan(List<String> lists)throws Exception{
		if(webDriver==null){
			webDriver = SeleniumUtil.initChromeDriver();
		}
		
		String uname = null;
		String pwd = null;
		
		String tuname = null;
		String tpwd = null;
		
		for(String s:lists){
			 uname = s.split("\\----")[0].trim();
			 pwd = s.split("\\----")[1].trim();
			 tuname = s.split("\\----")[2].trim();
			 tpwd = s.split("\\----")[3].trim();
			  LOG.printLog("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count);
			  try{
				    webDriver.get("http://www.dataoke.com/ucenter/mypid.asp");
					setCookis(uname, webDriver);
					webDriver.get("http://www.dataoke.com/ucenter/mypid.asp");
				    ShouQuan.webDriver = webDriver;
					if(webDriver.getPageSource().contains(tuname)){
						LOG.printLog("uname==="+uname+"已经授权"+" tname==="+tuname);
						count++;
						continue;
					}
				    
				    boolean flag = ShouQuan.shouquan2(uname, pwd, tuname, tpwd);
				    LOG.printLog("授权结果>>>>>>>>>>>>>"+flag+" uname==="+uname+" tname=="+tuname);
				    if(flag){
				    	count++;
				    }else{
				    	flag = ShouQuan.shouquan2(uname, pwd, tuname, tpwd);
				    	LOG.printLog("授权结果>>>>>>>>>>>>>"+flag+" uname==="+uname+" tname=="+tuname);
				    	if(flag){
				    		count++;
				    	}else{
				    		FileUtils.write(new File("d:\\testSeleniumError.txt"), s+"\r\n");
				    	}
				    }
			  }catch(Exception exceptio){
				  exceptio.printStackTrace();
				  LOG.printLog("失败>>>>>>>>>>>>>"+uname);
			  }finally{
				  webDriver.close();
				  webDriver = SeleniumUtil.initChromeDriver();
				  Thread.sleep(2000);
			  }
        	
		}
		
	}  	
	    
	
	
	public static void shouquanAndTuiGuang(String[] ids,String uname)throws Exception{
		if(webDriver==null){
			webDriver = SeleniumUtil.initChromeDriver();
		}
		webDriver.get("http://www.dataoke.com/ucenter/mypid.asp");
		setCookis(uname, webDriver);
		webDriver.get("http://www.dataoke.com/ucenter/mypid.asp");
		
	    ShouQuan.webDriver = webDriver;
	    boolean flag = ShouQuan.shouquan2(null, null, "粟范德萨", "wen195861111");
	    LOG.printLog("授权结果>>>>>>>>>>>>>"+flag+" uname==="+uname);
	    if(flag){
	    	for(String id:ids){
	    		//flag =  ShouQuan.zhuan2and1(id);
	    		getWebDriverCKSet(webDriver, uname);
	    		flag =  ShouQuan.tuijian(id);
	    		getWebDriverCKSet(webDriver, uname);
	    		flag = Test.taoTokenHttpClient(id, uname, "1");
		  	    LOG.printLog("推荐结果>>>>>>>>>>>>>"+flag+" pid==="+id+" uname==="+uname);
		  	    Thread.sleep(1000);
	    	}
	    	
	    }
	    
	    //webDriver.close();
	}
	
	public static void getWebDriverCKSet(WebDriver webDriver,String name){
		Set<Cookie> cookies =webDriver.manage().getCookies();
		String ckStr = "";
		for(Cookie c:cookies){
			if("".equals(ckStr)){
				ckStr=c.getName()+"="+c.getValue();
			}else{
				ckStr=ckStr+"; "+c.getName()+"="+c.getValue();
			}
		}
		CKUtils.map.put(name, ckStr);
		System.out.println("登录后的ck==="+ckStr);
	}
	
	
	
	public static void test()throws Exception{
		if(webDriver==null){
			webDriver = SeleniumUtil.initChromeDriver();
		}

		webDriver.get("http://www.dataoke.com/item?id=2318180");
		setCookis("15989716682", webDriver);
		webDriver.get("http://www.dataoke.com/item?id=2318180");
		
		/*WebElement element =webDriver.findElement(By.xpath("//*[@class='add-tui J_add_tui']"));
	    element.click();
	    
	    Thread.sleep(5000);
	    
		setCookis("13713419479", webDriver);
		webDriver.get("http://www.dataoke.com/item?id=2318180");
		
		 element =webDriver.findElement(By.xpath("//*[@class='add-tui J_add_tui']"));
	    element.click();
	    */
	    
	    
	    //webDriver.close();
	}
	
	public static boolean execute(String id,String uname)throws Exception{
		/*if(webDriver==null){
			webDriver = SeleniumUtil.initChromeDriver();
		}*/
		
		/* List<HttpHost> hosts = IpUtils.getips("http://ip.memories1999.com/api.php?dh=2764810913906166&sl=1&xl=%E5%9B%BD%E5%86%85&gl=1");
		 if(CollectionUtils.isEmpty(hosts)){
			 System.out.println("获取ip为kong>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 Thread.sleep(5000);
			 return false;
		 }else{
			 System.out.println("获取代理ip成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ip="+hosts.get(0).getHostName()+" prot:"+hosts.get(0).getPort());
		 }
		HttpHost host = hosts.get(0); */
		
		//http://ip.memories1999.com/api.php?dh=2764810913906166&sl=1&xl=%E5%9B%BD%E5%86%85&gl=1
		
		webDriver = SeleniumUtil.initChromeDriver();
		try{
			webDriver.get("http://www.dataoke.com/item?id="+id);
			setCookis(uname, webDriver);
			webDriver.get("http://www.dataoke.com/item?id="+id);
			Thread.sleep(Cmd.getSleepTime());
			WebElement element =webDriver.findElement(By.xpath("//*[@class='add-tui J_add_tui']"));
		    element.click();
		    Thread.sleep(Cmd.getSleepTime());
		    webDriver.get("http://www.dataoke.com/ucenter/favorites_quan.asp");
		    Thread.sleep(Cmd.getSleepTime());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			webDriver.close();
		}
		return true;
	}
	
	
	public static void setCookis(String uname,String cookisStr,WebDriver webDriver)throws Exception{
		Map<String, String> cookisMap = new HashMap<String, String>();
		
		for (String str : cookisStr.split("\\;")) {
			cookisMap.put(str.split("\\=")[0].trim(), str.split("\\=")[1]);
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
	
	public static void setCookis(String uname,WebDriver webDriver)throws Exception{
		Map<String, String> cookisMap = new HashMap<String, String>();
		String cookisStr = "random=8696; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492777462; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF; ASPSESSIONIDQSBTQSCS=OHDGKNPACFIHDDFNANILEPKF; token=883f5a1f6733a23954015a6672a70099; ASPSESSIONIDSQBQSSDT=KNFMMBABCBPCEFDLDGGAGLJO; ASPSESSIONIDQSASQTDT=CDAFIBABKOLMLCOGGMEINGBM";
		cookisStr= "UM_distinctid=15e0fa82d60da-0fdc8d80f896c1-3a3e5e06-1fa400-15e0fa82d61251A; tj_cid=864e6625-77db-424f-61ac-a72bc9115defA; _uab_collina=150650917885513423249654; _umdata=0823A424438F76AB662C07C35ABB19CD7E6CF2849366957A50CFC63CF123B531E9261C88222E7BF3CD43AD3E795C914CB0214C2EC9B26B6A389460DA864C8447; userid=537000; user_email=15201733861; user%5Femail=15201733861; upe=a2766ec2; upi=927f9c25; browserCode=c5818e19fdb4867f5e5f414721f7451a; dtk_web=clo1aqqutbnmeeeevheepqd632; token=d78a9d8d10cac2f0ffb9512015cd0728; random=6910; CNZZDATA1257179126=1264628945-1503496208-%7C1506953339";
		cookisStr="UM_distinctid=15db713ac1d3-0ce5e05938e0c08-41554130-1fa400-15db713ac1f143; CNZZDATA1257179126=2050176312-1502010160-%7C1504532038; browserCode=f958a9eacaf4d4d244f75497cf4f5fa2; tj_cid=e23b49e5-197f-20c1-7452-90baef759650; token=2583479153087d7dcc0de4015560bfdd; random=7222; dtk_web=p3vnnvvunhpootindb3tahhim6; _uab_collina=150764260183485975487133; _umdata=55F3A8BFC9C50DDA002B45A73004ACD120748500108A7D0D2BD4413A5F4DC3893D5979534E208EB8CD43AD3E795C914CC6858E199167E17A4505FBC8D51EF6AB; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=a2766ec2; upi=e1c693b8";
		cookisStr= CKUtils.getAll().get(uname); 
		LOG.printLog("获取webDriver ck==="+cookisStr);
		if(StringUtils.isEmpty(cookisStr)){
			LOG.printLog("uname=="+uname+" ck==="+cookisStr);
			return;
		}
		
		for (String str : cookisStr.split("\\;")) {
			cookisMap.put(str.split("\\=")[0].trim(), str.split("\\=")[1]);
		}

	/*	for (Cookie c : Test.getObjToFile(uname)) {
			// System.out.println(c.getName()+"===="+c.getValue());
			// buffer.append(c.getName()).append("=").append(c.getValue()).append("; ");
			cookisMap.put(c.getName().trim(), c.getValue());
		}
*/
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
