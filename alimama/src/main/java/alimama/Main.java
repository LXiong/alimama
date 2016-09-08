package alimama;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    //用户名
	static String uname = PropertiesUtil.getPropertiesMap("alimama.uname");
	//密码
	static String pwd = PropertiesUtil.getPropertiesMap("alimama.pwd");
	
	//27220956
	static String qq = PropertiesUtil.getPropertiesMap("alimama.qq");

	//设置百分比
	static String baifenbi = PropertiesUtil.getPropertiesMap("alimama.baifenbi");

	public static WebDriver webDriver = null;
	

	public static boolean validate(){
		String url = "https://github.com/xiaomin0322/alimama/releases/download/int/validate.txt";
		try {
			java.util.Properties properties = PropertiesUtil.getProperty(new URL(url).openStream());
			System.out.println("properties："+properties);
			String flag  = properties.getProperty("validate");
			System.out.println("flag:"+flag);
			return Boolean.valueOf(flag);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void init() {
		// webDriver
		// =SeleniumUtil.initWebDriver("http://192.168.1.120:4444/wd/hub");
		System.out.println("webDriver init start >>>>>>>>>>>>>>>>>");
		webDriver = new FirefoxDriver();
		webDriver.manage().window().maximize();

		System.out.println("webDriver init end <<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		System.out.println("uname = " + uname + "  pwd = " + pwd);
	}

	public static void main(String[] args) throws Exception {
		/*
		 * String fenzuName = "dateQ群打造爆款联系776628578"; String date =
		 * DateFormatUtils.format(new Date(), "HH:mm:ss");
		 * System.out.println(fenzuName.replace("date", date));
		 */
		//if(validate()){
		if(true){
			System.out.println("验证成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			execute();
		}else{
			System.out.println("验证失败>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
	}

	// 分组名称
	public static String getFenzuName() {
		String fenzuNameF = "dateQ群打造爆款联系"+qq;
		String date = DateFormatUtils.format(new Date(), "HH:mm:ss");
		fenzuName = fenzuNameF.replace("date", date);
		return fenzuName;
	}

	public static void execute() throws Exception {
		init();
		if (!login()) {
			System.out.println("登陆失败>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			return;
		}
		List<String> keys = new ArrayList<String>();
		keys.add("衣服");
		String[] keyss = null;
		if (PropertiesUtil.getPropertiesMap("alimama.keys") != null) {
			keyss = PropertiesUtil.getPropertiesMap("alimama.keys").split(",");
		} else {
			keyss = "昭通,龙江,交代,选出,回答者,状况,言情,版本,马尔康,我来,上天,山水,后期,板块,乐清,武义,什么时候,星期四,最后登陆,将有,发型,涉县,激烈,球员,四十,之多,是我国,呻吟,半导体,温泉,才可以,风光,芷江,骑兵,不认识,就此,文科,期刊,部门,运动,临夏市,现已,本报,扎实,并与,分别为,差距,护肤,呈现出,好友列表,谁说,乖乖,发现自己,长城,之外,玉环,并以,年轻,门源,两地,大幅,能看,注意到,公司简介,你是,脸颊,溆浦,灵活,模板,嘴里,通辽,持股,奉新,之星,蠡县,赣榆,乌鲁木齐,参照,昭觉,虚假,姐姐,例子,我又,房地产,这件事,男生,不信,到您,最初,融合,鱼鳞病,法庭,显现,惊奇,蔬菜,情人节,一页,是因为,此刻,停车场"
					.split(",");
		}
		System.out.println("keys=" + Arrays.toString(keyss));
		System.out.println("all size = " + keyss.length);

		for (String key : keyss) {
			System.out.println("key :" + key);
			// 添加商品
			try {
				boolean addflag = addshangpingAll(key);
				if (addflag) {
					faqizhaoshang(fenzuName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	static String fenzuName = "8.16.072Q群打造爆款联系"+qq;

	//最大数
	static int maxSize = StringUtils.isBlank(PropertiesUtil.getPropertiesMap("alimama.maxSize")) ? 198 :Integer.valueOf(PropertiesUtil.getPropertiesMap("alimama.maxSize"));

	/**
	 * 添加商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean addshangpingAll(String queryName) throws Exception {

		try {
			webDriver.get("http://pub.alimama.com/promo/search/index.htm");
			Thread.sleep(5000);

			// 搜索
			WebElement elementQuery = webDriver.findElement(By.id("q"));
			elementQuery.click();
			elementQuery.sendKeys(queryName);

			// 搜索 btn btn-brand search-btn
			elementQuery = webDriver.findElement(By.xpath("//*[@class='btn btn-brand search-btn']"));
			elementQuery.click();
			Thread.sleep(2000);

			// 已选数据 <span p-id="110">1</span>
			String size = "0";
			System.out.println("已选>>>>>>>>>>>>>>>>>>>>>>>" + size);

			while (Integer.valueOf(size) < maxSize) {
				WebElement elements = webDriver.findElement(By.xpath("//*[@class='select-btn select-all ']"));
				elements.click();
				Thread.sleep(1000);

				// 已选数
				elementQuery = webDriver.findElement(By.xpath("//*[@class='color-brand']"));
				size = elementQuery.getText();
				System.out.println("已选>>>>>>>>>>>>>>>>>>>>>>>" + size);
				if (!(Integer.valueOf(size) < maxSize)) {
					break;
				}

				// 下一页 btn-last btn btn-xlarge btn-white
				WebElement aElement = webDriver
						.findElement(By.xpath("//*[@class='btn-last btn btn-xlarge btn-white']"));
				aElement.click();
				Thread.sleep(3000);

			}

			Thread.sleep(1000);
			// 加入选品库 btn-brand add-selection
			WebElement element = webDriver.findElement(By.xpath("//a[@class='btn-brand add-selection']"));
			element.click();

			Thread.sleep(1000);
			// 新建普通分组 btn btn-common w140
			element = webDriver.findElement(By.xpath("//*[@class='btn btn-common w140']"));
			element.click();

			Thread.sleep(1000);
			// 分组输入框 J_groupTitle
			element = webDriver.findElement(By.id("J_groupTitle"));
			element.click();
			// 设置分组名称
			getFenzuName();
			element.sendKeys(fenzuName);
			Thread.sleep(1000);

			// 点击创建 btn btn-brand w80 mr10
			element = webDriver.findElement(By.xpath("//*[@class='btn btn-brand w80 mr10']"));
			element.click();
			Thread.sleep(1000);

			// 点击加入 btn btn-brand w100 mr10
			element = webDriver.findElement(By.xpath("//*[@class='btn btn-brand w100 mr10 ']"));
			element.click();
			Thread.sleep(1000);
			System.out.println("加入成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("失败>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			return false;
		}
		return true;
	}

	/**
	 * 添加商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean addshangping(String queryName) throws Exception {

		webDriver.get("http://pub.alimama.com/promo/search/index.htm");
		Thread.sleep(5000);

		// 搜索
		WebElement elementQuery = webDriver.findElement(By.id("q"));
		elementQuery.click();
		elementQuery.sendKeys(queryName);

		// 搜索 btn btn-brand search-btn
		elementQuery = webDriver.findElement(By.xpath("//*[@class='btn btn-brand search-btn']"));
		elementQuery.click();
		Thread.sleep(2000);

		// 已选数据 <span p-id="110">1</span>
		String size = "0";
		System.out.println("已选>>>>>>>>>>>>>>>>>>>>>>>" + size);

		while (Integer.valueOf(size) < maxSize) {
			List<WebElement> elements = webDriver.findElements(By.xpath("//*[@class='block-search-box tag-wrap']"));
			System.out.println(elements.size());
			if (elements != null) {
				for (int i = 0; i < elements.size(); i++) {
					WebElement element = elements.get(i);
					// box-btn-right
					// box-btn-group
					// a[@class='box-btn-right']
					WebElement aElement = element
							.findElement(By.xpath(".//div[@class='box-btn-group']/*[@class='box-btn-right']"));
					System.out.println(aElement.getText());
					aElement.click();
					Thread.sleep(1000);

					// 已选数
					elementQuery = webDriver.findElement(By.xpath("//*[@class='color-brand']"));
					size = elementQuery.getText();
					System.out.println("已选>>>>>>>>>>>>>>>>>>>>>>>" + size);
					if (!(Integer.valueOf(size) < maxSize)) {
						break;
					}

				}
			}
			// 下一页 btn-last btn btn-xlarge btn-white
			WebElement aElement = webDriver.findElement(By.xpath("//*[@class='btn-last btn btn-xlarge btn-white']"));
			aElement.click();
			Thread.sleep(3000);

		}

		Thread.sleep(1000);
		// 加入选品库 btn-brand add-selection
		WebElement element = webDriver.findElement(By.xpath("//a[@class='btn-brand add-selection']"));
		element.click();

		Thread.sleep(1000);
		// 新建普通分组 btn btn-common w140
		element = webDriver.findElement(By.xpath("//*[@class='btn btn-common w140']"));
		element.click();

		Thread.sleep(1000);
		// 分组输入框 J_groupTitle
		element = webDriver.findElement(By.id("J_groupTitle"));
		element.click();
		// 设置分组名称
		getFenzuName();
		element.sendKeys(fenzuName);
		Thread.sleep(1000);

		// 点击创建 btn btn-brand w80 mr10
		element = webDriver.findElement(By.xpath("//*[@class='btn btn-brand w80 mr10']"));
		element.click();
		Thread.sleep(1000);

		// 点击加入 btn btn-brand w100 mr10
		element = webDriver.findElement(By.xpath("//*[@class='btn btn-brand w100 mr10 ']"));
		element.click();
		Thread.sleep(1000);

		System.out.println("加入成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		return true;
	}

	/**
	 * 发起招商
	 * 
	 * @throws Exception
	 */
	public static void faqizhaoshang(String name) throws Exception {
		webDriver.get("http://pub.alimama.com/manage/selection/list.htm");
		Thread.sleep(5000);
		// link-area
		List<WebElement> elements = webDriver.findElements(By.xpath("//a[@class='link-area']"));
		WebElement element = elements.get(0);
		String text = element.getText();
		if (text.contains(name)) {
			System.out.println("招商选择正确>>>>>>>>>>>>>>>>>>>>");
		}
		// 发起招商 btn btn-white
		element = webDriver.findElement(By.xpath("//a[@class='btn btn-white']"));
		element.click();
		Thread.sleep(1000);

		// Q群淘客打造爆款联系QQ77662857

		// 招商活动名称
		element = webDriver.findElement(By.id("J_title"));
		element.click();
		element.sendKeys("Q群淘客打造爆款联系QQ"+qq);
		Thread.sleep(1000);
		
		
		//报名 活动日期
		
		String dataStr = DateFormatUtils.format(DateUtils.addDays(new Date(), 1), "yyyy-MM-dd");
		
		//活动开始日期
		Date nextDate = DateUtils.addDays(new Date(), 3);
		String nextDateStr  = DateFormatUtils.format(nextDate, "yyyy-MM-dd");
		
		//活动截止日期
		Date nextEndDate = DateUtils.addDays(new Date(), 4);
		String nextEndDateStr  = DateFormatUtils.format(nextEndDate, "yyyy-MM-dd");
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		
		//卖家报名时间  joinStartTime
		//js.executeScript("document.getElementsByName('joinStartTime')[0].value='"+dataStr+"'");
		//Thread.sleep(1000);
		
		//卖家报名时间   joinEndTime
		//btn btn-common btn-calendar
		List<WebElement> webElementsDates=webDriver.findElements(By.xpath("//button[@class='btn btn-common btn-calendar']"));
		

		element = webElementsDates.get(0);
		element.click();
		Thread.sleep(1000);
		element = webDriver.findElement(By.xpath("//span[@data-value='10']"));
		element.click();
		Thread.sleep(1000);
		
		//js.executeScript("document.getElementsByName('joinEndTime')[0].value='"+dataStr+"'");
		//Thread.sleep(1000);
		
		//活动起止时间  startTime
		//element = webDriver.findElement(By.name("startTime"));
		//element.sendKeys(nextDateStr);
		
		element = webElementsDates.get(1);
		element.click();
		Thread.sleep(1000);
		element = webDriver.findElement(By.xpath("//span[@data-value='10']"));
		element.click();
		Thread.sleep(1000);
		
		//js.executeScript("document.getElementsByName('startTime')[0].value='"+nextDateStr+"'");
		//Thread.sleep(1000);
		
		//活动起止时间 endTime
//		element = webDriver.findElement(By.name("endTime"));
		//element.sendKeys(nextEndDateStr);
		
		element = webElementsDates.get(2);
		element.click();
		Thread.sleep(1000);
		element = webDriver.findElement(By.xpath("//span[@data-value='12']"));
		element.click();
		Thread.sleep(1000);
		
		//js.executeScript("document.getElementsByName('endTime')[0].value='"+nextEndDateStr+"'");
		//Thread.sleep(1000);
		
		element = webElementsDates.get(3);
		element.click();
		Thread.sleep(1000);
		element = webDriver.findElement(By.xpath("//span[@data-value='13']"));
		element.click();
		Thread.sleep(1000);

		// 旺旺号 边花生
		element = webDriver.findElement(By.name("wangwangName"));
		element.click();
		element.sendKeys("边花生");
		Thread.sleep(1000);

		// 全选商品 <input type="checkbox" class="checkbox" data-linkage-name="all"
		// p-id="382">
		element = webDriver.findElement(By.xpath("//input[@data-linkage-name='all']"));
		element.click();
		Thread.sleep(1000);

		// 批量设置 btn btn-gray w100 mr5
		element = webDriver.findElement(By.xpath("//*[@class='btn btn-gray w100 mr5']"));
		element.click();
		Thread.sleep(1000);

		// 设置百分比 input w100
		element = webDriver.findElement(By.xpath("//input[@class='input w100']"));
		element.click();
		element.sendKeys(baifenbi);
		Thread.sleep(1000);

		// 应用 btn btn-brand w100
		element = webDriver.findElement(By.xpath("//*[@class='btn btn-brand w100']"));
		element.click();
		Thread.sleep(1000);

		// 完成 btn btn-brand w120
		element = webDriver.findElement(By.xpath("//*[@class='btn btn-brand w120']"));
		element.click();
		Thread.sleep(5000);

		String page = webDriver.getPageSource();
		if (page.contains("招商需求创建成功")) {
			System.out.println("招商需求创建成功 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}

	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean login() throws Exception {
		webDriver.get("http://pub.alimama.com/");
		Thread.sleep(5000);

		// webDriver.get("https://login.taobao.com/member/login.jhtml?style=mini&amp;newMini2=true&amp;from=alimama&amp;redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&amp;full_redirect=true&amp;disableQuickLogin=true");
		webDriver.switchTo().frame("taobaoLoginIfr");

		Thread.sleep(1000);

		String pages = webDriver.getPageSource();

		//System.out.println(pages);

		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		// WebElement c =(WebElement)js.executeScript("return
		// window.document.getElemeById('J_Quick2Static');");
		// WebElement c =(WebElement)js.executeScript("return
		// window.frames['taobaoLoginIfr'].window.document.getElementsById('J_Static2Quick');");
		WebElement c = webDriver.findElement(By.id("J_Quick2Static"));

		c.click();

		Thread.sleep(2000);

		WebElement element = webDriver.findElement(By.id("TPL_username_1"));
		element.click();

		Thread.sleep(2000);
		//
		// c = webDriver.findElement(By.id("J_Static2Quick"));
		// c.click();

		Thread.sleep(2000);

		element.sendKeys(uname);

		element = webDriver.findElement(By.id("TPL_password_1"));
		element.click();
		element.sendKeys(pwd);

		element = webDriver.findElement(By.id("J_SubmitStatic"));
		element.click();

		Thread.sleep(5000);

		String pageStr = webDriver.getPageSource();
		if (!pageStr.contains(uname)) {
			System.out.println("登陆失败>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			return true;
		}
		return true;
	}

}
