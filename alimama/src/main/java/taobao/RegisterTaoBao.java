package taobao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataoke.Ma60;
import dataoke.PassWordCreate;
import util.LOG;
import util.StringUtils;
import util.mail.POP3ReceiveMailTest;
import util.mail.SimpleSendReceiveMessage;

public class RegisterTaoBao {
	
	static WebDriver webDriver = Main.webDriver;
	
	public static String phoneNum = null;
	
	
	
	static{
		Ma60.login();
	}
	
	
	
	public static void main(String[] args)throws Exception {
		receiveMail("andreysv03@hotmail.com","d40rfJ493");
		//execute("eduardsvro@hotmail.com","v65cx2HqH");
		//start1("eduardsvro@hotmail.com","v65cx2HqH");
		//start2("https://passport.alibaba.com/member/request_dispatcher.htm?from=ACTIVE_BY_URL&amp;_ap_action=registerActive&amp;t=CN-SPLIT-ARCAxgoiCFJFR0lTVEVSMgEBOIPvo8XhK0ABShB8pauX7nxkC7V_nOI7Tnghu-_vbBLsLvrtQGrS0UXP5u-2BqU");
	}
	
	
	public static String getCode(){
		try{
			 String code = "";
			 LOG.printLog("获取短信内容");
			 for(int i=0;i<8;i++){
				 String str = Ma60.getmsg();
				 if(org.apache.commons.lang.StringUtils.isBlank(str)){
					 Thread.sleep(4000);
					 continue;
				 }else{
					 if(str.contains("淘宝")){
						 code = str.substring(str.length()-6, str.length());
						 return code;
					 }
				 }
			 }
			 LOG.printLog("验证码为："+code);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static boolean execute(String u,String p)throws Exception{
		String url = start1(u, p);
		try{
			phoneNum = Ma60.getnum("BA4E0F33A9F5952");
			if(org.apache.commons.lang.StringUtils.isBlank(phoneNum)){
				LOG.printLog("获取手机号码有问题》》》》》》》》》》》》》》");
				return false;
			 }else if(phoneNum.contains("余额不足")){
				 LOG.printLog("余额不足退出程序>>>>>>>>>>>>>>>>>>>>>>>>>.");
				 System.exit(0);
			 }
			
			start2(url);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			 try{
				 LOG.printLog("释放手机号码");
				 Ma60.resleNum(phoneNum);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		}
		
		return true;
	}
	
	public static String receiveMail(String u,String p){
		try{
			SimpleSendReceiveMessage sendReceiveMessage = new SimpleSendReceiveMessage(){
				@Override
				public Object call(Message... messages) {
					for(Message message:messages){
						Date sendDate=new Date();
						try {
							sendDate = message.getSentDate();
							LOG.printLog("发送邮件时间为："+DateFormatUtils.format(sendDate, "yyyy-MM-dd HH:mm:ss"));
						} catch (MessagingException e1) {
							e1.printStackTrace();
						}
						sendDate = DateUtils.addSeconds(sendDate, 200);
						if(sendDate.getTime() < new Date().getTime()){
							continue;
						}
						StringBuffer content = new StringBuffer(30);
						try {
							POP3ReceiveMailTest.getMailTextContent(message, content);
							String url = StringUtils.getTaoBaoZhuCeURL(content.toString());
							LOG.printLog("连接地址："+url);
							return url;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return null;
					
				}
			};
			sendReceiveMessage.username = u;
			sendReceiveMessage.passwd = p ;
			sendReceiveMessage.init();
			String url = (String) sendReceiveMessage.receive();
			if(org.apache.commons.lang3.StringUtils.isNotBlank(url)&&url.contains("http")){
				return url;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static boolean start2(String url)throws Exception{
		try{
                webDriver.get(url);
                Thread.sleep(4000);
                int size = new Random().nextInt(4);
				String newPwd =  new PassWordCreate().createPassWord(6+size);
				LOG.printLog("输入密码："+newPwd);
                WebElement element = webDriver.findElement(By.id("J_Password"));
                element.sendKeys(newPwd);
                
                Thread.sleep(1000);
                LOG.printLog("输入密码："+newPwd);
                
                element = webDriver.findElement(By.id("J_RePassword"));
                element.sendKeys(newPwd);
                
                Thread.sleep(1000);
                newPwd =  new PassWordCreate().createPassWord(6+size);
                LOG.printLog("输入会员名称："+newPwd);
                
                //J_Nick
                element = webDriver.findElement(By.id("J_Nick"));
                element.sendKeys(newPwd);
                //J_Company
                newPwd =  new PassWordCreate().createPassWord(6+size);
                
                Thread.sleep(1000);
                LOG.printLog("输入公司名称："+newPwd);
                //J_Nick
                element = webDriver.findElement(By.id("J_Company"));
                element.sendKeys(newPwd);
                
                
                Thread.sleep(1000);
                String phone = phoneNum;
                LOG.printLog("输入电话号码："+phone);
                //J_Mobile
                element = webDriver.findElement(By.id("J_Mobile"));
                element.sendKeys(phone);
                
                Thread.sleep(1000);
                LOG.printLog("点击确认：");
                //J_BtnInfoForm
                element = webDriver.findElement(By.id("J_BtnInfoForm"));
                element.click();
                
                
                Thread.sleep(1000);
                LOG.printLog("点击获取手机验证码：");
                // J_BtnMobileCode
                element = webDriver.findElement(By.id("J_BtnMobileCode"));
                element.click();
                
                
                Thread.sleep(1000);
                String code = getCode();
                LOG.printLog("输入手机验证码："+code);
                
                if(org.apache.commons.lang.StringUtils.isBlank(code)){
                	LOG.printLog("验证码为null>>>>>>>>>>>>>>>>>>>>>");
                	 return false;
                }
                
                // 手机验证码  J_MobileCode
                element = webDriver.findElement(By.id("J_MobileCode"));
                element.sendKeys(code);
                
                Thread.sleep(1000);
                //J_BtnMobileCodeForm
                LOG.printLog("点击确认：");
                //J_BtnInfoForm
                element = webDriver.findElement(By.id("J_BtnMobileCodeForm"));
                element.click();
                
                Thread.sleep(2000);
                
                if(webDriver.getPageSource().contains("验码不正确")){
                	 LOG.printLog("注册失败，验证码不正确>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                	 return false;
                }
                LOG.printLog("注册完成>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
		
	}
	
	
	public static String start1(String mail,String pwd)throws Exception{
		webDriver.get("https://passport.alibaba.com/member/reg/enter_fill_email.htm?_regfrom=TB_ENTERPRISE&_lang=");
		Thread.sleep(1000);
		webDriver = webDriver.switchTo().frame("alibaba-register-box");

		LOG.printLog("检测是否有验证码");
		if(webDriver.getPageSource().contains("拖动到最右边")){
	    	 LOG.printLog("检测到拖动验证码>>>>>>>>>>>>>>>>>>>请手动拖动，拖动后按回车键继续");
	    	 System.in.read();
	    }else{
	    	LOG.printLog("没有验证码");
	    }
		
		if(webDriver.getPageSource().contains("alibaba-register-box")){
			webDriver = webDriver.switchTo().frame("alibaba-register-box");
		}
		
		 WebElement element =webDriver.findElement(By.id("J_Email"));
	     element.sendKeys(mail);
		 Thread.sleep(1000);
	    
	    LOG.printLog("输入邮箱："+mail);
	    
	    
	   Thread.sleep(2000);
	   LOG.printLog("点击下一步"); 
	   element = webDriver.findElement(By.id("J_BtnEmailForm"));
	   element.click();
	   
	   
	   if(webDriver.getPageSource().contains("不正确")){
		   LOG.printLog("验证码不正确，请手动处理后按回车键继续>>>>>>>>>>>>>");
		   System.in.read();
	   }
	   
	   String url = null;
	   for(int i=0;i<20;i++){
		   LOG.printLog("等待接受邮件>>>>>>>>>>>>>>>>>>>>>>>>5秒一次查询邮件");
		   Thread.sleep(5000);
		    url = receiveMail(mail, pwd);
		   if(org.apache.commons.lang3.StringUtils.isBlank(url)){
			   continue;
		   }else{
			   break;
		   }
	   }
	   LOG.printLog("获取url打开连接==="+url);
	   if(org.apache.commons.lang3.StringUtils.isBlank(url)){
		   LOG.printLog("程序退出，获取不到邮件url地址");
		   System.exit(0);
	   }
	   return url;
	}

}
