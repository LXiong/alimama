package alimama;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class MatcherUtil {

	public static void main(String[] args) throws Exception{

	String content =	FileUtils.readFileToString(new File("E:\\我的QQ群.txt"));
	
	List<String> urls = getUrl(content);
	
	for (String string : urls) {
		System.out.println(string);
	}
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

	public static List<String> getUrl(String content) {
		List<String> list = matcher(content);
		
		List<String> newList = new ArrayList<String>();
		for (String str : list) {
			//if (!str.contains("shop.m.taobao.com")) {
			if ((str.contains("tmall.com") || str.contains("taobao.com")) 
					&& !str.contains("shop.m.taobao.com")
					//&& !str.contains("click.taobao.com")
					
					)
			{
				newList.add(str);
			}
		}
		return newList;

	}

}