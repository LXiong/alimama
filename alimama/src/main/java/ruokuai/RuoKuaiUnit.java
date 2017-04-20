package ruokuai;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class RuoKuaiUnit {
	static final Logger logger = Logger.getLogger(RuoKuaiUnit.class);
	public DocumentBuilderFactory dbf;
	public DocumentBuilder db;

	/**
	 * Creates new form RuoKuaiTest
	 */
	public RuoKuaiUnit() {
		dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public String getImgStr(String username, String password, String typeid,
			String softId, String softKey, byte[] bytes) {
		String result = RuoKuai.createByPost(username, password, typeid, "90",
				softId, softKey, bytes);
		logger.error("验证码信息：" + result);
		Document dm = null;
		ByteArrayInputStream by = null;
		try {
			by = new ByteArrayInputStream(result.getBytes("utf-8"));
			dm = db.parse(by);
			NodeList resultNl = dm.getElementsByTagName("Result");
			NodeList idNl = dm.getElementsByTagName("Id");
			NodeList errorNl = dm.getElementsByTagName("Error");

			if (resultNl.getLength() > 0) {
				result = resultNl.item(0).getFirstChild().getNodeValue();
				// System.out.println(String.format("结果：%s\r\n",
				// resultNl.item(0).getFirstChild().getNodeValue()));
				if (idNl.getLength() > 0) {
					// System.out.println(String.format("ID:%s\r\n",
					// idNl.item(0).getFirstChild().getNodeValue()));
				}
			} else if (errorNl.getLength() > 0) {
				String error = errorNl.item(0).getFirstChild().getNodeValue();
				System.out.println(String.format("错误：%s\r\n", error));
			} else {
				System.out.println("未知问题\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				by.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public String getImgStr(byte[] bytes) {
		String username = ruokuai.Constant.DM_USERNAME;
		String password = SecretHelper.jie(ruokuai.Constant.DM_PASSWORD);
		String softId = ruokuai.Constant.DM_SOFTID;
		String softKey = ruokuai.Constant.DM_SOFTKEY;
		String typeid = ruokuai.Constant.DM_TYPEID;
		String result = getImgStr(username, password, typeid, softId, softKey,
				bytes);
		return result;
	}

	public String getImgStr(BufferedImage bufferedImage) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (bufferedImage != null) {
			try {
				ImageIO.write(bufferedImage, "png", baos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String result = null;
		try {
			long start = System.currentTimeMillis();
			//result = getImgStr(baos.toByteArray());
			//result = RecCodeAuto.getCode(baos.toByteArray());
			result = ImgRecTianyiTest.getCode(baos.toByteArray());
			long end = System.currentTimeMillis();
			logger.error("获取验证码耗时：" + (end - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 上报错题
	private void submitError(String id) {// GEN-FIRST:event_jButton3ActionPerformed
		String username = "qq77662857";
		String password = "qq77662857";
		String softId = "21910";
		String softKey = "e5b6aa8a11af4051b0b14cfa8abc8ba4";
		// String typeid = "3040";
		String result = "";
		result = RuoKuai.report(username, password, softId, softKey, id);
		displayXmlResult(result);
	}

	/**
	 * 解析xml结果
	 * 
	 * @param xml
	 *            xml返回结果字符串
	 */
	public void displayXmlResult(String xml) {
		if (xml.length() <= 0) {
			System.out.println("未知问题");
			return;
		}
		Document dm;
		try {
			dm = db.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
			NodeList resultNl = dm.getElementsByTagName("Result");
			NodeList idNl = dm.getElementsByTagName("Id");
			NodeList errorNl = dm.getElementsByTagName("Error");

			if (resultNl.getLength() > 0) {
				System.out.println(String.format("结果：%s\r\n", resultNl.item(0)
						.getFirstChild().getNodeValue()));
				// this.jTextArea1.append(String.format("结果：%s\r\n",
				// resultNl.item(0).getFirstChild().getNodeValue()));
				if (idNl.getLength() > 0) {
					System.out.println(String.format("ID:%s\r\n", idNl.item(0)
							.getFirstChild().getNodeValue()));
					// this.jTextArea1.append(String.format("ID:%s\r\n",
					// idNl.item(0).getFirstChild().getNodeValue()));
				}
			} else if (errorNl.getLength() > 0) {
				String error = errorNl.item(0).getFirstChild().getNodeValue();
				System.out.println(String.format("错误：%s\r\n", error));
			} else {
				System.out.println("未知问题\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		String str = new RuoKuaiUnit().getImgStr(IOUtils
				.toByteArray(new FileInputStream("e:\\testasdasd.png")));
		System.out.println(str);
	}
}
