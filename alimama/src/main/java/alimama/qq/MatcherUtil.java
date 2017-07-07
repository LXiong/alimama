package alimama.qq;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.URLDecoder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import fx.HttpTest;

public class MatcherUtil {

	public static void main(String[] args) throws Exception{
	

/*	List<List<String>> list  = new ArrayList<List<String>>();
	System.out.println(URLEncoder.encode("https://item.taobao.com/item.htm?id=537764519790"));*/
	 //String url = "https://s.click.taobao.com/t_js?tu=https%3A%2F%2Fs.click.taobao.com%2Ft%3Fe%3Dm%253D2%2526s%253DwZY1m4P9%2FlgcQipKwQzePOeEDrYVVa64LKpWJ%252Bin0XLjf2vlNIV67k6sUyt%2FHOxawSB8%2FImevICvY1yQ5DBpWzLoYiD2i%2FTkY5nwOqv%252B3Kn59b2%2FtIPC7sIb6W87vOnX17G9PHyDxxTdk3s4taYJInWE7%2FiS8F4aIYULNg46oBA%253D%26pvid%3D10_14.117.123.0_502_1497401257871%26sc%3Df6VAGiw%26ref%3D%26et%3D2SAeSbhRl%252FC618fjagayH%252BPmnTNy69Cr";
	 //System.out.println(URLDecoder.decode(url));
		//testAll();
		//testDuan();
		//System.out.println(getTaoLongURL("https://s.click.taobao.com/DWgHKiw"));
		//filter();
		System.out.println(getURIByQueryVal("https://detail.tmall.com/item.htm?id=22334731828", "id"));
	}
	
	public static String getURIByQueryVal(String uri,String key){
		 URL url=null;
		try {
			url = new URL(uri);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String queryStr = url.getQuery();
		 for(String q:queryStr.split("\\&")){
			 if(StringUtils.isNotBlank(q) && q.startsWith(key)){
				 return q.split("\\=")[1];
			 }
		 }
		 return null;
	}
	
  public static void testDuan()throws Exception{
	  List<String> content =	FileUtils.readLines(new File("g:\\test1.txt"));
	  Set<String> idSet = new HashSet<String>();
	  for(String c:content){
		  idSet.add(c);
	  }
	  File file1 = new File("g:\\test11.txt");
	  for(String c:idSet){
		  try{
			  String uri = getTaoLongURL(c);
			  if(!StringUtils.isBlank(uri)){
				  FileUtils.write(file1,uri+"\r\n",true);
			  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
	  }
	  
  }	
	
  public static void filter()throws Exception{
	  List<String> content =	FileUtils.readLines(new File("g:\\test2.txt"));
	  Set<String> idSet = new HashSet<String>();
	  for(String c:content){
		/*  String  id = c.replace("https://item.taobao.com/item.htm?id=", "").replace("https://item.taobao.com/item.htm?id=", "")
				  .replace("https://detail.tmall.com/item.htm?id=", "");
		  if("543604497753".length()==id.length()){
			  idSet.add(id);
		  }*/
		  idSet.add(c);
		 
		 /* URL url = new URL(c);
			 String queryStr = url.getQuery();
			 if(StringUtils.isBlank(queryStr)){
				 continue;
			 }
			 for(String q:queryStr.split("\\&")){
				 if(StringUtils.isNotBlank(q) && "id".equalsIgnoreCase(q) && q.contains("=")){
					 String id = q.split("\\=")[1];
					 if(StringUtils.isNotBlank(id)){
						 idSet.add(id);
					 }
					
				 }
			 }*/
	  }
	  FileUtils.writeLines(new File("g:\\test22.txt"), idSet,true);
  }
	
  public static String getTaoLongURL(String duanURL)throws Exception{
	    HttpRequest httpRequest = null;
    	 httpRequest = HttpRequest.get(duanURL).timeout(20000);
		 httpRequest.header("Content-Type", "application/json");
		 httpRequest.header("User-Agent", HttpTest.getUserAgent());
		 
		 //HttpBrowser browser = new HttpBrowser();
		 //HttpResponse response = browser.sendRequest(httpRequest);
		 
		 HttpResponse response = httpRequest.send();
		 String location = response.header("Location");
		// System.out.println(location);
		 
		 Thread.sleep(100);
		 httpRequest.set(location);
		 response = httpRequest.send();
		 String location2 = response.header("Location");
		 location2 = URLDecoder.decode(location2);
		 //System.out.println(location2);

		 
		 String addStr = "&ref=&et=";
		 
		 URL url = new URL(location2);
		 String queryStr = url.getQuery();
		 for(String q:queryStr.split("\\&")){
			 if(StringUtils.isNotBlank(q) && q.startsWith("et")){
				 addStr = addStr + q.split("\\=")[1];
				 break;
			 }
		 }
		 
		 String nowURL = location + addStr;
		// System.out.println("newURL =="+nowURL);
		 
		 Thread.sleep(100);
		 httpRequest.set(nowURL);
		 httpRequest.header("Referer", location2);
		 response = httpRequest.send();
		 location2 = response.header("Location");
		// System.out.println(location2);
		 
		/* response.charset("gbk");
		 System.out.println(response.bodyText());*/
		 
		 
		 return location2;
  }
	
	
   public static void testAll()throws Exception{
	   File baseDir = new File("D:\\dataoke\\记录");	
		for(File f:baseDir.listFiles()){
			String content =	FileUtils.readFileToString(f);
			List<String> urls = getUrl(content);
			System.out.println(urls.size());
			//FileUtils.writeLines(new File("g:\\test.txt"), urls,true);
		}	
   }
	
	 /** 
     * 拆分任务 
     *  
     * @param tasks 
     * @param 拆分数量 
     * @return 
     */  
    public static <T> List<T> mergeTask(List<List<T>> tasks) {  
        if(tasks==null){  
            return null;  
        }  
        List<T> list = Lists.newArrayList();  
        for(List<T> l:tasks){  
            if(l!=null){  
                list.addAll(l);  
            }  
        }  
        return list;  
    }  
  
    /** 
     * 拆分任务 
     *  
     * @param tasks 
     * @param 拆分数量 
     * @return 
     */  
    public static <T> List<List<T>> splitTask(List<T> tasks, Integer taskSize) {  
        List<List<T>> list = new ArrayList<List<T>>();   
        if(tasks==null || taskSize <= 0){  
            return list;  
        }   
        if(tasks.size() < taskSize){  
            list.add(tasks);  
            return list;  
        }  
           
          
        int baseNum = tasks.size() / taskSize; // 每个list的最小size  
        int remNum = tasks.size() % taskSize; // 得到余数  
  
        int index = 0;  
        for (int i = 0; i < taskSize; i++) {  
            int arrNum = baseNum; // 每个list对应的size  
            if (i < remNum) {  
                arrNum += 1;  
            }  
            List<T> ls = new ArrayList<T>();  
            for (int j = index; j < arrNum + index; j++) {  
                ls.add(tasks.get(j));  
            }  
            list.add(ls);  
            index += arrNum;  
        }  
        return list;  
    }  
  
  

	public static List<String> matcher(String content) {
		Pattern p = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?");

		// Matcher m = p.matcher(
		// "多功能儿童智能手表手机天猫券后【69元】包邮秒杀领券:http://shop.m.taobao.com/shop/coupon.htm?activity_id=e4f0d0eeda8a45b78ea1ee2adf0f82c9&seller_id=1699430130
		// \r\n 抢购:http://s.click.taobao.com/XqZdaRx");
		Matcher m = p.matcher(content);

		List<String> list = new ArrayList<String>();
		while (m.find()) {
			/// System.out.println(m.group());
			list.add(m.group());
		}
		return list;

	}

	public static List<String> getUrl(String content)throws Exception {
		List<String> list = matcher(content);
		
		List<String> newList = new ArrayList<String>();
		for (String str : list) {
			//if (!str.contains("shop.m.taobao.com")) {
			
			/*if(!str.contains("id")){
				continue;
			}
			*/
			if ((str.contains("tmall.com") || str.contains("taobao.com")) 
					&& !str.contains("shop.m.taobao.com")
					//&& !str.contains("click.taobao.com")
					&& !str.contains("taoquan.taobao.com")
					&& !str.contains("market.m.taobao.com")) {
				newList.add(str);
				if(str.contains("click")){
					//FileUtils.write(new File("g:\\test1.txt"), str+"\r\n",true);
				}else{
					FileUtils.write(new File("g:\\test2.txt"), str+"\r\n",true);
				}
			}
		}
		return newList;

	}

}