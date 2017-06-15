package zhuanzhuan;

public class ParserUtils {
	public static String idParser(String url) {
		int index = url.indexOf("id=") + 3;
		int endIndex = url.substring(index).indexOf("&") + index;
		if (endIndex < index)
			return url.substring(index);
		else
			return url.substring(index, endIndex);
	}

	public static String imgPathParser(String url) {
		if (!url.startsWith("http")) {
			url = "https:" + url.trim();
		}
		return url.trim();
		// String caseUrl=url.toLowerCase();
		// int index =caseUrl.indexOf(".jpg")+4;
		// return url.substring(0,index);

	}

}
