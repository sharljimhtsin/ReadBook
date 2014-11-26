package org.readbook.entity;

/**
 * @author Administrator
 *
 */
public class BaseRequest {
	private String deviceID = "";
	// common parameters
	private int page = 1;
	private int pageSize = 20;
	// user auth parameters
	private String phoneNumber = ""; // 手机号码
	private String qq = ""; // QQ号码
	private String name = ""; // 用户真实姓名
	private String email = ""; // 用户email
	private String password = "";
	// user info parameters
	private int sex = 1; // 性别|0:女|1:男
	private int age = 0; // 用户年龄段
	// article parameters
	private int docTypeId = 0; // 段子类别
	private int docCategoryId = 0; // 段子类型
	private int articleId = 0; // 段子id

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
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the docTypeId
	 */
	public int getDocTypeId() {
		return docTypeId;
	}

	/**
	 * @param docTypeId
	 *            the docTypeId to set
	 */
	public void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**
	 * @return the docCategoryId
	 */
	public int getDocCategoryId() {
		return docCategoryId;
	}

	/**
	 * @param docCategoryId
	 *            the docCategoryId to set
	 */
	public void setDocCategoryId(int docCategoryId) {
		this.docCategoryId = docCategoryId;
	}

	/**
	 * @return the articleId
	 */
	public int getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId
	 *            the articleId to set
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

}