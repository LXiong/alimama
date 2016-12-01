package fx;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;

public class IpUtils {
	
	public static void main(String[] args) {
		getIpsmemories1999();
	}

	// http://ip.memories1999.com/api.php?dh=2764810913906166&sl=2&xl=%E5%9B%BD%E5%86%85&gl=1
	public static List<HttpHost> getIpsmemories1999() {
		return getips("http://ip.memories1999.com/api.php?dh=2764810913906166&sl=10&xl=%E5%9B%BD%E5%86%85&gl=1");
	}

	public  static List<HttpHost> getips(String url) {

		try {
			List<HttpHost> hosts = new ArrayList<HttpHost>();
			String str = getIpStr(url);

			System.out.println("get ipstr :" + str);

			String[] strs = str.split("\r\n");
			for (String ipstr : strs) {
				String[] ip = ipstr.split(":");
				HttpHost e = new HttpHost(ip[0], Integer.parseInt(ip[1]));
				hosts.add(e);
			}
			return hosts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getIpStr(String url) {
		String urlStr = new HttpTest().getContentByUrl(null, new HttpGet(url));
		return urlStr;
	}

}
