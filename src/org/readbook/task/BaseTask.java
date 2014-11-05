package org.readbook.task;

import java.util.HashMap;
import java.util.Map;

import org.readbook.clz.MyApplication;
import org.readbook.entity.BaseRequest;
import org.readbook.utils.LogUtil;
import org.readbook.utils.md5.MD5;
import org.readbook.utils.net.http.HttpHelper;

import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;

/**
 * 任务基类
 * 
 * @author dingmaolin
 * 
 */
public class BaseTask extends AsyncTask<Void, Void, Void> {

	private final static String key = "abcd";
	protected Handler handler;
	protected BaseRequest request;
	protected Map<String, String> map;
	protected HttpHelper httpHelper;

	public BaseTask(BaseRequest request, Handler handler) {
		this.request = request;
		this.handler = handler;
	}

	/**
	 * 初始化全局传递参数
	 *  $this->imei = I('post.imei',''); //设备唯一标识
	 *  $this->os_version = I('post.os_version',''); //系统版本 
	 *  $this->device_name = I('post.device_name',''); //设备名称
	 *  $this->device_brand = I('post.device_brand',''); //设备厂商
	 *  $this->province = I('post.province',''); //省份名 
	 *  $this->city = I('post.city',''); //城市名
	 *  $this->appversion = I('post.appversion',''); //赚话费版本号
	 *  $this->jid = I('post.jid',''); //Jpush标识 
	 *  $this->deviceID = I('post.deviceID','');//设备ID 
	 *  $this->taskID = I('post.taskID',''); //任务ID 
	 *  $this->phoneNumber = I('post.phoneNumber',''); //手机号码
	 *  $this->qq = I('post.qq',''); //QQ号码
	 *  $this->alipayAccount = I('post.alipayAccount',''); //支付宝账号
	 *  $this->alipayName = I('post.alipayName',''); //支付宝姓名 
	 *  $this->realName = I('post.realName',''); //用户真实姓名
	 *  $this->sex = I('post.sex',''); //性别|0:女|1:男 
	 *  $this->age = I('post.age',''); //用户年龄段 
	 *  $this->email = I('post.email',''); //用户email 
	 *  $this->money = I('post.money',''); //用户提现金额
	 *  $this->award = I('post.award',''); //每日签到奖励金额 
	 *  $this->verify = I('post.verify',''); //(所有客户端传递过来的参数+密钥)的MD5值
	 */
	protected void setRequestParams() {
		httpHelper = new HttpHelper();
		MyApplication application = MyApplication.getInstance();
		String imei = application.getPhoneIMEI();
		String os_version = application.getOSVersion();
		String device_name = application.getDeviceName();
		String device_brand = application.getDeviceBrand();
		String province = application.getLocationProvince();
		String city = application.getLocationCity();
		String appversion = application.getVersion();
		String deviceID = application.getDeviceID();
		String phoneNumber = request.getPhoneNumber();
		if (TextUtils.isEmpty(phoneNumber)) {
			phoneNumber = application.getPhoneNumber();
		}
		String qq = request.getQq();
		if (TextUtils.isEmpty(qq)) {
			qq = application.getQQ();
		}
		String name = request.getName();
		String sex = request.getSex();
		String age = request.getAge();
		String email = request.getEmail();

		map = new HashMap<String, String>();
		map.put("imei", imei);
		map.put("os_version", os_version);
		map.put("device_name", device_name);
		map.put("device_brand", device_brand);
		map.put("province", province);
		map.put("city", city);
		map.put("appversion", appversion);
		map.put("deviceID", deviceID);
		map.put("phoneNumber", phoneNumber);
		map.put("qq", qq);
		map.put("name", name);
		map.put("sex", sex);
		map.put("age", age);
		map.put("email", email);

		String verifyString = imei + os_version + device_name + device_brand
				+ province + city + appversion + deviceID + phoneNumber + qq
				+ name + sex + age + email + key;
		String verify = MD5.encode(verifyString);
		map.put("verify", verify);
		LogUtil.logD(LogUtil.TAG, "------ BaseRequest -------" + map.toString());
	}

	@Override
	protected Void doInBackground(Void... params) {
		return null;
	}
}
