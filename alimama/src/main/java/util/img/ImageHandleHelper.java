package util.img;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import zhuanzhuan.TbSpPage;

/**
 * @Description:图片处理工具
 * @author:liuyc
 * @time:2016年5月27日 上午10:18:00
 */
public class ImageHandleHelper {

	/**
	 * @Description:截图
	 * @author:liuyc
	 * @time:2016年5月27日 上午10:18:23
	 * @param srcFile源图片、targetFile截好后图片全名、startAcross 开始截取位置横坐标、StartEndlong开始截图位置纵坐标、width截取的长，hight截取的高
	 */
	public static void cutImage(String srcFile, String targetFile, int startAcross, int StartEndlong, int width,
			int hight) throws Exception {
		// 取得图片读入器
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		// 取得图片读入流
		InputStream source = new FileInputStream(srcFile);
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		// 图片参数对象
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(startAcross, StartEndlong, width, hight);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, targetFile.split("\\.")[1], new File(targetFile));
	}

	/**
	 * @Description:图片拼接 （注意：必须两张图片长宽一致哦）
	 * @author:liuyc
	 * @time:2016年5月27日 下午5:52:24
	 * @param files 要拼接的文件列表
	 * @param type1  横向拼接， 2 纵向拼接
	 */
	public static void mergeImage(String[] files, int type, String targetFile) {
		int len = files.length;
		if (len < 1) {
			throw new RuntimeException("图片数量小于1");
		}
		File[] src = new File[len];
		BufferedImage[] images = new BufferedImage[len];
		int[][] ImageArrays = new int[len][];
		for (int i = 0; i < len; i++) {
			try {
				src[i] = new File(files[i]);
				images[i] = ImageIO.read(src[i]);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			int width = images[i].getWidth();
			int height = images[i].getHeight();
			ImageArrays[i] = new int[width * height];
			ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);
		}
		int newHeight = 0;
		int newWidth = 0;
		for (int i = 0; i < images.length; i++) {
			// 横向
			if (type == 1) {
				newHeight = newHeight > images[i].getHeight() ? newHeight : images[i].getHeight();
				newWidth += images[i].getWidth();
			} else if (type == 2) {// 纵向
				newWidth = newWidth > images[i].getWidth() ? newWidth : images[i].getWidth();
				newHeight += images[i].getHeight();
			}
		}
		if (type == 1 && newWidth < 1) {
			return;
		}
		if (type == 2 && newHeight < 1) {
			return;
		}

		// 生成新图片
		try {
			BufferedImage ImageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			int height_i = 0;
			int width_i = 0;
			for (int i = 0; i < images.length; i++) {
				if (type == 1) {
					ImageNew.setRGB(width_i, 0, images[i].getWidth(), newHeight, ImageArrays[i], 0,
							images[i].getWidth());
					width_i += images[i].getWidth();
				} else if (type == 2) {
					ImageNew.setRGB(0, height_i, newWidth, images[i].getHeight(), ImageArrays[i], 0, newWidth);
					height_i += images[i].getHeight();
				}
			}
			//输出想要的图片
			ImageIO.write(ImageNew, targetFile.split("\\.")[1], new File(targetFile));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:小图片贴到大图片形成一张图(合成)
	 * @author:liuyc
	 * @time:2016年5月27日 下午5:51:20
	 */
	public static final void overlapImage(String bigPath, String smallPath, String outFile) {
		try {
			BufferedImage big = ImageIO.read(new File(bigPath));
			BufferedImage small = ImageIO.read(new File(smallPath));
			Graphics2D g = big.createGraphics();
			int x = (big.getWidth() - small.getWidth()) / 2;
			int y = (big.getHeight() - small.getHeight()) / 2;
			g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
			g.dispose();
			ImageIO.write(big, outFile.split("\\.")[1], new File(outFile));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	    public static void mergeImageX(File file1, File file2,File out) throws IOException {        
	        BufferedImage image1 = ImageIO.read(file1);  
	        BufferedImage image2 = ImageIO.read(file2);  
	  
	        BufferedImage combined = new BufferedImage(image1.getWidth() * 2, image1.getHeight(), BufferedImage.TYPE_INT_RGB);  
	  
	        // paint both images, preserving the alpha channels  
	        Graphics g = combined.getGraphics();  
	        g.drawImage(image1, 0, 0, null);  
	        g.drawImage(image2, image1.getWidth(), 0, null);  
	          
	        // Save as new image  
	        ImageIO.write(combined, "JPG", out);  
	    }  
	    
	    public static void mergeImageY(File file1, File file2,File out) throws IOException {        
	        BufferedImage image1 = ImageIO.read(file1);  
	        BufferedImage image2 = ImageIO.read(file2);  
	  
	        BufferedImage combined = new BufferedImage(image1.getWidth(), image1.getHeight() + image2.getHeight(), BufferedImage.TYPE_INT_RGB);  
	  
	        // paint both images, preserving the alpha channels  
	        Graphics g = combined.getGraphics();  
	        g.drawImage(image1, 0, 0, null);  
	        g.drawImage(image2, 0, image1.getHeight(), null);  
	          
	        // Save as new image  
	        ImageIO.write(combined, "JPG", out);  
	    }  
	    
	public static void main(String[] args)throws Exception {
		/*String targetFile = "d:\\dataoke\\2.jpg";
		String[] files={"d:\\111111.jpg","d:\\dataoke\\1.jpg"};
		mergeImage(files, 2, targetFile);*/
		
			
		
	        
	        System.out.println("开始生成左边商品描述图片>>>>>>>>>>>>>>>>>>>>>");
		     //左边图片
		    File file1 = new File("d:\\dataoke", "1.jpg");  
		    WordToPic.TextToPicTaoKe("雪纺长款连衣裙夏季女2017新款短袖气质收腰印花显瘦沙滩裙子长裙 ", "￥12.34", "￥23.32", "[￥33券]", 320, 130,file1);
		   
	        
		    System.out.println("开始生成推荐二位吗图片>>>>>>>>>>>>>>>>>>>>>");
		    //二维码图片
		    String textt = "https://s.click.taobao.com/3SgJ3iw";
		    File file2 = new File("d:\\dataoke", "2.jpg");  
			QRCodeUtil.encode(textt,"",file2,true,130);
			
			
			 System.out.println("开始合并商品描述图片和二维码图片>>>>>>>>>>>>>>>>>>>>>");
	        //拼接左边图片和二维码图片
	        File file3 = new File("d:\\dataoke", "3.jpg");
	        mergeImageX(file1,file2,file3);
	        
	        
	        //商品主图 寛450 高600
		     System.out.println("开始缩放商品主图>>>>>>>>>>>>>>>>>>>>>");
	        File taokeUpdate = new File("d:\\dataoke\\4.jpg");
	        ImageHelper.zoomImage("d:\\dataoke\\taoke.jpg",taokeUpdate.getAbsolutePath(), 450, 600);	
	        
	        
	        System.out.println("开始合并商品描述图片和二维码图片和商品主图>>>>>>>>>>>>>>>>>>>>>");
	        File all = new File("d:\\dataoke", "5.jpg");
	        mergeImageY(taokeUpdate, file3, all);
	        
	        System.out.println("合并完毕");
	        
	        
	        
	}
	public static void executeTest()throws Exception{
		   System.out.println("开始生成左边商品描述图片>>>>>>>>>>>>>>>>>>>>>");
		     //左边图片
		    File file1 = new File("d:\\dataoke", "1.jpg");  
		    WordToPic.TextToPicTaoKe("【2条装】安卓通用加长充电数据线 ", "原价:￥12", "现价:￥23", "[￥33券]", 320, 130,file1);
		   
	        
		    System.out.println("开始生成推荐二位吗图片>>>>>>>>>>>>>>>>>>>>>");
		    //二维码图片
		    String textt = "https://s.click.taobao.com/3SgJ3iw";
		    File file2 = new File("d:\\dataoke", "2.jpg");  
			QRCodeUtil.encode(textt,"",file2,true,130);
			
			
			 System.out.println("开始合并商品描述图片和二维码图片>>>>>>>>>>>>>>>>>>>>>");
	        //拼接左边图片和二维码图片
	        File file3 = new File("d:\\dataoke", "3.jpg");
	        mergeImageX(file1,file2,file3);
	        
	        
	        //商品主图 寛450 高600
		     System.out.println("开始缩放商品主图>>>>>>>>>>>>>>>>>>>>>");
	        File taokeUpdate = new File("d:\\dataoke\\4.jpg");
	        ImageHelper.zoomImage("d:\\dataoke\\taoke.jpg",taokeUpdate.getAbsolutePath(), 450, 600);	
	        
	        
	        System.out.println("开始合并商品描述图片和二维码图片和商品主图>>>>>>>>>>>>>>>>>>>>>");
	        File all = new File("d:\\dataoke", "5.jpg");
	        mergeImageY(taokeUpdate, file3, all);
	        
	        System.out.println("合并完毕");
	}
	
	
	public static void execute(TbSpPage page,File out)throws Exception{
		   System.out.println("开始生成左边商品描述图片>>>>>>>>>>>>>>>>>>>>>");
		     //左边图片
		    File file1 = new File("d:\\dataoke", "1.jpg");  
		    WordToPic.TextToPicTaoKe(page.getTbGoodsTitle(), "￥"+page.getTaoBaopOldrice(), "￥"+page.getTaoBaoprice(), "[￥"+page.getQuanPrice()+"券]", 320, 130,file1);
		   
	        
		    System.out.println("开始生成推荐二位吗图片>>>>>>>>>>>>>>>>>>>>>");
		    //二维码图片
		    String textt = page.getZhuanQQUrl();
		    File file2 = new File("d:\\dataoke", "2.jpg");  
			QRCodeUtil.encode(textt,"",file2,true,130);
			
			
			 System.out.println("开始合并商品描述图片和二维码图片>>>>>>>>>>>>>>>>>>>>>");
	        //拼接左边图片和二维码图片
	        File file3 = new File("d:\\dataoke", "3.jpg");
	        mergeImageX(file1,file2,file3);
	        
	        
	        //商品主图 寛450 高600
		     System.out.println("开始缩放商品主图>>>>>>>>>>>>>>>>>>>>>");
	        File taokeUpdate = new File("d:\\dataoke\\4.jpg");
	        File taokeUpdateSrc = page.getTbGoodsImgFiles().get(0);
	        ImageHelper.zoomImage(taokeUpdateSrc.getAbsolutePath(),taokeUpdate.getAbsolutePath(), 450, 600);	
	        
	        
	        System.out.println("开始合并商品描述图片和二维码图片和商品主图>>>>>>>>>>>>>>>>>>>>>");
	        //File all = new File("d:\\dataoke", "5.jpg");
	        mergeImageY(taokeUpdate, file3, out);
	        
	        System.out.println("合并完毕");
	}
	
}