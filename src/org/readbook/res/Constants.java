package org.readbook.res;

import org.readbook.R;

/**
 * @author ding
 * @modify_by Administrator at 2014年11月12日 下午2:48:08
 * 
 */
public class Constants {

	public static String version = "1.0";
	public static String net_error = "请检查网络";
	public static int TAG_VIEW = R.string.tag_view;
	public static int TAG_DATA = R.string.tag_data;

	public static final class Host {
		public final static String host = "http://42.121.137.60:44778";
		// Index
		public final static String index = host + "/Home/Index/index";
		// Task
		public final static String getAvailable = host
				+ "/Home/Task/getAvailable";
	}

	public static final class ShareRefrence {
		public final static String SHAREREFRENCE_NAME = "readbook";
		// /
		public final static String shareText = "shareText";
		public final static String shareUrl = "shareUrl";

		public final static String locationProvince = "locationProvince";
		public final static String locationCity = "locationCity";
		public final static String locationArea = "locationArea";
	}
}
