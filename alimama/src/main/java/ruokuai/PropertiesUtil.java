/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruokuai;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	public static final Map<String, String> propertiesMap = new HashMap<String, String>();
	private static final PropertiesUtil propertiesUtil = new PropertiesUtil();

	public PropertiesUtil() {
		Properties systemConfig = PropertiesUtil
				.getProperty("/configParameterSys.properties");
		propertiesMap.putAll((Hashtable) systemConfig);
	}

	public static String getPropertiesMap(String key) {
		return propertiesMap.get(key);
	}

	public static PropertiesUtil getPropertiesUtil() {
		return propertiesUtil;
	}

	public static Properties getProperty(String path) {
		InputStream inputStream = null;
		Properties p = new Properties();
		URL plugin = PropertiesUtil.class.getResource(path);
		try {
			inputStream = plugin.openStream();
			// PropertiesUtil.class.getClassLoader().getResourceAsStream("/cpu_cost_control.properties");
			p.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	public static List<String> getPropertyValues(String name) {
		Properties properties = getProperty(name);
		List<String> valueList = new ArrayList<String>();
		Enumeration<Object> enu = properties.elements();
		while (enu.hasMoreElements()) {
			valueList.add((String) enu.nextElement());
		}
		return valueList;
	}

	public static void main(String[] args) {
		System.out.println(Boolean.parseBoolean("true"));
		System.out.println(Boolean.parseBoolean(""));
	}

}
