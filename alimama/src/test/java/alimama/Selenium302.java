package alimama;

import alimama.qq.Main;

public class Selenium302 extends Main{
	public static void main(String[] args) {
		init();
		webDriver.get("http://s.click.taobao.com/8NF1HQx");
		String url = webDriver.getCurrentUrl();
		System.out.println(url);
		
	}
}
