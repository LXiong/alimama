package github;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class GitHubUtils {
	
	public static void main(String[] args)throws Exception {
		commitOline("66666");
	}
	
	public static void commitOline(String content){
		String date =DateFormatUtils.format(new Date(), "yyyyMMdd");
		String path =date+"/"+ DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmss") +".txt";
		commit("xiaomin99/online",content, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), path, false);
	}
	
	public static void commit(String rep,String content,String commitmsg,String path,boolean isAdd){
		try{
		GitHub github = GitHub.connectUsingPassword("xiaomin0322@sina.cn", "xiaomin0322#####");
		GHRepository repo =github.getRepository(rep);
		
		GHContent contentObj = null;
		if(isAdd){
			List<GHContent> contents = repo.getDirectoryContent("/");
			if(CollectionUtils.isNotEmpty(contents)){
				for(GHContent c:contents){
					String name = c.getName();
					System.out.println("name==="+name);
					if(path.equals(name)){
						System.out.println("找到目标文件");
						contentObj = c;
					}
				}
			}
		}
		if(contentObj==null){
			System.out.println("创建文件>>>>>>>>>>>>>>>>>>>>>");
			repo.createContent(content,commitmsg, path);
			//contentObj = repo.getFileContent(path);
		}
		if(isAdd){
           StringBuilder builder = new StringBuilder();
			String str = IOUtils.toString(contentObj.read());
			System.out.println("获取远程内容为："+str);
			builder.append(str).append("\r\n");
			builder.append(content);
			contentObj.update(builder.toString(), commitmsg);
		}
		
		System.out.println("提交成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}catch(Exception e){
			System.out.println("提交出现异常>>>>>>>>>>>>>>>>>>>>>>>>>");
			e.printStackTrace();
		}
		
	}
	
	public static void testCreate()throws Exception{
		GitHub github = GitHub.connectUsingPassword("xiaomin0322@sina.com", "xiaomin0322####");
		GHCreateRepositoryBuilder repo = github.createRepository("testgithub");
		repo.create();
	}

}
