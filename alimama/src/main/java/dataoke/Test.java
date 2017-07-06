package dataoke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import jodd.http.Cookie;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.http.ProxyInfo.ProxyType;
import jodd.http.net.SocketHttpConnectionProvider;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.CharUtil;
import util.HtmlUnitUtil;
import util.HttpClientUtil;
import util.IpPoolUtil;
import util.IpUtils;
import util.MyConnectionProvider;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fx.HttpTest;

public class Test {
	static String taoId = "118753669";//luoyuna0905@163.com
	static String pid = "mm_118753669_32220852_116226718";
	
	//static String taoId = "118054027";//xiaonin0322@163.com
	
	static Map<String,Cookie[]> map = new HashMap<String, Cookie[]>();
	
	public static void main(String[] args) throws Exception{
		//boolean flag = tuijian("2231931");
		//System.out.println("推广成功》》》》》》》》》》》》》》》》》》》"+flag);
		//execteAll("2255030",new File("G:\\taoke\\第4组.txt"));
		//executeTest();
	  // testck();
		/*File[] files = new File[]{new File("G:\\taoke\\第1组500.txt"),new File("G:\\taoke\\第2组500.txt")
		,new File("G:\\taoke\\第3组500.txt"),new File("G:\\taoke\\第4组.txt")};
		execteAll("2262582",files);*/
		
		//deleteAll("15201733860");
		//login("15201733860", "1qaz2wsx");
		//deleteById("2247791","13040003624");
		//check();
		
		
		//System.out.println(readDetailHttp("2325753", "13411679603"));
		
		//System.out.println(biaoji("2333730", "17189683009"));
		
		//System.out.println(queryPidByName("17189683009", "已破形常准"));
		
		//System.out.println(createPidAll("17189683009"));
		

		//System.out.println(loginHttpClient("15917728864", "3pd0qu"));
		
		//System.out.println(getUserPids("15917728864"));
		System.out.println(loginHttpClient("15201733860", "1qaz2wsx"));
		//System.out.println(updatePwdHttpClient("15201733860", "1qaz2wsx2", "1qaz2wsx"));
		System.out.println(taoTokenHttpClient("2895505", "15201733860"));
		
		//System.out.println(getExitsSetPidExeitHttpClient("15917728864"));
		
		//System.out.println(createPidHtppClient("13191048115"));
		//System.out.println(createPidAllHttpClient("15917728864"));
		
		//System.out.println(getExitsSetPidFlagExeitHttpClient("13923938760"));
		//System.out.println(deletePidHtppClient("15917728864","1684316"));
		
		//System.out.println(checkPidExeitHttpClient("13829502088"));
		
		
		//System.out.println(createPidAllHttpClient("15544729135"));
		
		//System.out.println(zhuan("13411679603", "2337538"));
		
		//System.out.println(goodClickWebClient("13411679603", "2318180"));
		
		//testHttpclientProxy();
		
		//System.out.println(tuijian("2359220", "17774505172"));
		
		//System.out.println(deleteAll("17774505172","0"));
		
		/*for(int i=0;i<500;i++){
			System.out.println(readDetailHttp("2378064", "15201733860"));
			Thread.sleep(Cmd.getSleepTime());
		}*/
		
		//System.out.println(tuijian("2449218", "13532300165"));
		//System.out.println(tuijianHttpClient("2516967", "15544728853"));
	
		//System.out.println(loginHttpClient("15544728853", "dbnat97"));
		//System.out.println(tuijianHttpClient("2544577", "15544728853"));
		//System.out.println(login("15544728853", "dbnat97"));

		//System.out.println(deleteAllHttpClient("15544728853", "0"));
	}
	
	 
	 
	 public static void testHttpclientProxy()throws Exception{
		 String url ="http://ip.chinaz.com/";
		 HttpPost httpRequest = new HttpPost(url);
		 HttpClientUtils httpClientUtils = new HttpClientUtils();
		 HttpHost proxy = getProxy();
		 String rc =  httpClientUtils.getContentByUrl(proxy, httpRequest, 10000);
			 System.out.println(rc);
		}
	
	public static void testjoddProxy()throws Exception{
		HttpRequest httpRequest = HttpRequest.get("http://ip.chinaz.com/");
		HttpResponse httpResponse = httpRequest.open(getSocketHttpConnectionProvider()).send();
		 String rc = httpResponse.bodyText();
		 System.out.println(rc);
	}
	
	public static boolean check(){
		try{
			String url = "http://m635674608.iteye.com/blog/2370843";
			 HttpRequest httpRequest = HttpRequest.post(url).timeout(20000);
			 HttpResponse response = httpRequest.send();
			 String rc = response.bodyText();
			 if(rc.contains("dataoke")){
				 System.out.println("验证码通过>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				 return true;
			 }else{
				 System.out.println("验证码不通过>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	public static boolean checkDataokeZhuCe(){
		try{
			String url = "http://m635674608.iteye.com/blog/2376153";
			 HttpRequest httpRequest = HttpRequest.post(url).timeout(20000);
			 HttpResponse response = httpRequest.send();
			 String rc = response.bodyText();
			 if(rc.contains("dataoke")){
				 System.out.println("验证码通过>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				 return true;
			 }else{
				 System.out.println("验证码不通过>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static void executeTest()throws Exception{
		String pid ="2250281";
		
			//String uname = "15201733860";
			//String pwd = "1qaz2wsx";
		   String uname = "17071610350";
		   String pwd = "1qaz2wsx";
		
			System.out.println("u = "+uname + "p = "+pwd +" 开始登陆");
			
			boolean flag = login(uname,pwd);
			System.out.println("u = "+uname + "登陆>>>>>>>>>>>>>"+flag);
			Thread.sleep(3000);
			if(flag){
				flag = tuijian(pid,uname);
				//flag = tuijianToFile(pid,uname);
				if(flag){
					System.out.println("推广成功》》》》》》》》》》》》》》》》》》》pid="+pid+" uname="+uname);
				}else{
					System.out.println("推广失败》》》》》》》》》》》》》》》》》》pid="+pid+"uname="+uname);
				}
			}
			Thread.sleep(5000);
	}
	
	
	public static void testck()throws Exception{
		//boolean flag = login("15201733860","1qaz2wsx");
		//System.out.println(flag);
		
		 boolean flag = getSlftConent("2217018","15201733860");
		Thread.sleep(1000);
		if(flag){
			System.out.println("商品已存在商品列表..删除商品");
			flag = deleteId("2217018","15201733860");
			if(flag){
				System.out.println("商品删除==="+flag);
			}else{
				System.out.println("商品删除失败结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		}else{
			System.out.println("不在推广列表>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		/*Thread.sleep(1000);
		flag = tuijianToFile("2217018","15201733860");
		if(flag){
			System.out.println("推广成功》》》》》》》》》》》》》》》》》》》");
		}else{
			System.out.println("推广失败》》》》》》》》》》》》》》》》》》》");
		}*/
	}
	
	static File fileStoreBase = new File("D:\\dataoke\\ck\\");
	
	/**
	 * 获取判断商品是否在推广商品页面
	 */
	public static boolean getSlftConent(String id,String uname)throws Exception{
		String url ="http://www.dataoke.com/ucenter/favorites_quan.asp";
		HttpRequest httpRequest = HttpRequest.get(url);
		// httpRequest.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/ucenter/favorites_quan.asp");
		 httpRequest.header("Upgrade-Insecure-Requests", "1");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 httpRequest.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		 httpRequest.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 

		setCookis(uname, httpRequest);
		 
		 HttpResponse response = httpRequest.send();
		 response = response.charset("gb2312");
		 String rc = response.bodyText();
		 System.out.println(rc);
		 
		 if(rc.contains(id)){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	static String cookisStr = "UM_distinctid=15c78440a2d0-08a9102ea-3d61430e-1fa400-15c78440a307a; browserCode=41ce055f56af650c64f1d39948332e12; userid=634819; user_email=13412800182; user%5Femail=13412800182; upe=751137f7; dtk_web=n2smhui1f7r15dodds7kj0k832; e88a8013345a8f05461081898691958c=f27878e0f34e03bba21508f32a090b503f309268a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22634819%22%3Bi%3A1%3Bs%3A11%3A%2213412800182%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDCCCQDQSC=DKLHDOBAHEGIHLKIFPMOLMBL; ASPSESSIONIDSQSABSSQ=PNJJFNBAAFKMJDDMEDMBKCAI; ASPSESSIONIDQSSCBTSR=KALPONBAJPNIKENGGKACLOGA; ASPSESSIONIDCCASAQSC=FJKDKNBAONEPAJGDMOECIOPO; ASPSESSIONIDQSRBBSTQ=MILJCOBAOLCFGEIJBIFIKNND; ASPSESSIONIDCADQBQTC=AMJFGNBAKGMLKMGBKMCOFPND; ASPSESSIONIDSQQCBSSQ=FALFJNBAMNCBBNBCHNIEGKKI; ASPSESSIONIDAABRDQTD=IKLNPNBACBAKAFJLADJECALG; ASPSESSIONIDAABRBRTD=CDEKCPBAJJEHMPFFJJGFEJEK; ASPSESSIONIDCCCQBRSC=FJDDGPBAAONKAFODFOEGDOJD; ASPSESSIONIDSSQDBTTQ=NJEMBPBAFPCNDKKLEPPMEICB; ASPSESSIONIDACDTBRTD=JEDCJOBAHHNNGNFJMGNAEEKC; ASPSESSIONIDQQRCASTQ=FKEFFPBAJFCGDOCOOABPOKAA; ASPSESSIONIDCAAQAQSC=LHDANOBABDCGEMFLADEEEIMI; ASPSESSIONIDQSTBATTQ=ICDBLPBAOLNDAIJBFDOBNCFM; ASPSESSIONIDQQSABTTR=EPDCMOBAAHJGALIKDNKNOLNG; ASPSESSIONIDACBTBQTD=CHDNLPBADMHNEDHCPEADCLCL; ASPSESSIONIDAAASCQTC=KBHLPPBAJOMBOILAHIPOKFAN; ASPSESSIONIDQSSCATTR=JBHHEACALCLBPJGIGPOPFFJK; ASPSESSIONIDSQSDASTR=DPGNOPBAGIOJJCNJBCOLGHKH; ASPSESSIONIDCAATAQTC=JAPJOACABMEMJOFLJJCLDBDE; ASPSESSIONIDCADQBQSD=BEAAJACABGAKDPKKBGIFPGJK; ASPSESSIONIDCCDQDQTD=IJACIBCALNDMNJHFLPJPOGCI; ASPSESSIONIDQSSDDRTQ=IBPJBBCAJANEBLIGPCKKGKMG; ASPSESSIONIDQSTDCQSQ=KLPDHBCACNOIAJICOFPCCBMO; ASPSESSIONIDCACRARSD=KIPHCBCAGFNJPKMBHNJJKMDN; ASPSESSIONIDQQRCBSTR=HDPBIACALAIFOMPCOHHICNDB; token=0089f37473d7e3b787aeecf9991003d4; random=6866; CNZZDATA1257179126=524482265-1496663805-%7C1498956192";
	
	public static void setCookis(String uname,HttpRequest httpRequest)throws Exception{
		Map<String, String> cookisMap = new HashMap<String, String>();
		//String cookisStr = "random=8696; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492777462; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF; ASPSESSIONIDQSBTQSCS=OHDGKNPACFIHDDFNANILEPKF; token=8f5d2c916cf9a2051dea789e96780d5d; ASPSESSIONIDSQBQSSDT=KNFMMBABCBPCEFDLDGGAGLJO; ASPSESSIONIDQSASQTDT=CDAFIBABKOLMLCOGGMEINGBM";
		//cookisStr = "UM_distinctid=15b80b0952ee2-0a7b58a877a4498-47534130-15f900-15b80b0952f128; CNZZDATA1257179126=1533751114-1492507982-http%253A%252F%252Fwww.dataoke.com%252F%7C1494984030; upe=c3ac5f62; not-any-more=1; userid=540111; user_email=13411679603; user%5Femail=13411679603; e88a8013345a8f05461081898691958c=aef7abed45989f76675cf8c4147603eee7651f15a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22540111%22%3Bi%3A1%3Bs%3A11%3A%2213411679603%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDQQBQSSCT=JCBFAJNBGJBJIFHKBIMFGPMI; dtk_web=2v6neq4vk4n7k6k41o516egct0; token=d42217d39115f757ac2cfa11ce086425; random=2053; ASPSESSIONIDAQCSCTDR=KLGFMINBPAIADKDCBELHBEID";
		//cookisStr ="UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1495022035; upe=20d3785a; userid=577118; user_email=13751442412; user%5Femail=13751442412; e88a8013345a8f05461081898691958c=7d9adb0eb6afa7a4eecc7c18902e6085c5ff7f4aa%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22577118%22%3Bi%3A1%3Bs%3A11%3A%2213751442412%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDCSDRDTDQ=GMMCEACCDGPMDPPGONLJOKOE; dtk_web=mbc6880vo5it1sf371aqbiasa5; ASPSESSIONIDSSBQRSDS=JDDNOPBCONNOEFIGOLFPKLPI; token=6d2ae79ff668877fd32526c96d116e4b; random=1366";
		for (String str : cookisStr.split("\\;")) {
			cookisMap.put(str.split("\\=")[0].trim(), str.split("\\=")[1]);
		}

		Cookie[] cks = getObjToFile(uname);
		if(ArrayUtils.isNotEmpty(cks)){
			for (Cookie c : getObjToFile(uname)) {
				// System.out.println(c.getName()+"===="+c.getValue());
				// buffer.append(c.getName()).append("=").append(c.getValue()).append("; ");
				cookisMap.put(c.getName().trim(), c.getValue());
			}

			StringBuffer buffer = new StringBuffer();

			for (Entry<String, String> en : cookisMap.entrySet()) {
				buffer.append(en.getKey().trim()).append("=").append(en.getValue())
						.append("; ");
			}
			httpRequest.header("Cookie", buffer.toString());
		}else{
			httpRequest.header("Cookie", cookisStr);
		}

		// System.out.println(buffer.toString());

		
	}
	

	public static void setCookis(String uname,HttpRequestBase httpRequest)throws Exception{
		Map<String, String> cookisMap = new HashMap<String, String>();
		//String cookisStr = "random=8696; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492777462; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF; ASPSESSIONIDQSBTQSCS=OHDGKNPACFIHDDFNANILEPKF; token=8f5d2c916cf9a2051dea789e96780d5d; ASPSESSIONIDSQBQSSDT=KNFMMBABCBPCEFDLDGGAGLJO; ASPSESSIONIDQSASQTDT=CDAFIBABKOLMLCOGGMEINGBM";
		//cookisStr ="UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1495022035; upe=20d3785a; userid=577118; user_email=13751442412; user%5Femail=13751442412; e88a8013345a8f05461081898691958c=7d9adb0eb6afa7a4eecc7c18902e6085c5ff7f4aa%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22577118%22%3Bi%3A1%3Bs%3A11%3A%2213751442412%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDCSDRDTDQ=GMMCEACCDGPMDPPGONLJOKOE; dtk_web=mbc6880vo5it1sf371aqbiasa5; ASPSESSIONIDSSBQRSDS=JDDNOPBCONNOEFIGOLFPKLPI; token=6d2ae79ff668877fd32526c96d116e4b; random=1366";
		for (String str : cookisStr.split("\\;")) {
			cookisMap.put(str.split("\\=")[0].trim(), str.split("\\=")[1]);
		}

		Cookie[] cks = getObjToFile(uname);
		if(ArrayUtils.isNotEmpty(cks)){
			for (Cookie c : getObjToFile(uname)) {
				cookisMap.put(c.getName().trim(), c.getValue());
			}

			StringBuffer buffer = new StringBuffer();

			for (Entry<String, String> en : cookisMap.entrySet()) {
				buffer.append(en.getKey().trim()).append("=").append(en.getValue())
						.append("; ");
			}
			httpRequest.setHeader("Cookie", buffer.toString());
		}else{
			httpRequest.setHeader("Cookie", cookisStr);
		}

	}
	
	public static boolean pidAddHttpClient(String uname,String pid,String type) throws Exception {

		//set_wx
       //set_qq
		String url = "http://www.dataoke.com/ucenter/mypid.asp?act="+type+"&id="+pid;
		HttpGet httpRequest = new HttpGet(url);
		httpRequest.setHeader("Host", "www.dataoke.com");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.setHeader("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);

		String rc =  HttpClientUtil.sendGetRequest(httpRequest,"gb2312",proxy);
		//System.out.println("pidAddwx：" + rc);

		if (rc.contains("欢迎")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean pidAdd(String uname,String pid,String type) throws Exception {

		//set_wx
       //set_qq
		String url = "http://www.dataoke.com/ucenter/mypid.asp?act="+type+"&id="+pid;
		HttpRequest httpRequest = HttpRequest.get(url);
		httpRequest.header("Host", "www.dataoke.com");
		httpRequest
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.header("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.header("Upgrade-Insecure-Requests", "1");
		httpRequest.header("Connection", "keep-alive");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.header("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);

		HttpResponse response = httpRequest.send();
		response = response.charset("gb2312");
		String rc = response.bodyText();

		//System.out.println("pidAddwx：" + rc);

		if (rc.contains("欢迎")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean copy(String uname,String pid,String type) throws Exception {

		//set_wx
       //set_qq
		String url = "http://ei.cnzz.com/stat.htm?id=1257179126&r=&lg=zh-cn&ntime=1493814841&cnzz_eid=1538129784-1492772062-http%3A%2F%2Fwww.dataoke.com%2F&showp=1920x1080&ei=%25E5%2595%2586%25E5%2593%2581%25E8%25AF%25A6%25E6%2583%2585%2520%25E5%25A4%258D%25E5%2588%25B6QQ%25E6%25A8%25A1%25E7%2589%2588%7C%25E7%2582%25B9%25E5%2587%25BB%7C%7C0%7C&t=%E3%80%90%E4%BA%94%E4%B8%80%E7%8B%82%E6%AC%A2%E3%80%91%E6%A0%BC%E5%8A%9B%E5%8F%B0%E5%BC%8F%E7%94%B5%E6%89%87%E5%AE%9A%E6%97%B6%E9%9D%99%E9%9F%B3%E8%8A%82%E8%83%BD%20-%20%E5%A4%A7%E6%B7%98%E5%AE%A2%E8%81%94%E7%9B%9F&umuuid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105&h=1&rnd=1287433446";
		HttpRequest httpRequest = HttpRequest.get(url);
		httpRequest.header("Host", "ei.cnzz.com");
		httpRequest
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.header("Referer",
				"http://www.dataoke.com/item?id="+pid);
		httpRequest.header("Upgrade-Insecure-Requests", "1");
		httpRequest.header("Connection", "keep-alive");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.header("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);

		HttpResponse response = httpRequest.send();
		response = response.charset("gb2312");
		String rc = response.bodyText();

		//System.out.println("pidAddwx：" + rc);

		if (rc.contains("欢迎")) {
			return true;
		} else {
			return false;
		}
	}

	
	public static String queryPidByName(String uname,String pname) throws Exception {

		String url = "http://www.dataoke.com/ucenter/mypid.asp";
		HttpRequest httpRequest = HttpRequest.get(url);
		httpRequest.header("Host", "www.dataoke.com");
		httpRequest
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.header("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.header("Upgrade-Insecure-Requests", "1");
		httpRequest.header("Connection", "keep-alive");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.header("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		HttpResponse response = httpRequest.send();
		response = response.charset("gb2312");
		String rc = response.bodyText();

		//System.out.println("createPid返回结果：" + rc);

		if (rc.contains("PID管理")) {
			Document document = Jsoup.parse(rc);
			Elements elements = document.getElementsByTag("tr");
			for(Element element:elements){
				String html = element.html();
				if(html.contains(pname) && !html.contains("领券优惠")){
					//System.out.println(html);
					//System.out.println("===============================");
					elements = element.getElementsByTag("a");
					for(Element e:elements){
						//System.out.println(e.html());
						String href = e.attr("href");
						if(StringUtils.isNotBlank(href) && href.contains("id=")){
							return href.replace("?act=set_qq&id=", "").replace("?act=set_wx&id=", "");
						}
					}
				}
			}
			
			
			return "";
		} else {
			return "";
		}
	}
	
	public static String queryPidByNameHttpClient(String uname,String pname) throws Exception {

		String url = "http://www.dataoke.com/ucenter/mypid.asp";
		HttpGet httpRequest =new HttpGet(url);
		httpRequest.setHeader("Host", "www.dataoke.com");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.setHeader("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		String rc = HttpClientUtil.sendGetRequest(httpRequest, "gb2312", proxy);

		//System.out.println("createPid返回结果：" + rc);

		if (rc.contains("PID管理")) {
			Document document = Jsoup.parse(rc);
			Elements elements = document.getElementsByTag("tr");
			for(Element element:elements){
				String html = element.html();
				if(html.contains(pname) && !html.contains("领券优惠")){
					//System.out.println(html);
					//System.out.println("===============================");
					elements = element.getElementsByTag("a");
					for(Element e:elements){
						//System.out.println(e.html());
						String href = e.attr("href");
						if(StringUtils.isNotBlank(href) && href.contains("id=")){
							return href.replace("?act=set_qq&id=", "").replace("?act=set_wx&id=", "");
						}
					}
				}
			}
			
			
			return "";
		} else {
			return "";
		}
	}
	
	
	public static boolean getExitsSetPidFlagExeitHttpClient(String uname) throws Exception {

		String url = "http://www.dataoke.com/ucenter/mypid.asp";
		HttpGet httpRequest =new HttpGet(url);
		httpRequest.setHeader("Host", "www.dataoke.com");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.setHeader("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		String rc = HttpClientUtil.sendGetRequest(httpRequest, "gb2312", proxy);

		//System.out.println("createPid返回结果：" + rc);
		List<String> pids = new ArrayList<String>();
		Document document = Jsoup.parse(rc);
		
		Elements elements =document.select("a");
        for(Element element: elements){
        	String href = element.attr("href");
        	if(href.contains("act=qx_bz")){
        		String trHtml = element.parent().parent().html();
        		if(trHtml.contains(pid)){
        			pids.add(href.replace("?act=qx_bz&id=", "").trim());
        		}
        	}
        }
        
        if(pids.size()==2){
        	return true;
        }
        
        return false;	
}
	
	public static List<String> getExitsSetPidExeitHttpClient(String uname) throws Exception {

		String url = "http://www.dataoke.com/ucenter/mypid.asp";
		HttpGet httpRequest =new HttpGet(url);
		httpRequest.setHeader("Host", "www.dataoke.com");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.setHeader("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		String rc = HttpClientUtil.sendGetRequest(httpRequest, "gb2312", proxy);

		//System.out.println("createPid返回结果：" + rc);
		List<String> pids = new ArrayList<String>();
		Document document = Jsoup.parse(rc);
		
		Elements elements =document.select("a");
        for(Element element: elements){
        	String href = element.attr("href");
        	if(href.contains("act=qx_bz")){
        		pids.add(href.replace("?act=qx_bz&id=", "").trim());
        	}
        }
        return pids;	
}
	
	
	public static boolean checkPidExeitHttpClient(String uname) throws Exception {

		String url = "http://www.dataoke.com/ucenter/mypid.asp";
		HttpGet httpRequest =new HttpGet(url);
		httpRequest.setHeader("Host", "www.dataoke.com");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.setHeader("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		String rc = HttpClientUtil.sendGetRequest(httpRequest, "gb2312", proxy);

		//System.out.println("createPid返回结果：" + rc);

		if (StringUtils.countMatches(rc, "取消")==2 || StringUtils.countMatches(rc, "取消")>2) {
		    return true;
			}
      return false;	
}
	
	public static boolean createPidAllHttpClient(String uname)throws Exception{
		
		System.out.println("开始检测是否设置pid>>>>>>>>>>>>>>"+uname);
		if(getExitsSetPidFlagExeitHttpClient(uname)){
			return true;
		}
		 Thread.sleep(2000);
		 if(checkPidExeitHttpClient(uname)){
			 System.out.println("pid已经存在>>>>>>>>>>>"+uname);
			 Thread.sleep(2000);
			
			 List<String> pids = getExitsSetPidExeitHttpClient(uname);
			 Thread.sleep(2000);
			 if(CollectionUtils.isNotEmpty(pids)){
				 boolean flag = deletePidHtppClient(uname, pids.toArray(new String[]{}));
				 System.out.println("uname =="+uname+" 取消pid==="+flag);
				 Thread.sleep(2000);
				 if(!flag){
					 pids = getExitsSetPidExeitHttpClient(uname);
					 Thread.sleep(2000);
					 flag = deletePidHtppClient(uname, pids.toArray(new String[]{}));
					 System.out.println("uname =="+uname+" 取消pid==="+flag);
					 Thread.sleep(2000);
				 }
				 if(!flag){
					 pids = getExitsSetPidExeitHttpClient(uname);
					 Thread.sleep(2000);
					 flag = deletePidHtppClient(uname, pids.toArray(new String[]{}));
					 System.out.println("uname =="+uname+" 取消pid==="+flag);
					 Thread.sleep(2000);
					 return false;
				  }
				 
			 }
 			 // return true;
		 }
		 System.out.println("没有设置pid，开始设置pid>>>>>>>>>>>>>>"+uname);
		 String title=createPidHtppClient(uname);
		 System.out.println("title: "+title);
		 Thread.sleep(2000);
		 boolean flag = false;
		 if(StringUtils.isNotBlank(title)){
			 System.out.println("开始查询pi========"+title);
			 String pid = queryPidByNameHttpClient(uname, title);
			 System.out.println("pid====="+pid+" title==="+title);
			 Thread.sleep(2000);
			  flag = pidAddHttpClient(uname, pid, "set_wx");
			 System.out.println("设为微信专用>>>>>>>>>>>>>>>"+flag);
		 }
		 Thread.sleep(2000);
		 title=createPidHtppClient(uname);
		 System.out.println("title: "+title);
		 if(StringUtils.isNotBlank(title) && flag){
			 Thread.sleep(2000);
			 System.out.println("开始查询pi========"+title);
			 String pid = queryPidByNameHttpClient(uname, title);
			 System.out.println("pid====="+pid+" title==="+title);
			 Thread.sleep(2000);
			  flag =  pidAddHttpClient(uname, pid, "set_qq");
			 System.out.println("设为Q群专用>>>>>>>>>>>>>>>"+flag);
		 }
		 return flag;
	}
	
	public static boolean createPidAll(String uname)throws Exception{
		 String title=createPid(uname);
		 System.out.println("title: "+title);
		 if(StringUtils.isNotBlank(title)){
			 String pid = queryPidByName(uname, title);
			 Thread.sleep(1000);
			 boolean flag = pidAdd(uname, pid, "set_wx");
			 System.out.println("设为微信专用>>>>>>>>>>>>>>>"+flag);
		 }
		 Thread.sleep(1000);
		 title=createPid(uname);
		 System.out.println("title: "+title);
		 if(StringUtils.isNotBlank(title)){
			 String pid = queryPidByName(uname, title);
			 Thread.sleep(1000);
			 boolean flag =  pidAdd(uname, pid, "set_qq");
			 System.out.println("设为Q群专用>>>>>>>>>>>>>>>"+flag);
		 }
		 return true;
	}
	
	public static String createPidHtppClient(String uname) throws Exception {

		String url = "http://www.dataoke.com/ucenter/mypid.asp?act=add";
		HttpPost httpRequest = new HttpPost(url);
		httpRequest.setHeader("Host", "www.dataoke.com");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.setHeader("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		//httpRequest.header("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		String title = CharUtil.drawRandomNum();
		//title = "Æ¬³åÊý·Å¶¼";
		//String pid = CharUtil.getRandomPid();
		//String pid = CharUtil.getRandomPid(taoId);
		//pid="mm_118753669_32220852_116226718";
		/*httpRequest.form("title", title);
		httpRequest.form("tong_pid", pid);
		httpRequest.form("Submit", " ´´ ½¨ ");
		
		httpRequest.charset("gb2312");
		
		System.out.println("title: "+title + " pid : "+pid);

		HttpResponse response = httpRequest.send();
		response = response.charset("gb2312");
		String rc = response.bodyText();
*/
		
		String rc = HttpClientUtil.sendPostRequest(httpRequest, "title="+title+"&"+"tong_pid="+pid+"&"+"Submit= ´´ ½¨ ", true, "gb2312", "gb2312", proxy, null);
		//System.out.println("createPid返回结果：" + rc);

		if (rc.contains("创建成功")) {
			//String ppid = queryPidByName(uname, title);
			return title;
		} else {
			return "";
		}
	}
	
	public static List<String> getUserPids(String uname) throws Exception {

		String url = "http://www.dataoke.com/ucenter/favorites_quan.asp";
		HttpGet httpRequest = new HttpGet(url);
		httpRequest.setHeader("Host", "www.dataoke.com");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.setHeader("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		//httpRequest.header("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		String title = CharUtil.drawRandomNum();
		//title = "Æ¬³åÊý·Å¶¼";
		//String pid = CharUtil.getRandomPid();
		String pid = CharUtil.getRandomPid(taoId);
		/*httpRequest.form("title", title);
		httpRequest.form("tong_pid", pid);
		httpRequest.form("Submit", " ´´ ½¨ ");
		
		httpRequest.charset("gb2312");
		
		System.out.println("title: "+title + " pid : "+pid);

		HttpResponse response = httpRequest.send();
		response = response.charset("gb2312");
		String rc = response.bodyText();
*/
		
		String rc = HttpClientUtil.sendGetRequest(httpRequest,"gb2312", proxy);
		//System.out.println("createPid返回结果：" + rc);

		List<String> users = new ArrayList<String>();
		Document document = Jsoup.parse(rc);
		for(Element e:document.select("a")){
			String href= e.attr("href");
			if(StringUtils.isNotBlank(href) && href.contains("item?id=")){
				String id = href.replace("/item?id=", "");
				users.add(id);
			}
		}
		return users;
	}
	
	/**
	 * 取消pid
	 */
	public static boolean deletePidHtppClient(String uname,String... ids) throws Exception {
		if(ids==null){
			return false;
		}
		boolean flag = true;
		for(String id:ids){
		String url = "http://www.dataoke.com/ucenter/mypid.asp?act=qx_bz&id="+id;
		HttpGet httpRequest = new HttpGet(url);
		httpRequest.setHeader("Host", "www.dataoke.com");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.setHeader("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");

		setCookis(uname, httpRequest);
		
		String rc = HttpClientUtil.sendGetRequest(httpRequest, "gb2312", proxy);
		//System.out.println("createPid返回结果：" + rc);
			if (rc.contains("授权管理")) {
				System.out.println("pid===="+id+" 删除成功>>>>>>>>>>>>>>>>>>>>");
			} else {
				System.out.println("pid===="+id+" 删除失败>>>>>>>>>>>>>>>>>>>>");
				flag = false;
			}
			Thread.sleep(2000);
		}
		return flag;
		
	}
	
	public static String createPid(String uname) throws Exception {

		String url = "http://www.dataoke.com/ucenter/mypid.asp?act=add";
		HttpRequest httpRequest = HttpRequest.post(url);
		httpRequest.header("Host", "www.dataoke.com");
		httpRequest
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.header("Referer",
				"http://www.dataoke.com/ucenter/mypid.asp");
		httpRequest.header("Upgrade-Insecure-Requests", "1");
		httpRequest.header("Connection", "keep-alive");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.header("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		//httpRequest.header("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		String title = CharUtil.drawRandomNum();
		//title = "Æ¬³åÊý·Å¶¼";
		String pid = CharUtil.getRandomPid();
		httpRequest.form("title", title);
		httpRequest.form("tong_pid", pid);
		httpRequest.form("Submit", " ´´ ½¨ ");
		
		httpRequest.charset("gb2312");
		
		System.out.println("title: "+title + " pid : "+pid);

		HttpResponse response = httpRequest.send();
		response = response.charset("gb2312");
		String rc = response.bodyText();

		//System.out.println("createPid返回结果：" + rc);

		if (rc.contains("创建成功")) {
			//String ppid = queryPidByName(uname, title);
			return title;
		} else {
			return "";
		}
	}
	
	public static boolean zhuan(String uname,String ppid)throws Exception {
		try{
			boolean flag = tuijian2to1_1(uname, ppid);
			System.out.println("二转一第一步>>>>>>>>>>>>>>>>>>>>>>>"+flag);
			
			if(!flag){
				return flag;
			}
			
			flag = tuijian2to1_2(uname, ppid);
			System.out.println("二转一第二步>>>>>>>>>>>>>>>>>>>>>>>"+flag);
			
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean tuijian2to1_1(String uname,String ppid) throws Exception {

		String url = "http://www.dataoke.com/dtpwd";
		HttpRequest httpRequest = HttpRequest.post(url);
		httpRequest.header("Host", "www.dataoke.com");
		httpRequest
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.header("Referer",
				"http://www.dataoke.com/item?id="+ppid);
		httpRequest.header("Upgrade-Insecure-Requests", "1");
		httpRequest.header("Connection", "keep-alive");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.header("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		//httpRequest.header("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		httpRequest.form("gid", ppid);
		httpRequest.form("type", 1);
		
		httpRequest.charset("gb2312");
		

		HttpResponse response = httpRequest.send();
		response = response.charset("gb2312");
		String rc = response.bodyText();

		//System.out.println("createPid返回结果：" + rc);

		if (rc.contains(":1")) {
			//String ppid = queryPidByName(uname, title);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean tuijian2to1_2(String uname,String ppid) throws Exception {

		String url = "http://www.dataoke.com/detailtpl";
		HttpRequest httpRequest = HttpRequest.post(url);
		httpRequest.header("Host", "www.dataoke.com");
		httpRequest
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		httpRequest.header("Referer",
				"http://www.dataoke.com/item?id="+ppid);
		httpRequest.header("Upgrade-Insecure-Requests", "1");
		httpRequest.header("Connection", "keep-alive");
		httpRequest.header("X-Requested-With", "XMLHttpRequest");
		httpRequest
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest.header("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		//httpRequest.header("X-Requested-With", "XMLHttpRequest");

		setCookis(uname, httpRequest);
		
		httpRequest.form("gid", ppid);
		
		httpRequest.charset("gb2312");

		HttpResponse response = httpRequest.send();
		response = response.charset("gb2312");
		String rc = response.bodyText();

		//System.out.println("createPid返回结果：" + rc);

		if (rc.contains(":1")) {
			//String ppid = queryPidByName(uname, title);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean biaojiAction(String goodId,String uname)throws Exception{
		
			String url ="http://www.dataoke.com/ucenter/save.asp?act=setup_fav_sendtime&gid="+goodId+"&_="+System.currentTimeMillis();
	HttpRequest httpRequest = HttpRequest.get(url);
	 httpRequest.header("Host", "www.dataoke.com");
	 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
	 httpRequest.header("Referer", "http://www.dataoke.com/ucenter/favorites_quan.asp");
	 httpRequest.header("Upgrade-Insecure-Requests", "1");
	 httpRequest.header("Connection", "keep-alive");
	 httpRequest.header("X-Requested-With", "XMLHttpRequest");
	 httpRequest.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	 httpRequest.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
	 httpRequest.header("X-Requested-With", "XMLHttpRequest");
	 

		setCookis(uname, httpRequest);
	 
	 HttpResponse response = httpRequest.send();
	 response = response.charset("gb2312");
	 String rc = response.bodyText();
	 
	 System.out.println("标记返回结果："+rc);
	 
	 if(rc.contains("成功")){
		 return true;
	 }else{
		 return false;
	 }
	}
	static SocketHttpConnectionProvider connectionProvider = null;
	
	
	public static HttpHost getProxy()throws Exception{
		 //List<HttpHost> hosts = IpUtils.getips("http://www.66ip.cn/getzh.php?getzh=2017050413171&getnum=1&isp=0&anonymoustype=2&start=&ports=&export=8080&ipaddress=&area=0&proxytype=2&api=https");
		 //ip.memories1999.com/api.php?dh=2764810913906166&sl=1&xl=国内&gl=1
		 List<HttpHost> hosts = IpUtils.getips("http://ip.memories1999.com/api.php?dh=2764810913906166&sl=1&xl=国内&gl=1");
		 
		 if(CollectionUtils.isEmpty(hosts)){
			 System.out.println("获取ip为kong>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 Thread.sleep(5000);
			 return null;
		 }else{
			 System.out.println("获取代理ip成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ip="+hosts.get(0).getHostName()+" prot:"+hosts.get(0).getPort());
		 }
		HttpHost host = hosts.get(0); 
	    return host;
	}
	
	public static SocketHttpConnectionProvider getSocketHttpConnectionProviderOld()throws Exception{
		 List<HttpHost> hosts = IpUtils.getips("http://tvp.daxiangdaili.com/ip/?tid=557335383289182&num=1&category=2&exclude_ports=8080,80,8081,8089&filter=on");
		 if(CollectionUtils.isEmpty(hosts)){
			 System.out.println("获取ip为kong>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 Thread.sleep(5000);
			 return null;
		 }else{
			 System.out.println("获取代理ip成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ip="+hosts.get(0).getHostName()+" prot:"+hosts.get(0).getPort());
		 }
		HttpHost host = hosts.get(0); 
		ProxyInfo proxyInfoObj = new ProxyInfo(ProxyType.HTTP, host.getHostName(), host.getPort(), "", "");
	    SocketHttpConnectionProvider provider =  new MyConnectionProvider();
	    provider.useProxy(proxyInfoObj);
	    return provider;
	}
	
	public static SocketHttpConnectionProvider getSocketHttpConnectionProvider()throws Exception{
		HttpHost host = IpPoolUtil.getHttpHost(); 
		ProxyInfo proxyInfoObj = new ProxyInfo(ProxyType.HTTP, host.getHostName(), host.getPort(), "", "");
	    SocketHttpConnectionProvider provider =  new MyConnectionProvider();
	    provider.useProxy(proxyInfoObj);
	    return provider;
	}
	
	public static boolean biaoji(String id,String uname)throws Exception{
		
		String url ="http://www.dataoke.com/ucenter/favorites_quan.asp";
		HttpRequest httpRequest = HttpRequest.get(url);
		// httpRequest.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/ucenter/favorites_quan.asp");
		 httpRequest.header("Upgrade-Insecure-Requests", "1");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 httpRequest.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		 httpRequest.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 
        setCookis(uname, httpRequest);
		 
		 HttpResponse response = httpRequest.send();
		 response = response.charset("gb2312");
		 String rc = response.bodyText();
		 
		 Document document = Jsoup.parse(rc);
		 
		Element elementId = document.getElementById("fav_add_rz_"+id);
		
		//System.out.println(elementId.attr("title"));
		
		elementId = elementId.previousElementSibling();
		String goodsId = elementId.attr("id");
		 System.out.println("id==="+id+" 获取商品id为："+goodsId);
		 if(StringUtils.isNotBlank(goodsId)){
			 goodsId = goodsId.replace("fav_sendtime_", "");
			 Thread.sleep(1000);
			 boolean bflag = biaojiAction(goodsId, uname);
			 System.out.println("商品："+id +" 标记结果："+bflag);
			 return bflag;
		 }
		
		/* Elements elements = document.getElementsByClass("fav_sendtime");
		 if(CollectionUtils.isNotEmpty(elements)){
			 Element element = elements.get(0);
			 String goodsId = element.attr("id");
			 System.out.println("获取商品id为："+goodsId);
			 if(StringUtils.isNotBlank(goodsId)){
				 goodsId = goodsId.replace("fav_sendtime_", "");
				 Thread.sleep(1000);
				 boolean bflag = biaojiAction(goodsId, uname);
				 System.out.println("商品："+id +" 标记结果："+bflag);
				 return bflag;
			 }
		 }*/
		 
		 
		 
			 return false;
	}
	
	
	public static boolean deleteId(String id,String uname)throws Exception{
		String url ="http://www.dataoke.com/ucenter/save.asp?act=setup_fav_sendtime&gid=530456660905&_="+System.currentTimeMillis();
		HttpRequest httpRequest = HttpRequest.get(url);
		 httpRequest.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/ucenter/favorites_quan.asp");
		 httpRequest.header("Connection", "keep-alive");
		 
		 Cookie[]  cookies = map.get(uname);
		 if(ArrayUtils.isNotEmpty(cookies)){
			 System.out.println("uanem ==="+uname+"  cookis 存在");
			 httpRequest.cookies(cookies);
		 }else{
			 cookies = getObjToFile(uname);
			 httpRequest.cookies(cookies);
		 }
		 
		 HttpResponse response = httpRequest.send().charset("utf-8");
		 String rc = response.bodyText();
		 System.out.println(rc);
		 
		 if(rc.contains("成功")){
			 return true;
		 }
		return false;
	}
	
	
	public static boolean addObjTOfile(Cookie[] obj,String id)throws Exception{
		try{
			File file = new File(fileStoreBase, id);
			if(file.exists()){
				file.delete();
			}
			//序列化对象
	        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
	        out.writeObject(obj);    //写入字面值常量
	        out.close();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
        return true;
      
	}
	
	public static Cookie[] getObjToFile(String id)throws Exception{
		System.out.println("id=="+id+"读取cookid文件");
		File file = new File(fileStoreBase, id);
		if(!file.exists()){
			return null;
		}
		  //反序列化对象
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Cookie[] obj3 = (Cookie[]) in.readObject();    //读取customer对象
        in.close();
        return obj3;
	}
	
	public static void execteDeleteAll(File... files)throws Exception{
		for(File f:files){
			executeDelete( f);
		}
	}
	
	
	public static void execteDeleteAllHttpClient(File... files)throws Exception{
		for(File f:files){
			executeDeleteHttpClient( f);
		}
	}
	
	
	public static void executeDeleteHttpClient(File file)throws Exception{
		List<String> lists=FileUtils.readLines(file);
		for(String s:lists){
			if(StringUtils.isBlank(s)){
				continue;
			}
			count++;
			
			/*if(lists.size() == count){
				System.out.println("执行完毕>>>>>>>>>>>>程序退出");
				System.exit(0);
			}*/
			
			try{
				//proxy  = IpPoolUtil.getHttpHost();
				
				String uname = s.split("\\----")[0].trim();
				String pwd = s.split("\\----")[1].trim();
				System.out.println("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count +"当前 文件名称："+file.getName());
				
				boolean flag = loginHttpClient(uname,pwd);
				System.out.println("u = "+uname + "登陆>>>>>>>>>>>>>"+flag);
				Thread.sleep(1000);
				if(flag){
					//flag = deleteAll(uname,"1");
					//Thread.sleep(1000);
					//flag = deleteAllHttpClient(uname,"0");
					flag = deleteAllHttpClient(uname,"0");
					//flag = tuijianToFile(pid,uname);
					if(flag){
						tuiguangOk+=1;
						System.out.println("删除成功》》》》》》》》》》》》》》》》》》》 uname="+uname+ " 当前删除成功："+tuiguangOk+" 当前ip=="+(proxy==null?"无":proxy.getHostName()));
					}else{
						System.out.println("删除失败》》》》》》》》》》》》》》》》》》uname="+uname);
					}
				}
				//Thread.sleep(1000);
				Thread.sleep(Cmd.getSleepTime());
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				proxy = null;
			}
		}
	}
	
	public static void executeDelete(File file)throws Exception{
		List<String> lists=FileUtils.readLines(file);
		for(String s:lists){
			if(StringUtils.isBlank(s)){
				continue;
			}
			count++;
			String uname = s.split("\\----")[0].trim();
			String pwd = s.split("\\----")[1].trim();
			System.out.println("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count +"当前 文件名称："+file.getName());
			
			boolean flag = login(uname,pwd);
			System.out.println("u = "+uname + "登陆>>>>>>>>>>>>>"+flag);
			Thread.sleep(1000);
			if(flag){
				//flag = deleteAll(uname,"1");
				//Thread.sleep(1000);
				//flag = deleteAllHttpClient(uname,"0");
				flag = deleteAll(uname,"0");
				//flag = tuijianToFile(pid,uname);
				if(flag){
					System.out.println("删除成功》》》》》》》》》》》》》》》》》》》 uname="+uname);
				}else{
					System.out.println("删除失败》》》》》》》》》》》》》》》》》》uname="+uname);
				}
			}
			//Thread.sleep(1000);
			Thread.sleep(Cmd.getSleepTime());
		}
	}
	static String ip = "";
	public static void setRandomIp(HttpRequest httpRequest){
		 //String ip = IpUtils.getRandomIp();
		 System.out.println("获取随机ip>>>>>>>>>>>>>>"+ip);
		 if(StringUtils.isNotBlank(ip)){
			 httpRequest.header("X-FORWARDED-FOR", ip);
			 httpRequest.header("CLIENT-IP", ip);
			 httpRequest.header("VIA", ip);
		 }
	}
	
	public static void execteAll(String[] pid,File... files)throws Exception{
		for(File f:files){
			try{
				execute(pid, f);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				proxy = null;
			}
			
		}
	}
	
	public static boolean goodClickWebClient(String uname,String pid){
		String url = "http://www.dataoke.com/item?id="+pid;
		WebClient webClient = HtmlUnitUtil.create();
		try {
			setCookis(uname, webClient);
			HtmlPage htmlPage = webClient.getPage(url);
			Thread.sleep(3000);
			
			//htmlPage.getFirstByXPath("//*[@class='add-tui J_add_tui']");
			
			htmlPage = (HtmlPage)HtmlUnitUtil.click(htmlPage, "//*[@class='add-tui J_add_tui']");
			Thread.sleep(3000);
			
			System.out.println(htmlPage.asXml());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean readDetailWebClient(String uname,String pid){
		String url = "http://www.dataoke.com/item?id="+pid;
		WebClient webClient = HtmlUnitUtil.create();
		try {
			setCookis(uname, webClient);
			HtmlPage htmlPage = webClient.getPage(url);
			Thread.sleep(1000);
			 if(htmlPage.asXml().contains("商家合作")){
				 return true;
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void setCookis(String uname,WebClient webClient)throws Exception{
		Map<String, String> cookisMap = new HashMap<String, String>();
		//String cookisStr = "random=8696; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492777462; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF; ASPSESSIONIDQSBTQSCS=OHDGKNPACFIHDDFNANILEPKF; token=8f5d2c916cf9a2051dea789e96780d5d; ASPSESSIONIDSQBQSSDT=KNFMMBABCBPCEFDLDGGAGLJO; ASPSESSIONIDQSASQTDT=CDAFIBABKOLMLCOGGMEINGBM";
		for (String str : cookisStr.split("\\;")) {
			cookisMap.put(str.split("\\=")[0].trim(), str.split("\\=")[1]);
		}

		for (Cookie c : getObjToFile(uname)) {
			// System.out.println(c.getName()+"===="+c.getValue());
			// buffer.append(c.getName()).append("=").append(c.getValue()).append("; ");
			cookisMap.put(c.getName().trim(), c.getValue());
		}

		StringBuffer buffer = new StringBuffer();

		for (Entry<String, String> en : cookisMap.entrySet()) {
			buffer.append(en.getKey().trim()).append("=").append(en.getValue())
					.append("; ");
			
			com.gargoylesoftware.htmlunit.util.Cookie cookie = new com.gargoylesoftware.htmlunit.util.Cookie("www.dataoke.com", en.getKey(), en.getValue());
					
					webClient.getCookieManager().addCookie(cookie);
					
		}

		// System.out.println(buffer.toString());

	}

	
	/**
	 * 点击商品详情
	 * @param pid
	 * @param uname
	 * @return
	 */
	public static boolean readDetailHttp(String pid,String uname){
		String url = "http://www.dataoke.com/item?id="+pid;
		try{
			 HttpRequest httpRequest = HttpRequest.get(url).timeout(30000);
			 httpRequest.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			 httpRequest.header("Host", "www.dataoke.com");
			 //httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
			 httpRequest.header("User-Agent", HttpTest.getUserAgent());
			 //httpRequest.header("Referer", "http://www.dataoke.com/item?id="+id);
			 //http://www.dataoke.com/search/?keywords=%E5%A4%8F%E5%AD%A3%E5%A5%B3%E5%A3%AB&xuan=keyword_miaoshu
			 httpRequest.header("Referer", "http://www.dataoke.com/top_tui");
			 httpRequest.header("Upgrade-Insecure-Requests", "1");
			 httpRequest.header("Connection", "keep-alive");
			 httpRequest.header("X-Requested-With", "XMLHttpRequest");
			 setRandomIp(httpRequest);
			 
			 
			 setCookis(uname, httpRequest);
			 
			 HttpResponse response = httpRequest.open().send();
			 response=  response.charset("utf-8");
			 String rc = response.bodyText();
			// System.out.println(rc);
			 
			 if(rc.contains("商家合作")){
				 return true;
			 }else{
				 System.out.println("商品详情返回："+rc);
			 }
			 
			}catch(Exception e){
				e.printStackTrace();
			}
		   
			 
			return false;
	
	}
	
	public static void readExecute(final String pid,final String uname){
		new Thread(){
			public void run() {
				int cout = new Random().nextInt(3);
				for(int i=0;i<cout;i++){
					try {
						boolean flag = readDetailHttp(pid, uname);
						System.out.println("uname :"+uname + " 刷阅读数结果 ："+flag);
						long time = new Random().nextInt(1000)+1000l;
						Thread.sleep(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	
	static int count = 0;
	static int tuiguangOk=0;
	public static void execute(String[] pids,File file)throws Exception{
		//String pid ="2247791";
		//List<String> lists=FileUtils.readLines(new File("G:\\taoke\\第2组500.txt"));
		System.out.println("开始读取文件>>>>>>>>>>"+file.getAbsolutePath());
		List<String> lists=FileUtils.readLines(file);
		for(String s:lists){
			if(StringUtils.isBlank(s)){
				continue;
			}
			count++;
			
			/*if(lists.size() == count){
				System.out.println("执行完毕>>>>>>>>>>>>程序退出");
				System.exit(0);
			}*/
			
			/*if(623>count){
				continue;
			}*/
		
			try{
				proxy  = IpPoolUtil.getHttpHost();
			
					String uname = s.split("\\----")[0].trim();
					String pwd = s.split("\\----")[1].trim();
					System.out.println("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count+" 当前 文件名称："+file.getName());
					
					//boolean flag = login(uname,pwd);
					boolean flag = loginHttpClient(uname,pwd);
					//boolean flag = true;
					System.out.println("u = "+uname + "登陆>>>>>>>>>>>>>"+flag);
					Thread.sleep(200);
					for(String pid:pids){
					    if(flag){
					    	readExecute(pid, uname);
							Thread.sleep(500);
							
							//boolean flagt = tuijian(pid,uname,true);
							//boolean flagt = tuijian(pid,uname);
							boolean flagt = tuijianHttpClient(pid,uname);
							//flag = tuijianToFile(pid,uname);
							//boolean flagt = zhuan(uname,pid);
							//boolean flagt = TestSelenium.execute(pid,uname);
							if(flagt){
								tuiguangOk += 1;
								System.out.println("推广成功》》》》》》》》》》》》》》》》》》》pid="+pid+" uname="+uname +" 当前已成功推广："+tuiguangOk+" 当前ip=="+(proxy==null?"无":proxy.getHostName()));
								//boolean bflag = biaoji(pid, uname);
								//System.out.println("标记结果》》》》》》》》》"+bflag+"》》》》》》》》pid="+pid+" uname="+uname);
								//Thread.sleep(1000);
								//System.out.println("删除>>>>>>>>>>>>>>>>>>推广>>>>>>>>>>>>>>>>"+deleteAll(uname,"0"));
								
							}else{
								System.out.println("推广失败》》》》》》》》》》》》》》》》》》     pid="+pid+"   uname="+uname);
								FileUtils.write(new File("D:\\dataoke\\推荐失败。txt"), s+"\r\n", true);
							}
						}
						Thread.sleep(Cmd.getSleepTime());
						ip = "";
					}
			
           }catch(Exception e){
				e.printStackTrace();
				FileUtils.write(new File("D:\\dataoke\\推荐失败.txt"), s+"\r\n", true);
			}finally {
				proxy=null;
			}
			
			
		}
	}
	
	
	/*public static boolean tuijianToFile(String id,String uname)throws Exception{
		String url ="http://www.dataoke.com/handle_popularize";
		 HttpRequest httpRequest = HttpRequest.post(url);
		 httpRequest.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/item?id="+id);
		 httpRequest.header("Upgrade-Insecure-Requests", "1");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 
		 Cookie[]  cookies = getObjToFile(uname);
		 if(ArrayUtils.isNotEmpty(cookies)){
			 System.out.println("uanem ==="+uname+"  cookis 存在");
			 httpRequest.cookies(cookies);
		 }
		 //act=add_quan&id=2231931
		 httpRequest.form("act", "add_quan");
		 httpRequest.form("id", id);
		 
		 HttpResponse response = httpRequest.send();
		 String rc = response.bodyText();
		 System.out.println("tuijianToFile===="+rc);
		 
		 if(rc.equalsIgnoreCase("ok")){
			 return true;
		 }
		 
		return false;
	}*/
	
	public static boolean deleteById(String id,String uname)throws Exception{
		String url ="http://www.dataoke.com/ucenter/save.asp?act=del_my_quan&id="+id;
		HttpRequest httpRequest = HttpRequest.get(url);
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("Content-Type", "application/x-www-form-urlencoded");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/ucenter/favorites_quan.asp");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 //httpRequest.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		 //httpRequest.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		// httpRequest.header("Accept-Encoding", "gzip, deflate");
		 
		
			
		 
		Map<String, String> cookisMap = new HashMap<String, String>();
		String cookisStr = "random=8696; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492777462; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF; ASPSESSIONIDQSBTQSCS=OHDGKNPACFIHDDFNANILEPKF; token=8f5d2c916cf9a2051dea789e96780d5d; ASPSESSIONIDSQBQSSDT=KNFMMBABCBPCEFDLDGGAGLJO; ASPSESSIONIDQSASQTDT=CDAFIBABKOLMLCOGGMEINGBM";
		for(String str:cookisStr.split("\\;")){
			cookisMap.put(str.split("\\=")[0],str.split("\\=")[1]);
		}
		
          
		 
		 for(Cookie c:getObjToFile(uname)){
				// System.out.println(c.getName()+"===="+c.getValue());
				// buffer.append(c.getName()).append("=").append(c.getValue()).append("; ");
			 cookisMap.put(c.getName(), c.getValue());
			 }
		 
		 StringBuffer buffer = new StringBuffer();
		 
		 for(Entry<String, String> en:cookisMap.entrySet()){
			 buffer.append( en.getKey()).append("=").append(en.getValue()).append("; ");
		 }
		 
		 System.out.println(buffer.toString());
			
		httpRequest.header("Cookie",buffer.toString());	
			
		//httpRequest.header("Cookie","random=8696; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492777462; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF; ASPSESSIONIDQSBTQSCS=OHDGKNPACFIHDDFNANILEPKF; token=8f5d2c916cf9a2051dea789e96780d5d; ASPSESSIONIDSQBQSSDT=KNFMMBABCBPCEFDLDGGAGLJO; ASPSESSIONIDQSASQTDT=CDAFIBABKOLMLCOGGMEINGBM");
		 
			
			
		 /*Cookie[]  cookies = map.get(uname);
		 if(ArrayUtils.isNotEmpty(cookies)){
			 System.out.println("uanem ==="+uname+"  cookis 存在");
			 httpRequest.cookies(cookies);
		 }else{
			 cookies = getObjToFile(uname);
			 httpRequest.cookies(cookies);
		 }*/
		 
		 
		 HttpResponse response = httpRequest.send();
		 String rc = response.bodyText();
		 System.out.println(rc);
		
		
		//String rc = "";
		 
		 
		 if(rc.equalsIgnoreCase("ok")){
			 System.out.println("id=="+id+" 删除成功>>>>>>>>>>>>>>>");
			 return true;
		 }
		 
		return false;
	}
	
	public static boolean deleteAllHttpClient(String uname,String type)throws Exception{
		String url ="http://www.dataoke.com/ucenter/all_del_quan.asp?act=del";
		
		HttpPost httpRequest = new HttpPost(url);
		 httpRequest.setHeader("Host", "www.dataoke.com");
		 httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
		 httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.setHeader("Referer", "http://www.dataoke.com/ucenter/all_del_quan.asp");
		 httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		 httpRequest.setHeader("Connection", "keep-alive");
		 httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		 httpRequest.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		 httpRequest.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		// httpRequest.header("Accept-Encoding", "gzip, deflate");
		 httpRequest.setHeader("User-Agent", HttpTest.getUserAgent());
		 
		 
		setCookis(uname, httpRequest);
		 
		
		    /*HttpClientUtils httpClientUtils = new HttpClientUtils();
		 
		    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("leibie", type));  
	        nvps.add(new BasicNameValuePair("zh_que_bt", "%C8%B7%C8%CF%C9%BE%B3%FD"));  
	        httpRequest.setEntity(new UrlEncodedFormEntity(nvps));  */
		    //0 未推广 1已推广
		 
		 
		 /*HttpResponse response = httpRequest.send();
		 response.charset("gb2312");
		 String rc = response.bodyText();*/
		// System.out.println(rc);
		 //String rc =  httpClientUtils.getContentByUrl(proxy, httpRequest, 10000,"gb2312");
		 String rc =  HttpClientUtil.sendPostRequest(httpRequest, "leibie="+type+"&zh_que_bt=%C8%B7%C8%CF%C9%BE%B3%FD",true,"gb2312","gb2312",proxy,null);
		 
		 if(rc.contains("删除成功")){
			 System.out.println("id=="+uname+" 删除成功>>>>>>>>>>>>>>>");
			 return true;
		 }
		 
		return false;
	}
	
	public static boolean deleteAll(String uname,String type)throws Exception{
		String url ="http://www.dataoke.com/ucenter/all_del_quan.asp?act=del";
		HttpRequest httpRequest = HttpRequest.post(url);
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("Content-Type", "application/x-www-form-urlencoded");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/ucenter/all_del_quan.asp");
		 httpRequest.header("Upgrade-Insecure-Requests", "1");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 httpRequest.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		 httpRequest.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		// httpRequest.header("Accept-Encoding", "gzip, deflate");
		 
		setCookis(uname, httpRequest);
		 
		 //act=add_quan&id=2231931
		 //httpRequest.form("leibie", "0");
		//0 未推广 1已推广
		httpRequest.form("leibie", type);
		 httpRequest.form("zh_que_bt", "%C8%B7%C8%CF%C9%BE%B3%FD");
		// httpRequest.form("zh_que_bt", "È·ÈÏÉ¾³ý");
		 
		 
		 HttpResponse response = httpRequest.send();
		 response.charset("gb2312");
		 String rc = response.bodyText();
		// System.out.println(rc);
		 
		 
		 if(rc.contains("删除成功")){
			 System.out.println("id=="+uname+" 删除成功>>>>>>>>>>>>>>>");
			 return true;
		 }
		 
		return false;
	}
	
	public static boolean tuijianHttpClient(String id,String uname)throws Exception{
		try{
		String url ="http://www.dataoke.com/handle_popularize";
		HttpPost httpRequest = new HttpPost(url);
		 httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 httpRequest.setHeader("Host", "www.dataoke.com");
		 httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.setHeader("Referer", "http://www.dataoke.com/item?id="+id);
		 httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		 httpRequest.setHeader("Connection", "keep-alive");
		 httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		 httpRequest.setHeader("User-Agent", HttpTest.getUserAgent());
		 
		 setCookis(uname, httpRequest);
/*
		   HttpClientUtils httpClientUtils = new HttpClientUtils();
		 
		    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("act", "add_quan"));  
	        nvps.add(new BasicNameValuePair("id", id));  
	        httpRequest.setEntity(new UrlEncodedFormEntity(nvps));  */
	        
		// HttpHost proxy = IpPoolUtil.getHttpHost();
		 //String rc =  httpClientUtils.getContentByUrl(proxy, httpRequest, 10000);
		 
		 String rc =  HttpClientUtil.sendPostRequest(httpRequest, "act=add_quan&id="+id,true,null,null,proxy,null);
         //System.out.println("str:"+str);
		 
		 if(rc != null && rc.equalsIgnoreCase("ok")){
			 return true;
		 }else{
			 System.out.println("推荐失败返回>>>>>>>>>>>>>>>"+rc);
			 //推荐失败返回>>>>>>>>>>>>>>>is_in
			 if("is_in".equalsIgnoreCase(rc)){
				/* System.out.println("当前已取消推荐>>>>>>>>>>再次推荐");
				 Thread.sleep(2000);
				 return tuijianHttpClient(id, uname);*/
			 }
		 }
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		return false;
	}

	
	public static boolean taoTokenHttpClient(String id,String uname)throws Exception{
		try{
		String url ="http://www.dataoke.com/dtpwd";
		HttpPost httpRequest = new HttpPost(url);
		 httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 httpRequest.setHeader("Host", "www.dataoke.com");
		 httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.setHeader("Referer", "http://www.dataoke.com/item?id="+id);
		 httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		 httpRequest.setHeader("Connection", "keep-alive");
		 httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		 httpRequest.setHeader("User-Agent", HttpTest.getUserAgent());
		 
		 setCookis(uname, httpRequest);
/*
		   HttpClientUtils httpClientUtils = new HttpClientUtils();
		 
		    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("act", "add_quan"));  
	        nvps.add(new BasicNameValuePair("id", id));  
	        httpRequest.setEntity(new UrlEncodedFormEntity(nvps));  */
	        
		// HttpHost proxy = IpPoolUtil.getHttpHost();
		 //String rc =  httpClientUtils.getContentByUrl(proxy, httpRequest, 10000);
		 
		 String rc =  HttpClientUtil.sendPostRequest(httpRequest, "type=2&gid="+id,true,null,null,proxy,null);
		 //System.out.println("str:"+rc);
         JSONObject jsonObject = JSONObject.parseObject(rc);
         String status = jsonObject.getString("status");
		 if("1".equals(status)){
			 httpRequest.setURI(new URI("http://www.dataoke.com/detailtpl"));
			 rc =  HttpClientUtil.sendPostRequest(httpRequest, "gid="+id,true,null,null,proxy,null);
			 //System.out.println("str:"+rc);
			 jsonObject = JSONObject.parseObject(rc);
	         status = jsonObject.getString("status");
			 if("1".equals(status)){
				 return true;	 
			 }else{
				 System.out.println("str:"+rc);
			 }
			 
		 }else{
			 System.out.println("str>>>>>>>>>>>>>>>"+rc);
		 }
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		return false;
	}

	
	
	public static boolean updatePwdHttpClient(String uname,String pwd,String newPwd)throws Exception{
		try{
		String url ="http://www.dataoke.com/ucenter/pwd.asp?act=edit";
		HttpPost httpRequest = new HttpPost(url);
		 httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 httpRequest.setHeader("Host", "www.dataoke.com");
		 httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.setHeader("Referer", "http://www.dataoke.com/ucenter/pwd.asp");
		 httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		 httpRequest.setHeader("Connection", "keep-alive");
		 httpRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		 httpRequest.setHeader("User-Agent", HttpTest.getUserAgent());
		 
		 setCookis(uname, httpRequest);
/*
		   HttpClientUtils httpClientUtils = new HttpClientUtils();
		 
		    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("act", "add_quan"));  
	        nvps.add(new BasicNameValuePair("id", id));  
	        httpRequest.setEntity(new UrlEncodedFormEntity(nvps));  */
	        
		// HttpHost proxy = IpPoolUtil.getHttpHost();
		 //String rc =  httpClientUtils.getContentByUrl(proxy, httpRequest, 10000);
		 //ymima=1qaz2wsx1&xmima=1qaz2wsx&qmima=1qaz2wsx&Submit=+%CC%E1+%BD%BB+
		 
		 //String param = "ymima="+pwd+"&"+"xmima="+newPwd+"&"+"qmima="+newPwd+"&"+"Submit= ´´ ½¨ ";
		 
		 String param = "ymima="+pwd+"&"+"xmima="+newPwd+"&"+"qmima="+newPwd+"&"+"Submit=+%CC%E1+%BD%BB+";
		 String rc =  HttpClientUtil.sendPostRequest(httpRequest, param,true,"gb2312","gb2312",proxy,null);
         //System.out.println("str:"+str);
		 
		 
		 if(rc.contains("密码")){
			 return true;
		 }else{
			 System.out.println("修改密码失败返回>>>>>>>>>>>>>>>"+rc);
		 }
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		return false;
	}

	
	public static boolean tuijian(String id,String uname)throws Exception{
		return tuijian(id, uname, false);
	}
	
	public static boolean tuijian(String id,String uname,boolean isProxy)throws Exception{
		try{
		 String url ="http://www.dataoke.com/handle_popularize";
		 HttpRequest httpRequest = null;
		 if(isProxy){
			 httpRequest= HttpRequest.post(url).open(getSocketHttpConnectionProvider()).timeout(30000);
		 }else{
			 httpRequest= HttpRequest.post(url).timeout(30000);
		 }
		
		
		 httpRequest.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 httpRequest.header("Host", "www.dataoke.com");
		 //httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/item?id="+id);
		 httpRequest.header("Upgrade-Insecure-Requests", "1");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");

		 
		 httpRequest.header("User-Agent", HttpTest.getUserAgent());
			
		 
		 
		  setRandomIp(httpRequest);
		 
		/* Cookie[]  cookies = map.get(uname);
		 if(ArrayUtils.isNotEmpty(cookies)){
			 System.out.println("uanem ==="+uname+"  cookis 存在");
			 httpRequest.cookies(cookies);
		 }else{
			 cookies = getObjToFile(uname);
			 httpRequest.cookies(cookies);
		 }*/
		  
		
		 setCookis(uname, httpRequest);
		
		 //httpRequest.header("Cookie", "UM_distinctid=15b80b0952ee2-0a7b58a877a4498-47534130-15f900-15b80b0952f128; CNZZDATA1257179126=1533751114-1492507982-http%253A%252F%252Fwww.dataoke.com%252F%7C1494984030; upe=c3ac5f62; not-any-more=1; userid=540111; user_email=13411679603; user%5Femail=13411679603; e88a8013345a8f05461081898691958c=aef7abed45989f76675cf8c4147603eee7651f15a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22540111%22%3Bi%3A1%3Bs%3A11%3A%2213411679603%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDQQBQSSCT=JCBFAJNBGJBJIFHKBIMFGPMI; dtk_web=2v6neq4vk4n7k6k41o516egct0; token=d42217d39115f757ac2cfa11ce086425; random=2053; ASPSESSIONIDAQCSCTDR=KLGFMINBPAIADKDCBELHBEID");
		  
		 //act=add_quan&id=2231931
		 httpRequest.form("act", "add_quan");
		 httpRequest.form("id", id);
		 
		 HttpResponse response = httpRequest.send();
		 String rc = response.bodyText();
		 System.out.println(rc);
		 
		 if(rc.equalsIgnoreCase("ok")){
			 return true;
		 }
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		return false;
	}
	static  HttpHost proxy = null;
		
	public static boolean loginHttpClient(String uname,String pwd)throws Exception{
		System.out.println("开始查找cookis文件是否存在>>>>>>>>>>>>>>>>>>");
		Cookie[]  cookies  = null;
		
		
		 cookies = getObjToFile(uname);
		if(ArrayUtils.isNotEmpty(cookies)){
			System.out.println("cookis文件存在>>>>>>>>>>>>>>>>>>返回登录成功");
			return true;
		}
		
		
		 System.out.println("用户开始登陆："+uname);
		 String baseURI = "http://www.dataoke.com/loginApi";
		 HttpPost httpRequest = new HttpPost(baseURI);
		 httpRequest.setHeader("Content-Type", "application/json");
		 httpRequest.setHeader("Host", "www.dataoke.com");
		 httpRequest.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		 httpRequest.setHeader("User-Agent", HttpTest.getUserAgent());
		 httpRequest.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		 httpRequest.setHeader("Referer", "http://www.dataoke.com/login");
		 httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		 httpRequest.setHeader("Connection", "keep-alive");
		 
		// setCookis(uname, httpRequest);
		
		 HttpClientUtils httpClientUtils = new HttpClientUtils();
		 
		    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("username", uname));  
	        nvps.add(new BasicNameValuePair("password", pwd));  
	        nvps.add(new BasicNameValuePair("vc", ""));  
	        nvps.add(new BasicNameValuePair("ref", ""));  
	        httpRequest.setEntity(new UrlEncodedFormEntity(nvps));  
		 
	       StringEntity stringEntity = new StringEntity("username="+uname+"&"+"password="+pwd+"&vc="+"&"+"ref=");
	        
	        
	        
	        //httpRequest.setEntity(new UrlEncodedFormEntity(nvps));  
	        httpRequest.setEntity(stringEntity);  
			
	        
	      //  HttpHost proxy = IpPoolUtil.getHttpHost();
			//cookies =  httpClientUtils.getContentByUrlCookis(proxy, httpRequest, 10000);
		      List<Cookie> list = new ArrayList<Cookie>();
	          String str =  HttpClientUtil.sendPostRequest(httpRequest, "username="+uname+"&"+"password="+pwd+"&vc="+"&"+"ref=",true,null,null,proxy,list);
	          System.out.println("str:"+str);
	          
	          JSONObject jsonObject = JSONObject.parseObject(str);
	          String status = jsonObject.getString("status");
	          
	          cookies =  list.toArray(new Cookie[]{});
		        
		  
		/* for(Cookie c:cookies){
			 System.out.println("rp=="+c.getName()+"===="+c.getValue());
		 }*/
		 
		/* 
		
		 
	    System.out.println("================================="+httpRequest.header("Cookie"));*/
		 
		 
		 //System.out.println("response.headers()");
		
		// System.out.println(response.headers());
		 if("1".equals(status)){
			 System.out.println("登录成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+uname);
			 map.put(uname, cookies);
			 addObjTOfile(cookies, uname);
			 return true;
		 }
		 return false;
		 
		
	}
	
	public static boolean login(String uname,String pwd)throws Exception{
		return login(uname, pwd, false);
	}
	
	public static boolean login(String uname,String pwd,boolean isProxy)throws Exception{
		System.out.println("开始查找cookis文件是否存在>>>>>>>>>>>>>>>>>>");
		Cookie[]  cookies  = null;
		
		
		 cookies = getObjToFile(uname);
		if(ArrayUtils.isNotEmpty(cookies)){
			System.out.println("cookis文件存在>>>>>>>>>>>>>>>>>>返回登录成功");
			return true;
		}
		
		
		System.out.println("用户开始登陆："+uname);
		 String baseURI = "http://www.dataoke.com/loginApi";
		 //HttpRequest httpRequest = HttpRequest.post(baseURI).timeout(20000);
		 HttpRequest httpRequest = null;
		 if(isProxy){
			 httpRequest = HttpRequest.post(baseURI).open(getSocketHttpConnectionProvider()).timeout(20000);
		 }else{
			 httpRequest = HttpRequest.post(baseURI).timeout(20000);
		 }
		 
		 httpRequest.header("Content-Type", "application/json");
		 httpRequest.header("Host", "www.dataoke.com");
		 //httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("User-Agent", HttpTest.getUserAgent());
		 httpRequest.header("Referer", "http://www.dataoke.com/login");
		 httpRequest.header("Upgrade-Insecure-Requests", "1");
		 httpRequest.header("Connection", "application/json");
		// httpRequest.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		// httpRequest.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		 
		 //username=15201733860&password=1qaz2wsx&vc=&ref=
		 //Cookie	random=9069; UM_distinctid=15b809dffcb9-00e25cf7281db-47534130-15f900-15b809dffcc172; CNZZDATA1257179126=2114245438-1492507982-http%253A%252F%252Fwww.dataoke.com%252F%7C1492507982; dtk_web=2prh3gjms7ut0g2r3fnm1oiap2; upe=98bf65d2; ASPSESSIONIDQSBSQSDT=CGNGMEPCJLPEEEMKOKKBJNCC; ASPSESSIONIDSSDTRTCS=HEDHLEPCNOOCHALCBPCKPEFN; token=15e519c05e03cdc8d3fb524de429edfa; ASPSESSIONIDQSBTQSCT=ADIDAGPCLLIMIEMMDGINPMCL; userid=538278; user_email=17097636339; user%5Femail=17097636339; e88a8013345a8f05461081898691958c=c33d184d1d3e5173a78fcd3eb25d0ed092bb3d58a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22538278%22%3Bi%3A1%3Bs%3A11%3A%2217097636339%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D
		 
		//httpRequest.header("Cookie", "random=9069; UM_distinctid=15b809dffcb9-00e25cf7281db-47534130-15f900-15b809dffcc172; CNZZDATA1257179126=2114245438-1492507982-http%253A%252F%252Fwww.dataoke.com%252F%7C1492507982; dtk_web=2prh3gjms7ut0g2r3fnm1oiap2; upe=98bf65d2; ASPSESSIONIDQSBSQSDT=CGNGMEPCJLPEEEMKOKKBJNCC; ASPSESSIONIDSSDTRTCS=HEDHLEPCNOOCHALCBPCKPEFN; token=15e519c05e03cdc8d3fb524de429edfa; ASPSESSIONIDQSBTQSCT=ADIDAGPCLLIMIEMMDGINPMCL; userid=538278; user_email=17097636339; user%5Femail=17097636339; e88a8013345a8f05461081898691958c=c33d184d1d3e5173a78fcd3eb25d0ed092bb3d58a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22538278%22%3Bi%3A1%3Bs%3A11%3A%2217097636339%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D");
		 
		 
		 //httpRequest.header("Cookie", "random=8337; ASPSESSIONIDSQCRRSDT=PMFGMKPANNDIPLFIEAGFOJHD; dtk_web=mgbpf1uvaohssnvee7m02u1lt7; UM_distinctid=15b906fc3d99a-0ca45cd09b0c9d-12616a4a-1fa400-15b906fc3db105; CNZZDATA1257179126=1538129784-1492772062-http%253A%252F%252Fwww.dataoke.com%252F%7C1492773526; userid=537000; user_email=15201733860; user%5Femail=15201733860; upe=537e2926; e88a8013345a8f05461081898691958c=834b4337570611838d9b6989521575fb85ae30b6a%3A4%3A%7Bi%3A0%3Bs%3A6%3A%22537000%22%3Bi%3A1%3Bs%3A11%3A%2215201733860%22%3Bi%3A2%3Bi%3A2592000%3Bi%3A3%3Ba%3A0%3A%7B%7D%7D; ASPSESSIONIDSSBQSTCT=ICEPOLPACLKKGLDMHNNFFFIA; ASPSESSIONIDQSCRRTDS=CACEBLPAJEAMCMJMGPHFAEOB; ASPSESSIONIDSQCTQTCS=HNCMFMPAEKHOCBIEFGDHDDLH; token=41d5a8a7ada70ee5b3840f0b84ef60d1; ASPSESSIONIDQQCTRTCS=OMKMBNPAOFLEBJBEGOKDNIIF");
		 
		 setCookis(uname, httpRequest);
		
		 httpRequest.form("username", uname);
		 httpRequest.form("password", pwd);
		 httpRequest.form("vc", "");
		 httpRequest.form("ref", "");
		 
		 HttpResponse response = httpRequest.send();
		 String rc = response.bodyText();
		 System.out.println(rc);
		 
		 
		  cookies = response.cookies();
		
		  
		/* for(Cookie c:cookies){
			 System.out.println("rp=="+c.getName()+"===="+c.getValue());
		 }
		 
		 
		
		 
	    System.out.println("================================="+httpRequest.header("Cookie"));
		 
		 
		 System.out.println("response.headers()"+response.headers("set-cookie"));
		
		System.out.println(response.headers());
		*/
		 
		 
		 
		 if(rc.contains("1")){
			 map.put(uname, cookies);
			 addObjTOfile(cookies, uname);
			 return true;
		 }
		 
		 return false;
		 
		
	}

}
