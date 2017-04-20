package ruokuai;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

public class Constant {
	static final Logger logger = Logger.getLogger(Constant.class);

	public static final String DM_USERNAME = PropertiesUtil
			.getPropertiesMap("dm.username");

	public static String DM_PASSWORD = PropertiesUtil
			.getPropertiesMap("dm.password");

	public static final String DM_SOFTID = PropertiesUtil
			.getPropertiesMap("dm.softId");
	public static final String DM_SOFTKEY = PropertiesUtil
			.getPropertiesMap("dm.softKey");
	public static final String DM_TYPEID = PropertiesUtil
			.getPropertiesMap("dm.typeid");



}
