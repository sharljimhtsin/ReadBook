package org.readbook.entity;

public class BaseRequest {
	/* 初始化全局传递参数 */
	private String imei = "";// 设备唯一标识
	private String os_version = ""; // 系统版本
	private String device_name = ""; // 设备名称
	private String device_brand = ""; // 设备厂商
	private String province = ""; // 省份名
	private String city = ""; // 城市名
	private String appversion = ""; // 版本号
	private String deviceID = ""; // 设备ID
	private String phoneNumber = ""; // 手机号码
	private String qq = ""; // QQ号码
	private String name = ""; // 用户真实姓名
	private String sex = "1"; // 性别|0:女|1:男
	private String age = ""; // 用户年龄段
	private String email = ""; // 用户email
	private String verify = ""; // (所有客户端传递过来的参数+密钥)的MD5值

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei
	 *            the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the os_version
	 */
	public String getOs_version() {
		return os_version;
	}

	/**
	 * @param os_version
	 *            the os_version to set
	 */
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	/**
	 * @return the device_name
	 */
	public String getDevice_name() {
		return device_name;
	}

	/**
	 * @param device_name
	 *            the device_name to set
	 */
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	/**
	 * @return the device_brand
	 */
	public String getDevice_brand() {
		return device_brand;
	}

	/**
	 * @param device_brand
	 *            the device_brand to set
	 */
	public void setDevice_brand(String device_brand) {
		this.device_brand = device_brand;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the appversion
	 */
	public String getAppversion() {
		return appversion;
	}

	/**
	 * @param appversion
	 *            the appversion to set
	 */
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}

	/**
	 * @param deviceID
	 *            the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq
	 *            the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the verify
	 */
	public String getVerify() {
		return verify;
	}

	/**
	 * @param verify
	 *            the verify to set
	 */
	public void setVerify(String verify) {
		this.verify = verify;
	}
}