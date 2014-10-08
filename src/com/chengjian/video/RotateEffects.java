package com.chengjian.video;

import java.io.File;


public class RotateEffects {
 
	public String outVideo;
    public static double seconds;
	// ��תЧ��
	public void RotateEffect(String picUrl, double seconds ,String outVideo) {
		this.outVideo = outVideo;
		this.seconds = seconds;
		try {
			ThreadA threadA = new ThreadA(picUrl);
			ThreadB threadB = new ThreadB(outVideo);
			ThreadC threadC = new ThreadC();

			threadA.start();

			while (!check(1)) {
				System.out.println("�ȴ�rotate_ts�������");
			}
			threadB.start();
			while (!check(2)) {
				System.out.println("�ȴ�Rotate_all_ts�������");
			}
			threadC.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean check(int type) {
		switch (type) {
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
							/ 2 + " -y -r "+ 3/seconds +" c:\\" + i + ".ts";

					Runtime.getRuntime().exec("cmd /c start " + command);

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// �ϲ�
	static class ThreadB extends Thread {
		String outVideo;

		public ThreadB(String outVideo) {
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
	static class ThreadC extends Thread {

		@Override
		public void run() {
			try {

				for (int j = 0; j <= 8; j++) {

					 
					Runtime.getRuntime().exec(
							"cmd /c del c:\\" + j + ".ts");

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
