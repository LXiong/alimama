package zhuanzhuan;

import java.io.File;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataoke.Cmd;
import util.SeleniumUtil;

public class DaTaobaoUtils {
	static File outBase = new File("D:\\dataoke\\dataokeimg");

	public static void main(String[] args) throws Exception {
		String url = "http://www.dataoke.com/item?id=2696223";

		// url="https://item.taobao.com/item.htm?spm=a219t.7900221/10.1998910419.d30ccd691.uPkZic&id=43022193676";

		executeAll("http://www.dataoke.com/item?id=2688624","http://www.dataoke.com/item?id=2695293");
	}
	
	
	public static void executeAll(String...strings)throws Exception{
		for(String url:strings){
			TbSpPage page = execute(url);
			//TbSpPage page = parsDaTaoBao(url,outBase);
			System.out.println("商品标题：" + page.getTbGoodsTitle());
			System.out.println("商品价格：" + page.getTaoBaoprice());
			System.out.println("商品詳細：" + page.getTbGoodsDetailInfo().replace("内裤", "裤"));
			System.out.println("商品主图大小：" + page.getTbGoodsImgFiles().size());
			UploadImgTest.execute(page);
		}
		
	}
	
	static String uname = "15201733860";

	public static TbSpPage execute(String url) throws Exception {
		if(login(uname, "1qaz2wsx1")){
			System.out.println("登陸成功?》》》》》》》》》》》》》》》》》》》》》》");
			return parsDaTaoBao(url, outBase);
		}else{
			System.out.println("登陸失敗>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		return null;
	}

	
	public static void webGet(String url) {
		try {
			webDriver.get(url);
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.stop();");
			System.out.println("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
		}
	}
	
	public static boolean isLogin(){
		String str =webDriver.getPageSource();
		if(str.contains(uname)){
			System.out.println("当前已登录》》》》》》》》》》");
			return true;
		}
		System.out.println("当前没有登录》》》》》》》》》》");
		return false;
	}

	public static boolean login(String uname, String pwd) throws Exception {
		try {
			if(isLogin()){
				return true;
			}
			webGet("http://www.dataoke.com/login");
			Thread.sleep(Cmd.getSleepTime(1000, 2000));
			WebElement element = webDriver.findElement(By
					.xpath("//input[@data-id='email']"));
			element.sendKeys(uname);

			element = webDriver
					.findElement(By.xpath("//input[@data-id='pwd']"));
			element.sendKeys(pwd);

			element = webDriver.findElement(By
					.xpath("//a[@class='submit-btn login-btn']"));
			element.click();

			Thread.sleep(Cmd.getSleepTime(1000, 2000));

			String str = webDriver.getPageSource();

			if (str.contains("")) {

			}
			// Thread.sleep(Cmd.getSleepTime());
			// webGet("http://www.dataoke.com/ucenter/favorites_quan.asp");
			// Thread.sleep(Cmd.getSleepTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	static WebDriver webDriver = SeleniumUtil.initChromeDriver();
	static{
    	webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
	public static TbSpPage parsDaTaoBao(String url, File outBase)
			throws Exception {

		TbSpPage pageBean = new TbSpPage();

		try {
			webGet(url);
			Thread.sleep(2000);

			String id = ParserUtils.idParser(url);
			File out = new File(outBase, id);
			if (!out.exists()) {
				out.mkdirs();
			}
			
			WebElement webelement = webDriver.findElement(By.id("J_trans_tkl"));
			webelement.click();
			
			Thread.sleep(1000);

			Document document = Jsoup.parse(webDriver.getPageSource());

			String tb_main_title = document.select(".goods-tit").select("h1").select("a").text(); 
			pageBean.setTbGoodsTitle(tb_main_title);

			String tm_price = document.getElementsByClass("new-price fl").html();
			
			
			Elements elements =document.getElementsByTag("span");
			for(Element element:elements){
				String html =element.html();
				if(html.contains("券后价") && html.contains("<i>￥</i>")){
					tm_price = element.select("b").text().replace("￥", "");
					//System.out.println("券后价 html "+element.html());
				}
			}
			pageBean.setTaoBaoprice(tm_price);

			// System.out.println(document.html());
			// 产品详情图
			Elements DivItemDesc = null;
			// DivItemDesc = document.select("#J_DivItemDesc").select("img");
			DivItemDesc = document.select(".goods-big-img").select("img");
			
			
			
			String tbGoodsDetailInfo = document.select(".tui-content").text();
			pageBean.setTbGoodsDetailInfo(tbGoodsDetailInfo);
			

			System.out.println(DivItemDesc.size());

			for (Element element : DivItemDesc) {
				String data_ks_lazyload = "http:"+element.attr("src");
                if(element.attr("src").startsWith("http")){
                	data_ks_lazyload = element.attr("src");
				}
				if (StringUtils.isNotBlank(data_ks_lazyload)) {
					String baseName = UUID.randomUUID().toString();
					File imgFile = new File(out, baseName + ".jpg");
					if (!imgFile.exists()) {
						FileUtils.copyURLToFile(new URL(data_ks_lazyload),
								imgFile, 10000, 10000);
						pageBean.getTbGoodsImgFiles().add(imgFile);
						System.out.println(data_ks_lazyload
								+ "下载成功>>>>>>>>>>>>");
					} else {
						pageBean.getTbGoodsImgFiles().add(imgFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//webDriver.close();
		}

		return pageBean;
	}

}
