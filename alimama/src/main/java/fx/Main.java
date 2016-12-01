package fx;

import org.apache.http.HttpHost;

public class Main {
	
	public void execute(String ip,int prot,String url)throws Exception{
		HttpHost host = new HttpHost(ip, prot);;
		HttpTest http = new HttpTest();
		http.aurl = url;
		http.host = host;
		http.execute();
	}
	
	public HttpHost getIp(){
		
		return null;
	}
	
	public boolean checkIp(String ip,int prot){
	
		return false;
	}
	
	public void destroy(String ip){
		
	}

}
