package org.readbook.res;

/**
 * 极光
 * 应用标识(AppKey)
d4c1235bb8fc78ca6a0eec34
API MasterSecret
8bfc3dea841bc2af182d3696 
应用包名(Android)
com.dml.myapp.qhf 

66D62507105B2C8825C223CB8D0C3D1F
 * @author ding
 * 
 */
public class Constants {

	public static String version = "1.0";
	public static String net_error = "请检查网络";
	
	public static final class Host {
		public final static String host = "http://42.121.137.60:44778";
		//Index
		public final static String index = host + "/Home/Index/index";
		//Task
		public final static String getAvailable = host + "/Home/Task/getAvailable";
		public final static String getReview = host + "/Home/Task/getReview";
		public final static String getSuccess = host + "/Home/Task/getSuccess";
		public final static String getFailed = host + "/Home/Task/getFailed";
		public final static String getDetail = host + "/Home/Task/getDetail";
		public final static String alreadyInstall = host + "/Home/Task/alreadyInstall";
		public final static String getDownload = host + "/Home/Task/getDownload";
		public final static String finishInstall = host + "/Home/Task/finishInstall";
		public final static String submitTask = host + "/Home/Task/submitTask";
		// Setting
		public final static String bindAccount = host + "/Home/Setting/bindAccount";
		public final static String getUserInfo = host + "/Home/Setting/getUserInfo";
		public final static String setAlipayInfo = host + "/Home/Setting/setAlipayInfo";
		public final static String setQQInfo = host + "/Home/Setting/setQQInfo";
		public final static String setUserInfo = host + "/Home/Setting/setUserInfo";
		//Account
		public final static String chargeAlipay = host + "/Home/Account/chargeAlipay";
		public final static String chargeMobile = host + "/Home/Account/chargeMobile";
		public final static String chargeQBee = host + "/Home/Account/chargeQBee";
		public final static String getAccountInfo = host + "/Home/Account/getAccountInfo";
		public final static String getFlow = host + "/Home/Account/getFlow";
		public final static String whetherCharge = host + "/Home/Account/whetherCharged";
		//Weekaward
		public final static String award = host + "/Home/Weekaward/award";
		public final static String getAwardList = host + "/Home/Weekaward/getList";
		
		public final static String getOfferList = host + "/Home/Offers/switcher";
	}

	public static final class ShareRefrence {
		public final static String SHAREREFRENCE_NAME = "readbook";
		public final static String deviceID = "deviceID";//设备ID
		public final static String bindStatus = "bindStatus";//该设备绑定状态 1未绑定  0已绑定
		////////////////////////////////////////////////
		public final static String phoneNumber = "phoneNumber";//绑定的手机号码
		public final static String qq = "qq"; // QQ号码
		public final static String name = "name"; // 姓名
		public final static String sex = "sex"; // 性别|0:女|1:男
		public final static String age = "age"; // 用户年龄段
		public final static String email = "email"; // 用户email
		///
		public final static String shareText = "shareText";
		public final static String shareUrl = "shareUrl";
		
		public final static String locationProvince = "locationProvince"; 
		public final static String locationCity = "locationCity"; 
	}
}
