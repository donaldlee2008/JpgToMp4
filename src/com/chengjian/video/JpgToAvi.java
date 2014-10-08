package com.chengjian.video;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.jim2mov.core.DefaultMovieInfoProvider;
import org.jim2mov.core.ImageProvider;
import org.jim2mov.core.Jim2Mov;
import org.jim2mov.core.MovieInfoProvider;
import org.jim2mov.utils.MovieUtils;

public class JpgToAvi {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
		// jpgsĿ¼����jpgͼƬ,ͼƬ�ļ���Ϊ(1.jpg,2.jpg...)
		final File[] jpgs = new File("C:\\pic\\").listFiles();

//		// ���ļ�����������(��ʾ���ٶ��ļ����е�����ԽС,������Ƶ��֡��Խ��ǰ)
//		Arrays.sort(jpgs, new Comparator<File>() {
//			public int compare(File file1, File file2) {
//				String numberName1 = file1.getName().replace(".jpg", "");
//				String numberName2 = file2.getName().replace(".jpg", "");
//				return new Integer(numberName1) - new Integer(numberName2);
//			}
//		});

		DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider("Newdst.mp4");
		dmip.setFPS(0.5f); // ����ÿ��֡��
		dmip.setNumberOfFrames(jpgs.length); // ��֡��
		dmip.setMWidth(1440);
		dmip.setMHeight(860);

		new Jim2Mov(new ImageProvider() {
			public byte[] getImage(int frame) {
				try {
					// ����ѹ����
					return MovieUtils.convertImageToJPEG((jpgs[frame]), 1.0f);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}, dmip, null).saveMovie(MovieInfoProvider.TYPE_AVI_MJPEG);
	}
}
