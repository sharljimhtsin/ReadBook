package org.readbook.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CommonUtil {

	public static final int ZH_CN = 1; // 简体
	public static final int ZH_TW = 2; // 繁体
	public static final int EN = 3; // 英文

	public static int getLocaleLanguage() {
		Locale l = Locale.getDefault();
		String language = String.format("%s-%s", l.getLanguage(),
				l.getCountry());
		if (language.equalsIgnoreCase("zh-CN")) {
			return 1;
		} else if (language.equalsIgnoreCase("zh-HK")
				|| language.equalsIgnoreCase("zh-TW")) {
			return 2;
		}
		return 3;
	}

	/**
	 * 通过反射取值
	 * 
	 * @param packageName
	 * @param className
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static int getResourseIdByName(String packageName, String className,
			String name) {
		Class r = null;
		int id = 0;
		try {
			r = Class.forName(packageName + ".R");
			Class[] classes = r.getClasses();
			Class desireClass = null;
			for (int i = 0; i < classes.length; i++) {
				if (classes[i].getName().split("\\$")[1].equals(className)) {
					desireClass = classes[i];
					break;
				}
			}
			if (desireClass != null)
				id = desireClass.getField(name).getInt(desireClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return id;

	}

	/**
	 * 获取字符串高度
	 * 
	 * @param fontSize
	 *            字体大小
	 * @return 返回字体高度
	 */
	public static int getFontHeight(float fontSize) {
		Paint m_paint = new Paint();
		m_paint.setTextSize(fontSize);
		FontMetrics fm = m_paint.getFontMetrics();
		return (int) Math.ceil(fm.descent - fm.top) + 2;
	}

	/**
	 * 获得字符串的长度
	 * 
	 * @param str
	 *            字符串
	 * @param fontSize
	 *            字体大小
	 * @return 返回字符串的长度
	 */
	public static int getStrWidth(String str, float fontSize) {
		Paint m_paint = new Paint();
		m_paint.setTextSize(fontSize);
		char ch;
		int width = 0;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			float[] widths = new float[1];
			String srt = String.valueOf(ch);
			m_paint.getTextWidths(srt, widths);
			width += (int) (Math.ceil(widths[0]));
		}
		return width;
	}

	public static byte[] file2Bytes(String path) {
		try {
			FileInputStream fin = new FileInputStream(path);
			// FileInputStream fin = openFileInput(fileName);
			// 用这个就不行了，必须用FileInputStream
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			fin.close();
			fin = null;
			return buffer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static byte[] file2Bytes(File file) {
		try {
			FileInputStream fin = new FileInputStream(file);
			// FileInputStream fin = openFileInput(fileName);
			// 用这个就不行了，必须用FileInputStream
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			fin.close();
			fin = null;
			return buffer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * dp值转化为px像素值
	 * 
	 * @param context
	 *            上下文对象
	 * @param dipValue
	 *            dp值
	 * @return 返回 输入dp值所对应的像素值
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px值转化为像素值
	 * 
	 * @param context
	 * @param pxValue
	 *            px值
	 * @return dp值
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}
	
	 // 取得AppKey
    public static String getAppKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai)
                metaData = ai.metaData;
            if (null != metaData) {
                appKey = metaData.getString("JPUSH_APPKEY");
                if ((null == appKey) || appKey.length() != 24) {
                    appKey = null;
                }
            }
        } catch (NameNotFoundException e) {

        }
        return appKey;
    }

}
