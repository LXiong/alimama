package util;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class CheckUtils {

	
	public static boolean check(String url,String content){
		try{
			 HttpRequest httpRequest = HttpRequest.post(url).timeout(20000);
			 HttpResponse response = httpRequest.send();
			 String rc = response.bodyText();
			 if(rc.contains(content)){
				 System.out.println("验证码通过>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				 return true;
			 }else{
				 System.out.println("验证码不通过>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
