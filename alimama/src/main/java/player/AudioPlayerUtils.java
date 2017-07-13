package player;

import java.io.File;

public class AudioPlayerUtils {
	
	 AudioPlayer audioPlayer =new AudioPlayer(new File("c:\\mp3.mp3"));
	
	 public  void startMp3(){
		 System.out.println("开始播放音乐>>>>>>>>>>>>>>>>>>>>");
		 audioPlayer.start();
	 }
	 
	 
	 public  void stopMp3(){
		 System.out.println("停止播放音乐>>>>>>>>>>>>>>>>>>>>");
	    	audioPlayer.stopPlay();
	 }
	 
	 
	 public static void main(String[] args) throws Exception{
		AudioPlayerUtils audioPlayerUtils = new AudioPlayerUtils();
	    audioPlayerUtils.startMp3();
	    Thread.sleep(5000);
		audioPlayerUtils.stopMp3();
		
		
	
	}

}
