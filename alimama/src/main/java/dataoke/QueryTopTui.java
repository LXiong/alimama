package dataoke;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import util.IpPoolUtil;

public class QueryTopTui {

	static Set<String> topIds = new HashSet<String>();

	public static void main(String[] args) throws Exception {
		// /item?id=2815627
		//System.out.println(getTopIds().size());
		execute();
	}
	
	
	public static void execute()throws Exception {
		Set<String> topIds = getTopIds();
		Map<String,String> maps = getUserPids();
		for(Entry<String, String> en:maps.entrySet()){
			String key = en.getKey();
			if(topIds.contains(key)){
				System.out.println("id=="+key+"  userIds==="+en.getValue());
			}
		}
		 
		
	}

	static HttpHost proxy = null;
	static File base = new File("D:\\dataoke\\queryids");
	static int count = 0;

	public static Map<String,String> getUserPids() throws Exception {
		Map<String,String> topUserIds = new HashMap<String,String>();
		List<String> users = new ArrayList<String>();
		for (File f : base.listFiles()) {
			String u = FileUtils.readLines(f).get(0);
			users.add(u);
		}
		System.out.println("用戶集合大小==="+users.size());
		count++;
		for (String s : users) {
			if (StringUtils.isBlank(s)) {
				continue;
			}
			try {
				proxy = null;
				String uname = s.split("\\----")[0].trim();
				String pwd = s.split("\\----")[1].trim();
				System.out.println("u = " + uname + "p = " + pwd
						+ " 开始登陆  当前已刷>>>>>>>>>>>>>>>" + count + " 当前 文件名称：");

				boolean flag = Test.loginHttpClient(uname, pwd);
				// boolean flag = true;
				System.out.println("u = " + uname + "登陆>>>>>>>>>>>>>" + flag);
				Thread.sleep(200);
				if (flag) {
					List<String> ids=Test.getUserPids(uname);
					for(String id:ids){
						if(!topUserIds.containsKey(id)){
							topUserIds.put(id.trim(), uname);
						}else{
							topUserIds.put(id.trim(), uname+","+topUserIds.get(id));
						}
					}
				} else {
					System.out.println("denglu失败》》》》》》》》》》》》》》》》》》      uname="
							+ uname);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				proxy = null;
				Thread.sleep(Cmd.getSleepTime(1000,2000));
			}

		}

		return topUserIds;
	}

	public static Set<String> getTopIds() throws Exception {
		Set<String> topIds = new HashSet<String>();
		Document document = Jsoup.parse(new URL(
				"http://www.dataoke.com/top_tui"), 100000);
		for (Element e : document.select("a")) {
			String href = e.attr("href");
			if (StringUtils.isNotBlank(href) && href.contains("item?id=")) {
				String id = href.replace("/item?id=", "");
				topIds.add(id.trim());
			}
		}
		return topIds;
	}

}
