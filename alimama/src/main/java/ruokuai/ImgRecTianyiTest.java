package ruokuai;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;


public class ImgRecTianyiTest{  
	private static  String DLLPATH=null;
	private static  String NETPATH=null;
	private static int netIndex=0;
	static{
		DLLPATH = "c:\\sunday.dll";
		System.out.println(DLLPATH);
//		NETPATH = "D:/test/xsd.lib";
		NETPATH = "c:\\weibo.lib";
		netIndex = JPYZM.INSTANCE.LoadLibFromFile(NETPATH,"123");
		System.out.println("netIndex:"+netIndex);
	}
	public static void init(){
	}
	public interface JPYZM extends StdCallLibrary{
		JPYZM INSTANCE = (JPYZM) Native.loadLibrary(DLLPATH, JPYZM.class);  
		int LoadLibFromFile(String path,String pwd);
		boolean GetCodeFromBuffer(int index,byte[] img,int len,byte[] code);
	}
	
	 public static byte[] getContent(String filePath) throws IOException {  
	        File file = new File(filePath);  
	        long fileSize = file.length();  
	        if (fileSize > Integer.MAX_VALUE) {  
	            System.out.println("file too big...");  
	            return null;  
	        }  
	        FileInputStream fi = new FileInputStream(file);  
	        byte[] buffer = new byte[(int) fileSize];  
	        int offset = 0;  
	        int numRead = 0;  
	        while (offset < buffer.length  
	        && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {  
	            offset += numRead;  
	        }  
	        // 确保所有数据均被读取  
	        if (offset != buffer.length) {  
	            throw new IOException("Could not completely read file "  
	                    + file.getName());  
	        }  
	        fi.close();  
	        return buffer;  
	    }  
	
	public static void main(String[] args) throws IOException {
		byte[] bs = getContent("e:\\img\\1.jpg");
		
		System.out.println(getCode(bs));
//		String s="222";
//		test(s);
//		System.out.println(s);
	}
	
	public static void test(String s){
		s="11";
	}
   
    
    public static String getCode(byte[] imgbs) throws IOException {  
    	System.out.println("imgbs.length:"+imgbs.length);
		long begin = System.currentTimeMillis();
//		System.out.println(netIndex);
		byte[] code = new byte[4];  
		String rtnCode = null;
//		boolean result = JPYZM.INSTANCE.GetVcodeFromFile(netIndex,"D:/img/qq/wy.JPG",code);
		boolean result = JPYZM.INSTANCE.GetCodeFromBuffer(netIndex,imgbs,imgbs.length,code);
		System.out.println(result);
		if(result){
			long end = System.currentTimeMillis();
			rtnCode = new String(code);
			System.out.println("识别时间："+(end-begin)+"ms 识别结果:"+rtnCode);
		}
		return rtnCode;
	}
}
