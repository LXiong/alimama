package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;

import weiboread.HttpTest;

public class IpUtils {
	/**
	 * <br>
	 * * 批量代理IP有效检测<br>
	 * *<br>
	 * * @param IP<br>
	 * * @param post<br>
	 */
	public static void createIPAddress(String ip, int port) {
		URL url = null;
		try {
			url = new URL("http://www.baidu.com");
		} catch (MalformedURLException e) {
			System.out.println("url invalidate");
		}
		InetSocketAddress addr = null;
		addr = new InetSocketAddress(ip, port);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http proxy
		InputStream in = null;
		try {
			URLConnection conn = url.openConnection(proxy);
			conn.setConnectTimeout(1000);
			in = conn.getInputStream();
		} catch (Exception e) {
			System.out.println("ip " + ip + " is not aviable");// 异常IP
		}
		String s = convertStreamToString(in);
		System.out.println(s);
		// System.out.println(s);
		if (s.indexOf("baidu") > 0) {// 有效IP
			System.out.println(ip + ":" + port + " is ok");
		}
	}
	
public  static List<HttpHost> getips(String url) {
		
		System.out.println("ip url =="+url);

		try {
			List<HttpHost> hosts = new ArrayList<HttpHost>();
			String str = getIpStr(url);
			
			if(StringUtils.isBlank(str)){
				System.out.println("获取ip是空>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}

			//System.out.println("get ipstr :" + str);

			String[] strs = str.replaceAll("\\t", "").replace("    ", "").replace("<br>", "").split("\r\n");
			for (String ipstr : strs) {
				String[] ip = ipstr.split(":");
				HttpHost e = new HttpHost(ip[0], Integer.parseInt(ip[1]));
				hosts.add(e);
			}
			return hosts;
		} catch (Exception e) {
			System.out.println("获取ip有问题>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			e.printStackTrace();
		}
		return null;
	}

public static String getIpStr(String url) {
	String urlStr = new HttpTest().getContentByUrl(null, new HttpGet(url));
	return urlStr;
}

	public static String convertStreamToString(InputStream is) {
		if (is == null)
			return "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

}
