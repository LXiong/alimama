package zhuanzhuan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import util.HtmlUnitUtil;

public class TaobaoUtils {
	
	public static void main(String[] args) throws Exception{
		String url = "https://detail.tmall.com/item.htm?id=530996956269&scm=1007.12807.73594.100200300000002&pvid=1d4b444e-5da2-4929-9546-9d9e85544d57";
		/*HttpGet httpGet = new HttpGet(url);
	    String rs = HttpClientUtil.sendGetRequest(httpGet, "utf-8", null);
	   Document document =  Jsoup.parse(rs);
	   
	   System.out.println(document.getElementsByClass("skin-box-bd clear-fix").html());*/
	   
	  // System.out.println(rs);
	
	
	   WebClient client = HtmlUnitUtil.create();
	   HtmlPage htmlPage = client.getPage(url);
	   
	   
	   
	   Thread.sleep(5000);
	   
	  // System.out.println(htmlPage.asXml());
	   
	   
           Document document =  Jsoup.parse(htmlPage.asXml());
	   
	  // System.out.println(document.getElementsByClass("skin-box-bd clear-fix").html());
           System.out.println(document.getElementById("shop15817541355").html());
     	  
	   
	  // HtmlElement element =  htmlPage.getFirstByXPath("//div[@class='skin-box-bd clear-fix']");
	 // System.out.println(element.asXml());
	   
	}

}
