package dataoke;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;

import util.HttpClientUtil;
import util.IpPoolUtil;

public class SendMail {
	
	static  HttpHost proxy = null;
	
	static List<String> Failmails = new CopyOnWriteArrayList<String>();
    static int len = 10;   
    static int allSize = 0;
    static int okSize = 0;
    static String id = null;
	public static void main(String[] args) throws Exception{
		
		if (args != null && args.length >= 1) {
			System.out.println("id==============="+id);
			id = args[0];
		}
		
		if (args != null && args.length >= 2) {
			len = Integer.valueOf(args[1]);
		}
		
		// TODO Auto-generated method stub
		//
		List<String> mails = FileUtils.readLines(new File("d:\\mails.txt"));
		System.out.println("一共需要发>>>>>>>>>>>>>>>邮件数量："+mails.size());
		for(String m:mails){
			if(StringUtils.isBlank(m)){
				continue;
			}
			if(!m.contains("@")){
				m = m+"@qq.com";
			}
			try{
				allSize+=1;
				proxy  = IpPoolUtil.getHttpHost();
				System.out.println("ip================"+proxy);
				boolean flag = loginHttpClient(m,id);
				if(flag){
					okSize+=1;
				}
				System.out.println("当前已经执行："+allSize+"  当前已经成功: "+okSize);
				TimeUnit.SECONDS.sleep(len);
			}catch(Exception e){
				
			}
			
		}
		
		//FileUtils.writeLines(new File("fialMail.txt"), Failmails);
		
		/*for(String m:Failmails){
			if(StringUtils.isBlank(m)){
				continue;
			}
			try{
				proxy  = IpPoolUtil.getHttpHost();
				System.out.println("ip================"+proxy);
				loginHttpClient(m);
				Thread.sleep(2000);
			}catch(Exception e){
				
			}
		}*/
		
		
		
	
	}

	public static boolean loginHttpClient(String mail,String id)
			throws Exception {
		System.out.println("开始查找cookis文件是否存在>>>>>>>>>>>>>>>>>>");

		String baseURI = "http://list.qq.com/cgi-bin/qf_compose_send";
		HttpPost httpRequest = new HttpPost(baseURI);
		httpRequest.setHeader("Content-Type",
				"application/x-www-form-urlencoded");
		httpRequest.setHeader("Host", "list.qq.com");
		httpRequest
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequest
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
		httpRequest.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequest
				.setHeader(
						"Referer",
						"http://list.qq.com/cgi-bin/qf_invite?id=647606dc58eb7e0c3d6544cede97ec65d2f56b477d2dcd0c");
		httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequest.setHeader("Connection", "keep-alive");

		String p = "t=qf_booked_feedback&id="+id+"&to="+mail;

		String str = HttpClientUtil.sendPostRequest(httpRequest, p, true, null,
				null, proxy, null);
		//System.out.println("str:" + str);
		
		if(str != null && str.contains("感谢您订阅")){
			System.out.println("发送邮件成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+mail);
			return true;
		}else{
			Failmails.add(mail);
			FileUtils.write(new File("fialMail.txt"), mail+"\r\n",true);
			System.out.println("发送频繁>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>...发送失败 "+mail);
		}
		return false;

	}

}
