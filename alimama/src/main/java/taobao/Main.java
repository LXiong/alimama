package taobao;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import util.LOG;
import util.SeleniumUtil;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		executeAll();
	}
	
	
    public static WebDriver webDriver = SeleniumUtil.initChromeDriver();
	static int maxOkSize = 200;
	static int allOkSize = 0;
	static String tnum = "";
	static int errorSize = 0;
	static int count = 0;
	public static void executeAll()throws Exception{
		List<String> lists=FileUtils.readLines(new File("d:\\ddd.txt"));
		for(String s:lists){
			if(StringUtils.isBlank(s)){
				continue;
			}
			count++;
			try{
					 String uname = s.split("\\----")[0].trim();
					 String pwd = s.split("\\----")[1].trim();
					 String mailU = s.split("\\----")[2].trim();
					 String mailP = s.split("\\----")[3].trim();
					 System.out.println("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count);
					 LOG.printLog("开始登陆大淘客>>>>>>>>>>>>>>>>>");
					 boolean flag = DaTaoKe.login(uname, pwd);
					 LOG.printLog("开始登陆大淘客>>>>>>>>>>>>>>>>>结果："+flag);
					 if(flag){
						 LOG.printLog("开始淘宝注册>>>>>>>>>>>>>>>>>");
						 boolean flagTaoBao = RegisterTaoBao.execute(mailU, mailP);
						 LOG.printLog("淘宝注册结果>>>>>>>>>>>>>>>>>结果："+flagTaoBao);
						 if(flagTaoBao){
							 LOG.printLog("开始大淘客授权>>>>>>>>>>>>>>>>>");
							 flagTaoBao = DaTaoKe.shouquan(uname, pwd, mailU, mailP);
							 LOG.printLog("开始大淘客授权结果>>>>>>>>>>>>>>>>>结果："+flagTaoBao);
							if(flagTaoBao){
								FileUtils.write(new File("D:\\OKtaobao.txt"), s+"----"+RegisterTaoBao.phoneNum+"\r\n", true);
							}
						 }
					 }
           }catch(Exception e){
				e.printStackTrace();
				FileUtils.write(new File("D:\\ERRORtaobao.txt"), s+"\r\n", true);
			}finally {
				try{
					 LOG.printLog("关闭浏览器");
					 webDriver.close();
					 LOG.printLog("按回车键后重启打开浏览器开始注册>>>>>>>>>>>>>>>>>>>>>>>");
					 System.in.read();
					 webDriver = SeleniumUtil.initChromeDriver();
					//DaTaoKe.webGet("http://www.dataoke.com/logout");
					//Thread.sleep(Cmd.getSleepTime(1000, 3000));
				}catch(Exception e){
					
				}
			}
		}
	}	
}
