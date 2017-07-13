package dataoke;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Monitor {

	static String tuijianURL = "http://www.dataoke.com/top_tui";
	static String shishiURL = "http://www.dataoke.com/top_sell";
	static String quanTianURL = "http://www.dataoke.com/top_all";
	public static void main(String[] args) throws Exception{
		 execute("2976176","2943079");
	}
	
	public static void execute(String... ids){
		for(;;){
			try{
				String tuijianb = "推荐榜  ："+paiMing(tuijianURL,ids);
		        System.out.println(tuijianb);
		        Thread.sleep(1000);
		        tuijianb = "实时销量榜  ："+paiMing(shishiURL,ids);
		        System.out.println(tuijianb);
		        Thread.sleep(1000);
		        tuijianb = "全天销量榜  ："+paiMing(quanTianURL,ids);
		        System.out.println(tuijianb);
		        System.out.println("当前时间>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					Thread.sleep(1000 * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String  paiMing(String url,String... ids)throws Exception{
		List<Element> list = getTopIds(url);
		StringBuilder builder =new StringBuilder();
		for(String id:ids){
			for(int i=0;i<list.size();i++){
				Element e = list.get(i);
				String pid = e.attr("id").replace("goods-items_", "");
				if(id.equalsIgnoreCase(pid)){
					String top = e.select("i").text().replace("￥", "").replace(" ", "");
					builder.append(" id="+id+";排第="+top+"名 |");
					break;
				}
			}
		}
		return builder.toString();
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
