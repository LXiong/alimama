package alimama;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import alimama.qq.Main;

public class Selenium302 extends Main{
	public static void main(String[] args) throws Exception{
		
		String content =	FileUtils.readFileToString(new File("E:\\我的QQ群.txt"));
		
		List<String> urls = MatcherUtil.getUrl(content);
		
		File out = new File("d:\\out.txt");
		
		for (String string : urls) {
			System.out.println(string);
            if(string.contains("click.taobao.com")){
            	
            }
		  }
		}
	
	
	static{
		init();
		webDriver.get("http://s.click.taobao.com/8NF1HQx");
		String url = webDriver.getCurrentUrl();
		System.out.println(url);
	}
	
	public static String get302(String url)throws Exception{
		webDriver.get(url);
		Thread.sleep(1000);
	    return webDriver.getCurrentUrl();
	}
}
