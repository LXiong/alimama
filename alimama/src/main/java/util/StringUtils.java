package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 
	 public static void main(String[] args) {
		String str = "asdafadsfafad"
				+ " http://www.cnblogs.com/itcqx/p/5683961.html asdfasdf";
		
		System.out.println(getLink(str));
	}
}
