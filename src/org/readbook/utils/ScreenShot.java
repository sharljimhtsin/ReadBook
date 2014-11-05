package org.readbook.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

/**
 * 截图工具类
 * @author dingmaolin
 *
 */
public class ScreenShot {
	// 获取指定Activity的截屏，保存到png文件
	private static Bitmap takeScreenShot(Activity activity) {
		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();

		// 获取状态栏高度
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		Log.i("TAG", "" + statusBarHeight);
		int width = frame.width();
		int height = frame.height();
//		// 获取屏幕长和高
//		int width = activity.getWindowManager().getDefaultDisplay().getWidth();
//		int height = activity.getWindowManager().getDefaultDisplay()
//				.getHeight();
//		// 去掉标题栏
//		// Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
//		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
//				- statusBarHeight);
		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
				- statusBarHeight);
		view.destroyDrawingCache();
		return b;
	}

	// 保存到sdcard
	private static void savePic(Bitmap b, String strFileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(strFileName);
			if (null != fos) {
				b.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 程序入口
	public static void shoot(Activity a, final String path) {
		ScreenShot.savePic(ScreenShot.takeScreenShot(a), path);
	}

	/**
	 * 图片保存到sdcard
	 */
	public static void saveImage(final Bitmap bitmap, final String imageName) {
		File file = new File(SdcardManager.getRootFilePath(), imageName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(file);
			output.write(BitmapHelper.bitmap2Bytes(bitmap));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 对控件截图。
	 * 
	 * @param v
	 *            需要进行截图的控件。
	 * @param quality
	 *            图片的质量
	 * @return 该控件截图的byte数组对象。
	 */
	public static byte[] printScreen(View v, int quality) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache(true);
		Bitmap bitmap = v.getDrawingCache();
		bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		return baos.toByteArray();
	}

	/**
	 * 截图
	 * 
	 * @param v
	 *            需要进行截图的控件
	 * @return 该控件截图的Bitmap对象。
	 */
	public static Bitmap printScreen(View v) {
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache();
		return v.getDrawingCache();
	}
}
