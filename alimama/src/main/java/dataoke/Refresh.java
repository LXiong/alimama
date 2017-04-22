package dataoke;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jodd.http.Cookie;

public class Refresh {
	
	static Map<String,Cookie[]> map = new HashMap<String, Cookie[]>();
	
	public static void main(String[] args) throws Exception{
		//boolean flag = tuijian("2231931");
		//System.out.println("推广成功》》》》》》》》》》》》》》》》》》》"+flag);
	
		//executeTest();
	  // testck();
		//File[] files = new File[]{new File("d:\\taoke\\第1组500.txt")};
		//execteAll("2255030",new File("G:\\taoke\\第4组.txt"));
		//Test.execteAll(new String[]{"2263225","2262582","2255030"},files);
		File[] files = new File[]{new File("d:\\taoke\\大淘客帐号\\B批第3组500.txt")};
		Test.execteAll(new String[]{"2262582"},files);
		
	}
	
	
}
