package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class StringUtils {
	/**
	  * 
	  * @param s
	  * @return 获得链接
	  */
	 public static List<String> getLink(final String s)
	 {
	  final List<String> list = new ArrayList<String>();
	  Pattern pattern = Pattern
				.compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");
	  final Matcher ma = pattern.matcher(s);
	  while (ma.find())
	  {
	   list.add(ma.group());
	  }
	  return list;
	 }
	 
	 public static void main(String[] args)throws Exception {
		String str = "asdafadsfafad"
				+ " http://www.cnblogs.com/itcqx/p/5683961.html asdfasdf";
		str = FileUtils.readFileToString(new File("G:\\mesos整理\\html.txt"));
		System.out.println(getLink(str));
		System.out.println(getTaoBaoZhuCeURL(str));
	}
	 
	 public static String getTaoBaoZhuCeURL(String str){
		 for(String s : getLink(str)){
			 s =s.replace("href=", "").replace("\"", "");
			 if(s.startsWith("https://passport.alibaba.com")){
				 return s;
			 }
		 }
		 return null;
	 }
}
