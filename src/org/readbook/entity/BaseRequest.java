package org.readbook.entity;

/**
 * @author Administrator
 *
 */
public class BaseRequest {
	/* 初始化全局传递参数 */
	private String phoneNumber = ""; // 手机号码
	private String qq = ""; // QQ号码
	private String name = ""; // 用户真实姓名
	private String sex = "1"; // 性别|0:女|1:男
	private String age = ""; // 用户年龄段
	private String email = ""; // 用户email
	// extend parameters
	private String docTypeId = "";
	private String docCategoryId = "";
	private String articleId = "";

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
	 * @return the docTypeId
	 */
	public String getDocTypeId() {
		return docTypeId;
	}

	/**
	 * @param docTypeId
	 *            the docTypeId to set
	 */
	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**
	 * @return the docCategoryId
	 */
	public String getDocCategoryId() {
		return docCategoryId;
	}

	/**
	 * @param docCategoryId
	 *            the docCategoryId to set
	 */
	public void setDocCategoryId(String docCategoryId) {
		this.docCategoryId = docCategoryId;
	}

	/**
	 * @return the articleId
	 */
	public String getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId
	 *            the articleId to set
	 */
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
}