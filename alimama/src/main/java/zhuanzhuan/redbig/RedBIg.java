package zhuanzhuan.redbig;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dataoke.Cmd;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class RedBIg {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
        //System.out.println(getmygrouplist());
        //System.out.println(getBigRedListByGroupID4R("793024305616125953"));
		//System.out.println(getBigRedByBigRedID4R("877144338755497984"));
		execteAll();
		
	}
	
	public static void execteAll()throws Exception{
		while(true){
			execte();
		}
	}
	
	
	public static void execte()throws Exception{
		JSONArray arraygrouplist = getmygrouplist();
		Thread.sleep(Cmd.getSleepTime(2000, 4000));
		if(CollectionUtils.isNotEmpty(arraygrouplist)){
			for(Object object:arraygrouplist){
				JSONObject jsonObject = (JSONObject)object;
				String groupId = jsonObject.getString("groupId");
				String title = jsonObject.getString("title");
				System.out.println("title："+title +" groupId:"+groupId+" 开始进去圈子>>>>>>>>>>>>>>>>>>>>获取红包列表>>>>>>>>>>>");
				arraygrouplist = getBigRedListByGroupID4R(groupId);
				Thread.sleep(Cmd.getSleepTime(2000, 4000));
				if(CollectionUtils.isNotEmpty(arraygrouplist)){
					for(Object objectRed:arraygrouplist){
						JSONObject jsonObjectRed = (JSONObject)objectRed;
						String status = jsonObjectRed.getString("status");//红包状态
						String bigRedID = jsonObjectRed.getString("bigRedID");//红包ID
						if("1".equalsIgnoreCase(status)){
							System.out.println("开始抢红包>>>>>>>>>>>>>>>>>>>>>红包信息为json为："+jsonObject);
					        JSONObject jsonObjectLast =getBigRedByBigRedID4R(bigRedID); 
					        Thread.sleep(Cmd.getSleepTime(2000, 4000));
				        if(jsonObjectLast!=null){
					        	String groupRedMoney = jsonObjectLast.getString("groupRedMoney");
					        	if(!bigRedMap.containsKey(bigRedID)){
					        		bigRedMap.put(bigRedID, groupRedMoney);
						        	for(Entry<String, String> map:bigRedMap.entrySet()){
						        		System.out.println("红包id==="+map.getKey()+" 获取红包==="+map.getValue());
						        	}
						        	System.out.println();
						        	System.out.println();
						        	System.out.println();
						        
					        	}
					        	}
					        
						}
					}
				}else{
					System.out.println("获取红包列表信息为null>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");		
				}
			}
		}else{
           System.out.println("获取圈子信息为null>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");			
		}
		
	}
	
	

	static String cookis="t=15;tk=E1B6D86E10C6CEBBD2EB4107D6533BA7;imei=866656024552665;v=3.2.1;channelid=market_914;lat=31.165799;lon=121.399652;osv=23;model=ALE-TL00;brand=Huawei;uid=48105775394318;PPU=\"UID=48105775394318&PPK=3e2d44bd&PPT=a5fae842&SK=1FC200CCC2CA2C7E99EE9D745EC682F26C9D7A5F3B264E242&LT=1497406397353&UN=mCYpuh&LV=e5d60885&PBODY=P02C97_zk36kM6LO1crHyH-lGgQaQr1LzAOsVc7I7dZngk_bJ2ommmUWVozzTWhrIShT8ykzfVFezI9f6_0b-xrkfE1NMrUqQ8n5F7UlFfDn0tsFPhKzqv8MBxbCBuqINleht77SVWEstZIDQzsHWsdaup8iG-5eb7xo0JgPSho&VER=1\"; Version=1; Domain=58.com; Path=/;";
	
	
	/**
	 * https://zhuan.58.com/zz/transfer/getBigRedByBigRedID4R?bigredid=877144338755497984&_=1497964603136&callback=Zepto1497964603060
	 * 获取红包
	 * @param groupid
	 * Zepto1497964704086({
	"respCode": 0,
	"respData": {
		"groupName": "宝宝的玩具乐园",
		"pic": "https://zzpic2.58cdn.com.cn/zhuanzh/n_v1bkuymc3l6unfspifxica.png",
		"myPic": "http://pic7.58cdn.com.cn/zhuanzh/n_v1bl2lwtoiirafssbkafxq.jpg",
		"groupRedMoney": "0.03",
		"statusDesc": "红包将存入微信零钱",
		"status": 2,
		"uid": "47297162573335",
		"userName": "橙宝妈³⁹³⁷⁷⁵⁵³⁷",
		"isMineRed": 0,
		"statusTogetherDesc": "50个红包,2分钟5秒已抢完",
		"bigRedID": "877144338755497984"
	}
})
	 * @return  抢红包方法
	 */
	public static JSONObject getBigRedByBigRedID4R(String bigredid){
		HttpRequest httpRequest = null;
		String baseURI = "https://zhuan.58.com/zz/transfer/getBigRedByBigRedID4R?bigredid="+bigredid+"&_="+System.currentTimeMillis()+"&callback=Zepto1497964603060";
		httpRequest = HttpRequest.get(baseURI);
		httpRequest.header("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		httpRequest.header("Connection", "keep-alive");
		httpRequest.header("accept", "*/*");
		httpRequest
				.header("User-Agent",
						"Mozilla/5.0 (Linux; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/49.0.2623.105 Mobile Safari/537.36 58ZhuanZh");
		httpRequest
				.header("cookie",
						cookis);
		
		httpRequest.header("referer", "https://m.zhuanzhuan.58.com/Mzhuanzhuan/zhuanzhuan/zzactivity/activity-quanzi-hongbao/html/hongbaoresult.html?bigredid="+bigredid+"&webview=zzn&tt=E1B6D86E10C6CEBBD2EB4107D6533BA71497965537533&zzv=3.2.1");		
		HttpResponse response = httpRequest.send();
		String rc = response.bodyText();
		System.out.println("===================" + rc);
		
		rc =rc.substring(rc.indexOf("(")+1, rc.lastIndexOf(")"));

		System.out.println("截取后的===================" + rc);
		
		JSONObject jsonObject = JSONObject.parseObject(rc);

		if ("0".equalsIgnoreCase(jsonObject.getString("respCode"))) {
			return jsonObject.getJSONObject("respData");
		} else {
			System.out.println("获取圈子信息为null》》》》》》》》》》》》》》》》》》》》》》》》》》");
		}
		return null;
	}
	
	static Map<String,String> bigRedMap = new HashMap<String, String>();
	
	
	/**
	 * https://zhuan.58.com/zz/transfer/getBigRedListByGroupID4R?groupid=793024305616125953&pagenum=1&pagesize=21&_=1497963829106&callback=Zepto1497963828998
	 * 
	 * Zepto1497963828998({
	"respCode": 0,
	"respData": [{
		"bigRedID": "877096153404186624",
		"userPic": "http://pic8.58cdn.com.cn/zhuanzh/n_v1bl2lwwmra2sfrm6n2z2q.png",
		"status": 2, 状态为1的为可以抢,2为抢完
		"userName": "天天想你@",
		"userRedMoney": "0.03"
	}) 
	 * @return 获取圈子红包列表
	 */
	public static JSONArray getBigRedListByGroupID4R(String groupid){
		HttpRequest httpRequest = null;
		String baseURI = "https://zhuan.58.com/zz/transfer/getBigRedListByGroupID4R?bigredid="+groupid+"&_="+System.currentTimeMillis()+"&callback=Zepto1497963828998";
		baseURI="https://zhuan.58.com/zz/transfer/getBigRedListByGroupID4R?groupid="+groupid+"&pagenum=1&pagesize=21&_="+System.currentTimeMillis()+"&callback=Zepto1497963828998";
		httpRequest = HttpRequest.get(baseURI);
		httpRequest.header("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		httpRequest.header("Connection", "keep-alive");
		httpRequest
				.header("User-Agent",
						"Mozilla/5.0 (Linux; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/49.0.2623.105 Mobile Safari/537.36 58ZhuanZhuan");
		httpRequest
				.header("cookie",
						cookis);
		
		httpRequest.header("referer", "https://m.zhuanzhuan.58.com/Mzhuanzhuan/zhuanzhuan/zzactivity/activity-quanzi-hongbao/html/hongbaolist.html?webview=zzn&groupid="+groupid+"&tt=E1B6D86E10C6CEBBD2EB4107D6533BA71497965336178&zzv=3.2.1");
		
		
		HttpResponse response = httpRequest.send();
		String rc = response.bodyText();
		System.out.println("===================" + rc);
		
		rc =rc.substring(rc.indexOf("(")+1, rc.lastIndexOf(")"));

		System.out.println("截取后的===================" + rc);
		
		JSONObject jsonObject = JSONObject.parseObject(rc);

		if ("0".equalsIgnoreCase(jsonObject.getString("respCode"))) {
			return jsonObject.getJSONArray("respData");
		} else {
			System.out.println("获取圈子信息为null》》》》》》》》》》》》》》》》》》》》》》》》》》");
		}
		return null;
	}
	
	/*
	 * https://zhuan.58.com/zz/transfer/getmygrouplist
	 * {
	"respCode": "0",
	"respData": {
		"groupList": [{
			"userCount": "796425",
			"infoCount": "60",
			"identity": "1",
			"infoPicUrls": "n_v1bl2lwtndfc7frxwcobba.jpg|n_v1bl2lwtosl3avr7hjdmqq.jpg|n_v1bl2lwkn7adefrj2knu5a.jpg",
			"addTimeStamp": "1497760888395",
			"groupId": "834667215444770819",
			"groupDesc": "爱生活🎐爱分享🎋欢迎回家🏡",
			"icon": "https://zzpic1.58cdn.com.cn/zhuanzh/n_v1bkujjd7ct3sfqhopsjoq.jpg",
			"title": "租房也是生活"
		}
	 */
	//获取所有圈子信息
	public static JSONArray getmygrouplist(){
		HttpRequest httpRequest = null;
		String baseURI = "https://zhuan.58.com/zz/transfer/getmygrouplist";
		httpRequest = HttpRequest.post(baseURI);
		httpRequest.header("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		httpRequest.header("Connection", "keep-alive");
		httpRequest
				.header("User-Agent",
						"Dalvik/2.1.0 (Linux; U; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00)");

		httpRequest
				.header("cookie",
						cookis);
		
		HttpResponse response = httpRequest.send();
		String rc = response.bodyText();
		System.out.println("===================" + rc);

		JSONObject jsonObject = JSONObject.parseObject(rc);

		if ("0".equalsIgnoreCase(jsonObject.getString("respCode"))) {
			return jsonObject.getJSONObject("respData").getJSONArray("groupList");
		} else {
			System.out.println("获取圈子信息为null》》》》》》》》》》》》》》》》》》》》》》》》》》");
		}
		return null;
	}

}
