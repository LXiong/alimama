package blog;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class OsChainSDK {

	static String baseURL = "https://www.oschina.net";
	
	static String access_token	="523f7854-9256-44af-8312-54538ddd544";//true	string	oauth2_token获取的access_token	ic
	
	static String client_id = "";
	
	static String client_secret = "";
	
	
	static String ck  = "_user_behavior_=c8fb9227-9234-40a7-b054-e4ef95a29128; __DAYU_PP=qiaUA236bfMEB2MiFAVr3d1386561de7; oscid=Qgdg8wRz2OcsdD4fp3FCcYwgebG1JwCmSPlVjKxReUbSNKmwdJT1KHu7q3xEwVlbKnTr%2FnXJ0phWKE%2Byr1pxcwGqY1FTfsDxg4t8O2Dr9el%2BbZSWQHtE6Sn7okJzEcTRkJ488fKQSm0ApT7CsKI%2BlwUb4rXZ3uJo1j4%2BJnWf3o46ZSBYvrRDow%3D%3D; aliyungf_tc=AQAAAGlKMhL34wsAKArnc+lbb+SsR3EA; Hm_lvt_a411c4d1664dd70048ee98afe7b28f0b=1513166342,1513349920,1513478141,1514543795; Hm_lpvt_a411c4d1664dd70048ee98afe7b28f0b=1514545234";
	
	
	public static void main(String[] args) {
		//test();
		
		
		getLog();
	}
	
	static{
		start();
	}
	
	public static void start(){
		if(StringUtils.isNotBlank(access_token)){
			return;
		}
		String code = authorize2();
		String rs = token(code);
		JSONObject jsonObject = JSONObject.parseObject(rs);
		access_token = jsonObject.getString("access_token");
	    
	    String refresh_token = jsonObject.getString("refresh_token");
	    
	    String title= "test";
	    String content= "test1111111111111111111111111111111111111111111111111111111";
	    String origin_url = "https://www.oschina.net";
		//boolean flag = execute(access_token,title,content,origin_url);
		//System.out.println("文件上传》》》》》》》》》》》》》》》》》》"+flag);
	}
	
	public static Map<String,String> getLog(){
		   String url  = baseURL+"/action/openapi/blog_catalog_list";
		    
		    HttpRequest httpRequest = HttpRequest.post(url); //1. 构建一个get请求
			httpRequest.header("Host", "www.oschina.net");
			httpRequest.header("Cookie", ck);
			httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
		   
			httpRequest.form("access_token", access_token);
			httpRequest.form("authoruname", "xiaomin0322");
			
			HttpResponse response = httpRequest.send();; //2.发送请求并接受响应信息
			response.charset("utf-8");
			//String str = UnicodeUtils.unicode2String(response.body());
			// System.out.println("上传文件返回json==="+response.bodyText());
			 
			 Map<String,String> map = new HashMap<String, String>();
		    JSONObject jsonObject2 = JSONObject.parseObject(response.bodyText());
		    
		    JSONArray array = jsonObject2.getJSONArray("blog_sys_catalog_list");
		    for(Object o:array){
		    	JSONObject jsono = (JSONObject)o;
		    	map.put(jsono.getString("name"), jsono.getString("id"));
		    }
		   System.out.println(map);
		   return map;
	}
	
	public static boolean execute(String token,String title,String content,String origin_url,String catalog){
		if(token==null){
			token = access_token;
		}
	    
	    String url  = baseURL+"/action/openapi/blog_pub";
	    
	    HttpRequest httpRequest = HttpRequest.post(url); //1. 构建一个get请求
		httpRequest.header("Host", "www.oschina.net");
		httpRequest.header("Cookie",ck);
		httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
	   
		
		httpRequest.form("access_token", token);
		httpRequest.form("title", title);
		httpRequest.form("content", content);
		httpRequest.form("type", 4);
		httpRequest.form("origin_url", origin_url);
		//系统博客分类 编程语言
		httpRequest.form("classification",428609);
		//博客分类  分类
		httpRequest.form("catalog",catalog);
		
		
		HttpResponse response = httpRequest.send().accept("utf-8"); //2.发送请求并接受响应信息
		
	    JSONObject jsonObject2 = JSONObject.parseObject(response.body());
	    
	    
	    System.out.println("上传文件返回json==="+jsonObject2);
	    String error = jsonObject2.getString("error");
	    if("200".equals(error)){
	    	return true;
	    }
	    return false;
	    
	    
	}
	
	
	public static String authorize2(){
		String url = "https://www.oschina.net/action/oauth2/authorize?client_id="+client_id+"&response_type=code&redirect_uri=http%3A%2F%2Fm635674608.iteye.com%2F&scope=blog_api%2Ccomment_api%2Cfavorite_api%2Cmessage_api%2Cnews_api%2Cnotice_api%2Cpost_api%2Cproject_api%2Csearch_api%2Ctweet_api%2Cuser_api%2Cuser_mod_api%2C&state=&user_oauth_approval=true";
		HttpRequest httpRequest = HttpRequest.post(url); //1. 构建一个get请求
		httpRequest.header("Host", "www.oschina.net");
		httpRequest.header("Cookie",ck);
		httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
	    HttpResponse response = httpRequest.send(); //2.发送请求并接受响应信息
	    System.out.println(response);//3.打印响应信息
	    
	    String Location = response.header("Location");
	    Location = Location.replace("&state=", "").replace("http://m635674608.iteye.com/?code=", "");
	    
	    System.out.println(Location);//3.打印响应信息
	    
	    
		return Location;
	}
	
	
	public static String authorize(){
		String url = "https://www.oschina.net/action/oauth2/authorize?response_type=code&state=xyz&client_id="+client_id+"&redirect_uri=http://m635674608.iteye.com/";
		HttpRequest httpRequest = HttpRequest.get(url); //1. 构建一个get请求
	    HttpResponse response = httpRequest.send(); //2.发送请求并接受响应信息
	    System.out.println(response);//3.打印响应信息
		return null;
	}
	
	public static String token(String code){
 		String authorizeURL= baseURL + "/action/openapi/token?client_id="+client_id+"&client_secret="+client_secret+"&"
    			+ "grant_type=authorization_code&redirect_uri=http://m635674608.iteye.com/&code="+
    	code+"&dataType=json";
 		HttpRequest httpRequest = HttpRequest.post(authorizeURL); //1. 构建一个get请求
		httpRequest.header("Host", "www.oschina.net");
		httpRequest.header("Cookie",ck);
		httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
	    HttpResponse response = httpRequest.send(); //2.发送请求并接受响应信息
	    System.out.println(response);//3.打印响应信息
	    return response.body();
	}
}
