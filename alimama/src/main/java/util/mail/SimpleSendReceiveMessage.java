package util.mail;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.imap.IMAPMessage;


/**
 * 简单发送和接收邮件
 * @author u1
 *
 */
public class SimpleSendReceiveMessage {

	//邮件通信会话
	Session session;
	//邮件接收处理对象
	Store store;
	
	//连接邮件发送的账号与密码
	/*String username="Ucm5tal@mail.ru";
	private String passwd="FOEyAx2m";
	private String receiveHost="imap.mail.ru";
	private String sendHost="smtp.mail.ru";*/
	
	
	/*String username="xiaomin0322@sina.com";
	private String passwd="xiaomin0322#####";
	private String receiveHost="imap.sina.com";
	private String sendHost="smtp.sina.com";*/

	
	public String username="petrv9zvev@hotmail.com";
	public String passwd="Mo8yOU8u";
	public String receiveHost="imap-mail.outlook.com";
	public String sendHost="smtp-mail.outlook.com";
	
	
	/**
	 * 邮件配置参数和连接接收邮件服务器
	 * @throws MessagingException
	 */
	private void init() throws MessagingException{
		Properties properties=new Properties();
		//设置发送和接收协议
		properties.put("mail.transport.protocal", "smtp");
		properties.put("mail.store.protocal", "imap");
		//设置ssl的端口
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.port", "993");
		properties.setProperty("mail.imap.socketFactory.port", "993");/*
		properties.put("mail.imap.port", "993");
		properties.put("mail.smtp.port", "465");*/
		//smtp认证
		properties.put("mail.smtp.auth", "true");
		//设置发送和接收处理类
		properties.put("mail.transport.class", "com.sun.mail.smtp.SMTPTransport");
		properties.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
		//设置发送邮件服务器
		properties.put("mail.smtp.host",sendHost);
		//获取邮件通信会话
		Authenticator auth=new MailAuthenticator();
		session=Session.getDefaultInstance(properties,auth);
		session.setDebug(true);
		//获取接收邮件对象
		store=session.getStore("imap");
		//连接接收邮件服务器
		store.connect(receiveHost, null, null);
	}
	
	/**
	 * 关闭邮件接收服务器
	 * @throws MessagingException
	 * @throws IOException 
	 */
	public void close() throws MessagingException, IOException
	{
		store.close();
		
	}
	
	/**
	 * 创建一封简单的邮件
	 * @param fromAddr
	 * @param toAddr
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public Message createSimpleMessage(String fromAddr,String toAddr) throws AddressException, MessagingException{
		//建立一封邮件
		MimeMessage message=new MimeMessage(session);
		//设置发送者和接收者
		message.setFrom(new InternetAddress(fromAddr));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
		//设置主题
		message.setSubject("使用JAVAMAIL发送邮件");
		//设置日期
		message.setSentDate(new Date(System.currentTimeMillis()));
		//设置正文
		message.setText("今天是2015-6-12，离职一个周，准备下一份工作");
		return message;
	}
	
	public Message createComplexMessage(String fromAddr,String toAddr) throws AddressException, MessagingException{
		//建立一封邮件
		MimeMessage message=new MimeMessage(session);
		//设置发送者和接收者
		message.setFrom(new InternetAddress(fromAddr));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
		//设置主题
		message.setSubject("使用JAVAMAIL发送邮件");
		//设置日期
		message.setSentDate(new Date(System.currentTimeMillis()));
		//设置正文
		Multipart mp=createMultiPart();
		message.setContent(mp);
		return message;
	}
	
	/**
	 * 创建复杂的正文
	 * @return
	 * @throws MessagingException 
	 */
	private Multipart createMultiPart() throws MessagingException {
		// TODO Auto-generated method stub
		Multipart multipart=new MimeMultipart();
		
		//第一块
		BodyPart bodyPart1=new MimeBodyPart();
		bodyPart1.setText("创建复杂的邮件，此为正文部分");
		multipart.addBodyPart(bodyPart1);
		
		//第二块 以附件形式存在
		MimeBodyPart bodyPart2=new MimeBodyPart();
		//设置附件的处理器
		FileDataSource attachFile=new FileDataSource(ClassLoader.getSystemResource("attach.txt").getFile());
		DataHandler dh=new DataHandler(attachFile);
		bodyPart2.setDataHandler(dh);
		bodyPart2.setDisposition(Part.ATTACHMENT);
		bodyPart2.setFileName("test");
		multipart.addBodyPart(bodyPart2);
		
		return multipart;
	}

	/**
	 * 发送邮件
	 * @param message
	 * @throws MessagingException
	 */
	public void send(Message message) throws MessagingException{
		Transport.send(message);
	}
	
	/**
	 * 接收邮件
	 * @throws Exception 
	 */
	public Object receive() throws Exception{
		return browseMessageFromFolder("INBOX");
	}

	/**
	 * 根据邮件夹名称浏览邮件
	 * @param folderName
	 * @throws Exception
	 */
	private Object browseMessageFromFolder(String folderName) throws Exception {
		// TODO Auto-generated method stub
		Folder folder=store.getFolder(folderName);
		if(folder==null) throw new Exception(folderName+"邮件夹不存在");
		return browseMessageFromFolder(folder);
	}

	/**
	 * 根据邮件夹对象浏览邮件
	 * @param folder
	 * @throws MessagingException 
	 * @throws IOException 
	 */
	private Object browseMessageFromFolder(Folder folder) throws Exception {
		// TODO Auto-generated method stub
		folder.open(Folder.READ_ONLY);
		System.out.println("总共有"+folder.getMessageCount()+"封邮件");
		System.out.println("总共有"+folder.getUnreadMessageCount()+"封未读邮件");
		Message[] messages=folder.getMessages();
		return call(messages);
		/*for (int i = 1; i <=messages.length; i++) {
			System.out.println("这是第"+i+"封邮件");
			//getMessageHeader(folder.getMessage(i));
			writeSubjectToOutPutStream(folder.getMessage(i));;
		}*/
		//folder.close(false);
	}
	
	/**
	 * 遍历每封邮件的头部部分
	 * @param message
	 * @throws MessagingException 
	 */
	private void getMessageHeader(Message message) throws MessagingException {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		Enumeration<Header> allHeader=message.getAllHeaders();
		for(;allHeader.hasMoreElements();){
			Header header=allHeader.nextElement();
			System.out.println(header.getName()+":"+header.getValue());
		}
	}

	/**
	 * 将每封邮件的主题写入输出流中
	 * @param message
	 * @throws MessagingException 
	 */
	private void writeSubjectToOutPutStream(Message message) throws Exception {
		if(message == null){
			return ;
		}
		IMAPMessage imapMessage = (IMAPMessage )message;
		try{
			
			//POP3ReceiveMailTest.parseMessage(message);
			call(imapMessage);
			// TODO Auto-generated method stub
			//System.out.println("邮件主题为:"+imapMessage.getSubject());
			//System.out.println(" 内容为："+imapMessage.getContentID());
		}catch(Exception e){
			e.printStackTrace();
		}
	    Thread.sleep(1000);
	}
	
	public  Object call(Message... message) {
		try {
			POP3ReceiveMailTest.parseMessage(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args){
		SimpleSendReceiveMessage sendReceiveMessage=new SimpleSendReceiveMessage();
		try {
			sendReceiveMessage.init();
			/*Message message=sendReceiveMessage.createSimpleMessage(sendReceiveMessage.username, sendReceiveMessage.username);
			sendReceiveMessage.send(message);
			message=sendReceiveMessage.createComplexMessage(sendReceiveMessage.username, sendReceiveMessage.username);
			sendReceiveMessage.send(message);*/
			
			sendReceiveMessage.receive();
			sendReceiveMessage.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 登陆认证
	 * @author u1
	 *
	 */
	private class MailAuthenticator extends Authenticator{

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			// TODO Auto-generated method stub
			return new PasswordAuthentication(username, passwd);
		}
		
	}
}
