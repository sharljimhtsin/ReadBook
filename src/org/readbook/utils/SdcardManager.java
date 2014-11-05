package org.readbook.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

/**
 * sdcard 初始化管理类
 * @author dingmaolin
 *
 */
public class SdcardManager {
	
	private static String root = "zhuaihuafei";

	public static boolean isSDCARDReadyReadWrite() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			return true;
		} else
			return false;
	}

	public static void prepare() {
		File rootFile = new File(Environment.getExternalStorageDirectory() + File.separator + root);
		rootFile.mkdirs();
	}

	public static String getRootFilePath() {
		return Environment.getExternalStorageDirectory() + File.separator + root;
	}

	public static String getUniqueFileName(String rootDir, String fileName) {
		if (TextUtils.isEmpty(fileName))
			return String.valueOf(System.currentTimeMillis());
		int i = 1;
		while (true) {
			File file = new File(rootDir + "/" + fileName);
			if (file.exists()) {
				int potindex = fileName.indexOf(".");
				if (potindex == -1)
					fileName = fileName + "(" + String.valueOf(i) + ")";
				else {
					fileName = fileName.substring(0, potindex) + "("
							+ String.valueOf(i) + ")"
							+ fileName.substring(potindex);
				}
			} else {
				break;
			}
			i++;
		}
		return fileName;

	}

	public static String getCacheDir(Context context) {
		File cacheDir = context.getCacheDir();
		return cacheDir.getAbsolutePath();

	}

}
