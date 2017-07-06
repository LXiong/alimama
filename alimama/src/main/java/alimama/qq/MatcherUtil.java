package alimama.qq;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;

public class MatcherUtil {

	public static void main(String[] args) throws Exception{
	File baseDir = new File("D:\\dataoke\\记录");	
	for(File f:baseDir.listFiles()){
		String content =	FileUtils.readFileToString(f);
		List<String> urls = getUrl(content);
		System.out.println(urls.size());
		FileUtils.writeLines(new File("g:\\test.txt"), urls,true);
	}	

/*	List<List<String>> list  = new ArrayList<List<String>>();
	System.out.println(URLEncoder.encode("https://item.taobao.com/item.htm?id=537764519790"));*/
	
	
	}
	
	 /** 
     * 拆分任务 
     *  
     * @param tasks 
     * @param 拆分数量 
     * @return 
     */  
    public static <T> List<T> mergeTask(List<List<T>> tasks) {  
        if(tasks==null){  
            return null;  
        }  
        List<T> list = Lists.newArrayList();  
        for(List<T> l:tasks){  
            if(l!=null){  
                list.addAll(l);  
            }  
        }  
        return list;  
    }  
  
    /** 
     * 拆分任务 
     *  
     * @param tasks 
     * @param 拆分数量 
     * @return 
     */  
    public static <T> List<List<T>> splitTask(List<T> tasks, Integer taskSize) {  
        List<List<T>> list = new ArrayList<List<T>>();   
        if(tasks==null || taskSize <= 0){  
            return list;  
        }   
        if(tasks.size() < taskSize){  
            list.add(tasks);  
            return list;  
        }  
           
          
        int baseNum = tasks.size() / taskSize; // 每个list的最小size  
        int remNum = tasks.size() % taskSize; // 得到余数  
  
        int index = 0;  
        for (int i = 0; i < taskSize; i++) {  
            int arrNum = baseNum; // 每个list对应的size  
            if (i < remNum) {  
                arrNum += 1;  
            }  
            List<T> ls = new ArrayList<T>();  
            for (int j = index; j < arrNum + index; j++) {  
                ls.add(tasks.get(j));  
            }  
            list.add(ls);  
            index += arrNum;  
        }  
        return list;  
    }  
  
  

	public static List<String> matcher(String content) {
		Pattern p = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?");

		// Matcher m = p.matcher(
		// "多功能儿童智能手表手机天猫券后【69元】包邮秒杀领券:http://shop.m.taobao.com/shop/coupon.htm?activity_id=e4f0d0eeda8a45b78ea1ee2adf0f82c9&seller_id=1699430130
		// \r\n 抢购:http://s.click.taobao.com/XqZdaRx");
		Matcher m = p.matcher(content);

		List<String> list = new ArrayList<String>();
		while (m.find()) {
			/// System.out.println(m.group());
			list.add(m.group());
		}
		return list;

	}

	public static List<String> getUrl(String content) {
		List<String> list = matcher(content);
		
		List<String> newList = new ArrayList<String>();
		for (String str : list) {
			//if (!str.contains("shop.m.taobao.com")) {
			
			/*if(!str.contains("id")){
				continue;
			}
			*/
			if ((str.contains("tmall.com") || str.contains("taobao.com")) 
					&& !str.contains("shop.m.taobao.com")
					//&& !str.contains("click.taobao.com")
					&& !str.contains("taoquan.taobao.com")) {
				newList.add(str);
			}
		}
		return newList;

	}

}