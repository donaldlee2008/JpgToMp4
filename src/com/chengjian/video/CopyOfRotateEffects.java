package com.chengjian.video;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class CopyOfRotateEffects {
	// ʹ���ź���
	public static Semaphore A = new Semaphore(1);
	public static Semaphore B = new Semaphore(1);
	public static Semaphore C = new Semaphore(1);
	public static Semaphore D = new Semaphore(1);
	public String outVideo;

	// ��תЧ��
	public void RotateEffect(String picUrl, String outVideo) {
this.outVideo = outVideo;
		try {
			ThreadA threadA = new ThreadA(picUrl);
			ThreadB threadB = new ThreadB(picUrl);
			ThreadC threadC = new ThreadC(outVideo);
			ThreadD threadD = new ThreadD();
			threadA.start();
			while(!check(0)){
				System.out.println("�ȴ�mp4�������");
			}
			threadB.start();
			while(!check(1)){
				System.out.println("�ȴ�ts�������");
			}
			threadC.start();
			while(!check(2)){
				System.out.println("�ȴ�outvideo�������");
			}
			//threadD.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean check(int type) {
		switch (type) {
		case 0:
			for (int i = 0; i <= 8; i++) {
				File file = new File("c:\\" + i + ".mp4");
				if (!file.exists())
					return false;
				continue;
			}
			return true;
		case 1:
			for (int i = 0; i <= 8; i++) {
				File file = new File("c:\\" + i + ".ts");
				if (!file.exists())
					return false;
				continue;
			}
			return true;
		case 2:
			File file = new File(outVideo);
			if (file.exists()) 
				return true;
			return false;
			
		}
		return false;
		
			
	}

	// classA:����Mp4��Ƶ
	// classB����ts��
	// classC�ϲ���mp4��Ƶ
	// classDɾ��mp4��ts��Ƶ
	static class ThreadA extends Thread {
		String picUrl;

		public ThreadA(String picUrl) {
			this.picUrl = picUrl;
		}

		@Override
		public void run() {
			try {

				for (int i = 0; i <= 8; i++) {

					String command = "ffmpeg -i " + picUrl + " -vf rotate=" + i
							/ 2 + " -y -r 2 c:\\" + i + ".mp4";

					Runtime.getRuntime().exec("cmd /c start " + command);

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// �D�a
	static class ThreadB extends Thread {
		String picUrl;

		public ThreadB(String picUrl) {
			this.picUrl = picUrl;
		}

		@Override
		public void run() {
			try {
				for (int j = 0; j <= 8; j++) {

					Runtime.getRuntime()
							.exec("cmd /c start ffmpeg -i c:\\"
									+ j
									+ ".mp4 -vcodec copy -acodec copy -vbsf h264_mp4toannexb -y c:\\"
									+ j + ".ts");

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			// A.release();
		}
	}

	// �ϲ�
	static class ThreadC extends Thread {
		String outVideo;

		public ThreadC(String outVideo) {
			this.outVideo = outVideo;
		}

		@Override
		public void run() {
			try {

				String command = "ffmpeg -i \"concat:";
				for (int j = 0; j < 8; j++) {
					command = command + "c:\\" + j + ".ts|";
				}
				command = command + "c:\\8.ts\" " + outVideo;
				Runtime.getRuntime().exec("cmd /c start " + command);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// �h��
	static class ThreadD extends Thread {

		@Override
		public void run() {
			try {

				for (int j = 0; j <= 8; j++) {

					Runtime.getRuntime().exec(
							"cmd /c start del c:\\" + j + ".mp4");
					Runtime.getRuntime()
							.exec("cmd /c start del c:\\" + j + ".ts");

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
