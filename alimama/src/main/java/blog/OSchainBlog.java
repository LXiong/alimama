package blog;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import util.HttpClientUtil;
import util.SeleniumUtil;

public class OSchainBlog {
	
	static File fileDir=new File("E:\\javaeye"); 
	
	static String baseURL="http://m635674608.iteye.com";
	
	public static void main(String[] args)throws Exception {
		
	          send("vvvvvvvvvv", "%3Cp%3Eweqrqerqer%3C%2Fp%3E%0A&");
	    
	}
	static  String ck="_user_behavior_=c8fb9227-9234-40a7-b054-e4ef95a29128; oscid=Qgdg8wRz2OcsdD4fp3FCcYwgebG1JwCmSPlVjKxReUbSNKmwdJT1KHu7q3xEwVlbKnTr%2FnXJ0phWKE%2Byr1pxcwGqY1FTfsDxg4t8O2Dr9el%2BbZSWQHtE6Sn7okJzEcTRkJ488fKQSm0ApT7CsKI%2BlwUb4rXZ3uJo1j4%2BJnWf3o46ZSBYvrRDow%3D%3D; aliyungf_tc=AQAAAMFLqBqcDw4AMzVi2hCFjQ+saSvA; Hm_lvt_a411c4d1664dd70048ee98afe7b28f0b=1512816806,1512817615,1512819582,1512826071; Hm_lpvt_a411c4d1664dd70048ee98afe7b28f0b=1512826487";
	
	
	public static void send(String title,String content){
		String draft = getdraft(title, content);
		String sendData="draft="+draft+"&user_code=wu7fxTLkG9vDXBEn2b0Sq5lt8EyAjQlSvDay9EQd&id=&catalog=3427650&title=aaaaaaaaaaaaaccca&content_type=4&tags=&classification=428602&type=1&origin_url=&privacy=0&deny_comment=0&as_top=0&isRecommend=on&abstracts=&content=%3Cp%3EJava%E4%BB%A3%E7%A0%81%3C%2Fp%3E%0A%0A%3Cp%3E%3Cembed+allowscriptaccess%3D%22always%22+height%3D%2215%22+pluginspage%3D%22http%3A%2F%2Fwww.macromedia.com%2Fgo%2Fgetflashplayer%22+quality%3D%22high%22+src%3D%22http%3A%2F%2Fm635674608.iteye.com%2Fjavascripts%2Fsyntaxhighlighter%2Fclipboard_new.swf%22+type%3D%22application%2Fx-shockwave-flash%22+width%3D%2214%22+wmode%3D%22transparent%22%3E%3C%2Fembed%3E%3C%2Fp%3E%0A%0A%3Cp%3E%26nbsp%3B%3Ca+href%3D%22javascript%3Avoid()%22+onclick%3D%22code_favorites_do_favorite(this)%3Breturn+false%3B%22%3E%3Cimg+alt%3D%22%E6%94%B6%E8%97%8F%E4%BB%A3%E7%A0%81%22+src%3D%22http%3A%2F%2Fm635674608.iteye.com%2Fimages%2Ficon_star.png%22+%2F%3E%3C%2Fa%3E%3C%2Fp%3E%0A%0A%3Col%3E%0A%09%3Cli%3E%26lt%3Bpre%26nbsp%3Bclass%3D%26quot%3Bjava%26quot%3B%26nbsp%3Bname%3D%26quot%3Bcode%26quot%3B%26gt%3B%26nbsp%3B%26lt%3B%2Fpre%26gt%3B%26nbsp%3B%26nbsp%3B%3C%2Fli%3E%0A%3C%2Fol%3E%0A%0A%3Cp%3E%26nbsp%3B%26nbsp%3B%26nbsp%3B+%E9%80%9A%E8%BF%87SecurityContextHolder.getContext()%E8%8E%B7%E5%BE%97SecurityContext%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B+%E6%80%BB%E6%8E%A5%E5%8F%A3SecurityContextHolderStrategy%3C%2Fp%3E%0A%0A%3Cp%3E%3Cimg+alt%3D%22%22+src%3D%22http%3A%2F%2Fdl.iteye.com%2Fupload%2Fattachment%2F0065%2F7123%2Fb01eea45-c57a-335d-970c-2ef67ea65c58.png%22+%2F%3E%3C%2Fp%3E%0A%0A%3Cp%3Eprivate+static+void+initialize()+%7B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+if+((strategyName+%3D%3D+null)+%7C%7C+%26quot%3B%26quot%3B.equals(strategyName))+%7B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+%2F%2F+Set+default%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+strategyName+%3D+MODE_THREADLOCAL%3B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+%7D%3C%2Fp%3E%0A%0A%3Cp%3E%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+if+(strategyName.equals(MODE_THREADLOCAL))+%7B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+strategy+%3D+new+ThreadLocalSecurityContextHolderStrategy()%3B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+%7D+else+if+(strategyName.equals(MODE_INHERITABLETHREADLOCAL))+%7B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+strategy+%3D+new+InheritableThreadLocalSecurityContextHolderStrategy()%3B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+%7D+else+if+(strategyName.equals(MODE_GLOBAL))+%7B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+strategy+%3D+new+GlobalSecurityContextHolderStrategy()%3B%3Cbr+%2F%3E%0A%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B%26nbsp%3B+%7D+else+%7B%3C%2Fp%3E%0A%0A%3Cp%3E%26nbsp%3B%3C%2Fp%3E%0A";
		String reqURL="https://my.oschina.net/action/blog/save";
		HttpPost httpPost   = new HttpPost(reqURL);
		httpPost.setHeader("Cookie",ck);
				httpPost.setHeader("Referer","https://my.oschina.net/xiaominmin/blog/edit");
	    httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
	    httpPost.setHeader("Host","my.oschina.net");
		String str = HttpClientUtil.sendPostRequest(httpPost, sendData, false, "UTF-8","UTF-8");
	    System.out.println(str);
	    
	}
	
	
	
	
	
	public static String getdraft(String title,String content){
		
		   
		    //draft=819110&user_code=wu7fxTLkG9vDXBEn2b0Sq5lt8EyAjQlSvDay9EQd&id=&catalog=3427650&title=213123aa&content_type=4&tags=&classification=428609&type=1&origin_url=&privacy=0&deny_comment=0&as_top=0&isRecommend=on&abstracts=&content=%3Cp%3E123123123123123123213123123213213123121312323%3C%2Fp%3E%0A&temp=1512827196859
		   String sendData="draft=0&user_code=wu7fxTLkG9vDXBEn2b0Sq5lt8EyAjQlSvDay9EQd&id=&catalog=3427650&title="+title+"&content_type=4&tags=&classification=428609&type=1&origin_url=&privacy=0&deny_comment=0&as_top=0&isRecommend=on&abstracts=&content="+content;
			
			
			
			String reqURL="https://my.oschina.net/action/blog/save?save_as_draft=1";
		
			
			HttpPost httpPost   = new HttpPost(reqURL);
			httpPost.setHeader("Cookie",ck);
					
					httpPost.setHeader("Referer","https://my.oschina.net/xiaominmin/blog/edit");
		    httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
		    httpPost.setHeader("Host","my.oschina.net");
		    
			
			String str = HttpClientUtil.sendPostRequest(httpPost, sendData, false, "UTF-8","UTF-8");
		    System.out.println("draft:"+str);
		    JSONObject jsonObject =JSON.parseObject(str);
		    return jsonObject.getString("draft");
		    
	}
	
	
	public static void tets()throws Exception {
		/*WebDriver driver = SeleniumUtil.initChromeDriverLocal();
	    driver.get("https://my.oschina.net/xiaominmin/blog/edit");*/
	    
	    
	    WebDriver firefoxDriver = SeleniumUtil.initFirefoxDriver();
	    
	    webGet(firefoxDriver, "http://m635674608.iteye.com/");
	    List<WebElement> elements = firefoxDriver.findElements(By.className("blog_main"));  
	    System.out.println("elements:"+elements.size());
	  /*  for(WebElement e:elements){
	    	String title = e.findElement(By.className("blog_title")).getText();
	        System.out.println("titile==="+title);
	    }
		*/
	    
	    
	    List<String> pageURLList = new ArrayList<String>();
	    
		Document document = Jsoup.parse(firefoxDriver.getPageSource());
		
		  for(Element e:document.select(".blog_main")){
		    	String title = e.select(".blog_title").text();
		    	
		        System.out.println("titile==="+title);
		        
		        String href= e.select(".blog_title").select("a").get(0).attr("href");
		        System.out.println("============"+baseURL+href);  
		        
		        pageURLList.add(baseURL+href);
		  }
		  
		  
		  for(String url:pageURLList){
			  webGet(firefoxDriver,url);
			  document = Jsoup.parse(firefoxDriver.getPageSource());
			  String id = url.replace(baseURL, "").replace("/blog/", "");
			  //System.out.println("id==="+id);
			  System.out.println("========"+document.text());
			  
			  
			  FileUtils.write(new File(fileDir,""+id), document.toString());
		  }
		  
	}
	
	public static void webGet(WebDriver webDriver, String url) {
		/*try{
			String title = webDriver.getTitle();
		}catch(Exception e){
			System.out.println("浏览器关闭，程序退出");
			System.exit(0);
		}*/
		
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		try {
			webDriver.get(url);
		} catch (Exception e) {
			
			  try { Thread.sleep(100); } catch (InterruptedException e1) {
			  e1.printStackTrace(); } JavascriptExecutor js =
			  (JavascriptExecutor) webDriver;
			  js.executeScript("window.stop();");
			 System.out.println("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
			 
		}
	}

}
