package taobao;

import javax.mail.Message;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.LOG;
import util.StringUtils;
import util.mail.POP3ReceiveMailTest;
import util.mail.SimpleSendReceiveMessage;

public class RegisterTaoBao {
	
	static WebDriver webDriver ;
	public static void main(String[] args) {
		
	}
	
	public static String receiveMail(String u,String p){
		try{
			SimpleSendReceiveMessage sendReceiveMessage = new SimpleSendReceiveMessage(){
				@Override
				public Object call(Message... messages) {
					for(Message message:messages){
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
			String url = (String) sendReceiveMessage.receive();
			if(org.apache.commons.lang3.StringUtils.isNotBlank(url)&&url.contains("http")){
				return url;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String start1(String mail,String pwd)throws Exception{
		webDriver.get("https://passport.alibaba.com/member/reg/enter_fill_email.htm?_regfrom=TB_ENTERPRISE&_lang=");
		WebElement element =webDriver.findElement(By.id("J_Email"));
	    element.sendKeys(mail);
	    LOG.printLog("输入邮箱："+mail);
	    
	    if(webDriver.getPageSource().contains("拖动到最右边")){
	    	 LOG.printLog("检测到拖动验证码>>>>>>>>>>>>>>>>>>>请手动拖动，拖动后按回车键继续");
	    	 System.in.read();
	    }
	    
	   LOG.printLog("点击下一步"); 
	   element = webDriver.findElement(By.id("J_BtnEmailForm"));
	   element.click();
	   
	   String url = null;
	   for(int i=0;i<12;i++){
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
	   return url;
	}

}
