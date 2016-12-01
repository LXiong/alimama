package fx;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpTest {

	// HttpHost host = new HttpHost("103.28.149.118", 8080);

	HttpHost host = new HttpHost("183.163.24.13", 8998);;
	String aurl = "http://www.qtmzmb.cn/read/182075/1391589.html";
	
	String X_Forwarded_For = null;

	String aid = "182075";

	String uid = "1391589";

	public static void main(String[] args) throws Exception {
		new HttpTest().test();
		//new Http().executeAll();
		// execute();
		// getAidAndUid();
		// System.out.println(X_Forwarded_For);
	}

	public void executeAll() throws Exception {
		HttpTest http = new HttpTest();
		http.aurl = "http://www.qtmzmb.cn/read/182151/1391589.html";
		http.execute();
	}

	public void test() throws Exception {
		HttpGet httpReq = new HttpGet(
				"http://haitmall.com/detail.php?aid=186&uid=203");
		HttpTest http = new HttpTest();
		String s = http.getContentByUrl(host, httpReq);
		// String s = HttpClientEx.getContentByUrl(host, httpReq);
		System.out.println(s);
		
		if(StringUtils.isNotBlank(s)){
			executeAll();
		}
		
	}


	void getAidAndUidAndHost() {
		String[] strs = aurl.replace(".html", "").split("\\/");
		System.out.println(Arrays.toString(strs));

		aid = strs[strs.length - 2];
		uid = strs[strs.length - 1];

		System.out.println("aid=" + aid + "  uid=" + uid);
		
		X_Forwarded_For = host.getHostName();
		
		System.out.println("X_Forwarded_For=" + X_Forwarded_For);
	}

	public void execute() throws Exception {

		getAidAndUidAndHost();

		HttpGet httpReq = new HttpGet(aurl);
		// http://www.stilllistener.com/checkpoint1/
		// HttpGet httpReq = new
		// HttpGet("http://www.stilllistener.com/checkpoint1/");
		// HttpHost host = new HttpHost("103.28.149.118", 8080);
		// HttpHost host = new HttpHost("122.226.189.97", 135);

		System.out.println("==============httpReq======================");

		// String s = getContentByUrl(null, httpReq);
		setHeaderqtmzmb(httpReq);
		String s = getContentByUrl(host, httpReq);

		System.out.println(s);

		System.out.println("==============httpReqCount======================");

		HttpGet httpReqCount = new HttpGet(
				"http://rrz27.t7yb.net/count.php?id=" + aid
						+ "&dm=www.qtmzmb.cn");
		setHeaderCount(httpReqCount);
		s = getContentByUrl(host, httpReqCount);

		System.out.println(s);

		System.out.println("=================httpReqgb===================");

		HttpGet httpReqgb = new HttpGet("http://gd87397616.cn/ac.php?uid="
				+ uid + "&aid=" + aid);
		setHeadergd(httpReqgb);
		s = getContentByUrl(host, httpReqgb);

		System.out.println(s);
	}

	public void setHeaderCount(HttpRequestBase request) {
		request.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Linux; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
		request.setHeader("Host", "rrz27.t7yb.net");
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-encoding", "gzip, deflate");
		request.setHeader("X-Forwarded-For", X_Forwarded_For);
		request.setHeader("Referer", aurl);
	}

	public void setHeaderqtmzmb(HttpRequestBase request) {
		request.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Linux; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
		request.setHeader("Host", "www.qtmzmb.cn");
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-encoding", "gzip, deflate");
		request.setHeader("X-Forwarded-For", X_Forwarded_For);
	}

	public void setHeadergd(HttpRequestBase request) {
		request.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Linux; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
		request.setHeader("Host", "gd87397616.cn");
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-encoding", "gzip, deflate");
		request.setHeader("X-Forwarded-For", X_Forwarded_For);
		request.setHeader("Referer", aurl);
	}

	public String getContentByUrl(HttpHost proxy, HttpRequestBase httpReq) {

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope("localhost", 8080),
				new UsernamePasswordCredentials("username", "password"));
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider).build();
		try {
			// https://us.fotolia.com/ //https://us.fotolia.com/id/35642224
			// HttpHost proxy = new HttpHost("117.185.124.77", 8088);
			// HttpHost httpHost = new HttpHost("www.fotolia.com", 443,
			// "https");
			// HttpHost proxy = new HttpHost("153.121.75.130",8080);
			// HttpGet httpget = new HttpGet("/id/35642224");

			if (null != proxy) {
				RequestConfig config = RequestConfig.custom().setProxy(proxy)
						.build();
				httpReq.setConfig(config);
			}
			System.out.println("Executing request " + httpReq.getRequestLine()
					+ " to " + httpReq.getURI() + " via " + proxy);
			// httpclient.execute(httpHost,
			// httpReq,MuResponseHanlder.responseHandler(filesavePath));

			HttpResponse response = httpclient.execute(httpReq);
			HttpEntity entity2 = response.getEntity();
			String entityBody = EntityUtils.toString(entity2, "utf-8");//
			return entityBody;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				Thread.sleep(100);
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
