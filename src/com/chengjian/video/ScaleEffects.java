package com.chengjian.video;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import com.chengjian.video.CopyOfRotateEffects.ThreadA;
import com.chengjian.video.CopyOfRotateEffects.ThreadB;
import com.chengjian.video.CopyOfRotateEffects.ThreadC;
import com.chengjian.video.CopyOfRotateEffects.ThreadD;

public class ScaleEffects {
	public static BufferedImage resizeImage(final BufferedImage bufferedimage,
            final int w, final int h) {
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.drawImage(bufferedimage, 0, 0, w, h, 0, 0, bufferedimage
                .getWidth(), bufferedimage.getHeight(), null);
        graphics2d.dispose();
        return img;
    }
//	public String outVideo;
//	public static double seconds;
//
//	// ����Ч��
//	public void ScaleEffects(String picUrl, double seconds, String outVideo) {
//		this.outVideo = outVideo;
//		this.seconds = seconds;
//		/*try {
//			ThreadA threadA = new ThreadA(picUrl);
//			ThreadB threadB = new ThreadB(outVideo);
//			ThreadC threadC = new ThreadC();
//
//			threadA.start();
//			while (!check(1)) {
//				System.out.println("�ȴ�ts�������");
//			}
//			threadB.start();
//			while (!check(2)) {
//				System.out.println("�ȴ�outvideo�������");
//			}
//			// threadC.start();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	public boolean check(int type) {
//		switch (type) {
//		
//		case 1:
//			for (int i = 0; i <= 9; i++) {
//				File file = new File("c:\\" + i + ".ts");
//				if (!file.exists())
//					return false;
//				continue;
//			}
//			return true;
//		case 2:
//			File file = new File(outVideo);
//			if (file.exists())
//				return true;
//			return false;
//
//		}
//		return false;
//
//	}
//
//	// classA:����Mp4��Ƶ
//	// classB����ts��
//	// classC�ϲ���mp4��Ƶ
//	// classDɾ��mp4��ts��Ƶ
//	static class ThreadA extends Thread {
//		String picUrl;
//
//		public ThreadA(String picUrl) {
//			this.picUrl = picUrl;
//		}
//
//		@Override
//		public void run() {
//			try {
//				// -vf pad='3/2*iw:3/2*ih:(ow-iw)/2:(oh-ih)/2'
//				for (int i = 0; i <= 9; i++) {
//
//					String command = "ffmpeg -i " + picUrl + " -vf pad=\""
//							+ (i + 2) + "/2*iw:" + (i + 2)
//							+ "/2*ih:(ow-iw)/2 : (oh-ih)/2\" "
//							+ " -y -r "+ 2.5/seconds +" c:\\" + i + ".ts";
//
//					Runtime.getRuntime().exec("cmd /c start " + command);
//
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//	}
//
// 
//	// �ϲ�
//	static class ThreadB extends Thread {
//		String outVideo;
//
//		public ThreadB(String outVideo) {
//			this.outVideo = outVideo;
//		}
//
//		@Override
//		public void run() {
//			try {
//
//				String command = "ffmpeg -i \"concat:";
//				for (int j = 0; j < 9; j++) {
//					command = command + "c:\\" + j + ".ts|";
//				}
//				command = command + "c:\\9.ts\" " + outVideo;
//				Runtime.getRuntime().exec("cmd /c start " + command);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//	}
//
//	// �h��
//	static class ThreadC extends Thread {
//
//		@Override
//		public void run() {
//			try {
//
//				for (int j = 0; j <= 9; j++) {
//
//					 
//					Runtime.getRuntime().exec(
//							"cmd /c del c:\\" + j + ".ts");
//
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//	}
//}*/
//		try {
//			ThreadA threadA = new ThreadA(picUrl);
//			ThreadB threadB = new ThreadB(picUrl);
//			ThreadC threadC = new ThreadC(outVideo);
//			ThreadD threadD = new ThreadD();
//			threadA.start();
//			while(!check(0)){
//				System.out.println("�ȴ�mp4�������");
//			}
//			threadB.start();
//			while(!check(1)){
//				System.out.println("�ȴ�ts�������");
//			}
//			threadC.start();
//			while(!check(2)){
//				System.out.println("�ȴ�outvideo�������");
//			}
//			threadD.start();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	public boolean check(int type) {
//		switch (type) {
//		case 0:
//			for (int i = 0; i <= 8; i++) {
//				File file = new File("c:\\" + i + ".mp4");
//				if (!file.exists())
//					return false;
//				continue;
//			}
//			return true;
//		case 1:
//			for (int i = 0; i <= 8; i++) {
//				File file = new File("c:\\" + i + ".ts");
//				if (!file.exists())
//					return false;
//				continue;
//			}
//			return true;
//		case 2:
//			File file = new File(outVideo);
//			if (file.exists()) 
//				return true;
//			return false;
//			
//		}
//		return false;
//		
//			
//	}
//
//	// classA:����Mp4��Ƶ
//	// classB����ts��
//	// classC�ϲ���mp4��Ƶ
//	// classDɾ��mp4��ts��Ƶ
//	static class ThreadA extends Thread {
//		String picUrl;
//
//		public ThreadA(String picUrl) {
//			this.picUrl = picUrl;
//		}
//
//		@Override
//		public void run() {
//			try {
//				// -vf pad='3/2*iw:3/2*ih:(ow-iw)/2:(oh-ih)/2'
//				for (int i = 0; i <= 9; i++) {
//
//					String command = "ffmpeg -i " + picUrl + " -vf pad=\""
//							+ (i + 2) + "/2*iw:" + (i + 2)
//							+ "/2*ih:(ow-iw)/2 : (oh-ih)/2\" "
//							+ " -y -r "+ 4/seconds +" c:\\" + i + ".mp4";
//
//					Runtime.getRuntime().exec("cmd /c start " + command);
//
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//	}
//
//	// �D�a
//	static class ThreadB extends Thread {
//		String picUrl;
//
//		public ThreadB(String picUrl) {
//			this.picUrl = picUrl;
//		}
//
//		@Override
//		public void run() {
//			try {
//				for (int j = 0; j <= 9; j++) {
//
//					Runtime.getRuntime()
//							.exec("cmd /c start ffmpeg -i c:\\"
//									+ j
//									+ ".mp4 -vcodec copy -acodec copy -vbsf h264_mp4toannexb -y c:\\"
//									+ j + ".ts");
//
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//			// A.release();
//		}
//	}
//
//	// �ϲ�
//	static class ThreadC extends Thread {
//		String outVideo;
//
//		public ThreadC(String outVideo) {
//			this.outVideo = outVideo;
//		}
//
//		@Override
//		public void run() {
//			try {
//
//				String command = "ffmpeg -i \"concat:";
//				for (int j = 0; j <= 8; j++) {
//					command = command + "c:\\" + j + ".ts|";
//				}
//				command = command + "c:\\9.ts\" " + outVideo;
//				Runtime.getRuntime().exec("cmd /c start " + command);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//	}
//
//	// �h��
//	static class ThreadD extends Thread {
//
//		@Override
//		public void run() {
//			try {
//
//				for (int j = 0; j <= 9; j++) {
//
//					Runtime.getRuntime().exec(
//							"cmd /c  del c:\\" + j + ".mp4");
//					Runtime.getRuntime()
//							.exec("cmd /c  del c:\\" + j + ".ts");
//
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//	}
}

