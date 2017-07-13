package dataoke;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import player.AudioPlayerUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Monitor {

	static String tuijianURL = "http://www.dataoke.com/top_tui";
	static String shishiURL = "http://www.dataoke.com/top_sell";
	static String quanTianURL = "http://www.dataoke.com/top_all";
	//多久检测一次
	static int sleepTime = 60;
	//少于多少在线报警
	static int monitorSize = 3000;
	//每次播放报警音乐时间
	static int map2PlayTime = 20;
	
	static boolean isPaly = false;
	//监控指标  1.推广帮 2.实时销量榜 3.全天销量榜 4.在线
	
	static String monitorStr = "1,2,3,4";
	
	public static void main(String[] args) throws Exception{
		//java -jar dataokeJiankong.jar 2976176,2943079 1234 3000 30
		//args = new String[]{"2976176,2943079","1234","3000","30"};
		
		String[] ids = args[0].split(",");
		
		if(args.length >=2){
			monitorStr = args[1];
		}
		
		if(args.length >=3){
			monitorSize = Integer.valueOf(args[2]);
		}
		
		if(args.length >=4){
			sleepTime = Integer.valueOf(args[3]);
		}
		
		
		System.out.println("当前需要监控的id==="+Arrays.toString(ids));
		System.out.println("当前多久检测一次===="+sleepTime+ " 秒");
		System.out.println("少于多少在线报警===="+monitorSize+"次");
		System.out.println("监控指标===="+monitorStr);
		
		Thread.sleep(3000);
		
		execute(ids);
	}
	
	public static void test(){
		JSONObject jsonObject = getInfoTianMao("549930979963");
		String view_count = jsonObject.getJSONObject("listDesc").getString("view_count");
		System.out.println(view_count);
	}
	
	public static JSONObject getInfoTianMao(String id) {
		try{
		String url = "https://lu.taobao.com/api/item?type=view_count&id="+id+"&_ksTS=1412581003175_109&callback=1&p=u&from=mypath&f=jp&jsonpCallback=jsonp";
		 HttpRequest httpRequest = null;
		 httpRequest= HttpRequest.post(url).timeout(30000);
		 httpRequest.header("origin", "https://detail.tmall.com");
		 httpRequest.header("accept-language", "zh-CN,zh;q=0.8");
		 
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.132 Safari/537.36");
		 httpRequest.header("accept", "text/plain, */*; q=0.01");
		 httpRequest.header("Referer", "https://detail.tmall.com/item.htm?id="+id);
		 //https://item.taobao.com/item.htm?spm=a230r.1.14.75.ebb2eb2LAqj38&id=549930979963&ns=1&abbucket=13
		// httpRequest.header("Referer", "https://item.taobao.com/item.htm?id="+id);
		 httpRequest.header("authority", "lu.taobao.com");
		 httpRequest.header("cookie","thw=cn; miid=8767858260596032311; UM_distinctid=15b3c086a1f2a9-08f0308c3-444a052a-15f900-15b3c086a202dc; l=AiQkl38p5do46/vULqq5BFt8dCwWZUgm; uc3=sg2=VynLSCec7A7pTNSwg%2FoXLs1Hz7DJYPzRfblXSzc6lZ0%3D&nk2=G4mgLD5MDidItXRYzw%3D%3D&id2=VynM0oskbdVN&vt3=F8dBzWEZLlL8CR7Gc0s%3D&lg2=WqG3DMC9VAQiUQ%3D%3D; uss=U7Y%2F4W35y%2BCDv1E%2BLYNjA5t7UILk7a6PkJV0fNzhZozqfahiBidkKaC%2FmdE%3D; lgc=xiaomi1991222; tracknick=xiaomi1991222; mt=np=&ci=0_1&cyk=-1_-1; _cc_=VT5L2FSpdA%3D%3D; tg=0; cna=UOdjDzyFDHgCAXTnNSwqohIL; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; t=5cc09c0cbb708ad357ed7d5ebfce7988; cookie2=106d29dfe0bb95b8f589890283dfa27c; _tb_token_=91c7bb3178ec; v=0; _m_h5_tk=c9bfcec57485ddda450c4bfde78c6062_1499767867577; _m_h5_tk_enc=38451495fe29fc876cc96cf720fbf3c5; isg=Ag8PUoFbimJ6MoBjes5aH7u6nqO1JGNMCoen1SEcq36F8C_yKQTzpg2ihhU3; uc1=cookie14=UoW%2BsWKGqvy5XQ%3D%3D");
		 httpRequest.header("content-length", "0");	 
		 HttpResponse response = httpRequest.send();
		 String rc = response.bodyText();
		 if(StringUtils.isBlank(rc)){
			 return null;
		 }
		// System.out.println(rc);
		 rc = rc.substring(2, rc.length()-1);
		 JSONObject jsonObject = JSON.parseObject(rc);
		 return jsonObject;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	static String bangName = "";
	public static void execute(String... ids){
		for(;;){
			try{
				String tuijianb = null;
				if(monitorStr.contains("1")){
					bangName = "推荐榜 ";
					 tuijianb = bangName+" ："+paiMing(tuijianURL,ids);
			        System.out.println(tuijianb);
			        Thread.sleep(1000);
				}
				
				if(monitorStr.contains("2")){
					 bangName = "实时销量榜";
				        tuijianb = bangName+" ："+paiMing(shishiURL,ids);
				        System.out.println(tuijianb);
				        Thread.sleep(1000);
				}
				
				if(monitorStr.contains("3")){
					bangName = "全天销量榜 ";
			        tuijianb = bangName+" ："+paiMing(quanTianURL,ids);
			        System.out.println(tuijianb);
				}
		        
		        System.out.println("当前时间>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					TimeUnit.SECONDS.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String  paiMing(String url,String... ids)throws Exception{
		List<Element> list = getTopIds(url);
		StringBuilder builderAll =new StringBuilder();
		for(String id:ids){
			//System.out.println("开始监控>>>>>>>>>>>>id="+id);
			MonitorObj monitorObj  = null;
			for(int i=0;i<list.size();i++){
				Element e = list.get(i);
				String pid = e.attr("id").replace("goods-items_", "");
				if(id.equalsIgnoreCase(pid)){
					monitorObj = new MonitorObj();
					String top = e.select("i").text().replace("￥", "").replace(" ", "");
					monitorObj.setId(id);
					monitorObj.setTop(top);
					if(monitorStr.contains("4")){
						//在线人数>>>>>>>>>>>>>>>>>>>>
						String data_goodsid = e.attr("data_goodsid");
						if(StringUtils.isNotBlank(data_goodsid)){
							JSONObject jsonObject = getInfoTianMao(data_goodsid);
							if(jsonObject!=null){
								String view_count = jsonObject.getJSONObject("listDesc").getString("view_count");
								int countInt = Integer.valueOf(view_count);
								monitorObj.setOnlineSize(countInt);
							}
						}
					}
					
					break;
				}
			}
			if(monitorObj==null){
				System.out.println(" id="+id+";不在"+bangName+" 榜上 | ");
				play();
			}else if(monitorSize > monitorObj.getOnlineSize() && monitorStr.contains("4")){
				System.out.println(" id="+id+" 在线:"+monitorObj.getOnlineSize());
				play();
			}else{
				builderAll.append(monitorObj);
			}
			System.out.println();
			TimeUnit.SECONDS.sleep(1);
		}
		return builderAll.toString();
	}
	
	public static void play(){
		new Thread(){
			@Override
			public void run() {
				if(!isPaly){
					isPaly = true;
					AudioPlayerUtils audioPlayerUtils = new AudioPlayerUtils();
					audioPlayerUtils.startMp3();
					try {
						TimeUnit.SECONDS.sleep(map2PlayTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					audioPlayerUtils.stopMp3();
					isPaly = false;
				}
			}
		}.start();
		
	}
	
	
	public static List<Element> getTopIds(String url) throws Exception {
		List<Element> topIds = new ArrayList<Element>();
		Document document = Jsoup.parse(new URL(
				url), 100000);
		for (Element e : document.select(".goods-item")) {
			  topIds.add(e);
			}
		return topIds;
	}

}

class MonitorObj{
	String id;
	String top;
	int onlineSize;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public int getOnlineSize() {
		return onlineSize;
	}
	public void setOnlineSize(int onlineSize) {
		this.onlineSize = onlineSize;
	}
	
	@Override
	public String toString() {
		return "id="+id+";排名="+top+";在线="+onlineSize+"| ";
	}
	
}
