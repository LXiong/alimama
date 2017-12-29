package blog;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import util.HttpClientUtil;

public class OSchainBlog {
	
	static File fileDir=new File("E:\\javaeye2"); 
	
	static String baseURL="http://m635674608.iteye.com";
	
	
	static  String ck=null;
	
	public static void main(String[] args)throws Exception {
		
	     String url="http://m635674608.iteye.com/blog/2376153";
		//send("vvvvvvvvvv", "%3Cp%3Eweqrqerqer%3C%2Fp%3E%0A&");
		//javaeye();
		//System.out.println(getjavaeyeBlogContent(url));
	          
	       //  fileDir=new File(fileDir, "1");
			  //sendAll(fileDir);
			 sendCreateAll(fileDir);
	   // donwloadErrorFIle();
	   //sendCreateCataAll(fileDir);	
	     
	     //System.out.println(getTitle("1395728"));
	     
	     //String title = "你好";
	     //createType(title);
	     
	    //();
	}
	
	public static boolean createType(String title)throws Exception{
			Thread.sleep(1000);
			String sendData="name="+title+"&sort_order=0";
			String reqURL="https://my.oschina.net/action/blog/add_blog_catalog?space=866802&id=0";
			HttpPost httpPost   = new HttpPost(reqURL);
			httpPost.setHeader("Cookie",ck);
					httpPost.setHeader("Referer","https://my.oschina.net/xiaominmin/blog/edit");
		    httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
		    httpPost.setHeader("Host","my.oschina.net");
			String str = HttpClientUtil.sendPostRequest(httpPost, sendData, true, "UTF-8","UTF-8");
		    System.out.println(str);
		    if(StringUtils.isNotBlank(str) && str.contains("id")){
		    	return true;
		    }
		return false;
	    
	}
	
	
	public static Map<String,String> getCataLog(){
		Map<String,String> map = new HashMap<String, String>();
		String reqURL="https://my.oschina.net/xiaominmin/blog/";
		//HttpGet httpPost   = new HttpGet(reqURL);
		HttpPost httpPost   = new HttpPost(reqURL);
		httpPost.setHeader("Cookie",ck);
				httpPost.setHeader("Referer","https://my.oschina.net/xiaominmin/blog/");
	    httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
	    httpPost.setHeader("Host","my.oschina.net");
		//String str = HttpClientUtil.sendGetRequest(httpPost, "utf-8",null);
	    String str = HttpClientUtil.sendPostRequest(httpPost, "", false, "UTF-8","UTF-8");
		
	    //System.out.println(str);
		
	    Document document = Jsoup.parse(str);
	    org.jsoup.select.Elements elements =document.select(".sort-list").select("a");
		for(Element element :elements){
			String href = element.attr("href");
			String id = href.replace("https://my.oschina.net/xiaominmin/blog?catalog=", "");
			String name = element.select(".name").text();
			System.out.println("id==="+id+" name=="+name);
			map.put(name, id);
		}
		
	    return map;
	}
	
	/**
	 * 创建分类
	 */
	
	public static void sendCreateCataAll(File file)throws Exception{
		Set<String> list  = new HashSet<String>();
		if(file.exists()){
			for(File f:file.listFiles()){
				fileDir = f;
				list.addAll(sendCreateCata(f));
			}
		}	
		
		System.out.println(list);
		
		System.out.println(list.size());
		int index = 0;
		for(String c:list){
			Map<String,String> map = getCataLog();
			if(map.containsKey(c)){
				System.out.println(c+"已经存在》》》》》》》》》》》》》");
				index ++;
				continue;
			}
			Thread.sleep(2000);
			createType(c);
			index++;
			
		}
		System.out.println("index======"+index);
		
	}
	
	public static Set<String> sendCreateCata(File file)throws Exception{
		//File file = new File(fileDir, "1");
		Set<String> list  = new HashSet<String>();
		if(file.exists()){
			for(File f:file.listFiles()){
				String name=f.getName();
				if(!name.contains("error")){
					boolean flag = false;
						//Map<String,String> map = getCataLog();
						//map.get("");
						String type = getType(name);
						if(StringUtils.isBlank(type)){
							continue;
						}
						type = URLDecoder.decode(type);
						//System.out.println(name+"======================="+type);
						list.add(type);
					}
					//return ;
				}
		}
		//System.out.println(list);
		return list;
	}
	static Map<String,String> map = null;
	
	public static void sendCreateAll(File file)throws Exception{
		map = getCataLog();
		if(file.exists()){
			for(File f:file.listFiles()){
				fileDir = f;
				sendCreate(f);
			}
		}	
	}
	
	
	
	public static void sendCreate(File file)throws Exception{
		//File file = new File(fileDir, "1");
	
		File okFile = new File("e:\\oschainOks.txt");
		String oks = FileUtils.readFileToString(okFile);
		
		if(file.exists()){
			for(File f:file.listFiles()){
				String name=f.getName();
				if(oks.contains(name)){
					System.out.println("文件名称已经包含>>>>>>>>>>>"+name);
					continue;
				}
				
				try{
				if(!name.contains("error")){
					boolean flag = false;
					for(int i=0;i<2;i++){
						if((flag=sendOschinaSDK(name))){
							System.out.println("文件名称："+name+" 上传结果："+flag+"上传成功>>>>>>>>>"); 
							Thread.sleep(10);
							break;
						}else{
							System.out.println("文件名称："+name+" 上传结果："+flag+"上传失败>>>>>>>>>"+i); 
							Thread.sleep(10);
						}
					}
					if(!flag){
						try{
							FileUtils.write(new File("d:\\errorjavaeye2.txt"), f.getAbsolutePath()+"\r\n", true);
						}catch(Exception e){
							e.printStackTrace();
						}
					}else{
						FileUtils.write(new File("d:\\oschainOK.txt"), f.getAbsolutePath()+"\r\n", true);
					}
					System.out.println("文件名称："+name+" 上传结果："+flag); 
					//return ;
				}
				
				}catch(Exception e){
					e.printStackTrace();
					FileUtils.write(new File("d:\\errorjavaeye2.txt"), f.getAbsolutePath()+"\r\n", true);
				}	finally {
					FileUtils.write(okFile, f.getAbsolutePath()+"\r\n", true);
					
				}
			}
		}
	}
	
	
	public static void sendAll(File file)throws Exception{
		//File file = new File(fileDir, "1");
		if(file.exists()){
			for(File f:file.listFiles()){
				String name=f.getName();
				if(!name.contains("error")){
					boolean flag = false;
					for(int i=0;i<10;i++){
						if((flag=sendOschina(name))){
							System.out.println("文件名称："+name+" 上传结果："+flag+"上传成功>>>>>>>>>"); 
							Thread.sleep(2000);
							break;
						}else{
							System.out.println("文件名称："+name+" 上传结果："+flag+"上传失败>>>>>>>>>"+i); 
							Thread.sleep(2000);
						}
					}
					if(!flag){
						FileUtils.write(new File("d:\\errorjavaeye.txt"), f.getAbsolutePath()+"\r\n", true);
					}
					System.out.println("文件名称："+name+" 上传结果："+flag); 
					//return ;
				}
			}
		}
	}
	
	
	public static boolean sendOschinaSDK(String id)throws Exception{
		 try{
				String content = getCentent(id);
				String title = getTitle(id);
				String type = getType(id);
				String typeId = map.get(type);
				
				if(StringUtils.isBlank(title) || StringUtils.isBlank(content) ){
					System.out.println("id===="+id+" type==="+type+" 内容为null");
					return false;
				}
				
				
				if(StringUtils.isBlank(typeId)){
					System.out.println("id===="+id+" type==="+type+" 获取不到分类");
					//return false;
					typeId = "3427650";
				}
				
				String origin_url = "http://m635674608.iteye.com/admin/blogs/"+id;
				boolean flag = OsChainSDK.execute(null, title, content, origin_url, typeId);
				
				//System.out.println(content);
				//System.out.println(title);
			    //boolean flag =   send(title, content);
			    //System.out.println(flag);
			    return flag;
				//return true;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return false;
		
	}
	
	public static boolean sendOschina(String id)throws Exception{
		 try{
				String content = getCentent(id);
				String title = getTitle(id);
				//System.out.println(content);
				//System.out.println(title);
			    boolean flag =   send(title, content);
			    //System.out.println(flag);
			    return flag;
				//return true;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return false;
		
	}
	
	
	public static void sendTest()throws Exception{
		   String id = "1395728";
			String content = getCentent(id);
			String title = getTitle(id);
			
			System.out.println(content);
			System.out.println(title);
		
		  boolean flag =   send(title, content);
		   System.out.println(flag);
	}
	
	public static String getCentent(String id)throws Exception{
		File file = new File(fileDir,id);
		Document document = Jsoup.parse(FileUtils.readFileToString(file));
		String content = document.select("#blog_content").html();
		String title = document.select(".blog_title").text();
		content = title + content;
		if(StringUtils.isNotBlank(content)){
			//content = URLEncoder.encode(content);
			return content;
		}
		
		return null;
	}
	
	
	public static String getType(String id)throws Exception{
		File file = new File(fileDir,id);
		Document document = Jsoup.parse(FileUtils.readFileToString(file));
		Elements elements = document.select(".blog_categories").select("a");
		String content =elements.size()>0?elements.get(0).text():"";
		if(StringUtils.isNotBlank(content)){
			content = URLDecoder.decode(content);
			return content;
		}
		
		return null;
	}
	
	public static String getjavaeyeBlogContent(String url){
		

		 HttpGet httpGet = new HttpGet(url);
		 httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		// httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		 httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
	         
		 
        httpGet.setHeader("Host", "m635674608.iteye.com");
        httpGet.setHeader("Upgrade-Insecure-Requests","1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
        httpGet.setHeader("Cookie", "_javaeye_cookie_id_=1503710070572891; remember_me=yes; login_token=NzM4ODA2X2I0NDA4MGY1N2ExMWQyNmI3NWJmMDc3ZTFkMDI3NGM2%0A; Hm_lvt_e19a8b00cf63f716d774540875007664=1512878614,1512878771,1512879085,1512879708; Hm_lpvt_e19a8b00cf63f716d774540875007664=1512882009; dc_tos=p0qb89; dc_session_id=1512879708791_0.9657392941342531; _javaeye3_session_=BAh7CToQX2NzcmZfdG9rZW4iMUJDMnNGcE90MnBSQUZiRzUzUHdueld5SmkveCtHazFSOEJCYzlvSm9vOUE9Ogx1c2VyX2lkaQP2RQs6E3NpbXBsZV9jYXB0Y2hhIi03ZTIzZjYzM2I0Y2Y0NDVlYTY1ZWQ0ZWIzNGY5NjUwZjY5MjMwODcxOg9zZXNzaW9uX2lkIiVjM2Q3OGNhOWI4ZDkyYzkwZDJkYWRiMjc0ZjZkNDkyNg%3D%3D--07dd2b9d7a1a4557b3fd0a308a5b0b8b25f98084");
     return		HttpClientUtil.sendGetRequest(httpGet, "utf-8",null);
		
		
	}
	
	public static String getTitle(String id)throws Exception{
		File file = new File(fileDir,id);
		Document document = Jsoup.parse(FileUtils.readFileToString(file));
		//String content = document.select(".blog_title").select("a").text();
		Elements elements = document.select(".blog_title").select("a");
		String content =elements.size()>0?elements.get(0).text():"";
		
		if(StringUtils.isNotBlank(content)){
			//content = URLEncoder.encode(content);
			return content;
		}
		return ""+content;
	}
	
	
	
	
	
	public static boolean send(String title,String content)throws Exception{
		String draft = getdraft(title, content);
		if(StringUtils.isNotBlank(draft)){
			Thread.sleep(1000);
			String sendData="draft="+draft+"&user_code=wu7fxTLkG9vDXBEn2b0Sq5lt8EyAjQlSvDay9EQd&id=&catalog=3427650&title="+title+"&content_type=4&tags=&classification=428602&type=1&origin_url=&privacy=0&deny_comment=0&as_top=0&isRecommend=on&abstracts=&content="+content;
			String reqURL="https://my.oschina.net/action/blog/save";
			HttpPost httpPost   = new HttpPost(reqURL);
			httpPost.setHeader("Cookie",ck);
					httpPost.setHeader("Referer","https://my.oschina.net/xiaominmin/blog/edit");
		    httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
		    httpPost.setHeader("Host","my.oschina.net");
			String str = HttpClientUtil.sendPostRequest(httpPost, sendData, false, "UTF-8","UTF-8");
		    System.out.println(str);
		    if(StringUtils.isNotBlank(str) && str.contains("id")){
		    	return true;
		    }
		}
		return false;
	    
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
	
	
	public static void javaeye()throws Exception {
		/*WebDriver driver = SeleniumUtil.initChromeDriverLocal();
	    driver.get("https://my.oschina.net/xiaominmin/blog/edit");*/
	    
	    //WebDriver firefoxDriver = SeleniumUtil.initChromeDriverLocal();
	    //firefoxDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    for(int i=166;i<=190;i++){
	    	try{
	    		for(int j=0;j<10;j++){
	    			boolean flag =javaPage2(i);
	    			if(flag){
	    				break;
	    			}
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	}
	
	
	public static void donwloadErrorFIle()throws Exception{
		  for(String url:FileUtils.readLines(new File("d:\\errorjavaeye2.txt"))){
			  try {
				  String id = url.split("\\\\")[3];
				  File eyeFile = new File(new File("E:\\javaeye2\\200\\"),""+id);
				  if(eyeFile.exists()){
					  continue;
				  }
				  url = "http://m635674608.iteye.com/blog/"+id;
				  System.out.println("url==="+url+" 开始下载");
				  String content =getjavaeyeBlogContent(url);
				  if(StringUtils.isNotBlank(content) && content.contains("m635674608")){
					  FileUtils.write(eyeFile, content);
					  Thread.sleep(2000);
				  }else{
					  System.out.println("文件加载不成功>>>>>>>>>>>>>>>"+content);
					  FileUtils.write(new File(eyeFile,"error_"+id), content);
					  Thread.sleep(2000);
				  }
				} catch (Exception e) {
					e.printStackTrace();
				}
		  }
	}
	
	
	   public static boolean javaPage2(int i){
		   boolean flag = false;
		   File file = new File(fileDir, ""+i);
		   if(!file.exists()){
			    file.mkdirs();
		   }
		   
		   try{
		    String urlList = "http://m635674608.iteye.com/?page="+i;
		    System.out.println("当前抓取第"+i+"页数据>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		    //List<WebElement> elements = firefoxDriver.findElements(By.className("blog_main"));  
		    String contentList = getjavaeyeBlogContent(urlList);
		    if(StringUtils.isBlank(contentList)){
		    	return flag;
		    }
		    
		    List<String> pageURLList = new ArrayList<String>();
			Document document = Jsoup.parse(contentList);
			Elements elements = document.select(".blog_main");
			System.out.println("当前页面有："+elements.size());
			  for(Element e:elements){
			    	String title = e.select(".blog_title").text();
			       // System.out.println("titile==="+title);
			        String href= e.select(".blog_title").select("a").get(0).attr("href");
			        System.out.println("============"+baseURL+href);  
			        pageURLList.add(baseURL+href);
			  }
			  for(String url:pageURLList){
				  try {
					  String id = url.replace(baseURL, "").replace("/blog/", "");
					  File eyeFile = new File(file,""+id);
					  if(eyeFile.exists()){
						  continue;
					  }
					  System.out.println("url==="+url+" 开始下载");
					  String content =getjavaeyeBlogContent(url);
					  if(StringUtils.isNotBlank(content) && content.contains("m635674608")){
						  FileUtils.write(eyeFile, content);
						  Thread.sleep(2000);
					  }else{
						  System.out.println("文件加载不成功>>>>>>>>>>>>>>>"+content);
						  FileUtils.write(new File(file,"error_"+id), content);
						  Thread.sleep(2000);
				
					  }
					} catch (Exception e) {
						e.printStackTrace();
					}
			  }
			  flag = true;
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		   
		   return flag;
			  
	   }
	
	
   public static boolean javaPage( WebDriver firefoxDriver ,int i){
	   boolean flag = false;
	   File file = new File(fileDir, ""+i);
	   if(!file.exists()){
		    file.mkdirs();
	   }
	   
	   try{
	    webGet(firefoxDriver, "http://m635674608.iteye.com/?page="+i);
	    System.out.println("当前抓取第"+i+"页数据>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	    List<WebElement> elements = firefoxDriver.findElements(By.className("blog_main"));  
	    System.out.println("elements:"+elements.size());
	    
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
			  try {
				  String id = url.replace(baseURL, "").replace("/blog/", "");
				  File eyeFile = new File(file,""+id);
				  if(eyeFile.exists()){
					  continue;
				  }
				  webGet(firefoxDriver,url);
				  String content = firefoxDriver.getPageSource();
				  if(content.contains("zzm")){
					  document = Jsoup.parse(content);
					  System.out.println("id==="+id);
					  //System.out.println("========"+document.text());
					  FileUtils.write(eyeFile, document.toString());
					  Thread.sleep(2000);
				  }else{
					  System.out.println("文件加载不成功>>>>>>>>>>>>>>>");
				  }
				} catch (Exception e) {
					e.printStackTrace();
				}
		  }
		  flag = true;
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	   
	   return flag;
		  
   }
	
	public static void webGet(WebDriver webDriver, String url) {
		/*try{
			String title = webDriver.getTitle();
		}catch(Exception e){
			System.out.println("浏览器关闭，程序退出");
			System.exit(0);
		}*/
		
	
		try {
			webDriver.get(url);
		} catch (Exception e) {
			
			  try { Thread.sleep(100); 
			  Thread.sleep(1000);
			 JavascriptExecutor js =
			  (JavascriptExecutor) webDriver;
			 // js.executeScript("window.stop();");
			 System.out.println("已停止加载页面》》》》》》》》》》》》》》》》》》》》》》》》");
			 if(url.contains("?")){
				  webDriver.get(url+"&temp="+System.currentTimeMillis());
			 }else{
				  webDriver.get(url+"?temp="+System.currentTimeMillis());
			 }
			
		} catch (Exception e1) {
			  e1.printStackTrace(); 
			  webDriver.get(url+"&temp="+System.currentTimeMillis());
			  //webDriver.close();
			  //webDriver = SeleniumUtil.initChromeDriverLocal();
			 // webDriver.navigate().refresh();
			  
			  }
	}
}
	
}
