package dataoke;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jodd.http.Cookie;

public class Refresh2 {
	
	static Map<String,Cookie[]> map = new HashMap<String, Cookie[]>();
	
	public static void main(String[] args) throws Exception{
		//boolean flag = tuijian("2231931");
		//System.out.println("推广成功》》》》》》》》》》》》》》》》》》》"+flag);
	
		//executeTest();
	  // testck();
		File[] files = new File[]{new File("d:\\taoke\\大淘客帐号\\A批第1组500.txt"),new File("d:\\taoke\\大淘客帐号\\A批第2组500.txt")};
		//execteAll("2255030",new File("G:\\taoke\\第4组.txt"));
		Test.execteAll(new String[]{"2262582"},files);
		
		
	}
	
	
}
