package dataoke;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Element;

public class GetGoodIdsByDataoke {

	public static void main(String[] args) throws Exception {
		execute();
	}
	
	static File out = new File("d:\\goodsurls.txt");
	
	public static void execute()throws Exception{
		for(int j=1;j<554;j++){
			try{
			List<Element> list = Monitor
					.getTopIds("http://www.dataoke.com/qlist/?page="+j);
			for (int i = 0; i < list.size(); i++) {
				Element e = list.get(i);
				//System.out.println(e);
				//String pid = e.attr("id").replace("goods-items_", "");
				//System.out.println("pid==="+pid);
				String goodsId = e.attr("data_goodsid");
				String url = "https://detail.tmall.com/item.htm?id="+goodsId;
				FileUtils.write(out, url+"\r\n",true);
				System.out.println(url+"當前是多少頁："+j);
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}
			Thread.sleep(1000);
		}
		
	}

}
