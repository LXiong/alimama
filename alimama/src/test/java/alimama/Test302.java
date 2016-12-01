package alimama;

import util.HtmlUnitUtil;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test302 {
	public static void main(String[] args)throws Exception {
		WebClient webClient = HtmlUnitUtil.create();
		HtmlPage htmlPage = webClient.getPage("http://s.click.taobao.com/8NF1HQx");
		
		Thread.sleep(1000);
		
		/*System.out.println(htmlPage.getUrl());
		htmlPage = webClient.getPage("http://s.click.taobao.com/t_js?tu=http%3A%2F%2Fs.click.taobao.com%2Ft%3Fe%3Dm%253D2%2526s%253Db1p5j7LwD1UcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67jjoSPUdWv%252F4VNjKoH%252FaCQPRGePCFkAxw1IwogiijGrXXM5lrk7Nf8aaImqn1I7ptgIGMGGjWrSklrfKbc84rlduVmJJG43TQWl81IfQPxUgomfkDJRs%252BhU%253D%26pvid%3D10_27.151.93.69_5766_1477199325118%26ref%3D%26et%3DX361fgWzYAU%252BFl40OhTkQ87gYurDEZH1");
		Thread.sleep(1000);*/
		/*
		ScriptResult scriptResult = htmlPage.executeJavaScript("bol();");
		
		System.out.println(scriptResult.toString());
		Thread.sleep(1000);*/
		
		//System.out.println(htmlPage.getUrl());
		
		//System.out.println(htmlPage.getPage().asXml());
		
		String url = htmlPage.getUrl().toString();
		System.out.println(url);
		
		
		HtmlElement domElement =  (HtmlElement) htmlPage.getElementById("exe");
		htmlPage = domElement.click();
		
		 url = htmlPage.getUrl().toString();
		System.out.println(url);
		
		
         htmlPage = webClient.getPage(url);
		
		url = htmlPage.getUrl().toString();
	    System.out.println(url);
	    
		
		
		
		url = HttpClientDemo302.getRedirectInfo2(url);
		System.out.println(url);
		
		
		
		
		
	}
}
