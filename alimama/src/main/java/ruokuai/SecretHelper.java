package ruokuai;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class SecretHelper {
	/**
	 * 加密解密接口
	 * 
	 * @param data
	 *            数据
	 * @param password
	 *            加密解密密码 必须8位字节
	 * @param flag
	 *            加密解密标志 0：加密 ，1：解密
	 * @return
	 */
	@SuppressWarnings("all")
	public static String doWork(String data, String password, int flag) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			//
			if (flag == 0) {
				BASE64Encoder base64encoder = new BASE64Encoder();
				cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
				return base64encoder.encode(cipher.doFinal(data
						.getBytes("UTF-8")));
			} else {
				BASE64Decoder base64decoder = new BASE64Decoder();
				byte[] encodeByte = base64decoder.decodeBuffer(data);
				cipher.init(Cipher.DECRYPT_MODE, securekey, random);
				byte[] decoder = cipher.doFinal(encodeByte);
				return new String(decoder, "UTF-8");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			 String s = jia("00-26-9E-F4-1F-54_2015-12-01");
			 System.out.println(s);
			// s=jia(MacAddressUtil.getMacAddress());
			System.out.println(s);
			// String rs =
			System.out.println(jie(s));
		System.out.println(jie("DmanKpl+hBaFdvVOaXw0N2bbHc4tu9Hre8Ij/hfwndciujzf+2t3KT63avFEmE/v"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public static String jie(String desc) {
		String password = "01010101";
		// 解密
		String str = SecretHelper.doWork(desc, password, 1);
		str = SecretHelper.doWork(str, password, 1);
		// System.out.println("明文：" + str);
		return str;

	}

	public static String jia(String str) {
		String password = "01010101";
		String desc = SecretHelper.doWork(str, password, 0);
		desc = SecretHelper.doWork(desc, password, 0);
		// System.out.println("密文：" + desc);
		return desc;

	}
}