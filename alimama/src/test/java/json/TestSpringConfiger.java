package json;

import jodd.http.HttpRequest;
import util.HttpClientUtil;

public class TestSpringConfiger {
	
	public static void main(String[] args) {
		String s = HttpClientUtil.sendPostRequest("http://localhost:1111/refresh",null, true);
		HttpRequest  httpRequest = HttpRequest.post("http://localhost:1111/refresh");
		
		
		System.out.println(httpRequest.send().body());	
		
		
		}

}
