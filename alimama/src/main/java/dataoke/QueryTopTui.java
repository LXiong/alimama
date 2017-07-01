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
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSONObject;

import fx.HttpTest;
import util.HttpClientUtil;

public class QueryTopTui {

	static Set<String> topIds = new HashSet<String>();

	public static void main(String[] args) throws Exception {
		// /item?id=2815627
		// System.out.println(getTopIds().size());
		//execute();
		//getTeamName("3247140");
		TeamNameAll();
	}
	
	public static void TeamNameAll()throws Exception{
		List<String> topIds = getdata_tk_zs_id();
		System.out.println(topIds);
		Map<String,String> map = new HashMap<String, String>();
		
		for(int i=0;i<topIds.size();i++){
			String id = topIds.get(i);
			String name = getTeamName(id);
			//System.out.println(name);
			Thread.sleep(1000);
			if(!map.containsKey(name)){
				map.put(name, "index="+i+";id="+id);
			}else{
				map.put(name, map.get(name)+","+"index="+i+";id="+id);
			}
		}
		System.out.println(map);
		for(Entry<String, String> en:map.entrySet()){
			System.out.println(en.getKey()+"_____"+en.getValue());
		}
	}
	
	public static List<String> getdata_tk_zs_id() throws Exception {
		List<String> topIds = new ArrayList<String>();
		Document document = Jsoup.parse(new URL(
				"http://www.dataoke.com/top_tui"), 100000);
		for (Element e : document.select(".goods_team_name")) {
			String href = e.attr("data_tk_zs_id");
				topIds.add(href.trim());
		}
		return topIds;
	}
	
	public static String getTeamName(String id){
		 String baseURI="http://www.dataoke.com/get_team_name";
		 HttpPost httpRequest = new HttpPost(baseURI);
		 httpRequest.setHeader("Content-Type", "application/json");
		 httpRequest.setHeader("Host", "www.dataoke.com");
		 httpRequest.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		 httpRequest.setHeader("User-Agent", HttpTest.getUserAgent());
		 httpRequest.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		 httpRequest.setHeader("Referer", "http://www.dataoke.com/login");
		 httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
		 httpRequest.setHeader("Connection", "keep-alive");
		 
		 String sendData = "data_tk_zs_id="+id;
		 String rs = HttpClientUtil.sendPostRequest(httpRequest, sendData, true, null, null);
		 
		 rs =  StringEscapeUtils.unescapeJava(rs);
		 System.out.println("rs-=-===="+rs);
		 
		 
		 JSONObject jsonObject = JSONObject.parseObject(rs);
		 return jsonObject.getString("name");
	}

	public static void execute() throws Exception {
		Set<String> topIds = getTopIds();
		System.out.println("topids=" + topIds.size());
		Map<String, String> maps = getUserPids();

		System.out.println("maps=" + maps);

		for (Entry<String, String> en : maps.entrySet()) {
			String key = en.getKey();
			if (topIds.contains(key)) {
				System.out.println("id==" + key + "  userIds==="
						+ en.getValue());
			}
		}

	}

	static HttpHost proxy = null;
	static File base = new File("D:\\dataoke\\queryids");
	static int count = 0;

	public static Map<String, String> getUserPids() throws Exception {
		final Map<String, String> topUserIds = new HashMap<String, String>();
		List<String> users = new ArrayList<String>();
		for (File f : base.listFiles()) {
			String u = FileUtils.readLines(f).get(0);
			users.add(u);
		}
		System.out.println("用戶集合大小===" + users.size());

		for (final String s : users) {
			if (StringUtils.isBlank(s)) {
				continue;
			}
			count++;
			try {
				FutureTask<Boolean> futureTask = new FutureTask<Boolean>(
						new Callable<Boolean>() {
							public Boolean call() throws Exception {
								proxy = null;
								String uname = s.split("\\----")[0].trim();
								String pwd = s.split("\\----")[1].trim();
								System.out.println("u = " + uname + "p = "
										+ pwd + " 开始登陆  当前已刷>>>>>>>>>>>>>>>"
										+ count + " 当前 文件名称：");

								boolean flag = Test.loginHttpClient(uname, pwd);
								// boolean flag = true;
								System.out.println("u = " + uname
										+ "登陆>>>>>>>>>>>>>" + flag);
								Thread.sleep(200);
								if (flag) {
									List<String> ids = Test.getUserPids(uname);
									for (String id : ids) {
										if (!topUserIds.containsKey(id)) {
											topUserIds.put(id.trim(), uname);
										} else {
											topUserIds.put(id.trim(), uname
													+ "," + topUserIds.get(id));
										}
									}
								} else {
									System.out
											.println("denglu失败》》》》》》》》》》》》》》》》》》      uname="
													+ uname);
								}
								return true;
							}
						});

				Thread thread = new Thread(futureTask);
				thread.start();
				boolean flag = futureTask.get(30, TimeUnit.SECONDS);
				System.out.println("线程回调结果>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
						+ flag);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				proxy = null;
				Thread.sleep(Cmd.getSleepTime(1000, 2000));
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
