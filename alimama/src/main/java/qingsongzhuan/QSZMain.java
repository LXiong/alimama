package qingsongzhuan;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;

import fx.IPDao;
import fx.PropertiesUtil;

public class QSZMain {
	
	String fileName = "D:\\qsz"+DateFormatUtils.format(new Date(), "yyyyMMdd");
	
	IPDao ipDao = new IPDao(new File(fileName));
	
	
	public static void main(String[] args)throws Exception {
		/*for(int i=0;i<10;i++){
			System.out.println(new Main().getRandomAurl());
		}*/
		new QSZMain().executeAll();
		
	}
	
	public void execute(String ip,int prot,String url){
		try{
			HttpHost host = new HttpHost(ip, prot);;
			QSZHttp http = new QSZHttp();
			http.aurl = url;
			http.host = host;
			http.execute();
			System.out.println("execute ok>>>>>>>>>>>>>>>>>>>>>>");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void executeAll(HttpHost httpHost)throws Exception{
		System.out.println("httpHost :"+httpHost);
		String ip  =httpHost.getHostName();
	//synchronized (ip) {
		boolean flag = checkIp(httpHost.getHostName(), httpHost.getPort());
		System.out.println("checkIp : "+flag);
		if(flag){
			String url = getRandomAurl();
			System.out.println("aurl : " +url);
			execute(httpHost.getHostName(), httpHost.getPort(), url);
			System.out.println("execute end >>>>>>>>>>>>>>>>>>>>>>>>");
			destroy(httpHost.getHostName());
			System.out.println("destroy end >>>>>>>>>>>>>>>>>>>>>>>>");
		 }
		//}	
		
	}
	
	public void executeAll()throws Exception{
		HttpHost httpHost = getIp();
		executeAll(httpHost);
	}
	
	
	public HttpHost getIp(){
		HttpHost host = new HttpHost("58.52.249.56", 8998);
		return host;
	}
	
	public  boolean checkIp(String ip,int prot){
			   boolean flag = ipDao.containsKey(ip);
			    if(!flag){
			    	//checkIp
			    	HttpGet httpReq = new HttpGet(
							"http://haitmall.com/login.php");
					QSZHttp http = new QSZHttp();
					HttpHost host = new HttpHost(ip, prot);;
					String s = http.getContentByUrl(host, httpReq);
					if(StringUtils.isNotBlank(s)){
						System.out.println("ip youxiao >>>>>>>>>>>>>>>>>>>>");
						return true;
					}else{
						System.out.println("ip wuxiao >>>>>>>>>>>>>>>>>>>>");
					}
			    }
				return false;
	}
	
	public void destroy(String ip){
		ipDao.addIp(ip);
	}
	
	
	public static List<String> aurls = new ArrayList<String>();
	
	static
	{
		String aurl = PropertiesUtil.getProperty("aurl");
		System.out.println("aurl>>>>>>>>>>>>>>> "+aurl);
		for(String url:aurl.split(",")){
			aurls.add(url);
		}
		
	}
	
	public String getRandomAurl(){
		return aurls.get(new Random().nextInt(aurls.size()));
	}

}
