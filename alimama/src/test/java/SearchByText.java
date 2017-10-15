


import java.io.*;
import java.net.*;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SearchByText {
	

	public static void main(String[] args) {
		final String gs = "订书机";//此变量为搜索关键字

		Thread jd = new Thread() {
			public void run() {
				JD(gs);
			}
		};

		Thread sn = new Thread() {
			public void run() {
				SN(gs);
			}
		};

		Thread ymx = new Thread() {
			public void run() {
				String sp = "";
				while(true){
					try {
					sp = YMX(gs);
					} catch (Exception e) {
						continue;
						}
					break;
				
				}
			/*	while(true){
					try {
						ymxNUM(sp);
					} catch (Exception e) {
						continue;
						}
					break;
				
				}*/
				
				}
				
		};
		
		Thread dd = new Thread() {
			public void run() {
				DD(gs);
			}
		};
		Thread yhd = new Thread() {
			public void run() {
				YHD(gs);
			}
		};
		Thread cgklp = new Thread() {
			public void run() {
				CGKLP(gs);
			}
		};
		Thread dl = new Thread() {
			public void run() {
				DL(gs);
			}
		};
		Thread gm = new Thread() {
			public void run() {
				GM(gs);
			}
		};
		Thread od = new Thread() {
			public void run() {
				OD(gs);
			}
		};
		Thread qx = new Thread() {
			public void run() {
				QX(gs);
			}
		};
		
		
		     jd.start(); /*sn.start(); dd.start(); yhd.start();  ymx.start();
	 	   cgklp.start(); dl.start(); gm.start(); od.start(); qx.start();*/
		 

		
	 		/*while(true){
				try {
ymxNUM("https://www.amazon.cn/DeLi%E5%BE%97%E5%8A%9B-0368-%E7%9C%81%E5%8A%9B%E8%AE%A2%E4%B9%A6%E6%9C%BA/dp/B004EWFVUE/ref=sr_1_1?ie=UTF8&qid=1507972075&sr=8-1&keywords=%E9%92%89%E4%B9%A6%E6%9C%BA"
							);
				} catch (Exception e) {
					System.out.println(e);
					continue;
					}
				break;
			} */
		 // ymx.start();
	//	dl.start();ymx.start();
		/*
		 * YMX(gs); JD(gs); SN(gs); OD(gs); DD(gs); YHD(gs); CGKLP(gs); DL(gs);
		 * GM(gs); OD(gs); QX(gs);
		 */

	}


	private static void OD(String goods) {
		HttpURLConnection odurlconn = null;
		URL url = null;
		BufferedReader br = null;
		// PrintWriter pw = null;

		String cookie = "__odc_cookie_region_key=2";
		try {
			String p = URLEncoder.encode(goods, "gbk");
			url = new URL(" http://buy.officedepot.com.cn/search.html?m=10&q="
					+ p + "&areaid=2");
			odurlconn = (HttpURLConnection) url.openConnection();
			odurlconn.setDoOutput(true);
			odurlconn.setRequestProperty("Cookie", cookie);
			odurlconn.setRequestProperty("Content-type", "text/html");
			odurlconn.setRequestProperty("Accept-Charset", "gbk");
			odurlconn.setRequestProperty("contentType", "gbk");
			odurlconn.connect();
			// pw = new PrintWriter(new FileWriter("od.html"), true);
			br = new BufferedReader(new InputStreamReader(
					odurlconn.getInputStream(), "gbk"));
			String buf = "";
			// String tmp = "";
			// StringBuilder tmp = new StringBuilder(); 线程不安全
			StringBuffer odtmp = new StringBuffer();
			while ((buf = br.readLine()) != null) {
				// tmp = tmp + buf;
				odtmp.append(buf);
				// pw.println(buf);

			}
			// File html = new File("od.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(odtmp.toString(), "gbk");

			String title = doc.select("li.list2 >a").first().text();
			// String disc = doc.select("li.list2 >span").first().text();
			String price = doc.select("li.list3 ").first().text();
			String img = doc.select("li.pic130 ").first()
					.getElementsByTag("img").first().attr("src");
			String spUrl = doc.select("li.pic130 >a").first().attr("href");
			System.out.println("商品名:" + title);
			System.out.println(price);
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品图片:" + img);

		}catch (NullPointerException e){
			System.out.println("欧迪没有此货物");
		}			
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// pw.close();
			// new File("od.html").delete();
			odurlconn.disconnect();
		}

	}

	private static void JD(String goods) {
		HttpURLConnection jdurlconn = null;
		URL url = null;
		BufferedReader br = null;
		// PrintWriter pw = null;

		try {
			String p = URLEncoder.encode(goods, "UTF-8");
			url = new URL("https://search.jd.com/Search?keyword=" + p
					+ "&enc=utf-8&wq=" + p);
			jdurlconn = (HttpURLConnection) url.openConnection();
			jdurlconn.setDoOutput(true);
			jdurlconn.setRequestProperty("Content-type", "text/html");
			jdurlconn.setRequestProperty("Accept-Charset", "utf-8");
			jdurlconn.setRequestProperty("contentType", "utf-8");

			jdurlconn.connect();
			// pw = new PrintWriter(new FileWriter("jd.html"), true);
			br = new BufferedReader(new InputStreamReader(
					jdurlconn.getInputStream(), "UTF-8"));
			String buf = "";
			String jdtmp = null;
			while ((buf = br.readLine()) != null) {

				// pw.println(buf);
				jdtmp = jdtmp + buf;

			}
			// File html = new File("jd.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(jdtmp, "UTF-8");
			String title = doc.select("div.p-name em").first().text();
			String price = doc.select("div.p-price i ").first().text();
			String img = doc.select("div.p-img img").first().attr("src");
			String spUrl = doc.select("div.p-name  >a").first().attr("href");

			System.out.println("商品名:" + title);
			System.out.println("价格:" + price);
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品图片:" + img);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// pw.close();
			// new File("jd.html").delete();
			jdurlconn.disconnect();
		}

	}

	private static void SN(String goods) {
		HttpURLConnection snurlconn = null;
		HttpURLConnection	snurl = null;
		URL url = null;
		BufferedReader br = null;
		// PrintWriter pw = null;

		try {
			String p = URLEncoder.encode(goods, "UTF-8");
			url = new URL("http://search.suning.com/" + p + "/");
			snurlconn = (HttpURLConnection) url.openConnection();
			snurlconn.setDoOutput(true);
			snurlconn.setRequestProperty("Content-type", "text/html");
			snurlconn
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36)");
			snurlconn.setRequestProperty("Accept-Charset", "utf-8");
			snurlconn.setRequestProperty("contentType", "utf-8");

			snurlconn.connect();
			// pw = new PrintWriter(new FileWriter("sn.html"), true);
			br = new BufferedReader(new InputStreamReader(
					snurlconn.getInputStream(), "UTF-8"));
			String buf = "";
			String sntmp = null;
			while ((buf = br.readLine()) != null) {

				// pw.println(buf);
				sntmp = sntmp + buf;

			}

			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(sntmp, "UTF-8");
			String title = doc.select("div.res-info a").first().text();
			// String price =
			// doc.select("strong.J_10360386500 >i ").first().text();
			String img = doc.select("div.img-block img").first().attr("src2");
			String code = img.substring(img.indexOf("-") + 1, img.indexOf("_"));
			String sort = img.substring(img.lastIndexOf("/") + 1,
					img.indexOf("-"));
			String priUrl = "http://ds.suning.cn/ds/generalForTile/" + code
					+ "_-021-2-" + sort + "-1--ds000000000985.jsonp";
			
			// URL pu = new URL(priUrl);
			Connection conn = Jsoup.connect(priUrl);
			conn.timeout(30000); // 30秒超时
			conn.ignoreContentType(true);
			Response response = conn.execute();
			String res = response.body();
			String price = res.substring(res.indexOf("price") + 8,
					res.indexOf("priceType") - 3);
			String spUrl = "http:"+doc.select("div.res-info a").first().attr("href");
			//-------------
			url = new URL(spUrl);
			snurl  = (HttpURLConnection) url.openConnection();
			snurl.setDoOutput(true);
			snurl.setRequestProperty("Content-type", "text/html");
			snurl.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36)");
			snurl.setRequestProperty("Accept-Charset", "utf-8");
			snurl.setRequestProperty("contentType", "utf-8");

			snurl.connect();
		//	  pw = new PrintWriter(new FileWriter("sn.html"), true);
		   BufferedReader br1 = new BufferedReader(new InputStreamReader(
					snurl.getInputStream(), "UTF-8"));
			String buf1 = "";
			String sntmp1 = null;
			while ((buf1 = br1.readLine()) != null) {

		//		  pw.println(buf1);
				sntmp1 = sntmp1 + buf1;

			}

			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc1 = Jsoup.parse(sntmp1, "UTF-8");
			String num = doc1.select("div.pop-onsale").first().text();
			//String cx = doc1.select("p#pointBox").text();
	/*		Document spconn = Jsoup.connect(spUrl).get();
			System.out.println(spconn.text());
			String num = spconn.select("strong#haveProduct").text();
			String cx = spconn.select("p#pointBox").text();
*/
			System.out.println("商品名:" + title);
			System.out.println("价格:" + price);
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品数量："+num);
			//System.out.println("促销："+cx);
			System.out.println("商品图片:" + img);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//  pw.close();
			// new File("jd.html").delete();
			snurl.disconnect();
			snurlconn.disconnect();
			
		}

	}

	private static String YMX(String goods) throws  Exception  {
		HttpURLConnection ymxurlconn = null;
		URL url = null;
		BufferedReader br = null;
		String spUrl = "";
		//  PrintWriter pw = null;
		String[] ua = {
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36 OPR/18.0.1284.68",
				"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.8.0.16453",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:7.0.1) Gecko/20100101 Firefox/7.0.1",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36 OPR/18.0.1284.68",
				"Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) CriOS/30.0.1599.12 Mobile/11A465 Safari/8536.25",
		};

		try {
			String p = URLEncoder.encode(goods, "UTF-8");
			url = new URL(
					"https://www.amazon.cn/s/ref=nb_sb_noss_1?__mk_zh_CN=亚马逊网站&url=search-alias%3Daps&field-keywords="
							+ p);
			ymxurlconn = (HttpURLConnection) url.openConnection();
			ymxurlconn.setDoOutput(true);
			ymxurlconn.setRequestProperty("Content-type", "text/html");
			ymxurlconn.setRequestProperty("Accept-Charset", "utf-8");
		 	String u = ua[new Random().nextInt(8)];
			ymxurlconn.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
			ymxurlconn.setRequestProperty("User-Agent", u);
			ymxurlconn.setRequestProperty("Host", "www.amazon.cn");
			ymxurlconn.connect();
			//  pw = new PrintWriter(new FileWriter("ymx.html"), true);
			br = new BufferedReader(new InputStreamReader(
					ymxurlconn.getInputStream(), "UTF-8"));
			String buf = "";
			String ymxtmp = null;
			while ((buf = br.readLine()) != null) {
			//	  pw.println(buf);
				ymxtmp = ymxtmp + buf;
			}
			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(ymxtmp, "UTF-8");
			String title = doc.select("div.a-spacing-mini h2").first().text();
			String price = doc.select("span.s-price ").first().text();
			spUrl = doc.select("div.a-spacing-base a").first()
					.attr("href");
			if(!(spUrl.indexOf("ps://www.amazon.cn")>1)){
				spUrl = "https://www.amazon.cn"+spUrl;
			}
			

			//----
			
			
			String img = doc.select("li#result_0 img").first().attr("src");
			System.out.println("商品名:" + title);
			System.out.println("价格:" + price);
		 
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品图片:" + img);
		} catch (RuntimeException e) {
			//e.printStackTrace();
		} finally {
			br.close();
			//  pw.close();
			// new File("jd.html").delete();
			ymxurlconn.disconnect();
		}
		return spUrl;

	}
	
	static void ymxNUM(String spUrl) throws Exception{
		HttpURLConnection ymxurl = null;
		URL url = null;
		 PrintWriter pw = null;
		//---------
		
			String[] ua = {
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36",
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36",
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36 OPR/18.0.1284.68",
					"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.8.0.16453",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:7.0.1) Gecko/20100101 Firefox/7.0.1",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36 OPR/18.0.1284.68",
					"Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) CriOS/30.0.1599.12 Mobile/11A465 Safari/8536.25",
			};
			url = new URL(spUrl);
			ymxurl  = (HttpURLConnection) url.openConnection();
			ymxurl.setDoOutput(true);
			ymxurl.setRequestProperty("Content-type", "text/html");
			ymxurl.setRequestProperty("Accept-Charset", "utf-8");
		 	String u = ua[new Random().nextInt(8)];
			ymxurl.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
			ymxurl.setRequestProperty("User-Agent", u);
			ymxurl.setRequestProperty("Host", "www.amazon.cn");
			ymxurl.connect();
		  	  pw = new PrintWriter(new FileWriter("ymx.html"), true);
		   BufferedReader br1 = new BufferedReader(new InputStreamReader(
					ymxurl.getInputStream(), "UTF-8"));
			String buf1 = "";
			String ymxtmp1 = null;
			while ((buf1 = br1.readLine()) != null) {

		  		  pw.println(buf1);
				ymxtmp1 = ymxtmp1 + buf1;

			}

			   File html = new File("ymx.html");
			   Document doc1 = Jsoup.parse(html,"UTF-8");
			// Document doc1 = Jsoup.parse(ymxtmp1, "UTF-8");
			String num = doc1.select("span#ddmAvailabilityMessage").first().text();
			System.out.println("商品数量："+num);
			ymxurl.disconnect();
		 	pw.close();

		
	}

	private static void DD(String goods) {

		HttpURLConnection ddurlconn = null;
		URL url = null;
		BufferedReader br = null;
		// PrintWriter pw = null;
		new File("ymx.html").delete();
		try {
			String p = URLEncoder.encode(goods, "GB2312");
			url = new URL("http://search.dangdang.com/?key=" + p + "&act=input");
			ddurlconn = (HttpURLConnection) url.openConnection();
			ddurlconn.setDoOutput(true);
			ddurlconn.setRequestProperty("Content-type", "text/html");
			ddurlconn
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36)");
			ddurlconn.setRequestProperty("Accept-Charset", "UTF-8");
			ddurlconn.setRequestProperty("contentType", "UTF-8");

			ddurlconn.connect();
			// pw = new PrintWriter(new FileWriter("sn.html"), true);
			br = new BufferedReader(new InputStreamReader(
					ddurlconn.getInputStream(), "GB2312"));
			String buf = "";
			String ddtmp = null;
			while ((buf = br.readLine()) != null) {
				// pw.println(buf);
				ddtmp = ddtmp + buf;
			}

			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(ddtmp, "UTF-8");
			String title = doc.select("ul.bigimg a").first().attr("title");
			String price = doc.select("ul.bigimg span ").first().text();
			String spUrl = doc.select("ul.bigimg a").first().attr("href");
			String img = doc.select("li.line1 img").first().attr("src");
			String shopId = "";
			try{
			String shref = doc.select("p.link a").first().attr("href");
			 shopId = shref.substring(shref.lastIndexOf("/")+1);
			}catch(NullPointerException e){
				shopId = "0";
			}
			
			String goodId = spUrl.substring(spUrl.lastIndexOf("/")+1,spUrl.lastIndexOf("."));
			//----库存
			String numurl = "http://product.dangdang.com/index.php?r=callback%2Fproduct-info&productId="+goodId
					+ "&isCatalog=0&shopId="+shopId;
			Connection conn = Jsoup.connect(numurl);
			conn.timeout(10000); // 10秒超时
			conn.ignoreContentType(true);
			Response response = conn.execute();
			String res = response.body();
			String num = res.substring(res.indexOf("stockType") +12,
					res.indexOf("},\"preSale")-1);
			
			String kc = num.equals("realStock")?"有货":"缺货";

			System.out.println("商品名:" + title);
			System.out.println("价格:" + price);
			System.out.println("商品数量："+kc);
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品图片:" + img);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ddurlconn.disconnect();
			// pw.close();
			// new File("jd.html").delete();
		}

	}

	private static void YHD(String goods) {
		HttpURLConnection urlconn = null;
		URL url = null;
		BufferedReader br = null;
		// PrintWriter pw = null;

		try {
			String p = URLEncoder.encode(goods, "UTF-8");
			url = new URL("http://search.yhd.com/c0-0/k" + p + "/");
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setDoOutput(true);
			urlconn.setRequestProperty("Content-type", "text/html");
			urlconn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36)");
			urlconn.setRequestProperty("Accept-Charset", "UTF-8");
			urlconn.setRequestProperty("contentType", "UTF-8");

			urlconn.connect();
			// pw = new PrintWriter(new FileWriter("sn.html"), true);
			br = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream(), "UTF-8"));
			String buf = "";
			String yhdtmp = null;
			while ((buf = br.readLine()) != null) {

				// pw.println(buf);
				yhdtmp = yhdtmp + buf;

			}
			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(yhdtmp, "UTF-8");
			String title = doc.select("p.proName").first().text();

			String price = doc.select("p.proPrice em ").first().text();

			String spUrl = doc.select("div#searchProImg a").first()
					.attr("href");
			String img = doc.select("div#searchProImg img").first().attr("src");

			System.out.println("商品名:" + title);
			System.out.println("价格:" + price);
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品图片:" + img);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			urlconn.disconnect();
			// pw.close();
			// new File("jd.html").delete();
		}

	}

	private static void CGKLP(String goods) {
		HttpURLConnection urlconn = null;
		URL url = null;
		BufferedReader br = null;
		// PrintWriter pw = null;

		try {
			String p = URLEncoder.encode(goods, "UTF-8");
			url = new URL("http://search.colipu.com/Search.html?q=" + p);
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setDoOutput(true);
			urlconn.setRequestProperty("Content-type", "text/html");
			// urlconn.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			urlconn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36)");
			urlconn.setRequestProperty("Accept-Charset", "UTF-8");
			urlconn.setRequestProperty("contentType", "UTF-8");

			urlconn.connect();
			// pw = new PrintWriter(new FileWriter("sn.html"), true);
			br = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream(), "UTF-8"));
			String buf = "";
			String cgklptmp = null;
			while ((buf = br.readLine()) != null) {

				// pw.println(buf);
				cgklptmp = cgklptmp + buf;

			}

			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(cgklptmp, "UTF-8");
			String title = doc.select("div.product-list >a").first()
					.attr("title");

			String price = doc.select("div.product-list p ").first().text();

			String spUrl = doc.select("div.product-list >a").first()
					.attr("href");
			String img = doc.select("div.product-list img").first().attr("src");

			System.out.println("商品名:" + title);
			System.out.println("价格:" + price);
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品图片:" + img);
		}catch (NullPointerException e){
			System.out.println("晨光科力普没有此货物");
		}	 
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			urlconn.disconnect();
			// pw.close();
			// new File("jd.html").delete();
		}

	}

	private static void DL(String goods) {
		HttpURLConnection urlconn = null;

		URL url = null;
		BufferedReader br = null;
	 //	 PrintWriter pw = null;
		try {
			String p = URLEncoder.encode(goods, "UTF-8");
			url = new URL("http://b2b.nbdeli.com/Goods/Search.aspx?KeyWord="
					+ p);
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setDoOutput(true);
			urlconn.setRequestProperty("Content-type", "text/html");
			urlconn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36)");
			urlconn.setRequestProperty("Accept-Charset", "UTF-8");
			urlconn.setRequestProperty("contentType", "UTF-8");
			urlconn.connect();
	 	//	 pw = new PrintWriter(new FileWriter("dl.html"), true);
			br = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream(), "UTF-8"));
			String buf = "";
			String dltmp = null;
			while ((buf = br.readLine()) != null) {
	 		//	 pw.println(buf);
				dltmp = dltmp + buf;
			}

			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(dltmp, "UTF-8");
			String title = doc.select("h3.recommen_goods_name >a").first()
					.attr("title");
			String spUrl = doc.select("p.recommen_goods_item >a").first()
					.attr("href");
			String img = doc.select("p.recommen_goods_item img").first().attr("src");
			if(img == null || img.equals("")){
				img= doc.select("p.recommen_goods_item img").first().attr("data-original");
			}
			String price = "";
		  if(dltmp.indexOf("ItemAvergePrice(")>1){
			  int num = dltmp.indexOf("ItemAvergePrice(")+27;
				String preprice = dltmp.substring(num,num+10);
				String  pri = preprice.substring(0, preprice.indexOf("|")>0?preprice.indexOf("|"):preprice.length());
			
				if(pri.indexOf(".")>0){
					 price = pri;
				}
		  }else{
			  String t = doc.select("input#hideItemIdsPrices").first().attr("value");
			  price = t.substring(t.indexOf(":"),t.indexOf("|")>0?t.indexOf("|"):t.length());
		  }
		  String goodId = img.substring(img.lastIndexOf("/")+1,img.lastIndexOf("."));
		  //---------------库存
	 	String numurl = "http://b2b.nbdeli.com/Goods/Services/AsyncItemColorService.ashx?callback=jsonp1507967348383&itemId="+goodId
		  +"&idPrefix=&isInBasket=false&isInScaringBuy=false&action=GetItemColorSize";		  

			Connection conn = Jsoup.connect(numurl);
			conn.timeout(10000); // 10秒超时
			conn.ignoreContentType(true);
			Response response = conn.execute();
			String res = response.body();
		 	String num = res.substring(res.indexOf("AllowBuy") +10,res.indexOf("NotAllowBuy")-2);
		 	String kc = num.equalsIgnoreCase("true")?"有货":"无货";
		 	
		  //------------
		  
			System.out.println("商品名:" + title);
			System.out.println("价格:"+price);
			System.out.println("商品数量："+kc);
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品图片:" + img);
		}catch (NullPointerException e){
			System.out.println("得力没有此货物");
		}	 
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			urlconn.disconnect();
		 	// pw.close();
			// new File("jd.html").delete();
		}

	}

	private static void GM(String goods) {

		HttpURLConnection urlconn = null;

		URL url = null;
		BufferedReader br = null;
	//	PrintWriter pw = null;

		try {
			String p = URLEncoder.encode(goods, "UTF-8");
			url = new URL("http://search.gome.com.cn/search?question=" + p
					+ "&searchType=goods");
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setDoOutput(true);
			urlconn.setRequestProperty("Content-type", "text/html");
			urlconn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36)");
			urlconn.setRequestProperty("Accept-Charset", "UTF-8");
			urlconn.setRequestProperty("contentType", "UTF-8");

			urlconn.connect();
		//	pw = new PrintWriter(new FileWriter("sn.html"), true);
			br = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream(), "UTF-8"));
			String buf = "";
			String gmtmp = null;
			while ((buf = br.readLine()) != null) {

			//	pw.println(buf);
				gmtmp = gmtmp + buf;

			}

			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(gmtmp, "UTF-8");
			String title = doc.select("p.item-pic >a").first().attr("title");
			String spUrl = doc.select("p.item-pic >a").first().attr("href");
			String img = doc.select("p.item-pic img").first().attr("gome-src");

			String code = spUrl.substring(spUrl.lastIndexOf("/") + 1,
					spUrl.indexOf("-") - 1);
			String sort = spUrl.substring(spUrl.indexOf("-") + 1,
					spUrl.indexOf(".html"));
			String priUrl = "http://ss.gome.com.cn/search/v1/price/single/"
					+ code + "/" + sort + "/21010000/flag/item/fn0";
			Connection conn = Jsoup.connect(priUrl);
			conn.timeout(10000); // 10秒超时
			conn.ignoreContentType(true);
			Response response = conn.execute();
			String res = response.body();
			String price = res.substring(res.indexOf("price") + 8,
					res.indexOf("priceType") - 3);
			
			//-------库存
			//http://ss.gome.com.cn/item/v1/d/m/store/unite/A0005022491/pop8004827449/N/21010100/210101001/1/null/flag/item/allStores
			
			
			//-----------
			
			
			
			
			
			
			
			
			
			System.out.println("商品名:" + title);
			System.out.println("价格:" + price);
			System.out.println("商品链接:" + spUrl);
			System.out.println("商品图片:" + img);
		} catch(NullPointerException e){
			System.out.println("国美没有此货物");
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			urlconn.disconnect();
		//	pw.close();
			// new File("jd.html").delete();
		}

	}

	private static void QX(String goods) {
		HttpURLConnection urlconn = null;

		URL url = null;
		BufferedReader br = null;
		// PrintWriter pw = null;

		try {
			String p = URLEncoder.encode(goods, "UTF-8");
			url = new URL(
					"https://www.comix.com.cn/Product/ProductList?keyword=" + p);
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setDoOutput(true);
			urlconn.setRequestProperty("Content-type", "text/html");
			urlconn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36)");
			urlconn.setRequestProperty("Accept-Charset", "UTF-8");
			urlconn.setRequestProperty("contentType", "UTF-8");
			urlconn.connect();
			// pw = new PrintWriter(new FileWriter("sn.html"), true);
			br = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream(), "UTF-8"));
			String buf = "";
			String qxtmp = null;
			while ((buf = br.readLine()) != null) {

				// pw.println(buf);
				qxtmp = qxtmp + buf;

			}

			// File html = new File("sn.html");
			// Document doc = Jsoup.parse(html,"UTF-8");
			Document doc = Jsoup.parse(qxtmp, "UTF-8");
			String title = doc.select("div.proListTit ").first().text();
			String price = doc.select("div.price ").first().text();
			String spUrl = doc.select("ul.list-item a").first().attr("href");
			String img = doc.select("ul.list-item img").first().attr("src");

			System.out.println("商品名:" + title);
			System.out.println("价格:" + price);
			System.out.println("商品链接:" + "https://www.comix.com.cn" + spUrl);
			System.out.println("商品图片:" + img);
		}catch (NullPointerException e){
			System.out.println("齐心没有此货物");
		}	 
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			urlconn.disconnect();
			// pw.close();
			// new File("jd.html").delete();
		}

	}
	
}
