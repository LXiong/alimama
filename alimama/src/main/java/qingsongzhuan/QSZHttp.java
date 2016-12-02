package qingsongzhuan;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import fx.HttpTest;

public class QSZHttp extends HttpTest {

	
	public void setHeaderCount(org.apache.http.client.methods.HttpRequestBase request) {
		request.setHeader(
				"User-Agent",
				ua);
		request.setHeader("Host", "qsz.t7yb.net");
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-encoding", "gzip, deflate");
		request.setHeader("X-Forwarded-For", X_Forwarded_For);
		request.setHeader("Referer", aurl);
	};
	
	
	@Override
	public void setHeaderqtmzmb(HttpRequestBase request) {
		request.setHeader(
				"User-Agent",
				ua);
				request.setHeader("Host", "www.chtgul.cn");
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-encoding", "gzip, deflate");
		request.setHeader("X-Forwarded-For", X_Forwarded_For);
	}
	
	
	@Override
	public void setHeadergd(HttpRequestBase request) {
		request.setHeader(
				"User-Agent",
				ua);
		request.setHeader("Host", "yonghongjieneng.com");
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-encoding", "gzip, deflate");
		request.setHeader("X-Forwarded-For", X_Forwarded_For);
		request.setHeader("Referer", aurl);
	}
	
	
	@Override
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
				"http://qsz.t7yb.net/count.php?id=" + aid
						+ "&dm=www.chtgul.cn");
		setHeaderCount(httpReqCount);
		s = getContentByUrl(host, httpReqCount);

		System.out.println(s);

		System.out.println("=================httpReqEnd===================");

		HttpGet httpReqgb = new HttpGet("http://yonghongjieneng.com/ac.php?uid="
				+ uid + "&aid=" + aid);
		setHeadergd(httpReqgb);
		s = getContentByUrl(host, httpReqgb,getRandomSleep());

		System.out.println(s);
	}
}
