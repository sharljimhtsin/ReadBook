package org.readbook.utils;

import android.util.Log;

/**
 * 提供程序开发过程中的日志打印
 * isShow标示是否启用log功能
 * @author dingmaolin
 *
 */
public class LogUtil {

	private static boolean isShow = true;
	public static String TAG = "readbook";

	public static void print(String str) {
		if (isShow) {
			System.out.println(str);
		}
	}

	public static void logI(String tag, String msg) {
		if (isShow) {
			Log.i(tag, msg);
		}
	}

	public static void logD(String tag, String msg) {
		if (isShow) {
			Log.d(tag, msg);
		}
	}

	public static void logV(String tag, String msg) {
		if (isShow) {
			Log.v(tag, msg);
		}
	}

	public static void logW(String tag, String msg) {
		if (isShow) {
			Log.w(tag, msg);
		}
	}

	public static void logE(String tag, String msg) {
		if (isShow) {
			Log.e(tag, msg);
		}
	}

}
