package util.img;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class WordToPic {

	//
	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	public static String TextToPic(String text, int width, int height,int fontSize) {
		try {
			String filepath = "D://" + getDate() + ".jpg";
			File file = new File(filepath);
			System.out.println("topic=" + text);
			Font font = new Font("微软雅黑", Font.BOLD, fontSize);
			BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			g2.setBackground(null);
			g2.clearRect(0, 0, width, height);
			g2.setFont(font);
			g2.setPaint(Color.BLACK);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.3f));
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			printString(g2, text, (width - (text.length() * fontSize)) / 2 + 0,(height - fontSize) / 2 + 40, fontSize);
			g2.dispose();
			ImageIO.write(bi, "png", file);
			return "image" + getDate() + ".png";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static File TextToPicTaoKe(String title,String oldPic,String newPic,String quanPic,int width, int height,File file) {
		try {
			int fontSize = 20;
			//String filepath = "D://dataoke//" + 2 + ".jpg";
			//File file = new File(filepath);
			
			BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			g2.setBackground(null);
			g2.clearRect(0, 0, width, height);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.3f));
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			Font font = new Font("STYLE_PLAIN", Color.RED.getRed(), fontSize);
			g2.setFont(font);
			
			int sgement1 = 17;
			int sgement2 =34;
			
			List<String> list = new ArrayList<String>();
			if(title.length() > sgement2){
				title = title.substring(0, sgement2);
				list.add(title.substring(0,sgement1));
				list.add(title.substring(sgement1,sgement2));
			}else if(title.length() > sgement1){
				list.add(title.substring(0,sgement1));
				list.add(title.substring(sgement1,title.length()));
			}else{
				list.add(title);
			}
			
			int indexY = 30;
			for(String str:list){
				//标题
				g2.setPaint(Color.black);
				printString(g2, str, 5, indexY, fontSize);
				indexY = indexY+30;
			}
			
			//券
			g2.setPaint(Color.red);
			int x = printString(g2, quanPic, 15, 100, fontSize);
			
			//原价
			g2.setPaint(Color.black);
			font = new Font("STYLE_PLAIN", Color.RED.getRed(), fontSize);
			Map<AttributedCharacterIterator.Attribute, Object> map = new HashMap<AttributedCharacterIterator.Attribute, Object>();  
			map.put(TextAttribute.FONT, font);//原字体  
			map.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);//增加的属性  
			font = Font.getFont(map);
			
			
			g2.setFont(font);
			int x1 = printString(g2, oldPic, x-10, 100, fontSize);
          
			//现价
			g2.setPaint(Color.red);
			font = new Font("微软雅黑", Color.RED.getRed(), fontSize);
			g2.setFont(font);
			int x2 = printString(g2, oldPic, x+x1-20, 100, fontSize);
			
			g2.dispose();
			ImageIO.write(bi, "png", file);
			return file;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static int printString(Graphics2D g2d, String str, int x, int y,int fontSize) {
		//FontMetrics metrics = SwingUtilities2.getFontMetrics(null,g2d.getFont());
		g2d.drawString(str, x, y);
		/*for (char ca : str.toCharArray()) {
			int px = metrics.stringWidth("" + ca);
			g2d.drawString("" + ca, x + (fontSize - px) / 2, y);
			x += fontSize-2;
		}*/
		return fontSize*(str.toCharArray().length);
	}

	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new Date());
	}

	public static void main(String[] args) throws IOException {
		  File file1 = new File("d:\\dataoke", "1.jpg");  
		//TextToPic("中文生成图片", 500, 100, 50);
		TextToPicTaoKe("中文中文文中文中文中文", "￥12", "￥23", "[￥33券]", 270, 130,file1);
	}

}