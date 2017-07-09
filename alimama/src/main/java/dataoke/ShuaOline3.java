package dataoke;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.gargoylesoftware.htmlunit.WebClient;

import util.HtmlUnitUtil;

public class ShuaOline3 {
	
	
	public static void main(String[] args) throws Exception{

		WebClient webClient = HtmlUnitUtil.create();
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		for(int i=0;i<10;i++){
			webClient.getPage("https://detail.m.tmall.com/item.htm?spm=0.0.0.0.5FpUFm&id=543557417955&abtest=21&rn=bbe5ec2e6fea4193aa04dbb1b8754dfd&sid=eb80b81938bb970e9c61caa81b4baf51");
			System.out.println("当前已刷>>>>>>>>>>>>>>>>>"+i);
		}
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
	}
	
	

}
