package zhuanzhuan;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class UploadImgTest {

	public static void main(String[] args) throws Exception {
		// System.out.println(getChiledCate("家居家具"));
		test();
	}

	public static void test() throws Exception {
		File file = new File("g:\\test.jpg");
		String imageName = uploadimg(file);
		Thread.sleep(5000);
		// System.out.println(httpClientUploadFile(file));
		// 原价
		String oriPrice = "180";
		// 现价
		String nowPrice = "101";
		// 标题描述
		String title = "漂亮的衣服111111111";
		// 一级分类 encoder
		String cateParentId = "家居家具";
		// cateId 二级分类
		String cateId = "2108014";
		boolean flag = addInfo(imageName, title, oriPrice, nowPrice,
				cateParentId, cateId);
		System.out.println("发布商品结构>>>>>>>>>>>>>>" + flag);
	}

	public static String getChiledCate(String cataN) {
		try {
			JSONObject respDataJsonObject = JSONObject
					.parseObject(FileUtils
							.readFileToString(new File(
									"D:\\workspace\\alimama\\alimama\\src\\main\\java\\zhuanzhuan\\cate.txt")));
			JSONArray array = respDataJsonObject.getJSONArray("respData");
			for (Object object : array) {
				JSONObject jsonObject = (JSONObject) object;
				String cataName = jsonObject.getString("cateName");
				if (cataN.equals(cataName)) {
					array = jsonObject.getJSONArray("subCateArr");
					return array.getJSONObject(
							new Random().nextInt(array.size())).getString(
							"subCateName");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void addForm(String str, HttpRequest httpRequest) {
		for (String kv : str.split("\\&")) {
			if (kv.split("\\=").length == 2) {
				String key = kv.split("\\=")[0];
				String val = kv.split("\\=")[1];
				httpRequest.form(key, val);
			} else if (kv.split("\\=").length == 1) {
				String key = kv.split("\\=")[0];
				httpRequest.form(key, "");
			}

		}
	}

	public static boolean addInfo(String imageName, String title,
			String oriPrice, String nowPrice, String cateParentId, String cateId)
			throws Exception {
		HttpRequest httpRequest = null;
		String baseURI = "https://zhuan.58.com/zz/transfer/addInfo";
		httpRequest = HttpRequest.post(baseURI);
		httpRequest.header("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		httpRequest.header("Connection", "keep-alive");
		httpRequest
				.header("User-Agent",
						"Dalvik/2.1.0 (Linux; U; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00)");

		httpRequest
				.header("cookie",
						"t=15;tk=E1B6D86E10C6CEBBD2EB4107D6533BA7;imei=866656024552665;v=3.2.1;channelid=market_914;lat=31.165799;lon=121.399652;osv=23;model=ALE-TL00;brand=Huawei;uid=48105775394318;PPU=\"UID=48105775394318&PPK=3e2d44bd&PPT=a5fae842&SK=1FC200CCC2CA2C7E99EE9D745EC682F26C9D7A5F3B264E242&LT=1497406397353&UN=mCYpuh&LV=e5d60885&PBODY=P02C97_zk36kM6LO1crHyH-lGgQaQr1LzAOsVc7I7dZngk_bJ2ommmUWVozzTWhrIShT8ykzfVFezI9f6_0b-xrkfE1NMrUqQ8n5F7UlFfDn0tsFPhKzqv8MBxbCBuqINleht77SVWEstZIDQzsHWsdaup8iG-5eb7xo0JgPSho&VER=1\"; Version=1; Domain=58.com; Path=/;");

		// String imgname = "n_f7d17baf54ff4529bc11006bf527e982.jpg";
		// imgname = "n_8ceb01a647334ef2b0eea1f2d8f74ea8.jpg";
		// 原价
		// String oriPrice="180";
		// 现价
		// String nowPrice="100";
		// 标题描述
		// String title = "漂亮的衣服";
		// 一级分类 encoder
		// String cateParentId="家居家具";
		// cateId 二级分类
		// String cateId = "2108014";
		if (StringUtils.isBlank(cateId)) {
			cateId = getChiledCate(cateParentId);
			if (StringUtils.isBlank(cateId)) {
				System.out.println("获取子分类id为null>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				return false;
			}

		}

		String p = "isnewlabel=0&lon=121.399652&oriPrice="
				+ oriPrice
				+ "&groupactivityid=&nowPrice="
				+ nowPrice
				+ "&city=2&pics="
				+ imageName
				+ "&cateParentId="
				+ cateParentId
				+ "&village=-5573351235747348960&postageExplain=2&business=6323&isblock=0&freigth=0&title="
				+ title
				+ "&area=6179&allowMobile=0&lat=31.165799&picMd5s=78051692deef771701a7a94e31dc048a&cateId="
				+ cateId;
		addForm(p, httpRequest);
		HttpResponse response = httpRequest.send();
		String rc = response.bodyText();
		System.out.println("===================" + rc);
		// {"respCode":"0","respData":{"infoId":"874904830388961293","popType":"1","infoUrl":"http://zhuanzhuan.58.com/zz/redirect/inforurlredirect?infoId=874904830388961293","babyInfo":"0","successUrl":"https://m.zhuanzhuan.58.com/Mzhuanzhuan/ZZMuying/index.html#/quickSale/poster?webview=zzn&from=publish&infoId=874904830388961293"}}

		JSONObject jsonObject = JSONObject.parseObject(rc);

		if ("0".equalsIgnoreCase(jsonObject.getString("respCode"))) {
			System.out.println("发布成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			return true;
		} else {
			System.out.println("发布失败》》》》》》》》》》》》》》》》》》》》》》》》》》");
		}

		return false;
	}

	public static String uploadimg(File image) throws Exception {
		HttpRequest httpRequest = null;
		String baseURI = "https://upload.58cdn.com.cn/";
		httpRequest = HttpRequest.post(baseURI);
		httpRequest.header("Pic-Flash", "1");

		// httpRequest.header("Content-Type",
		// "multipart/form-data;boundary=60ab2018-b3ef-4659-af77-a50c869997bx");
		httpRequest.header("Content-Type", "multipart/form-data");

		httpRequest.header("Charset", "utf-8");
		httpRequest.header("Connection", "keep-alive");

		httpRequest.header("Pic-Path", "/zhuanzh/");
		httpRequest.header("Pic-Size", "0*0");

		httpRequest.header("Pic-Bulk", "0");
		httpRequest.header("Pic-dpi", "0");
		httpRequest.header("Pic-Cut", "0*0*0*0");
		httpRequest.header("Pic-IsAddWaterPic", "false");
		httpRequest
				.header("User-Agent",
						"Dalvik/2.1.0 (Linux; U; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00)");
		httpRequest.header("File-Extensions", "jpg");
		httpRequest.header("Host", "upload.58cdn.com.cn");

		long leng = image.length();
		httpRequest.header("Content-Length", leng);
		System.out.println("文件长度是>>>>>>>>>>>>>>>" + leng);

		httpRequest.body(FileUtils.readFileToByteArray(image),
				ContentType.MULTIPART_FORM_DATA.toString());

		HttpResponse response = httpRequest.send();
		String rc = response.bodyText();

		if (rc.startsWith("n_")) {
			System.out
					.println("上传成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("===================" + rc);
			return rc;
		} else {
			System.out.println("===================" + rc);
		}
		return null;
	}

	public static boolean upload2(File image) {
		HttpRequest httpRequest = null;
		String baseURI = "https://upload.58cdn.com.cn/";
		httpRequest = HttpRequest.post(baseURI);
		httpRequest
				.header("User-Agent",
						"Dalvik/2.1.0 (Linux; U; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00)");
		httpRequest.header("Host", "upload.58cdn.com.cn");
		httpRequest.form("file", image);
		/*
		 * httpRequest.monitor(new HttpProgressListener() {
		 * 
		 * @Override public void transferred(int len) { // TODO Auto-generated
		 * method stub System.out.println(len/size); } });
		 */

		HttpResponse response = httpRequest.send();
		String rc = response.body();
		System.out.println("===================" + rc);

		return true;
	}

	public static boolean upload(File image) {
		HttpRequest httpRequest = null;
		String baseURI = "https://upload.58cdn.com.cn/";
		httpRequest = HttpRequest.post(baseURI);
		httpRequest.header("Pic-Flash", "1");

		// httpRequest.header("Content-Type",
		// "multipart/form-data;boundary=60ab2018-b3ef-4659-af77-a50c869997bx");
		httpRequest.header("Content-Type", "multipart/form-data");

		httpRequest.header("Charset", "utf-8");
		httpRequest.header("Connection", "keep-alive");

		httpRequest.header("Pic-Path", "/zhuanzh/");
		httpRequest.header("Pic-Size", "0*0");

		httpRequest.header("Pic-Bulk", "0");
		httpRequest.header("Pic-dpi", "0");
		httpRequest.header("Pic-Cut", "0*0*0*0");
		httpRequest.header("Pic-IsAddWaterPic", "false");
		httpRequest
				.header("User-Agent",
						"Dalvik/2.1.0 (Linux; U; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00)");
		httpRequest.header("File-Extensions", "jpg");
		httpRequest.header("Host", "upload.58cdn.com.cn");

		long leng = image.length();
		// httpRequest.header("Content-Length", leng);

		System.out.println("文件长度是>>>>>>>>>>>>>>>" + leng);

		httpRequest.form("file", image);
		/*
		 * httpRequest.monitor(new HttpProgressListener() {
		 * 
		 * @Override public void transferred(int len) { // TODO Auto-generated
		 * method stub System.out.println(len/size); } });
		 */

		HttpResponse response = httpRequest.send();
		String rc = response.bodyText();
		System.out.println("===========================" + rc);

		return true;
	}

	/**
	 * 中转文件
	 * 
	 * @param file
	 *            上传的文件
	 * @return 响应结果
	 */
	public static String httpClientUploadFile(File file) {
		final String remote_url = "https://upload.58cdn.com.cn/";// 第三方服务器请求地址
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		try {
			String fileName = file.getName();
			HttpPost httpPost = new HttpPost(remote_url);
			httpPost.setHeader("User-Agent",
					"Dalvik/2.1.0 (Linux; U; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00)");
			httpPost.setHeader("Host", "upload.58cdn.com.cn");
			// httpPost.setHeader("Content-Type",
			// "multipart/form-data;boundary=60ab2018-b3ef-4659-af77-a50c869997bx");
			httpPost.setHeader("Content-Type",
					ContentType.MULTIPART_FORM_DATA.toString());

			httpPost.setHeader("Charset", "utf-8");
			httpPost.setHeader("Connection", "keep-alive");

			httpPost.setHeader("Pic-Flash", "1");

			httpPost.setHeader("Pic-Path", "/zhuanzh/");
			httpPost.setHeader("Pic-Size", "0*0");

			httpPost.setHeader("Pic-Bulk", "0");
			httpPost.setHeader("Pic-dpi", "0");
			httpPost.setHeader("Pic-Cut", "0*0*0*0");
			httpPost.setHeader("Pic-IsAddWaterPic", "false");
			httpPost.setHeader("File-Extensions", "jpg");

			long leng = file.length();

			System.out.println("文件长度是>>>>>>>>>>>>>>>" + leng);
			// httpPost.setHeader("Content-Length", leng+"");

			FileBody bin = new FileBody(file, ContentType.create(
					"multipart/form-data", "UTF-8"));

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			FormBodyPart bodyPart = FormBodyPartBuilder.create().build();

			// builder.addPart(bin);

			// builder.addBinaryBody("file", FileUtils.openInputStream(file),
			// ContentType.DEFAULT_BINARY, fileName);// 文件流
			// builder.addTextBody("filename", fileName);//
			// 类似浏览器表单提交，对应input的name和value

			// builder.addBinaryBody("file", FileUtils.openInputStream(file));//
			// 文件流
			builder.addPart("file", bin);

			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);

			org.apache.http.HttpResponse response = httpClient
					.execute(httpPost);// 执行提交

			for (Header header : httpPost.getAllHeaders()) {
				// System.out.println(header.getName()+"="+header.getValue());
			}

			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				// 将响应内容转换为字符串
				result = EntityUtils.toString(responseEntity,
						Charset.forName("UTF-8"));
			}
			System.out.println("=============" + result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
