package alimama;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

public class HttpClientDemo302 {
	
	
	public static void main(String[] args) {
		String url = "http://s.click.taobao.com/8NF1HQx";
		String str = getRedirectInfo(url);
		System.out.println(str);
		
		str = getRedirectInfo2(str);
		System.out.println(str);
	}
	
	/**
	 * 获取重定向之后的网址信息
	 * 
	 * @see HttpClient缺省会自动处理客户端重定向
	 * @see 即访问网页A后,假设被重定向到了B网页,那么HttpClient将自动返回B网页的内容
	 * @see 若想取得B网页的地址
	 *      ,就需要借助HttpContext对象,HttpContext实际上是客户端用来在多次请求响应的交互中,保持状态信息的
	 * @see 我们自己也可以利用HttpContext来存放一些我们需要的信息,以便下次请求的时候能够取出这些信息来使用
	 */
	public static String getRedirectInfo2(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext httpContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Host", "s.click.taobao.com");
		httpGet.setHeader("Referer", url);
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
		
		
		
		try {
			// 将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
			HttpResponse response = httpClient.execute(httpGet, httpContext);
			// 获取重定向之后的主机地址信息,即"http://127.0.0.1:8088"
			HttpHost targetHost = (HttpHost) httpContext
					.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
			// 获取实际的请求对象的URI,即重定向之后的"/blog/admin/login.jsp"
			HttpUriRequest realRequest = (HttpUriRequest) httpContext
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			
			realRequest.setHeader("Host", "s.click.taobao.com");
			realRequest.setHeader("Referer", url);
			realRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
			
			for(Header h:realRequest.getAllHeaders()){
				//System.out.println(h.getName() + " "+h.getValue());
			}
			
			//System.out.println("主机地址:" + targetHost);
			//System.out.println("URI信息:" + realRequest.getURI());
			/*HttpEntity entity = response.getEntity();
			if (null != entity) {
				System.out.println("响应内容:"
						+ EntityUtils.toString(entity, ContentType
								.getOrDefault(entity).getCharset()));
				EntityUtils.consume(entity);
			}*/
			String ur = targetHost + realRequest.getURI().toString();
			
			System.out.println("ur"+ur);
			
			 response = httpClient.execute(new HttpGet(ur), httpContext);
			 
			 targetHost = (HttpHost) httpContext
						.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
				// 获取实际的请求对象的URI,即重定向之后的"/blog/admin/login.jsp"
			realRequest = (HttpUriRequest) httpContext
						.getAttribute(ExecutionContext.HTTP_REQUEST);
			 
			ur = targetHost + realRequest.getURI().toString();
			System.out.println("ur"+ur);
			return ur;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	/**
	 * 获取重定向之后的网址信息
	 * 
	 * @see HttpClient缺省会自动处理客户端重定向
	 * @see 即访问网页A后,假设被重定向到了B网页,那么HttpClient将自动返回B网页的内容
	 * @see 若想取得B网页的地址
	 *      ,就需要借助HttpContext对象,HttpContext实际上是客户端用来在多次请求响应的交互中,保持状态信息的
	 * @see 我们自己也可以利用HttpContext来存放一些我们需要的信息,以便下次请求的时候能够取出这些信息来使用
	 */
	public static String getRedirectInfo(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext httpContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Host", "s.click.taobao.com");
		
		try {
			// 将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
			HttpResponse response = httpClient.execute(httpGet, httpContext);
			// 获取重定向之后的主机地址信息,即"http://127.0.0.1:8088"
			HttpHost targetHost = (HttpHost) httpContext
					.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
			// 获取实际的请求对象的URI,即重定向之后的"/blog/admin/login.jsp"
			HttpUriRequest realRequest = (HttpUriRequest) httpContext
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			
			realRequest.setHeader("Host", "s.click.taobao.com");
			
			for(Header h:realRequest.getAllHeaders()){
				//System.out.println(h.getName() + " "+h.getValue());
			}
			
			//System.out.println("主机地址:" + targetHost);
			//System.out.println("URI信息:" + realRequest.getURI());
			/*HttpEntity entity = response.getEntity();
			if (null != entity) {
				System.out.println("响应内容:"
						+ EntityUtils.toString(entity, ContentType
								.getOrDefault(entity).getCharset()));
				EntityUtils.consume(entity);
			}*/
			String ur = targetHost + realRequest.getURI().toString();
			return ur;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
}