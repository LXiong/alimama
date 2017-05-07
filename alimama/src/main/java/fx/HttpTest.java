package fx;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpTest {

	// HttpHost host = new HttpHost("103.28.149.118", 8080);

	public HttpHost host = new HttpHost("58.154.213.21", 808);;
	public String aurl = "http://www.qtmzmb.cn/read/182075/1391589.html";
	
	public String X_Forwarded_For = null;

	public String aid = "182075";

	public String uid = "1391589";
	
	public String ua = getUserAgent();

	public static void main(String[] args) throws Exception {
		HttpTest httpTest = new HttpTest();
		httpTest.test();
		
		//new Http().executeAll();
		// execute();
		// getAidAndUid();
		// System.out.println(X_Forwarded_For);
		
		
		for(int i=0;i<100;i++){
			//System.out.println(new HttpTest().getUserAgent());
		}
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


	public void getAidAndUidAndHost() {
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

		//System.out.println(s);

		System.out.println("==============httpReqCount======================");

		HttpGet httpReqCount = new HttpGet(
				"http://rrz27.t7yb.net/count.php?id=" + aid
						+ "&dm=www.qtmzmb.cn");
		setHeaderCount(httpReqCount);
		s = getContentByUrl(host, httpReqCount);

		System.out.println(s);

		System.out.println("=================httpReqEnd===================");

		HttpGet httpReqgb = new HttpGet("http://gd87397616.cn/ac.php?uid="
				+ uid + "&aid=" + aid);
		setHeadergd(httpReqgb);
		s = getContentByUrl(host, httpReqgb,getRandomSleep());

		System.out.println(s);
	}
	
	public String getContentByUrl(HttpHost proxy, HttpRequestBase httpReq) {
		return getContentByUrl(proxy, httpReq, 10);
	}
	
	public long getRandomSleep(){
		return new Random().nextInt(5000)+1000;
	}
	
	static List<String> androidVersion = new ArrayList<String>();
	
	static{
	androidVersion.add("Mozilla/5.0 (Linux; U; Android 4.0.1; ja-jp; Galaxy Nexus Build/ITL41D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	androidVersion.add("Mozilla/5.0 (Linux; U; Android 4.0.3; ja-jp; URBANO PROGRESSO Build/010.0.3000) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	androidVersion.add("Mozilla/5.0 (Linux; U; Android 4.0.3; ja-jp; Sony Tablet S Build/TISU0R0110) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	androidVersion.add("Mozilla/5.0 (Linux; U; Android 4.0.4; ja-jp; SC-06D Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	androidVersion.add("Mozilla/5.0 (Linux; U; Android 4.1.1; ja-jp; Galaxy Nexus Build/JRO03H) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	androidVersion.add("Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03S) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Safari/535.19 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	androidVersion.add("Mozilla/5.0 (iPad; CPU OS 5_0_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A405 Safari/7534.48.3 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	androidVersion.add("Mozilla/5.0 (iPad; CPU OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	androidVersion.add("Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A403 Safari/8536.25 MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN");
	
	 androidVersion.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1"            );
     androidVersion.add("Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11"     );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6"            );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6"                   );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1"             );
     androidVersion.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5"                );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5"                  );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3"            );
     androidVersion.add("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3"                   );
     androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3" );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3"                   );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3"            );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3"                   );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3"            );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3"                   );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3"                   );
     androidVersion.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24"              );
     androidVersion.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24"          .trim());
     
     
     
     
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.7 (KHTML, like Gecko) Chrome/7.0.517.43 Safari/534.7                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.224 Safari/534.10 ChromePlus/1.5.2.0                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (en-us) AppleWebKit/534.14 (KHTML, like Gecko; Google Wireless Transcoder) Chrome/9.0.597 Safari/534.14                                                                                                        ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.151 Safari/534.16                                                                                                   ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.71 Safari/534.24                                                                                                     ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.24 (KHTML, like Gecko) Iron/11.0.700.2 Chrome/11.0.700.2 Safari/534.24                                                                                      ".trim());
    androidVersion.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.65 Safari/534.24                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30 ChromePlus/1.6.3.1                                                                                                   ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.112 Safari/534.30                                                                                                    ".trim());
    androidVersion.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.107 Safari/535.1                                                                                                                        ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.1 (KHTML, like Gecko) RockMelt/0.9.64.361 Chrome/13.0.782.218 Safari/535.1                                                                                  ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1                                                                                                      ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.220 Safari/535.1                                                                                                      ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1                                                                                                      ".trim());
    androidVersion.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1                                                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2                                                                                                                        ".trim());
    androidVersion.add("Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.2 (KHTML, like Gecko) Ubuntu/10.04 Chromium/15.0.874.106 Chrome/15.0.874.106 Safari/535.2                                                                                    ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.106 Safari/535.2                                                                                                      ".trim());
    androidVersion.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7                                                                                                                  ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7                                                                                                       ".trim());
    androidVersion.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30618; Lunascape 4.7.3)                                                                                     ".trim());
    androidVersion.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; Lunascape 5.0 alpha2)                                                                                                            ".trim());
    androidVersion.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; Lunascape 5.0 alpha2)                                       ".trim());
    androidVersion.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506; Tablet PC 2.0; Lunascape 5.0 alpha2)      ".trim());
    androidVersion.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; MAGW; .NET4.0C; Lunascape 6.5.8.24780)                      ".trim());
    androidVersion.add("Mozilla/2.02 (Macintosh; I; PPC)                                                                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/3.01 (Macintosh; I; PPC)                                                                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/4.01 (Macintosh; I; PPC)                                                                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/4.03 [en]C-IMS (Win95; I)                                                                                                                                                                                          ".trim());
    androidVersion.add("Mozilla/4.04 [ja] (Win95; I ;Nav)                                                                                                                                                                                          ".trim());
    androidVersion.add("Mozilla/4.04 [ja] (Win95; I)                                                                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/4.04 [ja] (WinNT; I ;Nav)                                                                                                                                                                                          ".trim());
    androidVersion.add("Mozilla/4.04 [ja] (WinNT; I)                                                                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/4.04 [ja] (Macintosh; I; PPC Nav)                                                                                                                                                                                  ".trim());
    androidVersion.add("Mozilla/4.04 [en] (X11; I; SunOS 5.5 sun4u)                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.05 [ja] (Win95; I)                                                                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/4.05 (Macintosh; I; PPC)                                                                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/4.06 [ja] (Win98; I)                                                                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/4.06 [ja] (Macintosh; I; PPC)                                                                                                                                                                                      ".trim());
    androidVersion.add("User-Agent: Mozilla/4.07 [ja_JP.EUC] (X11; I; MarkAgent FreeBSD 2.2.8-RELEASE i386; Nav)                                                                                                                                   ".trim());
    androidVersion.add("User-Agent: Mozilla/4.07 [ja_JP.EUC] (X11; I; FreeBSD 2.2.8-RELEASE i386; Nav)                                                                                                                                             ".trim());
    androidVersion.add("Mozilla/4.08 (Macintosh; I; PPC)                                                                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/4.5 [ja] (Win95; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.5 [ja] (Win98; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.5 [ja] (WinNT; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.5 (Macintosh; I; PPC)                                                                                                                                                                                            ".trim());
    androidVersion.add("Mozilla/4.51 [ja] (Win95; I)                                                                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/4.51 [ja] (Win98; I)                                                                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/4.51 [ja] (WinNT; I)                                                                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/4.51 [ja] (X11; I; SunOS 5.8 sun4u)                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.6 [ja] (Win95; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.6 [ja] (Win98; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.6 [ja] (WinNT; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.6 [ja] (WinNT; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.7 [en] (WinNT; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.7 [ja] (Win95; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.7 [ja] (Win98; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.7 [ja] (WinNT; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.7 [ja] (WinNT; I)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.7 [ja] (WinNT; U)                                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.7 [ja] (Macintosh; I; PPC)                                                                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/4.76 [en_jp] (X11; U; SunOS 5.8 sun4u)                                                                                                                                                                             ".trim());
    androidVersion.add("Mozilla/4.76 [ja] (X11; U; SunOS 5.8 sun4u)                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.78 [ja] (X11; U; SunOS 5.9 sun4u)                                                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/4.8 [ja] (X11; U; SunOS 5.7 sun4u)                                                                                                                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Win98; ja-JP; m18) Gecko/20001108 Netscape6/6.0                                                                                                                                                   ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; ja-JP; m18) Gecko/20010131 Netscape6/6.01                                                                                                                                         ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Win 9x 4.90; ja-JP; rv:0.9.4) Gecko/20011128 Netscape6/6.2.1                                                                                                                                      ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ja-JP; rv:0.9.4.1) Gecko/20020508 Netscape6/6.2.3                                                                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; N; PPC; ja-JP; macja-pub12) Gecko/20001108 Netscape6/6.0                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; U; PPC; ja-JP; rv:0.9.2) Gecko/20010726 Netscape6/6.1                                                                                                                                              ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; U; PPC; ja-JP; rv:0.9.4) Gecko/20011022 Netscape6/6.2                                                                                                                                              ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X; en-US; rv:0.9.4.1) Gecko/20020315 Netscape6/6.2.2                                                                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Win98; en-US; rv:1.0.2) Gecko/20030208 Netscape/7.02                                                                                                                                              ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ja-JP; rv:1.4) Gecko/20030624 Netscape/7.1 (ax)                                                                                                                                   ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.4) Gecko/20030624 Netscape/7.1 (ax)                                                                                                                                   ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.2) Gecko/20040804 Netscape/7.2 (ax)                                                                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.7.2) Gecko/20040805 Netscape/7.2                                                                                                                                              ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X; ja-JP; rv:1.0.2) Gecko/20021120 Netscape/7.01                                                                                                                                     ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; ja-JP; rv:1.4) Gecko/20030624 Netscape/7.1                                                                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; SunOS sun4u; ja-JP; rv:1.0.1) Gecko/20020921 Netscape/7.0                                                                                                                                             ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; ja-JP; rv:1.5) Gecko/20031007                                                                                                                                                     ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.6) Gecko/20040113                                                                                                                                                     ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.7) Gecko/20040616                                                                                                                                                     ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.6) Gecko/20040113                                                                                                                                              ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; ja-JP; rv:1.2.1) Gecko/20030225                                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; ja-JP; rv:1.4.1) Gecko/20031030                                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; FreeBSD i386; en-US; rv:1.7.1) Gecko/20040805                                                                                                                                                         ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; SunOS sun4u; ja-JP; rv:1.4) Gecko/20040414                                                                                                                                                            ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; rv:1.7.3) Gecko/20040913 Firefox/0.10                                                                                                                                                     ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; rv:1.7.3) Gecko/20040913 Firefox/0.10                                                                                                                                             ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; rv:1.7.3) Gecko/20040913 Firefox/0.10                                                                                                                                             ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; rv:1.7.3) Gecko/20041001 Firefox/0.10.1                                                                                                                                                   ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; rv:1.7.3) Gecko/20041001 Firefox/0.10.1                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; rv:1.7.3) Gecko/20041001 Firefox/0.10.1                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.2; rv:1.7.3) Gecko/20041001 Firefox/0.10.1                                                                                                                                           ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ja-JP; rv:1.6) Gecko/20040206 Firefox/0.8                                                                                                                                         ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Win98; en-US; rv:1.6) Gecko/20040206 Firefox/0.8                                                                                                                                                  ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; ja-JP; rv:1.6) Gecko/20040207 Firefox/0.8                                                                                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.6) Gecko/20040206 Firefox/0.8                                                                                                                                  ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ja-JP; rv:1.7) Gecko/20040614 Firefox/0.9                                                                                                                                         ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; es-ES; rv:1.7) Gecko/20040708 Firefox/0.9                                                                                                                                                 ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ja-JP; rv:1.7) Gecko/20040707 Firefox/0.9.2                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.5) Gecko/20041107 Firefox/0.9.2 StumbleUpon/1.994                                                                                                                   ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ja-JP; rv:1.7) Gecko/20040803 Firefox/0.9.3                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.7) Gecko/20040803 Firefox/0.9.3                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.7) Gecko/20040803 Firefox/0.9.3                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Win98; en-US; rv:1.7) Gecko/20040803 Firefox/0.9.3                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.7) Gecko/20040803 Firefox/0.9.3                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.4) Gecko/20040803 Firefox/0.9.3                                                                                                                                               ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.7) Gecko/20041013 Firefox/0.9.3 (Ubuntu)                                                                                                                                      ".trim());
    androidVersion.add("Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.7) Gecko/20041013 Firefox/0.9.3 (Ubuntu)                                                                                                                                    ".trim());
    androidVersion.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.7.5) Gecko/20041107 Firefox/1.0                                                                                                                                ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Win 9x 4.90; nl-NL; rv:1.7.5) Gecko/20041202 Firefox/1.0                                                                                                                                          ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Win 9x 4.90; nl-NL; rv:1.7.5) Gecko/20041202 Firefox/1.0                                                                                                                                          ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Win98; nl-NL; rv:1.7.5) Gecko/20041202 Firefox/1.0                                                                                                                                                ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; de-DE; rv:1.7.5) Gecko/20041108 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; de-DE; rv:1.7.5) Gecko/20041122 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; en-GB; rv:1.7.5) Gecko/20041107 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; en-GB; rv:1.7.5) Gecko/20041110 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.7.5) Gecko/20041107 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; fr-FR; rv:1.7.5) Gecko/20041108 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; it-IT; rv:1.7.5) Gecko/20041110 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; de-DE; rv:1.7.5) Gecko/20041107 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; de-DE; rv:1.7.5) Gecko/20041108 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; de-DE; rv:1.7.5) Gecko/20041122 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.7.5) Gecko/20041110 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.5) Gecko/20041107 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.5) Gecko/20041107 Firefox/1.0 StumbleUpon/1.999                                                                                                                     ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; es-ES; rv:1.7.5) Gecko/20041210 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; fr-FR; rv:1.7.5) Gecko/20041108 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; nl-NL; rv:1.7.5) Gecko/20041202 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; sv-SE; rv:1.7.5) Gecko/20041108 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US; rv:1.7.5) Gecko/20041107 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US; rv:1.8b) Gecko/20050212 Firefox/1.0+ (MOOX M3)                                                                                                                             ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; WinNT4.0; en-US; rv:1.7.5) Gecko/20041107 Firefox/1.0                                                                                                                                             ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.8b) Gecko/20050118 Firefox/1.0                                                                                                                                       ".trim());
    androidVersion.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8b) Gecko/20050118 Firefox/1.0                                                                                                                                       ".trim());
	}
	
	
	
	public static String getUserAgent(){
		//String ua = "Mozilla/5.0 (Linux; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN";

		String ua = androidVersion.get(new Random().nextInt(androidVersion.size())).trim();
		
		System.out.println("getUserAgent==="+ua);
		return ua;
	}

	public void setHeaderCount(HttpRequestBase request) {
		request.setHeader(
				"User-Agent",
				ua);
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
				ua);
				request.setHeader("Host", "www.qtmzmb.cn");
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-encoding", "gzip, deflate");
		request.setHeader("X-Forwarded-For", X_Forwarded_For);
	}

	public void setHeadergd(HttpRequestBase request) {
		request.setHeader(
				"User-Agent",
				ua);
		request.setHeader("Host", "gd87397616.cn");
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-encoding", "gzip, deflate");
		request.setHeader("X-Forwarded-For", X_Forwarded_For);
		request.setHeader("Referer", aurl);
	}

	public String getContentByUrl(HttpHost proxy, HttpRequestBase httpReq,long sleepTime) {

		/*CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope("localhost", 8080),
				new UsernamePasswordCredentials("username", "password"));
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider).build();*/
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
	
		//请求超时
		//httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
		//读取超时
		//httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
		
		//链接超时
		//httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);  
		//读取超时
		//httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000)
		HttpEntity entity2 = null;
		HttpResponse response =null;
		try {
			// https://us.fotolia.com/ //https://us.fotolia.com/id/35642224
			// HttpHost proxy = new HttpHost("117.185.124.77", 8088);
			// HttpHost httpHost = new HttpHost("www.fotolia.com", 443,
			// "https");
			// HttpHost proxy = new HttpHost("153.121.75.130",8080);
			// HttpGet httpget = new HttpGet("/id/35642224");
			
			org.apache.http.client.config.RequestConfig.Builder requestConfigBuilder = RequestConfig.custom().setSocketTimeout(30000)
					.setConnectionRequestTimeout(30000).setConnectTimeout(30000);//设置请求和传输超时时间
			if (null != proxy) {
				requestConfigBuilder.setProxy(proxy);
			}
			
			httpReq.setConfig(requestConfigBuilder.build());
			System.out.println("Executing request " + httpReq.getRequestLine()
					+ " to " + httpReq.getURI() + " via " + proxy);
			// httpclient.execute(httpHost,
			// httpReq,MuResponseHanlder.responseHandler(filesavePath));

			 response = httpclient.execute(httpReq);
			 entity2 = response.getEntity();
			String entityBody = EntityUtils.toString(entity2, "utf-8");//
			
			//response.getEntity().getContent().close();
			return entityBody;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			
			if(entity2!=null){
				try {
					EntityUtils.consumeQuietly(response.getEntity());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			

			try {
				httpReq.releaseConnection();
				Thread.sleep(sleepTime);
				//Thread.sleep(new Random().nextInt(5000)+1000);
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
