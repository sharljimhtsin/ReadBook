package org.readbook.task;

import java.util.LinkedHashMap;
import java.util.Map;

import org.readbook.clz.MyApplication;
import org.readbook.entity.BaseRequest;
import org.readbook.utils.LogUtil;
import org.readbook.utils.md5.MD5;
import org.readbook.utils.net.http.HttpHelper;

import android.os.AsyncTask;
import android.os.Handler;

/**
 * 任务基类
 * 
 * @author dingmaolin
 * @modify_by Administrator at 2014年11月10日 下午2:37:52
 * 
 */
public class BaseTask extends AsyncTask<Void, Void, Void> {

	private final static String key = "abcd";
	protected Handler handler;
	protected BaseRequest request;
	protected Map<String, Object> map;
	protected HttpHelper httpHelper;

	public BaseTask(BaseRequest request, Handler handler) {
		this.request = request;
		this.handler = handler;
	}

	/**
	 * 初始化全局传递参数
	 * 
	 * @deprecated
	 */
	protected void setRequestParams() {
		httpHelper = new HttpHelper();
		MyApplication application = MyApplication.getInstance();
		// basic fields
		String imei = application.getPhoneIMEI();
		String os_version = application.getOSVersion();
		String device_name = application.getDeviceName();
		String device_brand = application.getDeviceBrand();
		String province = application.getLocationProvince();
		String city = application.getLocationCity();
		String area = application.getLocationArea();
		String appversion = application.getVersion();
		// extend fields
		String deviceID = request.getDeviceID();
		String phoneNumber = request.getPhoneNumber();
		String qq = request.getQq();
		String name = request.getName();
		String email = request.getEmail();
		// logic fields
		int sex = request.getSex();
		int age = request.getAge();
		String docTypeId = request.getDocTypeId();
		String docCategoryId = request.getDocCategoryId();
		String articleId = request.getArticleId();

		map = new LinkedHashMap<String, Object>();
		map.put("imei", imei);
		map.put("os_version", os_version);
		map.put("device_name", device_name);
		map.put("device_brand", device_brand);
		map.put("province", province);
		map.put("city", city);
		map.put("area", area);
		map.put("appversion", appversion);
		map.put("deviceID", deviceID);
		map.put("phoneNumber", phoneNumber);
		map.put("qq", qq);
		map.put("name", name);
		map.put("email", email);
		map.put("sex", sex);
		map.put("age", age);
		map.put("docTypeId", docTypeId);
		map.put("docCategoryId", docCategoryId);
		map.put("articleId", articleId);
		map.put("verify", key);

		String verifyString = "";
		for (Object s : map.values()) {
			verifyString += String.valueOf(s);
		}
		String verify = MD5.encode(verifyString);
		map.put("verify", verify);
		LogUtil.logD(LogUtil.TAG, "------ BaseRequest -------" + map.toString());
	}

	@Override
	protected Void doInBackground(Void... params) {
		handler.sendMessage(handler.obtainMessage(1));
		return null;
	}
}
