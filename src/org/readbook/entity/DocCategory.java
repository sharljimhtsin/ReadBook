/**
 * 
 */
package org.readbook.entity;

/**
 * @author Administrator
 *
 */

public class DocCategory {
	private int class_id;
	private String class_name;
	private String subTitle;
	private String url;
	private int totalCount;
	private int parentType;

	/**
	 * @param id
	 * @param title
	 * @param subTitle
	 * @param url
	 * @param totalCount
	 * @param parentType
	 */
	public DocCategory(int id, String title, String subTitle, String url,
			int totalCount, int parentType) {
		super();
		this.class_id = id;
		this.class_name = title;
		this.subTitle = subTitle;
		this.url = url;
		this.totalCount = totalCount;
		this.parentType = parentType;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return class_name;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.class_name = title;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle
	 *            the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the parentType
	 */
	public int getParentType() {
		return parentType;
	}

	/**
	 * @param parentType
	 *            the parentType to set
	 */
	public void setParentType(int parentType) {
		this.parentType = parentType;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return class_id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.class_id = id;
	}
}
