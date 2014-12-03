package org.readbook.task;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
public class BaseTask extends AsyncTask<String, Void, Void> {

	private final static String MD5_SALT = "diaozatian_luomiou_xiie";
	protected Handler handler;
	protected BaseRequest request;
	protected Map<String, Object> map;
	protected HttpHelper httpHelper;
	protected List<String> skip;

	public BaseTask(BaseRequest request, Handler handler) {
		this.request = request;
		this.handler = handler;
	}

	/**
	 * 初始化全局传递参数
	 * 
	 */
	protected void setRequestParams() {
		MyApplication application = MyApplication.getInstance();
		// basic fields
		int client = 2; // for Android
		String identifer = application.getPhoneIMEI();
		String jpushId = application.getjPushId();
		String osVersion = application.getOSVersion();
		String deviceName = application.getDeviceName();
		String deviceBrand = application.getDeviceBrand();
		String province = application.getLocationProvince();
		String city = application.getLocationCity();
		String area = application.getLocationArea();
		String appVersion = application.getVersion();
		String qudao = application.getQudao();
		// extend fields
		String deviceID = request.getDeviceID();
		String phoneNumber = request.getPhoneNumber();
		String qq = request.getQq();
		String name = request.getName();
		String email = request.getEmail();
		// logic fields
		int sex = request.getSex();
		int age = request.getAge();
		String articleId = request.getArticleId();
		String classId = request.getDocCategoryId();
		String endTime = request.getEndTime();

		map = new LinkedHashMap<String, Object>();
		map.put("client", client);
		map.put("identifer", identifer);
		map.put("jpushId", jpushId);
		map.put("osVersion", osVersion);
		map.put("deviceName", deviceName);
		map.put("deviceBrand", deviceBrand);
		map.put("appVersion", appVersion);
		map.put("province", province);
		map.put("city", city);
		map.put("area", area);
		map.put("qudao", qudao);
		map.put("deviceID", deviceID);
		map.put("phoneNumber", phoneNumber);
		map.put("qq", qq);
		map.put("name", name);
		map.put("email", email);
		map.put("sex", sex);
		map.put("age", age);
		map.put("articleId", articleId);
		map.put("classId", classId);
		map.put("endTime", endTime);
		map.put("verify", MD5_SALT);

		skip = new ArrayList<String>();
		skip.add("deviceName");
		skip.add("deviceBrand");
		skip.add("qudao");
		skip.add("area");
		skip.add("deviceID");
		skip.add("phoneNumber");
		skip.add("qq");
		skip.add("name");
		skip.add("email");
		skip.add("sex");
		skip.add("age");

		String verifyString = "";
		for (String key : map.keySet()) {
			String tmp = String.valueOf(map.get(key));
			// check if the parameter should avoid MD5 calculate
			if (!skip.contains(key)) {
				verifyString += tmp;
			}
			// URLEncode Chinese character in HTTP-Header
			if (isHanzi(tmp)) {
				try {
					map.put(key, URLEncoder.encode(tmp, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		String verify = MD5.encode(verifyString);
		map.put("verify", verify);
		httpHelper = new HttpHelper(map);
		LogUtil.logD(LogUtil.TAG, "------ BaseRequest -------" + map.toString());
	}

	public boolean isHanzi(String s) {
		return s.getBytes().length != s.length();
	}

	@Override
	protected Void doInBackground(String... params) {
		return null;
	}
}
