package com.chengjian.video;

import java.awt.Dialog;
import java.io.File;
import java.io.IOException;

public class JpgToMp4 {

	/**
	 * @param args
	 */
public final static int ROTATE = 0;
public final static int NOISE = 1;
public final static int SCALE = 2;
public final static int BLUR = 3;


	public static void main(String[] args) {
	 //���δ��ɾ͵ȴ�
		if(!jpg2Video(new String[]{"http://g.hiphotos.bdimg.com/album/w%3D2048/sign=79f79f251b4c510faec4e51a5461272d/d1a20cf431adcbef88ba3febadaf2edda2cc9f55.jpg",
				"http://g.hiphotos.bdimg.com/album/w%3D2048/sign=5f9c0f75f703738dde4a0b228723b251/a8ec8a13632762d0119ed46aa1ec08fa503dc647.jpg",
				"http://g.hiphotos.bdimg.com/album/w%3D2048/sign=083d96ecb7003af34dbadb600112c45c/7aec54e736d12f2e87d6d8854ec2d56284356807.jpg",
				"http://qq.z66w.cn/fgii/allimg/c130216/1360951049C0-3E07.jpg"},new int[]{1,1,1,1},"c:\\music\\Apologize.mp3","c:\\final.mp4")){
			System.out.println("jpg2Video is in processing!");
		}
		
		/*NoneEffects noneEffects = new NoneEffects();
		noneEffects.noneEffects(new String[]{"http://cdn2.haibao.cn/store/wm/bigfiles/200735/887DD7B5EFCB99B486F6D5C24EA685.jpg",
				"http://g.hiphotos.bdimg.com/album/w%3D2048/sign=5f9c0f75f703738dde4a0b228723b251/a8ec8a13632762d0119ed46aa1ec08fa503dc647.jpg",
				"http://g.hiphotos.bdimg.com/album/w%3D2048/sign=083d96ecb7003af34dbadb600112c45c/7aec54e736d12f2e87d6d8854ec2d56284356807.jpg",
				"http://img3.pclady.com.cn/pclady/0803/06/258415_20071017102323_12335.jpg"},"c:\\z.mp3", "c:\\test.mp4",1);*/
		//��ɺ�Ĳ���
		System.out.println("completed");
	}
	
	public static boolean check(String outVideo) {
		 
			File file = new File(outVideo);
			if (file.exists())
				return true;
			return false;
 
	}
	
	
	 
	public static boolean check(String[] outNormals,String[] outAnims) {
		 
		 int length = outAnims.length;
		 for (int i = 0; i < length; i++) {
			File file_A = new File(outAnims[i]);
			File file_B = new File(outNormals[i]);
			if(file_A.exists() && file_B.exists())
				continue;
			return false;
		}
		 return true;

}
	 
	/**
	  * @param picUrls_
	  * ͼƬ��ַ����
	  * @param animations
	  * ͼƬ�������� 
	  * @param animTime
	  * ͼƬ����ʱ������ 
	  * @param musicUrl
	  * ���ֵ�ַ
	  * @param outVideo
	  * �����Ƶ��ַ
	  */	 
	 public static boolean jpg2Video(String picUrls_[],int animations[],double animTime[],String musicUrl,String outVideo){
		 String picUrls[] = new String[4];
		 int length_ = picUrls_.length;
		 int length = 4;
		 if(length_ == 1){
			 String temp = DownloadPic.download(picUrls_[0]);
			 for(int i = 0; i < 4; i++){
				 picUrls[i] = temp;
			 }
		 }else{
			 for(int i = 0; i < 4; i++){
				 picUrls[i] = DownloadPic.download(picUrls_[i]);
			 }
		 }
		 String outAnims[] = new String[length];
		 String outNormal[] = new String[length];
		 for(int i = 0; i < length; i++){
				int flag = i;
				outNormal[flag] = "c:\\normal" +flag + ".ts";
				NormalEffects normalEffects = new NormalEffects();
				normalEffects.createNormalVideo(picUrls[flag],0.5, "c:\\normal" +flag + ".ts");
				while (!check("c:\\normal" +flag + ".ts")) {
					System.out.println("�ȴ�"+ flag+1 + "��normal ts���");
				}
				switch (animations[flag]) {
				  
				case ROTATE:
					RotateEffects rotateEffect = new RotateEffects();
					rotateEffect.RotateEffect(picUrls[flag],animTime[i], "c:\\"+ flag +"_rotate.ts");
					outAnims[flag] = "c:\\"+ flag +"_rotate.ts";
					break;
				case NOISE:
					NoiseEffects noiseEffect = new NoiseEffects();
					noiseEffect.NoiseEffect(picUrls[flag],animTime[i], "c:\\"+ flag +"_noise.ts");
					outAnims[flag] = "c:\\"+ flag +"_noise.ts";
					break;
				case SCALE:
					ScaleEffects scaleEffects = new ScaleEffects();
					scaleEffects.ScaleEffects(picUrls[flag],animTime[i], "c:\\"+ flag +"_scale.ts");
					outAnims[flag] = "c:\\"+ flag +"_scale.ts";
					break;
				case BLUR:
					BlurEffects blurEffects = new BlurEffects();
					blurEffects.BlurEffects(picUrls[flag],animTime[i], "c:\\"+ flag +"_blur.ts");
					outAnims[flag] = "c:\\"+ flag +"_blur.ts";
					break;
				}
		}
		 
		 //��Ƭ�κϲ�
		 while (!check(outNormal, outAnims)) {
			System.out.println("�ȴ�����Ƭ����ɡ�����");
		}
		 
		 //�ϳ�Ƭ��
		 String command = "ffmpeg -i \"concat:";
		 for (int i = 0; i < length-1; i++) {
			command = command + outNormal[i] + "|" + outAnims[i] + "|";
		}
		 command = command + outNormal[length - 1] + "|" + outAnims[length - 1] + "\" c:\\temp_video.mp4";	 	 
		 //ִ��
		 try {
			Runtime.getRuntime().exec("cmd /c start " + command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		 //�ж��Ƿ��Ѿ��ϳ�
		  while(!check("c:\\temp_video.mp4")){
			  System.out.println("�ȴ���Ƶ�ĺϳ�");
		  }
		  
		  //��Ƭ��ɾ��
		  for (int i = 0; i < length; i++) {
			try {
				Runtime.getRuntime().exec("cmd /c del " + outNormal[i]);
				Runtime.getRuntime().exec("cmd /c del " + outAnims[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		  
		  //���ϱ�������
		  String commandWithMusic = "ffmpeg -i c:\\temp_video.mp4 -i " + musicUrl + " "+ outVideo;
		  try {
			Runtime.getRuntime().exec("cmd /c start " + commandWithMusic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		  while(!check(outVideo)){
			  System.out.println("�ȴ������Ƶ�ĺϳ�");
		  }
		  
		  
		  //�ϳɺ�ɾ���м�video��picture
		  try {
			Runtime.getRuntime().exec("cmd /c rd /q/s c:\\jpg");
			Runtime.getRuntime().exec("cmd /c del c:\\temp_video.mp4");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		  System.out.println("�������");
		  return true;
		  
	 }
	 
	 /**
	  * @param picUrls_
	  * ͼƬ��ַ����
	  * @param musicUrl
	  * ���ֵ�ַ
	  * @param outVideo
	  * �����Ƶ��ַ
	  * @param ȱʡ���֣�
	  * ͼƬ����ȱʡ���飺{0,1,2,3}; ͼƬȱʡ����ʱ������:{1,1,1,1}
	  * 
	  */
	 public static boolean jpg2Video(String picUrls_[],String musicUrl,String outVideo){
		
		 double default_animTime[] = new double[]{1,1,1,1};
		 String picUrls[] = new String[4];
		 int length_ = picUrls_.length;
		 int length = 4;
		 if(length_ == 1){
			 String temp = DownloadPic.download(picUrls_[0]);
			 for(int i = 0; i < 4; i++){
				 picUrls[i] = temp;
			 }
		 }else{
			 for(int i = 0; i < 4; i++){
				 picUrls[i] = DownloadPic.download(picUrls_[i]);
			 }
		 }
		 int animations[] = new int[]{0,1,2,3};
		 String outAnims[] = new String[length];
		 String outNormal[] = new String[length];
		 for(int i = 0; i < length; i++){
				int flag = i;
				outNormal[flag] = "c:\\normal" +flag + ".ts";
				NormalEffects normalEffects = new NormalEffects();
				normalEffects.createNormalVideo(picUrls[flag],0.5, "c:\\normal" +flag + ".ts");
				while (!check("c:\\normal" +flag + ".ts")) {
					System.out.println("�ȴ�"+ flag+1 + "��normal ts���");
				}
				switch (animations[flag]) {
				  
				case ROTATE:
					RotateEffects rotateEffect = new RotateEffects();
					rotateEffect.RotateEffect(picUrls[flag],default_animTime[i], "c:\\"+ flag +"_rotate.ts");
					outAnims[flag] = "c:\\"+ flag +"_rotate.ts";
					break;
				case NOISE:
					NoiseEffects noiseEffect = new NoiseEffects();
					noiseEffect.NoiseEffect(picUrls[flag],default_animTime[i], "c:\\"+ flag +"_noise.ts");
					outAnims[flag] = "c:\\"+ flag +"_noise.ts";
					break;
				case SCALE:
					ScaleEffects scaleEffects = new ScaleEffects();
					scaleEffects.ScaleEffects(picUrls[flag],default_animTime[i], "c:\\"+ flag +"_scale.ts");
					outAnims[flag] = "c:\\"+ flag +"_scale.ts";
					break;
				case BLUR:
					BlurEffects blurEffects = new BlurEffects();
					blurEffects.BlurEffects(picUrls[flag],default_animTime[i], "c:\\"+ flag +"_blur.ts");
					outAnims[flag] = "c:\\"+ flag +"_blur.ts";
					break;
				}
		}
		 
		 //��Ƭ�κϲ�
		 while (!check(outNormal, outAnims)) {
			System.out.println("�ȴ�����Ƭ����ɡ�����");
		}
		 
		 //�ϳ�Ƭ��
		 String command = "ffmpeg -i \"concat:";
		 for (int i = 0; i < length-1; i++) {
			command = command + outNormal[i] + "|" + outAnims[i] + "|";
		}
		 command = command + outNormal[length - 1] + "|" + outAnims[length - 1] + "\" c:\\temp_video.mp4";	 	 
		 //ִ��
		 try {
			Runtime.getRuntime().exec("cmd /c start " + command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		 //�ж��Ƿ��Ѿ��ϳ�
		  while(!check("c:\\temp_video.mp4")){
			  System.out.println("�ȴ��м���Ƶ�ĺϳ�");
		  }
		  
		  //��Ƭ��ɾ��
		  for (int i = 0; i < length; i++) {
			try {
				Runtime.getRuntime().exec("cmd /c del " + outNormal[i]);
				Runtime.getRuntime().exec("cmd /c del " + outAnims[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		  
		  //���ϱ�������
		  String commandWithMusic = "ffmpeg -i c:\\temp_video.mp4 -i " + musicUrl + " "+ outVideo;
		  try {
			Runtime.getRuntime().exec("cmd /c start " + commandWithMusic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		  while(!check(outVideo)){
			  System.out.println("�ȴ������Ƶ�ĺϳ�");
		  }
		  
		  
		  //�ϳɺ�ɾ���м�video��picture
		  try {
			Runtime.getRuntime().exec("cmd /c rd /q/s c:\\jpg");
			Runtime.getRuntime().exec("cmd /c del c:\\temp_video.mp4");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		  System.out.println("�������");
		  return true;
	 }
	
	 /**
	  * @param picUrls_
	  * ͼƬ��ַ����
	  * @param animations
	  * ͼƬ�������� 
	  * @param musicUrl
	  * ���ֵ�ַ
	  * @param outVideo
	  * �����Ƶ��ַ
	  * @param ȱʡ����
	  * ȱʡ�Ĺ���ʱ������Ϊ{1,1,1,1}
	  */	 
	 public static boolean jpg2Video(String picUrls_[],int animations[],String musicUrl,String outVideo){
		 double default_animTime[] = new double[]{1,1,1,1};
		 String picUrls[] = new String[4];
		 int length_ = picUrls_.length;
		 int length = 4;
		 if(length_ == 1){
			 String temp = DownloadPic.download(picUrls_[0]);
			 for(int i = 0; i < 4; i++){
				 picUrls[i] = temp;
			 }
		 }else{
			 for(int i = 0; i < 4; i++){
				 picUrls[i] = DownloadPic.download(picUrls_[i]);
			 }
		 }
		 String outAnims[] = new String[length];
		 String outNormal[] = new String[length];
		 for(int i = 0; i < length; i++){
				int flag = i;
				outNormal[flag] = "c:\\normal" +flag + ".ts";
				NormalEffects normalEffects = new NormalEffects();
				normalEffects.createNormalVideo(picUrls[flag],0.5, "c:\\normal" +flag + ".ts");
				while (!check("c:\\normal" +flag + ".ts")) {
					System.out.println("�ȴ�"+ flag+1 + "��normal ts���");
				}
				switch (animations[flag]) {
				  
				case ROTATE:
					RotateEffects rotateEffect = new RotateEffects();
					rotateEffect.RotateEffect(picUrls[flag],default_animTime[i], "c:\\"+ flag +"_rotate.ts");
					outAnims[flag] = "c:\\"+ flag +"_rotate.ts";
					break;
				case NOISE:
					NoiseEffects noiseEffect = new NoiseEffects();
					noiseEffect.NoiseEffect(picUrls[flag],default_animTime[i], "c:\\"+ flag +"_noise.ts");
					outAnims[flag] = "c:\\"+ flag +"_noise.ts";
					break;
				case SCALE:
					ScaleEffects scaleEffects = new ScaleEffects();
					scaleEffects.ScaleEffects(picUrls[flag],default_animTime[i], "c:\\"+ flag +"_scale.ts");
					outAnims[flag] = "c:\\"+ flag +"_scale.ts";
					break;
				case BLUR:
					BlurEffects blurEffects = new BlurEffects();
					blurEffects.BlurEffects(picUrls[flag],default_animTime[i], "c:\\"+ flag +"_blur.ts");
					outAnims[flag] = "c:\\"+ flag +"_blur.ts";
					break;
				}
		}
		 
		 //��Ƭ�κϲ�
		 while (!check(outNormal, outAnims)) {
			System.out.println("�ȴ�����Ƭ����ɡ�����");
		}
		 
		 //�ϳ�Ƭ��
		 String command = "ffmpeg -i \"concat:";
		 for (int i = 0; i < length-1; i++) {
			command = command + outNormal[i] + "|" + outAnims[i] + "|";
		}
		 command = command + outNormal[length - 1] + "|" + outAnims[length - 1] + "\" c:\\temp_video.mp4";	 	 
		 //ִ��
		 try {
			Runtime.getRuntime().exec("cmd /c start " + command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		 //�ж��Ƿ��Ѿ��ϳ�
		  while(!check("c:\\temp_video.mp4")){
			  System.out.println("�ȴ���Ƶ�ĺϳ�");
		  }
		  
		  //��Ƭ��ɾ��
		  for (int i = 0; i < length; i++) {
			try {
				Runtime.getRuntime().exec("cmd /c del " + outNormal[i]);
				Runtime.getRuntime().exec("cmd /c del " + outAnims[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		  
		  //���ϱ�������
		  String commandWithMusic = "ffmpeg -i c:\\temp_video.mp4 -i " + musicUrl + " "+ outVideo;
		  try {
			Runtime.getRuntime().exec("cmd /c start " + commandWithMusic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		  while(!check(outVideo)){
			  System.out.println("�ȴ������Ƶ�ĺϳ�");
		  }
		  
		  
		  //�ϳɺ�ɾ���м�video��picture
		  try {
			Runtime.getRuntime().exec("cmd /c  rd /q/s c:\\jpg");
			Runtime.getRuntime().exec("cmd /c del c:\\temp_video.mp4");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		  System.out.println("�������");
		  return true;
	 }
	 
	 
	
	
}
