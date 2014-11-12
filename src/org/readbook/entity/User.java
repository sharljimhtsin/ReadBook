/**
 * 
 */
package org.readbook.entity;

import com.avos.avoscloud.AVUser;

/**
 * @author Administrator
 *         <p>
 *         AVUser user = new AVUser(); <br>
 *         user.setUsername("steve");<br>
 *         user.setPassword("f32@ds*@&dsa");<br>
 *         user.setEmail("steve@company.com");<br>
 *         user.setMobilePhoneNumber("123456");
 *         </p>
 */

public class User extends AVUser {
	private String alias;
	private int age;
	private int sex;
	private String qq;
	private String province;
	private String city;
	private String area;
	private String job;
	private String refer;
	private int level;
	private int point;
	private int totalPoint;
	private int role;

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return getString("alias");
	}

	/**
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		put("alias", alias);
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return getInt("age");
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		put("age", age);
	}

	/**
	 * @return the qq
	 */
	public String getQq() {
		return getString("qq");
	}

	/**
	 * @param qq
	 *            the qq to set
	 */
	public void setQq(String qq) {
		put("qq", qq);
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return getString("province");
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		put("province", province);
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return getString("city");
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		put("city", city);
		;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return getString("area");
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		put("area", area);
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return getString("job");
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(String job) {
		put("job", job);
	}

	/**
	 * @return the refer
	 */
	public String getRefer() {
		return getString("refer");
	}

	/**
	 * @param refer
	 *            the refer to set
	 */
	public void setRefer(String refer) {
		put("refer", refer);
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return getInt("level");
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		put("level", level);
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return getInt("point");
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(int point) {
		put("point", point);
	}

	/**
	 * @return the totalPoint
	 */
	public int getTotalPoint() {
		return getInt("totalPoint");
	}

	/**
	 * @param totalPoint
	 *            the totalPoint to set
	 */
	public void setTotalPoint(int totalPoint) {
		put("totalPoint", totalPoint);
	}

	/**
	 * @return the role
	 */
	public int getRole() {
		return getInt("role");
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(int role) {
		put("role", role);
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return getInt("sex");
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		put("sex", sex);
	}
}
