package zhuanzhuan;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import util.HtmlUnitUtil;
import util.SeleniumUtil;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TaobaoUtils {
	
	public static void main(String[] args) throws Exception{
		String url = "https://detail.tmall.com/item.htm?id=530996956269&scm=1007.12807.73594.100200300000002&pvid=1d4b444e-5da2-4929-9546-9d9e85544d57";
		
		//url="https://item.taobao.com/item.htm?spm=a219t.7900221/10.1998910419.d30ccd691.uPkZic&id=43022193676";
		File file = new File("D:\\dataoke\\img");
		TbSpPage page = execute(url,file);
		
		System.out.println("商品标题："+page.getTbGoodsTitle());
		System.out.println("商品价格："+page.getTaoBaoprice());
		System.out.println("商品主图大小："+page.getTbGoodsImgFiles().size());
	}
	
	public static TbSpPage execute(String url,File outBase)throws Exception{
		if(url.contains("taobao")){
			return parsTaoBao(url, outBase);
		}else{
			return parsTianmao(url, outBase);
		}
	}
	
	public static TbSpPage parsTaoBao(String url,File outBase)throws Exception{
		//String url = "https://detail.tmall.com/item.htm?id=530996956269&scm=1007.12807.73594.100200300000002&pvid=1d4b444e-5da2-4929-9546-9d9e85544d57";
		WebDriver webDriver = SeleniumUtil.initChromeDriver();
		TbSpPage pageBean = new TbSpPage();
		try{
		webDriver.get(url);
		Thread.sleep(5000);
		
		String id = ParserUtils.idParser(url);
		File out = new File(outBase, id);
		if(!out.exists()){
			out.mkdirs();
		}
		/*WebElement webelement =webDriver.findElement(By.id("J_DivItemDesc"));
		
		System.out.println(webelement.getText());*/
		
		Document document =  Jsoup.parse(webDriver.getPageSource());
		
		String tb_main_title =document.select(".tb-main-title").text();   // webDriver.executeScript("return g_config.idata.item.title;")+""; //document.select(".tb-main-title").text();   //tbGoodsTitle
		pageBean.setTbGoodsTitle(tb_main_title);
		
		
		String tm_price =document.select("#J_PromoPriceNum").text();   
		pageBean.setTaoBaoprice(tm_price);
		
		
		//System.out.println(document.html());
		//产品详情图
		Elements DivItemDesc = null;
			//DivItemDesc = document.select("#J_DivItemDesc").select("img");
		DivItemDesc = document.select("#detail").select("img");
		
		
		System.out.println(DivItemDesc.size());
		
		for (Element element : DivItemDesc) {
			System.out.println(element.html()); 
			String data_ks_lazyload = element.attr("src");
			String data_src = element.attr("data-src");
			if(StringUtils.isNotBlank(data_ks_lazyload) && StringUtils.isNotBlank(data_src)){
				//String path= ParserUtils.imgPathParser(imgSrc + "   "+ data_ks_lazyload);
				String path= "https:"+data_src.replace("50x50", "400x400");
				System.out.println("path=="+path);
				String baseName = FilenameUtils.getBaseName(path);
				File imgFile = new File(out, baseName+".jpg");
				if(!imgFile.exists()){
					FileUtils.copyURLToFile(new URL(path), imgFile,10000,10000);
					pageBean.getTbGoodsImgFiles().add(imgFile);
					System.out.println(path +"下载成功>>>>>>>>>>>>");
				}else{
					pageBean.getTbGoodsImgFiles().add(imgFile);
				}
			}
		}
		

		/*for (Element element : DivItemDesc) {
			String imgSrc = element.attr("src");
			String data_ks_lazyload = element.attr("data-ks-lazyload");
			if(StringUtils.isNotBlank(data_ks_lazyload)){
				//String path= ParserUtils.imgPathParser(imgSrc + "   "+ data_ks_lazyload);
				String path= data_ks_lazyload;
				System.out.println("path=="+path);
				String baseName = FilenameUtils.getBaseName(path);
				File imgFile = new File(out, baseName+".jpg");
				if(!imgFile.exists()){
					FileUtils.copyURLToFile(new URL(path), imgFile,10000,10000);
					System.out.println(path +"下载成功>>>>>>>>>>>>");
				}
			}
		}*/
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			webDriver.close();
		}
		
		return pageBean;
	}
	
	public static TbSpPage parsTianmao(String url,File outBase)throws Exception{
		//String url = "https://detail.tmall.com/item.htm?id=530996956269&scm=1007.12807.73594.100200300000002&pvid=1d4b444e-5da2-4929-9546-9d9e85544d57";
		WebDriver webDriver = SeleniumUtil.initChromeDriver();
		TbSpPage pageBean = new TbSpPage();
		try{
		webDriver.get(url);
		Thread.sleep(5000);
		
		String id = ParserUtils.idParser(url);
		File out = new File(outBase, id);
		if(!out.exists()){
			out.mkdirs();
		}
		
		
		/*WebElement webelement =webDriver.findElement(By.id("J_DivItemDesc"));
		
		System.out.println(webelement.getText());*/
		
		
		Document document =  Jsoup.parse(webDriver.getPageSource());
		String tb_main_title =document.select(".tb-detail-hd").text();   // webDriver.executeScript("return g_config.idata.item.title;")+""; //document.select(".tb-main-title").text();   //tbGoodsTitle
		pageBean.setTbGoodsTitle(tb_main_title);
		
		String tm_price =document.select(".tm-promo-price").select(".tm-price").text();   
		pageBean.setTaoBaoprice(tm_price);
		
		//产品详情图
		Elements DivItemDesc = null;
			//DivItemDesc = document.select("#J_DcTopRight").select("img");
		DivItemDesc = document.select("#detail").select("img");
		
		System.out.println(DivItemDesc.size());

		for (Element element : DivItemDesc) {
			System.out.println(element.html()); 
			String data_ks_lazyload = element.attr("src");
			String data_src = element.attr("data-src");
			if(StringUtils.isNotBlank(data_ks_lazyload)){
				//String path= ParserUtils.imgPathParser(imgSrc + "   "+ data_ks_lazyload);
				if(!data_ks_lazyload.startsWith("http")){
					data_ks_lazyload = "https:"+data_ks_lazyload;
				}
				if(!data_ks_lazyload.contains("60x60")){
					continue;
				}
				String path= data_ks_lazyload.replace("60x60", "400x400");
				System.out.println("path=="+path);
				String baseName = FilenameUtils.getBaseName(path);
				File imgFile = new File(out, baseName+".jpg");
				if(!imgFile.exists()){
					FileUtils.copyURLToFile(new URL(path), imgFile,10000,10000);
					pageBean.getTbGoodsImgFiles().add(imgFile);
					System.out.println(path +"下载成功>>>>>>>>>>>>");
				}else{
					pageBean.getTbGoodsImgFiles().add(imgFile);
				}
			}
		}
		
		/*for (Element element : DivItemDesc) {
			//String imgSrc = element.attr("src");
			String data_ks_lazyload = element.attr("data-ks-lazyload");
			if(StringUtils.isNotBlank(data_ks_lazyload)){
				String path="https:"+ data_ks_lazyload;
				String baseName = FilenameUtils.getBaseName(path);
				System.out.println("baseName=="+baseName);
				File imgFile = new File(out, baseName+".jpg");
				if(!imgFile.exists()){
					FileUtils.copyURLToFile(new URL(path), imgFile,10000,10000);
					System.out.println(path +"下载成功>>>>>>>>>>>>");
				}
			}
		}*/
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			webDriver.close();
		}
		return pageBean;
	}
	
	public static void test()throws Exception{
		String url = "https://detail.tmall.com/item.htm?id=530996956269&scm=1007.12807.73594.100200300000002&pvid=1d4b444e-5da2-4929-9546-9d9e85544d57";
		/*HttpGet httpGet = new HttpGet(url);
	    String rs = HttpClientUtil.sendGetRequest(httpGet, "utf-8", null);
	   Document document =  Jsoup.parse(rs);
	   
	   System.out.println(document.getElementsByClass("skin-box-bd clear-fix").html());*/
	   
	  // System.out.println(rs);
	
	
	   WebClient client = HtmlUnitUtil.create();
	   HtmlPage htmlPage = client.getPage(url);
	   Thread.sleep(5000);
	   
	   String hrefValue = "window.scrollTo(0,document.body.scrollHeight);";
	   ScriptResult s = htmlPage.executeJavaScript(hrefValue);//执行js方法
	   htmlPage = (HtmlPage) s.getNewPage();//获得执行后的新page对象
	   Thread.sleep(10000);
	   
	   
	   
	  /* List<FrameWindow> frameWindows = htmlPage.getFrames();
	   
	   System.out.println("frameWindows==="+frameWindows.size());
	   
	   for(FrameWindow frameWindow:frameWindows){
		   System.out.println("name=="+frameWindow.getName());
		   //htmlPage =frameWindow.getEnclosingPage();
		   htmlPage =(HtmlPage) frameWindow.getEnclosedPage();
		   Thread.sleep(5000);
		   
		   System.out.println("==="+htmlPage.asXml());
		   
		   Document document =  Jsoup.parse(htmlPage.asXml());
		   Element element =document.getElementById("description");
		   if(element!=null&&!element.isBlock()){
			   System.out.println(element.html());  
		   }
		  
	   }*/
	   
	   
	   String rc = htmlPage.asXml();
	   System.out.println();
	   
	   FileUtils.write(new File("d:\\test.txt"), rc);
	   
	   
        /*   Document document =  Jsoup.parse(htmlPage.asXml());
	   
	  // System.out.println(document.getElementsByClass("skin-box-bd clear-fix").html());
           System.out.println(document.getElementById("shop15817541355").html());*/
     	  
	   
	  // HtmlElement element =  htmlPage.getFirstByXPath("//div[@class='skin-box-bd clear-fix']");
	 // System.out.println(element.asXml());
	}

}
