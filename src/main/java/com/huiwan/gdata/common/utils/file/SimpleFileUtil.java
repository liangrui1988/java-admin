package com.huiwan.gdata.common.utils.file;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class SimpleFileUtil {

	private static final Integer photoSize = 5242880;// 5MB 1024*1024*2=2097152

	// 获得指定文件的byte数组
	public static byte[] getBytes(File file) {
		byte[] buffer = null;
		try {
			// File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public static boolean checkingPhoto(MultipartFile mf) {

		try {

			if (!isImage(mf.getInputStream())) {
				// fileInfo.add("请上传有效图片文件");//提示信息
				return false;
			}
			if (mf.getSize() > photoSize) {

				// 2^10=1024
				// PromptSize = "(最大限制:" + (FileUpload.photoSize >> 20) +
				// "MB)";//提示信息
				// fileInfo.add("图片大于"+(new

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean isImage(File imageFile) {
		if (!imageFile.exists()) {
			return false;
		}
		Image img = null;
		try {
			img = ImageIO.read(imageFile);
			if (img == null || img.getWidth(null) <= 0
					|| img.getHeight(null) <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			img = null;
		}
	}

	public static boolean isImage(InputStream imageFile) {
		Image img = null;
		try {
			img = ImageIO.read(imageFile);
			if (img == null || img.getWidth(null) <= 0
					|| img.getHeight(null) <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			img = null;
		}
	}
}
