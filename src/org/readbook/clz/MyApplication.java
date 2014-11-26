package org.readbook.clz;

import java.io.File;
import java.util.UUID;

import org.readbook.res.Constants;
import org.readbook.utils.SdcardManager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class MyApplication extends Application {

	private static MyApplication instance;
	private SharedPreferences sharedPreferences;
	public LocationClient mLocationClient;
	public MyLocationListener mMyLocationListener;
	private String locationProvince = "";
	private String locationCity = "";
	private String locationArea = "";

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		sharedPreferences = getSharedPreferences(
				Constants.ShareRefrence.SHAREREFRENCE_NAME,
				Context.MODE_PRIVATE);
		SdcardManager.prepare();
		initImageLoader(this);
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		InitLocation();
	}

	public static MyApplication getInstance() {
		return instance;
	}

	public String getLocationProvince() {
		if (TextUtils.isEmpty(locationProvince)) {
			return sharedPreferences.getString(
					Constants.ShareRefrence.locationProvince, "");
		}
		return locationProvince;
	}

	public String getLocationCity() {
		if (TextUtils.isEmpty(locationCity)) {
			return sharedPreferences.getString(
					Constants.ShareRefrence.locationCity, "");
		}
		return locationCity;
	}

	public String getLocationArea() {
		if (TextUtils.isEmpty(locationArea)) {
			return sharedPreferences.getString(
					Constants.ShareRefrence.locationArea, "");
		}
		return locationArea;
	}

	/**
	 * 退出App
	 */
	public void exitAccount() {
		Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}

	public void showToast(Activity activity, String text) {
		Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
	}

	public void showToast(Activity activity, int resId) {
		Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show();
	}

	public static void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getCacheDirectory(context);
		// File cacheDir = SdcardManager.getImageCacheFile();
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				// .discCacheFileNameGenerator(new Md5FileNameGenerator())
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.discCache(new UnlimitedDiscCache(cacheDir)).build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	/**
	 * User-Agent
	 * 
	 * @return user-agent
	 */
	public String getUser_Agent() {
		String ua = "Android;" + getOSVersion() + ";" + getVersion() + ";"
				+ getDeviceBrand() + "-" + getDeviceName();
		return ua;
	}

	/**
	 * device model name, e.g: GT-I9100
	 * 
	 * @return the user_Agent
	 */
	public String getDeviceName() {
		return Build.MODEL;
	}

	/**
	 * device factory name, e.g: Samsung
	 * 
	 * @return the vENDOR
	 */
	public String getDeviceBrand() {
		return Build.BRAND;
	}

	/**
	 * @return the SDK version
	 */
	public int getSDKVersion() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * @return the OS version
	 */
	public String getOSVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * Retrieves application's version number from the manifest
	 * 
	 * @return versionName
	 */
	public String getVersion() {
		String version = "0.0.0";
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			version = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}

	/**
	 * Retrieves application's version code from the manifest
	 * 
	 * @return versionCode
	 */
	public int getVersionCode() {
		int code = 1;
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			code = packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return code;
	}

	public SharedPreferences getSharedPreferences() {
		return sharedPreferences;
	}

	public String getPhoneIMEI() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		// int ki = telephonyManager.getPhoneType();
		// if(telephonyManager.getPhoneType() ==
		// TelephonyManager.PHONE_TYPE_NONE)
		// {
		//
		// }
		boolean isImei = check(imei);
		if (!TextUtils.isEmpty(imei)) {
			isImei = check(imei);
			if (isImei)
				return imei;
		}

		if (TextUtils.isEmpty(imei) || !isImei) {
			// start get mac address
			WifiManager wifiMan = (WifiManager) this
					.getSystemService(Context.WIFI_SERVICE);
			if (wifiMan != null) {
				WifiInfo wifiInf = wifiMan.getConnectionInfo();
				if (wifiInf != null && wifiInf.getMacAddress() != null) {
					imei = wifiInf.getMacAddress().replaceAll(":", "");
					return imei;
				}
			}
		}
		if (TextUtils.isEmpty(imei) || !isImei) {
			imei = UUID.randomUUID().toString().replaceAll("-", "");
		}
		return imei;
	}

	private boolean check(String str) {
		if (!TextUtils.isEmpty(str)) {
			for (int i = 0; i < str.length(); i++) {
				char temp = str.charAt(i);
				if (temp != '0') {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEmulator() {
		return "sdk".equals(Build.PRODUCT);
	}

	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null) {
				return;
			}
			locationProvince = location.getProvince();
			locationCity = location.getCity();
			locationArea = location.getDistrict();
			if (!TextUtils.isEmpty(locationProvince)) {
				Editor editor = sharedPreferences.edit();
				editor.putString(Constants.ShareRefrence.locationProvince,
						locationProvince);
				editor.putString(Constants.ShareRefrence.locationCity,
						locationCity);
				editor.putString(Constants.ShareRefrence.locationArea,
						locationArea);
				editor.commit();
			}
		}
	}

	private void InitLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Battery_Saving);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(100000);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}

}
