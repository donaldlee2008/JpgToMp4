package com.chengjian.video;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FrameCreator {
	public static BufferedImage images[];

	public static void CreateFrames(int mp4Size, int effects[],
			String picUrls_[]) {
		// String outputPath="";
		String jpgPath[] = new String[4];
		int length_ = picUrls_.length;
		// int picCount = 4;
		if (length_ == 1) {
			String temp = DownloadPic.download(picUrls_[0]);
			for (int i = 0; i < 4; i++) {
				jpgPath[i] = temp;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				jpgPath[i] = DownloadPic.download(picUrls_[i]);
			}
		}
		// ��ͼƬ���뵽�ڴ��з���������Ĳ���
		images = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			try {
				File imgFile = new File(jpgPath[i]);
				images[i] = ImageIO.read(imgFile);
			} catch (IOException e) {
				System.out.println("image missing");
			}
		}
		// ����Ч�����н���֡����
		if (mp4Size == 0)// �����Ҫ��ʱ��6����
		{
			// �����ɾ�̬֡
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 9; k++) {
					// inputbig, "jpg", new
					// File("C:/imageSort/targetPIC/"+name));
					try {
						ImageIO.write(
								images[j],
								"jpg",
								new File(jpgPath[j].substring(0,
										jpgPath[j].lastIndexOf("."))
										+ "_" + k + ".jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			for (int j = 0; j < 3; j++)// ����Ч���������ɱ任֡			{
				switch (effects[j]) {
				case 0:// ��תЧ��
					for (int k = 0; k < 12; k++) {
						// ImageIO.write(images[j],"jpg",new
						// File(jpgPath[j].substring(0,jpgPath[j].lastIndexOf("."))+"_"+k+".jpg"));
						try {
							int temp=9+21*j+k;
							ImageIO.write(
									RotateEffects.rotateImage(images[j], 30*(k+1)),
									"jpg",
									new File(jpgPath[j].substring(0,
											jpgPath[j].lastIndexOf("."))
											+ "_" + temp + ".jpg"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				case 1://�s��Ч��
					for (int k = 0; k < 12; k++) {
						// ImageIO.write(images[j],"jpg",new
						// File(jpgPath[j].substring(0,jpgPath[j].lastIndexOf("."))+"_"+k+".jpg"));
						try {
							int temp=9+21*j+k;
							ImageIO.write(
									ScaleEffects.resizeImage(images[j], (int)(images[j].getWidth()*(0.917f-0.083f*k)),(int)(images[j].getHeight()*(0.917f-0.083f*k))),
									"jpg",
									new File(jpgPath[j].substring(0,
											jpgPath[j].lastIndexOf("."))
											+ "_" + temp + ".jpg"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;

				default:
					break;
				}
			}

		} 
		else// ���Ҫ��ʱ����9����
		{
			for (int j = 0; j < 4; j++)// ���ɾ�̬֡
			{
				for (int k = 0; k < 9; k++) {
					// inputbig, "jpg", new
					// File("C:/imageSort/targetPIC/"+name));
					try {
						ImageIO.write(
								images[j],
								"jpg",
								new File(jpgPath[j].substring(0,
										jpgPath[j].lastIndexOf("."))
										+ "_" + k + ".jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// return outputPath;
	}

}
